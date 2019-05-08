package rutgercoopman.howest.projectapp.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    public int id;
    public String name;
    public String size;
    public String brand;
    public String color;
    public int amount;

    @JsonCreator
    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name,
                   @JsonProperty("size") String size, @JsonProperty("brand") String brand,
                   @JsonProperty("color") String color, @JsonProperty("amountStock") int amount) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.brand = brand;
        this.color = color;
        this.amount = amount;
    }
}
