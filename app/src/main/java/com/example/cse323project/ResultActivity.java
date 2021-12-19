             package com.example.cse323project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    //Variable declarations
    TextView text1;
    ArrayList<String> processList;
    ImageButton backButton;
    int[][] values = new int[100][5];
    double avgwaittime;
    double avgturnaroundtime;
    double awt;
    double atat;

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
                try {
                    values[i-1][j] = Integer.valueOf(string[j]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        RoundRobin obj = new RoundRobin();
        obj.setN(processList.size()-1);
        obj.RoundRobinAlgorithm(values);

        text1 = (TextView)findViewById(R.id.textView4);
        text1.setText(String.format("%f",obj.getAverageWaitingTime()));
        backButton = (ImageButton)findViewById(R.id.imageButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    

//    public void PSJ(){
//
//        int n = processList.size() - 1;
//        int pid[] = new int[n]; // it takes pid of process
//        int at[] = new int[n]; // at means arrival time
//        int bt[] = new int[n]; // bt means burst time
//        int ct[] = new int[n]; // ct means complete time
//        int ta[] = new int[n];// ta means turn around time
//        int wt[] = new int[n];  // wt means waiting time
//        int f[] = new int[n];  // f means it is flag it checks process is completed or not
//        int k[]= new int[n];   // it is also stores brust time
//        int i, st=0, tot=0;
//        float avgwt=0, avgta=0;
//
//        for (i=0;i<n;i++)
//        {
//            pid[i] = values[i][0];
//            bt[i] = values[i][1];
//            at[i] = values[i][2];
//
//            k[i]= bt[i];
//            f[i]= 0;
//        }
//
//        while(true){
//            int min=99,c=n;
//            if (tot==n)
//                break;
//
//            for( i=0;i<n;i++)
//            {
//                if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
//                {
//                    min=bt[i];
//                    c=i;
//                }
//            }
//
//            if (c==n)
//                st++;
//            else
//            {
//                bt[c]--;
//                st++;
//                if (bt[c]==0)
//                {
//                    ct[c]= st;
//                    f[c]=1;
//                    tot++;
//                }
//            }
//        }
//
//        for(i=0;i<n;i++)
//        {
//            ta[i] = ct[i] - at[i];
//            wt[i] = ta[i] - k[i];
//            avgwt+= wt[i];
//            avgta+= ta[i];
//        }
//
//
//        avgta=(float)(avgta/n);
//        avgwt=(float)(avgwt/n);
//
//
//    }

//    public void npSJFAlgorithm(){
//
//        int n = processList.size();
//        int pid[] = new int[n];
//        int at[] = new int[n]; // at means arrival time
//        int bt[] = new int[n]; // bt means burst time
//        int ct[] = new int[n]; // ct means complete time
//        int ta[] = new int[n]; // ta means turn around time
//        int wt[] = new int[n];  //wt means waiting time
//        int f[] = new int[n];  // f means it is flag it checks process is completed or not
//        int st=0, tot=0;
//        float avgwt=0, avgta=0;
//
//        for(int i=0;i<n;i++)
//        {
//            pid[i] =values[i][0];
//            bt[i] = values[i][1];
//            at[i] = values[i][2];
//            f[i] = 0;
//        }
//
//        boolean a = true;
//        while(true)
//        {
//            int c=n, min=999;
//            if (tot == n) // total no of process = completed process loop will be terminated
//                break;
//            for (int i=0; i<n; i++)
//            {
//                /*
//                 * If i'th process arrival time <= system time and its flag=0 and burst<min
//                 * That process will be executed first
//                 */
//                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
//                {
//                    min=bt[i];
//                    c=i;
//                }
//            }
//            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
//            if (c==n)
//                st++;
//            else
//            {
//                ct[c]=st+bt[c];
//                st+=bt[c];
//                ta[c]=ct[c]-at[c];
//                wt[c]=ta[c]-bt[c];
//                f[c]=1;
//                tot++;
//            }
//        }
//        for(int i=0;i<n;i++)
//        {
//            avgwaittime+= wt[i];
//            avgta+= ta[i];
//        }
//
//        avgwaittime /= n;
//    }
//

//    public void fcfsAlgorithm(){
//
//        int n = processList.size();
//
//        int pid[] = new int[n];   // process ids
//        int ar[] = new int[n];    // arrival times
//        int bt[] = new int[n];    // burst or execution times
//        int ct[] = new int[n];    // completion times
//        int ta[] = new int[n];    // turn around times
//        int wt[] = new int[n];    // waiting times
//
//        int temp;
//
//        for(int i = 0; i < n; i++)
//        {
//            pid[i] = values[i][0];
//            bt[i] = values[i][1];
//            ar[i] = values[i][2];
//        }
//
////sorting according to arrival times
//        for(int i = 0 ; i <n; i++)
//        {
//            for(int  j=0;  j < n-(i+1) ; j++)
//            {
//                if( ar[j] > ar[j+1] )
//                {
//                    temp = ar[j];
//                    ar[j] = ar[j+1];
//                    ar[j+1] = temp;
//                    temp = bt[j];
//                    bt[j] = bt[j+1];
//                    bt[j+1] = temp;
//                    temp = pid[j];
//                    pid[j] = pid[j+1];
//                    pid[j+1] = temp;
//                }
//            }
//        }
//// finding completion times
//        for(int  i = 0 ; i < n; i++)
//        {
//            if( i == 0)
//            {
//                ct[i] = ar[i] + bt[i];
//            }
//            else
//            {
//                if( ar[i] > ct[i-1])
//                {
//                    ct[i] = ar[i] + bt[i];
//                }
//                else
//                    ct[i] = ct[i-1] + bt[i];
//            }
//            ta[i] = ct[i] - ar[i] ;          // turnaround time= completion time- arrival time
//            wt[i] = ta[i] - bt[i] ;          // waiting time= turnaround time- burst time
//            avgwaittime += wt[i] ;               // total waiting time
//            avgturnaroundtime += ta[i] ;               // total turnaround time
//        }
//
//    }
    }