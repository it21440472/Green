package com.example.agro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.agro.activities.FetchingActivity
import com.example.agro.activities.InsertionActivity
import com.example.agro.activities.MycropActivity
import com.example.agro.activities.SignActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class homeActivity : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button
    private var auth: FirebaseAuth? = null
    private var button: Button? = null
    private var textView: TextView? = null
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        auth = FirebaseAuth.getInstance()
        button = findViewById(R.id.btnlogout)

        user = auth?.currentUser
        if (user == null) {
            val intent = Intent(applicationContext, SignActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            textView?.text = user?.email
        }

        button?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, SignActivity::class.java)
            startActivity(intent)
            finish()
        }

        val imageButton: ImageButton =findViewById(R.id.mycropbtnmain3)
        imageButton.setOnClickListener{
            startActivity(Intent(this,MycropActivity::class.java))


        }
    }
}