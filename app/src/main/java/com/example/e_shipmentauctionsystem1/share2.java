package com.example.e_shipmentauctionsystem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class share2 extends AppCompatActivity {
    TextView tview;
    Button sharetxtbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share2);
        getSupportActionBar().setTitle("Share");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tview=(TextView)findViewById(R.id.tview);
        sharetxtbutton=(Button)findViewById(R.id.sharetxtbtn);

        sharetxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="Download our App";
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"write your subject here");
                intent.putExtra(Intent.EXTRA_TEXT,text);
                startActivity(Intent.createChooser(intent,"Share text via"));

            }
        });
    }
}
