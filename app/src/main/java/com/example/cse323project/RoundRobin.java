package com.example.cse323project;

// Java program to implement Round Robin
// Scheduling with different arrival time
class RoundRobin {


    float averageTurnAroundTime;
    float averageWaitingTime;
    int n;

    float getAverageTurnAroundTime() {
        return averageTurnAroundTime;
    }

    float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    void setN(int N) {
        n = N;
    }


    public void RoundRobinAlgorithm(int[][] values)
    {

        float res = 0;
        float resc = 0;

        // copy the burst array and arrival array
        // for not effecting the actual array
        int res_b[] = new int[n];
        int res_a[] = new int[n];

        for (int i = 0; i < res_b.length; i++) {
            res_b[i] = values[i][1];
            res_a[i] = values[i][2];
        }

        // critical time of system
        int t = 0;

        // for store the waiting time
        int w[] = new int[n];

        // for store the Completion time
        int comp[] = new int[n];

        while (true) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {

                // these condition for if
                // arrival is not on zero

                // check that if there come before qtime
                if (res_a[i] <= t) {
                    if (res_a[i] <= values[i][4]) {
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > values[i][4]) {

                                // make decrease the b time
                                t = t + values[i][4];
                                res_b[i] = res_b[i] - values[i][4] ;
                                res_a[i] = res_a[i] + values[i][4];

                            }
                            else {

                                // for last time
                                t = t + res_b[i];

                                // store comp time
                                comp[i] = t - values[i][2];

                                // store wait time
                                w[i] = t - values[i][1] - values[i][2];
                                res_b[i] = 0;

                            }
                        }
                    }
                    else if (res_a[i] > values[i][4]) {

                        // is any have less arrival time
                        // the coming process then execute them
                        for (int j = 0; j < n; j++) {

                            // compare
                            if (res_a[j] < res_a[i]) {
                                if (res_b[j] > 0) {
                                    flag = false;
                                    if (res_b[j] > values[i][4]) {
                                        t = t + values[i][4];
                                        res_b[j] = res_b[j] - values[i][4];
                                        res_a[j] = res_a[j] + values[i][4];

                                    }
                                    else {
                                        t = t + res_b[j];
                                        comp[j] = t - values[j][2];
                                        w[j] = t - values[j][1] - values[j][2];
                                        res_b[j] = 0;

                                    }
                                }
                            }
                        }


                        if (res_b[i] > 0) {
                            flag = false;


                            if (res_b[i] > values[i][4]) {
                                t = t + values[i][4];
                                res_b[i] = res_b[i] - values[i][4];
                                res_a[i] = res_a[i] + values[i][4];

                            }
                            else {
                                t = t + res_b[i];
                                comp[i] = t - values[i][2];
                                w[i] = t - values[i][1] - values[i][2];
                                res_b[i] = 0;

                            }
                        }
                    }
                }

                // if no process is come on thse critical
                else if (res_a[i] > t) {
                    t++;
                    i--;
                }
            }
            // for exit the while loop
            if (flag) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            res = res + w[i];
            resc = resc + comp[i];
        }

        averageWaitingTime = res/n;
        averageTurnAroundTime = resc/n;
    }

}
