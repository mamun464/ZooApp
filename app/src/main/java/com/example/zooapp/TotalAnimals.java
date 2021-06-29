package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TotalAnimals extends AppCompatActivity {
    private CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13;
    private EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13;
    private Button total, clear;
    private TextView result;

    private SharedPreferences sharedPreferences;
    private static final String Shared_Animal="AnimalPref";
    private static final String Shared_Total="totalCOunt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_animals);
        getSupportActionBar().setTitle("Total Animals");

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);
        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);
        c13 = findViewById(R.id.c13);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        e6 = findViewById(R.id.e6);
        e7 = findViewById(R.id.e7);
        e8 = findViewById(R.id.e8);
        e9 = findViewById(R.id.e9);
        e10 = findViewById(R.id.e10);
        e11 = findViewById(R.id.e11);
        e12 = findViewById(R.id.e12);
        e13 = findViewById(R.id.e13);

        total = findViewById(R.id.calT);
        clear = findViewById(R.id.clearField);
        result = findViewById(R.id.resulT);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("00");

            }
        });

        sharedPreferences = getSharedPreferences(Shared_Animal,MODE_PRIVATE);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = 0;
                final ArrayList<Integer> animalsNum = new ArrayList<>();
                if (c1.isChecked()) {
                    animalsNum.add(Integer.parseInt(e1.getText().toString()));
                }if (c2.isChecked()) {
                    animalsNum.add(Integer.parseInt(e2.getText().toString()));
                }if (c3.isChecked()) {
                    animalsNum.add(Integer.parseInt(e3.getText().toString()));
                }if (c4.isChecked()) {
                    animalsNum.add(Integer.parseInt(e4.getText().toString()));
                }if (c5.isChecked()) {
                    animalsNum.add(Integer.parseInt(e5.getText().toString()));
                }if (c6.isChecked()) {
                    animalsNum.add(Integer.parseInt(e6.getText().toString()));
                }if (c7.isChecked()) {
                    animalsNum.add(Integer.parseInt(e7.getText().toString()));
                }if (c8.isChecked()) {
                    animalsNum.add(Integer.parseInt(e8.getText().toString()));
                }if (c9.isChecked()) {
                    animalsNum.add(Integer.parseInt(e9.getText().toString()));
                }if (c10.isChecked()) {
                    animalsNum.add(Integer.parseInt(e10.getText().toString()));
                }if (c11.isChecked()) {
                    animalsNum.add(Integer.parseInt(e11.getText().toString()));
                }if (c12.isChecked()) {
                    animalsNum.add(Integer.parseInt(e12.getText().toString()));
                }if (c13.isChecked()) {
                    animalsNum.add(Integer.parseInt(e13.getText().toString()));
                }
                for (int i = 0; i < animalsNum.size(); i++) {
                    total += animalsNum.get(i);
                }
                result.setText(Integer.toString(total));

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString(Shared_Total,result.getText().toString());
                editor1.apply();


            }
        });

    }
}