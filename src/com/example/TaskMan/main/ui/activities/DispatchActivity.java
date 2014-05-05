package com.example.TaskMan.main.ui.activities;

import android.app.*;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
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


    private class StartMainActivity extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            User u = new Commands.getSelf().execute();
            if (u == null) {
               return false;
            }
            u.setProjects(new Commands.getProjects().execute());
            TaskManApplication.setCurrentUser(u);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);

            if (!success) {
                Toast.makeText(DispatchActivity.this,
                        "Networking error. Please, check connection to the internet",Toast.LENGTH_LONG).show();
                Intent i = new Intent(DispatchActivity.this, LoginActivity.class);
                startActivity(i);
                return;
            }
            Intent i = new Intent(DispatchActivity.this,ProjectListActivity.class);
            startActivity(i);
        }
    }
}

