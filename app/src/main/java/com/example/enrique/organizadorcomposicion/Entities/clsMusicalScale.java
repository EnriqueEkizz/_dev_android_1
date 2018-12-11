package com.example.enrique.organizadorcomposicion.Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class clsMusicalScale {
    private ArrayList<String> MUSICAL_NOTES;
    private ArrayList<Integer> NOTES_SELECTED;
    public ArrayList<ArrayList<Integer>> ESCALAS = new ArrayList<>();
    public ArrayList<Integer> ESCALA_MAYOR = new ArrayList<>();
    public ArrayList<Integer> ESCALA_MENOR = new ArrayList<>();
    public ArrayList<String> COORDENADAS_NOTAS = new ArrayList<>();

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
        //COORDENADAS GRAFICO DE NOTAS
        this.COORDENADAS_NOTAS.add("1-2|2-4|3-5"); // C
        this.COORDENADAS_NOTAS.add("1-1|1-2|1-3|1-4|1-5|1-6|2-2|3-4|4-5"); // C#
        this.COORDENADAS_NOTAS.add("2-1|3-2|2-3"); // D
        this.COORDENADAS_NOTAS.add("3-1|3-2|3-3|3-4|3-5|3-6|4-2|5-4"); // D#
        this.COORDENADAS_NOTAS.add("1-3|2-4|2-5"); // E
        this.COORDENADAS_NOTAS.add("1-1|1-2|1-3|1-4|1-5|1-6|2-3|3-4|3-5"); // F
        this.COORDENADAS_NOTAS.add("2-1|2-2|2-3|2-4|2-5|2-6|3-3|4-4|4-5"); // F#
        this.COORDENADAS_NOTAS.add("2-5|3-1|3-2|3-6"); // G
        this.COORDENADAS_NOTAS.add("4-1|4-2|4-3|4-4|4-5|4-6|5-3|6-4|6-5"); // G#
        this.COORDENADAS_NOTAS.add("2-2|2-3|2-4"); // A
        this.COORDENADAS_NOTAS.add("1-1|1-2|1-3|1-4|1-5|1-6|3-2|3-3|3-4"); // A#
        this.COORDENADAS_NOTAS.add("2-1|2-2|2-3|2-4|2-5|2-6|4-2|4-3|4-4"); // B
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

    public String getCoordinatesFromChord(String chord){
        return this.COORDENADAS_NOTAS.get(this.MUSICAL_NOTES.indexOf(chord));
    }
}
