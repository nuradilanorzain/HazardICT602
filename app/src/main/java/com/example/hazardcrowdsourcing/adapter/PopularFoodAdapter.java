package com.example.hazardcrowdsourcing.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hazardcrowdsourcing.Model.PopularFood;
import com.example.hazardcrowdsourcing.R;

import java.util.List;


public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder> {

    Context context;
    Dialog myDialog;
    List<PopularFood> popularFoodList;



    public PopularFoodAdapter(Context context, List<PopularFood> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.team_list, parent, false);
        myDialog = new Dialog(context);
        return new PopularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodViewHolder holder, int position) {



        holder.foodImage.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.name.setText(popularFoodList.get(position).getName());
        holder.sid.setText(popularFoodList.get(position).getSid());
        holder.group.setText(popularFoodList.get(position).getGroup());
        holder.code.setText(popularFoodList.get(position).getCode());
        holder.fakulti.setText(popularFoodList.get(position).getFakulti());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtclose,sid,name,group,code,fakulti;
                myDialog.setContentView(R.layout.custompopup);
                ImageView image;
                image = (ImageView) myDialog.findViewById(R.id.profile1);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                sid =(TextView) myDialog.findViewById(R.id.sid);
                name =(TextView) myDialog.findViewById(R.id.name);
                group =(TextView) myDialog.findViewById(R.id.group);
                code =(TextView) myDialog.findViewById(R.id.code);
                fakulti =(TextView) myDialog.findViewById(R.id.fakulti);

                txtclose.setText("x");
                image.setImageResource(popularFoodList.get(position).getImageUrl());
                sid.setText(popularFoodList.get(position).getSid());
                name.setText(popularFoodList.get(position).getName());
                group.setText(popularFoodList.get(position).getGroup());
                code.setText(popularFoodList.get(position).getCode());
                fakulti.setText(popularFoodList.get(position).getFakulti());


                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }


    public static final class PopularFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView sid, name,group,code,fakulti;

        public PopularFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            name = itemView.findViewById(R.id.name);
            sid = itemView.findViewById(R.id.sid);
            group = itemView.findViewById(R.id.group);
            code = itemView.findViewById(R.id.code);
            fakulti = itemView.findViewById(R.id.fakulti);



        }
    }

}
