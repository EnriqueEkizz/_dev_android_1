package com.example.enrique.organizadorcomposicion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Activity.ProjectActivity;
import com.example.enrique.organizadorcomposicion.Activity.RecordActivity;
import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;

import java.io.IOException;
import java.util.List;

public class AdapterContentProject extends RecyclerView.Adapter<AdapterContentProject.VHolder> {

    //ID DE PROYECTO PARA CONSULTAR EN BASE DE DATOS
    //Id project
    long ID;
    private Context context;
    List<clsHarmonyBlock> ListHarmonyBlocks;
    private int CODE;
    private MediaPlayer mediaPlayer;
    private int lastPosition = -1;

    ///////////////////// CLASE ViewHolder
    public class VHolder extends RecyclerView.ViewHolder {
        //Menu
        public ImageButton btnMenuOpen;
        public ImageButton btnMenuClose;
        public LinearLayout popupMenu;
        public LinearLayout frameNotes;
        //Opciones de menu
        public ImageButton btnAddRecording;
        public ImageButton btnAddNote;
        public ImageButton btnMoveUp;
        public ImageButton btnMoveDown;
        public ImageButton btnDeleteHarmonyBlock;
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
            btnAddRecording = itemView.findViewById(R.id.btnRec);
            btnMoveUp = itemView.findViewById(R.id.btnMoveUp);
            btnMoveDown = itemView.findViewById(R.id.btnMoveDown);
            btnDeleteHarmonyBlock = itemView.findViewById(R.id.btnDeleteHarmonyBlock);
        }
    }

    public AdapterContentProject(Context xContext, clsProjectStructure xProjectStructure, int xCodeRequest) {
        this.context = xContext;
        this.ID = xProjectStructure.getIdProject();
        this.ListHarmonyBlocks = xProjectStructure.getContent().getAllHarmonyBlock();
        this.CODE = xCodeRequest;
        mediaPlayer = new MediaPlayer();
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

        // INSERTAR NOTAS DE CIRUCLO ARMONICO EN BLOQUE INICIALES
        for (String note : harmonyBlock.getHarmonyNotes()) {
            addHarmonyNote(note, context, vHolder.frameNotes, false, 0);
        }
        // MOSTRAR / OCULTAR MENU
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
        // ESTABLECER GRABACION
        if (harmonyBlock.getRecording().length() != 0) {
            vHolder.tvRecordingName.setText(harmonyBlock.getRecording());
            vHolder.btnRecording.setVisibility(View.GONE);
            vHolder.btnPlayRecording.setVisibility(View.VISIBLE);
        } else {
            vHolder.btnRecording.setVisibility(View.VISIBLE);
            vHolder.btnPlayRecording.setVisibility(View.GONE);
        }
        // PLAY / PAUSE
        if (harmonyBlock.isPlaying()) {
            vHolder.btnPlayRecording.setImageResource(R.drawable.ic_pause_white);
        } else {
            vHolder.btnPlayRecording.setImageResource(R.drawable.ic_play_white);
        }
        // AGREGAR GRABACION EN CIRCULO ARMONICO
        vHolder.btnAddRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecordActivity.class);
                intent.putExtra("ID_PROJECT", ID);
                intent.putExtra("CALL_INDEX", vHolder.getAdapterPosition());
                ((Activity) context).startActivityForResult(intent, CODE);
            }
        });
        // MENU
            // AGREGAR NOTA DE CIRCULO ARMONICO
            vHolder.btnAddNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addHarmonyNote("C", context, vHolder.frameNotes, true, vHolder.getAdapterPosition());
                }
            });
            // MOVER ARRIBA CIRCULO ARMONICO
            vHolder.btnMoveUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tomando indice
                    int i = vHolder.getAdapterPosition();
                    if (i > 0) {
                        // Eliminando contenido de framenotas
                        deleteAllHarmonyNote(vHolder.frameNotes);
                        //deleteAllHarmonyNote(vHolder.getLayoutPosition();
                        // Tomando bloques
                        clsHarmonyBlock blockToUp = ListHarmonyBlocks.get(i);
                        clsHarmonyBlock blockToDown = ListHarmonyBlocks.get(i - 1);
                        // Cambiando de lugar
                        ListHarmonyBlocks.set(i - 1, blockToUp);
                        ListHarmonyBlocks.set(i, blockToDown);
                        // Refrescando adaptador
                        notifyDataSetChanged();
                    }
                }
            });
            // MOVER ABAJO CIRCULO ARMONICO
            vHolder.btnMoveDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tomando indice
                    int i = vHolder.getAdapterPosition();
                    if (i < getItemCount() - 1) {
                        // Eliminando contenido de framenotas
                        deleteAllHarmonyNote(vHolder.frameNotes);
                        // Tomando bloques
                        clsHarmonyBlock blockToDown = ListHarmonyBlocks.get(i);
                        clsHarmonyBlock blockToUp = ListHarmonyBlocks.get(i + 1);
                        // Cambiando de lugar
                        ListHarmonyBlocks.set(i + 1, blockToDown);
                        ListHarmonyBlocks.set(i, blockToUp);
                        // Refrescando adaptador
                        notifyDataSetChanged();
                    }
                }
            });
            // BORRAR CIRCULO ARMONICO
            vHolder.btnDeleteHarmonyBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cerrar menu de opciones
                    vHolder.popupMenu.setVisibility(View.GONE);
                    // Eliminar clase de lista harmonyblock
                    ListHarmonyBlocks.remove(vHolder.getAdapterPosition());
                    // Refrescar adaptador
                    notifyDataSetChanged();
                }
            });

        //REPRODUCIR AUDIO
        vHolder.btnPlayRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecorded(vHolder.getAdapterPosition());
            }
        });
        //AL FINALIZAR REPRODUCCION
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopMedia(lastPosition);
            }
        });
    }
    private void addHarmonyNote(String xNote, Context xContext, LinearLayout xLinearLayout, boolean addToList, int xPosition) {
        Button imgbtn = new Button(context);
        imgbtn.setTransformationMethod(null);
        imgbtn.setText(xNote);
        imgbtn.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
        imgbtn.setHeight(50);
        imgbtn.setWidth(50);
        imgbtn.setTextSize(18);
        xLinearLayout.addView(imgbtn);
        if (addToList) {
            this.ListHarmonyBlocks.get(xPosition).addHarmonyNotes(xNote);
        }
    }
    private void deleteAllHarmonyNote(LinearLayout xLinearLayout){
        Log.i("INGRESO CON: ", String.valueOf(xLinearLayout.getChildCount()));
        xLinearLayout.removeAllViews();
        Log.i("SALIO CON: ", String.valueOf(xLinearLayout.getChildCount()));
    }

    @Override
    public int getItemCount() {
        return ListHarmonyBlocks.size();
    }

    //ADMINISTRAR REPRODUCCION
    private void playRecorded(int position) {
        if (lastPosition < 0) {
            startMedia(position);
        } else if (lastPosition != position) {
            stopMedia(lastPosition);
            startMedia(position);
        } else {
            pauseMedia(position);
        }
        lastPosition = position;
    }
    //START
    private void startMedia(int p) {
        try{
            mediaPlayer.setDataSource(ListHarmonyBlocks.get(p).getPathRecording());
            mediaPlayer.prepare();
            mediaPlayer.start();
            ListHarmonyBlocks.get(p).play();
            notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //PAUSE
    private void pauseMedia(int p) {
        if (ListHarmonyBlocks.get(p).isPlaying()) {
            ListHarmonyBlocks.get(p).stop();
            mediaPlayer.pause();
        } else {
            ListHarmonyBlocks.get(p).play();
            mediaPlayer.start();
        }
        notifyDataSetChanged();
    }
    //STOP
    private void stopMedia(int p) {
        mediaPlayer.reset();
        lastPosition = -1;
        ListHarmonyBlocks.get(p).stop();
        notifyDataSetChanged();
    }
}
