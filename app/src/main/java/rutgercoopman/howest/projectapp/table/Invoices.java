package rutgercoopman.howest.projectapp.table;


        import rutgercoopman.howest.projectapp.models.Employee;
        import rutgercoopman.howest.projectapp.models.Stock;
        import rutgercoopman.howest.projectapp.models.Store;

public class Invoices {

    public Employee[] getEmployees() {
        Employee[] data = new Employee[80];

        for(int i = 0; i < 80; i ++) {
            Employee row = new Employee((i+1), "test" +1, (i*8), "lol " + i,"functie" +1, "20/02/2018");
            data[i] = row;
        }
        return data;
    }

    public Store[] getStores() {
        Store[] data = new Store[40];
        for (int i = 0; i < 40; i++) {
            Store row = new Store(i+1, "naam "+i, i ,i, "town"+i, "T" + i, "straat", 3+i);
            data[i]= row;
        }
        return data;
    }

    public Stock[] getStocks() {
        Stock[] data = new Stock[40];
        for (int i = 0; i < 40; i++) {
            Stock row = new Stock(i+1, "naam "+i, i + "maat", "merk"+i+i, "kleur" + i, i +i +i);
            data[i]= row;
        }
        return data;
    }



}
