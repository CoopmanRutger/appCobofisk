package rutgercoopman.howest.projectapp;


        import rutgercoopman.howest.projectapp.models.Employee;

public class Invoices {
    public Employee[] getInvoices() {
        Employee[] data = new Employee[20];

        for(int i = 0; i < 20; i ++) {
            Employee row = new Employee();
            row.id = (i+1);
            row.age = i * 8;
            row.startedOn = "20/02/2018";
            row.name = "test " + i;
            row.username =  "lol " + i;
            row.duty = "functie "+1;

            data[i] = row;
        }
        return data;
    }

}
