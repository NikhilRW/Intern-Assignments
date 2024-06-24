package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SecondAcitivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);
        TextView userData =findViewById(R.id.textView2);
        Intent intent =getIntent();
        List<User> userDataList = (List<User>) intent.getSerializableExtra("userData");
        Log.d("mytag", "onCreate: "+userDataList);
        if (intent != null) {
            List<User> users = (List<User>) intent.getSerializableExtra("userData");
            if (users != null) {
                StringBuilder userInfo = new StringBuilder();
                for (User user : users) {
                    userInfo.append("Name: ").append(user.getName()).append(" ");
                    userInfo.append("Age: ").append(user.getAge()).append(" ");
                    userInfo.append("Email: ").append(user.getEmail()).append("\n \n");
                }
                userData.setText(userInfo.toString());
            }
        }
    }


}
