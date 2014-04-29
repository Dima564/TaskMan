package com.example.TaskMan.main.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.example.TaskMan.R;
import com.example.TaskMan.User;
import com.example.TaskMan.main.Commands;
import com.example.TaskMan.main.TaskManApplication;

//import org.apache.commons.codec.binary.Base64;
public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */


    EditText mUsernameEditText;
    EditText mPasswordEditText;
    Button mLogInButton;
    Button mSignUpButton;
    ProgressDialog dialog;



    public static final String TAG = "LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.login_layout);



//      TaskManApplication.setCredentials("dima","password");


        mLogInButton = (Button) findViewById(R.id.login_Button);
        mSignUpButton = (Button) findViewById(R.id.registration_Button);

        mUsernameEditText = (EditText) findViewById(R.id.username_EditText);
        mPasswordEditText = (EditText) findViewById(R.id.password_EditText);


        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskManApplication.setCredentials(mUsernameEditText.getText().toString(), mPasswordEditText.getText().toString());
                new LogIn().execute();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                i.putExtra(mUsernameEditText.getText().toString(),RegistrationActivity.USERNAME);
                startActivity(i);
            }
        });



    }

    public class LogIn extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Logging in ... ");
            dialog.setMessage("Logging in. Please, wait");
            dialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {


            User u = new Commands.getSelf().execute();
            u.setProjects(new Commands.getProjects().execute());
            TaskManApplication.setCurrentUser(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(LoginActivity.this,ProjectListActivity.class);
            startActivity(i);


            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

}
