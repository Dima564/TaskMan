package com.example.TaskMan.main.ui.fragments;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.TaskMan.Project;
import com.example.TaskMan.R;
import com.example.TaskMan.main.Commands;
import com.example.TaskMan.main.TaskManApplication;
import com.example.TaskMan.main.ui.activities.ProjectActivity;

/**
 * Created by dima on 4/28/14.
 */
public class ProjectsListFragment extends ListFragment {

    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ProjectsAdapter(getActivity().getApplicationContext(), R.layout.project_icon));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListView taskList = new ListView(getActivity());
        taskList.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        taskList.setId(R.id.list);



        return taskList;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        // get get get get
        new openProject().execute(TaskManApplication.getCurrentUser().getProjects().get(position).getId());
        super.onListItemClick(l, v, position, id);
    }

    public class ProjectsAdapter extends ArrayAdapter<Project> {
        Context c;


        public ProjectsAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            c = context;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.project_icon,parent,false);
            }
            ((TextView)convertView.findViewById(R.id.project_title))
                    .setText(TaskManApplication.getCurrentUser().getProjects().get(position).getTitle());
            return convertView;
        }

        @Override
        public int getCount() {
            return TaskManApplication.getCurrentUser().getProjects().size();
        }


    }

    private class openProject extends AsyncTask<Integer,Void,Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading... ");
            dialog.setMessage("Loading tasks. Please, wait");
            dialog.show();

        }
        @Override
        protected Integer doInBackground(Integer... params) {


            new Commands.getTasksForProject().execute(params[0]);

            return params[0];
        }

        @Override
        protected void onPostExecute(Integer projectId) {
            Intent i = new Intent(getActivity(), ProjectActivity.class);
            i.putExtra(ProjectActivity.PROJECT_ID_EXTRA, projectId);
            startActivity(i);

            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
