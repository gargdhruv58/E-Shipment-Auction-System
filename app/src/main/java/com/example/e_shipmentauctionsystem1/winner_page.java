package com.example.e_shipmentauctionsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class winner_page extends AppCompatActivity {

    String productId;
    DatabaseReference dbReference;
    TextView textView304;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_page);
        getSupportActionBar().setTitle("Winner");

        textView304=(TextView) findViewById(R.id.textView304);
        Bundle b=getIntent().getExtras();
        productId=b.getString("prId_2");

        dbReference=FirebaseDatabase.getInstance().getReference();
        dbReference.child("products").child(productId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Customer= dataSnapshot.child("prEmail").getValue(String.class);
                textView304.setText(Customer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dbReference.child("products").child(productId).removeValue();
    }
}
