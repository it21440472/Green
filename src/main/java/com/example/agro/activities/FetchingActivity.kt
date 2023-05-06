package com.example.agro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agro.adapters.CropAdapter
import com.example.agro.CropDetailsActivity
import com.example.agro.R
import com.example.agro.models.cropModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {

    private lateinit var cropRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var cropList: ArrayList<cropModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        cropRecyclerView = findViewById(R.id.rvCrop)
        cropRecyclerView.layoutManager = LinearLayoutManager(this)
        cropRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        cropList = arrayListOf<cropModel>()

        getcropsData()
    }
    private fun getcropsData() {

        cropRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Crops")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cropList.clear()
                if (snapshot.exists()){
                    for (cropSnap in snapshot.children){
                        val cropData = cropSnap.getValue(cropModel::class.java)
                        cropList.add(cropData!!)
                    }
                    val mAdapter = CropAdapter(cropList)
                    cropRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : CropAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, CropDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("cropId", cropList[position].cropID)
                            intent.putExtra("cropName", cropList[position].cname)
                            intent.putExtra("cropType", cropList[position].ctype)
                            intent.putExtra("cropSize", cropList[position].csize)
                            intent.putExtra("cropWorkers", cropList[position].cwork)
                            intent.putExtra("cropExpense", cropList[position].cexp)
                            startActivity(intent)
                        }

                    })

                    cropRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
