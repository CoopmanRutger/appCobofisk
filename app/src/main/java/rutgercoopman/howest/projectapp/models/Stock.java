package rutgercoopman.howest.projectapp.models;

public class Stock {

    public int id;
    public String name;
    public String size;
    public String brand;
    public String color;
    public int amount;


    public Stock(int id, String name, String size, String brand, String color, int amount) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.brand = brand;
        this.color = color;
        this.amount = amount;
    }
}
