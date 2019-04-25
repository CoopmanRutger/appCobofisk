package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Employees extends AppCompatActivity {

    Button backButton;
    Button addEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        backButton = findViewById(R.id.backToHome);
        addEmployeeButton = findViewById(R.id.addEmployee);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddEmployee();
            }
        });

    }

    private void goBack() {
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
    }

    private void goToAddEmployee() {
        Intent intent = new Intent(this, Addemployee.class);
        startActivity(intent);
    }
}
