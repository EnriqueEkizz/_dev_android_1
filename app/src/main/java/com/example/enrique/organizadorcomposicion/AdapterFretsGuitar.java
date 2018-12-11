package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Entities.clsFretsGuitar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterFretsGuitar extends RecyclerView.Adapter<AdapterFretsGuitar.VHolder> {

    private Context context;
    private String[] drawNote;
    List<clsFretsGuitar> ListFretsGuitar;

    public AdapterFretsGuitar(Context context, List<clsFretsGuitar> listFretsGuitar, String drawNote) {
        this.context = context;
        ListFretsGuitar = listFretsGuitar;
        this.drawNote = drawNote.split("\\|");
    }

    public class VHolder extends RecyclerView.ViewHolder{
        public Button btnString1,
                btnString2,
                btnString3,
                btnString4,
                btnString5,
                btnString6;
        public TextView tvFretNumber;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            tvFretNumber = itemView.findViewById(R.id.tvFretNumber);
            btnString1 = itemView.findViewById(R.id.string_1);
            btnString2 = itemView.findViewById(R.id.string_2);
            btnString3 = itemView.findViewById(R.id.string_3);
            btnString4 = itemView.findViewById(R.id.string_4);
            btnString5 = itemView.findViewById(R.id.string_5);
            btnString6 = itemView.findViewById(R.id.string_6);
            getAdapterPosition();
        }
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guitar_fret_2, viewGroup, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, int i) {
        clsFretsGuitar fretsGuitar = ListFretsGuitar.get(i);

        vHolder.tvFretNumber.setText(fretsGuitar.getNumberFret());

        if (drawNote.length > 1) {
            for (int j = 1; j < 7; j++) {
                String coo = (i + 1) + "-" + j;
                if (Arrays.asList(drawNote).contains(coo)) {
                    switch (j) {
                        case 1:
                            vHolder.btnString1.setBackgroundResource(R.drawable.shape_c);
                            break;
                        case 2:
                            vHolder.btnString2.setBackgroundResource(R.drawable.shape_c);
                            break;
                        case 3:
                            vHolder.btnString3.setBackgroundResource(R.drawable.shape_c);
                            break;
                        case 4:
                            vHolder.btnString4.setBackgroundResource(R.drawable.shape_c);
                            break;
                        case 5:
                            vHolder.btnString5.setBackgroundResource(R.drawable.shape_c);
                            break;
                        case 6:
                            vHolder.btnString6.setBackgroundResource(R.drawable.shape_c);
                            break;
                    }
                }
            }

        }

        vHolder.btnString1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressStringOnList(vHolder.getAdapterPosition() + 1, 1, vHolder.btnString1);
            }
        });
        vHolder.btnString2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressStringOnList(vHolder.getAdapterPosition() + 1, 2, vHolder.btnString2);
            }
        });
        vHolder.btnString3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressStringOnList(vHolder.getAdapterPosition() + 1, 3, vHolder.btnString3);
            }
        });
        vHolder.btnString4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressStringOnList(vHolder.getAdapterPosition() + 1, 4, vHolder.btnString4);
            }
        });
        vHolder.btnString5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressStringOnList(vHolder.getAdapterPosition() + 1, 5, vHolder.btnString5);
            }
        });
        vHolder.btnString6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressStringOnList(vHolder.getAdapterPosition() + 1, 6, vHolder.btnString6);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListFretsGuitar.size();
    }

    private void pressStringOnList(int thisFret, int thisString, Button btnString) {
        thisFret--;
        if (ListFretsGuitar.get(thisFret).isPressed(thisString)) {
            ListFretsGuitar.get(thisFret).unpressString(thisString);
            btnString.setBackgroundResource(R.drawable.shape_e);
        } else {
            ListFretsGuitar.get(thisFret).pressString(thisString);
            btnString.setBackgroundResource(R.drawable.shape_c);
        }
    }

    public ArrayList<ArrayList<Integer>> getListNotesUsed() {
        int c = 1, y = 1;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> string1 = new ArrayList<>();
        ArrayList<Integer> string2 = new ArrayList<>();
        ArrayList<Integer> string3 = new ArrayList<>();
        ArrayList<Integer> string4 = new ArrayList<>();
        ArrayList<Integer> string5 = new ArrayList<>();
        ArrayList<Integer> string6 = new ArrayList<>();

        for (clsFretsGuitar fret : ListFretsGuitar) {
            c = 1;
            for (boolean pressed : fret.Strings) {
                if (pressed) {
                    switch (c) {
                        case 1:
                            string1.add(y);
                            break;
                        case 2:
                            string2.add(y);
                            break;
                        case 3:
                            string3.add(y);
                            break;
                        case 4:
                            string4.add(y);
                            break;
                        case 5:
                            string5.add(y);
                            break;
                        case 6:
                            string6.add(y);
                            break;
                    }
                }
                c++;
            }
            y++;
        }
        Log.i("Cuerda 1: ", string1.toString());
        list.add(string1);
        Log.i("Cuerda 2: ", string2.toString());
        list.add(string2);
        Log.i("Cuerda 3: ", string3.toString());
        list.add(string3);
        Log.i("Cuerda 4: ", string4.toString());
        list.add(string4);
        Log.i("Cuerda 5: ", string5.toString());
        list.add(string5);
        Log.i("Cuerda 6: ", string6.toString());
        list.add(string6);
        return list;
    }
}
