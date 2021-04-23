// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.utils.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.freedombox.freedombox.models.Shortcuts
import org.freedombox.freedombox.views.model.ConfigModel

fun getSharedPreference(sharedPreferences: SharedPreferences, key: String): String? =
        sharedPreferences.getString(key, null)

fun putSharedPreference(sharedPreferences: SharedPreferences,
                        key: String,
                        listItem: List<ConfigModel>) {
    val sharedPreferencesEditor = sharedPreferences.edit()
    sharedPreferencesEditor.putString(key,
        Gson().toJson(listItem))
    sharedPreferencesEditor.apply()
}

fun putSharedPreference(sharedPreferences: SharedPreferences,
                        key: String,
                        listItem: Map<String, ConfigModel>) {
    val sharedPreferencesEditor = sharedPreferences.edit()
    sharedPreferencesEditor.putString(key,
            Gson().toJson(listItem))
    sharedPreferencesEditor.apply()
}

val gson = GsonBuilder().create()

/**
 * Parses the sharedPreferences value for configured FreedomBoxes and converts it into a
 * Map<String, ConfigModel>
 * Returns null if the input string is null
 */
fun getConfiguredBoxesMap(configuredBoxesJSON: String?): Map<String, ConfigModel>? =
    configuredBoxesJSON?.let {
        gson.fromJson<Map<String, ConfigModel>>(it,
                object : TypeToken<Map<String, ConfigModel>>() {}.type)
    }

/**
 * Parses the sharedPreferences value for application shortcuts and
 * converts it into a Map<String, Shortcuts>
 * Returns null if the input string is null
 */
fun getShortcutsMap(shortcutsJSON: String?): Map<String, Shortcuts>? =
        shortcutsJSON?.let {
            gson.fromJson<Map<String, Shortcuts>>(it,
                    object : TypeToken<Map<String, Shortcuts>>() {}.type)
        }
