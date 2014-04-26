package com.example.TaskMan;

import org.json.JSONException;
import org.json.JSONObject;

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


    // TODO
    public JSONObject toJSON() {
        return null;
    }

    public User(JSONObject json) throws JSONException {
        mId = json.getInt(JSON_ID);
        mUsername = json.getString(JSON_USERNAME);
        mEmail = json.getString(JSON_EMAIL);
        if (json.has(JSON_FIRST_NAME)) {
            mFirstname = json.getString(JSON_FIRST_NAME);
        }
        if (json.has(JSON_LAST_NAME)) {
            mLastname = json.getString(JSON_LAST_NAME);
        }
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
