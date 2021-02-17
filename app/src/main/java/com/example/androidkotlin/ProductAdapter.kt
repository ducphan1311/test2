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

class ProductAdapter(var context: Context, var layout : Int, var mangProduct : ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layout, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mangProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product : Product = mangProduct.get(position)
        holder.txtvTenSanPham.setText(product.getName())

        var decimalFormat : DecimalFormat = DecimalFormat("###,###,###")
        holder.txtvGiaSanPham.setText("Giá: " + decimalFormat.format(product.getPrice()) + " Đ" )
        holder.txtvGiaSanPhamSale.setText("Giá: " + decimalFormat.format(product.getSalePrice()) + " Đ" )
        if (decimalFormat.format(product.getSalePrice()) == "0") {
            holder.txtvGiaSanPhamSale.visibility = View.INVISIBLE
            holder.imgDeleteGia.setVisibility(View.INVISIBLE)
        }
        Picasso.get().load(product.getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgSanPham)
        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click", Toast.LENGTH_LONG).show()
                }
                else {
                    var intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("product", product)
                    context.startActivity(intent)

                }
            }

        })
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        var txtvTenSanPham : TextView
        var txtvGiaSanPham : TextView
        var txtvGiaSanPhamSale : TextView
        var imgSanPham : ImageView
        var imgDeleteGia : ImageView
        private var itemClickListener: ItemClickListener? = null
        init {
            txtvTenSanPham = itemView.findViewById(R.id.txtvTenSanPham)
            txtvGiaSanPham= itemView.findViewById(R.id.txtvGiaSanPham)
            txtvGiaSanPhamSale = itemView.findViewById(R.id.txtvGiaSanPhamSale)
            imgSanPham = itemView.findViewById(R.id.imgSanPham)
            imgDeleteGia = itemView.findViewById(R.id.imgDeleteGia)
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