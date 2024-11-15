package com.aplicacion.cacho

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var flipCount = 0 // Contador global para los volteos de dado
    private val maxFlips = 2 // Número máximo de volteos permitidos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonLanzarDados: Button = findViewById(R.id.button)
        val buttonsalir: Button = findViewById(R.id.buttonAtras)
        val textViewCacho: TextView = findViewById(R.id.textView2)
        val textViewJugada: TextView = findViewById(R.id.textView3)
        val buttonReiniciar: Button = findViewById(R.id.button1)
        val imageView1: ImageView = findViewById(R.id.imageView)
        val imageView2: ImageView = findViewById(R.id.imageView2)
        val imageView3: ImageView = findViewById(R.id.imageView3)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val imageView5: ImageView = findViewById(R.id.imageView5)
        val diceImages = arrayOf(imageView1, imageView2, imageView3, imageView4, imageView5)
        var diceValues = IntArray(5)

        buttonLanzarDados.setOnClickListener {
            diceValues = rollDice()
            updateDiceImages(diceValues, diceImages)
            val result = checkResult(diceValues)
            textViewJugada.text = result
            textViewCacho.text = "TUS DADOS"
            inhabilitar()
        }
        buttonReiniciar.setOnClickListener {
            textViewCacho.text = "NUEVA PARTIDA"
            resetGame()
        }
        buttonsalir.setOnClickListener {
            finish()
        }


        diceImages.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                if (flipCount < maxFlips) {
                    diceValues[index] = flipDice(diceValues[index])
                    updateDiceImages(diceValues, diceImages)
                    flipCount++
                    val result = checkResult(diceValues)
                    textViewJugada.text = result
                }
            }
        }
    }

    private fun flipDice(value: Int): Int {
        return when (value) {
            1 -> 6
            2 -> 5
            3 -> 4
            4 -> 3
            5 -> 2
            6 -> 1
            else -> value
        }
    }

    private fun rollDice(): IntArray {
        val diceValues = IntArray(5)
        for (i in 0..4) {
            diceValues[i] = Random.nextInt(1, 7)
        }
        return diceValues
    }

    private fun updateDiceImages(diceValues: IntArray, diceImages: Array<ImageView>) {
        val diceDrawables = arrayOf(
            R.drawable.dado1, R.drawable.dado2, R.drawable.dado3,
            R.drawable.dado4, R.drawable.dado5, R.drawable.dado6
        )
        for (i in diceValues.indices) {
            diceImages[i].setImageResource(diceDrawables[diceValues[i] - 1])
        }
    }

    private fun checkResult(diceValues: IntArray): String {
        val counts = IntArray(7)
        for (value in diceValues) {
            counts[value]++
        }
        return when {
            isEscalera(diceValues) -> "ESCALERA!!"
            counts.contains(5) -> "DORMIDA!!"
            counts.contains(4) -> "POKER!!"
            counts.contains(3) || counts.contains(2) -> "FULL!!!"
            else -> "INTENTALO DE NUEVO!"
        }
    }

    private fun isEscalera(diceValues: IntArray): Boolean {
        val sortedValues = diceValues.sorted()
        return sortedValues == listOf(2, 3, 4, 5, 6) ||
                sortedValues == listOf(1, 2, 3, 4, 5) ||
                sortedValues == listOf(1, 3, 4, 5, 6)
    }

    private fun resetGame() {
        val initialDiceDrawable = R.drawable.dado

        val buttonLanzarDados: Button = findViewById(R.id.button)
        buttonLanzarDados.setBackgroundColor(Color.parseColor("#fabfb7"))
        buttonLanzarDados.setTextColor(Color.parseColor("#004d00"))
        buttonLanzarDados.isEnabled = true

        findViewById<ImageView>(R.id.imageView).setImageResource(initialDiceDrawable)
        findViewById<ImageView>(R.id.imageView2).setImageResource(initialDiceDrawable)
        findViewById<ImageView>(R.id.imageView3).setImageResource(initialDiceDrawable)
        findViewById<ImageView>(R.id.imageView4).setImageResource(initialDiceDrawable)
        findViewById<ImageView>(R.id.imageView5).setImageResource(initialDiceDrawable)
        findViewById<TextView>(R.id.textView2).text = "LANZA LOS DADOS!"
        findViewById<TextView>(R.id.textView3).text = " . . . "

        flipCount = 0
    }

    fun inhabilitar(){
        val buttonLanzarDados: Button = findViewById(R.id.button)
        buttonLanzarDados.setBackgroundColor(Color.parseColor("#c5c6c8"))
        buttonLanzarDados.setTextColor(Color.parseColor("#b2e2f2"))
        buttonLanzarDados.isEnabled = false
    }
}
