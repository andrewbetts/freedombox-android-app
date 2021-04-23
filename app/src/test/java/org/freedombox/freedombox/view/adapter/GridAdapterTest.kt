// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.view.adapter

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.app_container.view.*
import org.freedombox.freedombox.BuildConfig
import org.freedombox.freedombox.models.Shortcut
import org.freedombox.freedombox.utils.ImageRenderer
import org.freedombox.freedombox.views.adapter.GridAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.application
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class GridAdapterTest {
    private val applicationContext: Context = ApplicationProvider.getApplicationContext()
    private val gridAdapter = GridAdapter(applicationContext, imageRenderer = ImageRenderer(applicationContext), baseUrl = "https://localhost")
    private val gson = GsonBuilder().create()
    private var items = mutableListOf<Shortcut>()
    private val shortcut = gson.fromJson("""
{
  "name": "Deluge",
  "short_description": "BitTorrent Web Client",
  "description": null,
  "icon_url": "/plinth/static/theme/icons/deluge.png",
  "clients": [
    {
      "name": "Deluge",
      "description": "Bittorrent client written in Python/PyGTK",
      "platforms": [
        {
          "type": "web",
          "url": "/deluge"
        },
        {
          "type": "package",
          "format": "deb",
          "name": "deluge"
        }
      ]
    }
  ]
}
    """, Shortcut::class.java)

    @Before
    fun setUp() {
        items.add(shortcut)
    }

    @Test
    fun testItemCount() {
        gridAdapter.setData(items)

        assertEquals(gridAdapter.count, 1)
    }

    @Test
    fun testGetItemAtPosition() {
        gridAdapter.setData(items)

        assertEquals(gridAdapter.getItem(0), items[0])
    }

    @Test
    fun testViewIsGettingPopulated() {
        gridAdapter.setData(items)

        val view = gridAdapter.getView(0, null, null)

        assertEquals(view.appName.text.toString(), "Deluge")
        assertEquals(view.appDescription.text.toString(), "BitTorrent Web Client")
    }
}
