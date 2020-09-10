package com.example.sekhatapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewItemAdapter extends RecyclerView.Adapter<NewItemAdapter.ViewHolder> {
    List<newItemProp> newItemPropList;

    public NewItemAdapter(List<newItemProp> newItemPropList) {
        this.newItemPropList = newItemPropList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.newitem_recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        newItemProp newitemProp=newItemPropList.get(position);
        holder.newProductPrice.setText(newitemProp.getPrice());
        holder.newProductName.setText(newitemProp.getName());
        holder.newProductImage.setImageBitmap(newitemProp.getProImage());
    }

    @Override
    public int getItemCount() {
        return newItemPropList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newProductImage;
        TextView newProductName,newProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newProductImage=itemView.findViewById(R.id.newProductImage);
            newProductName=itemView.findViewById(R.id.newNameText);
            newProductPrice=itemView.findViewById(R.id.newPriceText);

        }
    }
}
