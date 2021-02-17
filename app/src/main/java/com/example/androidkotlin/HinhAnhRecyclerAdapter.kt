package com.example.androidkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class HinhAnhRecyclerAdapter(var context: Context, var layout : Int, var listHinhAnh : ArrayList<HinhAnh>) : RecyclerView.Adapter<HinhAnhRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layout, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHinhAnh.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var hinhAnh : HinhAnh = listHinhAnh.get(position)
        holder.txtvLineNameStorage.setText(hinhAnh.tenHinh)

        Picasso.get().load(hinhAnh.linkHinh)
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgLineStorage)
        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click", Toast.LENGTH_LONG).show()
                }
                else {
                    var intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("hinhanh", hinhAnh)
                    context.startActivity(intent)

                }
            }

        })
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        var txtvLineNameStorage : TextView

        var imgLineStorage : ImageView

        private var itemClickListener: ItemClickListener? = null
        init {
            txtvLineNameStorage = itemView.findViewById(R.id.txtvLineNameStorage)

            imgLineStorage = itemView.findViewById(R.id.imgLineStorage)
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
        fun setItemClickListener(itemClickListener: ItemClickListener?) {
            this.itemClickListener = itemClickListener
        }
        override fun onClick(v: View?) {
            itemClickListener?.onClick(v, adapterPosition, false)
        }

        override fun onLongClick(v: View?): Boolean {
            itemClickListener?.onClick(v, adapterPosition, true)
            return true
        }
    }

}