package com.dataservice.woolly.weighttracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

public class WeightTracker extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.message";

    public static class DateWeight implements Serializable,Comparable<DateWeight>{
        private static final String SAVE_FILE = "WeightData.dat";
        public Date date;
        public double weight;

        DateWeight(Date date, double weight){
            this.date = date;
            this.weight = weight;
        }

        public String toString(){
            String retString  = date + " " + weight;
            return retString;
        }

        @Override
        public int compareTo(DateWeight o){
            return date.compareTo(o.date);
        }
    }

    /* Linked list used to store the dates acquired by the send button.  */
    private LinkedList<DateWeight> weightsList;
    private WeightStorage storage = new WeightStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker);
        weightsList = storage.LoadData(this.getApplicationContext());
        DisplayWeights();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.v("WEIGHTSTORAGE","aPPLICATION PAUSED");
        Collections.sort(weightsList);
        storage.SaveData(weightsList, this.getApplicationContext());
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.newWeight);
        String readValue = editText.getText().toString();
        if(!readValue.matches("")) {
            Log.v("WEIGHTTRACKER",readValue);
            double weight = Double.parseDouble(readValue);
            Date date = getDateFromDatePicker((DatePicker) findViewById(R.id.datePicker));
            DateWeight todayWeight = new DateWeight(date, weight);
            weightsList.add(todayWeight);

            DisplayWeights();

        }
    }

    /** Called when the user taps the Clear button */
    public void clearData(View view) {
        weightsList.clear();
        DisplayWeights();
    }

    private void DisplayWeights(){
        String displayText = "";
        TextView textView = (TextView) findViewById(R.id.CollectedValues);
        for (DateWeight data : weightsList) {
            displayText += data + "\n";
        }
        textView.setText(displayText);
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
