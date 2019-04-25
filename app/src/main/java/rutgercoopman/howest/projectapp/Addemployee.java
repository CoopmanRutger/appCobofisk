package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Addemployee extends AppCompatActivity {

    Button backButton;
    Button confirmAddEmployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);


        ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        TextView textView = findViewById(R.id.textView2);
        textView.setText("Werknemer toevoegen");


        backButton = findViewById(R.id.backToEmployee);
        backButton.setText("Terug");

        confirmAddEmployee = findViewById(R.id.addEmployee);
        confirmAddEmployee.setText("Bevestigen");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });


        Spinner dropdown = findViewById(R.id.editJob);
        String[] items = new String[]{"Manager", "HR-manager", "werknemer", "tester", "student"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown1 = findViewById(R.id.editStore);
        String[] items1 = new String[]{"winkel1", "winkel2", "winkel3", "winkel4"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

    }

    private void goBack() {
        Intent intent = new Intent(this, Employees.class);
        startActivity(intent);
    }



}
