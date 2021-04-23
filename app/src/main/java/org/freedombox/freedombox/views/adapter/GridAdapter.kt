// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.app_container.view.*
import org.freedombox.freedombox.R
import org.freedombox.freedombox.models.Shortcut
import org.freedombox.freedombox.utils.ImageRenderer
import org.freedombox.freedombox.utils.network.launchApp
import org.freedombox.freedombox.utils.network.urlJoin

class GridAdapter(val context: Context, val imageRenderer: ImageRenderer, val baseUrl: String) : BaseAdapter() {

    private var items = listOf<Shortcut>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.app_container, null)
        val shortcut = items[position]

        rowView.appName.text = shortcut.name
        rowView.appDescription.text = shortcut.shortDescription

        // TODO Remove the `replace` after changes are done on the server
        val iconUrl = urlJoin(baseUrl, shortcut.iconUrl.replace(".png", ".svg"))

        imageRenderer.loadSvgImageFromURL(
                Uri.parse(iconUrl),
                rowView.appIcon
        )

        rowView.appIcon.setOnClickListener { launchApp(shortcut, context, baseUrl) }
        return rowView
    }

    override fun getItem(position: Int): Shortcut = items[position]

    override fun getItemId(position: Int) = items[position].hashCode().toLong()

    override fun getCount() = items.size

    @Suppress("SENSELESS_COMPARISON")
    fun setData(shortcuts: List<Shortcut>) {
        items = shortcuts.filter{ it.clients != null}

        notifyDataSetChanged()
    }
}
