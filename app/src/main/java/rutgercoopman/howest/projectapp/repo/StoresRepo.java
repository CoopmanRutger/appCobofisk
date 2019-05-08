package rutgercoopman.howest.projectapp.repo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rutgercoopman.howest.projectapp.models.DeliveryNote;
import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.models.Product;
import rutgercoopman.howest.projectapp.models.Store;

public class StoresRepo extends  Repository<Store> {
    public static final StoresRepo instance = new StoresRepo();

    @Override
    public List<Store> getItems() {
        try {
            String json = fetch("/stores");
            System.out.println("json: " + json);
            return getStoresFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Store> getStoresFromJson(String json) {
        final String ID = "id";
        final String NAME = "name";
        final String ZIP = "postal code";
        final String TOWN = "town";
        final String STREET = "street";
        final String NUMBER = "number";

        ArrayList<Store> stores = new ArrayList<>();
        try {
            JSONArray arrayOrders = new JSONArray(json);
            int numberOfOrders = arrayOrders.length();
            for(int i = 0; i < numberOfOrders; i++) {
                JSONObject Json = arrayOrders.getJSONObject(i);
                int id = Json.getInt(ID);
                String name = Json.getString(NAME);
                String zip = Json.getString(ZIP);
                String town = Json.getString(TOWN);
                String street = Json.getString(STREET);
                int number = Json.getInt(NUMBER);
                Store store = new Store(id,name,zip,town,street,number,"","" );
                stores.add(store);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
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


    @SuppressLint("StaticFieldLeak")
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

    @SuppressLint("StaticFieldLeak")
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

    @SuppressLint("StaticFieldLeak")
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
            return ProductsRepo.getProductsFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<DeliveryNote> getDeliveryNotesByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/deliveryNotes");
            return DeliveryNotesRepo.getDeliveryNotesFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private List<Employee> getEmployeesByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/employees");
            return EmployeesRepo.getEmployeesFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
