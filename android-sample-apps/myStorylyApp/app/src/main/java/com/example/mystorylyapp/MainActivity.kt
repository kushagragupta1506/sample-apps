package com.example.mystorylyapp

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appsamurai.storyly.*
import com.appsamurai.storyly.analytics.StorylyEvent
import com.appsamurai.storyly.data.managers.product.STRCart
import com.appsamurai.storyly.data.managers.product.STRCartEventResult
import com.appsamurai.storyly.data.managers.product.STRProductItem
import com.appsamurai.storyly.data.managers.product.STRProductVariant
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
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NDYsImluc19pZCI6MTI1ODJ9.k7IVUbx4b23WTobh7u-ZIAYMdjN1xIDyA8z5WWncWbU",
            segmentation = StorylySegmentation(setOf("how-to", "introduction", "discover", "newuser")),
            userData = userPropertiesData,
            customParameter = "Gold Member",
        )



        storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(
            //typeface = Typeface.MONOSPACE
        ))

        storylyView.setStoryGroupSize(StoryGroupSize.Small)
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

        //Storyly Product Listener
        storylyView.storylyProductListener = object : StorylyProductListener {

            override fun storylyAddToCartEvent(
                storylyView: StorylyView,
                product: STRProductItem?,
                extras: Map<String, String>,
                onSuccess: ((STRCart) -> Unit)?,
                onFail: ((STRCartEventResult) -> Unit)?
            ) {
                onSuccess?.invoke(
                    STRCart(items = listOf(), totalPrice = 0f, oldTotalPrice = null, currency = "10$")
                )
                onFail?.invoke(STRCartEventResult("AddToCard Failed"))
            }

            override fun storylyEvent(storylyView: StorylyView, event: StorylyEvent) {
                when (event){
                    StorylyEvent.StoryGoToCartClicked -> {
                        Log.d("Shopping", "StoryGoToCartClicked")
                        //storylyView.dismiss()
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

            //Storyly Hydration method
            override fun storylyHydration(storylyView: StorylyView, productIds: List<String>) {
                Log.d("Shopping", "storylyHydration ${productIds}")

            }
        }

        // Storyly Hydrate Products
        storylyView.hydrateProducts(products)

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