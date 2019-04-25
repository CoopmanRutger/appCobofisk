package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Storedetails extends AppCompatActivity {


    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetails);

        backButton = findViewById(R.id.backToStore);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void goBack() {
        Intent intent = new Intent(this, Stores.class);
        startActivity(intent);
    }
}
