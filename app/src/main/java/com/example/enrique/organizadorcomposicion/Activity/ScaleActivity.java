package com.example.enrique.organizadorcomposicion.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.enrique.organizadorcomposicion.Entities.clsMusicalScale;
import com.example.enrique.organizadorcomposicion.PageAdapter_scale;
import com.example.enrique.organizadorcomposicion.R;
import com.example.enrique.organizadorcomposicion.screen_guitar;
import com.example.enrique.organizadorcomposicion.Screen_percentage;

import java.util.ArrayList;

public class ScaleActivity extends AppCompatActivity {

    private screen_guitar guitar;
    private Screen_percentage screen_percentage;
    private ArrayList<Integer> notesPressed;
    private ArrayList<Integer> notasCoincidencia = new ArrayList<>();
    private clsMusicalScale musicalScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        ViewPager viewPager = findViewById(R.id.vpScale);
        PageAdapter_scale pageAdapter_scale = new PageAdapter_scale(getSupportFragmentManager());
        guitar = new screen_guitar();

        //screen_percentage = Screen_percentage.newInstance(notasCoincidencia);
        screen_percentage = new Screen_percentage();
        screen_percentage.arrPercentages = notasCoincidencia;
        pageAdapter_scale.addPageList(guitar);
        pageAdapter_scale.addPageList(screen_percentage);
        viewPager.setAdapter(pageAdapter_scale);

        //TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ESCALA MUSICAL
        musicalScale = new clsMusicalScale();

        //FLOATING BUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = 1;
                notesPressed = new ArrayList<>();
                ArrayList<ArrayList<Integer>> list = guitar.adapterFretsGuitar.getListNotesUsed();
                for (ArrayList<Integer> string : list) {
                    if (string.size() > 0) {
                        for (int note : string) {
                            switch (c) {
                                case 1:
                                    addNotedPressed(musicalScale.getString_1(), note);
                                    break;
                                case 2:
                                    addNotedPressed(musicalScale.getString_2(), note);
                                    break;
                                case 3:
                                    addNotedPressed(musicalScale.getString_3(), note);
                                    break;
                                case 4:
                                    addNotedPressed(musicalScale.getString_4(), note);
                                    break;
                                case 5:
                                    addNotedPressed(musicalScale.getString_5(), note);
                                    break;
                                case 6:
                                    addNotedPressed(musicalScale.getString_6(), note);
                                    break;
                            }
                        }
                    }
                    c++;
                }
                percent();
            }
        });
    }
    private void addNotedPressed(int valFret, int note) {
        int sum = valFret + note;
        int real = getDoce(sum);
        if (!notesPressed.contains(real)) {
            notesPressed.add(real);
        }
    }
    private int getDoce(int n) {
        while (n > 12) {
            n = n - 12;
        }
        return n;
    }
    private void percent() {
        int m, con;
        String escala, ns;
        notasCoincidencia = new ArrayList<>();

        for (ArrayList<Integer> nomenclatura : musicalScale.ESCALAS) {
            for (int i = 1; i < 13; i++) {
                escala = "";
                m = i;
                // Generar escala
                for (int n : nomenclatura) {
                    m = getDoce(m + n);
                    escala = escala + String.valueOf(m) + "," ;
                }
                //
                con = 0;
                for (int p : notesPressed) {
                    ns = "," + p;
                    if (escala.contains(ns)) {
                        con++;
                    }
                }
                notasCoincidencia.add(con);
            }
        }
        screen_percentage.adapterPercentageScale.arrPercentage = notasCoincidencia;
        screen_percentage.adapterPercentageScale.notifyDataSetChanged();
    }
}
