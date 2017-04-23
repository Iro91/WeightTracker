package com.dataservice.woolly.weighttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.LinkedList;

public class DisplayMessageActivity extends AppCompatActivity {

    /* Linked list used to store the dates acquired by the send button.  */
    LinkedList<String> weightsList = new LinkedList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(WeightTracker.EXTRA_MESSAGE);
        weightsList.add(message);

        // Capture the layout's TextView and set the string as its text
        String displayText = "";
        for(String data : weightsList){
            displayText += data + "\n";
        }
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(displayText);
    }
}
