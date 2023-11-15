package com.vz.foodydonatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vz.foodydonatingapp.models.CustomerInfo;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    EditText getMobileNum;
    Button sendSms;
    FirebaseDatabase database;
    DatabaseReference reference;
    String selectRole;
    CustomerInfo customerInfo;
    TextView donar,volunteer;
    FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        getMobileNum=findViewById(R.id.getMobile);
        sendSms=findViewById(R.id.SendSms);
        donar=findViewById(R.id.phoneDonar);
        volunteer=findViewById(R.id.phoneVolunteer);
        customerInfo=new CustomerInfo();

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
                volunteer.setBackgroundResource(R.drawable.shape);
                donar.setBackgroundResource(R.drawable.btn_shape);
            }
        });




                sendSms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userPhone=getMobileNum.getText().toString().trim();

                        if(!TextUtils.isEmpty(userPhone) && selectRole != null){
                            if(userPhone.length()==10) {
                                userPhone="+91"+userPhone;
                                PhoneAuthOptions options = PhoneAuthOptions.newBuilder().
                                        setPhoneNumber(userPhone)
                                        .setTimeout(60L, TimeUnit.SECONDS)
                                        .setActivity(PhoneActivity.this)
                                        .setCallbacks(mCallbacks)
                                        .build();

                                PhoneAuthProvider.verifyPhoneNumber(options);

                                //adding data to the firebase

                            }else{
                                Toast.makeText(PhoneActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(PhoneActivity.this, "Please Enter mobile number and also select a role", Toast.LENGTH_SHORT).show();
                        }
            }
        });
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider
            .OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(PhoneActivity.this, "6 digit code is sent to your number", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PhoneActivity.this, OtpVerification.class);
            intent.putExtra("verificationId", s);
            intent.putExtra("selectRole",selectRole);
            intent.putExtra("phoneNumber", getMobileNum.getText().toString());
            startActivity(intent);
        }
    };

    public void addDataToFirebase(String mobile,String role){
        customerInfo.setMobile(mobile);
        customerInfo.setRole(role);
        String userId = reference.push().getKey();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                reference.child(userId).setValue(customerInfo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}