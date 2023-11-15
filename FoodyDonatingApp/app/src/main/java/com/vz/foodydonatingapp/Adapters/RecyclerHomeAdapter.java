package com.vz.foodydonatingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vz.foodydonatingapp.R;
import com.vz.foodydonatingapp.models.HomeModel;

import java.util.ArrayList;



public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.viewHolder> {
    Context context;
    ArrayList<HomeModel> arrHome=new ArrayList<>();

    public RecyclerHomeAdapter(Context context, ArrayList<HomeModel> arrHome){
      this.context=context;
      this.arrHome=arrHome;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.home_cardview,parent,false);
        viewHolder viw=new viewHolder(view);


        return viw;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.profileView.setImageResource(arrHome.get(position).getProfileImg());
        holder.likeView.setImageResource(arrHome.get(position).getLove());
        holder.chatView.setImageResource(arrHome.get(position).getChat());
        holder.shareView.setImageResource(arrHome.get(position).getShare());
        holder.food1View.setImageResource(arrHome.get(position).getFood1());
        holder.food2View.setImageResource(arrHome.get(position).getFood2());
        holder.food3View.setImageResource(arrHome.get(position).getFood3());
        holder.moreView.setImageResource(arrHome.get(position).getMoreImg());

        holder.name.setText(arrHome.get(position).getName());
        holder.timeAgo.setText(arrHome.get(position).getMinsAgo());
        holder.minCount.setText(arrHome.get(position).getCountMin());
        holder.writeSome.setText(arrHome.get(position).getWriteSome());
        holder.countCom.setText(arrHome.get(position).getCountComment());
        holder.countShare.setText(arrHome.get(position).getCountShare());
        holder.comment.setText(arrHome.get(position).getComments());
        holder.share.setText(arrHome.get(position).getsHare());


    }

    @Override
    public int getItemCount() {
        return arrHome.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView likeView,chatView,shareView,food1View,food2View,food3View,moreView;
        ImageView profileView;
        TextView name,minCount,timeAgo,writeSome,countCom,countShare,comment,share;

        public  viewHolder(@NonNull View itemView) {
            super(itemView);

            profileView=itemView.findViewById(R.id.photo_profile);
            likeView=itemView.findViewById(R.id.love_img);
            chatView=itemView.findViewById(R.id.chat_img);
            shareView=itemView.findViewById(R.id.share_img);
            food1View=itemView.findViewById(R.id.image_food1);
            food2View=itemView.findViewById(R.id.image_food2);
            food3View=itemView.findViewById(R.id.image_food3);
            moreView=itemView.findViewById(R.id.moreOption);

            name=itemView.findViewById(R.id.userName);
            timeAgo=itemView.findViewById(R.id.time_ago);
            minCount=itemView.findViewById(R.id.minCount);
            writeSome=itemView.findViewById(R.id.writeSomething);
            countCom=itemView.findViewById(R.id.commentCount);
            countShare=itemView.findViewById(R.id.shareCount);
           comment=itemView.findViewById(R.id.comment);
            share=itemView.findViewById(R.id.share);
        }
    }
}
