package com.example.zooapp.AnimalsDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zooapp.R;

import java.util.List;

public class AnimalsAdapter2 extends RecyclerView.Adapter<ListDet2>{
    private Context Acont2;
    private List<AnimalsData2> Adata2;

    public AnimalsAdapter2(Context acont2, List<AnimalsData2> adata2) {
        Acont2 = acont2;
        Adata2 = adata2;
    }

    @NonNull
    @Override
    public ListDet2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Aview = LayoutInflater.from(parent.getContext()).inflate(R.layout.animals_card2, parent, false);
        return new ListDet2(Aview);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListDet2 holder, int position) {
        holder.cardsX2.setImageResource(Adata2.get(position).getImageA2());
        holder.CardofAnimals2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(Acont2, DescAnimals.class);
                i.putExtra("imageA", Adata2.get(holder.getAdapterPosition()).getImageA2());
                i.putExtra("namesA", Adata2.get(holder.getAdapterPosition()).getNamesA2());
                i.putExtra("specA", Adata2.get(holder.getAdapterPosition()).getSpecA2());
                i.putExtra("spanA", Adata2.get(holder.getAdapterPosition()).getSpanA2());
                i.putExtra("descA", Adata2.get(holder.getAdapterPosition()).getDescA2());
                Acont2.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Adata2.size();
    }
}
class ListDet2 extends RecyclerView.ViewHolder{
    ImageView cardsX2;
    CardView CardofAnimals2;
    public ListDet2(View itemView) {
        super(itemView);
        cardsX2 = itemView.findViewById(R.id.cardsB);
        CardofAnimals2 = itemView.findViewById(R.id.CardofAnimals2);
    }
}
