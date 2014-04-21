package com.example.TaskMan;

/**
 * Created by dima on 4/21/14.
 */
public class Task {
    private String mTitle;
    private String mDescription;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }


    private Task() {

    }

    private Task(String title, String description)  {

    }

    public Task fromJSON(String json) {

        String title= "";
        String description = "";
        return new Task(title,description);
    }


}
