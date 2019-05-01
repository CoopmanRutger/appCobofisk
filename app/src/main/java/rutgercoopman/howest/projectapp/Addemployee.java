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

    }

    private void basicStuff(){
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Werknemer toevoegen");

        TextView textName = findViewById(R.id.textName);
        textName.setText("Naam");

        TextView textAge = findViewById(R.id.textAge);
        textAge.setText("Leeftijd");

        TextView textJob = findViewById(R.id.textJob);
        textJob.setText("Job");

        TextView textStore = findViewById(R.id.textStore);
        textStore.setText("Store");


        TextView textUsername = findViewById(R.id.textUsername);
        textUsername.setText("Username");


        TextView textPassword = findViewById(R.id.textPassword);
        textPassword.setText("Password");

        Button backButton = findViewById(R.id.backToEmployee);
        backButton.setText("Terug");

        Button confirmAddEmployee = findViewById(R.id.addEmployee);
        confirmAddEmployee.setText("Bevestigen");

        spinners();

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

    private  void spinners() {
        //dynamisch maken!
        Spinner dropdown = findViewById(R.id.editJob);
        String[] items = new String[]{"Manager", "HR-manager", "werknemer", "tester", "student"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown1 = findViewById(R.id.editStore);
        String[] items1 = new String[]{"id:1 - winkelnaam", "id2: winkelnaam2", "id:3 winkelnaam", "id:4 winkelnaam"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

    }

    private void form(){

        TextView editName = findViewById(R.id.editName);
        String name = editName.getText().toString();

        TextView editAge = findViewById(R.id.editAge);
        String age = editAge.getText().toString();

        Spinner editJob = findViewById(R.id.editJob);
        String job = editJob.getSelectedItem().toString();

        Spinner editStore = findViewById(R.id.editStore);
        String store = editStore.getSelectedItem().toString();

        TextView editUsername = findViewById(R.id.editUsername);
        String username = editUsername.getText().toString();

        TextView editPassword = findViewById(R.id.editPassword);
        String password = editPassword.getText().toString();


        System.out.println(name + " (" + age + ") \n" + store + " -> "+ job +
                " \n(username: " + username + "   password:" + password );
    }

    private void goBack() {
        Intent intent = new Intent(this, Employees.class);
        startActivity(intent);
    }



}
