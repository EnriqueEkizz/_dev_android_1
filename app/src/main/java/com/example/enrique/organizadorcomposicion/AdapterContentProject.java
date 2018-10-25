package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;

import java.util.ArrayList;
import java.util.List;

public class AdapterContentProject extends RecyclerView.Adapter<AdapterContentProject.VHolder> {

    Context context;
    List<item_harmonyblock> ListHarmonyBlocks;

    public AdapterContentProject(Context xContext) {
        this.context = xContext;
        ListHarmonyBlocks = new ArrayList<>();
    }
    public void addHarmonyBlock(item_harmonyblock harmonyBlock) {
        this.ListHarmonyBlocks.add(harmonyBlock);
    }

    @NonNull
    @Override
    public AdapterContentProject.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_harmonyblock, viewGroup, false);
        AdapterContentProject.VHolder vHolder= new AdapterContentProject.VHolder(view, context);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContentProject.VHolder vHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return ListHarmonyBlocks.size();
    }

    ///////////////////// CLASE ViewHolder
    public static class VHolder extends RecyclerView.ViewHolder {

        public VHolder(@NonNull View itemView, Context context) {
            super(itemView);
        }
    }
}
