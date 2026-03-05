package com.example.openchatting.googleAuth

import android.content.Context
import com.example.openchatting.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.auth
import com.google.firebase.Firebase

class GoogleAuthClient(
    private val context: Context
) {
    private val auth = Firebase.auth

    fun getGoogleSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.web_server_client))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    fun signInWithGoogle(): Boolean {
        return try {
            val signedInAccount = GoogleSignIn.getLastSignedInAccount(context)
            signedInAccount != null
        } catch (e: Exception) {
            false
        }
    }
}