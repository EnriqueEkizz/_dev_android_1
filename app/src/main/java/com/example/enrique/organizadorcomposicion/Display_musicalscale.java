package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

public class Display_musicalscale extends Fragment {

    private View view;
    private JSONObject JsonScaleNotes;

    public Display_musicalscale() {
    }

    public static Display_musicalscale newInstance(JSONObject jsonObject) {
        Display_musicalscale display_musicalscale = new Display_musicalscale();
        Bundle bundle = new Bundle();
        bundle.putString("JSONSCALE", jsonObject.toString());
        display_musicalscale.setArguments(bundle);
        return display_musicalscale;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            JsonScaleNotes = new JSONObject(getArguments().getString("JSONSCALE"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.display_musicalscale, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_note().newInstance("D"));
        return  view;
    }
}
