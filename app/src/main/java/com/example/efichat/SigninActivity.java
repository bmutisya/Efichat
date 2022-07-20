package com.example.efichat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.efichat.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {
ActivitySigninBinding binding;
ProgressDialog progressDialog;
FirebaseAuth mAuth;
FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(SigninActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait\n Validation in Progress");

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.txtEmail.getText().toString().isEmpty() && !binding.txtPassword.getText().toString().isEmpty()) {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(binding.txtEmail.getText().toString(),binding.txtPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()){
                                        Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                                        startActivity(intent);

                                    }else {
                                        Toast.makeText(SigninActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else {

                    Toast.makeText(SigninActivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (mAuth.getCurrentUser()!=null){
           Intent intent=new Intent(SigninActivity.this,MainActivity.class);
           startActivity(intent);
        }
        binding.txtClickSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}