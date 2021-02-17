package com.example.androidkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.set

class MainActivity : AppCompatActivity() {
    private lateinit var mDatabase: DatabaseReference
    private var userAdapter : UserAdapter? = null
    private var mangUser : ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter(this, R.layout.line_user, mangUser)
        rcvMain.adapter = userAdapter
        rcvMain.setHasFixedSize(true)
        rcvMain.layoutManager = GridLayoutManager(this, 2)

        fireBase()



        btnClick.setOnClickListener {
            intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }
        btnStorage.setOnClickListener{
            intent = Intent(applicationContext, StorageActivity::class.java)
            startActivity(intent)
        }
        btnLogOut.setOnClickListener{
            Firebase.auth.signOut()
            Toast.makeText(this, "Log out thanh cong", Toast.LENGTH_LONG).show()
        }


        var a : String? = ""
        a = "duc"
//        a.toInt() //ep kieu
        val b : String = "final"
        a = null // null safety
        //swtich case
        var c : Int = 5
        when(a){
            "1" -> Log.d("AAA", "1")
            "2" -> Log.d("AAA", "2")
            "duc" -> Log.d("AAA", "hi duc")
            null -> Log.d("AAA", " null hi")
        }
        when(c){
            in 1..3 -> Log.d("AAA", "quy 1")
            in 4..6 -> Log.d("AAA", "quy 2")
            else -> Log.d("AAA", "ko co gi")
        }
        for (c in c..10 ){
            Log.d("AAA", "for lan " +c)
        }
        for (i in c-1 downTo 0 step 2){
            Log.d("AAA", "downto lan" +i)
        }

        //Mang
        var mangSo : IntArray = intArrayOf(1,2,3,4,5,6)
        mangSo.get(2) // 3

        var mangChuoi : List<String> = listOf("a", "b", "c")
        mangChuoi.get(2) //c

        var arrayList : ArrayList<String> = ArrayList();
        arrayList.add("a")
        arrayList.add("b")
        arrayList.add("c")
        arrayList.size
        arrayList.remove("c")
//        arrayList.removeAt(2) //c
        arrayList.set(0, "d") //a->d

        testSinhVien()
    }
    fun testSinhVien(){
//        var svl : SinhVien = SinhVien()
//        svl.setHoTen("Phan DUc")
//        svl.setDiaChi("nd")
//        svl.setNamSinh(1999)
//        svl
//        Log.d("AAA", svl.getHoTen())
    }


    private fun fireBase(){
        //Nhận cơ sở dữ liệu
        mDatabase = Firebase.database.reference
        testFireBase()
//        addData()
//        writeNewUser("1", "Duc", "duc@gmail.com")
        addDataChild()
    }

    fun testFireBase(){
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")

    }
    //Đọc và ghi dữ liệu
    private fun writeNewUser(userId: String, name: String, email: String?) {
        val user = User(name, email)
        mDatabase.child("users").child(userId).setValue(user)
        mDatabase.child("users").push().setValue(user)

        val myMap =
            hashMapOf<String, Int>()
        myMap["Sdt"] = 113
        mDatabase.child("HashMap").setValue(myMap)

        //Ket thuc ghi du lieu
        mDatabase.child("HoanThanh").push().setValue("Lap trinh Kotlin") { error, ref ->
            if (error == null){
                Toast.makeText(this,"Luu thanh cong",Toast.LENGTH_LONG ).show()
            }
            else
                Toast.makeText(this, "Luu that bai, Loi: " + error, Toast.LENGTH_LONG).show()
        }
    }
    //nhan du lieu
    private fun addData(){
        mDatabase.child("users").addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        txtvTest.text = snapshot.getValue().toString()
                    }

                })
    }
    //addChild
    private fun addDataChild(){
        mDatabase.child("users").addChildEventListener(object : ChildEventListener{
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
//                txtvTest.append(snapshot.getValue().toString())
//                txtvTest.text = snapshot.getValue().toString() + "\n"
//                Toast.makeText(applicationContext, snapshot.getValue().toString() + "\n", Toast.LENGTH_LONG).show()

                val users : User? = snapshot.getValue(User::class.java)
                Toast.makeText(applicationContext, users!!.email + "\n", Toast.LENGTH_LONG).show()

                mangUser.add(User(users.email, users.username))
                userAdapter?.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

        })
    }


}



