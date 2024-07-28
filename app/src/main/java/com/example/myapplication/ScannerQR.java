package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ScannerQR extends AppCompatActivity {

    public Button btn_scanner;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scanner_qr);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("com.example.myapplication.myrefrences", 0);
        btn_scanner = findViewById(R.id.scanner);



        btn_scanner.setOnClickListener(v->
        {
            ScanQR();
        });


    }

    private void ScanQR()
    {
        ScanOptions option = new ScanOptions();
        option.setPrompt("Volume up to flash on");
        option.setBeepEnabled(true);
        option.setOrientationLocked(true);
        option.setCaptureActivity(CapturAct.class);
        Laucher.launch(option);
    }

    ActivityResultLauncher<ScanOptions> Laucher = registerForActivityResult(new ScanContract() , result->
    {
        if(result.getContents() != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ScannerQR.this );

            builder.setTitle("You are Register in :");
            builder.setMessage(result.getContents());


            //to Save Register
            String name =sharedPreferences.getString("name", "not have name");
            String subject = result.getContents().toString();

            // Get current date and time
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm ", Locale.getDefault());


            String date = dateFormat.format(calendar.getTime());

            DataBaseHapler db = new DataBaseHapler(ScannerQR.this);

            AttendanceModel s = new AttendanceModel(0,name,subject,date);

            boolean x = db.AddOne_Attendance(s);

             // Toast.makeText(ScannerQR.this, "Data\n"+s.toString(), Toast.LENGTH_LONG).show();
            //List<AttendanceModel> models = db.getAllAttendance();
           // Toast.makeText(ScannerQR.this, "Data\n"+models.toString(), Toast.LENGTH_LONG).show();

            if(x)
            {
                builder.setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

            }

            else
            {
                Toast.makeText(ScannerQR.this, "Please try again Scanner", Toast.LENGTH_SHORT).show();

            }



            //

        }
        else
        {
            Toast.makeText(ScannerQR.this, "Please try again Scanner", Toast.LENGTH_SHORT).show();
        }
    });
}