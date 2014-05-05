package com.example.TaskMan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dima on 4/21/14.
 */
public class Task {
//    public static final String JSON_PROJECT = "project";
    public static final String JSON_ID = "id";
    public static final String JSON_TITLE = "title";
    public static final String JSON_BODY = "body";
    public static final String JSON_CREATED = "created";
    public static final String JSON_COMPLETED = "completed";
    public static final String JSON_PRIORITY = "priority";

    private int mProjectId = -1;
    private int mId = -1;
    private String mTitle = "";
    private String mBody = "";
    private String mCreated = "";
    private Boolean mCompleted = false;


    private Task() {

    }


    public static Task fromJSON(JSONObject json) {
        Task t = new Task();
        try {
//            t.mProjectId = json.getInt(JSON_PROJECT);
            t.mId = json.getInt(JSON_ID);
            t.mTitle = json.getString(JSON_TITLE);
            t.mBody = json.getString(JSON_BODY);
            t.mCreated = json.getString(JSON_CREATED);
            t.mCompleted = json.getBoolean(JSON_COMPLETED);
        } catch (JSONException e) {
            return null;
        }

        return t;
    }

    public static ArrayList<Task> fromJSONTasks(JSONArray array) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            for (int i = 0; i < array.length(); i++) {
                tasks.add(Task.fromJSON(array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<Task>();
        }

        return tasks;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public String getCreated() {
        return mCreated;
    }

    public Boolean isCompleted() {
        return mCompleted;
    }

    public static JSONObject newTaskJSON(String title, String body, Boolean completed, Integer priority) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_TITLE,title);
        json.put(JSON_BODY,body);
        json.put(JSON_COMPLETED,completed);
        json.put(JSON_PRIORITY,priority);

        return json;
    }
}
