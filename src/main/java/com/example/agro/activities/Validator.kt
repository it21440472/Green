package com.example.agro.activities

class Validator(
    private val name: String,
    private val type: String,
    private val size: Int,
    private val work: Int,
    private val exp: Int
) {

    // Function to check if the name is not empty
    fun isNameValid(): Boolean {
        return name.isNotEmpty()
    }

    // Function to check if the type is not empty
    fun isTypeValid(): Boolean {
        return type.isNotEmpty()
    }

    // Function to check if the size is positive
    fun isSizeValid(): Boolean {
        return size > 0
    }

    // Function to check if the work is positive
    fun isWorkValid(): Boolean {
        return work > 0
    }

    // Function to check if the exp is positive
    fun isExpValid(): Boolean {
        return exp > 0
    }

    // Function to check if all input parameters are valid
    fun isInputValid(): Boolean {
        return isNameValid() && isTypeValid() && isSizeValid() && isWorkValid() && isExpValid()
    }

    fun validateInput(): Boolean {
        return !name.isNullOrEmpty() && !type.isNullOrEmpty() && size > 0 && work > 0 && exp > 0
    }
}


//    fun validateInput(addcropname: String, addcroptype: String, addcropsize: Int, addcropwork: Int, addcropExp: Int): Boolean {
//        return !(addcropname.isEmpty() || addcroptype.isEmpty() || addcropsize <= 0 || addcropwork <= 0 || addcropExp <= 0)
//