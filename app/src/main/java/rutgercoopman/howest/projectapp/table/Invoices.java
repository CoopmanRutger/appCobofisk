package rutgercoopman.howest.projectapp.table;


        import rutgercoopman.howest.projectapp.models.Employee;

public class Invoices {

    public Employee[] getEmployees() {
        Employee[] data = new Employee[20];

        for(int i = 0; i < 20; i ++) {
            Employee row = new Employee((i+1), "test" +1, (i*8), "lol " + i,"functie" +1, "20/02/2018");
            data[i] = row;
        }
        return data;
    }

}
