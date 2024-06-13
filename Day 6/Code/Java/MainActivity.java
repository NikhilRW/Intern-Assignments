package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        EditText editText =findViewById(R.id.editTextText);
        EditText editText3 =findViewById(R.id.editTextTextPassword);
        TextView textView =findViewById(R.id.textView4);
        CheckBox checkBox = findViewById(R.id.checkBox);
        RadioGroup radioGroup =findViewById(R.id.radioGroup);
        Button button = findViewById(R.id.button4);
        checkBox.setOnClickListener(v->{
            if(!checkBox.isChecked()){
                button.setEnabled(false);
            }
            else{
                button.setEnabled(true);
            }
        });

        button.setOnClickListener(v->{
            String password = editText3.getText().toString();
            String username =editText.getText().toString();
            RadioButton radioButton= findViewById(radioGroup.getCheckedRadioButtonId());
            String gender = radioButton.getText().toString();
            if(password.isEmpty()||username.isEmpty()){
                Toast.makeText(this, "Please Fill Username And Password", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!checkBox.isChecked()){
                Toast.makeText(this, "Please Agree Terms And Condition", Toast.LENGTH_SHORT).show();
                return;
            }
            String message ="User Name : " +editText.getText().toString()+" \n Password : "+editText3.getText().toString();
            textView.setText(message);
            intent.putExtra("name",username);
            intent.putExtra("password",password);
            intent.putExtra("gender",gender);
            startActivity(intent);
        });
    }
}