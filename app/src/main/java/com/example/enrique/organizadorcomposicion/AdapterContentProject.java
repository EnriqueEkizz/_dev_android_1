package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterContentProject extends RecyclerView.Adapter<AdapterContentProject.VHolder> {

    private Context context;
    List<Item_harmonyblock> ListHarmonyBlocks;
    ActionMode actionMode;

    public AdapterContentProject(Context xContext) {
        this.context = xContext;
        ListHarmonyBlocks = new ArrayList<>();
    }
    public void addHarmonyBlock(Item_harmonyblock harmonyBlock) {
        //Pasar contexto a bloque armonico
        harmonyBlock.setContext(this.context);
        //Agregar bloque armonico a lista
        this.ListHarmonyBlocks.add(harmonyBlock);
    }

    @NonNull
    @Override
    public AdapterContentProject.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_harmonyblock_3, viewGroup, false);
        AdapterContentProject.VHolder vHolder = new AdapterContentProject.VHolder(view, context);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, int i) {
        vHolder.btnMenuOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vHolder.frameMenu.setVisibility(View.VISIBLE);
            }
        });
        vHolder.btnMenuClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vHolder.frameMenu.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ListHarmonyBlocks.size();
    }

    ///////////////////// CLASE ViewHolder
    public static class VHolder extends RecyclerView.ViewHolder {

        ImageButton btnMenuOpen;
        ImageButton btnMenuClose;
        LinearLayout frameMenu;

        public VHolder(@NonNull final View itemView, final Context context) {
            super(itemView);

            btnMenuOpen = itemView.findViewById(R.id.showMenuHarmonyBlock);
            btnMenuClose = itemView.findViewById(R.id.closeMenuHarmonyBlock);
            frameMenu = itemView.findViewById(R.id.menu_harmonyblock);
        }
    }
}
