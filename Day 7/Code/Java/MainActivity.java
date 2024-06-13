package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1;
    CheckBox checkBox2;
    Button selectButton;
    CheckBox checkBox3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);


        selectButton =findViewById(R.id.button);

        selectButton.setOnClickListener(v->{
            if(checkBox1.isChecked()&&checkBox2.isChecked()||checkBox2.isChecked()&&checkBox3.isChecked()||checkBox1.isChecked()&&checkBox3.isChecked()){
                Toast.makeText(this, "Only One Internship Is Allowed For One Student", Toast.LENGTH_SHORT).show();
            }
            else{
                String internSubject;
                if(checkBox1.isChecked()){
                    internSubject=checkBox1.getText().toString();
                }
                else if(checkBox2.isChecked()){
                    internSubject=checkBox2.getText().toString();
                }
                else{
                    internSubject=checkBox3.getText().toString();
                }
                Toast.makeText(this, "Applied For Internship :- "+internSubject, Toast.LENGTH_SHORT).show();
            }
        });
    }
}