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
    TextView textView1,textView2,textView3,textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);
        Button button = findViewById(R.id.exit);
        textView1=findViewById(R.id.usernameresult);
        textView2=findViewById(R.id.nameresult);
        textView3=findViewById(R.id.emailresult);
        textView4=findViewById(R.id.genderresult);
        Intent intent = getIntent();
        textView1.setText("Name : "+intent.getStringExtra("name"));
        textView2.setText("Username : "+intent.getStringExtra("username"));
        textView3.setText("Email : "+intent.getStringExtra("email"));
        textView4.setText("Gender : "+intent.getStringExtra("gender"));
        button.setOnClickListener(v->{
            finishAffinity();
        });

    }
}