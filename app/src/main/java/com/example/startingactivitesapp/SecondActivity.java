package com.example.startingactivitesapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityResultLauncher<Intent> cameraResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        // Register activity result launcher
        cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Log.w("Mervat", "Result2....>");
                        }
                    }
                });

        Intent fromPrevious = getIntent();
        String emailAdress= fromPrevious.getStringExtra("EmailAddress");
        TextView txtWelcome=(TextView) findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome Back: "+emailAdress );

        Button btnPhone=(Button)findViewById(R.id.btnPhone);
        Button btnSms=(Button)findViewById(R.id.btnSms);
        Button btnPic=(Button)findViewById(R.id.btnPic);
        Button btnMap=(Button)findViewById(R.id.btnMap);
        EditText editText=(EditText) findViewById(R.id.edtPhone);

        btnPhone.setOnClickListener(view -> {
            // Replace phoneNumber with the recipient's phone number
            String phoneNumber = editText.getText().toString();
            // Create the intent
            Intent call = new Intent(Intent.ACTION_DIAL);
            // Set the data URI with the recipient's phone number
            call.setData(Uri.parse("tel:" + phoneNumber));
            // Start the activity
            startActivity( call);

        });
        btnSms.setOnClickListener(view -> {
            // Replace phoneNumber with the recipient's phone number
            String phoneNumber = editText.getText().toString();
            // Create the intent
            Intent sms = new Intent(Intent.ACTION_SENDTO);
            // Set the data URI with the recipient's phone number
            sms.setData(Uri.parse("smsto:" + phoneNumber));
            // Start the activity
            startActivity(sms);
        });

        btnMap.setOnClickListener(view -> {
            // Replace latitude and longitude with the coordinates of the location you want to open
            double latitude = 37.7749;
            double longitude = -122.4194;

            // Create the intent
            Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Optionally, specify a zoom level
            mapIntent.putExtra("zoom", 15);

            // Start the activity
            startActivity(mapIntent);


        });

        btnPic.setOnClickListener(view -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);

        });
            /////



    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("Mervat","Done pic1");
        Bitmap thumbnail=data.getParcelableExtra("data");
        ImageView img=(ImageView) findViewById(R.id.img1);
        img.setImageBitmap(thumbnail);

        /*
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.w("Mervat","Done pic2");
         Bitmap thumbnail=data.getParcelableExtra("data");
            ImageView img=(ImageView) findViewById(R.id.img1);
            img.setImageBitmap(thumbnail);
        }*/
    }
}