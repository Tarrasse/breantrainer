package com.example.mahmoud.breantrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private Button startButton ;
    private TextView sumTextVew;
    private TextView pointsTextVew;
    private TextView timerTextView;
    private int a;
    private int b;
    private ArrayList<Integer> answers = new ArrayList<>();
    private int locationOfCorrectAns;
    private TextView resultTextView;
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private int score =0;
    private int numberOfQuestions =0;
    private Button playAgainButton;
    private RelativeLayout components;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = (Button)findViewById(R.id.startButton);               //start button init
        sumTextVew = (TextView)findViewById(R.id.sumtextview);
        timerTextView = (TextView)findViewById(R.id.timertextView);

        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);

        pointsTextVew = (TextView)findViewById(R.id.pointstextview);
        playAgainButton =(Button) findViewById(R.id.playAgainButton);

       components = (RelativeLayout)findViewById(R.id.componentsLayout);

        genQues();

        playAgain(playAgainButton);


    }

    public void genQues(){

        Random gen = new Random();
        a = gen.nextInt(21) ;
        b = gen.nextInt(21) ;

        answers.clear();

        sumTextVew.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAns = gen.nextInt(4);
        int inCorrectAns ;
        for (int i = 0 ; i<4 ; i++){
            if(i==locationOfCorrectAns)
                answers.add(a+b);
            else {
                inCorrectAns = gen.nextInt(41);
                while (inCorrectAns == a + b)
                    inCorrectAns = gen.nextInt(41);
                answers.add(inCorrectAns);
            }
        }
        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View v){
        score = 0;
        numberOfQuestions=0;
        timerTextView.setText("50s");
        pointsTextVew.setText("0/0");
        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100 ,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf( millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("DONE..!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    public void chooseAnswer (View v){
        Log.i("Tag = ", (String) v.getTag());
        if(v.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){
            resultTextView.setText("Correct");
            resultTextView.setVisibility(View.VISIBLE);
            score++;
        }else{
            resultTextView.setText("InCorrect");
            resultTextView.setVisibility(View.VISIBLE);
        }
        genQues();
        numberOfQuestions++;
        pointsTextVew.setText(Integer.toString(score) + "/" +Integer.toString(numberOfQuestions));
    }

    public void start (View v ){                                            //the starting Function

        components.setVisibility(View.VISIBLE);
        v.setVisibility(View.INVISIBLE);
    }


}
