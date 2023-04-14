package com.samplekt

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.appsamurai.storyly.*
import com.appsamurai.storyly.analytics.StorylyEvent
import com.samplekt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val firstPage = binding.root
        setContentView(firstPage)

        val storylyView = findViewById<StorylyView>(R.id.storyly_view)
        storylyView.storylyInit = StorylyInit(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjEwOTgsImFwcF9pZCI6NzA2LCJpbnNfaWQiOjcwMX0.TnIHnVLx7loloVe-AQrePlLANQuqw1ssth2GEPvT8dw",
            //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTIyNjIsImluc19pZCI6MTMyMzd9.CfmZwJaMa8QkLU1GgIpdfN2UjUpr_uTnoF1m9OuoxyY",
            //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjEwOTgsImFwcF9pZCI6NzA2LCJpbnNfaWQiOjcwMX0.TnIHnVLx7loloVe-AQrePlLANQuqw1ssth2GEPvT8dw",
            //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NTIsImluc19pZCI6MTI1ODl9.Z_F9S6OyES9rfwYDFV47zT0_DbP0WUwqE5EmYOpCZRQ",
            //customParameter = "2"
        )

        binding.button.setOnClickListener{
            val goSecondPage = Intent(applicationContext, MainActivity2::class.java)
            startActivity(goSecondPage)
        }

        storylyView.storylyListener = object : StorylyListener {
            var initialLoad = true


            /*override fun storylyLoaded(
                storylyView: StorylyView,
                storyGroupList: List<StoryGroup>,
                dataSource: StorylyDataSource
            ) {
                Log.d("[storyly]", "IntegrationActivity:storylyLoaded - storyGroupList size {${storyGroupList.size}} - source {$dataSource}")
            }*/

             override fun storylyLoaded(storylyView: StorylyView, storyGroupList: List<StoryGroup>, dataSource: StorylyDataSource) {
                super.storylyLoaded(storylyView, storyGroupList, dataSource)
                if (initialLoad && storyGroupList.isNotEmpty()) {
                    initialLoad = false
                    storylyView.visibility = View.VISIBLE
                }


                Log.d("[storyly]", "IntegrationActivity:storylyLoaded - storyGroupList size {${storyGroupList.size}} - source {$dataSource}")
            }

            override fun storylyLoadFailed(
                storylyView: StorylyView,
                errorMessage: String
            ) {
                Log.d("[storyly]", "IntegrationActivity:storylyLoadFailed - error {$errorMessage}")

                if (!initialLoad) {
                    storylyView.visibility = View.GONE;
                }
            }

            override fun storylyActionClicked(storylyView: StorylyView, story: Story) {

                story.media.actionUrl.let { url ->
                    Log.d(
                        "-----------[storyly]--------",
                        "IntegrationActivity:storylyActionClicked - forwarding to url {$url} and ${story.media.type} "
                    )
                    startActivity(
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("$url")
                        }
                    )
                }
            }



        }
    }
}