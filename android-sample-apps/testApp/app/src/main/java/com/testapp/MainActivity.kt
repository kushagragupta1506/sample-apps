package com.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.appsamurai.storyly.*
import com.appsamurai.storyly.styling.StoryGroupListStyling
import com.testapp.databinding.ActivityMainBinding
import com.testapp.styling_templates.WideLandscapeViewFactory
import com.testapp.styling_templates.ui.LandscapeViewFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        binding.stylingWideLandscape.storyGroupViewFactory = WideLandscapeViewFactory(this)
        //binding.stylingLandscape.storyGroupViewFactory = LandscapeViewFactory(this)

        setContentView(binding.root) // Ekranda custom styling gösterimi için SetContentView bu şekilde editlenmeli

        //val storylyView = findViewById<StorylyView>(R.id.storyly_view)
        val wideLandscapeViewFactory = findViewById<StorylyView>(R.id.styling_wide_landscape)

        val storeName = "new york"
        val segmentation = StorylySegmentation(setOf("store=$storeName"))
        wideLandscapeViewFactory.storylyInit = StorylyInit(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcwMTgsImFwcF9pZCI6MTE1NDQsImluc19pZCI6MTIzNDF9.Ffue1gq3hBpLuiQ6FVseby94Lh0Y6PNLOK6suaHbnvo",
        )




        /*val landscapeViewFactory = findViewById<StorylyView>(R.id.styling_landscape)
        landscapeViewFactory.storylyInit = StorylyInit(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjUwMjMsImFwcF9pZCI6OTQ3OSwiaW5zX2lkIjo5OTM3fQ.VDoqNGj9h_N8iHL_p_zk3C6DCJWsurpydn4BAfxLElY",
            //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcwMTgsImFwcF9pZCI6MTE1NDQsImluc19pZCI6MTIzNDF9.Ffue1gq3hBpLuiQ6FVseby94Lh0Y6PNLOK6suaHbnvo",
        )*/
    }
}