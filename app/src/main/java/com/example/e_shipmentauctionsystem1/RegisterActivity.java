package com.example.e_shipmentauctionsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText  emailId,etname,password,etreenterpassword;
    TextView tvrgregister;
    Button btnreglogin,btnreglogin2;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        etname=(EditText)findViewById(R.id.et_name);
        emailId=(EditText)findViewById(R.id.et_reg_email);
        password=(EditText)findViewById(R.id.et_reg_password);
        etreenterpassword=(EditText)findViewById(R.id.et2_reg_password);

        mauth=FirebaseAuth.getInstance();
        tvrgregister=(TextView)findViewById(R.id.tv_reg_register);

        btnreglogin=(Button)findViewById(R.id.btn_reg_login);
        btnreglogin2=(Button)findViewById(R.id.btn_reg_login2);

        tvrgregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnreglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                String rpwd=etreenterpassword.getText().toString();

                //condition checking or authentication
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if(!(pwd.equals(rpwd)))
                {
                    Toast.makeText(RegisterActivity.this, "Password did not Match", Toast.LENGTH_SHORT).show();
                }
                //password checking
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mauth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful())
                             {
                                 Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                 Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                 startActivity(intent);
                             }
                             else
                             {
                                 Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                             }
                        }
                    });
                }
            }
        });
        btnreglogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                String rpwd=etreenterpassword.getText().toString();

                //condition checking or authentication
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if(!(pwd.equals(rpwd)))
                {
                    Toast.makeText(RegisterActivity.this, "Password did not Match", Toast.LENGTH_SHORT).show();
                }
                //password checking
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mauth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
