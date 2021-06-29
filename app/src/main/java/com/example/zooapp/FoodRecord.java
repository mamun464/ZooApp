package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FoodRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1, s2;
    TextView fixed, need, totalVeg, fixed2, need2, totalmeat;
    EditText vegAV, price, meatAV, price2;
    Button complete, complete2;
    double vegprice = 0, meatprice = 0;

    private SharedPreferences sharedPreferences;
    private static final String Shared_Food = "FoodPref";
    private static final String Shared_Total_Veg = "totalVegCOunt";
    private static final String Shared_Total_Meat = "totalMeatCOunt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_record);
        getSupportActionBar().setTitle("Food Record");

        fixed = findViewById(R.id.fixed);
        fixed2 = findViewById(R.id.fixed2);
        vegAV = findViewById(R.id.vegAV);
        meatAV = findViewById(R.id.meatAV);
        price = findViewById(R.id.price);
        price2 = findViewById(R.id.price2);
        complete = findViewById(R.id.complete);
        complete2 = findViewById(R.id.complete2);
        totalVeg = findViewById(R.id.totalVeg);
        totalmeat = findViewById(R.id.totalmeat);
        need = findViewById(R.id.need);
        need2 = findViewById(R.id.need2);

        s1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.vegies, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(this);

        s2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.meat, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter2);
        s2.setOnItemSelectedListener(this);

        //sharedInfo
        sharedPreferences = getSharedPreferences(Shared_Food, MODE_PRIVATE);


        vegAV.setOnEditorActionListener(editorActionListener1);
        meatAV.setOnEditorActionListener(editorActionListener2);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String NEED = need.getText().toString();
                    if (NEED.isEmpty()) {
                        Toast.makeText(FoodRecord.this, "Need Required", Toast.LENGTH_SHORT).show();
                    } else {
                        double x3 = Double.parseDouble(need.getText().toString()), x4 = Double.parseDouble(price.getText().toString());
                        totalVeg.setText(String.format("%.2f", x3 * x4));
                        vegprice = vegprice + (x3 * x4);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putString(Shared_Total_Veg, String.valueOf(vegprice));
                        editor1.apply();
                    }
                }catch (Exception e){
                    Toast.makeText(FoodRecord.this,"Enter Amount",Toast.LENGTH_SHORT).show();
                }


            }
        });
        complete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String NEED2 = need2.getText().toString();
                    if (NEED2.isEmpty()) {
                        Toast.makeText(FoodRecord.this, "Need Required", Toast.LENGTH_SHORT).show();
                    } else {
                        double a3 = Double.parseDouble(need2.getText().toString()), a4 = Double.parseDouble(price2.getText().toString());
                        totalmeat.setText(String.format("%.2f", a3 * a4));
                        meatprice = meatprice + (a3 * a4);
                        SharedPreferences.Editor editor2 = sharedPreferences.edit();
                        editor2.putString(Shared_Total_Meat, String.valueOf(meatprice));
                        editor2.apply();
                    }
                }catch (Exception e){
                    Toast.makeText(FoodRecord.this,"Enter Amount",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private TextView.OnEditorActionListener editorActionListener1 = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            Double x1 = Double.parseDouble(fixed.getText().toString()), x2 = Double.parseDouble(vegAV.getText().toString());
            switch (i) {
                case EditorInfo.IME_ACTION_DONE:
                    need.setText(String.format("%.2f", x1 - x2));
                    break;
            }
            return false;
        }
    };
    private TextView.OnEditorActionListener editorActionListener2 = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            Double a1 = Double.parseDouble(fixed2.getText().toString()), a2 = Double.parseDouble(meatAV.getText().toString());
            switch (i) {
                case EditorInfo.IME_ACTION_DONE:
                    need2.setText(String.format("%.2f", a1 - a2));
                    break;
            }
            return false;
        }
    };

    double Peas = 12.4, BabyCorn = 17.8, Grass = 9.7, Cabbage = 11.3, Banana = 17.8;
    double Beef = 102.7, Chicken = 75.6, Mutton = 110.4, Fish = 59.3;

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text1 = adapterView.getItemAtPosition(i).toString();
        switch (text1) {
            case "Select...":
                fixed.setText("");
                break;
            case "Peas":
                fixed.setText(Double.toString(Peas));
                break;
            case "BabyCorn":
                fixed.setText(Double.toString(BabyCorn));
                break;
            case "Grass":
                fixed.setText(Double.toString(Grass));
                break;
            case "Cabbage":
                fixed.setText(Double.toString(Cabbage));
                break;
            case "Banana":
                fixed.setText(Double.toString(Banana));
                break;
        }

        String text2 = adapterView.getItemAtPosition(i).toString();
        switch (text2) {
            case "Select...":
                fixed2.setText("");
                break;
            case "Beef":
                fixed2.setText(Double.toString(Beef));
                break;
            case "Chicken":
                fixed2.setText(Double.toString(Chicken));
                break;
            case "Mutton":
                fixed2.setText(Double.toString(Mutton));
                break;
            case "Fish":
                fixed2.setText(Double.toString(Fish));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}