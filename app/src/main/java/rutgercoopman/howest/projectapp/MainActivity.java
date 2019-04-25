package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        TextView textView = findViewById(R.id.usernameInput);
        textView.setText("Username");
        TextView textView1 = findViewById(R.id.passwordInput);
        textView1.setText("Password");


        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setText("Login");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });




    }

    private void openHomeScreen() {
        TextView passwordView = findViewById(R.id.passwordText);
        TextView usernameView = findViewById(R.id.usernameText);
        String password = passwordView.getText().toString();
        String username = usernameView.getText().toString();

//        if (password.equals("test") && username.equals("test")){
            Intent intent = new Intent(this, Homescreen.class);
            startActivity(intent);
//        }
//        else {
//            System.out.println("nope");
//        }

    }
}

