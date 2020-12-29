package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sumTextView;
    ArrayList<Integer> possibleAnswers = new ArrayList<Integer>();
    ArrayList<Button> optionButtons = new ArrayList<Button>();
    int correctOption;

    public void chooseAnswer(View view) {
        String correct = Integer.toString(correctOption);
        String tag = view.getTag().toString();

        if(correct.equals(tag)) {
            Log.i("Correct", "You got it!");
        }
        else {
            Log.i("Oh no", ":(");
        }
    }

    public void startGame(View view) {
        goButton.setVisibility(View.INVISIBLE);
    }

    private static final int[] BUTTON_IDS = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int sum = a+b;
        correctOption = rand.nextInt(4);

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
}