package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Activity.ProjectActivity;
import com.example.enrique.organizadorcomposicion.Entities.clsItemProject;

import java.util.List;

public class AdapterListProject extends RecyclerView.Adapter<AdapterListProject.VHolder> {

    Context context;
    List<clsItemProject> lstData;

    ///////////////////// CLASE ViewHolder
    public class VHolder extends RecyclerView.ViewHolder{

        public TextView tvTitulo;
        public TextView tvFechaCreacion;
        public LinearLayout frameItemProject;

        public VHolder(@NonNull View itemView){
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvFechaCreacion = itemView.findViewById(R.id.tvFechaCreacion);
            frameItemProject = itemView.findViewById(R.id.frameTitleItemProject);
        }
    }

    public AdapterListProject(Context context, List<clsItemProject> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_file, viewGroup, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterListProject.VHolder vHolder, int i) {
        vHolder.tvTitulo.setText(lstData.get(i).getTitulo());
        vHolder.tvFechaCreacion.setText(lstData.get(i).getFechacreacion());
        // Establecer click listener sobre items de proyecto
        vHolder.frameItemProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjectActivity.class);
                intent.putExtra("ID_PROJECT", String.valueOf(lstData.get(vHolder.getAdapterPosition()).getIdProject()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

}
