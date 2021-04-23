// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.models

import com.google.gson.annotations.SerializedName

data class Shortcuts(val shortcuts: List<Shortcut>)

data class Shortcut(val name: String,
                    @SerializedName("short_description") val shortDescription: String,
                    val description: List<String>?,
                    @SerializedName("icon_url") val iconUrl: String,
                    val clients: List<Client>)

data class Client(val name: String,
                  val platforms: List<Platform>)

data class Platform(val type: String,
                    val format: String?,
                    val os: String?,
                    @SerializedName("store_name") val storeName: String?,
                    val url: String)