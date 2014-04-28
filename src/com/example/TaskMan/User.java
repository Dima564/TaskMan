package com.example.TaskMan;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dima on 4/22/14.
 */
public class User {
    private static final String JSON_ID = "id";
    private static final String JSON_USERNAME = "username";
    private static final String JSON_EMAIL = "email";
    private static final String JSON_FIRST_NAME = "first_name";
    private static final  String JSON_LAST_NAME = "last_name";


    private int mId;
    private String mUsername;
    private String mEmail;
    private String mLastname = "";
    private String mFirstname = "";
    private User mUser;
    private ArrayList<Project> mProjects;

    // TODO
    public JSONObject toJSON() {
        return null;
    }
    private User() {

    }

    public ArrayList<Project> getProjects() {
        return mProjects;
    }

    public void setProjects(ArrayList<Project> projects) {
        mProjects = projects;
    }

    public static User fromJSON(String jsonString) {


       User u = new User();
       try {
           JSONObject json = new JSONObject(jsonString);
           u.mId = json.getInt(JSON_ID);
           u.mUsername = json.getString(JSON_USERNAME);
           u.mEmail = json.getString(JSON_EMAIL);
           if (json.has(JSON_FIRST_NAME)) {
               u.mFirstname = json.getString(JSON_FIRST_NAME);
           }
           if (json.has(JSON_LAST_NAME)) {
               u.mLastname = json.getString(JSON_LAST_NAME);
           }
       } catch (JSONException e) {
           e.printStackTrace();
          return null;
       }

       return u;
   }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getLastname() {
        return mLastname;
    }

    public String getFirstname() {
        return mFirstname;
    }

}
