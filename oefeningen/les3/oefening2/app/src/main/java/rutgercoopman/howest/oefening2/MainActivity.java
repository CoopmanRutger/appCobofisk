package rutgercoopman.howest.oefening2;


import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titel = (TextView) findViewById(R.id.textView1);
        TextView content = (TextView) findViewById(R.id.content);
        titel.setText("ContactPerson");
        content.setText("blabla");

        Button addContact = findViewById(R.id.addContactPerson);
        addContact.setText("add Contact");
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddContactPerson();
            }
        });
    }

    public void openAddContactPerson(){
        Intent intent = new Intent(this, AddContactPerson.class);
        startActivity(intent);
    }
}