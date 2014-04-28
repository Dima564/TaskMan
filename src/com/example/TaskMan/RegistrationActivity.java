package com.example.TaskMan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dima on 4/16/14.
 */
public class RegistrationActivity extends Activity {

    public static final String USERNAME = "LOGINACTIVITY.LOGIN.EXTRA";


    EditText mUsernameEditText;
    EditText mPasswordEditText;
    EditText mEmailEditText;
    EditText mFirstNameEditText;
    EditText mLastNameEditText;
    ProgressDialog dialog;

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
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();

                Bundle args = new Bundle();
                args.putString(Commands.registerUser.USERNAME_EXTRA,username);
                args.putString(Commands.registerUser.PASSWORD_EXTRA,password);
                args.putString(Commands.registerUser.EMAIL_EXTRA,email);
                args.putString(Commands.registerUser.FIRST_NAME_EXTRA,firstName);
                args.putString(Commands.registerUser.LAST_NAME_EXTRA,lastName);
                new RegisterAsyncTask().execute(args);
            }
        });
    }


    private class RegisterAsyncTask extends AsyncTask<Bundle,Void,Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RegistrationActivity.this);
            dialog.setMessage("Registering user... ");
            dialog.setMessage("Registering user. Please, wait");
            dialog.show();

        }

    @Override
        protected Boolean doInBackground(Bundle... params) {
            Boolean success = true;
            success = new Commands.registerUser().execute(params[0]);
            User u = new Commands.getSelf().execute();
            u.setProjects(new Commands.getProjects().execute());
            TaskManApplication.setCurrentUser(u);
            return success;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);

            Intent i = new Intent(RegistrationActivity.this,TaskManMainActivity.class);
            startActivity(i);


            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
