// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import org.freedombox.freedombox.views.model.ConfigModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SharedPreferenceTest {

    private val preferenceName = "PrefTest"
    private val key = "preference"
    private val value = "success"
    private lateinit var sharedPreference: SharedPreferences

    @Before
    fun setup() {
        sharedPreference = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    private fun putKeyAndValueIntoSharedPreference() {
        val edit = sharedPreference.edit()
        edit.putString(key, value)
        edit.commit()
    }

    @Test
    fun shouldReturnNullWhenKeyNotExist() {
        putKeyAndValueIntoSharedPreference()
        val returnString = getSharedPreference(sharedPreference, "not_found")
        Assert.assertEquals(null, returnString)
    }

    @Test
    fun shouldReturnValueWhenKeyExist() {
        putKeyAndValueIntoSharedPreference()
        val returnString = getSharedPreference(sharedPreference, key)
        Assert.assertEquals(value, returnString)
    }

    @Test
    fun shouldPutKeyAndValueIntoSharedPreference() {
        val configModelList = listOf<ConfigModel>()
        putSharedPreference(sharedPreference, key, configModelList)
        val storedValue = sharedPreference.getString(key, null)
        Assert.assertNotNull(storedValue)
    }
}
