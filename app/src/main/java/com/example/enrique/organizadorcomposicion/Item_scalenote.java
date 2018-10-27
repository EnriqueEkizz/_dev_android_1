package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Item_scalenote extends Fragment {

    private View view;
    private String Note;
    private JSONObject jsonObject;

    public Item_scalenote() {
    }

    public static Item_scalenote newInstance(String xNote) {
        Item_scalenote item_note = new Item_scalenote();
        Bundle bundle = new Bundle();
        bundle.putString("NOTE", xNote);
        item_note.setArguments(bundle);
        return item_note;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            jsonObject = new JSONObject(getArguments().getString("NOTE"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.Note = getArguments().getString("NOTE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_scalenote, container, false);

        TextView tvNote = (TextView)view.findViewById(R.id.tvNote);

        tvNote.setText(this.Note);
        return view;
    }
}
