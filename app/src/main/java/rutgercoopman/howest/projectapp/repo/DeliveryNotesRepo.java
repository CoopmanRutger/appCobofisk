package rutgercoopman.howest.projectapp.repo;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rutgercoopman.howest.projectapp.models.DeliveryNote;
import rutgercoopman.howest.projectapp.models.Product;

public class DeliveryNotesRepo extends Repository<DeliveryNote> {
    @Override
    public List<DeliveryNote> getItems() {
        try {
            String json = fetch("/deliveryNotes");
            System.out.println("json: " + json);
            return (List<DeliveryNote>) new ObjectMapper().readValue(json, DeliveryNote.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public DeliveryNote getItemById(int id) {
        try {
            String json = fetch("/deliveryNotes/" + id);
            return new ObjectMapper().readValue(json, DeliveryNote.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // TODO: 01/05/2019
    public List<Product> getProductsByDeliveryNoteIdAsync(final int id) {
        try {
            return (new AsyncTask<Void, Void, List<Product>>() {
                protected List<Product> doInBackground(Void... objects) {
                    return getProductsByDeliveryNoteId(id);
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Product> getProductsByDeliveryNoteId(int id) {
        try {
            String json = fetch("/deliveryNotes/" + id + "/products");
            return (List<Product>) new ObjectMapper().readValue(json, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
