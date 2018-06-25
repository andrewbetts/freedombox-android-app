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
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import org.freedombox.freedombox.R


abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mToolbarLayout: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)

        mDrawerLayout = findViewById(R.id.drawer_layout)

        mToolbarLayout = findViewById(R.id.app_toolbar)
        setSupportActionBar(mToolbarLayout)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun loadFragment(fragmentContainerId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
        this.supportFragmentManager.executePendingTransactions()

        val newTransaction = this.supportFragmentManager.beginTransaction()
        newTransaction.replace(fragmentContainerId, fragment, fragment.javaClass.name)

        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.name)
        }

        newTransaction.commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_switch_freedombox) {
            startActivity(Intent(this, DiscoveryActivity::class.java))
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_url)))
            startActivity(browserIntent)
        } else if (id == R.id.nav_website) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website_url)))
            startActivity(browserIntent)
        } else if (id == R.id.nav_contact) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.contact_url)))
            startActivity(browserIntent)
        } else if (id == R.id.nav_faq) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.faq_url)))
            startActivity(browserIntent)
        } else if (id == R.id.nav_mastodon) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.mastodon_url)))
            startActivity(browserIntent)
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}

