package com.example.productmanagementkotlin.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.productmanagementkotlin.models.ProductModel
import com.example.productmanagementkotlin.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class InsertionActivity : AppCompatActivity() {

    private lateinit var productName: EditText
    private lateinit var productPrice: EditText
    private lateinit var productDescription: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        productName = findViewById(R.id.etEmpName)
        productPrice = findViewById(R.id.etEmpAge)
        productDescription = findViewById(R.id.etEmpSalary)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")


        btnSaveData.setOnClickListener {
            saveProductData()
        }
    }

    private fun saveProductData() {

        //getting values
        val empName = productName.text.toString()
        val empAge = productPrice.text.toString()
        val empSalary = productDescription.text.toString()

        if (empName.isEmpty()) {
            productName.error = "Please enter name"
        }
        if (empAge.isEmpty()) {
            productPrice.error = "Please enter age"
        }
        if (empSalary.isEmpty()) {
            productDescription.error = "Please enter salary"
        }

        val empId = dbRef.push().key!!

        val employee = ProductModel(empId, empName, empAge, empSalary)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                productName.text.clear()
                productPrice.text.clear()
                productDescription.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

}