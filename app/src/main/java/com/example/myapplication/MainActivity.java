package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnsubmit;
    EditText user,pass;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("com.example.myapplication.myrefrences", 0);

        Spinner spinner = findViewById(R.id.spinner);

        String[] rols = getResources().getStringArray(R.array.rols);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, rols);
        spinner.setAdapter(adapter);


        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);

        btnsubmit = findViewById(R.id.submit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.getText() == null || pass.getText() ==null )
                {
                    Toast.makeText(MainActivity.this, "Please input username and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String selectedRole = spinner.getSelectedItem().toString();

                    if (selectedRole.equals("Student")) {
                        // Start ScannerQRActivity
                        check(1 , user.getText().toString().toLowerCase() , pass.getText().toString().toLowerCase() );

                    } else if (selectedRole.equals("Teacher")) {
                        // Start GenerateQRActivity
                       check(0 , user.getText().toString().toLowerCase() , pass.getText().toString().toLowerCase() );

                    } else {
                        // Show toast message to select a role
                        Toast.makeText(MainActivity.this, "Please select a role", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }

    public void check(int role, String username, String password) {


        DataBaseHapler db = new DataBaseHapler(MainActivity.this);

        if (role == 0) { // teacher

            List<TeacherModel> teachers = db.getAllTeachers();

            for (TeacherModel teacher : teachers) {

                if (teacher.getUser().equals(username) && teacher.getPass().equals(password)) {
                    // Username and password match for a teacher
                    Intent intent = new Intent(MainActivity.this, Teacher.class);
                    startActivity(intent);
                }
                else
                {
                    // No matching teacher found
                  //  Toast.makeText(MainActivity.this, "user name or password is incorrect", Toast.LENGTH_SHORT).show();

                }

            }

        }


        else if (role == 1) { // student

            List<StudnetModel> students = db.getAllstudent();

            for (StudnetModel student : students) {

                if (student.getUser().equals(username) && student.getPass().equals(password)) {

                    // Username and password match for a student
                   if (addname(student.getName()))
                   {
                       Intent intent = new Intent(MainActivity.this, ScannerQR.class);
                       startActivity(intent);
                   }

                }
                else
                {
                    // No matching student found
                   // Toast.makeText(MainActivity.this, "user name or password is incorrect", Toast.LENGTH_SHORT).show();

                }
            }
           }

        else {
            // Invalid role
            Toast.makeText(MainActivity.this, "Invalid role, please try again", Toast.LENGTH_SHORT).show();
         }
    }

    private  boolean addname(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();
        return true;
    }



}