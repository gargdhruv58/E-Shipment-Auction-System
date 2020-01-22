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

public class MainActivity extends AppCompatActivity {
    EditText emailId,password;
    TextView tvRegister;
    Button btnLogin,btnLogin2;
    private FirebaseAuth mauth;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("E-Shipment Auction System");
        emailId=(EditText)findViewById(R.id.et_email);
        password=(EditText)findViewById(R.id.et_password);
     btnLogin=(Button)findViewById(R.id.btn_login);
     btnLogin2=(Button)findViewById(R.id.btn_login2);
     tvRegister=(TextView)findViewById(R.id.tv_register);
     mauth=FirebaseAuth.getInstance();



     tvRegister.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainActivity.this, RegisterActivity.class));
         }
     });

     btnLogin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String email=emailId.getText().toString();
             String pwd=password.getText().toString();
              mail=emailId.getText().toString();
             //condition checking or authentication
             if(email.isEmpty()){
                 emailId.setError("Please enter email id");
                 emailId.requestFocus();
             }
             //password checking
             else if(pwd.isEmpty()){
                 password.setError("Please enter your password");
                 password.requestFocus();
             }
             else if(email.isEmpty() && pwd.isEmpty())
             {
                 Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
             }

             else if(!(email.isEmpty() && pwd.isEmpty())) {
              mauth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful())
                      {
                          Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(MainActivity.this,Main2custActivity.class);
                          intent.putExtra("mail1", mail);
                         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(intent);

                      }
                      else
                      {
                          Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
              });

             }
         }
     });

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                mail=emailId.getText().toString();
                //condition checking or authentication
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                //password checking
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty() && pwd.isEmpty())) {
                    mauth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this,Transporter_1.class);
                                intent.putExtra("bdmail", mail);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}
