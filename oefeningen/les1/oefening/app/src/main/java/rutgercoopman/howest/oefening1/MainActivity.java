package rutgercoopman.howest.oefening1;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t1 = (TextView)findViewById(R.id.textView);
        TextView t2 = (TextView)findViewById(R.id.textView2);
        TextView t3 = (TextView)findViewById(R.id.textView3);


        t1.setText("Andriod version: " + Build.VERSION.RELEASE);
        t2.setText("Andriod SDK: " + Build.VERSION.SDK_INT);
        t3.setText("Manufacturer: " + Build.MANUFACTURER);


        TextView t4 = (TextView)findViewById(R.id.textView4);
        t4.setText("What is your name?");


        Button b1 = findViewById(R.id.btn_ok);
        b1.setText("OK");
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this.getIntent()));
                System.out.println("clicked");
                EditText ET1 = findViewById(R.id.editText);
                String name = String.valueOf(ET1.getText());
                System.out.println(name);

                TextView t5 = (TextView)findViewById(R.id.textView5);
                t5.setText("Hello " + name + "!");
            }
        });




    }

}
