package com.example.androidkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        btnRegister.setOnClickListener{
            dangKy()
        }
        txtvLogIn.setOnClickListener {
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun dangKy(){
        var email : String = edtEmailRegister.text.toString()
        var password : String = edtPassWordRegister.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "dang ky thanh cong", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Loi", Toast.LENGTH_LONG).show()
                }

            }
    }
}