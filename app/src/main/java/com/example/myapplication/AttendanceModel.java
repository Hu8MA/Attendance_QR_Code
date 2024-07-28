package com.example.myapplication;

public class AttendanceModel {

    public  int id;
    public String date;
    public String StudentName;
    public String subject;

    public AttendanceModel(int id, String studentName,String subject , String date ) {
        this.id = id;
        this.StudentName = studentName;
        this.subject = subject;
        this.date = date;
    }

    public AttendanceModel() {
    }

    @Override
    public String toString() {
        return "AttendanceModel{" +
                "id=" + id +
                ", StudentName='" + StudentName + '\'' +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +

                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

