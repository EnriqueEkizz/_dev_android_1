package com.example.enrique.organizadorcomposicion.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.enrique.organizadorcomposicion.PageAdapter_scale;
import com.example.enrique.organizadorcomposicion.R;
import com.example.enrique.organizadorcomposicion.screen_guitar;

public class ScaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        ViewPager viewPager = findViewById(R.id.vpScale);
        PageAdapter_scale pageAdapter_scale = new PageAdapter_scale(getSupportFragmentManager());
        pageAdapter_scale.addPageList(new screen_guitar());
        viewPager.setAdapter(pageAdapter_scale);

        //TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FLOATING BUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
