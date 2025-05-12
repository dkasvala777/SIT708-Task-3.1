package com.macco.quizdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    Button startButton;
    public static String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        startButton = findViewById(R.id.startButton);
        Button calcButton = findViewById(R.id.calcButton);


        if (!username.isEmpty()) {
            nameInput.setText(username);
        }

        startButton.setOnClickListener(v -> {
            username = nameInput.getText().toString();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });
        calcButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}