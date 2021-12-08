package com.example.cse323project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    //Variable declarations
    ListView lv;
    ArrayList<String> listItem;
    Button addButton;
    Button proceedButton;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Variables
        addButton = (Button)findViewById(R.id.button3);
        proceedButton = (Button)findViewById(R.id.button4);
        lv = (ListView) findViewById(R.id.processListView);
        listItem = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItem);
        adapter.add("ID                        H1         H2         H3        H4");
        lv.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("list",listItem);
                startActivity(intent);
            }
        });
    }

    public void openDialog()
    {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");

    }

    @Override
    public void applyTexts(String name, String burst, String arrival){
        listItem.add(name + " " + burst + " " + arrival);
        adapter.notifyDataSetChanged();
    }
    }
