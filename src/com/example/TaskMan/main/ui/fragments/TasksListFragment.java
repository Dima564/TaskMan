package com.example.TaskMan.main.ui.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.TaskMan.R;
import com.example.TaskMan.Task;
import com.example.TaskMan.main.TaskManApplication;
import com.example.TaskMan.main.ui.activities.ProjectActivity;

import java.util.ArrayList;

/**
 * Created by dima on 4/29/14.
 */
public class TasksListFragment extends ListFragment {
    ArrayList<Task> mTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int projectId = getArguments().getInt(ProjectActivity.PROJECT_ID_EXTRA);

        mTasks = TaskManApplication.getCurrentUser().getProject(projectId).getTasks();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListAdapter(new TasksAdapter(getActivity(),R.layout.task_layout));

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public class TasksAdapter extends ArrayAdapter<Task> {
        Context c;
        public TasksAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            c = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Task t = mTasks.get(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.task_layout,parent,false);
            }

            ((TextView)convertView.findViewById(R.id.task_title)).setText(t.getTitle());
            ((TextView)convertView.findViewById(R.id.task_description)).setText(t.getBody());


            // TODO Check api update
//            ((TextView)convertView.findViewById(R.id.task_priority)).setText(t.getPriority());
            CheckBox checkBox  = (CheckBox)convertView.findViewById(R.id.task_completed_checkbox);
            checkBox.setEnabled(true);
            checkBox.setActivated(t.isCompleted());


            return convertView;
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }
    }
}
