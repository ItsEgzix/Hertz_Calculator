package com.example.hertz_calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private EditText tempoEditText;
    private TextView resultTextView;
    private Button calculateButton;
    private Spinner beatSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tempoEditText = findViewById(R.id.tempoEditText);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);
        beatSpinner = findViewById(R.id.beatSpinner);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.beat_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        beatSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFrequency();
            }
        });


//        FirebaseMessaging.getInstance().subscribeToTopic("NEWS")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = "Done";
//                        if (!task.isSuccessful()) {
//                            msg = "Failed";
//                        }
//
//                    }
//                });
    }
    public void onSpotifyClicked(View view) {
        // Handle Spotify click action, e.g., open Spotify profile
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/artist/7j8GjlrNiEFTbTCh4MM85s?si=XA9HZ_eXSI-Yv6x7EzEccQ")));
    }

    public void onInstagramClicked(View view) {
        // Handle Instagram click action, e.g., open Instagram profile
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/its_egzix/")));
    }

    private void calculateFrequency() {
        try {
            String tempoString = tempoEditText.getText().toString();
            double tempoBpm = Double.parseDouble(tempoString);
            String selectedBeat = beatSpinner.getSelectedItem().toString();

            // Convert selected beat to duration (e.g., "1/16" -> 16)
            int beatDuration = Integer.parseInt(selectedBeat.split("/")[1]);

            double frequencyHz = FrequencyCalculator.calculateFrequency(tempoBpm, beatDuration);

            resultTextView.setText(String.format(
                    "The frequency of a %s beat at a tempo of %.2f BPM is approximately %.2f Hz.",
                    selectedBeat,
                    tempoBpm,
                    frequencyHz
            ));
        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid tempo input.");
        }
    }
}
