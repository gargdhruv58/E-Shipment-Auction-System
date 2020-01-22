package com.example.e_shipmentauctionsystem1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Collections.sort;

public class transporter_2 extends AppCompatActivity {

    //Database db;
    TextView bidd;
    TextView CW;
    TextView bidname;
    EditText editBid;
    EditText editBid1;
    Button buBid;
    //Button buShow;
    DatabaseReference databaseBidder;
    List<Integer> bid;
    //ArrayList<Bidder>bb;
    String productId;
    String name1;
    String id2,fmail;
    String opbid="Opening Bid";


    //private static final long START_TIME_IN_MILLIS = 360000;

    //TextView t;
    //CountDownTimer mCountDownTimer;

    //boolean mTimerRunning;

    //long mStartTimeInMillis;

    //long mTimeLeftInMillis=START_TIME_IN_MILLIS;
    //long mEndTime;

    static final long START_TIME_IN_MILLIS = 600000;

    TextView mTextViewCountDown;
    CountDownTimer mCountDownTimer;

    boolean mTimerRunning;
    long mTimeLeftInMillis;
    long mEndTime;
    long bidTime=1574710260000L;

    String tester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_2);
        getSupportActionBar().setTitle("Live Auction");

        mTextViewCountDown = (TextView) findViewById(R.id.textView4);
        //t=(TextView)findViewById(R.id.textView4);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

//        if(mTimerRunning){
//            tester="true";
//        }
//        else{
//            tester="false";
//        }
//        //Toast.makeText(getApplicationContext(),"in onCreate: "+ mTimeLeftInMillis,Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),"in onCreate: "+ mTimeLeftInMillis + " mEndTime " +mEndTime+
//                " tester " +tester,Toast.LENGTH_LONG).show();

        updateCountDownText();


        if(getIntent().getExtras()!=null) {
            Bundle b = getIntent().getExtras();
            fmail = b.getString("bdmail");

        }
        //db=new Database(this);
        bid=new ArrayList<>();
        //bb=new ArrayList<Bidder>();
        databaseBidder= FirebaseDatabase.getInstance().getReference();
        Bundle b=getIntent().getExtras();
        name1=b.getString("key");
        productId=b.getString("prId");



        bidd=(TextView)findViewById(R.id.CBid);
        bidd.setText(name1);
        bidname=(TextView) findViewById(R.id.bidname);
        bidname.setText(opbid);

        //CW=(TextView)findViewById(R.id.CWin);
        editBid=(EditText)findViewById(R.id.Bid);
        editBid1=(EditText)findViewById(R.id.bid);
        buBid=(Button)findViewById(R.id.button);
        //t=(TextView)findViewById(R.id.textView4);
        //buShow=(Button)findViewById(R.id.button2);

        id2=databaseBidder.push().getKey();
        Bidder b1=new Bidder(id2,name1,opbid);
        databaseBidder.child("bidder").child(productId).child(id2).setValue(b1);

        buBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBidder();
            }
        });
        //Add();
        //viewAll();
        //viewall();
        //startTimer();
    }

    private void startTimer() {

//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);

//        if(mTimerRunning){
//            tester="true";
//        }
//        else{
//            tester="false";
//        }
//        //Toast.makeText(getApplicationContext(),"in startTimer"+ tester,Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),"in startTimer: "+ mTimeLeftInMillis + " mEndTime " +mEndTime+
//                " tester " +tester,Toast.LENGTH_LONG).show();


        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                if(fmail==bidname.getText().toString()){
                    Intent intent=new Intent(transporter_2.this,winner_page.class);
                    intent.putExtra("prId_2",productId);
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(transporter_2.this,Loser_page.class);
                    startActivity(intent);
                }

            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }


    /*private long convertToMilliseconds(String time) {
        String[] array = time.split(":");
        return (Integer.parseInt(array[0]) * 60 * 1000) + (Integer.parseInt(array[1]) * 1000);
    }

    public void startTimer() {
        mEndTime=System.currentTimeMillis()+mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                //Intent intent=new Intent(MainActivity.this,Activty_2.class);
                //startActivity(intent);
                //mButtonStartPause.setText("Start");
                //mButtonStartPause.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        //Intent intent=new Intent(MainActivity.this,Activty_2.class);
        //startActivity(intent);
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        t.setText(timeLeftFormatted);
    }*/

    @Override
    protected void onStop() {
        super.onStop();

//        if(mTimerRunning){
//            tester="true";
//        }
//        else{
//            tester="false";
//        }
//        //Toast.makeText(getApplicationContext(),"In onStop: " + mTimeLeftInMillis,Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),"in onStop: "+ mTimeLeftInMillis + " mEndTime " +mEndTime+
//                " tester " +tester,Toast.LENGTH_LONG).show();

        //mTimerRunning=true;
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();

        }

    }



    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS-(System.currentTimeMillis()-bidTime));
        mTimerRunning = prefs.getBoolean("timerRunning", false);

//        if(mTimerRunning){
//            tester="true";
//        }
//        else{
//            tester="false";
//        }
//        Toast.makeText(getApplicationContext(),"in onStart: "+ mTimeLeftInMillis + " mEndTime " +mEndTime+
//                " tester " +tester,Toast.LENGTH_LONG).show();

        updateCountDownText();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
            } else {
                startTimer();
            }
        }
        else{
            startTimer();
        }



        databaseBidder.child("bidder").child(productId).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
//                bid.clear();
//                //bb.clear();
//                for(DataSnapshot bidSnapshot:dataSnapshot.getChildren())
//                {
//                    Bidder bidder=bidSnapshot.getValue(Bidder.class);
//                    bid.add(Integer.parseInt(bidder.getBid()));
//                    sort(bid);
//                    bidd.setText(bid.get(0).toString());
//
//
//                }

                //Query q1=databaseBidder.child("bidder").child(productId).orderByChild("bid");

                int minBid=Integer.MAX_VALUE;
                Bidder minBidObject = null;
                for(DataSnapshot bidSnapshot:dataSnapshot.getChildren())
                {
                    Bidder bidder=bidSnapshot.getValue(Bidder.class);
                    if(minBid>Integer.parseInt(bidder.getBid())){
                        minBid=Integer.parseInt(bidder.getBid());
                        minBidObject=bidder;
                    }
                }
                bidd.setText(minBidObject.getBid().toString());
                bidname.setText(minBidObject.getUserId().toString());


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
    }


    public void addBidder()
    {
        String B=editBid.getText().toString().trim();
        String b=editBid1.getText().toString().trim();

        int y=1125;
        int x=(y*Integer.parseInt(b))+Integer.parseInt(B);



        if(x<Integer.parseInt(bidd.getText().toString())) {
            String z = String.valueOf(x);
            if (!TextUtils.isEmpty(B) && !TextUtils.isEmpty(b)) {
                //String id=databaseBidder.push().getKey();


                Bidder bidder = new Bidder(id2, z,fmail);
                databaseBidder.child("bidder").child(productId).child(id2).setValue(bidder);
                Toast.makeText(this, "Bid added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter a valid bid!",Toast.LENGTH_SHORT).show();
        }
    }
}
