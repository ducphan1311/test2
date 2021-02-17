package com.example.androidkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        btnLogin.setOnClickListener{
            dangNhap()
        }
        txtvSignUp.setOnClickListener {
            intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun dangNhap(){
        var email : String = edtEmailLogin.text.toString()
        var password : String = edtPassWordLogin.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_LONG).show()
                    intent = Intent(applicationContext, StorageActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "That bai", Toast.LENGTH_LONG).show()
                }

                // ...
            }
    }
}