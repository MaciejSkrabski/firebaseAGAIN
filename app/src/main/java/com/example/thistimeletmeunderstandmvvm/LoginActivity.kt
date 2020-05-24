package com.example.thistimeletmeunderstandmvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = "Login Activity"
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        Log.d(TAG, "$currentUser")

        register.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java ))
        }

        loginl.setOnClickListener {
            val mail = emaill.text.toString().trim()
            val password = passwordl.text.toString().trim()

            if ( mail.isEmpty() ) {
                emaill.error = "Email shall not be empty!"
                emaill.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                emaill.error = "Actual email is required!"
                emaill.requestFocus()
                return@setOnClickListener
            }

            if ( password.isEmpty() ) {
                passwordl.error = "Password shall not be empty!"
                passwordl.requestFocus()
                return@setOnClickListener
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }
}
