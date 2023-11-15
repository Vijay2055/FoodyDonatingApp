package com.vz.foodydonatingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vz.foodydonatingapp.R;
import com.vz.foodydonatingapp.chatting;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.viewHolder> {
    String userName;
    Context mcontext;
    FirebaseDatabase database;
    DatabaseReference reference;

    public ChatAdapter(String userName, Context mcontext, List<String> userList) {
        this.userName = userName;
        this.mcontext = mcontext;
        this.userList = userList;
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
    }

    List<String> userList;
    public ChatAdapter(List<String> userList){
        this.userList=userList;

    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        reference.child("User").child(userList.get(position)).child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String othername=snapshot.child("name").getValue().toString();
                String imgUrl=snapshot.child("profilepic").getValue().toString();
                holder.chattext.setText(othername);
                if(imgUrl.equals("nulls")){
//                    holder.chatprof.setImageURI(R.drawable.monkey);

                }else{
                    Picasso.get().load(imgUrl).into(holder.chatprof);

                }

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(mcontext, chatting.class);
                        i.putExtra("userName",userName);
                        i.putExtra("otherName",othername);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView chattext;
        CircleImageView chatprof;
        CardView cardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            chattext=itemView.findViewById(R.id.chatTxt);
            chatprof=itemView.findViewById(R.id.chat_profile);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }
}
