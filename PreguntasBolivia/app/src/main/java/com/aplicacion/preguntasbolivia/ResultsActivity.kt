package com.aplicacion.preguntasbolivia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 10)
        val currentLevel = intent.getIntExtra("LEVEL", 1)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvLevel = findViewById<TextView>(R.id.tvLevel)
        val tvLevelUnlocked = findViewById<TextView>(R.id.tvLevelUnlocked)
        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)

        tvScore.text = "Puntaje: $score de $totalQuestions"
        tvLevel.text = "Nivel: ${getLevelName(currentLevel)}"

        // Lógica para desbloquear el siguiente nivel
        val nextLevel = currentLevel + 1
        val levelUnlocked = score >= (totalQuestions * 0.7) // 70% de aciertos para desbloquear

        if (levelUnlocked && nextLevel <= 3) {
            tvLevelUnlocked.text = "¡Felicidades! Has desbloqueado el nivel ${getLevelName(nextLevel)}"
            saveUnlockedLevel(nextLevel)
        } else {
            tvLevelUnlocked.text = "Sigue practicando para desbloquear el siguiente nivel"
        }

        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() // Asegúrate de que ResultsActivity se cierre
        }
    }

    private fun getLevelName(level: Int): String {
        return when (level) {
            1 -> "HISTORIA"
            2 -> "GEOGRAFIA"
            3 -> "CULTURA Y TRADICIONES "
            else -> "Desconocido"
        }
    }

    private fun saveUnlockedLevel(level: Int) {
        val sharedPref = getSharedPreferences("GamePrefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("UnlockedLevel", level)
            apply()
        }
    }

    // Sobrescribir onBackPressed para evitar comportamientos inesperados
    override fun onBackPressed() {
        val intent = Intent(this, LevelsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}