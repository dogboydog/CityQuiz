package com.example.fey.cityquiz;

/**
 * Created by Fey on 2015/11/9.
 */
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.EditText;



public class LeaderPage extends Activity {

    Button button12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderpage);



        button12 = (Button) findViewById(R.id.buttonLeaderonleaderboard);


        button12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(LeaderPage.this,HomePage.class);
                startActivity(intent);
            }
        });
    }
}
