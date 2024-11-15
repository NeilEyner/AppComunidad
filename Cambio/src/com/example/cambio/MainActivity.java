package com.example.cambio;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

public class MainActivity extends Activity {

    private EditText inputBs;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        inputBs = (EditText) findViewById(R.id.editText1);
        resultText = (TextView) findViewById(R.id.TextView01);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);

        // Configurar los listeners para los botones
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency("USD");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency("EUR");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency("JPY");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency("MXN");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency("BRL");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency("KRW");
            }
        });
    }

    private void convertCurrency(String currency) {
        String input = inputBs.getText().toString();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "Por favor ingrese una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }

        double amountBs = Double.parseDouble(input);
        double result = 0;

      
        if ("USD".equals(currency)) {
            double usdExchangeRate = 0.15;
            result = amountBs * usdExchangeRate;
            resultText.setText(String.format("%.2f USD", result));
        } else if ("EUR".equals(currency)) {
            double eurExchangeRate = 0.13;
            result = amountBs * eurExchangeRate;
            resultText.setText(String.format("%.2f EUR", result));
        } else if ("JPY".equals(currency)) {
            double jpyExchangeRate = 0.047;
            result = amountBs * jpyExchangeRate;
            resultText.setText(String.format("%.2f JPY", result));
        } else if ("MXN".equals(currency)) {
            double mxnExchangeRate = 0.37;
            result = amountBs * mxnExchangeRate;
            resultText.setText(String.format("%.2f MXN", result));
        } else if ("BRL".equals(currency)) {
            double brlExchangeRate = 1.26; 
            result = amountBs * brlExchangeRate;
            resultText.setText(String.format("%.2f BRL", result));
        } else if ("KRW".equals(currency)) {
            double krwExchangeRate = 0.0052; 
            result = amountBs * krwExchangeRate;
            resultText.setText(String.format("%.2f KRW", result));
        } else {
            resultText.setText(" No soportado");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
