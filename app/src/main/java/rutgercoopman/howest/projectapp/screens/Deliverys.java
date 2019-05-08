package rutgercoopman.howest.projectapp.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import rutgercoopman.howest.projectapp.R;
import rutgercoopman.howest.projectapp.models.Product;
import rutgercoopman.howest.projectapp.repo.DeliveryNotesRepo;

public class Deliverys extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverys);

        basicStuff();

        List<Product> productList = DeliveryNotesRepo.instance.getProductsByDeliveryNoteIdAsync(1);
        System.out.println(productList);
        // TODO: 01/05/2019
}

    @SuppressLint("SetTextI18n")
    private void basicStuff() {
        TextView textView = findViewById(R.id.textView4);
        textView.setText("Bestelbonnen");

        Button backButton = findViewById(R.id.backToHome3);
        backButton.setText("Terug");
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
