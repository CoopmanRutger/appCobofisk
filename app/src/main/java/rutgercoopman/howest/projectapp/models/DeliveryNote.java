package rutgercoopman.howest.projectapp.models;

public class DeliveryNote {
    public int id;
    public int storeId;
    public String status;
    public String extra;
    public int productId;
    public int amount;
    public String date;

    public DeliveryNote(int id, int storeId, String status, String extra, int productId, int amount, String date) {
        this.id = id;
        this.storeId = storeId;
        this.status = status;
        this.extra = extra;
        this.productId = productId;
        this.amount = amount;
        this.date = date;
    }
}
