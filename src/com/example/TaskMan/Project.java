package com.example.TaskMan;

import android.app.ProgressDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by dima on 4/21/14.
 */
public class Project {

    public static final String JSON_AUTHOR = "author";
    public static final String JSON_TASKS = "tasks";
    public static final String JSON_ID = "id";
    public static final String JSON_TITLE = "title";
    public static final String JSON_BODY = "body";
    public static final String JSON_CREATED = "created";
    public static final String JSON_FOLLOWERS = "followers";

    private int mAuthor;
    private int mId;
    private String mTitle;
    private String mBody;

    // TODO Use Date instead
    private String mCreated;
    private ArrayList<Task> mTasks;
    private ArrayList<Integer> mFollowers;


    private Project() {

    }

    public void addTasks(JSONArray taskArray) {
        mTasks = Task.fromJSONTasks(taskArray);
    }

    public static Project fromJSON(JSONObject json) {

        Project p = new Project();
        p.mTasks = new ArrayList<Task>();
        p.mFollowers = new ArrayList<Integer>();


        try {
            p.mAuthor = json.getInt(JSON_AUTHOR);
            p.mId = json.getInt(JSON_ID);
            p.mTitle = json.getString(JSON_TITLE);
            p.mBody = json.getString(JSON_BODY);
            p.mCreated = json.getString(JSON_CREATED);
            JSONArray followersArray = (JSONArray) new JSONTokener(json.getString(JSON_FOLLOWERS)).nextValue();
            for (int i = 0; i < followersArray.length(); i++) {
                p.mFollowers.add(followersArray.getInt(i));
            }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return p;
    }





    public static ArrayList<Project> fromJSONProjects(String jsonString) {
        ArrayList<Project> projectList = new ArrayList<Project>();
        try {
            JSONArray array = (JSONArray) new JSONTokener(jsonString).nextValue();
            for (int i = 0; i < array.length(); i++) {
                    projectList.add(Project.fromJSON(array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<Project>();
        }


        return projectList;
    }

    public String getTitle() {
        return mTitle;
    }
}
