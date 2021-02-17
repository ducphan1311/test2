package com.example.androidkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class SinhVienAdapter(var context: Context, var layout : Int ,var mangSinhVien : ArrayList<SinhVien>) : RecyclerView.Adapter<SinhVienAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layout, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mangSinhVien.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var sv : SinhVien = mangSinhVien.get(position)
        holder.txtvName.setText(sv.getHoTen())
        holder.txtvDiaChi.setText(sv.getDiaChi())

        var decimalFormat : DecimalFormat = DecimalFormat("###,###,###")
        holder.txtvNamSinh.setText(decimalFormat.format(sv.getNamSinh()) )
        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click", Toast.LENGTH_LONG).show()
                }
                else {
                    var intent = Intent(context, MainActivity4::class.java)
                    intent.putExtra("sinhvien", sv)
                    context.startActivity(intent)

                }
            }

        })
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        var txtvName : TextView
        var txtvDiaChi : TextView
        var txtvNamSinh : TextView
        private var itemClickListener: ItemClickListener? = null
        init {
            txtvName = itemView.findViewById(R.id.txtvName)
            txtvDiaChi = itemView.findViewById(R.id.txtvDiaChi)
            txtvNamSinh = itemView.findViewById(R.id.txtvNamSinh)
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