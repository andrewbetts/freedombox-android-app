// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils.network

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConnectivityMonitorTest {

    @Test
    fun testSubscriber() {
        val application = getApplicationContext<Context>()
        val monitor = ConnectivityMonitor(application.applicationContext)

        monitor.subscribe {
            assertTrue(true)
        }

        val intent = Intent(ConnectivityManager.CONNECTIVITY_ACTION)
        application.sendBroadcast(intent)
    }
}
