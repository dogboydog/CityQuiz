package com.example.fey.cityquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class ResultPage extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);

        //get num right/wrong from the quizpage
        Intent intent = getIntent();
        String numCorrect = (String) intent.getSerializableExtra("correct");
        String numWrong = (String) intent.getSerializableExtra("wrong");

        //Update text view to display the number of correct/wrong answers
        TextView resultsView = (TextView) findViewById(R.id.textView3);
        resultsView.setText("You got "+ numCorrect + " correct, and " + numWrong + " wrong.");

        //go home button
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

    }

    /**
     *Button sends user back to home screen
     */
    @Override
    public void onClick(View v){
        Intent intent = new Intent(ResultPage.this,HomePage.class);
        startActivity(intent);
    }
}
