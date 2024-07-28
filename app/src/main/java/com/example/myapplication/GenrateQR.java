package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenrateQR extends AppCompatActivity {

    public Spinner spinner;
    public Button btn_genrate;
    public ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genrate_qr);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btn_genrate = findViewById(R.id.genrate);
        image = findViewById(R.id.qr);
        spinner = findViewById(R.id.spinner2);

        // Retrieve the array of subjects from resources
        String[] subjects = getResources().getStringArray(R.array.subjects);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        btn_genrate.setOnClickListener(v->
        {
            genrateQR();
        });
    }

    private void genrateQR() {

            String subject = spinner.getSelectedItem().toString();
            if (subject.equals("Select the Subject"))
            {
                Toast.makeText(GenrateQR.this, "Please select a Subject !", Toast.LENGTH_SHORT).show();
            }
            else
            {
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(subject , BarcodeFormat.QR_CODE,600,600);

                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    image.setImageBitmap(bitmap);
                }
                catch (WriterException e)
                {
                    throw new RuntimeException(e);
                }
            }
    }



}