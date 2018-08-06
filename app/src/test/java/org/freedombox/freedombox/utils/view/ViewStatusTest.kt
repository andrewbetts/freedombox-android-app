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

package org.freedombox.freedombox.utils.view

import android.widget.EditText
import android.widget.Switch
import org.junit.Assert
import org.freedombox.freedombox.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ViewStatusTest {

    private val value = "test"

    @Test
    fun shouldReturnEnteredTextInTextBox() {
        val editText = EditText(RuntimeEnvironment.application)
        editText.setText(value)
        Assert.assertEquals(value, getEnteredText(editText))
    }

    @Test
    fun shouldReturnEmptyWhenNotEnteredText() {
        val editText = EditText(RuntimeEnvironment.application)
        Assert.assertEquals("", getEnteredText(editText))
    }

    @Test
    fun shouldReturnTrueWhenSwitchIsOn() {
        val switch = Switch(RuntimeEnvironment.application)
        switch.isChecked = true
        Assert.assertTrue(getSwitchStatus(switch))
    }

    @Test
    fun shouldReturnFalseWhenSwitchIsOff() {
        val switch = Switch(RuntimeEnvironment.application)
        switch.isChecked = false
        Assert.assertFalse(getSwitchStatus(switch))
    }

}
