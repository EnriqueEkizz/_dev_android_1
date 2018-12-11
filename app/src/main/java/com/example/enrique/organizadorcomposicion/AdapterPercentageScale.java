package com.example.enrique.organizadorcomposicion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Entities.clsMusicalScale;

import java.util.ArrayList;

public class AdapterPercentageScale extends RecyclerView.Adapter<AdapterPercentageScale.VHolder> {

    clsMusicalScale musicalScale = new clsMusicalScale();
    public ArrayList<Integer> arrPercentage;
    private Context context;

    public AdapterPercentageScale(ArrayList<Integer> values, Context context) {
        this.arrPercentage = values;
        this.context = context;
    }

    public class VHolder extends RecyclerView.ViewHolder{
        public ImageButton btnAccept;
        public TextView tvChordName;
        public ProgressBar pbPercentage;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            btnAccept = itemView.findViewById(R.id.ibElegirEscala);
            tvChordName = itemView.findViewById(R.id.tvAcorde);
            pbPercentage = itemView.findViewById(R.id.pbPorcentaje);
            getAdapterPosition();
        }
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_porcentage, viewGroup, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, final int i) {
        ArrayList<Integer> values = arrPercentage;
        //Descripción
        vHolder.tvChordName.setText(musicalScale.getChordFromIndex(i + 1));
        //Progressbar
        vHolder.pbPercentage.setProgress(values.get(i));
        //Boton aceptar
        vHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ind = i + 1;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("INDEX", 1);
                if (ind < 13) {
                    returnIntent.putExtra("ESCALA", 0);
                } else {
                    returnIntent.putExtra("ESCALA", 1);
                    ind = i - 12;
                }
                returnIntent.putExtra("CHORD", ind);
                ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPercentage.size();
    }
}
