// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils.view

import android.content.Context
import android.widget.EditText
import android.widget.Switch
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ViewStatusTest {

    private val value = "test"
    private var application = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun shouldReturnEnteredTextInTextBox() {
        val editText = EditText(application)
        editText.setText(value)
        Assert.assertEquals(value, getEnteredText(editText))
    }

    @Test
    fun shouldReturnEmptyWhenNotEnteredText() {
        val editText = EditText(application)
        Assert.assertEquals("", getEnteredText(editText))
    }

    @Test
    fun shouldReturnTrueWhenSwitchIsOn() {
        val switch = Switch(application)
        switch.isChecked = true
        Assert.assertTrue(getSwitchStatus(switch))
    }

    @Test
    fun shouldReturnFalseWhenSwitchIsOff() {
        val switch = Switch(application)
        switch.isChecked = false
        Assert.assertFalse(getSwitchStatus(switch))
    }

}
