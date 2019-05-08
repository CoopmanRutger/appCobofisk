package rutgercoopman.howest.projectapp.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rutgercoopman.howest.projectapp.R;

public class Login_screen extends AppCompatActivity {


    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       basicStuff();
    }

    @SuppressLint("SetTextI18n")
    private void basicStuff() {
        ConstraintLayout rl = findViewById(R.id.background);
        rl.setBackgroundColor(getResources().getColor(R.color.colorRed));

        TextViewSet(R.id.usernameInput, "Username");
        TextViewSet(R.id.passwordInput, "Password");
        textView2 = TextViewSet(R.id.textWarning, "");

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setText("Login");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });
    }

    private void openHomeScreen() {
        String password = TextViewGet(R.id.passwordText);
        String username = TextViewGet(R.id.usernameText);

        if (password.equals("admin") && username.equals("admin")){
            GoTo();
        }
        else {
            warning();
        }
    }

    private void GoTo(){
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
    }

    private void warning() {
            textView2.setText("WARNING! \n Wrong username and/or password");
            textView2.setBackgroundColor(getResources().getColor(R.color.colorOrange));
            textView2.setGravity(Gravity.CENTER);
    }

    private String TextViewGet(int textId) {
        TextView textView = findViewById(textId);
        return textView.getText().toString();
    }

    private TextView TextViewSet(int id, String name) {
        TextView textView = findViewById(id);
        textView.setText(name);
        return textView;
    }
}

