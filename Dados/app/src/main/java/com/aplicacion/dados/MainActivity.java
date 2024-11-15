package com.aplicacion.dados;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3, imageView4;
    private Button buttonSortear1, buttonSortear2, buttonReiniciar;
    private TextView textViewStatus;
    private Random random;
    private int firstDiceResult, secondDiceResult;
    private boolean isStageOneCompleted;
    private MediaPlayer diceSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        textViewStatus = findViewById(R.id.textViewStatus);

        buttonSortear1 = findViewById(R.id.button);
        buttonSortear2 = findViewById(R.id.button2);
        buttonReiniciar = findViewById(R.id.button3);

        random = new Random();
        diceSound = MediaPlayer.create(this, R.raw.dice_sound);

        buttonSortear1.setOnClickListener(v -> rollFirstStage());
        buttonSortear2.setOnClickListener(v -> rollSecondStage());
        buttonReiniciar.setOnClickListener(v -> resetGame());

        resetGame();
    }

    private void rollFirstStage() {
        playDiceSound();
        buttonSortear1.setEnabled(false);
        textViewStatus.setText("Lanzando dados...");

        new Handler().postDelayed(() -> {
            firstDiceResult = random.nextInt(6) + 1;
            secondDiceResult = random.nextInt(6) + 1;

            updateDiceImages(imageView1, firstDiceResult);
            updateDiceImages(imageView2, secondDiceResult);

            int sum = firstDiceResult + secondDiceResult;
            if ((firstDiceResult == 1 && secondDiceResult == 1)) {
                textViewStatus.setText("¡Perdiste! doble 1.");
                buttonSortear1.setBackgroundColor(Color.parseColor("#B0B0B0"));
                disableButtons();
            }
            else {if (sum == 7 ) {
                textViewStatus.setText("¡GANASTE! SACASTE 7 ");
                buttonSortear1.setBackgroundColor(Color.parseColor("#B0B0B0"));
                buttonSortear2.setBackgroundColor(Color.parseColor("#B0B0B0"));
                disableButtons();
            } else {
                textViewStatus.setText("Primera etapa completada. Lanza los siguientes dados.");
                buttonSortear1.setBackgroundColor(Color.parseColor("#B0B0B0"));
                isStageOneCompleted = true;
                buttonSortear2.setEnabled(true);
            }}
        }, 1000);
    }

    private void rollSecondStage() {
        if (!isStageOneCompleted) return;
        playDiceSound();
        buttonSortear2.setEnabled(false);
        textViewStatus.setText("Lanzando dados...");

        new Handler().postDelayed(() -> {
            int thirdDiceResult = random.nextInt(6) + 1;
            int fourthDiceResult = random.nextInt(6) + 1;

            updateDiceImages1(imageView3, thirdDiceResult);
            updateDiceImages1(imageView4, fourthDiceResult);

            int sum = thirdDiceResult + fourthDiceResult;
            if (sum == 7) {
                textViewStatus.setText("¡Perdiste! Salió 7.");
                buttonSortear2.setBackgroundColor(Color.parseColor("#B0B0B0"));
                disableButtons();
            } else if (sum == firstDiceResult + secondDiceResult) {
                textViewStatus.setText("¡Ganaste! Igualaste la jugada.");
                buttonSortear2.setBackgroundColor(Color.parseColor("#B0B0B0"));
                disableButtons();
            } else {
                textViewStatus.setText("Intenta de nuevo.");
                buttonSortear2.setEnabled(true);
            }
        }, 1000);
    }

    private void resetGame() {
        buttonSortear1.setEnabled(true);
        buttonSortear2.setEnabled(false);
        textViewStatus.setText("Presiona SORTEAR para comenzar.");
        imageView1.setImageResource(R.drawable.dado1);
        imageView2.setImageResource(R.drawable.dado1);
        imageView3.setImageResource(R.drawable.dadob1);
        imageView4.setImageResource(R.drawable.dadob1);
        isStageOneCompleted = false;
        buttonSortear1.setBackgroundColor(Color.parseColor("#1976D2"));
        buttonSortear2.setBackgroundColor(Color.parseColor("#1976D2"));
    }

    private void disableButtons() {
        buttonSortear1.setEnabled(false);
        buttonSortear2.setEnabled(false);
    }

    private void updateDiceImages(ImageView imageView, int diceResult) {
        switch (diceResult) {
            case 1:
                imageView.setImageResource(R.drawable.dado1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dado2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dado3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dado4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dado5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dado6);
                break;
        }
    }
    private void updateDiceImages1(ImageView imageView, int diceResult) {
        switch (diceResult) {
            case 1:
                imageView.setImageResource(R.drawable.dadob1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dadob2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dadob3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dadob4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dadob5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dadob6);
                break;
        }
    }

    private void playDiceSound() {
        if (diceSound != null) {
            diceSound.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (diceSound != null) {
            diceSound.release();
            diceSound = null;
        }
    }
}
