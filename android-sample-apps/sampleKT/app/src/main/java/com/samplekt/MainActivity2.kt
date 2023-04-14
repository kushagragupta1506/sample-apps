package com.samplekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.samplekt.databinding.ActivityMain2Binding



class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val secondPage = binding.root
        setContentView(secondPage)

        val storylyView = findViewById<StorylyView>(R.id.storyly_view)
        storylyView.storylyInit = StorylyInit(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NTIsImluc19pZCI6MTI1ODl9.Z_F9S6OyES9rfwYDFV47zT0_DbP0WUwqE5EmYOpCZRQ",

        )

        val externalData = mutableListOf<Map<String, Any?>>(
            mapOf(
                "{name}" to "sahin",
                //"{surnmae}" to "aycicek"
            ),
            mapOf(
                "{name}" to "alper",
                // "{surnmae}" to "aycicek"
            )
        )
        storylyView.setExternalData(externalData)


        binding.button2.setOnClickListener{
            val goFirstPage = Intent(applicationContext, MainActivity::class.java)
            startActivity(goFirstPage)
        }

    }
}