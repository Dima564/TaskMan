package com.example.TaskMan;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dima on 4/17/14.
 */
public abstract class ServerFetcher {
    public static final String ENDPOINT = "http://54.187.34.1:8000/";

    public static final String TAG = "ServerFetcher";

    /*
        Authorization string format: "username:password" in Base64
    */

    public static int post(String authorization, String request, String body) throws IOException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization","Basic " + authorization);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream out = connection.getOutputStream();
        out.write(body.getBytes());
        out.close();

        return connection.getResponseCode();
    }



    public static int delete(String authorization,String request) throws IOException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization","Basic " + authorization);
        connection.setRequestMethod("DELETE");
        return connection.getResponseCode();
    }



    public static String get(String authorization, String request) throws IOException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization","Basic " + authorization);
        connection.setRequestMethod("GET");

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        InputStream in = connection.getInputStream();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            return null;
        }

        int bytesRead = 0;
        byte[] buffer = new byte[1024];

        while((bytesRead = in.read(buffer)) > 0) {
            o.write(buffer,0,bytesRead);
        }
        o.close();

        return o.toString();
    }


}





