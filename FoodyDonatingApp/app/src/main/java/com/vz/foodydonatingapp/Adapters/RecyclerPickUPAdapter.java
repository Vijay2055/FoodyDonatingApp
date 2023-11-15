package com.vz.foodydonatingapp.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vz.foodydonatingapp.R;
import com.vz.foodydonatingapp.models.CustomerInfo;
import com.vz.foodydonatingapp.models.DonationDetail;
import com.vz.foodydonatingapp.models.PickUpModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerPickUPAdapter extends RecyclerView.Adapter<RecyclerPickUPAdapter.viewHolder> {
    Context context;
    ArrayList<DonationDetail> list=new ArrayList<>();

    public RecyclerPickUPAdapter(Context context, ArrayList<DonationDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pickup_cardview,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DonationDetail detail=list.get(position);
        holder.pickName.setText(detail.getDonarName());
        holder.pickAmount.setText(detail.getItems_Detail());
        holder.pickLocationText.setText(detail.getPickupAddress());
        holder.pickTime.setText(detail.getPick_up_time());
        FirebaseDatabase.getInstance().getReference().child("User").child(detail.getDonationId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CustomerInfo info=snapshot.getValue(CustomerInfo.class);
                Picasso.get()
                        .load(info.getProfilepic())
                        .placeholder(R.drawable.monkey).into(holder.pickImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        CircleImageView pickImage;
        TextView pickName,pickAmount,pickLocationText,pickTime,pickStatus;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            pickImage=itemView.findViewById(R.id.pickup_profile);
            pickName=itemView.findViewById(R.id.pickupName);
            pickAmount=itemView.findViewById(R.id.pickUpAmount);
            pickLocationText=itemView.findViewById(R.id.pickUPAddress);
            pickTime=itemView.findViewById(R.id.pickUpTime);
            pickStatus=itemView.findViewById(R.id.pickup_text);

        }
    }
}
