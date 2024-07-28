package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHapler extends SQLiteOpenHelper {


    public DataBaseHapler(@Nullable Context context  ) {
        super(context, "SystemApp.db", null, 1);



    }

    // is was call when first create DB
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createStudentsTable = "CREATE TABLE STUDENTS_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USER TEXT, PASS TEXT)";
        String createTeacherTable = "CREATE TABLE TEACHER_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USER TEXT, PASS TEXT)";
        String createAttendanceTable = "CREATE TABLE ATTENDANCE_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENTNAME TEXT, SUBJECT TEXT, DATE TEXT)";

        sqLiteDatabase.execSQL(createStudentsTable);
        sqLiteDatabase.execSQL(createTeacherTable);
        sqLiteDatabase.execSQL(createAttendanceTable);
    }


    // is call when DB version is changed
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public  boolean AddOne_Student(StudnetModel model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("NAME",model.getName());
        value.put("USER",model.getUser());
        value.put("PASS",model.getPass());

        long insert = db.insert("STUDENTS_TABLE",null,value);
        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public  boolean AddOne_Teacher(TeacherModel model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("NAME",model.getName());
        value.put("USER",model.getUser());
        value.put("PASS",model.getPass());

        long insert = db.insert("TEACHER_TABLE",null,value);
        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public  boolean AddOne_Attendance(AttendanceModel model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
       // System.out.println(model.getStudentName()+"\n"+model.getSubject()+"\n"+model.getDate());
        value.put("STUDENTNAME",model.getStudentName());
        value.put("SUBJECT",model.getSubject());
        value.put("DATE",model.getDate());

        long insert = db.insert("ATTENDANCE_TABLE",null,value);
        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public List<StudnetModel> getAllstudent()
    {
        List<StudnetModel> studnetModels = new ArrayList<>();

        String query = "SELECT * FROM STUDENTS_TABLE";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String name= cursor.getString(1);
                String user= cursor.getString(2);
                String pass= cursor.getString(3);

                StudnetModel studnetModel =new StudnetModel(id,name,user,pass);
                studnetModels.add(studnetModel);
            }while (cursor.moveToNext());
        }
        else
        {
           System.out.println("the DB is not have any object yet ! ");

        }
        cursor.close();
        db.close();
        return studnetModels;
    }

    public List<TeacherModel> getAllTeachers()
    {
        List<TeacherModel> teacherModels = new ArrayList<>();
        String query = "SELECT * FROM TEACHER_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String name= cursor.getString(1);
                String user= cursor.getString(2);
                String pass= cursor.getString(3);

                TeacherModel teacherModel =new TeacherModel(id,name,user,pass);
                teacherModels.add(teacherModel);
            }while (cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return teacherModels;
    }

    public List<AttendanceModel> getAllAttendance()
    {
        List<AttendanceModel> attendanceModels = new ArrayList<>();
        String query = "SELECT * FROM ATTENDANCE_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String student= cursor.getString(1);
                String subject= cursor.getString(2);
                String date= cursor.getString(3);

                AttendanceModel  attendanceModel = new AttendanceModel(id,student,subject,date);
                attendanceModels.add(attendanceModel);
            }while (cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return attendanceModels;
    }

}
