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
            // data columns
            final TextView tvId = new TextView(this);
            tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tvId.setGravity(Gravity.CENTER);

            tvId.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tvId.setText("Id nr");
                tvId.setBackgroundColor(Color.parseColor("#ba160C"));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvId.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tvId.setText(String.valueOf(row.id));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TextView tvDuty = new TextView(this);
            if (i == -1) {
                tvDuty.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvDuty.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvDuty.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvDuty.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tvDuty.setGravity(Gravity.CENTER);

            tvDuty.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tvDuty.setText("Functie");
                tvDuty.setBackgroundColor(Color.parseColor("#ba160C"));
            }else {
                tvDuty.setBackgroundColor(Color.parseColor("#ffffff"));
                tvDuty.setTextColor(Color.parseColor("#000000"));
                tvDuty.setText(row.duty);
            }

            final TextView tvName = new TextView(this);
            if (i == -1) {
                tvName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvName.setPadding(5, 5, 0, 5);
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvName.setPadding(5, 0, 0, 5);
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tvName.setGravity(Gravity.CENTER);
            tvName.setPadding(5, 10, 0, 10);

            if (i == -1) {
                tvName.setText("naam (leeftijd)");
                tvName.setBackgroundColor(Color.parseColor("#ba160C"));
            } else {
                tvName.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tvName.setTextColor(Color.parseColor("#000000"));
                tvName.setText(row.name + " (" + row.age + ")");
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }

            final TextView tvUsername = new TextView(this);
            if (i == -1) {
                tvUsername.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvUsername.setPadding(5, 5, 1, 5);

            } else {
                tvUsername.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvUsername.setPadding(5, 5, 1, 5);
            }

            tvUsername.setGravity(Gravity.CENTER);
            tvUsername.setPadding(5, 10, 0, 10);

            if (i == -1) {
                tvUsername.setText("Username");
                tvUsername.setBackgroundColor(Color.parseColor("#ba160C"));
                tvUsername.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }else {
                tvUsername.setBackgroundColor(Color.parseColor("#ffffff"));
                tvUsername.setTextColor(Color.parseColor("#000000"));
                tvUsername.setText(row.username);
                tvUsername.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TextView tvStartedOn = new TextView(this);
            if (i == -1) {
                tvStartedOn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvStartedOn.setPadding(5, 5, 1, 5);
            } else {
                tvStartedOn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvStartedOn.setPadding(5, 0, 1, 5);
            }

            tvStartedOn.setGravity(Gravity.CENTER);
            tvStartedOn.setPadding(5, 10, 0, 10);

            if (i == -1) {
                tvStartedOn.setText("Begonnen op");
                tvStartedOn.setBackgroundColor(Color.parseColor("#ba160C"));
                tvStartedOn.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvStartedOn.setBackgroundColor(Color.parseColor("#ffffff"));
                tvStartedOn.setTextColor(Color.parseColor("#000000"));
                tvStartedOn.setText(row.startedOn);
                tvStartedOn.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            tr.addView(tvId);
            tr.addView(tvName);
            tr.addView(tvDuty);
            tr.addView(tvUsername);
            tr.addView(tvStartedOn);
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
