package com.example.fey.cityquiz;

import android.widget.Toast;
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
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HomePage extends Activity {

    //take quiz button
    Button button;
    //boolean to determine if parse has already been initialized
    public static boolean parseIsInitialized = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Take Quiz button
        button = (Button) findViewById(R.id.button);

        //Check internet connection
        ConnectivityManager con=(ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        //This one checks both wifi and normal connection
        if(wifi|internet)
        {
            Toast.makeText(getApplicationContext(),
                    "There is connections", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "No connection", Toast.LENGTH_LONG).show();
        }

        // Initializes Parse so that we can store and retrieve objects to Parse.
        if(!parseIsInitialized) {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "2DRZJCltwfbJKH7cAcbkVNU2i2UFGq2uuDEIaxIK", "CEzdLzmlsCUSOhIM5r5spTz7ZTRjbVScAqS0dzU2");
            parseIsInitialized = true;
        }

        /**
         * This function is called when the take quiz button is clicked.
         * It checks to make sure the user has not taken the quiz today, then goes to the quiz page.
         */
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
                if(dateOfLastQuizTaken.equals(todaysDate)){     //SET THIS TO ALWAYS EQUAL FALSE IF YOU WANT TO RUN THE QUIZ MORE THAN ONCE PER DAY
                    button.setText("You've already taken the quiz today. Come back tommorow!");
                    button.setTextSize(20);
                }
                else{   //else, take the quiz
                    Intent intent = new Intent(HomePage.this,QuizPage.class);
                    startActivity(intent);
                }

            }
        });

        //Submit question button
        Button button1 = (Button)findViewById(R.id.button8);
        /**
         * This function runs when the submit button is clicked.
         * Goes to separate page to submit a question.
         */
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(HomePage.this, SubmitPage.class);
                startActivity(myIntent);
            }
        });

        //About page button
        Button button2 = (Button)findViewById(R.id.button7);
        /**
         * This function runs when the about page button is clicked.
         * Goes to separate about page.
         */
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //go to about page
                Intent myIntent = new Intent(HomePage.this, AboutPage.class);
                startActivity(myIntent);
            }
        });

        Button button3 = (Button)findViewById(R.id.buttonLeader);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //go to leader board page
                Intent myIntent = new Intent(HomePage.this, LeaderPage.class);
                startActivity(myIntent);
            }
        });

    }


    /**
     * Creates menu bar on home page for user registration
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_page, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
