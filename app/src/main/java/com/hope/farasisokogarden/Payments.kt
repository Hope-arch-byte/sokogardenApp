package com.hope.farasisokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams
import org.w3c.dom.Text

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Receive/retrieve Extras data the product_name and cost
        //This data is passed via intent
        val productName = intent.getStringExtra("product_name")
        val productCost = intent.getIntExtra("product_cost",0)
        val productPhoto = intent.getStringExtra("product_photo")
        val productDescription = intent.getStringExtra("product_description")

        //find views by their ids
        val photo = findViewById<ImageView>(R.id.product_photo)

        val name = findViewById<TextView>(R.id.product_name)

        val cost = findViewById<TextView>(R.id.product_cost)

        val description = findViewById<TextView>(R.id.product_description)

        val phone = findViewById<EditText>(R.id.phone)

        val pay =findViewById<Button>(R.id.purchase)

        //updating TextViews with values passed via intent
        name.text = productName
        description.text = productDescription
        cost.text = "Ksh $productCost"


        //loading the photo
        Glide.with(this)
            .load(productPhoto)
            .circleCrop()
            .into(photo)

        pay.setOnClickListener {
            //calling mpesa api
            val api = "http://hopegathoni.alwaysdata.net/api/mpesa_payment"

            //collecting data using request params, put phone and cost as key value pairs
            val data = RequestParams()
            data.put("amount",productCost) //passed via intent
            data.put("phone",phone.text.toString()) //entered by user in phone edittext

            //access api helper
            val helper = ApiHelper(applicationContext)
            //post data to api endpoint
            helper.post(api,data)
        }
    }
}