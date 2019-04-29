package rutgercoopman.howest.projectapp.repo;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public abstract class Repository<T> {

    private String defaultDomain;

    public Repository(String defaultDomain) {
        this.defaultDomain = defaultDomain;
    }

    public Repository() {
        this("http://10.0.0.2:3000");
    }

    public abstract List<T> getItems();

    public abstract T getItemById(int id);

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
}