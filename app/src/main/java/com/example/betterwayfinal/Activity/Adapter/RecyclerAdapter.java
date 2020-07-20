package com.example.betterwayfinal.Activity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterwayfinal.R;
import com.example.betterwayfinal.Activity.model.Recycler;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private List<Recycler> listaCord;

    public RecyclerAdapter(List<Recycler> lista) {
        this.listaCord = lista;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                                                .inflate(R.layout.lista_recycler, parent, false);

        return new MyviewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        Recycler recycler = listaCord.get(position);
        holder.cord.setText((int) recycler.getCordX());
        holder.cord.setText((int) recycler.getCordY());
        holder.cord.setText((int) recycler.getCordZ());
        holder.cord.setText( recycler.gettipoDeDesnivel() );
        holder.cord.setText((int) recycler.getLatitude());
        holder.cord.setText((int) recycler.getLongitude());

    }

    @Override
    public int getItemCount() {
        return this.listaCord.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView cord;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            cord = itemView.findViewById(R.id.textViewRecycler);
        }
    }

}
