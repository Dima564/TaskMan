package com.example.TaskMan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

//import org.apache.commons.codec.binary.Base64;
public class LoginActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    public static final String ENDPOINT = "http://54.187.34.1:8000/";


    EditText mUsernameEditText;
    EditText mPasswordEditText;
    Button mLogInButton;
    Button mSignUpButton;
    ProgressDialog dialog;



    public static final String TAG = "LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


//      TaskManApplication.setCredentials("dima","password");


        mLogInButton = (Button) findViewById(R.id.login_Button);
        mSignUpButton = (Button) findViewById(R.id.registration_Button);

        mUsernameEditText = (EditText) findViewById(R.id.username_EditText);
        mPasswordEditText = (EditText) findViewById(R.id.password_EditText);


        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            dialog.



            }
        });



    }

    private class StartMainActivity extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            User u = new Commands.getSelf().execute();
            TaskManApplication.setCurrentUser(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(LoginActivity.this,TaskManMainActivity.class);
            startActivity(i);
        }
    }

}
