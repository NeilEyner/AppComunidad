package com.aplicacion.neiljacqueline;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class love extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.love);
        TextView textDays = findViewById(R.id.textDays);

        String startDateStr = "2023-06-13";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            Date startDate = sdf.parse(startDateStr);
            long daysDiff = calculateDaysDifference(startDate, new Date());
            textDays.setText(daysDiff+" Días juntos " );
        } catch (Exception e) {
            e.printStackTrace();
            textDays.setText("Error al calcular la fecha.");
        }


    }
    private long calculateDaysDifference(Date startDate, Date endDate) {
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return diffInMillis / (1000 * 60 * 60 * 24);
    }
}