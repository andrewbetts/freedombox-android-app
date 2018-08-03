/*
 * This file is part of FreedomBox.
 *
 * FreedomBox is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FreedomBox is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FreedomBox. If not, see <http://www.gnu.org/licenses/>.
 */

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
