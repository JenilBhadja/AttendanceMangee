package com.example.attendancemangee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var webSettings : WebSettings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        webView = findViewById(R.id.webview)

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true


        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url == "") {
                    view.loadUrl("https://attendancemangee.azurewebsites.net/Identity/Account/Login?ReturnUrl=%2F")
                } else if (url == "https://attendancemangee.azurewebsites.net/") {
                    view.loadUrl("https://attendancemangee.azurewebsites.net/Identity/Account/Login?ReturnUrl=%2F")
                } else {
                    view.loadUrl(url)
                }
                return true
            }
        }
        webView.loadUrl("https://attendancemangee.azurewebsites.net/Identity/Account/Login?ReturnUrl=%2F")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}