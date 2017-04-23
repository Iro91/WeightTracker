package com.dataservice.woolly.weighttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class WeightTracker extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.message";

    /* Linked list used to store the dates acquired by the send button.  */
    LinkedList<String> weightsList = new LinkedList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);



        // Capture the layout's TextView and set the string as its text
        weightsList.add(message);
        String displayText = "";
        for(String data : weightsList){
            displayText += data + "\n";
        }
        TextView textView = (TextView) findViewById(R.id.CollectedValues);
        textView.setText(displayText);
    }
}
