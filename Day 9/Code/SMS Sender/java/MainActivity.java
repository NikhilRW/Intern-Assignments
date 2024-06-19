package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
    SmsManager smsManager;
    EditText message,contact;
    static final  int  REQUEST_SMS_PERMISSION =69;
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
         message = findViewById(R.id.message);
        contact = findViewById(R.id.numbercontact);
        Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()){
                    Toast.makeText(MainActivity.this, "Message Sended", Toast.LENGTH_SHORT).show();
                    sendMessage(message.getText().toString(), contact.getText().toString());
                }
                else{
                    requestSmsPermission();
                    sendMessage(message.getText().toString(), contact.getText().toString());
                }
            }
        });

    }

    private  void sendMessage(String message,String contact){
            smsManager =SmsManager.getDefault();
            try{
           smsManager.sendTextMessage(
                   contact,null,message,null,null);
            }
            catch (Exception e){
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
    }
    public void requestSmsPermission(){ActivityCompat.requestPermissions(MainActivity.this,
            new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
    }
    public boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            return false;

        } else {
            // Permission is already granted, send the message
//            sendMessage(message.getText().toString(), contact.getText().toString());
            return true;
        }
    }
}
//package com.example.smssender;
//
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.Manifest;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//public class MainActivity extends AppCompatActivity {
//
//    private static final int REQUEST_CODE = 1;
//
//    private EditText PhoneEditText , messageEditText;
//
//    private Button sendSmsButton;
//    private View phoneEditText;
//    private PackageManager packageManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        PhoneEditText = findViewById(R.id.PhoneEditText);
//        messageEditText = findViewById(R.id.messageEditText);
//        sendSmsButton = findViewById(R.id.sendSmsButton);
//
//        sendSmsButton.setOnClickListener(v -> {
//
//            String msgText = messageEditText.getText().toString();
//            String phoneText = PhoneEditText.getText().toString();
//
//            if (checkSmsPermission()){
//                sendSms(phoneText , msgText);
//            }
//            else {
//                requestSmsPermissions();
//            }
//        });
//
//    }
//
//    private boolean checkSmsPermission()
//    {
//        int result =  ContextCompat.checkSelfPermission(MainActivity.this , android.Manifest.permission.SEND_SMS);
//        return result == packageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestSmsPermissions()
//    {
//        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},REQUEST_CODE);
//    }
//
//    private void sendSms(String phone , String message){
//        try{
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phone, null , message ,null, null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}