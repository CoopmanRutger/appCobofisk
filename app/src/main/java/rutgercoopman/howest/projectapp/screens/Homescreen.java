package rutgercoopman.howest.projectapp.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import rutgercoopman.howest.projectapp.R;

public class Homescreen extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ConstraintLayout rl = findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        ButtonSet(R.id.logOutButton, "Uitloggen", Login_screen.class);
        ButtonSet(R.id.deliverysButton, "Bestelbonnen", Deliverys.class);
        ButtonSet(R.id.storesButton, "Winkels", Stores.class);
        ButtonSet(R.id.employeesButton, "Werknemers", Employees.class);
    }

    private void ButtonSet(int id, String name, final Class<?> goTo) {
        Button button = findViewById(id);
        button.setText(name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoTo(goTo);
            }
        });
    }

    private void GoTo(Class<?> goTo){
        Intent intent = new Intent(this, goTo);
        startActivity(intent);
    }
}
