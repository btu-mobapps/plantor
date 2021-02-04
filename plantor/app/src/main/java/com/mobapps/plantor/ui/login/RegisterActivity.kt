package com.mobapps.plantor.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mobapps.plantor.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerEmail : EditText
    private lateinit var registerPassword : EditText
    private lateinit var registerButton : Button
    private lateinit var userAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userAuth = FirebaseAuth.getInstance()
        registerEmail = findViewById(R.id.registerEmail)
        registerPassword = findViewById(R.id.registerPassword)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener{
            val email = registerEmail.text.toString()
            val password = registerPassword.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "Email Empty", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty() || password.length < 5){
                Toast.makeText(this, "Password Empty", Toast.LENGTH_SHORT).show()
            }else {
                userAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Error Error Error ", Toast.LENGTH_SHORT).show()
                    }
                }

            }



        }



    }
}