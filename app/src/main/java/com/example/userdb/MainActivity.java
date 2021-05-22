package com.example.userdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.userdb.data.DatabaseHelper;
import com.example.userdb.model.Foods;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signUpButton);

        db = new DatabaseHelper(this);
        db.getWritableDatabase();
        db.insertFood(new Foods("android.resource://com.example.userdb/drawable/parma", "Parma", "Description", "computer"));
        db.insertFood(new Foods("android.resource://com.example.userdb/drawable/parma", "Parma1", "Description", "computer"));

        loginButton.setOnClickListener(view -> {
        boolean result = db.fetchUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        if (result == true)
        {
            Toast.makeText(MainActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeView.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this, "The user does not exist.", Toast.LENGTH_SHORT).show();
        }
        });

        signupButton.setOnClickListener(view -> {
            Intent signupIntent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(signupIntent);
        });
    }
}