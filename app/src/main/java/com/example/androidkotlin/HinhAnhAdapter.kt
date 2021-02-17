package com.example.androidkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class HinhAnhAdapter(var context: Context, var layout: Int,var list: ArrayList<HinhAnh>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(layout, null)
            viewHolder.imgLineStorage = convertView.findViewById(R.id.imgLineStorage)
            viewHolder.txtvLineNameStorage = convertView.findViewById(R.id.txtvLineNameStorage)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        val hinhAnh: HinhAnh = getItem(position) as HinhAnh
        viewHolder.txtvLineNameStorage?.setText(hinhAnh.tenHinh)
        Picasso.get().load(hinhAnh.linkHinh)
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(viewHolder.imgLineStorage)
        return convertView!!
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return list.size
    }
    internal inner class ViewHolder {
        var imgLineStorage: ImageView? = null
        var txtvLineNameStorage: TextView? = null
    }


}