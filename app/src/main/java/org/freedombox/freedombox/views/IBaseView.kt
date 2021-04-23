// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views

import com.google.android.material.snackbar.Snackbar

interface IBaseView {
    fun showSnackMessage(message: String, duration: Int = Snackbar.LENGTH_SHORT)
}
