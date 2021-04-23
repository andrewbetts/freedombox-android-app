// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils.view

import android.widget.EditText
import android.widget.Switch

fun getEnteredText(element: EditText) = element.editableText.toString()

fun getSwitchStatus(element: Switch) = element.isChecked

