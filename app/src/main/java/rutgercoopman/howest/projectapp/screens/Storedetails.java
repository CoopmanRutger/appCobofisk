package rutgercoopman.howest.projectapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rutgercoopman.howest.projectapp.models.DeliveryNote;
import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.models.Product;
import rutgercoopman.howest.projectapp.TestData.Invoices;
import rutgercoopman.howest.projectapp.models.Store;
import rutgercoopman.howest.projectapp.repo.StoresRepo;

public class Storedetails extends AppCompatActivity {

    TabLayout tabFull;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetails);

        basicStuff();


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabFull));
        tabFull.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static int storeId;

        public PlaceholderFragment() {
            storeId = Storedetails.number;
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                return fillInDetails(inflater, container);
            }

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                return fillInStock(inflater,container);

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                return fillInEmployees(inflater, container);

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 4) {
                return fillInDeliveryNotes(inflater,container);
            }
            return rootView;
        }

        private View fillInDetails( LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_details, container, false);

            System.out.println("id:  " + storeId);

            // TODO: 01/05/2019 fetch met id store
            StoresRepo storesRepo = new StoresRepo();
//            Store store = storesRepo.getItemById(storeId);
            Invoices invoices = new Invoices();
            Store[] data = invoices.getStores();
            Store store = data[storeId];

            // standaard
            TextView textTown = (TextView) rootView.findViewById(R.id.textTown);
            textTown.setText("Gemeente: ");
            TextView textZip = (TextView) rootView.findViewById(R.id.textZIP);
            textZip.setText("Postcode: ");
            TextView textStreet = (TextView) rootView.findViewById(R.id.textStreet);
            textStreet.setText("Straat: ");
            TextView textNumber = (TextView) rootView.findViewById(R.id.textNumber);
            textNumber.setText("Nr: ");
            TextView textAmountEmployees = (TextView) rootView.findViewById(R.id.textAmountEmployees);
            textAmountEmployees.setText("Aantal werknemers: ");
            TextView textAmountDeliverynotes = (TextView) rootView.findViewById(R.id.textAmountOfDeliveryNotes);
            textAmountDeliverynotes.setText("Aantal bestelbonnen: ");

            // fill in
            TextView textIdF = (TextView) rootView.findViewById(R.id.textIdFillIn);
            textIdF.setText(Integer.toString(store.id));
            TextView textView1 = (TextView) rootView.findViewById(R.id.textStoreNameFillIn);
            textView1.setText(store.name);
//            textView1.setText(store.name);
            TextView textTownF = (TextView) rootView.findViewById(R.id.textTownFillIn);
            textTownF.setText(store.town);
            TextView textZipF = (TextView) rootView.findViewById(R.id.textZIPFillIn);
            textZipF.setText(store.zip);
            TextView textStreetF = (TextView) rootView.findViewById(R.id.textStreetFillIn);
            textStreetF.setText(store.street);
            TextView textNumberF = (TextView) rootView.findViewById(R.id.textNumberFillIn);
            textNumberF.setText(Integer.toString(store.number));
            TextView textAmountEmployeesF = (TextView) rootView.findViewById(R.id.textAmountEmployeesFillIn);
            textAmountEmployeesF.setText(Integer.toString(store.amountEmployees));
            TextView textAmountDeliverynotesF = (TextView) rootView.findViewById(R.id.textAmountOfDeliveryNotesFillIn);
            textAmountDeliverynotesF.setText(Integer.toString(store.amountOfDeliveryNotes));

            // TODO: dynamische figuur

            ImageView img = new ImageView(rootView.getContext());
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.MATCH_PARENT), LinearLayout.LayoutParams.WRAP_CONTENT);
            if (storeId == 1) {
                img.setImageResource(R.drawable.bcc);
            }
            if (storeId == 2) {
                img.setImageResource(R.drawable.lidle);
            }
            if (storeId == 3) {
                img.setImageResource(R.drawable.action);
            }
            if (storeId == 4) {
                img.setImageResource(R.drawable.zeeman);
            }
            if (storeId == 5) {
                img.setImageResource(R.drawable.oxfam);
            }

            img.setLayoutParams(params);
            relativeLayout.addView(img);

            return rootView;
        }

        private View fillInStock(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_listview, container, false);
            ListView listView = rootView.findViewById(R.id.simpleListView);

            Invoices invoices = new Invoices();
            Product[] productList = invoices.getStocks();
            // TODO: 01/05/2019
            StoresRepo storesRepo = new StoresRepo();
            List<Product> stockList = storesRepo.getProductsByStoreIdAsync(storeId);

            List<String> list = new ArrayList<>();
            for (Product product : productList) {
                int id = product.id;
                String name = product.name;
                String size = product.size;
                String brand = product.brand;
                String color = product.color;
                int amount = product.amount;

                list.add(id + "\t\t\t\t\t\t\t\t\t" + name + "\t\t\t\t\t\t\t\t (" + size + ")"
                        + "\t\t\t\t\t\t aantal: " + amount + "\n\t\t\t\t\t\t\t\t\t\t\t" + brand
                        + "\t\t\t\t\t\t\t\t " + color);
            }
            ArrayAdapter<String> employeeArrayAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_store_listview, R.id.textViewEmployees , list );
            listView.setAdapter(employeeArrayAdapter);

            return rootView;
        }

        private View fillInEmployees(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_listview, container, false);

            ListView listView = rootView.findViewById(R.id.simpleListView);

            Invoices invoices = new Invoices();
            Employee[] employeesList = invoices.getEmployees();
            // TODO: 01/05/2019
            StoresRepo storesRepo = new StoresRepo();
            List<Employee> employeeList = storesRepo.getEmployeesByStoreIdAsync(storeId);

            List<String> list = new ArrayList<>();
            for (Employee employee : employeesList) {
                String name = employee.name;
                int age = employee.age;
                int id = employee.id;
                String duty = employee.duty;

                list.add(id + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + name + "\t(" + age + ") \t\t\t\t\t\t\t\t\t\t\t\t\t " + duty);
            }
            ArrayAdapter<String> employeeArrayAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_store_listview, R.id.textViewEmployees , list );
            listView.setAdapter(employeeArrayAdapter);

            return rootView;
        }

        private View fillInDeliveryNotes(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_listview, container, false);

            ListView listView = rootView.findViewById(R.id.simpleListView);

            Invoices invoices = new Invoices();
            DeliveryNote[] deliveryNoteList = invoices.getDeliveryNotes();
            // TODO: 30/04/2019 delivery notes layout
            StoresRepo storesRepo = new StoresRepo();
//            List<DeliveryNote> deliveryNoteList =  storesRepo.getDeliveryNotesByStoreIdAsync(storeId);

            List<String> list = new ArrayList<>();
            for (DeliveryNote deliveryNote : deliveryNoteList) {
                int id = deliveryNote.id;
                String status = deliveryNote.status;
                int amount = deliveryNote.amount;
                String date = deliveryNote.date;
                int productId = deliveryNote.productId;
                String extra = deliveryNote.extra;

                list.add(id + "\t\t\t\t\t\t\t\t\t\t\t\t [" + status + "]\t\t\t\t\t\t\t\t\t\t\t\t"
                        + date + "\n\t\t\t\t - " + productId + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  aantal: " + amount + "\n\t\t\t\t\t (" + extra + ")");
            }
            ArrayAdapter<String> deliveryNoteArrayAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_store_listview, R.id.textViewEmployees , list );
            listView.setAdapter(deliveryNoteArrayAdapter);

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return tabFull.getTabCount();
        }
    }

    private void basicStuff() {
        Intent intent = getIntent();
        int storeId = intent.getIntExtra("storeId", 0);
        String storeName = intent.getStringExtra("storeName");
        number = storeId;

        TextView title = findViewById(R.id.textView3);
        title.setText(storeName + " (nr:" + storeId + ")");

        Button backButton = findViewById(R.id.backToStore);
        backButton.setText("Terug");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        makeTabFull();
    }

    private void makeTabFull() {
        tabFull = findViewById(R.id.storeTab);
        tabFull.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabFull.setTabTextColors(Color.BLACK,Color.RED);
        makeTabs();
    }

    private void makeTabs() {
        TabLayout.Tab detailsTab = tabFull.newTab();
        TabLayout.Tab stockTab = tabFull.newTab();
        TabLayout.Tab employeesTab = tabFull.newTab();
        TabLayout.Tab deliveryTab = tabFull.newTab();

        nameTab(detailsTab, "Details", R.drawable.info);
        nameTab(stockTab, "Product", R.drawable.stock);
        nameTab(employeesTab, "Werknemers", R.drawable.employees);
        nameTab(deliveryTab, "Bestelbonnen", R.drawable.deliverynote);
    }

    private void nameTab(TabLayout.Tab tab, String string, int number) {
        tab.setText(string);
        tab.setIcon(number);
        tabFull.addTab(tab,true);
    }

    private void goBack() {
        Intent intent = new Intent(this, Stores.class);
        startActivity(intent);
    }
}
