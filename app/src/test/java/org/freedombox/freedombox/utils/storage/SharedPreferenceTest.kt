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
