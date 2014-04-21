package com.example.TaskMan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dima on 4/17/14.
 */
public abstract class ServerFetcher {
    public static final String ENDPOINT = "http://54.187.34.1:8000/";



    /*
        Authorization string format: "username:password" in Base64
    */
    public String sendRequest(String autorization, String request, String method) throws IOException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization","Basic " + autorization);
        connection.setRequestMethod(method);

        // if method is not get, setDoOutput
        if (!method.equals("GET")) {
            connection.setDoOutput(true);
        }




    }
}
