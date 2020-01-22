package com.example.e_shipmentauctionsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class home2 extends AppCompatActivity {
    SearchView mySearchView;
    ArrayList<AdapterItems>    listnewsData = new ArrayList<AdapterItems>();
    MyCustomAdapter myadapter;
    String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        getSupportActionBar().setTitle("Home");
        if(getIntent().getExtras()!=null){
        Bundle b=getIntent().getExtras();
        //if(b.getString("mail2")!=null){
            name1 = b.getString("mail2");
        }
        mySearchView=(SearchView)findViewById(R.id.searchview);
       // getSupportActionBar().setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //add data and view it
        //listnewsData.add(new AdapterItems(R.drawable.Electronics,"developer"));
        myadapter=new MyCustomAdapter(listnewsData);
        ListView lsNews=(ListView)findViewById(R.id.lvlist);
        lsNews.setAdapter(myadapter);//intisal with data

        //update  data in listview
        listnewsData.add(new AdapterItems(R.drawable.electronics,"Electronic"));
        listnewsData.add(new AdapterItems(R.drawable.books1,"Books"));
        listnewsData.add(new AdapterItems(R.drawable.mercedies,"Vehicles"));
        listnewsData.add(new AdapterItems(R.drawable.sporty,"Sports Item"));
        listnewsData.add(new AdapterItems(R.drawable.furnitur1,"Furniture"));
        listnewsData.add(new AdapterItems(R.drawable.mitv1,"TVs & Appliances"));
        listnewsData.add(new AdapterItems(R.drawable.luggage2,"Luggage"));
        listnewsData.add(new AdapterItems(R.drawable.wardrobe1,"Others Item"));

        myadapter.notifyDataSetChanged();

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               // myadapter.getFilter().filter(s);

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs101 = getSharedPreferences("prefs101", MODE_PRIVATE);
        name1 = prefs101.getString("mail_101",name1);
        //Toast.makeText(getApplicationContext(),"in home2 "+ name1,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs101 = getSharedPreferences("prefs101", MODE_PRIVATE);
        SharedPreferences.Editor editor101 = prefs101.edit();

        editor101.putString("mail_101",name1);
        editor101.apply();
        //Toast.makeText(getApplicationContext(),"in home2 stop "+ name1,Toast.LENGTH_SHORT).show();
    }

    private class MyCustomAdapter extends BaseAdapter {
        public  ArrayList<AdapterItems>  listnewsDataAdpater ;


        public MyCustomAdapter(ArrayList<AdapterItems>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.types, null);

            final   AdapterItems s = listnewsDataAdpater.get(position);

            ImageView iv=( ImageView)myView.findViewById(R.id.imageView);
            iv.setBackgroundResource(s.ID);

            TextView tv=(TextView)myView.findViewById(R.id.name);
            tv.setText(s.product);

            Button bu=(Button)myView.findViewById(R.id.button);
            bu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoToNext();
                }
            });
            return myView;
        }

        private void GoToNext() {
            Intent intent=new Intent(home2.this,productdescActivity.class);
            intent.putExtra("mail3", name1);
            startActivity(intent);
        }


    }

}
