package com.example.TaskMan;

import android.util.Base64;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ServiceConfigurationError;

/**
 * Created by dima on 4/17/14.
 */
public abstract class Commands {

    public static final String TAG = "Commands";
    // register user and return it in case of success
    // null in case of failure
    // TODO add error codes/ exceptions
    public static class SignUp implements Command<User> {
        @Override
        public User execute(Object... params) {

            String username = (String) params[0];
            String password = (String) params[1];
            String email = (String) params[2];
            String lastName = (String) params[3];
            String firstName = (String) params[4];


            return null;
        }
    }



    // get all projects
    // - your projects
    // - projects you're involved in
    public class GetProjects implements Command<String> {
        @Override
        public String execute(Object... params) {
            return null;
        }
    }

    public class getTasks implements Command<String > {
        @Override
        public String execute(Object... params) {
           int id = ((Integer) params[0]).intValue();
//           return ServerFetcher

            return null;
        }
    }


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

}
