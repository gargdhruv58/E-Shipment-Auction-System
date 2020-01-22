package com.example.e_shipmentauctionsystem1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Transporter_1 extends AppCompatActivity {
    ListView lvGoods;
    DatabaseReference dbProduct;
    List<Product> productList;
    String mail,mail1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_1);
        getSupportActionBar().setTitle("Transporter");
        if(getIntent().getExtras()!=null) {
            Bundle b = getIntent().getExtras();
            mail = b.getString("bdmail");
            mail1=mail.replaceAll("@gmail.com","");

        }
        dbProduct = FirebaseDatabase.getInstance().getReference("products");
        lvGoods=(ListView) findViewById(R.id.lvGoods);
        productList= new ArrayList<>();
//
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
                Transporter_Adapter adapter= new Transporter_Adapter(Transporter_1.this,productList);
                lvGoods.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class Transporter_Adapter extends ArrayAdapter<Product>{
        private Activity context;
        private List<Product> productList;

        public Transporter_Adapter(Activity context, List<Product> productList){
            super(context, R.layout.transporter1_layout, productList);
            this.context= context;
            this.productList= productList;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater= context.getLayoutInflater();

            View listViewItem= inflater.inflate(R.layout.transporter1_layout, null, true);

            ImageView imageView2=(ImageView) listViewItem.findViewById(R.id.imageView2);
            TextView tvDesc=(TextView) listViewItem.findViewById(R.id.tvDesc);
            TextView tvWeight=(TextView) listViewItem.findViewById(R.id.tvWeight);
            TextView tvLength=(TextView) listViewItem.findViewById(R.id.tvLength);
            TextView tvWidth=(TextView) listViewItem.findViewById(R.id.tvWidth);
            TextView tvHeight=(TextView) listViewItem.findViewById(R.id.tvHeight);
            TextView  tvSbid=(TextView) listViewItem.findViewById(R.id.tvSbid);
            TextView tvPick=(TextView) listViewItem.findViewById(R.id.tvPick);
            Button btBid=(Button) listViewItem.findViewById(R.id.btBid);



            Product pr2= productList.get(position);


            Picasso.get()
                    .load(pr2.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(imageView2);
            tvDesc.setText(pr2.getPrDesc());
            tvWeight.setText(pr2.getPrWeight());
            tvLength.setText(pr2.getPrLength());
            tvWidth.setText(pr2.getPrWidth());
            tvHeight.setText(pr2.getPrHeight());
            tvSbid.setText(pr2.getStartBid());
            tvPick.setText(pr2.getPrPickup());


            final String bidding=tvSbid.getText().toString();
            final String productId=pr2.getPrID().toString();
            //final String premail=pr2.getPremail().toString();

        btBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Transporter_1.this,transporter_2.class);
                intent.putExtra("key",bidding);
                intent.putExtra("prId",productId);
                intent.putExtra("bdmail", mail1);
                startActivity(intent);

            }
        });
        return listViewItem;
        }
    }
}
