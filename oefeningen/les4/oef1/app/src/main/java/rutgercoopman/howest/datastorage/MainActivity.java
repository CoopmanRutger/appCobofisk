package rutgercoopman.howest.datastorage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private int color;
    private SharedPreferences sprf;
    private String sharedPrefFile;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        System.out.println(color);
//        sharedPrefFile = this.getString(R.string.preference_file_key);
//        sprf = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
//        color = sprf.getInt("colorSave", 0);
//        System.out.println(color);

        Button blackButton = (Button) findViewById(R.id.blackButton);
        blackButton.setText("Black");

        Button redButton = (Button) findViewById(R.id.redButton);
        redButton.setText("Red");

        Button yellowButton = (Button) findViewById(R.id.yellowButton);
        yellowButton.setText("yellow");

        Button blueButton = (Button) findViewById(R.id.blueButton);
        blueButton.setText("blue");


        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLACK;
                setBackgroundColor();
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.RED;
                setBackgroundColor();
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.YELLOW;
                setBackgroundColor();
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLUE;
                setBackgroundColor();
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

//        int colorSave = color;

        SharedPreferences.Editor editor = sprf.edit();
        editor.putInt("colorSave", color);
        editor.apply();
    }

//    @Override
//    protected  void onResetClick(View v) {
//        SharedPreferences.Editor editor = sprf.edit();
//        editor.clear();
//        editor.apply();
//    }

    private void setBackgroundColor() {
        View view = this.getWindow().getDecorView(); view.setBackgroundColor(color);
    }



}
