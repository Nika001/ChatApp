package com.example.chatapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var editTextTextEmailAddress : TextView
    private lateinit var editTextTextPassword : TextView
    private lateinit var registrationText : TextView
    private lateinit var logInBtn : Button
    private lateinit var passwordForgot : TextView

    private lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // actionbar info
        val actionBar = supportActionBar
        actionBar!!.title = "Welcome"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))




        mAuth = FirebaseAuth.getInstance()


        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)


        // move to registration page
        registrationText = findViewById(R.id.registrationText)
        registrationText.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        // email and password log in
        logInBtn = findViewById(R.id.logInBtn)
        logInBtn.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()
            //if email or paas is  empty start again
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            login(email, password)
        }
        //move to password recovery page
        passwordForgot = findViewById(R.id.passwordForgot)
        passwordForgot.setOnClickListener {
            startActivity(Intent(this, RecoverPasswordActivity::class.java))
        }


    }


    private fun login(email : String, password : String){
    //login for user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "email or password is incorrect", Toast.LENGTH_SHORT).show()
                }
            }
    }

}