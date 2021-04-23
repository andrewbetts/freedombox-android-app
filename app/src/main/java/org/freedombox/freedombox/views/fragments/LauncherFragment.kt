// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.View
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_launcher.*
import org.freedombox.freedombox.R
import org.freedombox.freedombox.SERVICES_URL
import org.freedombox.freedombox.components.AppComponent
import org.freedombox.freedombox.models.Shortcuts
import org.freedombox.freedombox.utils.ImageRenderer
import org.freedombox.freedombox.utils.network.apiUrl
import org.freedombox.freedombox.utils.network.getApps
import org.freedombox.freedombox.utils.network.urlJoin
import org.freedombox.freedombox.utils.storage.getSharedPreference
import org.freedombox.freedombox.utils.storage.getShortcutsMap
import org.freedombox.freedombox.views.adapter.GridAdapter
import org.freedombox.freedombox.views.model.ConfigModel
import javax.inject.Inject

class LauncherFragment : BaseFragment() {

    @Inject lateinit var imageRenderer: ImageRenderer

    @Inject lateinit var sharedPreferences: SharedPreferences

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun getLayoutId() = R.layout.fragment_launcher

    private val gson: Gson = GsonBuilder().create()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currentBox = arguments!!.getParcelable<ConfigModel>("current_box")
        val adapter = GridAdapter(activity!!.applicationContext, imageRenderer, currentBox.domain)
        appGrid.adapter = adapter

        val onSuccess = fun(response: String) {
            val cachedShortcuts = getSharedPreference(sharedPreferences, getString(R.string.shortcuts))
            val shortcutsFromResponse = getShortcutsFromResponse(response)
            val updatedResponse = (getShortcutsMap(cachedShortcuts) ?: mapOf())
                    .plus(Pair(currentBox.boxName, shortcutsFromResponse))

            sharedPreferences.edit().putString(getString(R.string.shortcuts), gson.toJson(updatedResponse)).apply()
            val shortcuts = shortcutsFromResponse!!.shortcuts

            // Only show shortcuts which have either a web or a mobile client
            @Suppress("USELESS_ELVIS") // Shadowsocks has clients: null
            val shortcutsToDisplay = shortcuts.filter {
                (it.clients ?: listOf()).any{
                    (it.platforms ?: listOf()).any{
                        it.type in listOf("web", "store") } } }
            adapter.setData(shortcutsToDisplay)
        }

        val onFailure = fun() {
            val responses = getSharedPreference(sharedPreferences, getString(R.string.shortcuts))

            if (responses?.isBlank() != false) {
                appsNotAvailable.visibility = View.VISIBLE
            }
            else {
                val appResponseMap = getShortcutsMap(responses)!!

                if (appResponseMap.containsKey(currentBox.boxName))
                    adapter.setData(appResponseMap[currentBox.boxName]!!.shortcuts)
                else
                    appsNotAvailable.visibility = View.VISIBLE
            }
        }

        val servicesUrl = urlJoin(apiUrl(currentBox.domain), SERVICES_URL)
        getApps(context!!, servicesUrl, onSuccess, onFailure)

        swipeRefreshLayout = view!!.findViewById(R.id.launcherSwipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            getApps(context!!, servicesUrl, onSuccess, onFailure)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    companion object {
        fun new(savedInstanceState: Bundle): LauncherFragment {
            val fragment = LauncherFragment()
            fragment.arguments = savedInstanceState
            return fragment
        }
    }

    override fun injectFragment(appComponent: AppComponent) = appComponent.inject(this)

    private fun getShortcutsFromResponse(response: String): Shortcuts? {
        return gson.fromJson(response, Shortcuts::class.java)
    }
}
