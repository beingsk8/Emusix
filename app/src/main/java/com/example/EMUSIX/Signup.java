package com.example.EMUSIX;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText emailText;
    EditText passwordText;
    Button signupbtn;
    Button loginbtn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        emailText = (EditText) findViewById(R.id.email_id_text);
        passwordText = (EditText) findViewById(R.id.password_id_text);
        signupbtn = (Button) findViewById(R.id.signup_button);
        loginbtn = (Button) findViewById(R.id.login_button);

        loginbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext() , Login.class);
                        startActivity(intent);
                    }
                }
        );

        signupbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = emailText.getText().toString();
                        String password = passwordText.getText().toString();
                        firebaseAuth = FirebaseAuth.getInstance();
                        if(email.isEmpty()){
                            emailText.setError("please enter your email id");
                            emailText.requestFocus();
                        }else if(password.isEmpty()){
                            passwordText.setError("please enter your password");
                            passwordText.requestFocus();
                        }else if(!email.isEmpty() && !password.isEmpty()){
                            firebaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(!task.isSuccessful()){
                                                Toast.makeText(Signup.this , "Register Failed" , Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(Signup.this , "Register successfully" , Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext() , Login.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                            );
                        }
                    }
                }
        );
    }
}

