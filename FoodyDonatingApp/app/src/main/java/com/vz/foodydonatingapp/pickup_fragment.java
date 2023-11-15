package com.vz.foodydonatingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.vz.foodydonatingapp.Adapters.RecyclerHomeAdapter;
import com.vz.foodydonatingapp.Adapters.RecyclerPickUPAdapter;
import com.vz.foodydonatingapp.models.DonationDetail;
import com.vz.foodydonatingapp.models.HomeModel;
import com.vz.foodydonatingapp.models.PickUpModel;

import java.util.ArrayList;


public class pickup_fragment extends Fragment {

    RecyclerView pickupRecycler;
    RecyclerPickUPAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<DonationDetail> arrPickUp=new ArrayList<>();
    public pickup_fragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pickup_fragment, container, false);


        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        pickupRecycler=view.findViewById(R.id.pickup_recycler);
        pickupRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new RecyclerPickUPAdapter(getActivity(),arrPickUp);
        pickupRecycler.setAdapter(adapter);
        database.getReference().child("Donation Detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DonationDetail detail=dataSnapshot.getValue(DonationDetail.class);
                    arrPickUp.add(detail);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
}