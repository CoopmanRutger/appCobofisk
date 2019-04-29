package rutgercoopman.howest.projectapp.models;

public class Employee {

    public int id;
    public String name;
    public  int age;
    public  String username;
    public  String duty;
    public  String startedOn;

    public Employee(int id, String name, int age, String username, String duty, String startedOn) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.duty = duty;
        this.startedOn = startedOn;
    }
}
