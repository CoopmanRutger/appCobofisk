package rutgercoopman.howest.projectapp.repo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rutgercoopman.howest.projectapp.models.Product;

public class ProductsRepo extends Repository<Product> {
    @Override
    public List<Product> getItems() {
        try {
            String json = fetch("/products");
            System.out.println("json: " + json);
            return getProductsFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getItemById(int id) {
        try {
            String json = fetch("/products/" + id);
            return new ObjectMapper().readValue(json, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    static ArrayList<Product> getProductsFromJson(String json) {
        final String ID = "id";
        final String NAME = "name";
        final String BRAND = "brand";
        final String SIZE = "size";
        final String COLOR = "color";
        final String AMOUNTSTOCK = "amountStock";

        ArrayList<Product> products = new ArrayList<>();
        try {
            JSONArray arrayProducts = new JSONArray(json);
            int numberOfProducts = arrayProducts.length();
            for(int i = 0; i < numberOfProducts; i++) {
                JSONObject Json = arrayProducts.getJSONObject(i);
                int id = Json.getInt(ID);
                String name = Json.getString(NAME);
                String brand = Json.getString(BRAND);
                String size = Json.getString(SIZE);
                String color = Json.getString(COLOR);
                int amountStock = Json.getInt(AMOUNTSTOCK);


                Product product = new Product(id ,name ,size,brand,color,amountStock);
                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }

}
