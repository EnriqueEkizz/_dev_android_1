package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Entities.clsItemRecording;

import java.util.List;

public class AdapterListRecording extends RecyclerView.Adapter<AdapterListRecording.VHolder> {

    Context context;
    List<clsItemRecording> lstData;

    public AdapterListRecording(Context context, List<clsItemRecording> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public AdapterListRecording.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recorded_file, viewGroup, false);
        AdapterListRecording.VHolder vHolder= new AdapterListRecording.VHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, int i) {
        vHolder.tvTitulo.setText(lstData.get(i).getTitulo());
        vHolder.tvFechaCreacion.setText(lstData.get(i).getFechacreacion());
        vHolder.tvDuracion.setText(lstData.get(i).getDuracion());
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    ///////////////////// CLASE ViewHolder
    public static class VHolder extends RecyclerView.ViewHolder {

        private TextView tvTitulo;
        private TextView tvFechaCreacion;
        private TextView tvDuracion;

        public VHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
            tvFechaCreacion= (TextView)itemView.findViewById(R.id.tvFechaCreacion);
            tvDuracion = (TextView)itemView.findViewById(R.id.tvDuracion);
        }
    }

}
