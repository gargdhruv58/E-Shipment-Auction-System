package com.example.e_shipmentauctionsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myproduct extends AppCompatActivity {
    ListView lvGoods;
    DatabaseReference dbProduct;
    List<Product> productList;
   String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproduct);
        getSupportActionBar().setTitle("My Product");
        if((getIntent().getExtras())!=null) {
            Bundle b = getIntent().getExtras();
            name1 = b.getString("mail5");
       }
        dbProduct = FirebaseDatabase.getInstance().getReference(name1);

        lvGoods=(ListView) findViewById(R.id.lvGoods);
        productList= new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for(DataSnapshot prSnapshot: dataSnapshot.getChildren())
                {
                    Product pr3=prSnapshot.getValue(Product.class);
                    productList.add(pr3);
                }
                ProductList adapter= new ProductList(myproduct.this,productList);
                lvGoods.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
