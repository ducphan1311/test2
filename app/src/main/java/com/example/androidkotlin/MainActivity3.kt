package com.example.androidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main3.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity3 : AppCompatActivity() {
//    var mangSinhVien : ArrayList<SinhVien> = ArrayList()
//    var sinhvienAdapter : SinhVienAdapter? = null
var mangProduct : ArrayList<Product> = ArrayList()
    var productAdapter : ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var name : String? = intent.getStringExtra("ok")
        txtvTest.text = name
        init()
    }

    private fun init() {
//        sinhvienAdapter = SinhVienAdapter(this, R.layout.line_sv, mangSinhVien)
//        rcvTest.adapter = sinhvienAdapter
//        rcvTest.setHasFixedSize(true)
//        rcvTest.layoutManager = GridLayoutManager(this, 2)
//        sinhvienAdapter!!.notifyDataSetChanged()
//        mangSinhVien.add(SinhVien("name", "dia chi", 2000))
//        mangSinhVien.add(SinhVien("duc", "nd", 1999))
        productAdapter = ProductAdapter(this, R.layout.line_product, mangProduct)
        rcvTest.adapter = productAdapter
        rcvTest.hasFixedSize()
        rcvTest.layoutManager = LinearLayoutManager(this)
        val arrayRequest : JsonArrayRequest = JsonArrayRequest(Request.Method.GET, "http://192.168.1.13/getSapById.php?categoryid=1", null,
        Response.Listener { response ->
            for (i:Int in 0 until response.length()){
                try {
                    val jsonObject: JSONObject = response.getJSONObject(i)
                    mangProduct.add(Product(jsonObject.getInt("id"), jsonObject.getString("name"),
                            jsonObject.getInt("price"), jsonObject.getInt("saleprice"), jsonObject.getString("avatar"),
                            jsonObject.getString("description"), jsonObject.getInt("categoryid")))
                    productAdapter!!.notifyDataSetChanged()
                }catch (e : JSONException){
                    e.printStackTrace()
                }

            }
        },
        Response.ErrorListener {  })
        val queue : RequestQueue = Volley.newRequestQueue(this)
        queue.add(arrayRequest)

    }
}