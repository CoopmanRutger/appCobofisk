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
import rutgercoopman.howest.projectapp.models.Product;

public class DeliveryNotesRepo extends Repository<DeliveryNote> {

    public static final DeliveryNotesRepo instance = new DeliveryNotesRepo();

    @Override
    public List<DeliveryNote> getItems() {
        try {
            String json = fetch("/deliveryNotes");
            return getDeliveryNotesFromJson(json);
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
    @SuppressLint("StaticFieldLeak")
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
            return ProductsRepo.getProductsFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<DeliveryNote> getDeliveryNotesFromJson(String json) {
        final String ID = "id";
        final String STATUS = "status";
        final String EXTRA = "extra";
        final String PRODUCTID = "productId";
        final String AMOUNT = "amount";
        final String DATE = "date";

        ArrayList<DeliveryNote> deliveryNotes = new ArrayList<>();
        try {
            JSONArray arrayEmployees = new JSONArray(json);
            int numberOfEmployees = arrayEmployees.length();
            for(int i = 0; i < numberOfEmployees; i++) {
                JSONObject Json = arrayEmployees.getJSONObject(i);
                int id = Json.getInt(ID);
                String status = Json.getString(STATUS);
                String extra = Json.getString(EXTRA);
                int productId = Json.getInt(PRODUCTID);
                int amount = Json.getInt(AMOUNT);
                String date = Json.getString(DATE);

                DeliveryNote deliveryNote = new DeliveryNote(id,status , extra, productId, amount, date);
                deliveryNotes.add(deliveryNote);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deliveryNotes;
    }


}
