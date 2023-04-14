package com.example.nativewebview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.wb_webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://dashboard.storyly.io/preview-web/736f5c7e7451757f597f5c5f436c5f27587f5f655f78442375555f205f7d664e40555c2f38736f5c7e4f2458707741477f597c7127587c43655f7b506175502f664c555f205b425b27587c5b655f7b7a6375272f664c555f205b424725586c542f38595c20663b626657576e7a652f4e4f4366575f79657a5f6e615e576070787d4f794e477b2f2e53546e7d616a4d79747c7375623659747c7375624b")
        webView.webChromeClient = WebChromeClient()
        //webViewSetup()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false

    }

    /*@SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        val wb_webView = findViewById<WebView>(R.id.wb_webView)

        wb_webView.apply {
            //loadUrl("https://google.com")
            //loadUrl("https://dashboard.storyly.io/preview-web/736f5c7e7451757f597f5c5f436c5f27587f5f655f78442375555f205f7d664e40555c2f38736f5c7e4f2458707741477f597c7127587c43655f7b506175502f664c555f205b425b27587c5b655f7b7a6375272f664c555f205b424725586c542f38595c20663b626657576e7a652f4e4f4366575f79657a5f6e615e576070787d4f794e477b2f2e53546e7d616a4d79747c7375623659747c7375624b")
            settings.javaScriptEnabled = true
        }
    }*/

}