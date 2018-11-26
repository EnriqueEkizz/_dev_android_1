package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Activity.ProjectActivity;
import com.example.enrique.organizadorcomposicion.Data.DatabaseHelper;
import com.example.enrique.organizadorcomposicion.Entities.clsItemProject;

import java.util.List;

public class AdapterListProject extends RecyclerView.Adapter<AdapterListProject.VHolder> {

    Context context;
    List<clsItemProject> lstData;
    DatabaseHelper databaseHelper;

    ///////////////////// CLASE ViewHolder
    public class VHolder extends RecyclerView.ViewHolder{

        public TextView tvTitulo;
        public TextView tvFechaCreacion;
        public LinearLayout frameItemProject;
        public ImageButton ibItemOptionsProject;

        public VHolder(@NonNull View itemView){
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvFechaCreacion = itemView.findViewById(R.id.tvFechaCreacion);
            frameItemProject = itemView.findViewById(R.id.frameTitleItemProject);
            ibItemOptionsProject = itemView.findViewById(R.id.btnItemOptionsProject);
        }
    }

    public AdapterListProject(Context context, List<clsItemProject> lstData, DatabaseHelper xDatabaseHelper) {
        this.context = context;
        this.lstData = lstData;
        this.databaseHelper = xDatabaseHelper;
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
        // Establecer menu
        vHolder.ibItemOptionsProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, vHolder.ibItemOptionsProject);
                popupMenu.inflate(R.menu.menu_item_project);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.optionProjects_1: // Delete item project
                                Log.i("DELETE: ", String.valueOf(vHolder.getAdapterPosition()));
                                // Eliminar de database
                                databaseHelper.deleteProject(lstData.get(vHolder.getAdapterPosition()).getIdProject());
                                // Eliminar clase de lista en adaptador
                                lstData.remove(vHolder.getAdapterPosition());
                                // Refrescar adaptador
                                notifyDataSetChanged();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    private void deleteProjectFromDatabase() {

    }
}
