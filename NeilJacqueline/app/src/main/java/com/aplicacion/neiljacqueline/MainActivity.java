package com.aplicacion.neiljacqueline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.button_pulse);
        Button inicio = findViewById(R.id.startButton);
        inicio.startAnimation(pulseAnimation);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicio.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        inicio.animate().scaleX(1f).scaleY(1f).setDuration(100);
                    }
                });
                Intent intent = new Intent(MainActivity.this, love.class);
                startActivity(intent);
            }
        });
        Button gal = findViewById(R.id.galeriaButton);
        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gal.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        gal.animate().scaleX(1f).scaleY(1f).setDuration(100);
                    }
                });
                Intent intent = new Intent(MainActivity.this, galery.class);
                startActivity(intent);
            }
        });
        Button his = findViewById(R.id.historiaButton);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                his.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        his.animate().scaleX(1f).scaleY(1f).setDuration(100);
                    }
                });
                Intent intent = new Intent(MainActivity.this, history.class);
                startActivity(intent);
            }
        });

    }
}