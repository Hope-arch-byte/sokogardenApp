package com.hope.farasisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //sign up intent
        val signIn = findViewById<TextView>(R.id.signin_link)
        signIn.setOnClickListener {
            val signInLink = Intent(applicationContext, SignUp::class.java)
            startActivity(signInLink)
        }

        //http://hopegathoni.alwaysdata.net/api/mpesa_payment


        //find the edittext button by id
        val email = findViewById<EditText>(R.id.email)

        val password = findViewById<EditText>(R.id.password)

        val signinButton = findViewById<Button>(R.id.signin)

        signinButton.setOnClickListener {

            val api = "http://hopegathoni.alwaysdata.net/api/signin"
            //request params is the container used to collect the user details and take to the apis.It is similar to form data in js
            val data = RequestParams()

            data.put("email",email.text.toString())
            data.put("password",password.text.toString().trim())

            //api helper delivers data to the api
            val helper = ApiHelper(applicationContext)
            helper.post_login(api,data)
        }
    }
}