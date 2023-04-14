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

        val userPropertiesData = mapOf(
            "username" to "John",
            "gift_amount" to "35%",
            "cover_img" to "https://cdn.pixabay.com/photo/2022/10/15/21/23/cat-7523894_1280.jpg"
        )

        storylyView.storylyInit = StorylyInit(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjc3NzMsImFwcF9pZCI6MTMxOTIsImluc19pZCI6MTQzNDR9.AY5SuUs6aTL8cjBiEvgwhTlZyO06_BIl7Dg63jxFYYU",
            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjcwMTgsImFwcF9pZCI6MTMwNTgsImluc19pZCI6MTQxODB9.1owqiSRf7LPmYbeHtq2WWfIcpfqLXjinFJMfWuCSxQU",
            segmentation = StorylySegmentation(setOf("how-to", "introduction", "discover", "newuser")),
            userData = userPropertiesData,
            customParameter = "Gold Member",
        )

        val externalData = mutableListOf<Map<String, Any?>>(
            mapOf(
                "{username}" to "John Doe",
                "{e-mail}" to "john@email.com",
            )
        )
        storylyView.setExternalData(externalData)

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