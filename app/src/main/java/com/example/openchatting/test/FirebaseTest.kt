package com.example.openchatting.test

import com.google.firebase.auth.FirebaseAuth

class FirebaseTest {
    fun testFirebase() {
        val auth = FirebaseAuth.getInstance()
        println("Firebase Auth instance: $auth")
    }
}
