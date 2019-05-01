package rutgercoopman.howest.projectapp.repo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

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
            return (List<Employee>) new ObjectMapper().readValue(json, Employee.class);
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
    public void addEmployee (Employee employee) {
        try {
            String json = fetch("/employees/add");
            System.out.println("json: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}