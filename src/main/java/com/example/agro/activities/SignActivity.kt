package com.example.agro.activities

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.agro.R
import com.example.agro.homeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignActivity : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLog: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private var mAuth: FirebaseAuth? = null

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth?.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, homeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        mAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.email) as TextInputEditText
        editTextPassword = findViewById(R.id.password) as TextInputEditText
        buttonLog = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.registerNow)

        textView.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
            finish()
        }

        buttonLog.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this) { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        applicationContext,
                        "Login Successfull",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, homeActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {

                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                        .show()

                }
            }?.addOnFailureListener(this) { exception ->
                progressBar.visibility = View.GONE
                Toast.makeText(
                    baseContext, "Authentication failed: " + exception.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
