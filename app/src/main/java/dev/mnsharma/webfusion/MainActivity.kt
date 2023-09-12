package dev.mnsharma.webfusion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var loadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.editTextUrl)
        loadButton = findViewById(R.id.buttonLoad)

        loadButton.setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isNotEmpty()) {
                // Start the HomeActivity and pass the URL as an extra
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
            }
        }
    }
}