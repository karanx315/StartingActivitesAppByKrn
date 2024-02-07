package com.example.startingactivitesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("Main Activity","On create");
        Button btn1=(Button)findViewById(R.id.btn1);

        EditText emailEditText=(EditText) findViewById(R.id.editTextTextEmailAddress);
        btn1.setOnClickListener(view -> {
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "EmailAddress", emailEditText.getText().toString() );

            startActivity( nextPage);
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.w("Main Activity","on Start");

    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.w("Main Activity","on Resume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.w("Main Activity","on Pause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.w("Main Activity","on Stop");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.w("Main Activity","on Destroy");
    }





}