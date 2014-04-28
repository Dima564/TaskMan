package com.example.TaskMan;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dima on 4/17/14.
 */
public abstract class Commands {

    public static final String TAG = "Commands";




    public static String authorization(String username, String password) {
        return Base64.encodeToString((username + ":" + password).getBytes(),0);
    }

    public static class getProjects implements Command<ArrayList<Project>> {

        @Override
        public ArrayList<Project> execute(Object... params) {
            String auth = authorization(TaskManApplication.getUsername(),TaskManApplication.getPassword());
            String jsonString = "";

            try {
                jsonString = ServerFetcher.get(auth,ServerFetcher.ENDPOINT + "api/projects");
            } catch (IOException e) {
                Log.i(TAG,"Networking error");
                return new ArrayList<Project>();
            }

          return Project.fromJSONProjects(jsonString);

        }
    }

    public static class getSelf implements Command<User> {
        @Override
        public User execute(Object... params) {
            String auth = authorization(TaskManApplication.getUsername(),TaskManApplication.getPassword());
            String jsonString = "";

            try {
                jsonString = ServerFetcher.get(auth, ServerFetcher.ENDPOINT + "api/users/self");
            } catch (IOException e) {
                Log.i(TAG,"Networking error");
                return null;
            }


            return User.fromJSON(jsonString);
        }
    }

    public static class addProject implements Command<Boolean> {

        @Override
        public Boolean execute(Object... params) {
            String title = (String) params[0];
            String body = (String) params[1];
            String auth = authorization(TaskManApplication.getUsername(),TaskManApplication.getPassword());
            String requestBody = null;
            try {
                requestBody = Project.newProjectJSON(title,body).toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            try {
                ServerFetcher.post(auth, ServerFetcher.ENDPOINT + "api/projects", requestBody);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }  

            return true;
        }
    }


    // TODO This is argument pass example: refactor existing commands to match this pattern
    // TODO Handle return value
    public static class registerUser implements Command<Boolean> {

        public static final String USERNAME_EXTRA = "username";
        public static final String PASSWORD_EXTRA = "password";
        public static final String EMAIL_EXTRA = "email";
        public static final String FIRST_NAME_EXTRA = "firstName";
        public static final String LAST_NAME_EXTRA = "lastName";

        @Override
        public Boolean execute(Object... params) {
            Bundle args = (Bundle) params[0];
            String username = args.getString(USERNAME_EXTRA);
            String password = args.getString(PASSWORD_EXTRA);
            String email = args.getString(EMAIL_EXTRA);
            String firstName = args.getString(FIRST_NAME_EXTRA);
            String lastName = args.getString(LAST_NAME_EXTRA);
            String requestBody = "";

            try {
                requestBody = User.newUserJSON(username,password,email,firstName,lastName).toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            try {
                ServerFetcher.post("",ServerFetcher.ENDPOINT + "api/users/register", requestBody);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

}
