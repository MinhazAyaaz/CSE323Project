package com.example.cse323project;

public class FCFS {

    int pid[];
    int ar[];
    int bt[];
    int ct[];
    int ta[];
    int wt[];
    int numberOfProcess;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int n;
    int[][] values;

    void setValues(int[][] valuesRecieved) {
        values = valuesRecieved;
    }

    float getAverageTurnAroundTime() {
        return averageTurnAroundTime;
    }

    float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    void setN(int N) {
        n = N;
    }

    int temp;

    public void getProcess() {
        pid = new int[n];
        bt = new int[n];
        ar = new int[n];
        ct = new int[n];
        ta = new int[n];
        wt = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = values[i][0];
            bt[i] = values[i][1];
            ar[i] = values[i][2];
        }
    }

    public void FirstComeFirstServeAlgorithm(){

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
        averageTurnAroundTime += wt[i] ;               // total waiting time
        averageWaitingTime += ta[i] ;               // total turnaround time
    }

}

}
