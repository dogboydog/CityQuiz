package com.example.fey.cityquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Fey on 2015/9/23.
 */
public class ResultPage extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

    }


    @Override
    public void onClick(View v){
        Intent intent = new Intent(ResultPage.this,HomePage.class);
        startActivity(intent);
    }
}
