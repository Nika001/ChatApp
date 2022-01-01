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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    lateinit var editTextTextPersonName: TextView
    lateinit var editTextTextEmailAddress2 : TextView
    lateinit var editTextTextPassword2 : TextView
    lateinit var registerButton : Button
    lateinit var logInTxt : TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

    //actionbar info
    val actionBar = supportActionBar
    actionBar!!.title = "Registration"
    actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor(	"#000000")))
    init()
    listener()

    }

    private fun init(){

        mAuth = FirebaseAuth.getInstance()
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2)
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2)
        registerButton = findViewById(R.id.registerButton)
        logInTxt = findViewById(R.id.logInTxt)
    }

    private fun listener() {
        // move to login page
        logInTxt.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        // user registration
        registerButton.setOnClickListener {
            val email = editTextTextEmailAddress2.text.toString()
            val password = editTextTextPassword2.text.toString()
            val name = editTextTextPersonName.text.toString()

            if (email.isEmpty() || password.isEmpty() || password.length <8 || name.isEmpty()) {
                Toast.makeText(this, "Enter Name, Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registration(name, email, password)

        }
    }

    private fun registration(name : String, email : String, password : String){
        //registration user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this, LogInActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String){
        database = FirebaseDatabase.getInstance().getReference()

        database.child("user").child(uid).setValue(User(name, email, uid))

    }
}
