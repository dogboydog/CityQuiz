package com.example.fey.cityquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;





/**
 * Created by Fey on 2015/9/23.
 */
public class QuizPage extends Activity implements OnClickListener {

    String test_q = "Dota is ";
    String test_a1 = "Coke";
    String test_a2 = "Pepsi";
    String test_a3 = "Monster";
    String test_a4 = "MOBA game";
    String text_c = "MOBA game";
    Question test_question = new Question(test_q,test_a1,test_a2,test_a3,test_a4,text_c);


    Quiz CurrentQuiz = new Quiz();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);


        CurrentQuiz.AddQuestion(test_question);
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


        /*
        //there are more questions..
        if (test_question.isAnswer(answered))
        {

        }
        //no more questions, go to result page
        else
        {
            Intent intent = new Intent(QuizPage.this,ResultPage.class);
            startActivity(intent);
        }
        */


    }


    public void generate_question(Question question)
    {


        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(question.button1().toString());
        button2.setOnClickListener(this);

        //
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText(question.button2().toString());
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText(question.button3().toString());
        button4.setOnClickListener(this);

        Button button5= (Button) findViewById(R.id.button5);
        button5.setText(question.button4().toString());
        button5.setOnClickListener(this);

        TextView current_question = (TextView) findViewById(R.id.textView2);
        current_question.setText(question.return_question().toString());
    }

}
