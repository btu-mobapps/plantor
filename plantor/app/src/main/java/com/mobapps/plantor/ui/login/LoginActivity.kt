package com.mobapps.plantor.ui.login


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.mobapps.plantor.MainActivity


import com.mobapps.plantor.R

class LoginActivity : AppCompatActivity() {
    private lateinit var userName: EditText
    private lateinit var password : EditText
    private lateinit var button : Button
    private lateinit var recoverButton: Button

    private lateinit var userAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userAuth = FirebaseAuth.getInstance()

        if (userAuth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        userName = findViewById(R.id.username)
        password = findViewById(R.id.password)
        button = findViewById(R.id.button)
        recoverButton = findViewById(R.id.recoverButton)

        button.setOnClickListener{
            val email = userName.text.toString()
            val password = password.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "Empty Email", Toast.LENGTH_SHORT).show()
            }
            else if (password.isEmpty() || password.length < 5 ){
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
            }
            else{
                userAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    } else{
                        if (it.exception is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "Incorrect credentials!", Toast.LENGTH_SHORT).show()
                        } else {
                            startActivity(Intent(this,RegisterActivity::class.java))
                        }
                    }
                }
            }
        }

        recoverButton.setOnClickListener{
            startActivity(Intent(this,LostActivity::class.java))
        }
    }
}