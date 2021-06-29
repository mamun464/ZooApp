package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.zooapp.AnimalsDetails.AnimalsList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ZooMap extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_map);

        //navigation
        navigationView=findViewById(R.id.botNav);
        navigation();

    }

    private void navigation() {
        navigationView.setSelectedItemId(R.id.mainLoc);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainHome:
                        startActivity(new Intent(ZooMap.this,Page1.class));
                        return true;
                }
                switch (item.getItemId()){
                    case R.id.Animals:
                        startActivity(new Intent(ZooMap.this, AnimalsList.class));
                        return true;
                }
                switch (item.getItemId()){
                    case R.id.calculation:
                        startActivity(new Intent(ZooMap.this, Calculations.class));
                        return true;
                }
                return false;
            }
        });

    }
}