package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.HashMap;

public class RegisterP extends AppCompatActivity {
    EditText Rmail,Rpass,username;
    Button signup;
    TextView login;
    private FirebaseAuth fireAuth;
    ProgressDialog prog;
    private CircularImageView pickImg;
    private static final int PICK_IMAGE = 1;
    Uri resultImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerp);
        ActionBar bar=getSupportActionBar();
        bar.setTitle("Create Account");

        //pickImage from gallery
        pickImg=findViewById(R.id.pickImage);
        pickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });


        fireAuth=FirebaseAuth.getInstance();
        Rmail=findViewById(R.id.Rmail);
        Rpass=findViewById(R.id.Rpass);
        signup=findViewById(R.id.signup);
        username=findViewById(R.id.name);
        login=findViewById(R.id.loginpage);

        //progress
        prog=new ProgressDialog(this);
        prog.setMessage("Registering User...");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=Rmail.getText().toString();
                String pass=Rpass.getText().toString();
                String name=username.getText().toString();
                //validation
                if(name.isEmpty()){
                    username.setError("Enter your name");
                    username.setFocusable(true);
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    Rmail.setError("Invalid e-mail");
                    Rmail.setFocusable(true);
                }else  if(pass.length()<6){
                    Rpass.setError("Password length must contain 6 characters");
                    Rpass.setFocusable(true);
                }else{
                    registerUser(mail,pass);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterP.this,LoginP.class));
            }
        });


    }

    private void registerUser(String mail, String pass) {
        prog.show();
        fireAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    prog.dismiss();
                    FirebaseUser user=fireAuth.getCurrentUser();

                    //email & uid from fireauth
                    String usermail= user.getEmail();
                    String uid=user.getUid();
                    HashMap<Object,String> hash=new HashMap<>();
                    hash.put("email",usermail);
                    hash.put("name","");
                    hash.put("pro-pic","");
                    hash.put("uid",uid);
                    //firebase database
                    FirebaseDatabase data=FirebaseDatabase.getInstance();
                    DatabaseReference reference=data.getReference("Users");
                    reference.child(uid).setValue(hash);


                    Toast.makeText(RegisterP.this,"Registered...\n"+user.getEmail(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterP.this,LoginP.class));
                    finish();
                }else {
                    prog.dismiss();
                    Toast.makeText(RegisterP.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                prog.dismiss();
                Toast.makeText(RegisterP.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            resultImg = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultImg);
                pickImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        super.onBackPressed();
    }
}