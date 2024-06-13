package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);
        textView1=findViewById(R.id.textView5);
        textView2=findViewById(R.id.textView6);
        textView3=findViewById(R.id.textView7);
        Intent intent = getIntent();
        textView1.setText("Name : "+intent.getStringExtra("name"));
        textView2.setText("Password : "+intent.getStringExtra("password"));
        textView3.setText("Gender : "+intent.getStringExtra("gender"));

    }
}