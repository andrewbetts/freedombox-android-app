// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.activities

import android.os.Bundle
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.aboutlibraries.ui.LibsActivity
import org.freedombox.freedombox.R

class AboutActivity : LibsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val aboutFreedomBox = getString(R.string.about_freedombox)
        val aboutApp = getString(R.string.about_app)

        val aboutDescription = """<p>$aboutFreedomBox<br/><br/>$aboutApp</p>"""

        val libsBuilder = LibsBuilder()
                .withLibraries("volley")
                .withActivityTheme(R.style.FreedomBoxTheme)
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withAboutDescription(aboutDescription)
                .withAboutAppName(getString(R.string.app_name))
                .withLicenseShown(true)
                .withActivityTitle(getString(R.string.about))

        intent = libsBuilder.intent(this)
        super.onCreate(savedInstanceState)
    }
}
