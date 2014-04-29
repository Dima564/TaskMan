package com.example.TaskMan.main;

/**
 * Created by dima on 4/18/14.
 */
public interface Command<T> {
    public T execute(Object ... params);
}
