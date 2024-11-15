package com.aplicacion.preguntasbolivia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartGame: Button = findViewById(R.id.btnStartGame)
        val btnProgress: Button = findViewById(R.id.btnProgress)
        val btnCredits: Button = findViewById(R.id.btnCredits)
        val btnExit: Button = findViewById(R.id.btnExit)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnStartGame.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
        }

        btnProgress.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }

        btnCredits.setOnClickListener {
            val intent = Intent(this, CreditsActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finish()
        }
    }
}
