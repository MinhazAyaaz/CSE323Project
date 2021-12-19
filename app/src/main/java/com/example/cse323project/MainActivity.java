package com.example.cse323project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    //Variable declarations
    ListView lv;
    ArrayList<String> listItem;
    Button addButton;
    Button proceedButton;
    Button deleteButton;
    Spinner options;
    ArrayAdapter<String> adapter;
    int processCount = 0;
    int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Variables
        addButton = (Button)findViewById(R.id.button3);
        proceedButton = (Button)findViewById(R.id.button4);
        deleteButton = (Button)findViewById(R.id.button);
        lv = (ListView) findViewById(R.id.processListView);
        options = (Spinner) findViewById(R.id.spinner);
        listItem = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItem);
        adapter.add("ID             BT             AT             PR            TQ");
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listItem.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No item selected!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    listItem.remove(selectedIndex);
                    adapter.notifyDataSetChanged();
                }
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

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listItem.clear();
                adapter.add("ID             BT             AT             PR            TQ");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void openDialog()
    {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");

    }

    @Override
    public void applyTexts(String burst, String arrival, String priority, String quantum){
        processCount++;
        listItem.add(processCount + " " + burst + " " + arrival + " " + priority + " " + quantum);
        adapter.notifyDataSetChanged();
    }
    }
