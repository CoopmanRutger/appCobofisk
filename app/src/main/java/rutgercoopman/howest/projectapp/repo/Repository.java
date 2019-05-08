package rutgercoopman.howest.projectapp.repo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.screens.Storedetails;

public abstract class Repository<T> {

    private String defaultDomain;

    private Repository(String defaultDomain) {
        this.defaultDomain = defaultDomain;
    }


    Repository() {
//    thuis
        this("http://192.168.0.251:3000");
        //    school ip
//                this("http://172.31.27.35:3000");
    }

    public abstract List<T> getItems();

    public abstract T getItemById(int id);


    @SuppressLint("StaticFieldLeak")
    public List<T> getItemsAsync() {
        try {
            return (new AsyncTask<Void, Void, List<T>>() {
                @Override
                protected List<T> doInBackground(Void... objects) {
                    return getItems();
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public T getItemByIdAsync(final int id) {
        try {
            return (new AsyncTask<Void, Void, T>() {
                @Override
                protected T doInBackground(Void... objects) {
                    return getItemById(id);
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    String fetch(String urlString) throws IOException {
        URL url = new URL(defaultDomain + urlString);
        
        URLConnection connection = url.openConnection();
        System.out.println("Made connection");
        InputStream inputStream = connection.getInputStream();
        System.out.println("Got inputstream");

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\Z");
        String out = scanner.next();
        scanner.close();
        return out;
    }

    // TODO: 01/05/2019
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String post(Employee employee) throws IOException, JSONException {
        URL url = new URL(defaultDomain + "/employees/add");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        JSONObject jsonObject = buildJsonObject(employee);
        setPostRequestContent(connection, jsonObject);
        connection.connect();

        return connection.getResponseMessage();
    }

    private JSONObject buildJsonObject(Employee employee) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", employee.name);
        jsonObject.put("age", employee.age);
        jsonObject.put("storeId", employee.storeId);
        jsonObject.put("duty", employee.duty);
        jsonObject.put("username", employee.username);
        jsonObject.put("password", employee.password);
        jsonObject.put("startedOn", employee.startedOn);

        return jsonObject;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setPostRequestContent(HttpURLConnection connection, JSONObject jsonObject) throws IOException {
        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

        writer.write(jsonObject.toString());
        Log.i(Storedetails.class.toString(), jsonObject.toString());

        writer.flush();
        writer.close();
        os.close();
    }
}