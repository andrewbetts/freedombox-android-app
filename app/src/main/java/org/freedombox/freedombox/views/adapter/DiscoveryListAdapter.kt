// SPDX-License-Identifier: GPL-3.0-or-later

package org.freedombox.freedombox.views.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.freedombox.freedombox.R
import org.freedombox.freedombox.views.model.ConfigModel

class DiscoveryListAdapter(private val context: Context,
                           private val boxList: List<ConfigModel>,
                           private val isConfigured: Boolean,
                           private val itemClickListener: DiscoveryListAdapter.OnItemClickListener):
        androidx.recyclerview.widget.RecyclerView.Adapter<DiscoveryListAdapter.DiscoveryListItemViewHolder>() {

    override fun onBindViewHolder(holder: DiscoveryListItemViewHolder, position: Int) {
        holder.let {
            holder.updateView(boxList.getOrNull(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoveryListItemViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.discovery_listview, null)
        return DiscoveryListItemViewHolder(view)
    }

    override fun getItemCount(): Int = boxList.size

    override fun getItemId(position: Int): Long {
        return boxList[position].hashCode().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    inner class DiscoveryListItemViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var boxNameTextView: TextView = view.findViewById<TextView>(R.id.boxName) as TextView
        var portNumberTextView: TextView = view.findViewById<TextView>(R.id.port) as TextView
        var boxIcon: ImageView = view.findViewById<ImageView>(R.id.box_icon) as ImageView
        var borderBottom: View = view.findViewById<View>(R.id.bottom_border) as View

        init {
            view.setOnClickListener { itemClickListener.onItemClick(adapterPosition) }
        }

        @SuppressLint("SetTextI18n")
        fun updateView(box: ConfigModel?) = box?.let{
            boxNameTextView.text = box.boxName
            portNumberTextView.text = box.domain
            if (isConfigured) {
                boxIcon.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_freedombox_blue))
            }
            if (boxList.size == 1) {
                borderBottom.visibility = View.INVISIBLE
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
