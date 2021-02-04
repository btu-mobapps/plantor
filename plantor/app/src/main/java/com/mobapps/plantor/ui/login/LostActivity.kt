package com.mobapps.plantor.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mobapps.plantor.R

class LostActivity : AppCompatActivity() {

    private lateinit var textRecover : EditText
    private lateinit var buttonRecover : Button
    private lateinit var userAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost)

        textRecover = findViewById(R.id.textRecover)
        buttonRecover = findViewById(R.id.buttonRecover)
         userAuth = FirebaseAuth.getInstance()

        buttonRecover.setOnClickListener{
            val email = textRecover.text.toString()
            if (email.isEmpty()){
                Toast.makeText(this, "Email Empty", Toast.LENGTH_SHORT).show()
            } else {
                userAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Password was sent ", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }

            }


        }

    }
}