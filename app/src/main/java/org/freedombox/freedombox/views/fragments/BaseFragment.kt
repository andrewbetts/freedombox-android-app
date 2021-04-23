// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.fragments

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.freedombox.freedombox.applications.FreedomBoxApp
import org.freedombox.freedombox.components.AppComponent
import org.freedombox.freedombox.views.IBaseView

abstract class BaseFragment : androidx.fragment.app.Fragment(), IBaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (activity!!.application as FreedomBoxApp).appComponent
        appComponent.inject(this)
        injectFragment(appComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
        inflater.inflate(getLayoutId(), container, false)

    protected abstract fun getLayoutId(): Int

    protected abstract fun injectFragment(appComponent: AppComponent)

    override fun showSnackMessage(message: String, duration: Int) {
        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            message,
            duration
        ).show()
    }
}
