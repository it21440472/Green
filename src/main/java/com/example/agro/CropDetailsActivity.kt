package com.example.agro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.agro.activities.FetchingActivity
import com.example.agro.models.cropModel
import com.google.firebase.database.FirebaseDatabase



class CropDetailsActivity : AppCompatActivity() {

    private lateinit var tvcropId: TextView
    private lateinit var tvcropName: TextView
    private lateinit var tvcropType: TextView
    private lateinit var tvcropSize: TextView
    private lateinit var tvcropWork: TextView
    private lateinit var tvcropExp: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("cropId").toString(),
                intent.getStringExtra("cropName").toString(),
                intent.getStringExtra("cropType").toString(),
                intent.getStringExtra("cropSize").toString(),
                intent.getStringExtra("cropWorkers").toString(),
                intent.getStringExtra("cropExpense").toString()
            )
        }
        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Crop Data")
                .setMessage("Are you sure you want to delete this crop record?")
                .setPositiveButton("Yes") { _, _ ->
                    deleteRecord(intent.getStringExtra("cropId").toString())
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    private fun initView() {
        tvcropId = findViewById(R.id.tvcropId)
        tvcropName = findViewById(R.id.tvcropName)
        tvcropType = findViewById(R.id.tvcropType)
        tvcropSize = findViewById(R.id.tvcropSize)
        tvcropWork = findViewById(R.id.tvcropWork)
        tvcropExp = findViewById(R.id.tvcropExp)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvcropId.text = intent.getStringExtra("cropId")
        tvcropName.text = intent.getStringExtra("cropName")
        tvcropType.text = intent.getStringExtra("cropType")
        tvcropSize.text = intent.getStringExtra("cropSize")
        tvcropWork.text = intent.getStringExtra("cropWorkers")
        tvcropExp.text = intent.getStringExtra("cropExpense")

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Crops").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Crop data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun openUpdateDialog(
        cropId: String,
        cropName: String,
        cropType:String,
        cropSize:String,
        cropWorkers:String,
        cropExpense:String

    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etcName = mDialogView.findViewById<EditText>(R.id.etcName)
        val etcType= mDialogView.findViewById<EditText>(R.id.etcType)
        val etcSize= mDialogView.findViewById<EditText>(R.id.etcSize)
        val etcWorkers= mDialogView.findViewById<EditText>(R.id.etcWorkers)
        val etcExp= mDialogView.findViewById<EditText>(R.id.etcExp)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etcName.setText(intent.getStringExtra("cropName").toString())
        etcType.setText(intent.getStringExtra("cropType").toString())
        etcSize.setText(intent.getStringExtra("cropSize").toString())
        etcWorkers.setText(intent.getStringExtra("cropWorkers").toString())
        etcExp.setText(intent.getStringExtra("cropExpense").toString())

        mDialog.setTitle("Updating $cropName Crop Details")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateCropData(

                cropId,
                etcName.text.toString(),
                etcType.text.toString(),
                etcSize.text.toString(),
                etcWorkers.text.toString(),
                etcExp.text.toString()


            )

            Toast.makeText(applicationContext, "Crop Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvcropName.text = etcName.text.toString()
            tvcropType.text = etcType.text.toString()
            tvcropSize.text = etcSize.text.toString()
            tvcropWork.text = etcWorkers.text.toString()
            tvcropExp.text = etcExp.text.toString()
            alertDialog.dismiss()
        }
    }

    private fun updateCropData(
        id: String,
        name: String,
        type:String,
        size: String,
        workers: String,
        expenses: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Crops").child(id)
        val cropInfo = cropModel(id, name, type, size, workers,expenses)
        dbRef.setValue(cropInfo)
    }

}

