package com.example.androidkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main4.*
import org.json.JSONException

class MainActivity4 : AppCompatActivity() {
    var mangProduct : ArrayList<Product> = ArrayList()
    var productAdapter : ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
//        val sinhVien  = intent.getSerializableExtra("sinhvien") as SinhVien?
//        txtvTest.text = sinhVien!!.getHoTen()

        init()
    }
    private fun init() {
        rcvTest4.setHasFixedSize(true)
        rcvTest4.layoutManager = GridLayoutManager(this, 2)
        productAdapter = ProductAdapter(this, R.layout.line_product, mangProduct)
        rcvTest4.adapter = productAdapter
        val arrayRequest = JsonArrayRequest(Request.Method.GET, "http://192.168.1.13/getSapById.php?categoryid=1",
                null, Response.Listener { response ->
            for (i in 0 until response.length()) {
                try {
                    val jsonObject = response.getJSONObject(i)
                    mangProduct.add(Product(9, "saptest", 5000, 3000, "https://waxformen.net/wp-content/uploads/2020/05/sap-apestomen-volcanic-clay.jpg",
                    "ƯU ĐIỂM:", 1))
                    mangProduct.add(Product(jsonObject.getInt("id"), jsonObject.getString("name"),
                            jsonObject.getInt("price"), jsonObject.getInt("saleprice"), jsonObject.getString("avatar"),
                            jsonObject.getString("description"), jsonObject.getInt("categoryid")))
                    productAdapter!!.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, Response.ErrorListener { })
        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(arrayRequest)

    }
}