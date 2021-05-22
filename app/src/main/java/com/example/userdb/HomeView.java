package com.example.userdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.userdb.data.DatabaseHelper;
import com.example.userdb.model.Foods;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends AppCompatActivity implements CardAdapter.OnRowClickListener {

    CardAdapter cardAdapter;
    RecyclerView recyclerView;
    DatabaseHelper db;

    ArrayList<Card> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        Button newButton = findViewById(R.id.newButton);
        db = new DatabaseHelper(this);
        db.getWritableDatabase();

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Home", "Account", "My list"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                if(item == "Account"){
                }else if(item == "My list"){
                    Intent intent = new Intent(HomeView.this, MyList.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        newButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeView.this, newFoodItem.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerViewList);
        cardAdapter = new CardAdapter(cards, getApplicationContext(), this);
        recyclerView.setAdapter(cardAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<Foods> foodList = db.fetchAllFoods();
        for(Foods food:foodList){
            Card card = new Card(food.getFood_id(), Uri.parse(food.getFoodImage()), food.getTitle(), food.getDesc());
            cards.add(card);
        }

    }
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", cards.get(position).getTitle());
        bundle.putString("DESC", cards.get(position).getDesc());
    }
}