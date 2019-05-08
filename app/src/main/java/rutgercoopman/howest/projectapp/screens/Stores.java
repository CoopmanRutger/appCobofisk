package rutgercoopman.howest.projectapp.screens;

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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import rutgercoopman.howest.projectapp.R;
import rutgercoopman.howest.projectapp.models.Store;
import rutgercoopman.howest.projectapp.repo.StoresRepo;

public class Stores extends AppCompatActivity {

    private TableLayout tableLayout;
    ProgressDialog progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        basicStuff();
        tableMaker();
    }

    private void tableMaker() {
        progressBar = new ProgressDialog(this);
        tableLayout = findViewById(R.id.storeTable);
        textView = findViewById(R.id.textView5);
        tableLayout.setStretchAllColumns(true);
        startLoadData();
    }

    private void startLoadData() {
        progressBar.setCancelable(false);
        progressBar.setMessage("Fetching Stores..");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
        new LoadDataTask().execute(0);
    }

    @SuppressLint({"SetTextI18n", "RtlHardcoded"})
    public void loadData() {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize, smallTextSize;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);

        List<Store> data = StoresRepo.instance.getItemsAsync();
        int rows = data.size();
        System.out.println(rows);
        textView.setText("winkels (" + String.valueOf(rows) + ")");
        TextView textSpacer;

        tableLayout.removeAllViews();

        for(int i = -1; i < rows; i ++) {
            Store row = null;
            if (i > -1) {
                String amountEmployees = String.valueOf(StoresRepo.instance.getEmployeesByStoreIdAsync(i+1).size());
                String amountDeliveryNotes = String.valueOf(StoresRepo.instance.getDeliveryNotesByStoreIdAsync(i+1).size());

                row = new Store(data.get(i).id, data.get(i).name, data.get(i).town,
                        data.get(i).postal_code, data.get(i).street, data.get(i).number,
                        amountEmployees, amountDeliveryNotes);
            }
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }
            final TextView tvId = new TextView(this);
            tvId.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tvId.setGravity(Gravity.CENTER);
            tvId.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tvId.setText(" \n Id nr");
                tvId.setBackgroundColor(Color.parseColor("#ba160C"));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvId.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tvId.setText(String.valueOf(row.id));
                tvId.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
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
                tvName.setText("\n winkelnaam");
                tvName.setBackgroundColor(Color.parseColor("#ba160C"));
            } else {
                tvName.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tvName.setTextColor(Color.parseColor("#000000"));
                tvName.setText(row.name);
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }

            final LinearLayout layAddress = new LinearLayout(this);
            layAddress.setOrientation(LinearLayout.VERTICAL);
            layAddress.setPadding(0, 10, 0, 10);
            layAddress.setBackgroundColor(Color.parseColor("#f8f8f8"));

            final TextView tvAddress = new TextView(this);
            if (i == -1) {
                tvAddress.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvAddress.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvAddress.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvAddress.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tvAddress.setGravity(Gravity.CENTER);
            tvAddress.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tvAddress.setText(" \n adres");
                tvAddress.setBackgroundColor(Color.parseColor("#ba160C"));
            }else {
                tvAddress.setBackgroundColor(Color.parseColor("#ffffff"));
                tvAddress.setTextColor(Color.parseColor("#000000"));
                tvAddress.setText(row.town + " (" + row.postal_code + ") ");
            }
            layAddress.addView(tvAddress);

            if (i > -1) {
                final TextView tvStreetAddress = new TextView(this);
                tvStreetAddress.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvStreetAddress.setGravity(Gravity.RIGHT);
                tvStreetAddress.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tvStreetAddress.setPadding(5, 1, 0, 5);
                tvStreetAddress.setTextColor(Color.parseColor("#aaaaaa"));
                tvStreetAddress.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tvStreetAddress.setText(row.street + " " + row.number);
                layAddress.addView(tvStreetAddress);
            }
            final LinearLayout layAmounts = new LinearLayout(this);
            layAmounts.setOrientation(LinearLayout.VERTICAL);
            layAmounts.setGravity(Gravity.RIGHT);
            layAmounts.setPadding(0, 10, 0, 10);
            layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));

            final TextView tvEmployees = new TextView(this);
            if (i == -1) {
                tvEmployees.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvEmployees.setPadding(5, 5, 0, 5);
                tvEmployees.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tvEmployees.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvEmployees.setPadding(5, 0, 0, 5);
                tvEmployees.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tvEmployees.setGravity(Gravity.CENTER);
            tvEmployees.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tvEmployees.setText("Aantal \n werknemers");
                tvEmployees.setBackgroundColor(Color.parseColor("#ba160C"));
            } else {
                tvEmployees.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tvEmployees.setTextColor(Color.parseColor("#000000"));
                tvEmployees.setText(row.amountOfEmployees);
                tvEmployees.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }

            final TextView tvDeliveryNotes = new TextView(this);
            if (i == -1) {
                tvDeliveryNotes.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvDeliveryNotes.setPadding(5, 5, 1, 5);
            } else {
                tvDeliveryNotes.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvDeliveryNotes.setPadding(5, 5, 1, 5);
            }

            tvDeliveryNotes.setGravity(Gravity.CENTER);
            tvDeliveryNotes.setPadding(5, 10, 0, 10);
            if (i == -1) {
                tvDeliveryNotes.setText("Aantal \n bestelbonnen");
                tvDeliveryNotes.setBackgroundColor(Color.parseColor("#ba160C"));
                tvDeliveryNotes.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }else {
                tvDeliveryNotes.setBackgroundColor(Color.parseColor("#ffffff"));
                tvDeliveryNotes.setTextColor(Color.parseColor("#000000"));
                tvDeliveryNotes.setText(row.amountOfDeliveryNotes);
                tvDeliveryNotes.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TableRow tr = new TableRow(this);
            tr.setId(i+1);
            tr.setClickable(true);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            tr.addView(tvId);
            tr.addView(tvName);
            tr.addView(layAddress);
            tr.addView(tvEmployees);
            tr.addView(tvDeliveryNotes);
            if (i > -1) {
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        TextView idColomn = (TextView) tr.getChildAt(0);
                        TextView nameColomn = (TextView) tr.getChildAt(1);
                        int id = Integer.parseInt(idColomn.getText().toString());
                        String storeName = nameColomn.getText().toString();

                        goToStoreDetails(id, storeName);
                    }
                });
            }
            tableLayout.addView(tr, trParams);

            if (i > -1) {
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

    private void goToStoreDetails(int id, String name) {
        Intent intent = new Intent(this, Storedetails.class);
        intent.putExtra("storeId", id);
        intent.putExtra("storeName", name);
        startActivity(intent);
    }

    // The params are dummy and not used
    @SuppressLint("StaticFieldLeak")
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
    private void basicStuff(){
        TextView textView = findViewById(R.id.textView5);
        textView.setText("Winkels");

        Button backbutton = findViewById(R.id.backToHome2);
        backbutton.setText("Terug");
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
