package com.example.cse323project;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.cse323project.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    Button addButton;
    Button proceedButton;

    private String[][] content = {
            {"Process 1", "5", "5", "5", "5"},
            {"Process 2", "3", "3", "3", "3"},
            {"Process 3", "4", "4", "4", "4"},
            {"Process 4", "2", "2", "2", "2"},
            {"Process 5", "7", "7", "7", "7"},
            {"Process 5", "7", "7", "7", "7"},
            {"Process 5", "7", "7", "7", "7"},
            {"Process 5", "7", "7", "7", "7"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.processListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("ID                        H1         H2         H3        H4");
        for(int i = 0; i < content.length; i++) {
            adapter.add(content[i][0]+"           "
                       +content[i][1]+"           "
                       +content[i][2]+"           "
                       +content[i][3]+"           "
                       +content[i][4]+"           "

            );
        }
        lv.setAdapter(adapter);

        addButton = (Button)findViewById(R.id.button3);
        proceedButton = (Button)findViewById(R.id.button4);

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
                Bundle b = new Bundle();
                b.putSerializable("list",content);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

    public void openDialog()
    {
        Dialogbox dialogBox = new Dialogbox();
        dialogBox.show(getSupportFragmentManager(), "Dialog Box");

    }

    }
