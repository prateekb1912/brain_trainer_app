package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton, restartButton;
    TextView sumTextView, responseTextView, scoreTextView, timerTextView;
    ArrayList<Button> optionButtons = new ArrayList<Button>();
    int correctOption, score = 0, questions = 0;
    private static final int[] BUTTON_IDS = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
    };

    public void playAgain(View view) {
        score = 0;
        questions = 0;
        timerTextView.setText("30s");
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                responseTextView.setText("DONE!");
                restartButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion() {

        ArrayList<Integer> possibleAnswers = new ArrayList<Integer>();
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int sum = a+b;
        correctOption = rand.nextInt(4);

        newQuestion();

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        for(int i=0; i<4; i++)
        {
            optionButtons.add(findViewById(BUTTON_IDS[i]));

            if(i == correctOption)
                possibleAnswers.add(sum);
            else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == sum) {
                    wrongAnswer = rand.nextInt(41);
                }
                possibleAnswers.add(wrongAnswer);
            }

            optionButtons.get(i).setText(Integer.toString(possibleAnswers.get(i)));
        }
    }

    public void chooseAnswer(View view) {
        String correct = Integer.toString(correctOption);
        String tag = view.getTag().toString();

        if(correct.equals(tag)) {
            responseTextView.setText("Correct!");
            score++;
            questions++;
        }
        else {
            responseTextView.setText("Wrong :(");
            questions++;
        }
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(questions));
        newQuestion();
    }

    public void startGame(View view) {
        goButton.setVisibility(View.INVISIBLE);
        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        responseTextView = findViewById(R.id.responseTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        restartButton = findViewById(R.id.restartButton);

        playAgain(timerTextView);
    }
}