// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

class ConnectivityMonitor(applicationContext: Context) {
    private val connectivityManager: ConnectivityManager = applicationContext
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val subscribers = mutableListOf<(Boolean) -> Unit>()

    private val networkReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            notifySubscribers(isNetworkConnected())
        }
    }

    init {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        applicationContext.registerReceiver(networkReceiver, intentFilter)
    }

    fun subscribe(subscriber: (Boolean) -> Unit) {
        if (!subscribers.contains(subscriber))
            subscribers.add(subscriber)
    }

    fun unSubscribe(subscriber: (Boolean) -> Unit) {
        subscribers.remove(subscriber)
    }

    fun notifySubscribers(state: Boolean) {
        subscribers.forEach {
            it(state)
        }
    }

    fun isNetworkConnected(): Boolean {
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isConnected
    }
}
