package com.example.TaskMan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dima on 4/16/14.
 */
public class RegistrationActivity extends FragmentActivity {

    public static final String USER = "LOGINACTIVITY.LOGIN.EXTRA";
    public static final String PASSWORD = "LOGINACTIVITY.PASSWORD.EXTRA";


    EditText mUsernameEditText;
    EditText mPasswordEditText;
    EditText mEmailEditText;
    EditText mFirstNameEditText;
    EditText mLastNameEditText;

    Button mRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        bindWidgets();

    }

    private void bindWidgets() {
        mUsernameEditText = (EditText) findViewById(R.id.username_EditText);
        mPasswordEditText = (EditText) findViewById(R.id.password_EditText);
        mEmailEditText = (EditText) findViewById(R.id.email_EditText);
        mFirstNameEditText = (EditText) findViewById(R.id.first_name);
        mLastNameEditText = (EditText) findViewById(R.id.last_name);

        mRegister = (Button) findViewById(R.id.registration_Button);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String firstname = mFirstNameEditText.getText().toString();
                String lastname = mLastNameEditText.getText().toString();



            }
        });
    }


    private class RegisterAsyncTask extends AsyncTask<Bundle,Void,Void> {
        @Override
        protected Void doInBackground(Bundle... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
