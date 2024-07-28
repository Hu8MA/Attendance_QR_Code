package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Attendance extends AppCompatActivity {

    public ListView listView_student;
    public Button btn_seed; Button btn_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attendance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        listView_student = findViewById(R.id.students);

        btn_seed = findViewById(R.id.seed);
        btn_seed.setVisibility(View.INVISIBLE);


        btn_select = findViewById(R.id.vlauseall);

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    DataBaseHapler db =new DataBaseHapler(Attendance.this);

                    /*
                     List<StudnetModel> models = db.getAllstudent();
                     Toast.makeText(Attendance.this, "Data\n"+models.toString(), Toast.LENGTH_LONG).show();
                     ArrayAdapter arrayAdapter = new ArrayAdapter<StudnetModel>(Attendance.this , android.R.layout.simple_list_item_1,models);
                     listView_student.setAdapter(arrayAdapter);
                     */


                    List<AttendanceModel> models = db.getAllAttendance();

                    //Toast.makeText(Attendance.this, "Data\n"+models.toString(), Toast.LENGTH_LONG).show();

                    if (models.isEmpty())
                    {
                        Toast.makeText(Attendance.this, "is not have any data yet !!", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        listviewAdapter adapter = new listviewAdapter(Attendance.this , R.layout.list_row , models);

                        listView_student.setAdapter(adapter);
                    }



                }catch (Exception e)
                {
                    Toast.makeText(Attendance.this, "Error\n"+e.toString(), Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}




  /*
        btn_seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StudnetModel studnetModel = new StudnetModel();
                TeacherModel teacherModel = new TeacherModel();

                DataBaseHapler db = new DataBaseHapler(Attendance.this);

                for (StudnetModel s: studnetModel.getStudents() )
                {
                   boolean x= db.AddOne_Student(s);
                   if (x)
                   {
                       Toast.makeText(Attendance.this, "Seccuss "+x, Toast.LENGTH_SHORT).show();

                   }
                   else
                   {
                       Toast.makeText(Attendance.this, "Error "+x, Toast.LENGTH_SHORT).show();

                   }
                }

                for (TeacherModel s: teacherModel.getteacher() )
                {
                    boolean x= db.AddOne_Teacher(s);
                    if (x)
                    {
                        Toast.makeText(Attendance.this, "Seccuss "+x, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(Attendance.this, "Error "+x, Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
*/
