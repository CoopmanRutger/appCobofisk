package rutgercoopman.howest.projectapp.models;

public class Store {
    public int id;
    public String name;
    public int amountEmployees;
    public int amountOfDeliveryNotes;
    public String town;
    public String zip;
    public String street;
    public int number;

    public Store(int id, String name, int amountEmployees, int amountOfDeliveryNotes, String town, String zip, String street, int number) {
        this.id = id;
        this.name = name;
        this.amountEmployees = amountEmployees;
        this.amountOfDeliveryNotes = amountOfDeliveryNotes;
        this.town = town;
        this.zip = zip;
        this.street = street;
        this.number = number;
    }
}
