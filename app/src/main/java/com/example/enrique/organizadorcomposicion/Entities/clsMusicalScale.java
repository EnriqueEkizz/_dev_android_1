package com.example.enrique.organizadorcomposicion.Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class clsMusicalScale {
    private ArrayList<String> MUSICAL_NOTES;
    private ArrayList<Integer> NOTES_SELECTED;
    public ArrayList<ArrayList<Integer>> ESCALAS = new ArrayList<>();
    public ArrayList<Integer> ESCALA_MAYOR = new ArrayList<>();
    public ArrayList<Integer> ESCALA_MENOR = new ArrayList<>();

    private int string_1;
    private int string_2;
    private int string_3;
    private int string_4;
    private int string_5;
    private int string_6;

    public clsMusicalScale() {
        this.MUSICAL_NOTES = new ArrayList<>();
        this.MUSICAL_NOTES.add("C");  // 1
        this.MUSICAL_NOTES.add("C#"); // 2
        this.MUSICAL_NOTES.add("D");  // 3
        this.MUSICAL_NOTES.add("D#"); // 4
        this.MUSICAL_NOTES.add("E");  // 5
        this.MUSICAL_NOTES.add("F");  // 6
        this.MUSICAL_NOTES.add("F#"); // 7
        this.MUSICAL_NOTES.add("G");  // 8
        this.MUSICAL_NOTES.add("G#"); // 9
        this.MUSICAL_NOTES.add("A");  // 10
        this.MUSICAL_NOTES.add("A#"); // 11
        this.MUSICAL_NOTES.add("B");  // 12
        this.MUSICAL_NOTES.add("C");  // 1
        this.MUSICAL_NOTES.add("C#"); // 2
        this.MUSICAL_NOTES.add("D");  // 3
        this.MUSICAL_NOTES.add("D#"); // 4
        this.MUSICAL_NOTES.add("E");  // 5
        this.MUSICAL_NOTES.add("F");  // 6
        this.MUSICAL_NOTES.add("F#"); // 7
        this.MUSICAL_NOTES.add("G");  // 8
        this.MUSICAL_NOTES.add("G#"); // 9
        this.MUSICAL_NOTES.add("A");  // 10
        this.MUSICAL_NOTES.add("A#"); // 11
        this.MUSICAL_NOTES.add("B");  // 12
        // Definifición de escalas
        this.ESCALA_MAYOR.addAll(Arrays.asList(0,2,2,1,2,2,2,1));
        this.ESCALA_MENOR.addAll(Arrays.asList(0,2,1,2,2,1,2,2));
        this.ESCALAS.add(this.ESCALA_MAYOR);
        this.ESCALAS.add(this.ESCALA_MENOR);
        // Afinación
        this.string_1 = 5;  // E
        this.string_2 = 12; // B
        this.string_3 = 8;  // G
        this.string_4 = 3;  // D
        this.string_5 = 10; // A
        this.string_6 = 5;  // E
        this.NOTES_SELECTED = new ArrayList<>();
    }

    public void addNoteSelected(int x, int y){
        int ind = 0;
        //String note;
        switch (x) {
            case 1:
                ind = this.string_1 + y;
                break;
            case 2:
                ind = this.string_2 + y;
                break;
            case 3:
                ind = this.string_3 + y;
                break;
            case 4:
                ind = this.string_4 + y;
                break;
            case 5:
                ind = this.string_5 + y;
                break;
            case 6:
                ind = this.string_6 + y;
                break;
        }
        // Agregando notas pisadas
        if (!this.MUSICAL_NOTES.contains(ind)) {
            this.NOTES_SELECTED.add(ind);
        }
    }

    public ArrayList<Integer> getScaleMatch() {
        ArrayList<Integer> coincidencia = new ArrayList<>();
        int v, n, c;
        //Recorrer notas musicales
        for (int e = 1; e < 13; e++) {
            n = 0;
            c = 0;
            //Recorrer escala mayor
            for (int i = 0; i < this.ESCALA_MAYOR.size(); i++) {
                v = this.ESCALA_MAYOR.get(i);
                n = n + e + v;
                //Verificar si "n" esta contenido en notas pisadas
                if (this.NOTES_SELECTED.contains(n)) {
                    c++;
                }
            }
            //Agregar coincidencia
            coincidencia.add(c);

            n = 0;
            c = 0;
            //Recorrer escala menor
            for (int i = 0; i < this.ESCALA_MENOR.size(); i++) {
                v = this.ESCALA_MENOR.get(i);
                n = n + e + v;
                //Verificar si "n" esta contenido en notas pisadas
                if (this.NOTES_SELECTED.contains(n)) {
                    c++;
                }
            }
            //Agregar coincidencia
            coincidencia.add(c);
        }
        return coincidencia;
    }

    public int getString_1() {
        return string_1;
    }
    public int getString_2() {
        return string_2;
    }
    public int getString_3() {
        return string_3;
    }
    public int getString_4() {
        return string_4;
    }
    public int getString_5() {
        return string_5;
    }
    public int getString_6() {
        return string_6;
    }

    public String getChordFromIndex(int i) {
        return this.MUSICAL_NOTES.get(i - 1);
    }
    public String getChordFromScale(int chord, int indexScale, int position) {
        ArrayList<Integer> scale = this.ESCALAS.get(indexScale);
        int sum = chord;
        for (int n = 0; n < position; n++) {
            sum = sum + scale.get(n);
            if (sum > 12) {
                sum = sum - 12;
            }
        }
        return getChordFromIndex(sum);
    }
}
