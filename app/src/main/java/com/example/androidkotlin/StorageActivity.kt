package com.example.androidkotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList


class StorageActivity : AppCompatActivity() {
    var REQUEST_CODE_IMAGE : Int = 1;
    lateinit var storage : FirebaseStorage
    var imgStorage : ImageView? = null
    private lateinit var mDatabase: DatabaseReference
    private lateinit var hinhAnhAdapter: HinhAnhAdapter
    private  var listHinhAnh : ArrayList<HinhAnh> = ArrayList()
//private var hinhAnhAdapter : HinhAnhAdapter? = null
//    private var listHinhAnh : ArrayList<HinhAnh> = ArrayList()
//private var hinhAnhAdapter : HinhAnhRecyclerAdapter? = null
//    private var listHinhAnh : ArrayList<HinhAnh> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        mDatabase = FirebaseDatabase.getInstance().getReference()

        imgStorage = findViewById(R.id.imgStorage)
        storage = Firebase.storage

        val storageRef = storage.getReferenceFromUrl("gs://androidkotlin-c7661.appspot.com")

        imgStorage!!.setOnClickListener{
            intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE_IMAGE)
        }
        btnSaveImage.setOnClickListener{
            var calendar : Calendar = Calendar.getInstance()
            var mountainsRef = storageRef.child("image" + calendar.timeInMillis + ".png")
            imgStorage!!.isDrawingCacheEnabled = true
            imgStorage!!.buildDrawingCache()
            val bitmap = (imgStorage!!.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                Toast.makeText(this, "That bai", Toast.LENGTH_LONG).show()
            }.addOnSuccessListener { taskSnapshot ->
                Toast.makeText(this, "Thanh cong", Toast.LENGTH_LONG).show()

                //lay link hinh anh
                val uri = taskSnapshot.storage.downloadUrl
                while(!uri.isComplete());
                var url = uri.result

                var hinhAnh:HinhAnh = HinhAnh(edtTenHinh.text.toString(), url.toString() )
                mDatabase.child("HinhAnh").push().setValue(hinhAnh){error, ref ->

                    if (error == null){
                        Toast.makeText(this, "Luu data thanh cong", Toast.LENGTH_LONG).show()
                    }
                    else
                        Toast.makeText(this, "loi", Toast.LENGTH_LONG).show()
                }
            }
        }

//        rcvStorage.adapter = hinhAnhAdapter
//        rcvStorage.setHasFixedSize(true)
//        rcvStorage.layoutManager = LinearLayoutManager(this)

        setListView()

    }
    private fun setListView(){
        mDatabase.child("HinhAnh").addChildEventListener(object : ChildEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                hinhAnhAdapter = HinhAnhAdapter(applicationContext, R.layout.line_hinh_anh, listHinhAnh)
                lvStorage.adapter = hinhAnhAdapter
                val hinhAnh: HinhAnh? = snapshot.getValue(HinhAnh::class.java)
                listHinhAnh.add(HinhAnh(hinhAnh?.tenHinh, hinhAnh?.linkHinh))
                hinhAnhAdapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK && data!=null){
            var bitmap : Bitmap = data.extras?.get("data") as Bitmap
            imgStorage!!.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    //Tải lên từ dữ liệu trong bộ nhớ
    private fun upLoad(){

    }
}