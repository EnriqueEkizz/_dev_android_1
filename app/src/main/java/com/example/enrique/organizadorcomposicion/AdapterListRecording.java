package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.enrique.organizadorcomposicion.Entities.clsItemRecording;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class AdapterListRecording extends RecyclerView.Adapter<AdapterListRecording.VHolder> {

    private Context context;
    private List<clsItemRecording> lstData;
    private MediaPlayer mediaPlayer;
    private int lastPosition = -1;

    ///////////////////// CLASE ViewHolder
    public class VHolder extends RecyclerView.ViewHolder {

        public TextView tvTitulo;
        public TextView tvFechaCreacion;
        public TextView tvDuracion;
        public LinearLayout lyFrameRecord;
        public ImageView ivIconRecordingFile;
        public ImageButton ibItemOptionsRecording;

        public VHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvFechaCreacion= itemView.findViewById(R.id.tvFechaCreacion);
            tvDuracion = itemView.findViewById(R.id.tvDuracion);
            lyFrameRecord = itemView.findViewById(R.id.lyFrameRecord);
            ivIconRecordingFile = itemView.findViewById(R.id.ivIconRecordingFile);
            ibItemOptionsRecording = itemView.findViewById(R.id.btnItemOptionsRecording);
        }
    }

    public AdapterListRecording(Context context, List<clsItemRecording> lstData) {
        this.context = context;
        this.lstData = lstData;
        mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recorded_file, viewGroup, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, final int i) {
        final clsItemRecording itemRecording = lstData.get(i);

        vHolder.tvTitulo.setText(itemRecording.getTitulo());
        vHolder.tvFechaCreacion.setText(itemRecording.getFechacreacion());
        vHolder.tvDuracion.setText(itemRecording.getDuracion());
        if (itemRecording.isPlaying()) {
            vHolder.ivIconRecordingFile.setImageResource(R.drawable.ic_pause);
        } else {
            vHolder.ivIconRecordingFile.setImageResource(R.drawable.ic_play);
        }

        //MENU OPTIONS
        vHolder.ibItemOptionsRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, vHolder.ibItemOptionsRecording);
                popupMenu.inflate(R.menu.menu_item_recording);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.optionRecordings_1:
                                // Eliminar file
                                File file = new File(lstData.get(vHolder.getAdapterPosition()).getPath());
                                if (file.delete()) {
                                    // Eliminar clase de adaptador
                                    lstData.remove(vHolder.getAdapterPosition());
                                    // Refrescar adaptador
                                    notifyDataSetChanged();
                                }
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        //REPRODUCIR AUDIO
        vHolder.lyFrameRecord.setOnClickListener(new View.OnClickListener() {
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
    @Override
    public int getItemCount() {
        return lstData.size();
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
            mediaPlayer.setDataSource(lstData.get(p).getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            lstData.get(p).play();
            notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //PAUSE
    private void pauseMedia(int p) {
        if (lstData.get(p).isPlaying()) {
            lstData.get(p).stop();
            mediaPlayer.pause();
        } else {
            lstData.get(p).play();
            mediaPlayer.start();
        }
        notifyDataSetChanged();
    }
    //STOP
    private void stopMedia(int p) {
        mediaPlayer.reset();
        lastPosition = -1;
        lstData.get(p).stop();
        notifyDataSetChanged();
    }
}
