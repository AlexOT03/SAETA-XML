package com.example.saeta

import TuristFragment
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class Detail_Place_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_place)

        // Configurar el toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de navegación
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Configurar el listener para la flecha de navegación
        toolbar.setNavigationOnClickListener {
            finish()  // Termina la actividad y regresa a la anterior
        }

        //widgets
        val detail_title : TextView = findViewById(R.id.detail_title)
        val detail_description : TextView = findViewById(R.id.detail_description)
        val detail_image : ImageView = findViewById(R.id.detail_image)


        // Recibir los valores pasados a través del Intent
        val titulo = intent.getStringExtra("titulo")
        val descripcion = intent.getStringExtra("descripcion")
        val imagen = intent.getIntExtra("imagen", R.drawable.ic_home)

        //Setear lo recibido
        detail_title.text = titulo
        detail_description.text = descripcion
        detail_image.setImageResource(imagen)


    }
    // En Detail_Place_Activity
    override fun onBackPressed() {

        val fragmentOrigen = intent.getStringExtra("fragment")
        when (fragmentOrigen) {
            "Detail_Place_Activity" -> {
                val fragment = TuristFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
            // Otros fragmentos
            else -> super.onBackPressed()
        }
    }

}