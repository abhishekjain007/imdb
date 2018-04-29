package com.example.abhij.imdb;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {


    private static final String TAG ="Tag" ;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;

    EditText editText_name, editText_userName, editText_email, editText_pass;
    Button signUp, signIn;

    String name, userName, email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

// Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, null);


        editText_name = (EditText) findViewById(R.id.edit_login_name);
        editText_userName = (EditText) findViewById(R.id.edit_login_username);
        editText_email = (EditText) findViewById(R.id.edit_login_email);
        editText_pass = (EditText) findViewById(R.id.edit_login_pass);




    }

    public void onClick(View view) {
        name = editText_name.getText().toString();
        userName = editText_userName.getText().toString();
        email = editText_email.getText().toString();
        password = editText_pass.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

}

