// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.components

import dagger.Component
import org.freedombox.freedombox.applications.FreedomBoxApp
import org.freedombox.freedombox.modules.AppModule
import org.freedombox.freedombox.views.fragments.BaseFragment
import org.freedombox.freedombox.views.fragments.DiscoveryFragment
import org.freedombox.freedombox.views.fragments.LauncherFragment
import org.freedombox.freedombox.views.fragments.SetupFragment
import org.freedombox.freedombox.views.fragments.SplashFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(application: FreedomBoxApp)

    fun inject(baseFragment: BaseFragment)

    fun inject(splashFragment: SplashFragment)

    fun inject(launcherFragment: LauncherFragment)

    fun inject(discoveryFragment: DiscoveryFragment)

    fun inject(setupFragment: SetupFragment)
}
