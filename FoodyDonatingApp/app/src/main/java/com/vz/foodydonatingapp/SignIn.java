package com.vz.foodydonatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    Button signIn;
    EditText signInEmail,password;
    ProgressDialog progressDialog;
    TextView forgotPass,signInGoogle,signInFb,signInPhone,signUp;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signIn=findViewById(R.id.SignInButton);
        signUp=findViewById(R.id.SignUp);
        signInEmail=findViewById(R.id.emailField);
        password=findViewById(R.id.passwordEdit);
        forgotPass=findViewById(R.id.forgot);
        signInGoogle=findViewById(R.id.SignWithGoogle);
        signInFb=findViewById(R.id.SignWithFb);
        signInPhone=findViewById(R.id.SignInWithphone);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        progressDialog=new ProgressDialog(SignIn.this);
        progressDialog.setTitle("Signing In...");
        progressDialog.setMessage("Please wait....");


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMail=signInEmail.getText().toString();
                String userPass=password.getText().toString();

                if(!userMail.equals("") && !userPass.equals("")){
                    progressDialog.show();
                    signInFirebase(userMail,userPass);

                }
                else{
                    Toast.makeText(SignIn.this, "Please enter your password and email first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignIn.this,ForgotActivity.class);
                startActivity(i);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        signInPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignIn.this,PhoneActivity.class);
                startActivity(i);
            }
        });


    }





    public void signInFirebase(String userMail,String userPass){


            auth.signInWithEmailAndPassword(userMail, userPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent i=new Intent(SignIn.this,MainActivity.class);

                                startActivity(i);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Intent i=new Intent(SignIn.this,MainActivity.class);
            startActivity(i);
        }

    }
}