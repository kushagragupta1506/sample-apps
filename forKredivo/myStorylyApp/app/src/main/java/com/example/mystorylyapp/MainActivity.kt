package com.example.mystorylyapp

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appsamurai.storyly.*
import com.appsamurai.storyly.analytics.StorylyEvent
import com.appsamurai.storyly.styling.StoryGroupIconStyling
import com.appsamurai.storyly.styling.StoryGroupTextStyling
import com.appsamurai.storyly.styling.StoryHeaderStyling
import com.example.mystorylyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val mainPage = binding.root
        setContentView(mainPage)

        val storylyView = findViewById<StorylyView>(R.id.storyly_view)
        val storylyView_second = findViewById<StorylyView>(R.id.storyly_view_second)
        val storylyView_third = findViewById<StorylyView>(R.id.storyly_view_third)

        val userPropertiesData = mapOf(
            "username" to "John",
            "gift_amount" to "35%",
            "cover_img" to "https://cdn.pixabay.com/photo/2022/10/15/21/23/cat-7523894_1280.jpg"
        )

        storylyView.storylyInit = StorylyInit(
            "YOUR_TOKEN",
            segmentation = StorylySegmentation(setOf("how-to", "introduction", "discover", "newuser")),
            customParameter = "8TUi2H0CnU",
        )

        storylyView_second.storylyInit = StorylyInit(
            "YOUR_TOKEN",
            customParameter = "8TUi2H0CnU",

            )

        storylyView_third.storylyInit = StorylyInit(
            "YOUR_TOKEN",
            customParameter = "8TUi2H0CnU",

            )
        /*storylyView_second.setStoryGroupSize(StoryGroupSize.Custom)
        storylyView_second.setStoryGroupIconStyling(StoryGroupIconStyling(
            200f,
            200f,
            100f
        ))*/


        val externalData = mutableListOf<Map<String, Any?>>(
            mapOf(
                "{username}" to "John Doe",
                "{e-mail}" to "john@email.com",
            )
        )
        //storylyView.setExternalData(externalData)

        storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(
            //typeface = Typeface.MONOSPACE
        ))

        storylyView.setStoryGroupSize(StoryGroupSize.Large)
        storylyView.setStoryGroupIconStyling(StoryGroupIconStyling(
            200f,
            200f,
            100f
        ))
        //storylyView.setStoryInteractiveTextTypeface(Typeface.MONOSPACE)

        storylyView.setStoryHeaderStyling(StoryHeaderStyling(
            isTextVisible = true,
            isIconVisible = true
        ))

        storylyView.storylyListener = object : StorylyListener {
            // Override event functions
            override fun storylyLoaded(storylyView: StorylyView,
                                       storyGroupList: List<StoryGroup>,
                                       dataSource: StorylyDataSource
            ) {
                Log.d("[Storyly]","IntegrationActivity: StoryLoaded StoryGroup Size: {${storyGroupList.size}} - Data Source: {${dataSource}}")
            }

            override fun storylyLoadFailed(storylyView: StorylyView,
                                           errorMessage: String) {
                Log.d("[Storyly]", "IntegrationActivity: storylyLoadFailed Eror: {${errorMessage}}")
            }

            override fun storylyActionClicked(storylyView: StorylyView, story: Story)
            {
                // story.media.actionUrl is important field
                story.media.actionUrl.let { url ->
                    Log.d("[storyly]", "IntegrationActivity:storylyActionClicked - forwarding to url {$url}")
                    startActivity(
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("$url")
                        }
                    )
                }
            }

            override fun storylyEvent(storylyView: StorylyView, event: StorylyEvent,
                                      storyGroup: StoryGroup?, story: Story?,
                                      storyComponent: StoryComponent?) {
                Log.d("-----Storyly-----", "StoryComponent:${storyComponent?.type}")
                Log.d("-----Storyly-----", "Story:${story?.media}")
                Log.d("-----Storyly-----", "StoryEvent:${event}")
            }

            override fun storylyUserInteracted(storylyView: StorylyView,
                                               storyGroup: StoryGroup,
                                               story: Story,
                                               storyComponent: StoryComponent) {
                Log.d("Storyly", "storyComponent:${storyComponent}")
            }
        }

    }
}