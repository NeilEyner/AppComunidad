package com.aplicacion.poker

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val cardIds = listOf(
        // Lista de IDs de recursos de cartas.
        // Tréboles (Clubs)
        R.drawable.clubs_1_,
        R.drawable.clubs_2_,
        R.drawable.clubs_3_,
        R.drawable.clubs_4_,
        R.drawable.clubs_5_,
        R.drawable.clubs_6_,
        R.drawable.clubs_7_,
        R.drawable.clubs_8_,
        R.drawable.clubs_9_,
        R.drawable.clubs_10_,
        R.drawable.clubs_11_,
        R.drawable.clubs_12_,
        R.drawable.clubs_13_,

        // Corazones (Hearts)
        R.drawable.hearts_1_,
        R.drawable.hearts_2_,
        R.drawable.hearts_3_,
        R.drawable.hearts_4_,
        R.drawable.hearts_5_,
        R.drawable.hearts_6_,
        R.drawable.hearts_7_,
        R.drawable.hearts_8_,
        R.drawable.hearts_9_,
        R.drawable.hearts_10_,
        R.drawable.hearts_11_,
        R.drawable.hearts_12_,
        R.drawable.hearts_13_,

        // Diamantes (Diamonds)
        R.drawable.diamonds_1_,
        R.drawable.diamonds_2_,
        R.drawable.diamonds_3_,
        R.drawable.diamonds_4_,
        R.drawable.diamonds_5_,
        R.drawable.diamonds_6_,
        R.drawable.diamonds_7_,
        R.drawable.diamonds_8_,
        R.drawable.diamonds_9_,
        R.drawable.diamonds_10_,
        R.drawable.diamonds_11_,
        R.drawable.diamonds_12_,
        R.drawable.diamonds_13_,

        // Picas (Spades)
        R.drawable.spades_1_,
        R.drawable.spades_2_,
        R.drawable.spades_3_,
        R.drawable.spades_4_,
        R.drawable.spades_5_,
        R.drawable.spades_6_,
        R.drawable.spades_7_,
        R.drawable.spades_8_,
        R.drawable.spades_9_,
        R.drawable.spades_10_,
        R.drawable.spades_11_,
        R.drawable.spades_12_,
        R.drawable.spades_13_,

        // Comodines (Jokers)
        R.drawable.red_joker,
        R.drawable.black_joker
    )

    private lateinit var cardViews: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configurar la vista para ajustar los márgenes de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener las vistas de las cartas
        cardViews = listOf(
            findViewById(R.id.card1),
            findViewById(R.id.card2),
            findViewById(R.id.card3),
            findViewById(R.id.card4),
            findViewById(R.id.card5)
        )

        // Configurar el botón de barajar
        val shuffleButton: Button = findViewById(R.id.shuffleButton)
        shuffleButton.setOnClickListener {
            shuffleCards()
            val bestHand = checkBestHand()
            if(bestHand != "NONE"){
                showPokerHandDialog(bestHand)
            }
//            Toast.makeText(this, " $bestHand", Toast.LENGTH_SHORT).show()

        }

        // Barajar cartas al inicio
        shuffleCards()
    }

    private fun shuffleCards() {
        val shuffledCards = cardIds.shuffled().take(5)
        cardViews.forEachIndexed { index, imageView ->
            imageView.setImageResource(shuffledCards[index])
            imageView.tag = shuffledCards[index] // Guarda el ID del recurso como un tag
        }
    }

    private fun checkBestHand(): String {
        val cardValueMap = mapOf(
            // Tréboles (Clubs)
            R.drawable.clubs_1_ to Pair(1, "clubs"),
            R.drawable.clubs_2_ to Pair(2, "clubs"),
            R.drawable.clubs_3_ to Pair(3, "clubs"),
            R.drawable.clubs_4_ to Pair(4, "clubs"),
            R.drawable.clubs_5_ to Pair(5, "clubs"),
            R.drawable.clubs_6_ to Pair(6, "clubs"),
            R.drawable.clubs_7_ to Pair(7, "clubs"),
            R.drawable.clubs_8_ to Pair(8, "clubs"),
            R.drawable.clubs_9_ to Pair(9, "clubs"),
            R.drawable.clubs_10_ to Pair(10, "clubs"),
            R.drawable.clubs_11_ to Pair(11, "clubs"),
            R.drawable.clubs_12_ to Pair(12, "clubs"),
            R.drawable.clubs_13_ to Pair(13, "clubs"),

            // Corazones (Hearts)
            R.drawable.hearts_1_ to Pair(1, "hearts"),
            R.drawable.hearts_2_ to Pair(2, "hearts"),
            R.drawable.hearts_3_ to Pair(3, "hearts"),
            R.drawable.hearts_4_ to Pair(4, "hearts"),
            R.drawable.hearts_5_ to Pair(5, "hearts"),
            R.drawable.hearts_6_ to Pair(6, "hearts"),
            R.drawable.hearts_7_ to Pair(7, "hearts"),
            R.drawable.hearts_8_ to Pair(8, "hearts"),
            R.drawable.hearts_9_ to Pair(9, "hearts"),
            R.drawable.hearts_10_ to Pair(10, "hearts"),
            R.drawable.hearts_11_ to Pair(11, "hearts"),
            R.drawable.hearts_12_ to Pair(12, "hearts"),
            R.drawable.hearts_13_ to Pair(13, "hearts"),

            // Diamantes (Diamonds)
            R.drawable.diamonds_1_ to Pair(1, "diamonds"),
            R.drawable.diamonds_2_ to Pair(2, "diamonds"),
            R.drawable.diamonds_3_ to Pair(3, "diamonds"),
            R.drawable.diamonds_4_ to Pair(4, "diamonds"),
            R.drawable.diamonds_5_ to Pair(5, "diamonds"),
            R.drawable.diamonds_6_ to Pair(6, "diamonds"),
            R.drawable.diamonds_7_ to Pair(7, "diamonds"),
            R.drawable.diamonds_8_ to Pair(8, "diamonds"),
            R.drawable.diamonds_9_ to Pair(9, "diamonds"),
            R.drawable.diamonds_10_ to Pair(10, "diamonds"),
            R.drawable.diamonds_11_ to Pair(11, "diamonds"),
            R.drawable.diamonds_12_ to Pair(12, "diamonds"),
            R.drawable.diamonds_13_ to Pair(13, "diamonds"),

            // Picas (Spades)
            R.drawable.spades_1_ to Pair(1, "spades"),
            R.drawable.spades_2_ to Pair(2, "spades"),
            R.drawable.spades_3_ to Pair(3, "spades"),
            R.drawable.spades_4_ to Pair(4, "spades"),
            R.drawable.spades_5_ to Pair(5, "spades"),
            R.drawable.spades_6_ to Pair(6, "spades"),
            R.drawable.spades_7_ to Pair(7, "spades"),
            R.drawable.spades_8_ to Pair(8, "spades"),
            R.drawable.spades_9_ to Pair(9, "spades"),
            R.drawable.spades_10_ to Pair(10, "spades"),
            R.drawable.spades_11_ to Pair(11, "spades"),
            R.drawable.spades_12_ to Pair(12, "spades"),
            R.drawable.spades_13_ to Pair(13, "spades"),

            // Comodines (Jokers)
            R.drawable.red_joker to Pair(0, "joker"),
            R.drawable.black_joker to Pair(0, "joker")
        )

        // Obtener los valores y palos de las cartas actuales
        val currentHandValues = cardViews.mapNotNull { cardView ->
            val drawableId = cardView.tag as? Int
            cardValueMap[drawableId]?.first
        }

        val currentHandSuits = cardViews.mapNotNull { cardView ->
            val drawableId = cardView.tag as? Int
            cardValueMap[drawableId]?.second
        }

        val bestHand = when {
            isFiveOfAKind(currentHandValues) -> "GANASTE"
            isStraightFlush(currentHandValues, currentHandSuits) -> "ESCALERA"
            isFourOfAKind(currentHandValues) -> "POKER"
            isFullHouse(currentHandValues) -> "FULL"
            isFlush(currentHandSuits) -> "COLOR"
            isStraight(currentHandValues) -> "ESCALERA"
            isThreeOfAKind(currentHandValues) -> "TRIO"
            isTwoPair(currentHandValues) -> "DOBLE PAREJA"
            isOnePair(currentHandValues) -> "PAREJA"
            else -> "NONE"
        }

        return bestHand
    }


    private fun isFiveOfAKind(values: List<Int>): Boolean {
        return values.distinct().size == 1 && values.first() != 0
    }

    private fun isFourOfAKind(values: List<Int>): Boolean {
        return values.groupingBy { it }.eachCount().containsValue(4)
    }

    private fun isFullHouse(values: List<Int>): Boolean {
        val valueCounts = values.groupingBy { it }.eachCount()
        return valueCounts.containsValue(3) && valueCounts.containsValue(2)
    }

    private fun isFlush(suits: List<String>): Boolean {
        return suits.distinct().size == 1
    }

    private fun isStraight(values: List<Int>): Boolean {
        val sortedValues = values.sorted()
        return sortedValues.zipWithNext().all { (a, b) -> b == a + 1 }
    }

    private fun isStraightFlush(values: List<Int>, suits: List<String>): Boolean {
        return isStraight(values) && isFlush(suits)
    }

    private fun isThreeOfAKind(values: List<Int>): Boolean {
        return values.groupingBy { it }.eachCount().containsValue(3)
    }

    private fun isTwoPair(values: List<Int>): Boolean {
        val valueCounts = values.groupingBy { it }.eachCount()
        return valueCounts.values.count { it == 2 } == 2
    }

    private fun isOnePair(values: List<Int>): Boolean {
        return values.groupingBy { it }.eachCount().containsValue(2)
    }

    private fun showPokerHandDialog(handType: String) {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
//        builder.setTitle("POKER")
        builder.setMessage(handType)
//        builder.setPositiveButton("CERRAR") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }


}
