package com.example.TaskMan;

import android.app.ProgressDialog;

import java.util.ArrayList;

/**
 * Created by dima on 4/21/14.
 */
public class Project {
    private String mTitle;
    private String mDescription;
    ArrayList<Task> mTasks;

    private Project() {

    }

    private Project(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public Project fromJSON(String json) {
        String title = "";
        String description = "";
        return new Project(title,description);
    }


}
