package com.example.fey.cityquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;

import java.util.concurrent.TimeUnit;


/**
 * Created by Fey on 2015/9/23.
 */
public class QuizPage extends Activity implements OnClickListener {

    String test_q = "Dota is ";
    String test_a1 = "This is an answer that is very long and should be resized to a smaller font size";
    String test_a2 = "Pepsi";
    String test_a3 = "Monster";
    String test_a4 = "MOBA game";
    String text_c = "MOBA game";
    Question test_question = new Question(test_q,test_a1,test_a2,test_a3,test_a4,text_c);


    Quiz CurrentQuiz = new Quiz();

    TextView timerTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);

        timerTextView = (TextView) findViewById(R.id.timeTextView);
        timerTextView.setText("Time Remaining:\n60");

        //60 second timer
        final CounterClass timer = new CounterClass(60000, 1000);
        timer.start();
        CurrentQuiz.AddQuestion(test_question);
        Question TempQuestion = CurrentQuiz.ReturnQuestion();
        generate_question(TempQuestion);

        System.out.println(CurrentQuiz.getNumberRemainingQuestions());
    }

    @Override
    public void onClick(View v){



        //which button user clicked
        Button the_clicked_button = (Button) findViewById(v.getId());
        String answered = the_clicked_button.getText().toString();

        CurrentQuiz.CheckAnswer(answered);

        //no more questions, go to result page
        if(CurrentQuiz.isEmpty())
        {
            Intent intent = new Intent(QuizPage.this,ResultPage.class);
            String numCorrect = Integer.toString(CurrentQuiz.getCorrectAnswered());
            String numWrong = Integer.toString(CurrentQuiz.getWrongAnswered());
            intent.putExtra("correct", numCorrect);
            intent.putExtra("wrong", numWrong);
            startActivity(intent);
        }
        //still questions left
        else
        {
            CurrentQuiz.CurrentQuestionIndex++;
            Question TempQuestion = CurrentQuiz.ReturnQuestion();
            generate_question(TempQuestion);
        }


    }


    public void generate_question(Question question)
    {
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(question.button1().toString());
        //adjust button text size based on length of question
        if(button2.getLineCount() > 4 && button2.getLineCount() <= 6){
            button2.setTextSize(15);
        }
        else if(button2.getLineCount() > 6){
            button2.setTextSize(10);
        }
        button2.setOnClickListener(this);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText(question.button2().toString());
        if(button3.getLineCount() > 4 && button3.getLineCount() <= 6){
            button3.setTextSize(15);
        }
        else if(button3.getLineCount() > 6){
            button3.setTextSize(10);
        }
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText(question.button3().toString());
        if(button4.getLineCount() > 4 && button4.getLineCount() <= 6){
            button4.setTextSize(15);
        }
        else if(button4.getLineCount() > 6){
            button4.setTextSize(10);
        }
        button4.setOnClickListener(this);

        Button button5= (Button) findViewById(R.id.button5);
        button5.setText(question.button4().toString());
        if(button5.getLineCount() > 4 && button5.getLineCount() <= 6){
            button5.setTextSize(15);
        }
        else if(button5.getLineCount() > 6){
            button5.setTextSize(10);
        }
        button5.setOnClickListener(this);

        TextView current_question = (TextView) findViewById(R.id.textView2);
        current_question.setText(question.return_question().toString());
    }

    //This class is for the 60 second timer in the QuizPage.
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        // Updates timer every second
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("Time Remaining:\n%02d", TimeUnit.MILLISECONDS.toSeconds(millis));
            timerTextView.setText(hms);
        }

        @Override
        //This runs when the timer hits 0- goes to the results page while marking any unanswered questions false
        public void onFinish() {
            timerTextView.setText("Out of Time");
            Intent intent = new Intent(QuizPage.this,ResultPage.class);
            String numCorrect = Integer.toString(CurrentQuiz.getCorrectAnswered());
            int numWrong = CurrentQuiz.getWrongAnswered();
            numWrong += CurrentQuiz.getNumberRemainingQuestions();
            String numWrongString = Integer.toString(numWrong);
            intent.putExtra("correct", numCorrect);
            intent.putExtra("wrong", numWrongString);
            startActivity(intent);
        }
    }

}
