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

package com.macco.quizdemo;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizActivity extends AppCompatActivity {
    TextView questionTitle, questionDetails, questionCount;
    Button[] answerButtons = new Button[3];
    ProgressBar progressBar;
    Button submitButton;
    int currentIndex = 0;
    int score = 0;
    int selected = -1;

    String[][] questions = {
            {"What is Android?", "OS", "Game", "App", "OS"},
            {"Java is...", "Fruit", "Language", "Car", "Language"},
            {"2 + 2 = ?", "3", "4", "5", "4"},
            {"Best IDE for Android?", "VSCode", "Xcode", "Android Studio", "Android Studio"},
            {"Android SDK is...", "Tool", "Game", "Framework", "Tool"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        questionTitle = findViewById(R.id.questionTitle);
        questionDetails = findViewById(R.id.questionDetails);
        questionCount = findViewById(R.id.questionCount);
        progressBar = findViewById(R.id.progressBar);
        submitButton = findViewById(R.id.submitButton);

        answerButtons[0] = findViewById(R.id.answer1);
        answerButtons[1] = findViewById(R.id.answer2);
        answerButtons[2] = findViewById(R.id.answer3);
        loadQuestion();

        for (int i = 0; i < answerButtons.length; i++) {
            int finalI = i;
            answerButtons[i].setOnClickListener(v -> {
                selected = finalI;
                for (Button b : answerButtons) b.setBackgroundColor(Color.LTGRAY);
                answerButtons[finalI].setBackgroundColor(Color.YELLOW);
            });
        }

        submitButton.setOnClickListener(v -> {
            if (selected == -1) return;

            String correctAnswer = questions[currentIndex][4];
            if (answerButtons[selected].getText().equals(correctAnswer)) {
                answerButtons[selected].setBackgroundColor(Color.GREEN);
                score++;
            } else {
                answerButtons[selected].setBackgroundColor(Color.RED);
                for (Button b : answerButtons) {
                    if (b.getText().equals(correctAnswer)) {
                        b.setBackgroundColor(Color.GREEN);
                        break;
                    }
                }
            }

            new Handler().postDelayed(() -> {
                currentIndex++;
                if (currentIndex < questions.length) {
                    selected = -1;
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        });
    }

    void loadQuestion() {
        questionTitle.setText("Android question title:");
        questionDetails.setText(questions[currentIndex][0]);
        for (int i = 0; i < 3; i++) {
            answerButtons[i].setText(questions[currentIndex][i + 1]);
            answerButtons[i].setBackgroundColor(Color.LTGRAY);
        }
        questionCount.setText((currentIndex + 1) + "/" + questions.length);
        progressBar.setProgress((int) (((currentIndex + 1) / (float) questions.length) * 100));
    }
}

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

