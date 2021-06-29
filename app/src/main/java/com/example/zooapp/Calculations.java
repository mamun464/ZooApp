package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zooapp.AnimalsDetails.AnimalsList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Calculations extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private LinearLayout l1,l2,l3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);

        l1=findViewById(R.id.totalAnimals);
        l2=findViewById(R.id.foodrecords);
        l3=findViewById(R.id.receipt);

        layoutCards();

        //navigation
        navigationView=findViewById(R.id.botNav);
        navigation();
    }

    private void layoutCards() {
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calculations.this,TotalAnimals.class));
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calculations.this,FoodRecord.class));
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calculations.this,Receipt.class));
            }
        });
    }

    private void navigation() {
        navigationView.setSelectedItemId(R.id.calculation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainLoc:
                        startActivity(new Intent(Calculations.this,ZooMap.class));
                        return true;
                }
                switch (item.getItemId()){
                    case R.id.Animals:
                        startActivity(new Intent(Calculations.this, AnimalsList.class));
                        return true;
                }
                switch (item.getItemId()){
                    case R.id.mainHome:
                        startActivity(new Intent(Calculations.this, Page1.class));
                        return true;
                }
                return false;
            }
        });
    }
}