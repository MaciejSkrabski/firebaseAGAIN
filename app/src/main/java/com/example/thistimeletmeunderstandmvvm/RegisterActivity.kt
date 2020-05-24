package com.example.thistimeletmeunderstandmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        register_button.setOnClickListener {
            val mail = email.text.toString().trim()
            val pass = password.text.toString().trim()

            // string validation
            if ( mail.isEmpty() ) {
                email.error = "Email shall not be empty!"
                email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                email.error = "Actual email is required!"
                email.requestFocus()
                return@setOnClickListener
            }

            if ( pass.isEmpty() || pass.length < 6) {
                password.error = "Minimum 6 characters per password!"
                password.requestFocus()
                return@setOnClickListener
            }

            //actual registration

            registerUser(mail, pass)


        }



        register_tv.setOnClickListener { startActivity(Intent(this@RegisterActivity, MainActivity::class.java )) }

    }

    private fun registerUser(mail: String, pass: String) {
        progressbar.visibility = View.VISIBLE

        mAuth.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(this) { task ->
                progressbar.visibility = View.GONE
                if (task.isSuccessful) {
                    //Registration successful
                    login()
                } else {
                task.exception?.message?.let { toast(it) }
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
