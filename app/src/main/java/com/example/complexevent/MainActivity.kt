package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar

    private var progressHandler: Handler = Handler()
    private var progressRunnable: Runnable? = null
    private var progress: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.EditText)
        checkBox = findViewById(R.id.CheckBox)
        textView = findViewById(R.id.TextView)
        progressBar = findViewById(R.id.ProgressBar)

        val button: Button = findViewById(R.id. Button)
        button.setOnClickListener {
            if (checkBox.isChecked) {
                val text = editText.text.toString()
                textView.text = text
                startProgress()
            }
        }
    }

    private fun startProgress() {
        progress = 0
        progressBar.progress = progress
        progressRunnable = object : Runnable {
            override fun run() {
                progress += 10
                progressBar.progress = progress
                if (progress >= 100) {
                    progressHandler.postDelayed(this, 1000)
                    progressHandler.removeCallbacks(this)
                    progress = 0
                    progressBar.progress = progress
                } else {
                    progressHandler.postDelayed(this, 200)
                }
            }
        }
        progressHandler.postDelayed(progressRunnable!!, 1000)
    }
}
