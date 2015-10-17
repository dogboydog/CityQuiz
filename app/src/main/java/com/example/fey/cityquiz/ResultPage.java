package com.example.fey.cityquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Fey on 2015/9/23.
 */
public class ResultPage extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);

        Intent intent = getIntent();
        //Quiz quiz = (Quiz) intent.getSerializableExtra("quiz");
        String numCorrect = (String) intent.getSerializableExtra("correct");
        String numWrong = (String) intent.getSerializableExtra("wrong");

        System.out.println("Correct answered = " + numCorrect);
        System.out.println("Wrong answered = " + numWrong);

        TextView resultsView = (TextView) findViewById(R.id.textView3);
        resultsView.setText("You got "+ numCorrect + " correct, and " + numWrong + " wrong.");

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

    }


    @Override
    public void onClick(View v){
        Intent intent = new Intent(ResultPage.this,HomePage.class);
        startActivity(intent);
    }
}
