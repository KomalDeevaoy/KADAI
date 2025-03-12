package com.example.kadai

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the LinearLayout from the layout file
        val linearLayout = findViewById<LinearLayout>(R.id.main)

        // Create a new ScrollView
        val scrollView = ScrollView(this)

        // Set layout parameters for the ScrollView to match the parent
        val scrollViewLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        scrollView.layoutParams = scrollViewLayoutParams

        // Remove the LinearLayout from its parent (the original root)
        val parent = linearLayout.parent as ViewGroup
        parent.removeView(linearLayout)

        // Add the LinearLayout to the ScrollView
        scrollView.addView(linearLayout)

        // Add the ScrollView to the original parent
        parent.addView(scrollView)

        val eTPrompt = findViewById<EditText>(R.id.eTPrompt)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tVResult = findViewById<TextView>(R.id.tVResult)

        btnSubmit.setOnClickListener {
            val prompt = eTPrompt.text.toString()
            val generativeModel = GenerativeModel(
                modelName = "gemini-1.5-pro-latest",
                apiKey = "AIzaSyC9ivHGmIBXFIt7pSjIgaSywft6YLj-a9M" // Replace with your actual API key
            )
            CoroutineScope(Dispatchers.Main).launch {
                val response = generativeModel.generateContent(prompt)
                tVResult.text = response.text
            }
        }
    }
}