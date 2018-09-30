package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterListProject extends RecyclerView.Adapter<AdapterListProject.VHolder> {

    Context context;
    List<clsItemProject> lstData;

    public AdapterListProject(Context context, List<clsItemProject> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public AdapterListProject.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_file, viewGroup, false);

        AdapterListProject.VHolder vHolder= new AdapterListProject.VHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListProject.VHolder vHolder, int i) {
        vHolder.tvTitulo.setText(lstData.get(i).getTitulo());
        vHolder.tvFechaCreacion.setText(lstData.get(i).getFechacreacion());
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    ///////////////////// CLASE ViewHolder
    public static class VHolder extends RecyclerView.ViewHolder {

        private TextView tvTitulo;
        private TextView tvFechaCreacion;

        public VHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
            tvFechaCreacion= (TextView)itemView.findViewById(R.id.tvFechaCreacion);
        }
    }

}
