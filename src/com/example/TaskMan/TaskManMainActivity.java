package com.example.TaskMan;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

/**
 * Created by dima on 4/22/14.
 */
public class TaskManMainActivity extends Activity {
    private static final int DIALOG_NEW_PROJECT = 0;

    EditText titleEditText;
    EditText bodyEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FrameLayout fl = new FrameLayout(this);
        fl.setId(R.id.fragmentContainer);
        setContentView(fl);

        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null ) {
            fragment = new ProjectsFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.projects_optiopns_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.new_project:
                showDialog(DIALOG_NEW_PROJECT);
                return true;
            case R.id.log_out:
                TaskManApplication.deleteCredentials();
                Intent i = new Intent(TaskManMainActivity.this,LoginActivity.class);
                startActivity(i);
            default:
                 return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_NEW_PROJECT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.new_project_dialog,null);
                titleEditText = (EditText) v.findViewById(R.id.project_title);
                bodyEditText = (EditText) v.findViewById(R.id.project_description);

                builder.setView(v)
                        .setPositiveButton(R.string.create_project, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AddProject().execute(titleEditText.getText().toString(),bodyEditText.getText().toString());
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(TaskManMainActivity.this,"cancel",Toast.LENGTH_LONG).show();
                            }
                        });

                return builder.create();
            default:
                return super.onCreateDialog(id);
        }

    }


    private class AddProject extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            Boolean added= new Commands.addProject().execute(params[0],params[1]);
            if (added) {
                TaskManApplication.getCurrentUser().setProjects(new Commands.getProjects().execute());
            }
            return added;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            String state = result ? "Project added" : "Error has occurred";
            Toast.makeText(TaskManMainActivity.this,state,Toast.LENGTH_LONG);
            updateUI();
        }
    }

    private void updateUI() {
        FragmentManager manager = getFragmentManager();
        ListFragment fragment = (ListFragment) manager.findFragmentById(R.id.fragmentContainer);
        ((ProjectsFragment.ProjectsAdapter)fragment.getListAdapter()).notifyDataSetChanged();
    }
}
