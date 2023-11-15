package com.vz.foodydonatingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.vz.foodydonatingapp.models.CustomerInfo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class editProfile extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseStorage storage;
    EditText name,mobile,address,profession;
    CircleImageView userProile;
    ProgressDialog dialog,dialogUpdate;

    ImageView backImage;
    Button save;
    CustomerInfo customerDetails;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name=findViewById(R.id.editName);
        mobile=findViewById(R.id.editNum);
        address=findViewById(R.id.editAddress);
        profession=findViewById(R.id.editProfession);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference();
        storage=FirebaseStorage.getInstance();
        dialog=new ProgressDialog(editProfile.this);
        dialog.setTitle("Updating");
        dialog.setMessage("Please wait until it is saving");
        dialogUpdate=new ProgressDialog(editProfile.this);
        dialogUpdate.setTitle("Uploading");
        dialogUpdate.setMessage("Uploading your image, Please wait");


        userProile=findViewById(R.id.yourProfileimg);
        backImage=findViewById(R.id.edtbackimg);

        save=findViewById(R.id.edtSave);

        userProile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,11);
            }
        });

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loadFragment(new profile_fragment(),true);
            }
        });

        ref.child("User").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    CustomerInfo cust=snapshot.getValue(CustomerInfo.class);
                    Picasso.get().
                            load(cust.getProfilepic())
                            .placeholder(R.drawable.monkey)
                            .into(userProile);
                    name.setText(cust.getName());
                    mobile.setText(cust.getMobile());
                    profession.setText(cust.getAddress());
                    address.setText(cust.getProfession());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=name.getText().toString();
                String userMob=mobile.getText().toString();
                String userAdd=address.getText().toString();
                String userProf=profession.getText().toString();

                if(!userName.equals("") && !userMob.equals("") && !userAdd.equals("") && !userProf.equals("")){
                    dialog.show();
                    updateUser(userName,userMob,userAdd,userProf);
                }else{
                    Toast.makeText(editProfile.this, "Fill all the required field", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }



    public void updateUser(String name, String mobile,String address, String profesion){
       DatabaseReference ref2= ref.child("User").child(auth.getUid());
       ref2.child("name").setValue(name);
       ref2.child("address").setValue(address);
       ref2.child("profession").setValue(profesion);
       ref2.child("mobile").setValue(mobile).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
               dialog.hide();
               Toast.makeText(editProfile.this, "Your data is updated", Toast.LENGTH_SHORT).show();

           }
       });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            Uri uri=data.getData();
            userProile.setImageURI(uri);
            dialogUpdate.show();
            final StorageReference storageReference=storage.getReference()
                    .child("profile_pic").child(FirebaseAuth.getInstance().getUid());
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    dialogUpdate.hide();
                    Toast.makeText(editProfile.this, "Profile is saved", Toast.LENGTH_SHORT).show();

                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String id=auth.getCurrentUser().getUid();
                            ref.child("User").child(id).child("profilepic").setValue(uri.toString());
                        }
                    });

                }
            });
        }
    }




}