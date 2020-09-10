package com.example.sekhatapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

public class AdapterRecycleMain extends RecyclerView.Adapter<AdapterRecycleMain.viewHolder> {
    List<MainPropRecycle> mainPropRecycleList;
    Drawable background;
    View view;
    AdapterRecycleMain(List<MainPropRecycle> mainPropRecycleList){
        this.mainPropRecycleList=mainPropRecycleList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycle,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
         MainPropRecycle mainPropRecycle=mainPropRecycleList.get(position);
        Log.i(TAG, "onBindViewHolder: used");

        holder.recycleHolderLayout.setBackground(mainPropRecycle.getColorBack());
        holder.nameText.setText(mainPropRecycle.getName());
        holder.priceText.setText(mainPropRecycle.getPrice());
        holder.productImage.setImageBitmap(mainPropRecycle.getPic());
    }

    @Override
    public int getItemCount() {
        return mainPropRecycleList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        LinearLayout recycleHolderLayout;
        TextView priceText;
        TextView nameText;
        ImageView productImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            recycleHolderLayout=itemView.findViewById(R.id.recycleHolderLayout);
            priceText=itemView.findViewById(R.id.priceText);
            nameText=itemView.findViewById(R.id.nameText);
            productImage=itemView.findViewById(R.id.productImage);
        }
    }
}
