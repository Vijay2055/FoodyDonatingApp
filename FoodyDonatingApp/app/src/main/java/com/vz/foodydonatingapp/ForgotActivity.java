package com.vz.foodydonatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    EditText emailforReset;
    Button reset;
    FirebaseAuth auth=FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        emailforReset=findViewById(R.id.emailReset);
        reset=findViewById(R.id.Reset);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=emailforReset.getText().toString();

                if(!userEmail.equals("")){

                    auth.sendPasswordResetEmail(userEmail).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgotActivity.this, "We have sent an email to reset your password",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                    else {
                                        Toast.makeText(ForgotActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
                else{
                    Toast.makeText(ForgotActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}