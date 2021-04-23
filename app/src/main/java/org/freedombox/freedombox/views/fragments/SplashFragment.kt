// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import org.freedombox.freedombox.R
import org.freedombox.freedombox.components.AppComponent
import org.freedombox.freedombox.utils.storage.getConfiguredBoxesMap
import org.freedombox.freedombox.utils.storage.getSharedPreference
import org.freedombox.freedombox.views.activities.DiscoveryActivity
import org.freedombox.freedombox.views.activities.LauncherActivity
import org.freedombox.freedombox.views.model.ConfigModel
import javax.inject.Inject

class SplashFragment : BaseFragment() {
    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun getLayoutId() = R.layout.fragment_splash

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(getDefaultBox() != null) {
            val intent = Intent(activity, LauncherActivity::class.java)
            intent.putExtra(getString(R.string.current_box), getDefaultBox())
            startActivity(intent)
        } else {
            val intent = Intent(activity, DiscoveryActivity::class.java)
            Handler().postDelayed({ startActivity(intent) }, 1000)
        }
    }

    private fun getDefaultBox(): ConfigModel? {
        val configuredBoxesJSON = getSharedPreference(sharedPreferences,
                getString(R.string.saved_boxes))

        return configuredBoxesJSON?.let {
            if (configuredBoxesJSON.isBlank()) null else {
            val configuredBoxMap = getConfiguredBoxesMap(configuredBoxesJSON)
            configuredBoxMap!!.entries.find { it.value.isDefault() }?.value }
        }

    }

    companion object {
        fun new(args: Bundle) = SplashFragment().apply { arguments = args }
    }

    override fun injectFragment(appComponent: AppComponent) = appComponent.inject(this)
}
