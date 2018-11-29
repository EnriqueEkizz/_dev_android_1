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

import com.example.enrique.organizadorcomposicion.Activity.RecordActivity;
import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;

import java.io.IOException;
import java.util.List;

public class AdapterContentProject extends RecyclerView.Adapter<AdapterContentProject.VHolder> {

    //ID DE PROYECTO PARA CONSULTAR EN BASE DE DATOS
    //Id project
    long ID;
    private clsProjectStructure projectStructure;
    private Context context;
    List<clsHarmonyBlock> ListHarmonyBlocks;
    private int CODE;
    private MediaPlayer mediaPlayer;
    private int lastPosition = -1;
    private OnItemClickListener onItemClickListener;

    ///////////////////// CLASE ViewHolder
    public class VHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public LinearLayout frameNotes;
        //Opciones de menu
        public ImageButton btnAddRecording;
        //Detalles de bloque
        public ImageButton btnRecording;
        public ImageButton btnPlayRecording;
        public TextView tvRecordingName;

        public VHolder(@NonNull final View itemView) {
            super(itemView);
            //FRAME PARA NOTA DE CIRCULO ARMONICO
            frameNotes = itemView.findViewById(R.id.frameNotes);
            btnRecording = itemView.findViewById(R.id.btnRec);
            btnPlayRecording = itemView.findViewById(R.id.btnPlay);
            tvRecordingName = itemView.findViewById(R.id.tvNameRecording);
            //OPCIONES DE MENU
            btnAddRecording = itemView.findViewById(R.id.btnRec);
            //LISTENER
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemLongPress(getAdapterPosition());
            }
            return true;
        }
    }

    public AdapterContentProject(Context xContext, clsProjectStructure xProjectStructure, int xCodeRequest) {
        this.context = xContext;
        this.ID = xProjectStructure.getIdProject();
        this.projectStructure = xProjectStructure;
        this.ListHarmonyBlocks = xProjectStructure.getContent().getAllHarmonyBlock();
        this.CODE = xCodeRequest;
        mediaPlayer = new MediaPlayer();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        //if (!harmonyBlock.isInflated()) { // Verificar si ya esta inflado
        Log.i("Inflar: ", String.valueOf(harmonyBlock.isInflate()));
        //if (!harmonyBlock.isInflate()) {
            for (String note : harmonyBlock.getHarmonyNotes()) {
                addHarmonyNote(note, context, vHolder.frameNotes, false, 0);
            }
            //harmonyBlock.setInflate(true);
        //}

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
        //REPRODUCIR AUDIO
        vHolder.btnPlayRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("last position; ", String.valueOf(vHolder.getAdapterPosition()));
                playRecorded(vHolder.getAdapterPosition());
                notifyItemChanged(vHolder.getAdapterPosition());
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopMedia(lastPosition);
                Log.i("last position; ", String.valueOf(lastPosition));
                notifyItemChanged(lastPosition);
                //ListHarmonyBlocks.get(lastPosition).setInflate(true);
                lastPosition = -1;
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
            this.ListHarmonyBlocks.get(xPosition).addHarmonyNote(xNote);
        }
    }
    private void deleteAllHarmonyNote(LinearLayout xLinearLayout){
        xLinearLayout.removeAllViews();
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
            //notifyDataSetChanged();
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
        //notifyDataSetChanged();
    }
    //STOP
    private void stopMedia(int p) {
        mediaPlayer.reset();
        //lastPosition = -1;
        ListHarmonyBlocks.get(p).stop();
        //notifyDataSetChanged();
    }

    // INTERFACE
    public interface OnItemClickListener{
        void onItemLongPress(int position);
    }
}
