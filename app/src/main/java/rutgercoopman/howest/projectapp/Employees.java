package rutgercoopman.howest.projectapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.Arrays;

import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.repo.EmployeesRepo;
import rutgercoopman.howest.projectapp.table.Invoices;

public class Employees extends AppCompatActivity {

    private TableLayout tableLayout;
    ProgressDialog progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        basicStuff();
        tableMaker();
    }


    private void tableMaker(){
        progressBar = new ProgressDialog(this);
        tableLayout = (TableLayout) findViewById(R.id.employeeTable);
        tableLayout.setStretchAllColumns(true);
        startLoadData();
    }

    private void startLoadData() {
        progressBar.setCancelable(false);
        progressBar.setMessage("Fetching Employees..");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
        new LoadDataTask().execute(0);
    }

    public void loadData() {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);

        Invoices employees = new Invoices();
        Employee[] data = employees.getEmployees();
//        System.out.println(Arrays.toString(EmployeesRepo.instance.getItems().toArray()));
//        Employee[] data = (Employee[]) EmployeesRepo.instance.getItems().toArray();


        int rows = data.length;
        textView.setText("Werknemers (" + String.valueOf(rows) + ")");
        TextView textSpacer = null;

        tableLayout.removeAllViews();
        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            Employee row = null;
            if (i > -1)
            row = data[i];
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");

            }
//            // data columns
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tv.setGravity(Gravity.CENTER);

            tv.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tv.setText("Id nr");
                tv.setBackgroundColor(Color.parseColor("#ba160C"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText(String.valueOf(row.id));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

//
            final TextView tv2 = new TextView(this);
            if (i == -1) {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv2.setGravity(Gravity.CENTER);

            tv2.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tv2.setText("Functie");
                tv2.setBackgroundColor(Color.parseColor("#ba160C"));
            }else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(row.duty);
            }

            final TextView tv3 = new TextView(this);
            if (i == -1) {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 5, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 0, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv3.setGravity(Gravity.CENTER);
            tv3.setPadding(5, 10, 0, 10);

            if (i == -1) {
                tv3.setText("naam (leeftijd)");
                tv3.setBackgroundColor(Color.parseColor("#ba160C"));
            } else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setText(row.name + " (" + row.age + ")");
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }

            final TextView tv3b = new TextView(this);
            if (i == -1) {
                tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv3b.setPadding(5, 5, 1, 5);

            } else {
                tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3b.setPadding(5, 5, 1, 5);
            }

            tv3b.setGravity(Gravity.CENTER);
            tv3b.setPadding(5, 10, 0, 10);

            if (i == -1) {
                tv3b.setText("Username");
                tv3b.setBackgroundColor(Color.parseColor("#ba160C"));
                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }else {
                tv3b.setBackgroundColor(Color.parseColor("#ffffff"));
                tv3b.setTextColor(Color.parseColor("#000000"));
                tv3b.setText(row.username);
                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TextView tv4 = new TextView(this);
            if (i == -1) {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setPadding(5, 5, 1, 5);
            } else {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setPadding(5, 0, 1, 5);
            }

            tv4.setGravity(Gravity.CENTER);
            tv4.setPadding(5, 10, 0, 10);

            if (i == -1) {
                tv4.setText("Begonnen op");
                tv4.setBackgroundColor(Color.parseColor("#ba160C"));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv4.setText(row.startedOn);
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv3b);
            tr.addView(tv4);
            if (i > -1) {
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        //do whatever action is needed
                    }
                });
            }
            tableLayout.addView(tr, trParams);

            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);
                tableLayout.addView(trSep, trParamsSep);
            }
        }
    }

        // The params are dummy and not used
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.hide();
            loadData();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }

    @SuppressLint("SetTextI18n")
    private void basicStuff() {
        Button backButton = findViewById(R.id.backToHome);
        backButton.setText("Terug");
        Button addEmployeeButton = findViewById(R.id.addEmployee);
        addEmployeeButton.setText("Toevoegen");

        textView = findViewById(R.id.textView6);
        textView.setText("Werknemers");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddEmployee();
            }
        });


    }

    private void goBack() {
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
    }

    private void goToAddEmployee() {
        Intent intent = new Intent(this, Addemployee.class);
        startActivity(intent);
    }
}
