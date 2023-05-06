package com.example.mad12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertationActivity : AppCompatActivity() {

    private lateinit var editTextTextPersonName : EditText
    private lateinit var editTextTextPersonName1 : EditText
    private lateinit var editTextTextPersonName2 : EditText
    private lateinit var editTextTextPersonName3: EditText
    private lateinit var editTextTextPersonName4: EditText
    private lateinit var button7 : Button
    private lateinit var button5 : Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertation)

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextTextPersonName1 = findViewById(R.id.editTextTextPersonName1)
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2)
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3)
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4)
        button7= findViewById(R.id.button7)
        button5= findViewById(R.id.button5)

        dbRef = FirebaseDatabase.getInstance().getReference("Machine")

        button5.setOnClickListener{
            saveMachine()
        }
    }
    private fun saveMachine() {
        //getting values
        val OwnerName = editTextTextPersonName.text.toString()
        val MachineName = editTextTextPersonName1.text.toString()
        val MachineModel = editTextTextPersonName2.text.toString()
        val RentalPay = editTextTextPersonName3.text.toString()
        val ContactNumber = editTextTextPersonName4.text.toString()

        if (OwnerName.isEmpty()) {
            editTextTextPersonName.error = "Please Enter Owner Name"
        }

        if (MachineName.isEmpty()) {
            editTextTextPersonName1.error = "Please Enter Machine Name"
        }
        if (MachineModel.isEmpty()) {
            editTextTextPersonName2.error = "Please Enter Machine Model"
        }
        if (RentalPay.isEmpty()) {
            editTextTextPersonName3.error = "Please Enter Rental Play"
        }
        if (ContactNumber.isEmpty()) {
            editTextTextPersonName4.error = "Please Enter Contact Number"
        }
        val empId = dbRef.push().key!!

        val Machine = EmployeeModel(empId,OwnerName, MachineName, MachineModel, RentalPay, ContactNumber)

        dbRef.child(empId).setValue(Machine)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Inserted successfully", Toast.LENGTH_LONG).show()

                editTextTextPersonName.text.clear()
                editTextTextPersonName1.text.clear()
                editTextTextPersonName2.text.clear()
                editTextTextPersonName3.text.clear()
                editTextTextPersonName4.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}