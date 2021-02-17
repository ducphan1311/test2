package com.example.androidkotlin

import java.io.Serializable

data class User(
    var username: String? = "",
    var email: String? = ""
) : Serializable