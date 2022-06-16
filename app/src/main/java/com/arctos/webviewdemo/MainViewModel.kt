package com.arctos.webviewdemo


import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.lifecycle.ViewModel
import com.arctos.webviewdemo.webview.WebViewController
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {
    private lateinit var webViewController: WebViewController
    val jsMessage: MutableStateFlow<String> = MutableStateFlow("")

    fun initWebViewController(web_view: WebView) {
        if (!::webViewController.isInitialized) {
            webViewController = WebViewController(web_view)
            addJavascriptInterface(this, "Android")
            setWebChromeClient()
            setWebViewClient()
        }

    }

    private fun addJavascriptInterface(obj: Any, interface_name: String) {
        if (::webViewController.isInitialized) {
            webViewController.addJavascriptInterface(obj, interface_name)
        }
    }


    private fun setWebChromeClient() {
        if (::webViewController.isInitialized) {
            webViewController.setWebChromeClient()
        }
    }

    private fun setWebViewClient() {
        if (::webViewController.isInitialized) {
            webViewController.setWebViewClient()
        }
    }

    fun loadURL(url: String) {
        if (::webViewController.isInitialized) {
            webViewController.loadURL(url)
        }
    }

    @JavascriptInterface
    fun call(message: String ) {
        jsMessage.value = message
    }
}