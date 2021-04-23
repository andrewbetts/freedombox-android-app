// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.activities

import android.os.Bundle
import org.freedombox.freedombox.R
import org.freedombox.freedombox.views.fragments.SettingsFragment

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Settings"
        loadFragment(R.id.rootLayout, SettingsFragment.new(savedInstanceState ?: Bundle()))
    }
}