package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Stores extends AppCompatActivity {

    Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        backbutton = findViewById(R.id.backToHome2);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void goBack() {
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
    }
}
