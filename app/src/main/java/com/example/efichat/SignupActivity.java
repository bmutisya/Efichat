package com.example.efichat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.efichat.Models.Users;
import com.example.efichat.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //acce all textvie the binding

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
       getSupportActionBar().hide();
       progressDialog= new ProgressDialog(SignupActivity.this);
       progressDialog.setTitle("Creating Account");
       progressDialog.setMessage("we are creating your account");
       binding.btnSignup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //check the text fields
               if (!binding.txtUsername.getText().toString().isEmpty() && !binding.txtEmail.getText().toString().isEmpty() && !binding.txtPassword.getText().toString().isEmpty()){

                   progressDialog.show();
                   mAuth.createUserWithEmailAndPassword(binding.txtEmail.getText().toString(),binding.txtPassword.getText().toString())
                           .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   progressDialog.dismiss();
                                   if (task.isSuccessful()){
                                       Users user = new Users(binding.txtUsername.getText().toString(),binding.txtEmail.getText().toString(),binding.txtPassword.getText().toString());
                                       String id=task.getResult().getUser().getUid();
                                       database.getReference().child("Users").child(id).setValue(user);
                                       Toast.makeText(SignupActivity.this, "Sign up is Successful", Toast.LENGTH_SHORT).show();

                                   }else {
                                       Toast.makeText(SignupActivity.this,task.getException().toString(), Toast.LENGTH_SHORT).show();

                                   }

                               }

                           });

               }
               else {
                   Toast.makeText(SignupActivity.this, "Enter Credential", Toast.LENGTH_SHORT).show();
               }
           }
       });
       binding.txtAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
               startActivity(intent);
           }
       });
    }
}