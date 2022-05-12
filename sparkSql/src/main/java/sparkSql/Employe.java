package sparkSql;

import java.io.Serializable;

public class Employe implements Serializable {
    private long id;

    public Employe() {

    }

    private String name;
    private String phone;
    private double salary;
    private long age;
    private String departement;

    public Employe(long id, String name, String phone, double salary, long age, String departement) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
        this.departement = departement;
    }
    //getters and setters :

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public double getSalary() {
        return salary;
    }

    public long getAge() {
        return age;
    }

    public String getDepartement() {
        return departement;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
