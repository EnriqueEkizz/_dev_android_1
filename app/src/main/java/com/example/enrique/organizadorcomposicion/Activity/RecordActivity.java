package com.example.enrique.organizadorcomposicion.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.enrique.organizadorcomposicion.R;

public class RecordActivity extends AppCompatActivity {

    private ImageButton btnRecording;
    private ImageButton btnSaveRecord;
    private static boolean flagRecording = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        btnRecording = findViewById(R.id.btnRecording);
        btnSaveRecord = findViewById(R.id.btnSaveRecord);

        btnRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagRecording) {
                    btnRecording.setImageResource(R.drawable.ic_play);
                    flagRecording = false;
                } else {
                    btnRecording.setImageResource(R.drawable.ic_pause_record);
                    flagRecording = true;
                }
            }
        });
    }
}
