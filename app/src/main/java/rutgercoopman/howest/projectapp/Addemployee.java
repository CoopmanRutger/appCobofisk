package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Addemployee extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);


        ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        basicStuff();

        Spinner dropdown = findViewById(R.id.editJob);
        String[] items = new String[]{"Manager", "HR-manager", "werknemer", "tester", "student"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown1 = findViewById(R.id.editStore);
        String[] items1 = new String[]{"winkel1", "winkel2", "winkel3", "winkel4"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

    }

    private void basicStuff(){
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Werknemer toevoegen");

        TextView textUsername = findViewById(R.id.textName);
        textUsername.setText("naam");

        TextView textAge = findViewById(R.id.textAge);
        textAge.setText("leeftijd");

        TextView textJob = findViewById(R.id.textJob);
        textJob.setText("Job");

        Button backButton = findViewById(R.id.backToEmployee);
        backButton.setText("Terug");

        Button confirmAddEmployee = findViewById(R.id.addEmployee);
        confirmAddEmployee.setText("Bevestigen");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        confirmAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form();
            }
        });

    }

    private void form(){

        TextView editName = findViewById(R.id.editName);
        String name = editName.getText().toString();
        System.out.println(name);
    }

    private void goBack() {
        Intent intent = new Intent(this, Employees.class);
        startActivity(intent);
    }



}
