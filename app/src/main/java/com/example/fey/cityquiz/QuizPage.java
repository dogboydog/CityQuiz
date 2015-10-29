package com.example.fey.cityquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import com.parse.ParseObject;
import java.util.concurrent.TimeUnit;
import com.parse.ParseQuery;
import com.parse.ParseException;
import java.util.List;

public class QuizPage extends Activity implements OnClickListener {

    Quiz CurrentQuiz = new Quiz();
    TextView timerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);

        //newQuestion stores the question information form parse
        ParseObject newQuestion = null;
        //questionList holds the five ParseObject questions
        List<ParseObject> questionList = null;

        //query gets the first 5 objects from parse and store them in the questionlist
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.setLimit(5);
        try {
            //query.find() returns a list of 5 questions
            questionList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Get information from the parseobjects and make them into Question objects and add the questions to the CurrentQuiz
        for(int i=0; i<5; i++) {
                newQuestion = questionList.get(i);
                String quest = newQuestion.getString("question");
                String a1 = newQuestion.getString("answer1");
                String a2 = newQuestion.getString("answer2");
                String a3 = newQuestion.getString("answer3");
                String a4 = newQuestion.getString("answer4");
                String ca = newQuestion.getString("correctAnswer");
                Question test_question1 = new Question(quest, a1, a2, a3, a4, ca);
                CurrentQuiz.AddQuestion(test_question1);
        }

        //create textview for the timer
        timerTextView = (TextView) findViewById(R.id.timeTextView);
        timerTextView.setText("Time Remaining:\n60");

        //60 second timer
        final CounterClass timer = new CounterClass(60000, 1000);
        timer.start();

        Question TempQuestion = CurrentQuiz.ReturnQuestion();
        generate_question(TempQuestion);

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

    //generates question in text box, and answers in the buttons.
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
