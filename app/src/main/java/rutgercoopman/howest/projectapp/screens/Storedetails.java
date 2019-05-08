package rutgercoopman.howest.projectapp.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
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

import rutgercoopman.howest.projectapp.R;
import rutgercoopman.howest.projectapp.models.DeliveryNote;
import rutgercoopman.howest.projectapp.models.Employee;
import rutgercoopman.howest.projectapp.models.Product;
import rutgercoopman.howest.projectapp.models.Store;
import rutgercoopman.howest.projectapp.repo.StoresRepo;

public class Storedetails extends AppCompatActivity {

    TabLayout tabFull;
    public static int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetails);

        BasicStuff();

        // TODO: 01/05/2019   mooiere layout!
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = findViewById(R.id.container);
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
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            assert getArguments() != null;
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
            return null;
        }

        @SuppressLint("SetTextI18n")
        private View fillInDetails(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_details, container, false);

            Store store = StoresRepo.instance.getItemByIdAsync(storeId);
            int amountEmployees = StoresRepo.instance.getEmployeesByStoreIdAsync(storeId).size();
            int amountDeliveryNotes = StoresRepo.instance.getDeliveryNotesByStoreIdAsync(storeId).size();

            // standaard
            TextViewSet(R.id.textTown, "Gemeente: ", rootView);
            TextViewSet(R.id.textZIP, "Postcode: ", rootView);
            TextViewSet(R.id.textStreet, "Straat: ", rootView);
            TextViewSet(R.id.textNumber, "Nr: ", rootView);
            TextViewSet(R.id.textAmountEmployees, "Aantal werknemers:: ", rootView);
            TextViewSet(R.id.textAmountOfDeliveryNotes, "Aantal bestelbonnen: ", rootView);

            // fill in
            TextViewSet(R.id.textIdFillIn, Integer.toString(storeId), rootView);
            TextViewSet(R.id.textStoreNameFillIn, store.name, rootView);
            TextViewSet(R.id.textTownFillIn, store.town, rootView);
            TextViewSet(R.id.textZIPFillIn, store.postal_code, rootView);
            TextViewSet(R.id.textStreetFillIn, store.street, rootView);
            TextViewSet(R.id.textNumberFillIn, Integer.toString(store.number), rootView);
            TextViewSet(R.id.textAmountEmployeesFillIn, Integer.toString(amountEmployees), rootView);
            TextViewSet(R.id.textAmountOfDeliveryNotesFillIn, Integer.toString(amountDeliveryNotes), rootView);


            ImageView img = new ImageView(rootView.getContext());
            RelativeLayout relativeLayout = rootView.findViewById(R.id.relativeLayout);
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

            List<Product> stockList = StoresRepo.instance.getProductsByStoreIdAsync(storeId);
            List<String> list = new ArrayList<>();
            list.add("nr \t\t\t\t\t\t\t\t\t naam \t\t\t\t\t\t\t\t (maat) \t\t\t\t\t\t\t\t " +
                    "aantal: x \n\t\t\t\t\t\t\t\t\t\t\t merk "
                    + "\t\t\t\t\t\t\t\t kleur");
            for (Product product : stockList) {
                int id = product.id;
                String name = product.name;
                String size = product.size;
                String brand = product.brand;
                String color = product.color;
                int amount = product.amount;

                list.add(id + "\t\t\t\t\t\t\t\t\t" + name + "\t\t\t\t\t\t\t\t (" + size + ")"
                        + "\t\t\t\t\t\t\t\t\t\t\t\t aantal: " + amount + "\n\t\t\t\t\t\t\t\t\t\t\t" + brand
                        + "\t\t\t\t\t\t\t\t " + color);
            }
            ArrayAdapter<String> employeeArrayAdapter = new ArrayAdapter<>(rootView.getContext(),
                    R.layout.fragment_store_listview, R.id.textViewEmployees , list );
            listView.setAdapter(employeeArrayAdapter);

            return rootView;
        }

        private View fillInEmployees(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_listview, container, false);
            ListView listView = rootView.findViewById(R.id.simpleListView);

            List<Employee> employeesList = StoresRepo.instance.getEmployeesByStoreIdAsync(storeId);

            List<String> list = new ArrayList<>();
            list.add("nr \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t naam (leeftijd) \t\t\t\t\t\t\t\t\t job");
            for (Employee employee : employeesList) {
                int id = employee.id;
                String name = employee.name;
                int age = employee.age;
                String duty = employee.duty;

                list.add(id + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + name + "("
                        + age + ")\t\t\t\t\t\t\t\t\t\t\t\t"
                        + duty);
            }
            ArrayAdapter<String> deliveryNoteArrayAdapter = new ArrayAdapter<>(rootView.getContext(),
                    R.layout.fragment_store_listview, R.id.textViewEmployees , list );
            listView.setAdapter(deliveryNoteArrayAdapter);

            return rootView;
        }

        private View fillInDeliveryNotes(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_store_listview, container, false);
            ListView listView = rootView.findViewById(R.id.simpleListView);

            List<DeliveryNote> deliveryNoteList =  StoresRepo.instance.getDeliveryNotesByStoreIdAsync(storeId);
            List<String> list = new ArrayList<>();

            list.add("nr \t\t\t\t\t\t\t\t\t\t\t\t\t\t [status]\t\t\t\t\t\t\t\t\t\t\t\t\t datum " +
                    "\n\t\t\t\t - product nr \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                    "aantal: x \n\t\t\t\t\t (extra)");
            for (DeliveryNote deliveryNote : deliveryNoteList) {
                int id = deliveryNote.id;
                String status = deliveryNote.status;
                int amount = deliveryNote.amount;
                String date = deliveryNote.date;
                int productId = deliveryNote.productId;
                String extra = deliveryNote.extra;

                list.add(id + "\t\t\t\t\t\t\t\t\t\t\t\t [" + status + "]\t\t\t\t\t\t\t\t\t\t\t\t"
                        + date + "\n\t\t\t\t - " + productId + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                        + "aantal: " + amount + "\n\t\t\t\t\t (" + extra + ")");
            }
            ArrayAdapter<String> deliveryNoteArrayAdapter = new ArrayAdapter<>(rootView.getContext(),
                    R.layout.fragment_store_listview, R.id.textViewEmployees , list );
            listView.setAdapter(deliveryNoteArrayAdapter);

            return rootView;
        }

        private void TextViewSet(int id, String name, View view) {
            TextView textView = view.findViewById(id);
            textView.setText(name);
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

    @SuppressLint("SetTextI18n")
    private void BasicStuff() {
        Intent intent = getIntent();
        int storeId = intent.getIntExtra("storeId", 0);
        String storeName = intent.getStringExtra("storeName");
        number = storeId;

        TextViewSet(storeName + " (nr:" + storeId + ")");

        Button backButton = findViewById(R.id.backToStore);
        backButton.setText("Terug");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });

        MakeTabFull();
    }

    private void MakeTabFull() {
        tabFull = findViewById(R.id.storeTab);
        tabFull.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabFull.setTabTextColors(Color.BLACK,Color.RED);
        MakeTabs();
    }

    private void MakeTabs() {
        TabLayout.Tab detailsTab = tabFull.newTab();
        TabLayout.Tab stockTab = tabFull.newTab();
        TabLayout.Tab employeesTab = tabFull.newTab();
        TabLayout.Tab deliveryTab = tabFull.newTab();

        NameTab(detailsTab, "Details", R.drawable.info);
        NameTab(stockTab, "Product", R.drawable.stock);
        NameTab(employeesTab, "Werknemers", R.drawable.employees);
        NameTab(deliveryTab, "Bestelbonnen", R.drawable.deliverynote);
    }

    private void NameTab(TabLayout.Tab tab, String string, int number) {
        tab.setText(string);
        tab.setIcon(number);
        tabFull.addTab(tab,true);
    }

    private void GoBack() {
        Intent intent = new Intent(this, Stores.class);
        startActivity(intent);
    }

    private void TextViewSet(String name) {
        TextView textView = findViewById(R.id.textView3);
        textView.setText(name);
    }
}
