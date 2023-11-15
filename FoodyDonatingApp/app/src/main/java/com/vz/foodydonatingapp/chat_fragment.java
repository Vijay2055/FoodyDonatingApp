package com.vz.foodydonatingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vz.foodydonatingapp.Adapters.ChatAdapter;
import com.vz.foodydonatingapp.R;

import java.util.ArrayList;
import java.util.List;

public class chat_fragment extends Fragment {
    RecyclerView rv;
    FirebaseDatabase database;
    FirebaseUser user;
    List<String> list;
    ChatAdapter adapter;
    String userName;
    DatabaseReference reference;
    FirebaseAuth auth;




    public chat_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chat_fragment, container, false);
//        auth=FirebaseAuth.getInstance();
//        user=auth.getCurrentUser();
//        database=FirebaseDatabase.getInstance();
//        reference=database.getReference();
//        rv=view.findViewById(R.id.chatrecyclerView);
//       list=new ArrayList<>();
//            reference.child("User").child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    userName=snapshot.getValue().toString();
//                    getUser();
//                    adapter=new ChatAdapter(userName, getContext(), list);
//                    rv.setAdapter(adapter);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });



//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return  view;
    }

    public void getUser(){
        reference.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key=snapshot.getKey();
                if(!key.equals(user.getUid())){
                    list.add(key);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}