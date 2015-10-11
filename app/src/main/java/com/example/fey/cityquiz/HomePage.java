package com.example.fey.cityquiz;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.view.MenuInflater;
import android.view.Menu;

public class HomePage extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_page, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onClick(View v){
        Intent intent = new Intent(HomePage.this,QuizPage.class);
        startActivity(intent);
    }




}
