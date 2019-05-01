package rutgercoopman.howest.projectapp.repo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

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

    public List<Product> getProductsByDeliveryNoteId(int id) {
        try {
            String json = fetch("/deliveryNotes/" + id + "/products");
            return (List<Product>) new ObjectMapper().readValue(json, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
