package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Homescreen extends AppCompatActivity {

    Button logoutButton;
    Button deliverysButton;
    Button storesButton;
    Button employeesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        logoutButton = findViewById(R.id.logOutButton);
        deliverysButton = findViewById(R.id.deliverysButton);
        storesButton = findViewById(R.id.storesButton);
        employeesButton = findViewById(R.id.employeesButton);

        logoutButton.setText("Uitloggen");
        deliverysButton.setText("Bestelbonnen");
        storesButton.setText("Winkels");
        employeesButton.setText("Werknemers");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginScreen();
            }
        });
        deliverysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDeliverys();
            }
        });

        storesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStores();
            }
        });
        employeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmployees();
            }
        });


    }

    private void loginScreen(){
        Intent intent = new Intent(this,MainActivity.class );
        startActivity(intent);
    }
    private void goToDeliverys(){
        Intent intent = new Intent(this,Deliverys.class );
        startActivity(intent);
    }
    private void goToStores(){
        Intent intent = new Intent(this, Stores.class );
        startActivity(intent);
    }
    private void goToEmployees(){
        Intent intent = new Intent(this, Employees.class );
        startActivity(intent);
    }



}
