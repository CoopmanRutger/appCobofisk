package rutgercoopman.howest.projectapp.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryNote {
    public int id;
    public String status;
    public String extra;
    public int productId;
    public int amount;
    public String date;

    @JsonCreator
    public DeliveryNote(@JsonProperty("id") int id, @JsonProperty("status") String status,
                        @JsonProperty("extra") String extra,
                        @JsonProperty("productId") int productId,
                        @JsonProperty("amount") int amount, @JsonProperty("date") String date) {
        this.id = id;
        this.status = status;
        this.extra = extra;
        this.productId = productId;
        this.amount = amount;
        this.date = date;
    }
}
