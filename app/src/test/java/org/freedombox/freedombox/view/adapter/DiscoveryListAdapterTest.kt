// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.view.adapter

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.freedombox.freedombox.views.adapter.DiscoveryListAdapter
import org.freedombox.freedombox.views.model.ConfigModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DiscoveryListAdapterTest {

    private val applicationContext: Context = ApplicationProvider.getApplicationContext()
    private lateinit var listAdapter: DiscoveryListAdapter
    private val box1 = ConfigModel("box1", "alice.freedombox.rocks", false)
    private val box2 = ConfigModel("box2", "bob.freedombox.rocks", false)
    private val boxList = listOf<ConfigModel>(box1, box2)

    @Before
    fun setUp() {
        listAdapter = DiscoveryListAdapter(applicationContext, boxList, false,
                object : DiscoveryListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    @Test
    fun testItemCount() {
        Assert.assertEquals(listAdapter.itemCount, 2)
    }

    @Test
    fun testGetItemViewTypeAtPositionZero() {
        Assert.assertEquals(listAdapter.getItemViewType(0), 0)
    }

    @Test
    fun testGetItemIdAtPositionOne() {
        Assert.assertEquals(listAdapter.getItemId(1), boxList[1].hashCode().toLong())
    }

}
