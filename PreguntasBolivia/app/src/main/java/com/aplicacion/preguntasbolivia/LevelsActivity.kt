package com.aplicacion.preguntasbolivia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LevelsActivity : AppCompatActivity() {
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        btn1 = findViewById(R.id.btnLevel1)
        btn2 = findViewById(R.id.btnLevel2)
        btn3 = findViewById(R.id.btnLevel3)

        val unlockedLevel = getUnlockedLevel()

        btn1.setOnClickListener {
            startGame(1)
        }

        btn2.setOnClickListener {
            if (unlockedLevel >= 2) {
                startGame(2)
            } else {
                // Mostrar un mensaje de que el nivel está bloqueado
            }
        }

        btn3.setOnClickListener {
            if (unlockedLevel >= 3) {
                startGame(3)
            } else {
                // Mostrar un mensaje de que el nivel está bloqueado
            }
        }

        // Actualizar la apariencia de los botones según el nivel desbloqueado
        updateButtonAppearance(unlockedLevel)
    }

    private fun startGame(level: Int) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("LEVEL", level)
        startActivity(intent)
    }

    private fun getUnlockedLevel(): Int {
        val sharedPref = getSharedPreferences("GamePrefs", MODE_PRIVATE)
        return sharedPref.getInt("UnlockedLevel", 1)
    }

    private fun updateButtonAppearance(unlockedLevel: Int) {
        btn1.isEnabled = true
        btn2.isEnabled = unlockedLevel >= 2
        btn3.isEnabled = unlockedLevel >= 3

        // Aquí puedes cambiar la apariencia de los botones según si están habilitados o no
    }
}