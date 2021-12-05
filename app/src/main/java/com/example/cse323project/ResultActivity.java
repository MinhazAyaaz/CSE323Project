package com.example.cse323project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private String[][] content;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        content=null;
        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("list");
        if(objectArray!=null){
            content = new String[objectArray.length][];
            for(int i=0;i<objectArray.length;i++){
                content[i]=(String[]) objectArray[i];
            }
        }

        text1 = (TextView)findViewById(R.id.textView4);
        text1.setText(String.format("%s %s %s %s %s", content[0][0], content[0][1],
                content[0][2], content[0][3], content[0][4]));

    }

    public void fcfsAlgorithm(){



    }


    }