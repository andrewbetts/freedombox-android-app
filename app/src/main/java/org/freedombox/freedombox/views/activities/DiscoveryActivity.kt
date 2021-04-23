// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.activities

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.freedombox.freedombox.R
import org.freedombox.freedombox.views.fragments.DiscoveryFragment

class DiscoveryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.servers)
        loadFragment(R.id.rootLayout, DiscoveryFragment.new(savedInstanceState ?: Bundle()))
        //val fab = findViewById(R.id.fab) as FloatingActionButton
    }
}
