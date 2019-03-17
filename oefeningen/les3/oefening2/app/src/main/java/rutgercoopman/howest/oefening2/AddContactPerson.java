package rutgercoopman.howest.oefening2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddContactPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_person);

        TextView titel = (TextView)findViewById(R.id.title);
        titel.setText("Add a new contact person");

        TextView lastName = (TextView)findViewById(R.id.lastName);
        TextView firstName = (TextView)findViewById(R.id.firstName);
        TextView phoneNumber = (TextView)findViewById(R.id.phoneNumber);
        TextView email = (TextView)findViewById(R.id.email);
        TextView address = (TextView)findViewById(R.id.address);


        lastName.setText("Enter last name");
        firstName.setText("Enter first name");
        phoneNumber.setText("Enter phone number");
        email.setText("Enter e-mail");
        address.setText("address");

        EditText editLastName = (EditText)findViewById(R.id.editLastName);
        EditText editFirstName = (EditText)findViewById(R.id.editFirstName);
        EditText editPhoneNumber = (EditText)findViewById(R.id.editPhoneNumber);
        EditText editEmail = (EditText)findViewById(R.id.editEmail);
        EditText editStreet = (EditText)findViewById(R.id.editStreet);
        EditText editNumber = (EditText)findViewById(R.id.editNumber);

        Address address1 = new Address(editStreet.getText().toString(), Integer.parseInt(editNumber.getText().toString()));
        String lastname = editLastName.getText().toString();
        String firstname = editFirstName.getText().toString();
        String phoneNumber1 = editPhoneNumber.getText().toString();
        String email1 = editEmail.getText().toString();

        Contact contact = new Contact(lastname, firstname,phoneNumber1,email1, address1);

        System.out.println(contact);
    }
}