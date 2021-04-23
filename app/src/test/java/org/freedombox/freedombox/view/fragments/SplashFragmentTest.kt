// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.view.fragments

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import org.freedombox.freedombox.views.activities.DiscoveryActivity
import org.freedombox.freedombox.views.activities.LauncherActivity
import org.freedombox.freedombox.views.activities.MainActivity
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication
import org.robolectric.shadows.ShadowLooper


@RunWith(RobolectricTestRunner::class)
class SplashFragmentTest {
    val key = "saved_boxes"
    val applicationContext = ApplicationProvider.getApplicationContext<Context>()
    val sharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(applicationContext)

    @After
    fun destroy() {
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun navigateToLauncherScreenWhenDefaultFreedomBoxConfigured() {

        val value = """
            {"FreedomBox": {
	            "boxName": "FreedomBox",
	            "default": true,
	            "domain": "/10.42.0.1"
            }}
        """

        sharedPreferences.edit().putString(key, value).commit()
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        val expectedIntent = Intent(activity, LauncherActivity::class.java)
        Assert.assertEquals(expectedIntent.javaClass, actualIntent.javaClass)
    }

    @Test
    fun navigateToDiscoveryScreenWhenNoDefaultFreedomBoxConfigured() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        val expectedIntent = Intent(activity, DiscoveryActivity::class.java)
        Assert.assertEquals(expectedIntent.javaClass, actualIntent.javaClass)
    }

}
