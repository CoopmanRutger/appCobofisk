package rutgercoopman.howest.projectapp.repo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

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

    public List<Product> getProductsByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/products");
            return (List<Product>) new ObjectMapper().readValue(json, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DeliveryNote> getDeliveryNotesByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/deliveryNotes");
            return (List<DeliveryNote>) new ObjectMapper().readValue(json, DeliveryNote.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Employee> getEmployeesByStoreId(int id) {
        try {
            String json = fetch("/stores/" + id + "/employees");
            return (List<Employee>) new ObjectMapper().readValue(json, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
