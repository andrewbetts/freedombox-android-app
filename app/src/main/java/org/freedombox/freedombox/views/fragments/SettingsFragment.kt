// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.fragments

import android.os.Bundle
import org.freedombox.freedombox.R
import org.freedombox.freedombox.components.AppComponent

class SettingsFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun injectFragment(appComponent: AppComponent) = appComponent.inject(this)

    companion object {
        fun new(args: Bundle) = SettingsFragment().apply{ arguments = args }
    }
}