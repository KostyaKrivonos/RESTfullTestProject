package com.example.personProject.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "persons")
public class Person {
    @Id
    private ObjectId id;

    @NotNull
    @Size(min=2, max=15, message = "name length 2-15")
    private String name;

    @NotNull
    @Size(min=10, max=13, message = "phone numbers length 10-13")
    private String phone;

    public Person() {
    }

    public Person( String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
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

    @Override
    public String toString() {
        return "Person: " +
                "id=" + id +
                ", name='" + name +
                ", phone='" + phone;
    }
}
