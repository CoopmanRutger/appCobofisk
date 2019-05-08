package rutgercoopman.howest.projectapp.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Store {
    public int id;
    public String name;
    public String town;
    public String postal_code;
    public String street;
    public int number;
    public String amountOfEmployees;
    public String amountOfDeliveryNotes;

    @JsonCreator
    public Store(@JsonProperty("id") int id, @JsonProperty("name") String name,
                 @JsonProperty("postal code") String zip, @JsonProperty("town") String town,
                 @JsonProperty("street") String street, @JsonProperty("number") int number,
                 @JsonProperty("created_at") String amountOfEmployees,
                 @JsonProperty("updated_at") String amountOfDeliveryNotes) {
        this.id = id;
        this.name = name;
        this.postal_code = zip;
        this.town = town;
        this.street = street;
        this.number = number;
        this.amountOfEmployees = amountOfEmployees;
        this.amountOfDeliveryNotes = amountOfDeliveryNotes;
    }
}
