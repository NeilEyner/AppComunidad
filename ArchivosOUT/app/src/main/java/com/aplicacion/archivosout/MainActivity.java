package com.aplicacion.archivosout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView contentTextView;
    private EditText searchEditText;
    private Button clearButton, finishButton;
    private ActivityResultLauncher<Intent> filePickerLauncher;
    private List<String[]> csvData = new ArrayList<>();
    private String[] columnHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTextView = findViewById(R.id.contentTextView);
        searchEditText = findViewById(R.id.searchEditText);
        clearButton = findViewById(R.id.clearButton);
        finishButton = findViewById(R.id.finishButton);
        Button selectFileButton = findViewById(R.id.selectFileButton);

        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            readCsvFile(uri);
                            displayCsvData();
                        }
                    }
                }
        );

        selectFileButton.setOnClickListener(v -> openFilePicker());
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterAndDisplayCsvData();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        clearButton.setOnClickListener(v -> {
            searchEditText.setText("");
            displayCsvData();
        });

        finishButton.setOnClickListener(v -> finish());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        filePickerLauncher.launch(intent);
    }

    private void readCsvFile(Uri uri) {
        csvData.clear();
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                if (isFirstLine) {
                    columnHeaders = row;
                    isFirstLine = false;
                } else {
                    csvData.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al leer el archivo: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void displayCsvData() {
        StringBuilder content = new StringBuilder();
        // Mostrar la primera fila con los encabezados de columna
        content.append(String.join(" | ", columnHeaders)).append("\n");
        content.append("-".repeat(columnHeaders.length * 6 - 1)).append("\n");
        // Mostrar el resto de las filas
        for (String[] row : csvData) {
            content.append(String.join(" | ", row)).append("\n");
        }
        contentTextView.setText(content.toString());
    }

    private void filterAndDisplayCsvData() {
        String searchText = searchEditText.getText().toString().toLowerCase();
        StringBuilder content = new StringBuilder();
        // Mostrar la primera fila con los encabezados de columna
        content.append(String.join(" | ", columnHeaders)).append("\n");
        content.append("-".repeat(columnHeaders.length * 6 - 1)).append("\n");
        // Mostrar las filas que coinciden con la búsqueda
        for (String[] row : csvData) {
            for (String cell : row) {
                if (cell.toLowerCase().contains(searchText)) {
                    content.append(String.join(" | ", row)).append("\n");
                    break;
                }
            }
        }
        contentTextView.setText(content.toString());
    }
}