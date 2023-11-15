package com.vz.foodydonatingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vz.foodydonatingapp.R;
import com.vz.foodydonatingapp.models.DonationDetail;


public class donate_fragment extends Fragment {
    String pickTime;
    DonationDetail detail;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String  selectDay;
    EditText pickUp,items,ngo;
    RadioGroup radioGrp;
    RadioButton today,tomorrow,dayAfter;
    TextView first,second,third,fourth,fifth;
    Button confirm;


    public donate_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_donate_fragment, container, false);



        pickUp=view.findViewById(R.id.donateLocation);
        items=view.findViewById(R.id.donateitem);
        ngo=view.findViewById(R.id.toNgo);
        radioGrp=view.findViewById(R.id.radioGroup8);
        today=view.findViewById(R.id.todayDonation);
        tomorrow=view.findViewById(R.id.tommorowDonation);
        dayAfter=view.findViewById(R.id.dayAfterDonation);
        first=view.findViewById(R.id.firsttime);
        second=view.findViewById(R.id.secondtime);
        third=view.findViewById(R.id.thirdTime);
        fourth=view.findViewById(R.id.fourthTime);
        fifth=view.findViewById(R.id.fifthTime);
        confirm=view.findViewById(R.id.confirmDoantoin);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading...");
        progressDialog.setMessage("Please wait...");

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime=first.getText().toString();
                fourth.setBackgroundResource(R.drawable.shape);
                third.setBackgroundResource(R.drawable.shape);
                second.setBackgroundResource(R.drawable.shape);
                first.setBackgroundResource(R.drawable.btn_shape_dontaion);
                fifth.setBackgroundResource(R.drawable.shape);

            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime=second.getText().toString();
                fourth.setBackgroundResource(R.drawable.shape);
                third.setBackgroundResource(R.drawable.shape);
                second.setBackgroundResource(R.drawable.btn_shape_dontaion);
                first.setBackgroundResource(R.drawable.shape);
                fifth.setBackgroundResource(R.drawable.shape);

            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime=third.getText().toString();
                fourth.setBackgroundResource(R.drawable.shape);
                third.setBackgroundResource(R.drawable.btn_shape_dontaion);
                second.setBackgroundResource(R.drawable.shape);
                first.setBackgroundResource(R.drawable.shape);
                fifth.setBackgroundResource(R.drawable.shape);

            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime=fourth.getText().toString();
                fourth.setBackgroundResource(R.drawable.btn_shape_dontaion);
                third.setBackgroundResource(R.drawable.shape);
                second.setBackgroundResource(R.drawable.shape);
                first.setBackgroundResource(R.drawable.shape);
                fifth.setBackgroundResource(R.drawable.shape);

            }
        });

        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime=fifth.getText().toString();
                fourth.setBackgroundResource(R.drawable.shape);
                third.setBackgroundResource(R.drawable.shape);
                second.setBackgroundResource(R.drawable.shape);
                first.setBackgroundResource(R.drawable.shape);
                fifth.setBackgroundResource(R.drawable.btn_shape_dontaion);


            }
        });





        radioGrp.clearCheck();
        // Check which radio button has been clicked
        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            // Get the selected Radio Button
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location=pickUp.getText().toString();
                String itemsDetail=items.getText().toString();
                String ngoName=ngo.getText().toString();


                int selectedItem=radioGrp.getCheckedRadioButtonId();
                if(selectedItem!=-1){
                    RadioButton radioButton=(RadioButton) radioGrp.findViewById(selectedItem);
                    selectDay=radioButton.getText().toString();
                }

                if(!location.equals("") && !itemsDetail.equals("") && !ngoName.equals("")){
                    if(selectDay!=null){
                       if(pickTime!=null){
                           progressDialog.show();
                           uploadDataToFirebase(location,itemsDetail,ngoName,selectDay,pickTime);


                       }else{
                           Toast.makeText(getContext(), "Please, choose pickup time", Toast.LENGTH_SHORT).show();
                       }

                    }else{
                        Toast.makeText(getContext(), "Please, choose pickup day", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getContext(), "Please, fill all the field first", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }

    public void uploadDataToFirebase(String location,String items,String ngoName,
                                     String selectedDay,String selectedTime){

        detail=new DonationDetail(location,items,selectedTime,selectedDay,ngoName);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id=auth.getCurrentUser().getUid();
                detail.setDonationId(id);
                if(snapshot.child("User").child(id).hasChild("name")){
                    String name=(String)snapshot.child("User").child(id).child("name").getValue();
                    detail.setDonarName(name);

                }
                if(snapshot.child("User").child(id).hasChild("mobile")) {
                    String mobile=(String)snapshot.child("User").child(id).child("mobile").getValue();
                    detail.setMobile(mobile);
                }
                reference.child("Donation Detail").child(id).setValue(detail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Your donation is uploaded, Thank you", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),"Can't Be uploaded. Try again" , Toast.LENGTH_SHORT).show();

            }
        });

    }



}