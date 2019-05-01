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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.models.Product;
import rutgercoopman.howest.projectapp.TestData.Invoices;

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
            // TODO: 30/04/2019 detail layout

            System.out.println("id:  " + storeId);
            // TODO: 01/05/2019 fetch met id store


            TextView textView = (TextView) rootView.findViewById(R.id.textStoreName);
            textView.setText("winkel: ");

            TextView textView1 = (TextView) rootView.findViewById(R.id.textStoreNameFillIn);
            textView1.setText("winkel naam");
            return rootView;
        }

        private View fillInStock(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_stock, container, false);
            ListView listView = rootView.findViewById(R.id.simpleListView);
            Invoices invoices = new Invoices();
            Product[] productList = invoices.getStocks();
            List<String> list = new ArrayList<>();

            for (Product product : productList) {
                int id = product.id;
                String name = product.name;
                String size = product.size;
                String brand = product.brand;
                String color = product.color;
                int amount = product.amount;

                list.add(id + "   " + name + "(" + size + ") " + brand + " " + color + " => " + amount);
            }

            ArrayAdapter<String> employeeArrayAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_store_stock, R.id.textViewStock , list );
            listView.setAdapter(employeeArrayAdapter);


            return rootView;
        }

        private View fillInEmployees(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_employees, container, false);

            ListView listView = rootView.findViewById(R.id.simpleListView);
            Invoices invoices = new Invoices();
            Employee[] employeesList = invoices.getEmployees();
            List<String> list = new ArrayList<>();

            for (Employee employee : employeesList) {
                String name = employee.name;
                int age = employee.age;
                int id = employee.id;
                String duty = employee.duty;

                list.add(id + "   " + name + "(" + age + ")  => " + duty);
            }
            ArrayAdapter<String> employeeArrayAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.fragment_store_employees, R.id.textViewEmployees , list );
            listView.setAdapter(employeeArrayAdapter);

            return rootView;
        }

        private View fillInDeliveryNotes(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_delivery_notes, container, false);

            // TODO: 30/04/2019 delivery notes layout

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
