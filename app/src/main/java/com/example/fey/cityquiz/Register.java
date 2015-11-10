package com.example.fey.cityquiz;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.parse.*;

/**
 * Created by Jordon Rapoport on 11/4/15.
 *
 * This class allows a user, not currently logged in, to register an account. All accounts are
 * stored via Parse with a Username, an encrypted password, and an email address.
 *
 */
public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick(View v){
        Intent intent = new Intent(Register.this,HomePage.class);
        startActivity(intent);
    }

    /**
     * Checks to see if any fields are empty and/or if two password fields are equal
     */
    public void checkFields(View v){

        EditText name_field = (EditText)findViewById(R.id.nameField);
        EditText email_field = (EditText)findViewById(R.id.emailField);
        EditText pass1 = (EditText)findViewById(R.id.passwordField);
        EditText pass2 = (EditText)findViewById(R.id.passwordConfirmField);

        String name = name_field.getText().toString().trim();
        String email = email_field.getText().toString().trim();
        String password = pass1.getText().toString().trim();
        String passwordRetry = pass2.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            name_field.setError("You must enter a username");
        }

        if(TextUtils.isEmpty(email)){
            email_field.setError("You must enter an email");
        }

        if(TextUtils.isEmpty(password)){
            pass1.setError("You must enter a password");
        }

        if(TextUtils.isEmpty(passwordRetry)){
            pass2.setError("You must enter a password");
        }

        if(password.equals(passwordRetry)){
            ParseUser user = new ParseUser();
            user.setUsername(name);
            user.setPassword(password);
            user.setEmail(email);

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        //success
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                        Log.d("Error", e.toString());
                        //ParseUser.logOut();

                    }
                }
            });

        } else {
            //Displays dialog to inform the user the passwords were unequal and they must try again
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage(R.string.unequalPasswords).setTitle(R.string.passwordDialogTitle);
            builder.setCancelable(true);
            builder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        }



    }



}
