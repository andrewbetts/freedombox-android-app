// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import org.freedombox.freedombox.R


abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mDrawerLayout: androidx.drawerlayout.widget.DrawerLayout

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

    fun loadFragment(fragmentContainerId: Int, fragment: androidx.fragment.app.Fragment, addToBackStack: Boolean = false) {
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

        when (item.itemId) {
            R.id.nav_switch_freedombox -> startActivity(Intent(this, DiscoveryActivity::class.java))
            // R.id.nav_settings -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.nav_about -> startActivity(Intent(this, AboutActivity::class.java))
            R.id.nav_website -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website_url))))
            R.id.nav_contact -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.contact_url))))
            R.id.nav_faq -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.faq_url))))
            R.id.nav_mastodon -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.mastodon_url))))
            else -> return false
        }

        val drawer = findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}

