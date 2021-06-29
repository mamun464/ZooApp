package com.example.zooapp.AnimalsDetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zooapp.R;

public class DescAnimals extends AppCompatActivity {
    Toolbar animalsBAR;
    ImageView imageA;
    TextView namesA,specA,spanA,descA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_animals);
        animalsBAR=findViewById(R.id.animalsBAR);
        setSupportActionBar(animalsBAR);
        ActionBar bar=getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);

        imageA=findViewById(R.id.imageA);
        namesA=findViewById(R.id.namesA); specA=findViewById(R.id.specA); spanA=findViewById(R.id.spanA); descA=findViewById(R.id.descA);
        Bundle b1=getIntent().getExtras();
        if(b1!=null){
            imageA.setImageResource(b1.getInt("imageA"));
            namesA.setText(b1.getString("namesA"));
            specA.setText(b1.getString("specA"));
            spanA.setText(b1.getString("spanA"));
            descA.setText(b1.getString("descA"));
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}