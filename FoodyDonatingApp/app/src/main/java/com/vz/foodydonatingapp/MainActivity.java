package com.vz.foodydonatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vz.foodydonatingapp.Adapters.RecyclerHomeAdapter;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnView;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FloatingActionButton donate;
    String selectedRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnView = findViewById(R.id.bnView);

        donate=findViewById(R.id.flaotinActionButton);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference = database.getReference();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userId = auth.getCurrentUser().getUid();
               selectedRole=(String) snapshot.child("User").child(userId).child("role").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });






        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedRole!=null){
                    if (selectedRole.equals("Volunteer"))
                        loadFragment(new pickup_fragment(),true);
                    else
                        loadFragmentRm(new donate_fragment(),true);


                }else{
                    Toast.makeText(MainActivity.this, "No network, Please connect to your network", Toast.LENGTH_SHORT).show();

                }

                }

        });


        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    loadFragment(new home_fragment(),true);

                } else if (id == R.id.track) {

//                    loadFragment(new location_fragment(),false);
                    loadFragment(new MapsFragment(),false);

                } else if (id == R.id.chat) {
                    loadFragment(new chat_fragment(),false);

                } else if(id==R.id.profile) {
                    loadFragment(new profile_fragment(),false);

                }


                return true;
            }
        });

        bnView.setSelectedItemId(R.id.home);

    }

    public void loadFragment(Fragment fragment,boolean flag){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        if(flag)
          ft.add(R.id.container,fragment);
        ft.replace(R.id.container,fragment);
        ft.commit();
    }

    private void loadFragmentRm(Fragment fragment, boolean hideBottomNavigation) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);


        if (hideBottomNavigation) {
            // Hide the bottom navigation bar when navigating to the donate_fragment
            BottomAppBar bottomNavigationView = findViewById(R.id.bottomAppbar);
            bottomNavigationView.setVisibility(View.GONE);
            // hiding the floating item

            FloatingActionButton actionButton=findViewById(R.id.flaotinActionButton);
            actionButton.setVisibility(View.GONE);
        }

        transaction.commit();
    }
}