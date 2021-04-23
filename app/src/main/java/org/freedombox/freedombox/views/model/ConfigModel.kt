// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.model

import android.os.Parcel
import android.os.Parcelable

data class ConfigModel(val boxName: String,
                       val domain: String,
                       private val default: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(boxName)
        parcel.writeString(domain)
        parcel.writeByte(if (default) 1 else 0)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ConfigModel> {
        override fun createFromParcel(parcel: Parcel) = ConfigModel(parcel)

        override fun newArray(size: Int) = arrayOfNulls<ConfigModel>(size)
    }

    fun isDefault() = this.default
}

