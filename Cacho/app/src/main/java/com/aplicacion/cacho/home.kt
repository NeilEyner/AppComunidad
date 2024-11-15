package com.aplicacion.cacho

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val iniciar: Button = findViewById(R.id.button_play)
        iniciar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val salir: Button = findViewById(R.id.button_exit)
        salir.setOnClickListener{
            finish()
        }
    }

}
