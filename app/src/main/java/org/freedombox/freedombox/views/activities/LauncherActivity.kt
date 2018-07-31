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

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import org.freedombox.freedombox.R
import org.freedombox.freedombox.views.fragments.LauncherFragment
import org.freedombox.freedombox.views.model.ConfigModel

class LauncherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = LauncherFragment.new(savedInstanceState ?: Bundle())
        fragment.arguments = intent.extras
        supportFragmentManager.beginTransaction().add(android.R.id.content, fragment).commit()

        loadFragment(R.id.rootLayout, fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_launcher, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.getItemId() == R.id.action_edit) {
            val intent = Intent(this, SetupActivity::class.java)
            intent.putExtra(getString(R.string.current_box),
                    this.intent.extras.getParcelable<ConfigModel>(getString(R.string.current_box)))
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
