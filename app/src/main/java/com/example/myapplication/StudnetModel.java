package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public   class StudnetModel {
    public  int id;
    public  String name;
    public String user;
    public  String pass;

    public StudnetModel(int id, String name, String user, String pass) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    public StudnetModel( ) {

    }

    @Override
    public String toString() {
        return "StudnetModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public  List<StudnetModel> getStudents() {
        List<StudnetModel> students = new ArrayList<>();
        students.add(new StudnetModel(1, "John", "john123", "123"));
        students.add(new StudnetModel(2, "Alice", "alice456", "123"));
        students.add(new StudnetModel(3, "Bob", "bob789", "123"));
        students.add(new StudnetModel(4, "Emily", "emily321", "123"));

        return students;
    }

}
