package com.uiapp

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uiapp.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import com.appsamurai.storyly.*
import android.util.Log
import android.view.View
import com.appsamurai.storyly.StoryComponentType.*
import com.appsamurai.storyly.analytics.StorylyEvent
import com.appsamurai.storyly.data.managers.product.STRProductItem
import com.appsamurai.storyly.data.managers.product.STRProductVariant
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMediaPlaylist
import com.appsamurai.storyly.styling.StoryGroupIconStyling
import com.appsamurai.storyly.styling.StoryGroupListOrientation
import com.appsamurai.storyly.styling.StoryGroupListStyling
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val segmentData = "french"
        const val customData = "1"
        //const val storylyFlag = true
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val lang = Locale.getDefault().getLanguage()
        Log.d("<<<<<<<Lang>>>>>>>","Lang: {$lang}")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val firstPage = binding.root
        setContentView(firstPage)
        val storylyFlag = true
        val padVal_1 = 40f
        val padVal_2 = 100000000f
        val storylyView = findViewById<StorylyView>(R.id.storyly_view)
        storylyView.setStoryGroupIconBackgroundColor(color = android.graphics.Color.parseColor("#FF0000"))
        if (storylyFlag == true) {
            storylyView.storylyInit = StorylyInit(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjcxMzYsImFwcF9pZCI6MTI5NDAsImluc19pZCI6MTQwMjN9.IGRFFWr9ydeSAg1PKhz1CmQT8hSbIQV5dx9uazz2SZo",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjU2NTEsImFwcF9pZCI6MTIzNjQsImluc19pZCI6MTMzNjl9.eW116xdCY_56am2obYEHBPZ7dOH_yxDmAw6KTBUpU3s",
                //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjU2NjQsImFwcF9pZCI6MTMwMzEsImluc19pZCI6MTQxNTJ9.PWZqbnK4ZWu5D1yCSQlFAMSWMTgxl-oyPXxtHDBCdew",
                //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjcwNjgsImFwcF9pZCI6MTQ2OTcsImluc19pZCI6MTYwNDJ9.r67BlljpdGAGeZ3WfR9N4GDKro8Ctond8T0C1DTfLN4",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjczMzYsImFwcF9pZCI6MTI1MDgsImluc19pZCI6MTM1NDF9.gRxp7XXtzcxNgqY8qTRYZ0uYYPAG8va6KciU-LHgG7E",
                //Alper token "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjUwMjMsImFwcF9pZCI6OTQ3OSwiaW5zX2lkIjo5OTM3fQ.VDoqNGj9h_N8iHL_p_zk3C6DCJWsurpydn4BAfxLElY",
                //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjgxNzAsImFwcF9pZCI6MTM5NDYsImluc19pZCI6MTUxODd9.QFTvJ4W-hLKq-KyPsQx3oL2H7jk0tHwY-Ud7HVgiocg",
                // Coindcx token "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjQ3MTcsImFwcF9pZCI6ODk4NSwiaW5zX2lkIjo5MzU4fQ.jMrUtigWaYo23cjYkcV_SmD3cHsJwPx4ctkaLAVxOe4",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NTIsImluc19pZCI6MTI1ODl9.Z_F9S6OyES9rfwYDFV47zT0_DbP0WUwqE5EmYOpCZRQ",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcwMDQsImFwcF9pZCI6MTE1MzIsImluc19pZCI6MTIzMjZ9.VZV_a_mgDEj4TZ2dULUZAwY6TZPKetzkA0crgWq9wOM",
                // Coin Stats"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjU1MzIsImFwcF9pZCI6MTAzNTUsImluc19pZCI6MTA5NDd9.yDiVVy2BBDr7VgDs8YO0PIDnb7jplrBty3Ds_0h-xNQ",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjU3NzcsImFwcF9pZCI6MTA3NzYsImluc19pZCI6MTE0NjB9.7_roOzDBU95Kd1L4WYSTSeByseXCvEUtYe-l9JGy_DM",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjUwMjMsImFwcF9pZCI6OTQ3OSwiaW5zX2lkIjo5OTM3fQ.VDoqNGj9h_N8iHL_p_zk3C6DCJWsurpydn4BAfxLElY",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjQ2NDUsImFwcF9pZCI6MTIwMDMsImluc19pZCI6MTI5MDJ9.WXCJBzovJhuVQu9vQGSfk1g7OfGmjWOqgssZ9j02-d4",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMTksImFwcF9pZCI6MTE3MTIsImluc19pZCI6MTI1NDd9.wnubJL8RMSMVu2zGMTeJWdBJAS0eezSCtHwPErUEl-Q",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjUzMDMsImFwcF9pZCI6OTk0NSwiaW5zX2lkIjoxMDQ2MX0.sRshhTKpzBqRX5MdZCucQJIarx90hvjzILxxNgvywR8",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NDYsImluc19pZCI6MTI1ODJ9.k7IVUbx4b23WTobh7u-ZIAYMdjN1xIDyA8z5WWncWbU",
                //Clear token "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjU4NDcsImFwcF9pZCI6MTA5MTksImluc19pZCI6MTE2MjN9.zkysLGVqLhOHPVx-MZbceTpsq12mqQaqR_1e_6xCgWk",
                //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTIyNjIsImluc19pZCI6MTMyMzd9.CfmZwJaMa8QkLU1GgIpdfN2UjUpr_uTnoF1m9OuoxyY",
                //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjcwMTgsImFwcF9pZCI6MTMwNTgsImluc19pZCI6MTQxODB9.1owqiSRf7LPmYbeHtq2WWfIcpfqLXjinFJMfWuCSxQU",
                customParameter = customData,
                //segmentation = StorylySegmentation(setOf(lang))
                /*segmentation = StorylySegmentation(setOf(
                    "discover", "newuser", "wrapped_test", "germany", "de", "my", "en", "turkey", "french"
                )),*/
                userData = mapOf(
                    "username" to "Sonya",
                ),
            )
        }
        Log.d("Init", "Init ${storylyView.storylyInit}")
        if (storylyFlag == false) {
            storylyView.visibility = View.GONE
        }


        //storylyView.setStorylyLayoutDirection(layoutDirection = StorylyLayoutDirection.RTL)


        //storylyView.setStoryInteractiveTextTypeface(Typeface.MONOSPACE)
        storylyView.setStoryGroupSize(StoryGroupSize.Small)
        storylyView.setStoryGroupIconStyling(storyGroupIconStyling = StoryGroupIconStyling(
            cornerRadius = 16f
        ))

        //storylyView.setStoryGroupIconBackgroundColor(color = android.graphics.Color.parseColor("#FF0000"))

        //storylyView.setStoryItemTextTypeface(Typeface.MONOSPACE)
        //storylyView.openStory("62376", "542324")

        storylyView.setStoryGroupListStyling(
            StoryGroupListStyling(
                horizontalEdgePadding = padVal_1,
                horizontalPaddingBetweenItems = padVal_1,
                orientation = StoryGroupListOrientation.Vertical,
                sections = 2

            )
        )

        binding.button.setOnClickListener{
            val goSecondPage = Intent(applicationContext, MainActivity2::class.java)
            startActivity(goSecondPage)
            //storylyView.openStory("51351", "444704") // --------Open Story--------
        }

        storylyView.storylyProductListener = object : StorylyProductListener {
            override fun storylyEvent(
                storylyView: StorylyView,
                event: StorylyEvent,
                product: STRProductItem?,
                extras: Map<String, String>
            ) {
                when (event){
                    StorylyEvent.StoryAddToCartClicked ->{
                        Log.d("Shopping", "StoryAddToCartClicked ${product}")
                    }
                    StorylyEvent.StoryGoToCartClicked -> {
                        Log.d("Shopping", "StoryGoToCartClicked")
                    }
                    StorylyEvent.StoryProductCatalogOpened -> {
                        Log.d("Shopping", "StoryProductCatalogOpened")
                    }
                    StorylyEvent.StoryProductCatalogClosed -> {
                        Log.d("Shopping", "StoryProductCatalogClosed")
                    }
                    StorylyEvent.StoryProductSelected -> {
                        Log.d("Shopping", "StoryProductSelected")
                    }
                }
            }

            override fun storylyHydration(storylyView: StorylyView, productIds: List<String>) {
                Log.d("Shopping", "storylyHydration ${productIds}")

            }
        }

        storylyView.storylyListener = object : StorylyListener {
            var initialLoad = true

            override fun storylyActionClicked(storylyView: StorylyView, story: Story) {

                story.media.actionUrl.let { url ->
                    Log.d("-----------[storyly]--------", "IntegrationActivity:storylyActionClicked - forwarding to url {$url} and ${story.media.type} ")
                    startActivity(
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("$url")
                        }
                    )
                }

                storylyView.dismiss(animationResId = 1)


                /*val goSecondPage = Intent(applicationContext, MainActivity2::class.java)
                startActivity(goSecondPage) */ // go to other page

                /* val fragment = DemoFragment()
                 fragment.onCloseClick = {
                     removeFragments()
                 }
                 showExternalFragment(fragment) */
            }

            override fun storylyLoaded(
                storylyView: StorylyView,
                storyGroupList: List<StoryGroup>,
                dataSource: StorylyDataSource
            ) {
                //Log.d("[storyly]", "IntegrationActivity:storylyLoaded - storyGroupList size {${storyGroupList.size}} - source {$dataSource}")

                /*if (initialLoad && storyGroupList.isNotEmpty()) {
                    initialLoad = false
                    storylyView.visibility = View.VISIBLE
                    Log.d("customParameter", "-----> {${customData}}" )
                }*/

                Log.d("storylyURL", "URL:${storyGroupList}")

                if (dataSource == StorylyDataSource.Local) {
                    Log.d("[Local]", "IntegrationActivity:storylyLoaded - storyGroupList size {${storyGroupList.size}} - source {$dataSource}")
                }

                /*if (dataSource == StorylyDataSource.API) {
                    storylyView.openStory("62376", "542324")
                }*/
            }

            override fun storylyLoadFailed(
                storylyView: StorylyView,
                errorMessage: String
            ) {
                Log.d("[storyly]", "IntegrationActivity:storylyLoadFailed - error {$errorMessage}")

            }

            override fun storylyUserInteracted(
                storylyView: StorylyView,
                storyGroup: StoryGroup,
                story: Story,
                storyComponent: StoryComponent
            ) {

                val quizComponent = storyComponent as? StoryQuizComponent
                val pollComponent = storyComponent as? StoryPollComponent
                val emojiComponent = storyComponent as? StoryEmojiComponent
                val ratingComponent = storyComponent as? StoryRatingComponent
                val promoCodeComponent = storyComponent as? StoryPromoCodeComponent
                val commentComponent = storyComponent as? StoryCommentComponent

                Log.d("--------------storyly-------------------", "StoryComponent{${storyComponent}} and {${story}}")
                Log.d("--------------StoryComponent-------------------", "promoCodeComponent:${promoCodeComponent?.text}")
                Log.d("emojiComponent","customPayload:${emojiComponent?.customPayload}")
                Log.d("quizComponent","customPayload:${quizComponent?.customPayload} ")



            }

            override fun storylyEvent(storylyView: StorylyView, event: StorylyEvent,
                                      storyGroup: StoryGroup?, story: Story?,
                                      storyComponent: StoryComponent?) {
                val quizComponent = storyComponent as? StoryQuizComponent
                val pollComponent = storyComponent as? StoryPollComponent
                val emojiComponent = storyComponent as? StoryEmojiComponent
                val ratingComponent = storyComponent as? StoryRatingComponent
                val promoCodeComponent = storyComponent as? StoryPromoCodeComponent
                val commentComponent = storyComponent as? StoryCommentComponent

                /*val customPay = when (storyComponent?.type){
                    Emoji -> storyComponent as? StoryEmojiComponent
                    Quiz -> storyComponent as? StoryQuizComponent
                    Poll -> storyComponent as? StoryPollComponent
                    Rating -> storyComponent as? StoryRatingComponent
                    PromoCode -> storyComponent as? StoryPromoCodeComponent
                    //SwipeAction -> storyComponent as? Story
                    //ButtonAction -> storyComponent as?
                    //Text -> storyComponent as? StoryEmojiComponent
                    //Image -> storyComponent as? StoryEmojiComponent
                    //Countdown -> storyComponent as? StoryComponent
                    //ProductTag -> storyComponent as? StoryEmojiComponent
                    Comment -> storyComponent as? StoryCommentComponent
                    //Video -> storyComponent as? StoryEmojiComponent
                    //Vod -> storyComponent as? StoryEmojiComponent
                    //null -> storyComponent as? StoryEmojiComponent
                    SwipeAction -> TODO()
                    ButtonAction -> TODO()
                    Text -> TODO()
                    Image -> TODO()
                    Countdown -> TODO()
                    ProductTag -> TODO()
                    Video -> TODO()
                    Vod -> TODO()
                    null -> TODO()
                }*/

                //Log.d("Storyly","customPayload:${emojiComponent?.customPayload} ")
                Log.d("event----->","StoryPaused:${StorylyEvent.StoryPaused} ") // You can track StoryViewed event.
                Log.d("event----->","StoryViewed:${StorylyEvent.StoryViewed} ") // You can track StoryViewed event.
                Log.d ("Event", "PromoCode:${StorylyEvent.StoryPromoCodeCopied}")



                Log.d("--------------StoryType-------------------", "StoryComponent{${storyComponent?.type}}")


                Log.d("--------------storyly-------------------","story:${story?.seen} and ${story?.uniqueId}")
                Log.d("--------------Index-------------------","storyGroupIndex: ${storyGroup?.index} and storyIndex: ${story?.index}")

            }

        }

        //binding.storylyView.storylyListener = storylyListener

        binding.storylyView.hydrateProducts(products)

    }



    fun showExternalFragment(fragment : Fragment) {
        binding.storylyView.showExternalFragment(fragment)
    }

    fun removeFragments() {
        binding.storylyView.dismissAllExternalFragment()
    }
}


var products = listOf(
    STRProductItem(
        productId = "1",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#6D7868"),
            STRProductVariant(name = "size", value = "XS"),
            STRProductVariant(name = "condition", value = "NEW"),
        )
    ),
    STRProductItem(
        productId = "2",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#6D7868"),
            STRProductVariant(name = "size", value = "S"),
            STRProductVariant(name = "condition", value = "NEW"),
        )
    ),
    STRProductItem(
        productId = "5",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#6D7868"),
            STRProductVariant(name = "size", value = "XL"),
            STRProductVariant(name = "condition", value = "NEW")
        )
    ),
    STRProductItem(
        productId = "6",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#282025"),
            STRProductVariant(name = "size", value = "XS"),
            STRProductVariant(name = "condition", value = "NEW")
        )
    ),
    STRProductItem(
        productId = "7",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#282025"),
            STRProductVariant(name = "size", value = "S"),
            STRProductVariant(name = "condition", value = "NEW")
        )
    ),
    STRProductItem(
        productId = "8",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#282025"),
            STRProductVariant(name = "size", value = "M"),
            STRProductVariant(name = "condition", value = "NEW")
        )
    ),
    STRProductItem(
        productId = "9",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#282025"),
            STRProductVariant(name = "size", value = "L"),
            STRProductVariant(name = "condition", value = "NEW")
        )
    ),
    STRProductItem(
        productId = "10",
        productGroupId = "1",
        title = "High-waist midi skirt",
        url = "https://www.storyly.io/",
        desc = "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#282025"),
            STRProductVariant(name = "size", value = "XL"),
            STRProductVariant(name = "condition", value = "OLD")
        )
    ),
    STRProductItem(
        productId = "11",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#171614"),
            STRProductVariant(name = "size", value = "XS")
        )
    ),
    STRProductItem(
        productId = "12",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#171614"),
            STRProductVariant(name = "size", value = "S")
        )
    ),
    STRProductItem(
        productId = "13",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#171614"),
            STRProductVariant(name = "size", value = "M")
        )
    ),
    STRProductItem(
        productId = "14",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#171614"),
            STRProductVariant(name = "size", value = "L")
        )
    ),
    STRProductItem(
        productId = "15",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#171614"),
            STRProductVariant(name = "size", value = "XL")
        )
    ),
    STRProductItem(
        productId = "16",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#A5B9DE"),
            STRProductVariant(name = "size", value = "XS")
        )
    ),
    STRProductItem(
        productId = "17",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#A5B9DE"),
            STRProductVariant(name = "size", value = "S")
        )
    ),
    STRProductItem(
        productId = "18",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#A5B9DE"),
            STRProductVariant(name = "size", value = "M")
        )
    ),
    STRProductItem(
        productId = "19",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#A5B9DE"),
            STRProductVariant(name = "size", value = "L")
        )
    ),
    STRProductItem(
        productId = "20",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#A5B9DE"),
            STRProductVariant(name = "size", value = "XL")
        )
    ),
    STRProductItem(
        productId = "21",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#CFBAD9"),
            STRProductVariant(name = "size", value = "XS")
        )
    ),
    STRProductItem(
        productId = "22",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#CFBAD9"),
            STRProductVariant(name = "size", value = "S")
        )
    ),
    STRProductItem(
        productId = "23",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#CFBAD9"),
            STRProductVariant(name = "size", value = "M")
        )
    ),
    STRProductItem(
        productId = "24",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#CFBAD9"),
            STRProductVariant(name = "size", value = "L")
        )
    ),
    STRProductItem(
        productId = "25",
        productGroupId = "2",
        title = "Basic long-sleeve crop top",
        url = "https://www.storyly.io/",
        desc = "Fitted elastic top with a round neck and long sleeves.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#CFBAD9"),
            STRProductVariant(name = "size", value = "XL")
        )
    ),
    STRProductItem(
        productId = "26",
        productGroupId = "3",
        title = "Handbag with handle",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-3/1-1F1D20.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/2-1F1D20.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/3-1F1D20.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/4-1F1D20.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#1F1D20"),
        )
    ),
    STRProductItem(
        productId = "27",
        productGroupId = "3",
        title = "Handbag with handle",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-3/1-F2EEE3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/2-F2EEE3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/3-F2EEE3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/4-F2EEE3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#F2EEE3"),
        )
    ),
    STRProductItem(
        productId = "28",
        productGroupId = "3",
        title = "Handbag with handle",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-3/1-C2D1B7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/2-C2D1B7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/3-C2D1B7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-3/4-C2D1B7.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#C2D1B7"),
        )
    ),
    STRProductItem(
        productId = "29",
        productGroupId = "4",
        title = "High-heel mini platform boots",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf("https://random-feed-generator.vercel.app/images/clothes/group-4/1-252726.jpg", "https://random-feed-generator.vercel.app/images/clothes/group-4/2-252726.jpg"),
        variants = listOf(
            STRProductVariant(name = "color", value = "#252726"),
            STRProductVariant(name = "size", value = "7,5")
        )
    ),
    STRProductItem(
        productId = "31",
        productGroupId = "4",
        title = "High-heel mini platform boots",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf("https://random-feed-generator.vercel.app/images/clothes/group-4/1-252726.jpg", "https://random-feed-generator.vercel.app/images/clothes/group-4/2-252726.jpg"),
        variants = listOf(
            STRProductVariant(name = "color", value = "#252726"),
            STRProductVariant(name = "size", value = "8,5")
        )
    ),
    STRProductItem(
        productId = "33",
        productGroupId = "4",
        title = "High-heel mini platform boots",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf("https://random-feed-generator.vercel.app/images/clothes/group-4/1-EBE5D5.jpg", "https://random-feed-generator.vercel.app/images/clothes/group-4/2-EBE5D5.jpg"),
        variants = listOf(
            STRProductVariant(name = "color", value = "#EBE5D5"),
            STRProductVariant(name = "size", value = "8")
        )
    ),
    STRProductItem(
        productId = "34",
        productGroupId = "4",
        title = "High-heel mini platform boots",
        url = "https://www.storyly.io/",
        desc = "",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf("https://random-feed-generator.vercel.app/images/clothes/group-4/1-EBE5D5.jpg", "https://random-feed-generator.vercel.app/images/clothes/group-4/2-EBE5D5.jpg"),
        variants = listOf(
            STRProductVariant(name = "color", value = "#EBE5D5"),
            STRProductVariant(name = "size", value = "8,5")
        )
    ),
    STRProductItem(
        productId = "35",
        productGroupId = "5",
        title = "Faux suede shirt",
        url = "https://www.storyly.io/",
        desc = "Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-5/1-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/2-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/3-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/4-8092AA.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#8092AA"),
            STRProductVariant(name = "size", value = "S")
        )
    ),
    STRProductItem(
        productId = "36",
        productGroupId = "5",
        title = "Faux suede shirt",
        url = "https://www.storyly.io/",
        desc = "Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-5/1-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/2-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/3-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/4-8092AA.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#8092AA"),
            STRProductVariant(name = "size", value = "M")
        )
    ),
    STRProductItem(
        productId = "37",
        productGroupId = "5",
        title = "Faux suede shirt",
        url = "https://www.storyly.io/",
        desc = "Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-5/1-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/2-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/3-8092AA.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/4-8092AA.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#8092AA"),
            STRProductVariant(name = "size", value = "L")
        )
    ),
    STRProductItem(
        productId = "38",
        productGroupId = "5",
        title = "Faux suede shirt",
        url = "https://www.storyly.io/",
        desc = "Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-5/1-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/2-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/3-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/4-E6DFD7.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E6DFD7"),
            STRProductVariant(name = "size", value = "S")
        )
    ),
    STRProductItem(
        productId = "39",
        productGroupId = "5",
        title = "Faux suede shirt",
        url = "https://www.storyly.io/",
        desc = "Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-5/1-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/2-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/3-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/4-E6DFD7.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E6DFD7"),
            STRProductVariant(name = "size", value = "M")
        )
    ),
    STRProductItem(
        productId = "40",
        productGroupId = "5",
        title = "Faux suede shirt",
        url = "https://www.storyly.io/",
        desc = "Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-5/1-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/2-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/3-E6DFD7.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-5/4-E6DFD7.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E6DFD7"),
            STRProductVariant(name = "size", value = "L")
        )
    ),
    STRProductItem(
        productId = "41",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "34")
        )
    ),
    STRProductItem(
        productId = "42",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "43",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "44",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "45",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "42")
        )
    ),
    STRProductItem(
        productId = "46",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "44")
        )
    ),
    STRProductItem(
        productId = "47",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#415A7E"),
            STRProductVariant(name = "size", value = "46")
        )
    ),
    STRProductItem(
        productId = "48",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "34")
        )
    ),
    STRProductItem(
        productId = "49",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "50",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "51",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "52",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "42")
        )
    ),
    STRProductItem(
        productId = "53",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "44")
        )
    ),
    STRProductItem(
        productId = "54",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#E3E7E8"),
            STRProductVariant(name = "size", value = "46")
        )
    ),
    STRProductItem(
        productId = "55",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "34")
        )
    ),
    STRProductItem(
        productId = "56",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "57",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "58",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "59",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "42")
        )
    ),
    STRProductItem(
        productId = "60",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "44")
        )
    ),
    STRProductItem(
        productId = "61",
        productGroupId = "6",
        title = "Super skinny jeans",
        url = "https://www.storyly.io/",
        desc = "The super-stretchy denim with slim tapered ankle.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#7B7E83"),
            STRProductVariant(name = "size", value = "46")
        )
    ),
    STRProductItem(
        productId = "62",
        productGroupId = "7",
        title = "Faded-effect denim corset top",
        url = "https://www.storyly.io/",
        desc = "Straight-neck top with thin black straps.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "Small")
        )
    ),
    STRProductItem(
        productId = "63",
        productGroupId = "7",
        title = "Faded-effect denim corset top",
        url = "https://www.storyly.io/",
        desc = "Straight-neck top with thin black straps.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "Medium")
        )
    ),
    STRProductItem(
        productId = "64",
        productGroupId = "7",
        title = "Faded-effect denim corset top",
        url = "https://www.storyly.io/",
        desc = "Straight-neck top with thin black straps.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "Large")
        )
    ),
    STRProductItem(
        productId = "65",
        productGroupId = "7",
        title = "Faded-effect denim corset top",
        url = "https://www.storyly.io/",
        desc = "Straight-neck top with thin black straps.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "X-Large")
        )
    ),
    STRProductItem(
        productId = "66",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFC0CB"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "67",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFC0CB"),
            STRProductVariant(name = "size", value = "37")
        )
    ),
    STRProductItem(
        productId = "68",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFC0CB"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "69",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFC0CB"),
            STRProductVariant(name = "size", value = "39")
        )
    ),
    STRProductItem(
        productId = "70",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFC0CB"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "71",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#C2B280"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "72",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#C2B280"),
            STRProductVariant(name = "size", value = "37")
        )
    ),
    STRProductItem(
        productId = "73",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#C2B280"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "74",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#C2B280"),
            STRProductVariant(name = "size", value = "39")
        )
    ),
    STRProductItem(
        productId = "75",
        productGroupId = "8",
        title = "Heeled slingback strap shoes",
        url = "https://www.storyly.io/",
        desc = "Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#C2B280"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "76",
        productGroupId = "9",
        title = "Striped sweatshirt with zip",
        url = "https://www.storyly.io/",
        desc = "An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "XS")
        )
    ),
    STRProductItem(
        productId = "77",
        productGroupId = "9",
        title = "Striped sweatshirt with zip",
        url = "https://www.storyly.io/",
        desc = "An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "S")
        )
    ),
    STRProductItem(
        productId = "78",
        productGroupId = "9",
        title = "Striped sweatshirt with zip",
        url = "https://www.storyly.io/",
        desc = "An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "M")
        )
    ),
    STRProductItem(
        productId = "79",
        productGroupId = "9",
        title = "Striped sweatshirt with zip",
        url = "https://www.storyly.io/",
        desc = "An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "L")
        )
    ),
    STRProductItem(
        productId = "80",
        productGroupId = "9",
        title = "Striped sweatshirt with zip",
        url = "https://www.storyly.io/",
        desc = "An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "XL")
        )
    ),
    STRProductItem(
        productId = "81",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFFDD0"),
            STRProductVariant(name = "size", value = "34")
        )
    ),
    STRProductItem(
        productId = "82",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFFDD0"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "83",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFFDD0"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "84",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFFDD0"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "85",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#FFFDD0"),
            STRProductVariant(name = "size", value = "42")
        )
    ),
    STRProductItem(
        productId = "86",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#808080"),
            STRProductVariant(name = "size", value = "34")
        )
    ),
    STRProductItem(
        productId = "87",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#808080"),
            STRProductVariant(name = "size", value = "36")
        )
    ),
    STRProductItem(
        productId = "88",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#808080"),
            STRProductVariant(name = "size", value = "38")
        )
    ),
    STRProductItem(
        productId = "89",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#808080"),
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "90",
        productGroupId = "10",
        title = "Wide-leg cargo jeans",
        url = "https://www.storyly.io/",
        desc = "Made from super soft stretch denim with a high rise waist and utility pocket styling.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "color", value = "#808080"),
            STRProductVariant(name = "size", value = "42")
        )
    ),
    STRProductItem(
        productId = "91",
        productGroupId = "11",
        title = "Men's basic sneakers",
        url = "https://www.storyly.io/",
        desc = "White basic trainers. Tag detail on the laces.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-11/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-11/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-11/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "40")
        )
    ),
    STRProductItem(
        productId = "92",
        productGroupId = "11",
        title = "Men's basic sneakers",
        url = "https://www.storyly.io/",
        desc = "White basic trainers. Tag detail on the laces.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-11/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-11/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-11/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "41")
        )
    ),
    STRProductItem(
        productId = "93",
        productGroupId = "11",
        title = "Men's basic sneakers",
        url = "https://www.storyly.io/",
        desc = "White basic trainers. Tag detail on the laces.",
        price = 25.99f, salesPrice = 21.99f,
        currency = "USD",
        imageUrls = listOf(
            "https://random-feed-generator.vercel.app/images/clothes/group-11/1.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-11/2.jpg",
            "https://random-feed-generator.vercel.app/images/clothes/group-11/3.jpg"
        ),
        variants = listOf(
            STRProductVariant(name = "size", value = "42")
        )
    ),
)