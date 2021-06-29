package com.example.zooapp.AnimalsDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.zooapp.Calculations;
import com.example.zooapp.Page1;
import com.example.zooapp.R;
import com.example.zooapp.ZooMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AnimalsList extends AppCompatActivity {
    BottomNavigationView navigationView;
    Toolbar animalsBAR;
    RecyclerView rview1,rview2;
    List<AnimalsData> Alist;
    List<AnimalsData2> Alist2;
    AnimalsData2 Adata2;
    AnimalsData Adata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_list);

        animalsBAR = findViewById(R.id.animalsBAR);
        setSupportActionBar(animalsBAR);
        //navigation
        navigationView = findViewById(R.id.botNav);
        navigation();

        rview1 = findViewById(R.id.rview1); rview2=findViewById(R.id.rview2);
        GridLayoutManager grid1 = new GridLayoutManager(AnimalsList.this, 1);
        rview1.setLayoutManager(grid1);
        Alist = new ArrayList<>();
        Adata = new AnimalsData(R.drawable.tiger, "Tiger", "Species: Panthera tigris", "LIFE-SPAN: 10 – 15 years", "Description: The tiger is the largest extant cat species and a member of the genus Panthera. It is most recognisable for its dark vertical stripes on orange-brown fur with a lighter underside. It is an apex predator, primarily preying on ungulates such as deer and wild boar.");
        Alist.add(Adata);
        Adata = new AnimalsData(R.drawable.panda,"Panda","Species: Ailuropoda melanoleuca","LIFE-SPAN: 20 years","Description: The giant panda, also known as the panda bear or simply the panda, is a bear native to south central China. It is characterised by large, black patches around its eyes, over the ears, and across its round body. The name \"giant panda\" is sometimes used to distinguish it from the red panda, a neighboring musteloid.");
        Alist.add(Adata);
        Adata = new AnimalsData(R.drawable.zebra,"Zebra","Species: Plains zebra","LIFE-SPAN: 25 years","Description: The plains zebra, also known as the common zebra, is the most common and geographically widespread species of zebra. Its range is fragmented, but spans much of southern and eastern Africa south of the Sahara. Six subspecies have been recognised including the extinct quagga which was thought to be a separate species.");
        Alist.add(Adata);
        Adata = new AnimalsData(R.drawable.hippopotamus,"Hippopotamus","Species: Hippopotamus amphibius","LIFE-SPAN: 40 – 50 years","Description: The hippopotamus, also called the hippo, common hippopotamus or river hippopotamus, is a large, mostly herbivorous, semiaquatic mammal and ungulate native to sub-Saharan Africa. It is one of only two extant species in the family Hippopotamidae, the other being the pygmy hippopotamus.");
        Alist.add(Adata);
        Adata = new AnimalsData(R.drawable.alligator,"Alligator","Species: Alligator mississippiensis","LIFE-SPAN: 30 – 50 years","Description: The American alligator, sometimes referred to colloquially as a gator or common alligator.");
        Alist.add(Adata);

        AnimalsAdapter adapter = new AnimalsAdapter(AnimalsList.this, Alist);
        rview1.setAdapter(adapter);

        GridLayoutManager grid2 = new GridLayoutManager(AnimalsList.this,1);
        rview2.setLayoutManager(grid2);
        Alist2 = new ArrayList<>();
        Adata2 = new AnimalsData2(R.drawable.bear,"Bear","Species: Ursidae","LIFE-SPAN: 25 years","Description: Bears are carnivoran mammals of the family Ursidae. They are classified as caniforms, or doglike carnivorans. Although only eight species of bears are extant, they are widespread, appearing in a wide variety of habitats throughout the Northern Hemisphere and partially in the Southern Hemisphere.");
        Alist2.add(Adata2);
        Adata2 = new AnimalsData2(R.drawable.lion,"Lion","Species: Panthera leo","LIFE-SPAN: 10 – 14 years","Description: The lion is a species in the family Felidae and a member of the genus Panthera. It is most recognisable for its muscular, deep-chested body, short, rounded head, round ears, and a hairy tuft at the end of its tail. It is sexually dimorphic; adult male lions have a prominent mane.");
        Alist2.add(Adata2);
        Adata2 = new AnimalsData2(R.drawable.elephant,"Elephant","Species: Loxodonta","LIFE-SPAN: 60 – 70 years","Description: The African bush elephant, also known as the African savanna elephant, is the largest living terrestrial animal with bulls reaching a shoulder height of up to 3.96 m. Both sexes have tusks, which erupt when they are 1–3 years old and grow throughout life.");
        Alist2.add(Adata2);
        Adata2 = new AnimalsData2(R.drawable.rhino,"Rhino","Species: Rhinocerotidae","LIFE-SPAN: 40 – 50 years","Description: The rhinoceros or square-lipped rhinoceros is the largest extant species of rhinoceros. It has a wide mouth used for grazing and is the most social of all rhino species.");
        Alist2.add(Adata2);
        Adata2 = new AnimalsData2(R.drawable.monkey,"Monkey","Species: Cercopithecidae","LIFE-SPAN: 20 years","Description: Monkey is a common name that may refer to groups or species of mammals, in part, the simians of infraorder Simiiformes. The term is applied descriptively to groups of primates, such as families of New World monkeys and Old World monkeys.");
        Alist2.add(Adata2);

        AnimalsAdapter2 adapter2 = new AnimalsAdapter2(AnimalsList.this,Alist2);
        rview2.setAdapter(adapter2);


    }


    private void navigation() {
        navigationView.setSelectedItemId(R.id.Animals);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mainHome:
                        startActivity(new Intent(AnimalsList.this, Page1.class));
                        return true;
                }
                switch (item.getItemId()) {
                    case R.id.calculation:
                        startActivity(new Intent(AnimalsList.this, Calculations.class));
                        return true;
                }
                switch (item.getItemId()) {
                    case R.id.mainLoc:
                        startActivity(new Intent(AnimalsList.this, ZooMap.class));
                        return true;
                }
                return false;
            }
        });

    }
}