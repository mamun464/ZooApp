package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.zooapp.AnimalsDetails.AnimalsList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class Page1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SliderLayout sliderLayout;
    private BottomNavigationView navigationView;
    protected DrawerLayout drawerLayout;
    private NavigationView navigationView2;
    private ImageView nav_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView2 = findViewById(R.id.nav_view);
        nav_menu = findViewById(R.id.drawer_menu);
        navigationDrawer();


        //image slide
        sliderLayout = findViewById(R.id.imageSlider);
        imageSlider();

        //navigation
        navigationView = findViewById(R.id.botNav);
        navigation();

    }

    private void navigationDrawer() {
        navigationView2.bringToFront();
        navigationView2.setNavigationItemSelectedListener(this);
        navigationView2.setCheckedItem(R.id.drawer_home);

        nav_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void navigation() {
        navigationView.setSelectedItemId(R.id.mainHome);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mainLoc:
                        startActivity(new Intent(Page1.this, ZooMap.class));
                        return true;
                }
                switch (item.getItemId()) {
                    case R.id.Animals:
                        startActivity(new Intent(Page1.this, AnimalsList.class));
                        return true;
                }
                switch (item.getItemId()) {
                    case R.id.calculation:
                        startActivity(new Intent(Page1.this, Calculations.class));
                        return true;
                }
                return false;
            }
        });

    }

    private void imageSlider() {
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.THIN_WORM);
        sliderLayout.setAutoScrolling(true);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);
        for (int i = 0; i <= 5; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.lion1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.bear1);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.giraffe1);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.zebra1);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.tiger1);
                    break;
                case 5:
                    sliderView.setImageDrawable(R.drawable.snake1);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.drawer_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(Page1.this, LoginP.class));
                break;
        }
        return true;
    }
}