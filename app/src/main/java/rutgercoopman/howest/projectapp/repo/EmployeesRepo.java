package rutgercoopman.howest.projectapp.repo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rutgercoopman.howest.projectapp.models.Employee;

public class EmployeesRepo extends  Repository<Employee> {

    public static final EmployeesRepo instance = new EmployeesRepo();

    private EmployeesRepo() {
    }

    public List<Employee> getItems() {
        try {
            String json = fetch("/employees");
            return getEmployeesFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getItemById(int id) {
        return null;
    }

    // TODO: 01/05/2019  toevoegen
    @SuppressLint("StaticFieldLeak")
    public void addEmployeeAsync(final Employee employee) {
        try {
            (new AsyncTask<Void, Void, Employee>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                protected Employee doInBackground(Void... objects) {
                    return addEmployee(employee);
                }
            }).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private Employee addEmployee (Employee employee) {
        try {

            String json = post(employee);
            System.out.println("json: " + json);

            return employee;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<Employee> getEmployeesFromJson(String json) {
        final String ID = "id";
        final String STOREID = "storeId";
        final String NAME = "name";
        final String AGE = "age";
        final String DUTY = "duty";
        final String USERNAME = "username";
        final String STARTEDON = "created_at";

        ArrayList<Employee> employees = new ArrayList<>();
        try {
            JSONArray arrayEmployees = new JSONArray(json);
            int numberOfEmployees = arrayEmployees.length();
            for(int i = 0; i < numberOfEmployees; i++) {
                JSONObject Json = arrayEmployees.getJSONObject(i);
                int id = Json.getInt(ID);
                int storeId = Json.getInt(STOREID);
                String name = Json.getString(NAME);
                int age = Json.getInt(AGE);
                String duty = Json.getString(DUTY);
                String username = Json.getString(USERNAME);
                String startedOn = Json.getString(STARTEDON);

                String[] al = startedOn.split("T");
                String data = al[0];

                Employee employee = new Employee(id, storeId,name, age, duty, username, "", data);
                employees.add(employee);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return employees;
    }


}