package rutgercoopman.howest.projectapp.repo;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public abstract class EmployeesRepo<T> {

    private String defaultDomain;

    public GenericRepo(String defaultDomain) {
        this.defaultDomain = defaultDomain;
    }

    public EmployeesRepo() {
        this("http://localhost");
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

        InputStream inputStream = connection.getInputStream();

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\Z");
        String out = scanner.next();
        scanner.close();
        return out;
    }
}