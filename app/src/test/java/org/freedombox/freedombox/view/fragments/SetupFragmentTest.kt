// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.view.fragments

import android.content.Intent
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.test.core.app.ActivityScenario
import org.freedombox.freedombox.R
import org.freedombox.freedombox.utils.storage.getConfiguredBoxesMap
import org.freedombox.freedombox.utils.storage.getSharedPreference
import org.freedombox.freedombox.views.activities.DiscoveryActivity
import org.freedombox.freedombox.views.activities.SetupActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowApplication
import org.robolectric.shadows.ShadowLooper


@RunWith(RobolectricTestRunner::class)
class SetupFragmentTest {

    @Test
    fun shouldBeAbleToSeeViewsOnTheScreen() {

        val scenario = ActivityScenario.launch(SetupActivity::class.java)
        scenario.onActivity { activity ->
            run {
                for (view in listOf(R.id.boxName, R.id.discoveredUrl, R.id.defaultStatus,
                        R.id.saveConfig, R.id.deleteConfig))
                    Assert.assertNotNull(activity.findViewById(view))
            }
        }
    }

    @Test
    fun saveButtonFinishesActivityOnButtonClick() {
        val activity: SetupActivity = Robolectric.setupActivity(SetupActivity::class.java)
        activity.findViewById<Button>(R.id.saveConfig).performClick()
        Assert.assertTrue(activity.isFinishing)
    }

    @Test
    fun deleteButtonNavigatesToDiscoveryActivityOnButtonClick() {
        val activity = Robolectric.setupActivity(SetupActivity::class.java)
        activity.findViewById<Button>(R.id.deleteConfig).performClick()
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        val expectedIntent = Intent(activity, DiscoveryActivity::class.java)
        Assert.assertEquals(expectedIntent.javaClass, actualIntent.javaClass)
    }

    @Test
    fun deleteValidExistingConfig() {
        val applicationContext = RuntimeEnvironment.application.applicationContext
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(applicationContext)

        val boxName = "freedomBox"
        val domain = "domain"
        val default = false

        val activity = Robolectric.setupActivity(SetupActivity::class.java)

        (activity.findViewById(R.id.boxName) as EditText).setText(boxName)
        (activity.findViewById(R.id.discoveredUrl) as EditText).setText(domain)
        (activity.findViewById(R.id.defaultStatus) as Switch).isChecked = default

        activity.findViewById<Button>(R.id.saveConfig).performClick()

        val configBeforeDelete = getConfiguredBoxesMap(
                sharedPreferences.getString("saved_boxes", null))
        Assert.assertFalse(configBeforeDelete?.isEmpty() ?: true)

        activity.findViewById<Button>(R.id.deleteConfig).performClick()

        val configAfterDelete = getConfiguredBoxesMap(
                sharedPreferences.getString("saved_boxes", null))
        Assert.assertTrue(configAfterDelete?.isEmpty() ?: true)
    }

    @Test
    fun checkInformationStoredInSharedPreferenceOnButtonClick() { // TODO replace with a fixture
        val applicationContext = RuntimeEnvironment.application.applicationContext
        val sharedPreferences = PreferenceManager .getDefaultSharedPreferences(applicationContext)

        val boxName = "freedomBox"
        val domain = "domain"
        val default = false

        val value = """
            {"$boxName":{"boxName":"$boxName","domain":"https://$domain","default":true}}
        """.trim()

        val activity = Robolectric.setupActivity(SetupActivity::class.java)

        (activity.findViewById(R.id.boxName) as EditText).setText(boxName)
        (activity.findViewById(R.id.discoveredUrl) as EditText).setText(domain)
        (activity.findViewById(R.id.defaultStatus) as Switch).isChecked = default

        activity.findViewById<Button>(R.id.saveConfig).performClick()

        val configuredBoxesJSON = sharedPreferences.getString("saved_boxes", null)
        Assert.assertEquals(value, configuredBoxesJSON)
    }

    @Test
    fun switchDefaultFreedomBoxToAnother() {
        val applicationContext = RuntimeEnvironment.application.applicationContext
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        val boxName = "freedomBox"
        val domain = "domain"

        val value = """
            {"$boxName":{"boxName":"$boxName","domain":"https://$domain","default":true}}
        """.trim()

        sharedPreferences.edit().putString("saved_boxes", value).apply()

        val activity = Robolectric.setupActivity(SetupActivity::class.java)

        (activity.findViewById(R.id.boxName) as EditText).setText(boxName + '2')
        (activity.findViewById(R.id.discoveredUrl) as EditText).setText(domain)
        (activity.findViewById(R.id.defaultStatus) as Switch).isChecked = true

        activity.findViewById<Button>(R.id.saveConfig).performClick()

        val configuredBoxes = getConfiguredBoxesMap(
                getSharedPreference(sharedPreferences, "saved_boxes"))
        val filtered = configuredBoxes!!.filterValues { it.isDefault() }
        Assert.assertTrue(filtered.size == 1)
        Assert.assertEquals(boxName + '2', filtered.entries.first().key)
    }
}
