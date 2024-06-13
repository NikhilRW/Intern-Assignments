package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText username,email,password,confirmpassword,name;
    RadioGroup genderSelect;
    CheckBox agreeCheckBox;
    Button signup,login;
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
          username=findViewById(R.id.username);
          password=findViewById(R.id.password);
          name=findViewById(R.id.name);
        agreeCheckBox = findViewById(R.id.agreeCheckBox);
        confirmpassword = findViewById(R.id.confirmpassword);
        genderSelect =findViewById(R.id.radioGroup);
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.email);
        agreeCheckBox.setOnClickListener(v->{
            if(!agreeCheckBox.isChecked()){
                signup.setEnabled(false);
            }
            else{
                signup.setEnabled(true);
            }
        });

        signup.setOnClickListener(v->{
            String passwordstr = password.getText().toString();
            String usernamestr =username.getText().toString();
            String namestr =name.getText().toString();
            String emailstr =email.getText().toString();
            String confirmpasswordstr =confirmpassword.getText().toString();
            RadioButton radioButton= findViewById(genderSelect.getCheckedRadioButtonId());
            if(usernamestr.isEmpty()||namestr.isEmpty()||passwordstr.isEmpty()||confirmpasswordstr.isEmpty()||emailstr.isEmpty()|| radioButton == null){
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                return;
            }
            String gender = radioButton.getText().toString();
            if(!passwordstr.equals(confirmpasswordstr)){
                Toast.makeText(this, "Confirm Password Not Matched", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!agreeCheckBox.isChecked()){
                Toast.makeText(this, "Please Agree Terms And Condition", Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("name",namestr);
            intent.putExtra("username",usernamestr);
            intent.putExtra("email",emailstr);
            intent.putExtra("gender",gender);
            startActivity(intent);
        });
    }
}