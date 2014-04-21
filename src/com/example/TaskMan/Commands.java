package com.example.TaskMan;

/**
 * Created by dima on 4/17/14.
 */
public abstract class Commands {


    // register user and return it in case of success
    // null in case of failure
    // TODO add error codes/ exceptions
    public class SignUp implements Command<String> {
        @Override
        public String execute(Object... params) {
            return null;
        }
    }



    // get all projects
    // - your projects
    // - projects you're involved in
    public class GetProjects implements Command<String> {
        @Override
        public String execute(Object... params) {
            return null;
        }
    }

    public class getTasks implements Command<String > {
        @Override
        public String execute(Object... params) {
            return null;
        }
    }

}
