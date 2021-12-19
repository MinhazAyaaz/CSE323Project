package com.example.cse323project;

public class NPSJF {

    int pid[];
    int at[];
    int bt[];
    int ct[];
    int ta[];
    int wt[];
    int f[];  // f means it is flag it checks process is completed or not
    int numberOfProcess;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int n;
    int st=0, tot=0;
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
        at = new int[n];
        f = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = values[i][0];
            bt[i] = values[i][1];
            at[i] = values[i][2];
            f[i] = 0;
        }
    }

    public void NonPreemptiveSJF(){

        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }
        for(int i=0;i<n;i++)
        {
            averageWaitingTime+= wt[i];
            averageTurnAroundTime+= ta[i];
        }

        averageWaitingTime /= n;


    }
}
