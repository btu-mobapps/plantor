package com.mobapps.plantor.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.mobapps.plantor.MainActivity
import com.mobapps.plantor.R

class ChangePasswordActivity : AppCompatActivity() {


    private lateinit var userAuth: FirebaseAuth
    private lateinit var newPassword : EditText
    private lateinit var confirmPassword : EditText
    private lateinit var buttonChange : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        userAuth = FirebaseAuth.getInstance()
        newPassword = findViewById(R.id.newPassword)
        confirmPassword = findViewById(R.id.confirmPassword)
        buttonChange = findViewById(R.id.buttonChange)

        buttonChange.setOnClickListener{
           val newPassword = newPassword.text.toString()
            val confirmPassword = confirmPassword.text.toString()

            if (newPassword.isEmpty() ){
                Toast.makeText(this, "Password Can't be Empty !  ", Toast.LENGTH_SHORT).show()}

            else if (newPassword != confirmPassword){
                Toast.makeText(this, "Please Confirm Password ! ", Toast.LENGTH_SHORT).show()
            }else {

                userAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Password Succesfully Changed", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Something Wrong ! ", Toast.LENGTH_SHORT).show()
                    }

                }
            }


    }
}}