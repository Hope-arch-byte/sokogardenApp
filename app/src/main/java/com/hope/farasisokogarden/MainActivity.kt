package com.hope.farasisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signIn=findViewById<Button>(R.id.signin)
        signIn.setOnClickListener{
            val signInIntent = Intent(applicationContext, SignIn::class.java)
            startActivity(signInIntent)
        }
        val signUp=findViewById<Button>(R.id.signup)
        signUp.setOnClickListener {
            val signUpIntent = Intent(applicationContext, SignUp::class.java)
            startActivity(signUpIntent)
        }

        // fetch progress bar and recycler view by their ids
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val api = "http://hopegathoni.alwaysdata.net/api/getproductdetails"

        val helper = ApiHelper(applicationContext)
        helper.loadProducts(api,recyclerView,progressbar)

        val about = findViewById<Button>(R.id.about)
        about.setOnClickListener {
            val aboutIntent = Intent(applicationContext, About::class.java)
            startActivity(aboutIntent)
        }


    }
}