package com.example.testProject.entity;
import org.springframework.data.annotation.Id;


public class Person {
    @Id
    private int id;
    private String name;
    private String phone;
    private String adress;

    public Person() {
    }

    public Person(String name, String phone, String adress) {
        this.name = name;
        this.phone = phone;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id= " + id +
                ", name= " + name +
                ", phone= " + phone +
                ", adress= " + adress;
    }
}
