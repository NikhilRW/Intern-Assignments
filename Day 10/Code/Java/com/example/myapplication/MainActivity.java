package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name,email,age;
    Button insert,read,delete,update;
    UserDatabaseManager userDatabaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainview), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name =findViewById(R.id.name);
        email =findViewById(R.id.age);
        age =findViewById(R.id.age);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        read = findViewById(R.id.read);
        delete = findViewById(R.id.delete);
        userDatabaseManager = new UserDatabaseManager(this);
        userDatabaseManager.open();
        insert.setOnClickListener(v->{
            userDatabaseManager.insertUser(name.getText().toString(),Integer.parseInt(age.getText().toString()),email.getText().toString());
        });
        read.setOnClickListener(v->{
            Intent intent =new Intent(MainActivity.this,SecondAcitivity.class);
            List<User> users = userDatabaseManager.getAllUsers();

            intent.putExtra("userData", (Serializable) users);
            startActivity(intent);
        });
        delete.setOnClickListener(v->{
            userDatabaseManager.deleteUser(name.getText().toString());
        });
        update.setOnClickListener(v->{
            userDatabaseManager.updateUser(name.getText().toString(),Integer.parseInt(age.getText().toString()),email.getText().toString());
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDatabaseManager.close();
    }
}