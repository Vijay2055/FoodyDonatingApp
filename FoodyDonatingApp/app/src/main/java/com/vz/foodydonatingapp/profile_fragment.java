package com.vz.foodydonatingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.vz.foodydonatingapp.R;
import com.vz.foodydonatingapp.models.CustomerInfo;

import de.hdodenhof.circleimageview.CircleImageView;


public class profile_fragment extends Fragment {
    TextView role,profession,contact,location,name;
    CircleImageView profileImg;
    FirebaseAuth auth;
    FirebaseDatabase database;

    DatabaseReference reference;



    public profile_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        LinearLayout history=view.findViewById(R.id.history);
        TextView logOut=view.findViewById(R.id.logOut);
        ImageView imageView=view.findViewById(R.id.imageView3);

        profession=view.findViewById(R.id.profilepro);
        role=view.findViewById(R.id.profile_role);
        contact=view.findViewById(R.id.profile_contact);
        name=view.findViewById(R.id.profile_name);
        location=view.findViewById(R.id.profile_location);
        profileImg=view.findViewById(R.id.fragment_profile);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        String id=auth.getCurrentUser().getUid();



        reference.child("User").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    CustomerInfo cust=snapshot.getValue(CustomerInfo.class);
                    Picasso.get().
                            load(cust.getProfilepic())
                            .placeholder(R.drawable.monkey)
                            .into(profileImg);
                    name.setText(cust.getName());
                    contact.setText(cust.getMobile());
                    location.setText(cust.getAddress());
                    profession.setText(cust.getProfession());
                    role.setText(cust.getRole());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "histry is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),editProfile.class);
                startActivity(intent);

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        FirebaseAuth.getInstance().signOut();
                        Intent i=new Intent(getContext(),SignIn.class);
                        startActivity(i);

            }
        });
        return view;
    }


}