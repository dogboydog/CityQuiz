package com.example.fey.cityquiz;


import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.view.MenuInflater;
import android.view.Menu;
import android.content.SharedPreferences;
import com.parse.Parse;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class HomePage extends Activity /*implements OnClickListener*/ {

    //take quiz button
    Button button;
    public static boolean parseIsInitialized = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Enable Local Datastore.
        if(!parseIsInitialized) {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "2DRZJCltwfbJKH7cAcbkVNU2i2UFGq2uuDEIaxIK", "CEzdLzmlsCUSOhIM5r5spTz7ZTRjbVScAqS0dzU2");
            parseIsInitialized = true;
        }


        button = (Button) findViewById(R.id.button);

       //This is all the onclick information for the 'Take Quiz' button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //Open sharedPreferences, which saves information between app loads
                SharedPreferences prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //get date of the last time the player took a quiz
                String dateOfLastQuizTaken = prefs.getString("dateKey", "0");

                //get todays date
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yy");
                String todaysDate = sdf.format(c.getTime());

                //Update the sharedPreferences to store todays date
                editor.putString("dateKey", todaysDate);
                editor.commit();

                //Check to see if the user has already taken the quiz today
                if(false){//dateOfLastQuizTaken.equals(todaysDate)){     //SET THIS TO ALWAYS EQUAL FALSE IF YOU WANT TO RUN THE QUIZ MORE THAN ONCE PER DAY
                    button.setText("You've already taken the quiz today. Come back tommorow!");
                    button.setTextSize(20);
                }
                else{   //else, take the quiz
                    Intent intent = new Intent(HomePage.this,QuizPage.class);
                    startActivity(intent);
                }

            }
        });

        //when clicked, will take the user to the submit page
        Button button1 = (Button)findViewById(R.id.button8);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //go to submit page
                Intent myIntent = new Intent(HomePage.this, SubmitPage.class);
                startActivity(myIntent);
            }
        });

        //when clicked, will take the user to the about page
        Button button2 = (Button)findViewById(R.id.button7);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //go to about page
                Intent myIntent = new Intent(HomePage.this, AboutPage.class);
                startActivity(myIntent);
            }
        });

    }


    @Override
    //creates menu bar on home page
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_page, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
