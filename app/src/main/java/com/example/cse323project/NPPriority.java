package com.example.cse323project;

public class NPPriority {

    int[] burstTime;
    int[] priority;
    int[] arrivalTime;
    int[] processId;
    int numberOfProcess;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int m;
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
        m = N;
    }

    void getProcessData() {
        numberOfProcess = m;
        burstTime = new int[numberOfProcess];
        priority = new int[numberOfProcess];
        arrivalTime = new int[numberOfProcess];
        processId = new int[numberOfProcess];

        for (int i = 0; i < numberOfProcess; i++) {
            processId[i] = values[i][0];
            burstTime[i] = values[i][1];
            arrivalTime[i] = values[i][2];
            priority[i] = values[i][3];
        }

    }

    void sortAccordingArrivalTimeAndPriority(int[] at, int[] bt, int[] prt, int[] pid) {

        int temp;
        int stemp;
        for (int i = 0; i < numberOfProcess; i++) {

            for (int j = 0; j < numberOfProcess - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                }
                //sorting according to priority when arrival timings are same
                if (at[j] == at[j + 1]) {
                    if (prt[j] > prt[j + 1]) {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        //swapping process identity
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                    }
                }
            }

        }
    }

    void priorityNonPreemptiveAlgorithm() {
        int[] finishTime = new int[numberOfProcess];
        int[] bt = burstTime.clone();
        int[] at = arrivalTime.clone();
        int[] prt = priority.clone();
        int[] pid = processId.clone();
        int[] waitingTime = new int[numberOfProcess];
        int[] turnAroundTime = new int[numberOfProcess];

        sortAccordingArrivalTimeAndPriority(at, bt, prt, pid);

        //calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < numberOfProcess; i++) {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }
        float sum = 0;
        for (int n : waitingTime) {
            sum += n;
        }
        averageWaitingTime = sum / (numberOfProcess - 1);

        sum = 0;
        for (int n : turnAroundTime) {
            sum += n;
        }
        averageTurnAroundTime = sum / (numberOfProcess - 1);


    }


}
