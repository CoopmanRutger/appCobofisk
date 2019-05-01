package rutgercoopman.howest.projectapp.repo;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rutgercoopman.howest.projectapp.models.DeliveryNote;
import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.models.Product;
import rutgercoopman.howest.projectapp.models.Store;

public class StoresRepo extends  Repository<Store> {
    @Override
    public List<Store> getItems() {
        try {
            String json = fetch("/stores");
            System.out.println("json: " + json);
            return (List<Store>) new ObjectMapper().readValue(json, Store.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getItemById(int id) {
        try {
            String json = fetch("/stores/" + id);
            return new ObjectMapper().readValue(json, Store.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // TODO: 01/05/2019
    public List<Product> getProductsByStoreIdAsync(final int id) {
        try {
            return (new AsyncTask<Void, Void, List<Product>>() {
                protected List<Product> doInBackground(Void... objects) {
                    return getProductsByStoreId(id);
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: 01/05/2019
    public List<DeliveryNote> getDeliveryNotesByStoreIdAsync(final int id) {
        try {
            return (new AsyncTask<Void, Void, List<DeliveryNote>>() {
                protected List<DeliveryNote> doInBackground(Void... objects) {
                    return getDeliveryNotesByStoreId(id);
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: 01/05/2019
    public List<Employee> getEmployeesByStoreIdAsync(final int id) {
        try {
            return (new AsyncTask<Void, Void, List<Employee>>() {
                protected List<Employee> doInBackground(Void... objects) {
                    return getEmployeesByStoreId(id);
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<Product> getProductsByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/products");
            return (List<Product>) new ObjectMapper().readValue(json, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<DeliveryNote> getDeliveryNotesByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/deliveryNotes");
            return (List<DeliveryNote>) new ObjectMapper().readValue(json, DeliveryNote.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private List<Employee> getEmployeesByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/employees");
            return (List<Employee>) new ObjectMapper().readValue(json, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
