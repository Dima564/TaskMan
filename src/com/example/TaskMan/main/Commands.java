package com.example.TaskMan.main;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.example.TaskMan.Project;
import com.example.TaskMan.ServerFetcher;
import com.example.TaskMan.Task;
import com.example.TaskMan.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

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
                jsonString = ServerFetcher.get(auth, ServerFetcher.ENDPOINT + "api/projects");
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

    public static class getTasksForProject implements Command<Void> {
        public static final String PROJECT_ID_EXTRA = "id";
        @Override
        public Void execute(Object... params) {
            int projectId = (Integer)params[0];
            String auth = authorization(TaskManApplication.getUsername(),TaskManApplication.getPassword());
            String jsonString = "";

            try {
                jsonString = ServerFetcher.get(auth,
                        ServerFetcher.ENDPOINT + "api/projects/" + String.valueOf(projectId) + "/tasks");
            } catch (IOException e) {
                Log.i(TAG,"Networking error");
                return null;
            }

            JSONArray json = null;
            try {
                json = (JSONArray) new JSONTokener(jsonString).nextValue();
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            TaskManApplication.getCurrentUser().getProject(projectId).addTasks(json);


            return null;
        }
    }


    public static class addTask implements Command<Boolean> {
        public static final String PROJECT_ID_EXTRA = "project_id";
        public static final String TITLE_EXTRA = "title";
        public static final String BODY_EXTRA = "body";
        public static final String COMPLETED_EXTRA = "completed";
        public static final String PRIORITY_EXTRA = "priority";

        @Override
        public Boolean execute(Object... params) {
            Bundle args = (Bundle) params[0];
            Integer projectId = args.getInt(PROJECT_ID_EXTRA);
            String title = args.getString(TITLE_EXTRA);
            String body = args.getString(BODY_EXTRA);
            Boolean completed = false;
            Integer priority = args.getInt(PRIORITY_EXTRA);
            String auth = authorization(TaskManApplication.getUsername(),TaskManApplication.getPassword());
            String requestBody = null;

            try {
                requestBody = Task.newTaskJSON(title, body, completed, priority).toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            try {
                ServerFetcher.post(auth, ServerFetcher.ENDPOINT + "api/projects/" + String.valueOf(projectId) + "/tasks", requestBody);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

}
