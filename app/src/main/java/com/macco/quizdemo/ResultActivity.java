package com.macco.quizdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView resultText;
    Button restartButton, finishButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        resultText = findViewById(R.id.resultText);
        restartButton = findViewById(R.id.restartButton);
        finishButton = findViewById(R.id.finishButton);

        int score = getIntent().getIntExtra("score", 0);
        resultText.setText("Congratulations " + MainActivity.username + "!\nYour Score: " + score + "/5");

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        finishButton.setOnClickListener(v -> finishAffinity());
    }
}