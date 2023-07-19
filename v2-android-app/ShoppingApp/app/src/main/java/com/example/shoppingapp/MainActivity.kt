package com.example.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.config.StorylyConfig
import com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling
import com.example.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val mainPage = binding.root
        setContentView(mainPage)

        val storylyView = findViewById<StorylyView>(R.id.storyly_view)

        storylyView.storylyInit = StorylyInit(
            storylyId ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NDYsImluc19pZCI6MTI1ODJ9.k7IVUbx4b23WTobh7u-ZIAYMdjN1xIDyA8z5WWncWbU",
            StorylyConfig.Builder()
                .setStoryGroupStyling(
                    StorylyStoryGroupStyling.Builder()
                        .setIconHeight(240)
                        .setIconWidth(240)
                        .setIconCornerRadius(30)
                        .setSize(StoryGroupSize.Custom)
                        .build()
                )
                .build()
        )

    }
}