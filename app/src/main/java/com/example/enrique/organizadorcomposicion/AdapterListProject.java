package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Activity.ProjectActivity;
import com.example.enrique.organizadorcomposicion.Entities.clsItemProject;

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
        AdapterListProject.VHolder vHolder= new AdapterListProject.VHolder(view, context);

        // Establecer click listener sobre items de proyecto
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjectActivity.class);
                context.startActivity(intent);
            }
        });

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
    public static class VHolder extends RecyclerView.ViewHolder{

        private TextView tvTitulo;
        private TextView tvFechaCreacion;
        private Context context;

        public VHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
            tvFechaCreacion= (TextView)itemView.findViewById(R.id.tvFechaCreacion);
        }
    }
}
