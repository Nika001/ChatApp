package com.example.chatapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UsersActivity : AppCompatActivity() {

    private lateinit var recyclerViewUser : RecyclerView
    private lateinit var userList : ArrayList<User>
    private lateinit var adapter : UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        supportActionBar?.title = "Users"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(    "#000000")))

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()

        recyclerViewUser = findViewById(R.id.recyclerViewUser)
        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        recyclerViewUser.layoutManager = LinearLayoutManager(this)
        recyclerViewUser.adapter = adapter

        mDatabase.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for (postSnapshot in snapshot.children){

                    val currentUser = postSnapshot.getValue(User::class.java)

                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                        userList.add(currentUser!!)

                    }

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}