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

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var changePassword : TextView
    lateinit var changeBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

    //actionbar info
    val actionBar = supportActionBar
    actionBar!!.title = "Password Change"
    actionBar.setDisplayHomeAsUpEnabled(true)
    actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor(    "#000000")))
    init()
    }

    // initialization buttons
    private fun init() {


        changePassword = findViewById(R.id.changePassword)
        changeBtn = findViewById(R.id.ChangeBtn)

        //Password change
        changeBtn.setOnClickListener {
            val newPassword = changePassword.text.toString()
            if (newPassword.isEmpty() || newPassword.length < 8){
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, LogInActivity::class.java))
                    }else {
                        Toast.makeText(this, "Password must be 8 symbol", Toast.LENGTH_SHORT).show()
                    }

                }

        }

    }
}