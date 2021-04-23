// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import org.freedombox.freedombox.utils.ImageRenderer
import org.freedombox.freedombox.utils.network.ConnectivityMonitor
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {
    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(application.applicationContext)

    @Provides
    @Singleton
    fun provideConnectivityMonitor(): ConnectivityMonitor =
        ConnectivityMonitor(application.applicationContext)

    @Provides
    @Singleton
    fun provideImageRenderer(): ImageRenderer =
        ImageRenderer(application.applicationContext)

    @Provides
    fun provideGson() : Gson = GsonBuilder().setPrettyPrinting().create();
}
