package com.vz.foodydonatingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vz.foodydonatingapp.Adapters.RecyclerHomeAdapter;
import com.vz.foodydonatingapp.R;
import com.vz.foodydonatingapp.models.HomeModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class home_fragment extends Fragment {

    RecyclerView homeRecyclerView;
    CircleImageView homeImg;
    TextView homeSomething;
    RecyclerHomeAdapter adapter;
    ArrayList<HomeModel> arrHome=new ArrayList<>();
    public home_fragment() {




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_fragment, container, false);

        TextView name= view.findViewById(R.id.topName);





        //Note this is only for testing purpose

          arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));

        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));
        arrHome.add(new HomeModel(R.drawable.girl,R.drawable.more,R.drawable.food1,
                R.drawable.food2,R.drawable.food3,R.drawable.love,R.drawable.chat,R.drawable.share,
                "1","5","20","Farida","mins ago",
                "Felt good after denotinting food to the needy people. The happiness on their face was priceless",
                "Comment","Share"));

        homeRecyclerView= view.findViewById(R.id.home_recycler);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new RecyclerHomeAdapter(getActivity(),arrHome);
        homeRecyclerView.setAdapter(adapter);




        return view;
    }
}