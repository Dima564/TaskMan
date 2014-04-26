package com.example.TaskMan;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by dima on 4/22/14.
 */
public class TaskManMainActivity extends FragmentActivity {
    private TextView mUsername;
    private TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mUsername = (TextView) findViewById(R.id.username_TextView);
        mEmail = (TextView) findViewById(R.id.email_TextView);

        mUsername.setText(TaskManApplication.getCurrentUser().getUsername());
        mEmail.setText(TaskManApplication.getCurrentUser().getEmail());
    }
}
