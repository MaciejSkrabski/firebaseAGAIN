package com.example.thistimeletmeunderstandmvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = "Login Activity"
    private lateinit var mAuth: FirebaseAuth

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

            if ( password.isEmpty() || password.length < 6) {
                passwordl.error = "Minimum 6 characters per password!"
                passwordl.requestFocus()
                return@setOnClickListener
            }

            loginUser(mail, password)

        }
    }

    private fun loginUser(mail: String, password: String) {
        progressbar.visibility = View.VISIBLE

        mAuth.signInWithEmailAndPassword(mail, password)
            .addOnCompleteListener(this) { task ->
                progressbar.visibility = View.GONE
                if(task.isSuccessful) {
                    login()
                } else {
                    task.exception?.message?.let{
                        toast(it)
                    }
                }
            }
    }

    override fun onStart() {
        super.onStart()

        mAuth.currentUser.let {
            login()
        }

    }
}
