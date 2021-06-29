package com.example.zooapp.AnimalsDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zooapp.R;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<ListDet> {
    private Context Acont;
    private List<AnimalsData> Adata;

    public AnimalsAdapter(Context acont, List<AnimalsData> adata) {
        Acont = acont;
        Adata = adata;
    }

    @NonNull
    @Override
    public ListDet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Aview = LayoutInflater.from(parent.getContext()).inflate(R.layout.animals_card, parent, false);
        return new ListDet(Aview);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListDet holder, int position) {
        holder.cardsX.setImageResource(Adata.get(position).getImageA());
        holder.CardofAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(Acont, DescAnimals.class);
                i.putExtra("imageA", Adata.get(holder.getAdapterPosition()).getImageA());
                i.putExtra("namesA", Adata.get(holder.getAdapterPosition()).getNamesA());
                i.putExtra("specA", Adata.get(holder.getAdapterPosition()).getSpecA());
                i.putExtra("spanA", Adata.get(holder.getAdapterPosition()).getSpanA());
                i.putExtra("descA", Adata.get(holder.getAdapterPosition()).getDescA());
                Acont.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Adata.size();
    }
}

class ListDet extends RecyclerView.ViewHolder {
    ImageView cardsX;
    CardView CardofAnimals;

    public ListDet(View itemView) {
        super(itemView);
        cardsX = itemView.findViewById(R.id.cardsA);
        CardofAnimals = itemView.findViewById(R.id.CardofAnimals);
    }
}