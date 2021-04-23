// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.applications

import android.app.Application
import org.freedombox.freedombox.components.AppComponent
import org.freedombox.freedombox.components.DaggerAppComponent
import org.freedombox.freedombox.modules.AppModule
import org.freedombox.freedombox.utils.ImageRenderer
import org.freedombox.freedombox.utils.network.ConnectivityMonitor
import javax.inject.Inject

class FreedomBoxApp : Application() {
    lateinit var appComponent: AppComponent

    @Inject lateinit var connectivityMonitor: ConnectivityMonitor
    @Inject lateinit var imageRenderer: ImageRenderer

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}
