package com.example.cse323project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    //Variable declarations
    TextView text1;
    ArrayList<String> processList;
    int[][] values = new int[100][3];
    double avgwt;
    double avgta;
    String global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Retrieving ArrayList sent by MainActivity
        processList = new ArrayList<String>();
        processList = (ArrayList<String>) getIntent().getSerializableExtra("list");

        for(int i=1;i< processList.size();i++) {
            String[] string = processList.get(i).split(" ");

            for (int j = 0; j < string.length; j++) {
                values[i][j] = Integer.valueOf(string[j]);
            }
        }

        fcfsAlgorithm();
        text1 = (TextView)findViewById(R.id.textView4);
        text1.setText(String.format("%f",avgwt/4));
    }

    public void fcfsAlgorithm(){

        int n = processList.size();

        int pid[] = new int[n];   // process ids
        int ar[] = new int[n];    // arrival times
        int bt[] = new int[n];    // burst or execution times
        int ct[] = new int[n];    // completion times
        int ta[] = new int[n];    // turn around times
        int wt[] = new int[n];    // waiting times

        int temp;

        for(int i = 0; i < n; i++)
        {
            pid[i] = values[i][0];
            bt[i] = values[i][1];
            ar[i] = values[i][2];
        }

//sorting according to arrival times
        for(int i = 0 ; i <n; i++)
        {
            for(int  j=0;  j < n-(i+1) ; j++)
            {
                if( ar[j] > ar[j+1] )
                {
                    temp = ar[j];
                    ar[j] = ar[j+1];
                    ar[j+1] = temp;
                    temp = bt[j];
                    bt[j] = bt[j+1];
                    bt[j+1] = temp;
                    temp = pid[j];
                    pid[j] = pid[j+1];
                    pid[j+1] = temp;
                }
            }
        }
// finding completion times
        for(int  i = 0 ; i < n; i++)
        {
            if( i == 0)
            {
                ct[i] = ar[i] + bt[i];
            }
            else
            {
                if( ar[i] > ct[i-1])
                {
                    ct[i] = ar[i] + bt[i];
                }
                else
                    ct[i] = ct[i-1] + bt[i];
            }
            ta[i] = ct[i] - ar[i] ;          // turnaround time= completion time- arrival time
            wt[i] = ta[i] - bt[i] ;          // waiting time= turnaround time- burst time
            avgwt += wt[i] ;               // total waiting time
            avgta += ta[i] ;               // total turnaround time
        }

    }


    }