package com.example.e_shipmentauctionsystem1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class productdescActivity extends AppCompatActivity  {
    private static final int PICK_IMAGE_REQUEST = 456;

    ImageView imageView3;
    EditText etWeight,etLength,etWidth,etHeight,etDesc,etPick;
    Button buContinue,btChose;
    DatabaseReference dbProduct,myCart;
    String sn;
    int result;
    List<String> arr;
    EditText editText;
    Geocoder geocoder;
    List<Address> addresses;
    Double latitude=28.6297;
    Double longitude=77.3721;
   String name4;
    private Uri filePath;
    StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdesc);
        editText=(EditText)findViewById(R.id.edittext);
        etDesc=(EditText) findViewById(R.id.etDesc);
        etPick=(EditText)findViewById(R.id.etPick);
        if((getIntent().getExtras())!=null) {
            Bundle b = getIntent().getExtras();

            name4 = b.getString("mail3");

        }


        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses=geocoder.getFromLocation(latitude,longitude,1);
            String address=addresses.get(0).getAddressLine(0);
            String area=addresses.get(0).getLocality();
            String city=addresses.get(0).getAdminArea();
            String country=addresses.get(0).getCountryName();
            String postalcode=addresses.get(0).getPostalCode();
            String fulladdress= address+','+area+','+city+','+country+','+postalcode;
            etPick.setText(postalcode);

            editText.setText(fulladdress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mStorageRef = FirebaseStorage.getInstance().getReference();
        dbProduct= FirebaseDatabase.getInstance().getReference("products");
        myCart=FirebaseDatabase.getInstance().getReference(name4);

        getSupportActionBar().setTitle("Product description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etWeight=(EditText) findViewById(R.id.etWeight);
        etLength=(EditText) findViewById(R.id.etLength);
        etWidth=(EditText) findViewById(R.id.etWidth);
        etHeight=(EditText) findViewById(R.id.etHeight);
        buContinue=(Button) findViewById(R.id.buContinue);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        btChose=(Button) findViewById(R.id.btChose);

        Spinner spineer=findViewById(R.id.Spinner1);
        Spinner spineer2=findViewById(R.id.Spinner2);
        arr =new ArrayList<String>();
        arr.add("New Delhi");
        arr.add("Mumbai");
        arr.add("Gujarat");
        arr.add("Jammu & Kashmir");
        arr.add("Other State");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arr);
        spineer.setAdapter(arrayAdapter);
        spineer2.setAdapter(arrayAdapter);
         sn= spineer2.getSelectedItem().toString();;

         spineer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 sn=arr.get(i);

             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs102 = getSharedPreferences("prefs102", MODE_PRIVATE);
        name4 = prefs102.getString("mail_102",name4);
        //Toast.makeText(getApplicationContext(),"in productdesc "+ name4,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs102 = getSharedPreferences("prefs102", MODE_PRIVATE);
        SharedPreferences.Editor editor102 = prefs102.edit();

        editor102.putString("mail_102",name4);
        editor102.apply();
        //Toast.makeText(getApplicationContext(),"in descr stop "+ name4,Toast.LENGTH_SHORT).show();
    }

    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }





    public void buCont(View view) {

        int n1;
        n1 = Integer.parseInt(etWeight.getText().toString());
        if(sn.equals("New Delhi")){
            result=n1*30;
        }
        else if(sn.equals("Mumbai")){
            result=n1*50;
        }
        else if(sn.equals("Gujarat")){
            result=n1*50;
        }
        else if(sn.equals("Jammu & Kashmir")){
            result=n1*160;
        }
        else{
            result=n1*120;
        }

        addDetails();

      //  String result1 = Double.toString(result);
//        Intent intent = new Intent(productdescActivity.this, pop.class);
//        intent.putExtra("predictedprice", result1);
//        startActivity(intent);

    }
    private void addDetails(){
        if(filePath!=null) {
            final String weight = etWeight.getText().toString().trim();
            final String length = etLength.getText().toString().trim();
            final String width = etWidth.getText().toString().trim();
            final String height = etHeight.getText().toString().trim();
            final String Desc = etDesc.getText().toString().trim();
            final String pickup = editText.getText().toString();
            final String result2=Integer.toString(result);

            final ProgressDialog progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Uploading....");
            progressDialog.show();

            final StorageReference riversRef = mStorageRef.child("images/"+ System.currentTimeMillis() + "." + getFileExtension(filePath));


            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();
                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    Intent intent = new Intent(productdescActivity.this, pop.class);
                                    intent.putExtra("predictedprice", result2);
                                    intent.putExtra("matrix", name4);
                                    startActivity(intent);

                                    String uploadId = dbProduct.push().getKey();
                                    Product upload = new Product(uploadId, weight, length, width, height, Desc, pickup,result2,
                                            downloadUrl.toString(),name4);
                                    myCart.child(uploadId).setValue(upload);
                                    dbProduct.child(uploadId).setValue(upload);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% Uploaded....");

                        }
                    })
            ;



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!= null && data.getData() != null ){
            filePath=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView3.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void btSelect(View view) {
        showFileChooser();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
