package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginP extends AppCompatActivity {
    EditText Lmail,Lpass;
    Button signin;
    TextView reg,fPass;
    private FirebaseAuth fireAuth;
    ProgressDialog prog;
    Dialog dialog;
    private FirebaseAuth.AuthStateListener AuthState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_p);
        ActionBar bar=getSupportActionBar();
        bar.setTitle("User Log In");
        //back button
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);

        reg=findViewById(R.id.registerpage);
        Lmail=findViewById(R.id.Lmail);
        Lpass=findViewById(R.id.Lpass);
        signin=findViewById(R.id.login);

        fireAuth=FirebaseAuth.getInstance();
        AuthState=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser FireUser=fireAuth.getCurrentUser();
                if( FireUser != null ){
                    Toast.makeText(LoginP.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginP.this, Page1.class));
                }

            }
        };
        prog=new ProgressDialog(this);
        prog.setMessage("Loggin In...");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=Lmail.getText().toString();
                String pass=Lpass.getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    Lmail.setError("Invalid e-mail");
                    Lmail.setFocusable(true);
                }
                else  if(pass.isEmpty()){
                    Lpass.setError("Enter your password");
                    Lpass.requestFocus();
                }else {
                    loginUser(mail,pass);
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginP.this,RegisterP.class));
            }
        });
        dialog=new Dialog(this);


    }

    private void loginUser(String mail, String pass) {
        prog.show();
        fireAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    prog.dismiss();
                    FirebaseUser user=fireAuth.getCurrentUser();
                    startActivity(new Intent(LoginP.this,Page1.class));
                    finish();
                }else {
                    prog.dismiss();
                    Toast.makeText(LoginP.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                prog.dismiss();
                Toast.makeText(LoginP.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowpopUp(View v){
        dialog.setContentView(R.layout.recoverpass);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        final AwesomeValidation b=new AwesomeValidation(ValidationStyle.BASIC);
        final TextView recover,cancel;
        final EditText recM;
        cancel=dialog.findViewById(R.id.cancelB);
        recover=dialog.findViewById(R.id.recoverB);
        recM=dialog.findViewById(R.id.recM);
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recmail=recM.getText().toString();
                if(recmail.isEmpty()){
                    b.addValidation(LoginP.this,R.id.recM,Patterns.WEB_URL,R.string.validMail);
                    Toast.makeText(LoginP.this,"Please Enter Recovery Mail",Toast.LENGTH_SHORT).show();
                }else {
                    String mailrecover = recM.getText().toString();
                    beginRecovery(mailrecover);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    private void beginRecovery(String mailrecover) {
            fireAuth.sendPasswordResetEmail(mailrecover).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginP.this,"E-mail Sent",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginP.this,"Incorrect E-mail, try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //go previous activity
        startActivity(new Intent(LoginP.this,RegisterP.class));
        return super.onSupportNavigateUp();
    }


    @Override
    protected void onStart() {
        super.onStart();
        fireAuth.addAuthStateListener(AuthState);
    }
}