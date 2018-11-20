package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;

import java.util.List;

public class AdapterContentProject extends RecyclerView.Adapter<AdapterContentProject.VHolder> {

    //ID DE PROYECTO PARA CONSULTAR EN BASE DE DATOS
    private Context context;
    List<clsHarmonyBlock> ListHarmonyBlocks;

    ///////////////////// CLASE ViewHolder
    public class VHolder extends RecyclerView.ViewHolder {

        //Menu
        public ImageButton btnMenuOpen;
        public ImageButton btnMenuClose;
        public LinearLayout popupMenu;
        public LinearLayout frameNotes;
        //Opciones de menu
        public ImageButton btnAddNote;
        //Detalles de bloque
        public ImageButton btnRecording;
        public ImageButton btnPlayRecording;
        public TextView tvRecordingName;

        public VHolder(@NonNull final View itemView) {
            super(itemView);

            //FRAME PARA NOTA DE CIRCULO ARMONICO
            frameNotes = itemView.findViewById(R.id.frameNotes);
            popupMenu = itemView.findViewById(R.id.popupMenu);
            btnRecording = itemView.findViewById(R.id.btnRec);
            btnPlayRecording = itemView.findViewById(R.id.btnPlay);
            tvRecordingName = itemView.findViewById(R.id.tvNameRecording);

            //MENU
            btnMenuOpen = itemView.findViewById(R.id.btnOpenMenu);
            btnMenuClose = itemView.findViewById(R.id.btnCloseMenu);

            //OPCIONES DE MENU
            btnAddNote = itemView.findViewById(R.id.btnAddNote);
        }
    }

    public AdapterContentProject(Context xContext, clsProjectStructure xProjectStructure) {
        this.context = xContext;
        this.ListHarmonyBlocks = xProjectStructure.getContent().getAllHarmonyBlock();
    }
    public void addHarmonyBlock(clsHarmonyBlock xHarmonyBlock) {
        this.ListHarmonyBlocks.add(xHarmonyBlock);
    }

    @NonNull
    @Override
    public AdapterContentProject.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_harmonyblock_3, viewGroup, false);
        AdapterContentProject.VHolder vHolder = new AdapterContentProject.VHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterContentProject.VHolder vHolder, int i) {
        clsHarmonyBlock harmonyBlock = ListHarmonyBlocks.get(i);

        //INSERTAR NOTAS DE CIRUCLO ARMONICO EN BLOQUE INICIALES
        for (String note : harmonyBlock.getHarmonyNotes()) {
            addHarmonyNote(note, context, vHolder.frameNotes);
        }

        //MOSTRAR / OCULTAR MENU
        vHolder.btnMenuOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vHolder.popupMenu.setVisibility(View.VISIBLE);
            }
        });

        vHolder.btnMenuClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vHolder.popupMenu.setVisibility(View.GONE);
            }
        });

        //ESTABLECER NOMBRE DE BLOQUE
        if (harmonyBlock.getRecording().length() != 0) {
            vHolder.tvRecordingName.setText(harmonyBlock.getRecording());
            vHolder.btnRecording.setVisibility(View.GONE);
        } else {
            vHolder.btnPlayRecording.setVisibility(View.GONE);
        }

        //AGREGAR NOTA DE CIRCULO ARMONICO
        vHolder.btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHarmonyNote("C", context, vHolder.frameNotes);
            }
        });
    }
    private void addHarmonyNote(String xNote, Context xContext, LinearLayout xLinearLayout) {
        Button imgbtn = new Button(context);
        imgbtn.setTransformationMethod(null);
        imgbtn.setText(xNote);
        imgbtn.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
        imgbtn.setHeight(50);
        imgbtn.setWidth(50);
        imgbtn.setTextSize(18);
        xLinearLayout.addView(imgbtn);
    }

    @Override
    public int getItemCount() {
        return ListHarmonyBlocks.size();
    }
}
