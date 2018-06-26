package org.freedombox.freedombox.views.activities

import android.os.Bundle
import org.freedombox.freedombox.R
import org.freedombox.freedombox.models.Shortcut
import org.freedombox.freedombox.views.fragments.AboutFragment

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "About"
        loadFragment(R.id.rootLayout, AboutFragment.new(savedInstanceState ?: Bundle()))
    }
}