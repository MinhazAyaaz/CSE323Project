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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    //Variable declarations
    ListView lv;
    ArrayList<Process> listItem;
    Button addButton;
    Button deleteButton;
    Button refreshButton;
    Button proceedButton;
    Spinner options;
    ProcessListAdapter adapter;
    int processCount = 0;
    int selectedIndex;
    Process column;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Variables
        addButton = (Button)findViewById(R.id.button3);
        proceedButton = (Button)findViewById(R.id.button4);
        deleteButton = (Button)findViewById(R.id.button);
        refreshButton = (Button)findViewById(R.id.button2);
        lv = (ListView) findViewById(R.id.processListView);
        options = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Weight, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapter1);
        text = options.getSelectedItem().toString();

        listItem = new ArrayList<Process>();
        column = new Process("ID","BT","AT","PT","TQ");
        listItem.add(column);
        adapter = new ProcessListAdapter(this,R.layout.adapter_view_layout,listItem);
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
                try {
                    if(selectedIndex!=0) {
                        listItem.remove(selectedIndex);
                        adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No item selected!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listItem.size()==1){
                    Toast.makeText(getApplicationContext(), "Please add process!",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("list", listItem);
                    intent.putExtra("algo", text);
                    startActivity(intent);
                }
            }
        });

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listItem.clear();
                adapter.add(column);
                adapter.notifyDataSetChanged();
                text = parent.getItemAtPosition(position).toString();
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
        Process temp = new Process(Integer.toString(processCount),burst,arrival,priority,quantum);
        listItem.add(temp);
        adapter.notifyDataSetChanged();
    }
    }
