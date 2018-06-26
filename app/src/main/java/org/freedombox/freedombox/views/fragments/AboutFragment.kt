package org.freedombox.freedombox.views.fragments

import android.os.Bundle
import org.freedombox.freedombox.R
import org.freedombox.freedombox.components.AppComponent

class AboutFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_about

    override fun injectFragment(appComponent: AppComponent) = appComponent.inject(this)

    companion object {
        fun new(args: Bundle) = AboutFragment().apply{ arguments = args }
    }
}