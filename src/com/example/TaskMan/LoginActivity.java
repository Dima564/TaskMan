package com.example.TaskMan;

import android.app.Activity;
import android.app.Notification;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        setContentView(R.layout.login);


        mLogInButton = (Button) findViewById(R.id.login_Button);
        mSignUpButton = (Button) findViewById(R.id.registration_Button);

        mUsernameEditText = (EditText) findViewById(R.id.username_EditText);
        mPasswordEditText = (EditText) findViewById(R.id.password_EditText);


        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("Please wait");
                dialog.setMessage("Loggin in, please wait.");
                dialog.show();

                Bundle userPass = new Bundle();
                userPass.putString(USER,mUsernameEditText.getText().toString());
                userPass.putString(PASSWORD,mPasswordEditText.getText().toString());
                new RegisterUser().execute(userPass);
//                Toast.makeText(LoginActivity.this, "PostExecute",Toast.LENGTH_LONG);




            }
        });



    }


    class RegisterUser extends AsyncTask<Bundle,Void,Integer> {


        @Override
        protected Integer doInBackground(Bundle... params) {
            String request = ENDPOINT + "api/user/register";
            HttpURLConnection connection = null;
            OutputStream out = null;
            int response = 0;
            byte[] data = "{\"username\": \"some-usernam33e\",\"password\": \"some-pass2\",\"email\": \"emai3l@g.com\",\"first_name\": \"somename\",\"last_name\": \"somelname\" }".getBytes();

            try {
                URL url = new URL(request);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                out = connection.getOutputStream();
                out.write(data);
                out.close();
                response = connection.getResponseCode();
                //System.out.println(connection.getResponseCode());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }


            String username = params[0].getString(USER);
            String password = params[0].getString(PASSWORD);
            return response;
        }

        @Override
        protected void onPostExecute(Integer s) {
            Log.i(TAG,String.valueOf(s));
//            Log.i(TAG,Base64.encodeToString(s.getBytes(),0));
            //String s = new  String(Base64.encode(s.getBytes()));
//            Log.i(TAG, Base64.encode(s.getBytes()));

        }
    }


}
