package rutgercoopman.howest.projectapp.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    public int id;
    public int storeId;
    public String name;
    public int age;
    public String duty;
    public String username;
    public String password;
    public String startedOn;

    @JsonCreator
    public Employee(@JsonProperty("id") int id, @JsonProperty("storeId") int storeId ,
                    @JsonProperty("name") String name, @JsonProperty("age") int age,
                    @JsonProperty("duty") String duty, @JsonProperty("username") String username,
                    @JsonProperty("password") String password,
                    @JsonProperty("created_at") String startedOn) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.age = age;
        this.duty = duty;
        this.username = username;
        this.password = password;
        this.startedOn = startedOn;
    }
}
