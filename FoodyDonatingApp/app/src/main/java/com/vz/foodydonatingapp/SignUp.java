package com.vz.foodydonatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vz.foodydonatingapp.models.CustomerInfo;

public class SignUp extends AppCompatActivity {

    EditText userName,userEmail,userPassword,confirmPass;
    Button signUp;
    String user;
    String selectRole="";
    TextView donar,volunteer,signInNext;
    CustomerInfo customerDetails;
    FirebaseAuth auth;
    FirebaseDatabase database;
//    DatabaseReference reference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userEmail=findViewById(R.id.signUpEmail);
        userName=findViewById(R.id.signUpName);
        userPassword=findViewById(R.id.signUpPassword);
        confirmPass=findViewById(R.id.signUpConfirmPassword);
        signUp=findViewById(R.id.SignUpBotton);
        donar=findViewById(R.id.donar);
        volunteer=findViewById(R.id.volunteer);
        signInNext=findViewById(R.id.sendagain);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        progressDialog=new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating Account...");
        progressDialog.setMessage("Please wait your account is creating...");



        donar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRole="Donar";
                volunteer.setBackgroundResource(R.drawable.btn_shape);
                donar.setBackgroundResource(R.drawable.shape);

            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRole="Volunteer";
                donar.setBackgroundResource(R.drawable.btn_shape);
                volunteer.setBackgroundResource(R.drawable.shape);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name=userName.getText().toString();
                String userMail=userEmail.getText().toString();
                String userPass=userPassword.getText().toString();
                String confrmPass=confirmPass.getText().toString();

                if(!name.equals("")  && !userMail.equals("") && !userPass.equals("") && !confrmPass.equals("") && !selectRole.equals("")){

                    if(userPass.equals(confrmPass)){
                        progressDialog.show();
                        signUp(name,userMail,userPass,selectRole);
                    }else{
                        Toast.makeText(SignUp.this, "Your password is not matched..", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(SignUp.this, "Please enter all the credentials and select your role also", Toast.LENGTH_SHORT).show();
                }





            }


        });

signInNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(SignUp.this,SignIn.class);
        startActivity(i);
        finish();
    }
});




    }



    public void signUp(String name,String userMail,String userPass,String role){


            auth.createUserWithEmailAndPassword(userMail, userPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                customerDetails=new CustomerInfo(name,role,userMail,userPass);
                                String id=task.getResult().getUser().getUid();
                                database.getReference().child("User").child(id).setValue(customerDetails);
//
                                Toast.makeText(SignUp.this, "Your account is created", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(SignUp.this,MainActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(SignUp.this, "Sorry we are facing some issues to create your account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }



}