package com.example.chatapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var profileButton : Button
    lateinit var usersButton : Button
    lateinit var logOutButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //actionbar info
        val actionBar = supportActionBar
        actionBar!!.title = "Chats App"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor(	"#1BD322")))
        init()
        listener()
    }
    //initialization
    private fun init(){
        profileButton = findViewById(R.id.profileButton)
        usersButton = findViewById(R.id.usersButton)
        logOutButton = findViewById(R.id.logOutButton)

    }
    // listeners
    private fun listener(){

        // move to profile page
        profileButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        //move to user list
        usersButton.setOnClickListener {
            startActivity(Intent(this, UsersActivity::class.java))
        }

        // logout from account and move to login page
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
    }
}