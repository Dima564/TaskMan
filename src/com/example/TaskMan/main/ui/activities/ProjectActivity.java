package com.example.TaskMan.main.ui.activities;

import android.app.*;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.TaskMan.Project;
import com.example.TaskMan.R;
import com.example.TaskMan.main.Commands;
import com.example.TaskMan.main.TaskManApplication;
import com.example.TaskMan.main.ui.fragments.TasksListFragment;

/**
 * Created by dima on 4/29/14.
 */
public class ProjectActivity extends Activity {
    private static final int DIALOG_NEW_TASK = 0;

    public static final String PROJECT_ID_EXTRA = "project";
    private Project mProject;
    private TextView mDescriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int projectId = getIntent().getIntExtra(PROJECT_ID_EXTRA,-1);
        if (projectId == -1) {
            //TODO Handle error
        }

        mProject = TaskManApplication.getCurrentUser().getProject(projectId);


        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().setTitle(mProject.getTitle());

        setContentView(R.layout.project_layout);


        mDescriptionTextView = (TextView) findViewById(R.id.project_body);
        mDescriptionTextView.setText(mProject.getBody());

        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new TasksListFragment();
            Bundle args = new Bundle();
            args.putInt(PROJECT_ID_EXTRA,projectId);
            fragment.setArguments(args);
            manager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DIALOG_NEW_TASK:
                final EditText titleEditText;
                final EditText bodyEditText;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.new_task_dialog,null);

                titleEditText = (EditText) v.findViewById(R.id.task_title);
                bodyEditText = (EditText) v.findViewById(R.id.task_body);

                builder.setView(v)
                        .setPositiveButton(R.string.create_task, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle args = new Bundle();
                                args.putString(Commands.addTask.TITLE_EXTRA,titleEditText.getText().toString());
                                args.putString(Commands.addTask.BODY_EXTRA,bodyEditText.getText().toString());
                                args.putInt(Commands.addTask.PROJECT_ID_EXTRA, mProject.getId());

                                new AddTask().execute(args);
//                                new Commands.addTask().execute(titleEditText.getText().toString(),bodyEditText.getText().toString());
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                return builder.create();



        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.project_options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.new_task_menu:
                showDialog(DIALOG_NEW_TASK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AddTask extends AsyncTask<Bundle,Void,Boolean> {
        @Override
        protected Boolean doInBackground(Bundle... params) {
            Boolean added= new Commands.addTask().execute(params[0]);
//            if (added) {
//            }
            return added;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            String state = result ? "Project added" : "Error has occurred";
//            Toast.makeText(ProjectListActivity.this,state,Toast.LENGTH_LONG);
//            updateUI();
        }
    }
}
