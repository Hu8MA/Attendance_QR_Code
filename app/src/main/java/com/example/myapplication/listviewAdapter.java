package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listviewAdapter extends ArrayAdapter<AttendanceModel> {

    private Context context;
    private int resource;
    public listviewAdapter(@NonNull Context context, int resource, @NonNull List<AttendanceModel> objects) {
        super(context, resource, objects);

        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Obtain a LayoutInflater object from the provided context
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        // Inflate the layout specified by the resource parameter
        // Convert XML layout file into a View object
        // The parent parameter is the parent ViewGroup into which the inflated layout will be placed
        // false indicates that the inflated layout should not be attached to the parent during the inflation process
        convertView = layoutInflater.inflate(resource , parent , false);

        // Find the TextView with the id "namestd" within the inflated layout
       TextView textView1 = convertView.findViewById(R.id.namestd);

        // Find the TextView with the id "subjectname" within the inflated layout
        TextView textView2 = convertView.findViewById(R.id.subjectname);

        // Find the TextView with the id "dateregister" within the inflated layout
        TextView textView3 = convertView.findViewById(R.id.dateregister);

        // Set the text of textView1 to the "StudentName" property of the data item at the current position in the adapter's data set
        textView1.setText(getItem(position).StudentName);

        // Set the text of textView2 to the "subject" property of the data item at the current position in the adapter's data set
        textView2.setText(getItem(position).subject);

        // Set the text of textView3 to the "date" property of the data item at the current position in the adapter's data set
         textView3.setText(getItem(position).date);

        // Return the convertView, which now contains the populated layout
        return convertView;

    }
}
