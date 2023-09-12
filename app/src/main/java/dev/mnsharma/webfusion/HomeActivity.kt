package dev.mnsharma.webfusion

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var webView : WebView
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        webView = findViewById(R.id.webView)

        // Initialize the ProgressBar
        progressBar = findViewById(R.id.PBLoading)
        progressBar.progressTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this,
                R.color.blurple
            )
        )
        progressBar.max = 100 // Set the maximum value for the progress

        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progressBar.progress = newProgress
                if (newProgress == 100) {
                    progressBar.visibility = ProgressBar.GONE
                } else {
                    progressBar.visibility = ProgressBar.VISIBLE
                }
            }
        }

        webView.webViewClient = WebViewClient()

        // Enable JavaScript (optional)
        webView.settings.javaScriptEnabled = true

        // Retrieve the URL passed from the main activity
        val url = intent.getStringExtra("url")

        // Check if the URL starts with "https://" and load the URL into the WebView.
        webView.webViewClient = WebViewClient()
        if (url != null) {
            if (url.startsWith("https://")) {
                // Load the website using HTTPS.
                webView.loadUrl(url)
            } else {
                // Load the website using HTTP.
                webView.loadUrl("https://$url")
            }
        }
    }
}