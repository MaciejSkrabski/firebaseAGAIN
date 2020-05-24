package com.example.thistimeletmeunderstandmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            val mail = email.text.toString().trim()
            val pass = password.text.toString().trim()

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

            if ( pass.isEmpty() ) {
                password.error = "Password shall not be empty!"
                password.requestFocus()
                return@setOnClickListener
            }

        }



        register_tv.setOnClickListener { startActivity(Intent(this@RegisterActivity, MainActivity::class.java )) }

    }
}
