package com.example.enrique.organizadorcomposicion.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.enrique.organizadorcomposicion.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecordActivity extends AppCompatActivity {

    private ImageButton btnRecording;
    private ImageButton btnResetRecording;
    private static boolean flagRecording = false;

    private MediaRecorder myAudioRecorder;
    private String outputFile;
    private String nameRecording;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        btnRecording = findViewById(R.id.btnRecording);
        btnResetRecording = findViewById(R.id.btnResetRecorder);

        // BOTON INICIAR / GUARDAR GRABACION
        btnRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagRecording) {
                    btnRecording.setImageResource(R.drawable.ic_play);
                    flagRecording = false;
                    stopRecording();
                } else {
                    btnRecording.setImageResource(R.drawable.ic_save_recording);
                    flagRecording = true;
                    startRecording();
                }
            }
        });

        // BOTON LIMPIAR GRABACION
        btnResetRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagRecording) {
                    resetRecording();
                }
            }
        });
    }
    private void startRecording() {
        DateFormat df = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String date = df.format(Calendar.getInstance().getTime());

        nameRecording = date + ".m4a";
        outputFile = getExternalFilesDir("Recordings") + "/" + nameRecording;
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioSamplingRate(44100);
        myAudioRecorder.setAudioEncodingBitRate(96000);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
        myAudioRecorder.setOutputFile(outputFile);

        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
            flagRecording = true;
            Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void stopRecording() {
        myAudioRecorder.stop();

        //CALCULANDO DURACION
        Uri uri = Uri.parse(outputFile);
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(this.getBaseContext(), uri);
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

        Toast.makeText(getApplicationContext(), "Audio Recorder successfully", Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("PATH", outputFile);
        returnIntent.putExtra("NAME", nameRecording);
        returnIntent.putExtra("DURATION", duration);
        returnIntent.putExtra("CALL_INDEX", getIntent().getIntExtra("CALL_INDEX",0));
        setResult(RESULT_OK, returnIntent);

        myAudioRecorder.release();
        myAudioRecorder = null;
        flagRecording = false;
        finish();
    }
    private void resetRecording() {
        myAudioRecorder.reset();
        myAudioRecorder.release();
        myAudioRecorder = null;
        flagRecording = false;

        Toast.makeText(getApplicationContext(), "Audio Reset successfully", Toast.LENGTH_LONG).show();
    }

}
