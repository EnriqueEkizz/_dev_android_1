package com.example.enrique.organizadorcomposicion;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class item_harmonyblock extends Fragment {

    private View view;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Context context;

    public item_harmonyblock() {

    }

    public void setContext(Context xContext){
        this.context = xContext;
        this.fragmentManager = ((AppCompatActivity)xContext).getSupportFragmentManager();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_harmonyblock_2, container,false);
        return view;
    }

    public void addNoteView(String note) {
        Item_scalenote itemScalenote = new Item_scalenote().newInstance(note);
        this.fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameNotes, itemScalenote);
        fragmentTransaction.commit();
    }
}
