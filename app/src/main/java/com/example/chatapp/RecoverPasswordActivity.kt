package com.example.chatapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RecoverPasswordActivity : AppCompatActivity() {
    lateinit var editTextTextEmailAddress3 : TextView
    lateinit var Recover : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        //actionbar info
        val actionBar = supportActionBar
        actionBar!!.title = "Password Recover"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor(	"#000000")))
        init()
    }

    private fun init(){

        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3)
        Recover = findViewById(R.id.Recover)

        // move to password change page if email is correct.
        // if not type email again
        Recover.setOnClickListener {
            val email = editTextTextEmailAddress3.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // email check
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, ChangePasswordActivity::class.java))
                    }else{
                        Toast.makeText(this, "Enter Correct Email", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}