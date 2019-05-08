package rutgercoopman.howest.projectapp.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import rutgercoopman.howest.projectapp.R;
import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.repo.EmployeesRepo;

public class Addemployee extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);

        ConstraintLayout rl = findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        basicStuff();
    }

    @SuppressLint("SetTextI18n")
    private void basicStuff(){
        TextViewSet(R.id.textView2, "Werknemer toevoegen");
        TextViewSet(R.id.textName, "Naam");
        TextViewSet(R.id.textAge, "Leeftijd");
        TextViewSet(R.id.textJob, "Job");
        TextViewSet(R.id.textStore, "Store");
        TextViewSet(R.id.textUsername,"Username");
        TextViewSet(R.id.textPassword, "Password");

        Button backButton = findViewById(R.id.backToEmployee);
        backButton.setText("Terug");

        Button confirmAddEmployee = findViewById(R.id.addEmployee);
        confirmAddEmployee.setText("Bevestigen");

        Addspinners();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });

        confirmAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Form();
            }
        });
    }

    private  void Addspinners() {
        //todo dynamisch maken!
        SpinnersBuilder(R.id.editJob, new String[]{"Manager", "HR-manager", "werknemer", "tester", "student"});
        SpinnersBuilder(R.id.editStore, new String[]{"1", "2", "3", "4"});
    }

    private void Form(){
        String name = TextViewGet(R.id.editName);
        String username = TextViewGet(R.id.editUsername);
        String password = TextViewGet(R.id.editPassword);
        String ageText = TextViewGet(R.id.editAge);

        String job = SpinnerGet(R.id.editJob);
        String storeIdText = SpinnerGet(R.id.editStore);

        int age = Integer.parseInt(ageText);
        int storeId = Integer.parseInt(storeIdText);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        Employee employee = new Employee(0, storeId,  name, age, job, username, password, formatter.format(date));
        EmployeesRepo.instance.addEmployeeAsync(employee);


        GoBack();
    }

    private void GoBack() {
        Intent intent = new Intent(this, Employees.class);
        startActivity(intent);
    }

    private String SpinnerGet(int id) {
        Spinner spinner = findViewById(id);
        return spinner.getSelectedItem().toString();
    }

    private void SpinnersBuilder(int id, String[] items ) {
        Spinner spinner = findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    private String TextViewGet(int textId) {
        TextView textView = findViewById(textId);
        return textView.getText().toString();
    }

    private void TextViewSet(int id, String name) {
        TextView textView = findViewById(id);
        textView.setText(name);
    }
}
