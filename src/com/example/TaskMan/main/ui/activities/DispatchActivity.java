package com.example.TaskMan.main.ui.activities;

import android.app.*;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.example.TaskMan.User;
import com.example.TaskMan.main.Commands;
import com.example.TaskMan.main.TaskManApplication;

/**
 * Created by dima on 4/22/14.
 */
public class DispatchActivity extends Activity {
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
            u.setProjects(new Commands.getProjects().execute());
            TaskManApplication.setCurrentUser(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(DispatchActivity.this,ProjectListActivity.class);
            startActivity(i);
        }
    }
}

