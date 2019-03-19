package rutgercoopman.howest.oefening2;

public class Contact {
    private String lastName;
    private String firstName;
    private String tel;
    private String Email;
    private Address  address;


    public Contact(String lastName, String firstName, String tel, String email, Address address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.tel = tel;
        Email = email;
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", tel='" + tel + '\'' +
                ", Email='" + Email + '\'' +
                ", address=" + address +
                '}';
    }
}