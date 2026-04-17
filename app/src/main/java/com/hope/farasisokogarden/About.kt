package com.hope.farasisokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {
    //Declaring a tts variable
    //lateinit is used to initialize the TTS later
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text = findViewById<TextView>(R.id.textview)
        val speakButton = findViewById<EditText>(R.id.inputText)
        val listen = findViewById<Button>(R.id.speakButton)

        //initializing tts
        //create a TTS (text-to speech) object

        tts = TextToSpeech(this) {
            //check if the speech is successful
            if (it == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
            }
        }
       // set button listener
        speakButton.setOnClickListener {
            val abouttext = text.text.toString()
            tts.speak(abouttext, TextToSpeech.QUEUE_FLUSH,null,null)
        }


        //the vice versa code
//        val input = listen.text.toString()
//         val textToSpeak = if (input.isNotEmpty()) {
//             input
//         } else {
//             text.text.toString()
//         }
    }

    override fun onDestroy() {
        tts.stop()// stops tts
        tts.shutdown()
        super.onDestroy()
    }
}
