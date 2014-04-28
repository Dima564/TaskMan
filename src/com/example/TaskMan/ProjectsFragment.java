package com.example.TaskMan;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by dima on 4/28/14.
 */
public class ProjectsFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ProjectsAdapter(getActivity().getApplicationContext(),R.layout.project_icon));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
}
