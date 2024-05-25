package com.example.saeta

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed)

        val ruta = intent.getParcelableExtra<LanguageData>("ruta")
        if (ruta !=null){
            val textView : TextView = findViewById(R.id.detailedActivityTv)
            val imageView : ImageView = findViewById(R.id.detailedActivityIv)

            textView.text = ruta.title
            imageView.setImageResource(ruta.logo)
        } else {
            Toast.makeText(this, "Error: No se pudo obtener el objeto LanguageData del intent", Toast.LENGTH_SHORT).show()
        }
    }
}