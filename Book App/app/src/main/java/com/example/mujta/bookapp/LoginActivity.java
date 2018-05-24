package com.example.mujta.bookapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //Created 4 variables for all all objects created in the xml layout
    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Assigned the variables to the id of the objects
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPwd = (EditText) findViewById(R.id.txtPasswordLogin);
        //created a variable called firebaseAuth to identify the user
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //Function for when the user selects the login button
    public void btnUserLogin_Click(View view){
        //Dialog created to show the progression of the login
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...", true);
        //used the method signInWithEmailAndPassed to retrieve the inputted email and password and see if it matches the details in Firebase
        (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPwd.getText().toString()))
                //user's credentials
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //Pop up will appear saying login was successful
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            //Once successful it will take the user from the login screen to the home screen
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            //the user's email is also passed on to the home screen
                            intent.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(intent);

                        }
                        else {
                            //If user was not successful in logging in, a pop up will appear saying Error
                            Log.e("Error", task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
