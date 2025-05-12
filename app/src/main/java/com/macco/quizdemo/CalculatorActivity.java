package com.macco.quizdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {
    EditText input1, input2;
    Button addBtn, subBtn;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        resultText = findViewById(R.id.resultText);

        Button backToQuizBtn = findViewById(R.id.backToQuizBtn);
        backToQuizBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        addBtn.setOnClickListener(v -> {
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = Double.parseDouble(input2.getText().toString());
            double result = num1 + num2;
            resultText.setText("Result: " + result);
        });

        subBtn.setOnClickListener(v -> {
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = Double.parseDouble(input2.getText().toString());
            double result = num1 - num2;
            resultText.setText("Result: " + result);
        });
    }
}