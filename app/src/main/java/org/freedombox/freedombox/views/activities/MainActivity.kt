// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.activities

import android.os.Bundle
import org.freedombox.freedombox.R
import org.freedombox.freedombox.views.fragments.SplashFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFragment(R.id.rootLayout, SplashFragment.new(savedInstanceState ?: Bundle()))
    }
}
