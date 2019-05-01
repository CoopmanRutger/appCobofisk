package rutgercoopman.howest.projectapp.repo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import rutgercoopman.howest.projectapp.models.Product;

public class ProductsRepo extends Repository<Product> {
    @Override
    public List<Product> getItems() {
        try {
            String json = fetch("/products");
            System.out.println("json: " + json);
            return (List<Product>) new ObjectMapper().readValue(json, Product.class);
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


}
