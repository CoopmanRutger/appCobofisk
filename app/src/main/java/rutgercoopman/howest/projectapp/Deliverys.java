package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Deliverys extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverys);

        backButton = findViewById(R.id.backToHome3);
        backButton.setOnClickListener(new View.OnClickListener() {
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
