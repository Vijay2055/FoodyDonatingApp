package com.vz.foodydonatingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vz.foodydonatingapp.models.CustomerInfo;

public class OtpVerification extends AppCompatActivity {

    EditText one,two,three,four,five,six;
    Button confirm;
    CustomerInfo customerInfo;
    TextView sendAgain;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    private String verificationId;
    private String phoneNumber;
    private String selectRole;

    TextView showMobileNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        one=findViewById(R.id.digitone);
        two=findViewById(R.id.digittwo);
        three=findViewById(R.id.digitthree);
        four=findViewById(R.id.digitfour);
        five=findViewById(R.id.digitfive);
        six=findViewById(R.id.digitsix);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("User");
        showMobileNum=findViewById(R.id.showTextNumber);

        //using TextWatcher to track all the keyword
        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    two.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    three.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    four.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    five.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    six.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        confirm=findViewById(R.id.confirm);
        sendAgain=findViewById(R.id.sendagain);

        Intent intent = getIntent();
        verificationId = intent.getStringExtra("verificationId");
        phoneNumber = intent.getStringExtra("phoneNumber");
        selectRole=intent.getStringExtra("selectRole");
        showMobileNum.setText(phoneNumber);




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String digitOne=one.getText().toString();
                String digitTwo=two.getText().toString();
                String digitThree=three.getText().toString();
                String digitFour=four.getText().toString();
                String digitFive=five.getText().toString();
                String digitSix=six.getText().toString();
                String otp=digitOne+digitTwo+digitThree+digitFour+digitFive+digitSix;

                otp.trim();

                if (!TextUtils.isEmpty(otp)) {
                    verifyPhoneNumberWithOTP(otp);
                }
            }
        });
    }

    private void verifyPhoneNumberWithOTP(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        customerInfo=new CustomerInfo(phoneNumber,selectRole);
                        String id=auth.getCurrentUser().getUid();
                        reference.child(id).setValue(customerInfo);


                        Intent i=new Intent(OtpVerification.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {

                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}