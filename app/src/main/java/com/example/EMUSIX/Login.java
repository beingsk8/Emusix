package com.example.EMUSIX;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button login_btn;
    Button signup_btn;
    EditText email_text;
    EditText password_text;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        email_text = (EditText) findViewById(R.id.login_email_id_text);
        password_text = (EditText) findViewById(R.id.login_password_id_text);
        signup_btn = (Button) findViewById(R.id.signup_btn);
        login_btn = (Button) findViewById(R.id.login_btn);

        signup_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext() , Signup.class);
                        startActivity(intent);
                    }
                }
        );

        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String emailId = email_text.getText().toString();
                        String passwordString = password_text.getText().toString();
                        firebaseAuth = FirebaseAuth.getInstance();
                        if(emailId.isEmpty()){
                            email_text.setError("please enter your email id");
                            email_text.requestFocus();
                        }else if(passwordString.isEmpty()){
                            password_text.setError("please enter your password");
                            password_text.requestFocus();
                        }else if(!emailId.isEmpty() && !passwordString.isEmpty()){
                            firebaseAuth.signInWithEmailAndPassword(emailId , passwordString).addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Login.this , "Login Successfully" , Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                Toast.makeText(Login.this , "Login Failed" , Toast.LENGTH_SHORT).show();
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