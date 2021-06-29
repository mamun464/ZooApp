package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Receipt extends AppCompatActivity {
    TextView t1, t2, t3, t4;
    Button receiptfinal;

    private SharedPreferences sharedPreferences1;
    private static final String Shared_Animal = "AnimalPref";
    private static final String Shared_Total = "totalCOunt";

    private SharedPreferences sharedPreferences2;
    private static final String Shared_Food = "FoodPref";
    private static final String Shared_Total_Veg = "totalVegCOunt";
    private static final String Shared_Total_Meat = "totalMeatCOunt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        getSupportActionBar().setTitle("Receipt");

        t1 = findViewById(R.id.anitotal);
        t2 = findViewById(R.id.vegtotal);
        t3 = findViewById(R.id.meattotal);
        t4 = findViewById(R.id.pricetotal);
        receiptfinal = findViewById(R.id.receiptfinal);

        sharedPreferences1 = getSharedPreferences(Shared_Animal, MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences(Shared_Food, MODE_PRIVATE);

        receiptfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String anitotal = sharedPreferences1.getString(Shared_Total, null);
                    if (anitotal != null) {
                        t1.setText(anitotal);
                    }
                    String vegtotal = sharedPreferences2.getString(Shared_Total_Veg, null);
                    String meattotal = sharedPreferences2.getString(Shared_Total_Meat, null);
                    if (vegtotal != null || meattotal != null) {
                        t2.setText(vegtotal);
                        t3.setText(meattotal);
                    }
                    t4.setText(String.format("%.2f", Double.parseDouble(vegtotal) + Double.parseDouble(meattotal)));
                }catch (Exception e){
                    Toast.makeText(Receipt.this,"Calculations Required",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}