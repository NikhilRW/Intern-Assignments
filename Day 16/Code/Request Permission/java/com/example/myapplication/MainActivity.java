package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    TextView textView;
    ImageView imageView;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        // Check if the WRITE_EXTERNAL_STORAGE permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            imageView.setImageResource(R.drawable.check);
            String message = "Permission Already Granted";
            textView.setText(message);
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toastView.getBackground().setColorFilter(Color.parseColor("#FF00B900"), PorterDuff.Mode.SRC_IN);
            TextView toastText = toastView.findViewById(android.R.id.message);
            toastText.setTextColor(Color.WHITE); // Change this to the color you want
            toast.show();
        }
    }
    // Handle permission request response
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageView.setImageResource(R.drawable.check);
                String message = "Permission Granted";
                textView.setText(message);
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toastView.getBackground().setColorFilter(Color.parseColor("#FF00B900"), PorterDuff.Mode.SRC_IN);
                TextView toastText = toastView.findViewById(android.R.id.message);
                toastText.setTextColor(Color.WHITE); // Change this to the color you want
                toast.show();
            } else {
                imageView.setImageResource(R.drawable.cross);
                String message = "Permission Denied";
                textView.setText(message);
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toastView.getBackground().setColorFilter(Color.parseColor("#FFF34336"), PorterDuff.Mode.SRC_IN);
                TextView toastText = toastView.findViewById(android.R.id.message);
                toastText.setTextColor(Color.WHITE); // Change this to the color you want
                toast.show();
            }
        }
    }
}
