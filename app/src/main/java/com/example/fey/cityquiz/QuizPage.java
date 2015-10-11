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

    String test_q = "University of Pittsburgh is ";
    String test_a1 = "Food";
    String test_a2 = "Animal";
    String test_a3 = "Vehicle";
    String test_a4 = "University";
    String text_c = "University";
    Question test_question = new Question(test_q,test_a1,test_a2,test_a3,test_a4,text_c);

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);

        generate_question();
    }

    @Override
    public void onClick(View v){

        Button the_clicked_button = (Button) findViewById(v.getId());
        String answered = the_clicked_button.getText().toString();
        if (test_question.isAnswer(answered))
        {
            Intent intent = new Intent(QuizPage.this,ResultPage.class);
            startActivity(intent);
        }
        else
        {
            //Wrong result
        }


    }


    public void generate_question()
    {


        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(test_question.button1().toString());
        button2.setOnClickListener(this);

        //
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText(test_question.button2().toString());
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText(test_question.button3().toString());
        button4.setOnClickListener(this);

        Button button5= (Button) findViewById(R.id.button5);
        button5.setText(test_question.button4().toString());
        button5.setOnClickListener(this);

        TextView question = (TextView) findViewById(R.id.textView2);
        question.setText(test_question.return_question().toString());
    }

}
