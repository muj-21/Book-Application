package com.example.mujta.bookapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterActivity extends AppCompatActivity {


    //Created 3 variables for all objects in the xml layout
    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Assigned the variables to the id of the objects
        txtEmailAddress = (EditText) findViewById(R.id.txtEmailRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        //created a variable called firebaseAuth to identify the user
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //Function for when the user selects the register button
    public void btnRegisterUser_Click(View view){
        //Dialog created to show the progression of the login
        final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this, "Please wait...", "Processing...", true);
        //used the method createUserWithEmailAndPassword to retrieve the inputted email and password and create an account on Firebase with those credentials
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        //if user was successful in registering an account, a pop up will appear syaing Register successful
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            //The user will be passed on to the next screen
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        //if user was unsuccessful in registering, a pop will appear saying Error
                        else {
                            Log.e("Error", task.getException().toString());
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                });
    }
}
