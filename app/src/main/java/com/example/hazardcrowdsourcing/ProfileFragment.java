package com.example.hazardcrowdsourcing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.hazardcrowdsourcing.Model.PopularFood;
import com.example.hazardcrowdsourcing.adapter.PopularFoodAdapter;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment implements  AdapterView.OnItemClickListener{

    Dialog myDialog;
    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
               View rootView =  inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);//Make sure you have this line of code.
        myDialog = new Dialog(getContext());


        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Nurul Azni Athirah Binti Ahmad Tarmizy","2021793977","RCS2404A","CS240","Fakulti Sains Komputer dan Matematik", R.drawable.azni));
        popularFoodList.add(new PopularFood("Nur Aliah Binti Mufek","2021183021","RCS2404A","CS240","Fakulti Sains Komputer dan Matematik", R.drawable.aliah));
        popularFoodList.add(new PopularFood("Nuradila Binti Norzain","2021986421","RCS2404A","CS240","Fakulti Sains Komputer dan Matematik",R.drawable.adila));


        popularRecycler = rootView.findViewById(R.id.popular_recycler);

        setPopularRecycler(popularFoodList);


       return rootView;
    }

    private void setPopularRecycler(List<PopularFood> popularFoodList) {


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(getContext(),popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    private void URL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}