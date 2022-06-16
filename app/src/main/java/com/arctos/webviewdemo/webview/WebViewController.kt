package com.arctos.webviewdemo.webview

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull

@SuppressLint("SetJavaScriptEnabled")
class WebViewController(@NonNull private val webView: WebView) {

    init {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.databaseEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
    }

    @SuppressLint("JavascriptInterface")
    fun addJavascriptInterface(obj: Any, interface_name: String) {
        webView.addJavascriptInterface(obj, interface_name)
    }

    fun loadURL(url: String) {
        webView.loadUrl(url)
    }

    fun setWebChromeClient() {
        val webChromeClient = WebChromeClient()
        webView.webChromeClient = webChromeClient
    }

    fun setWebViewClient() {
        val webViewClient = WebViewClient()
        webView.webViewClient = webViewClient
    }
}