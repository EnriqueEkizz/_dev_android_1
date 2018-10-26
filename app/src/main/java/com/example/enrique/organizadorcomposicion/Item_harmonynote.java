package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Item_harmonynote extends Fragment {
    View view;
    private String Note;

    public Item_harmonynote() {
    }

    public static Item_harmonynote newInstance(String xNote) {
        Item_harmonynote item_harmonynote = new Item_harmonynote();
        Bundle bundle = new Bundle();
        bundle.putString("HARMONYNOTE", xNote);
        item_harmonynote.setArguments(bundle);
        return item_harmonynote;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.Note = getArguments().getString("HARMONYNOTE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_harmonynote, container, false);

        TextView tvNote = (TextView)view.findViewById(R.id.tvHarmonyNote);

        tvNote.setText(this.Note);
        return view;
    }
}
