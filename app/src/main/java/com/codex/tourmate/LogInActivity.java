package com.codex.tourmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LogInActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailET = findViewById(R.id.email_ET);
        passwordET = findViewById(R.id.password_ET);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user!=null){
            finish();
            goToMainActivity();
        }

    }

    public void logIn(View view) {

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            auth = FirebaseAuth.getInstance();
           auth.signInWithEmailAndPassword(email,password)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                          goToMainActivity();

                       }
                   }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {

               }
           });
        }
    }

    public void goToSignUp(View view) {
        Intent gotoSignUp = new Intent(this, SignUpActivity.class);
        startActivity(gotoSignUp);

    }

    public void goToMainActivity(){
        Intent intent = new Intent(LogInActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
