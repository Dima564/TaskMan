package com.example.TaskMan.main;

        import android.app.Application;
        import android.content.SharedPreferences;
        import com.example.TaskMan.User;

/**
 * Created by dima on 4/17/14.
 */
public class TaskManApplication extends Application {
    public static final String TAG = "TaskManApplication";
    public static final String PREFS_CREDENTIALS = "user_credentials";
    public static final String PREFS_USERNAME = "username";
    public static final String PREFS_PASSWORD = "password";
    private static User currentUser;
    private static SharedPreferences credentials;
    private static String username = "";
    private static String password = "";

    @Override
    public void onCreate() {
        super.onCreate();
        credentials = getSharedPreferences(PREFS_CREDENTIALS,MODE_PRIVATE);
//        deleteCredentials();
        updateCredentials();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        TaskManApplication.currentUser = currentUser;

    }

    // Encoded?
    public static void setCredentials(String username, String password) {
        SharedPreferences.Editor editor = credentials.edit();
        editor.putString(PREFS_USERNAME,username);
        editor.putString(PREFS_PASSWORD,password);
        editor.commit();
        updateCredentials();
    }

    private static void updateCredentials() {
        username = credentials.getString(PREFS_USERNAME,"");
        password = credentials.getString(PREFS_PASSWORD,"");
    }

    public static void deleteCredentials() {
        SharedPreferences.Editor editor = credentials.edit();
        editor.putString(PREFS_USERNAME,"");
        editor.putString(PREFS_PASSWORD,"");
        editor.commit();
        updateCredentials();
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
