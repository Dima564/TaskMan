package com.example.TaskMan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by dima on 4/22/14.
 */
public class DispatchActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = TaskManApplication.getUsername();
        String password = TaskManApplication.getPassword();


        if (TaskManApplication.getUsername() == "" || TaskManApplication.getPassword() == "") {
            Intent i = new Intent(DispatchActivity.this, LoginActivity.class);
            startActivity(i);
        } else {
            new StartMainActivity().execute();
        }
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
            Intent i = new Intent(DispatchActivity.this,TaskManMainActivity.class);
            startActivity(i);
        }
    }
}
