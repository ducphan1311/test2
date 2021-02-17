package com.example.androidkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(var context: Context, var layout : Int, var mangUser : ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layout, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mangUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var user : User = mangUser.get(position)
        holder.txtvUsername.setText(user.username)
        holder.txtvEmail.setText(user.email)

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click", Toast.LENGTH_LONG).show()
                }
                else {
                    var intent = Intent(context, RegisterActivity::class.java)
                    intent.putExtra("user", user)
                    context.startActivity(intent)

                }
            }

        })
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        var txtvUsername : TextView
        var txtvEmail : TextView
        private var itemClickListener: ItemClickListener? = null
        init {
            txtvUsername = itemView.findViewById(R.id.txtvUsername)
            txtvEmail = itemView.findViewById(R.id.txtvEmail)
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