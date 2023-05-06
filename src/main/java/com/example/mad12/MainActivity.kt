package com.example.mad12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var insert : Button
    private lateinit var fetch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insert = findViewById(R.id.button)
        fetch = findViewById(R.id.button2)

       insert.setOnClickListener{
           val intent = Intent(this,InsertationActivity::class.java)
           startActivity(intent)
       }

        fetch.setOnClickListener{
            val intent = Intent(this,FetchingActivity::class.java)
            startActivity(intent)
        }
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}