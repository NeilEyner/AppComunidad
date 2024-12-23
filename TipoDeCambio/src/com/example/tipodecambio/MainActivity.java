package com.example.tipodecambio;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText editTextBolivianos;
    private EditText editTextDolares;
    private EditText editTextEuros;
    private EditText editTextYenes;
    private EditText editTextSoles;
    private EditText editTextReales;
    private EditText editTextPesos;
    private EditText editTextPesosChilenos;
    private Button buttonConvert;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        

        // Inicializar los campos de texto
        editTextBolivianos = (EditText) findViewById(R.id.editTextBolivianos);
        editTextDolares =(EditText) findViewById(R.id.editTextDolares);
        editTextEuros =(EditText) findViewById(R.id.editTextEuros);
        editTextYenes =(EditText) findViewById(R.id.editTextYenes);
        editTextSoles =(EditText) findViewById(R.id.editTextSoles);
        editTextReales =(EditText) findViewById(R.id.editTextReales);
        editTextPesos =(EditText) findViewById(R.id.editTextPesos);
        editTextPesosChilenos = (EditText)findViewById(R.id.editTextPesosChilenos);

        // Inicializar botones
        buttonConvert = (Button) findViewById(R.id.buttonConvert);
        buttonClear = (Button) findViewById(R.id.buttonClear);

        // Configurar el bot�n de convertir
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });

        // Configurar el bot�n de limpiar
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void convertCurrency() {
        // Obtener los valores de los campos
        String bolivianosStr = editTextBolivianos.getText().toString();
        String dolaresStr = editTextDolares.getText().toString();
        String eurosStr = editTextEuros.getText().toString();
        String yenesStr = editTextYenes.getText().toString();
        String solesStr = editTextSoles.getText().toString();
        String realesStr = editTextReales.getText().toString();
        String pesosStr = editTextPesos.getText().toString();
        String pesosChilenosStr = editTextPesosChilenos.getText().toString();

        // Tasas de conversi�n
        double tasaDolar = 0.14;  // Ejemplo: 1 Boliviano = 0.14 D�lares
        double tasaEuro = 0.13;   // Ejemplo: 1 Boliviano = 0.13 Euros
        double tasaYen = 15.34;   // Ejemplo: 1 Boliviano = 15.34 Yenes
        double tasaSol = 0.39;    // Ejemplo: 1 Boliviano = 0.39 Soles
        double tasaReal = 0.68;   // Ejemplo: 1 Boliviano = 0.68 Reales
        double tasaPesoMexicano = 2.35;  // Ejemplo: 1 Boliviano = 2.35 Pesos Mexicanos
        double tasaPesoChileno = 113.92; // Ejemplo: 1 Boliviano = 113.92 Pesos Chilenos

        double bolivianos = 0;

        // Determinar cu�l campo tiene un valor y hacer la conversi�n
        if (bolivianosStr != null && bolivianosStr.length() > 0) {
            bolivianos = Double.parseDouble(bolivianosStr);
        } else if (dolaresStr != null && dolaresStr.length() > 0) {
            bolivianos = Double.parseDouble(dolaresStr) / tasaDolar;
        } else if (eurosStr != null && eurosStr.length() > 0) {
            bolivianos = Double.parseDouble(eurosStr) / tasaEuro;
        } else if (yenesStr != null && yenesStr.length() > 0) {
            bolivianos = Double.parseDouble(yenesStr) / tasaYen;
        } else if (solesStr != null && solesStr.length() > 0) {
            bolivianos = Double.parseDouble(solesStr) / tasaSol;
        } else if (realesStr != null && realesStr.length() > 0) {
            bolivianos = Double.parseDouble(realesStr) / tasaReal;
        } else if (pesosStr != null && pesosStr.length() > 0) {
            bolivianos = Double.parseDouble(pesosStr) / tasaPesoMexicano;
        } else if (pesosChilenosStr != null && pesosChilenosStr.length() > 0) {
            bolivianos = Double.parseDouble(pesosChilenosStr) / tasaPesoChileno;
        }

        // Si se ha determinado el valor en Bolivianos, realizar las conversiones
        if (bolivianos > 0) {
            double dolares = bolivianos * tasaDolar;
            double euros = bolivianos * tasaEuro;
            double yenes = bolivianos * tasaYen;
            double soles = bolivianos * tasaSol;
            double reales = bolivianos * tasaReal;
            double pesos = bolivianos * tasaPesoMexicano;
            double pesosChilenos = bolivianos * tasaPesoChileno;

            // Mostrar los resultados en los campos de texto correspondientes
            editTextBolivianos.setText(String.format("%.2f", bolivianos));
            editTextDolares.setText(String.format("%.2f", dolares));
            editTextEuros.setText(String.format("%.2f", euros));
            editTextYenes.setText(String.format("%.2f", yenes));
            editTextSoles.setText(String.format("%.2f", soles));
            editTextReales.setText(String.format("%.2f", reales));
            editTextPesos.setText(String.format("%.2f", pesos));
            editTextPesosChilenos.setText(String.format("%.2f", pesosChilenos));
        } else {
            clearFields();
        }
    }




    private void clearFields() {
        editTextBolivianos.setText("");
        editTextDolares.setText("");
        editTextEuros.setText("");
        editTextYenes.setText("");
        editTextSoles.setText("");
        editTextReales.setText("");
        editTextPesos.setText("");
        editTextPesosChilenos.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
