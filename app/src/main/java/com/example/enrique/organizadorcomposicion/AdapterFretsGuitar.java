package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Entities.clsFretsGuitar;

import java.util.List;

public class AdapterFretsGuitar extends RecyclerView.Adapter<AdapterFretsGuitar.VHolder> {

    private Context context;
    List<clsFretsGuitar> ListFretsGuitar;

    public AdapterFretsGuitar(Context context, List<clsFretsGuitar> listFretsGuitar) {
        this.context = context;
        ListFretsGuitar = listFretsGuitar;
    }

    public class VHolder extends RecyclerView.ViewHolder{
        public ImageButton btnString1,
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guitar_fret, viewGroup, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, int i) {
        clsFretsGuitar fretsGuitar = ListFretsGuitar.get(i);

        //vHolder.tvFretNumber.setText(fretsGuitar.getNumberFret(i));
        switch (i) {
            case 2: case 4: case 6: case 8: case 11: case 14: case 16: case 18: case 20: case 23:
                vHolder.tvFretNumber.setText(fretsGuitar.getNumberFret());
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

    private void pressStringOnList(int thisFret, int thisString, ImageButton btnString) {
        thisFret--;
        if (ListFretsGuitar.get(thisFret).isPressed(thisString)) {
            ListFretsGuitar.get(thisFret).unpressString(thisString);
            btnString.setBackgroundResource(R.drawable.shape_e);
        } else {
            ListFretsGuitar.get(thisFret).pressString(thisString);
            btnString.setBackgroundResource(R.drawable.shape_c);
        }
    }
}
