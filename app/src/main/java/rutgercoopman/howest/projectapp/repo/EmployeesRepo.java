package rutgercoopman.howest.projectapp.repo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import rutgercoopman.howest.projectapp.Employees;
import rutgercoopman.howest.projectapp.models.Employee;

public class EmployeesRepo extends  Repository<Employee> {

    public static final EmployeesRepo instance = new EmployeesRepo("http://10.0.0.2:3000");

    public EmployeesRepo(String domain) {
        super(domain);
    }

    public EmployeesRepo() {
    }

    public List<Employee> getItems() {
        try {
            String json = fetch("/employees");
            System.out.println("json: " + json);
            return (List<Employee>) new ObjectMapper().readValue(json, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


//fout
    public Employee getItemById(int id) {
        try {
            String json = fetch("/products/" + id);
            return new ObjectMapper().readValue(json, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}