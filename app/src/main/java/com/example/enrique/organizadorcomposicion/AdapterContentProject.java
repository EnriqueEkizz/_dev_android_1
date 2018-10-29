package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterContentProject extends RecyclerView.Adapter<AdapterContentProject.VHolder> {

    private Context context;
    List<item_harmonyblock> ListHarmonyBlocks;
    ActionMode actionMode;

    public AdapterContentProject(Context xContext) {
        this.context = xContext;
        ListHarmonyBlocks = new ArrayList<>();
    }
    public void addHarmonyBlock(item_harmonyblock harmonyBlock) {
        //Pasar contexto a bloque armonico
        harmonyBlock.setContext(this.context);
        //Agregar bloque armonico a lista
        this.ListHarmonyBlocks.add(harmonyBlock);
    }

    @NonNull
    @Override
    public AdapterContentProject.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_harmonyblock_2, viewGroup, false);
        AdapterContentProject.VHolder vHolder = new AdapterContentProject.VHolder(view, context);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, int i) {
        vHolder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.addnote:
                        // ADD NOTE TO FRAME
                        Log.i("asdf:" , String.valueOf(vHolder.getAdapterPosition()));
                        ListHarmonyBlocks.get(vHolder.getAdapterPosition()).addNoteView("C");
                        return true;
                    case R.id.moveup:
                        return true;
                    case R.id.movedown:
                        return true;
                    default:
                        return false;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return ListHarmonyBlocks.size();
    }

    ///////////////////// CLASE ViewHolder
    public static class VHolder extends RecyclerView.ViewHolder {

        private android.support.v7.widget.Toolbar toolbar;

        public VHolder(@NonNull final View itemView, final Context context) {
            super(itemView);

            toolbar = itemView.findViewById(R.id.toolbarBlock);
            toolbar.inflateMenu(R.menu.menu_harmonyblock);
        }
    }
}
