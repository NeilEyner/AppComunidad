package com.aplicacion.preguntasbolivia

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CreditsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_credits)
        val btnExit: Button = findViewById(R.id.btnBackToMain)
        btnExit.setOnClickListener {
            finish()
        }

    }

}