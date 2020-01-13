public class RoundRobin
{
    // Method that calculates the waiting time for all processes
    static int[] calcWaitingTime(int burstTime[], int quantum) {
        int n = burstTime.length;       ///te number of processes
        int[] waitingtime = new int[n];     //array to calculate waiting time of each process
        for (int i = 0; i < n; i++) {       //initialization of waiting time array with zeros
            waitingtime[i] = 0;
        }
        boolean done = true;        // a boolean to check if all processes are terminated
        int[] bt = new int[n];      //a copy of the burst time array so we can keep the original data untouched
        bt = burstTime.clone();
        do {        //while there is at least one unfinished process
            for (int i = 0; i < n; i++) {       //for every process to be run in a full circle of quantum giving
                for (int j = 0; j < n; j++) {       //for every waiting process
                    if (j != i) {       //if the waiting process is not the one running at the moment
                        if (bt[j] != 0) {       //if the remaining burst time of the running process is not zero
                            if (bt[i] >= quantum) {     //if remaining time of running process is equal or greater than the quantum
                                waitingtime[j] += quantum;      //add the quantum to the paused process
                            } else {
                                waitingtime[j] += bt[i];        //else add the remaining burst time
                            }
                        }
                    }
                }
                if (bt[i] >= quantum) {     //if the remaining burst time of the running process is equal or greater than the quantum
                    bt[i] -= quantum;       //subtract the quantum from the remaining burst time
                } else {
                    bt[i] = 0;      //otherwise make it zero
                }
            }
            done = true;        //initializing boolean done to true
            for (int i = 0; i < n; i++) {       //for every process
                if (bt[i] != 0) {       //if there is at least one process with remaining burst time greater than 0
                    done = false;       //make done false
                    break;      //break of the loop, no need to check the rest of the processes
                }
            }
        }while (!done);     //repeat until done is false, which is when there are unfinished processes
        return waitingtime;     //return the array with waiting times
    }

    // Method that calculates turn around time for all processes
    static int[] calcTurnAroundTime(int burstTime[], int waitingTime[]){
        /*
         * Put your code here!
         */
        int n = burstTime.length;       //number of processes
        int[] tat = new int[n];     //creating array with turnaround times
        for(int i = 0; i<n; i++){       //for every process
            tat[i] = burstTime[i]+ waitingTime[i];      //turnaround time equals the sum of its burst and waiting times
        }
        return tat;     //return array with turnaround times

    }

    // Method that prints the results and calculates the average waiting and
    // turnaround times
    static void printAvgTimes(int burstTime[], int quantum)
    {
        int n = burstTime.length;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        // Find waiting time of all processes
        int[] waitingTime = calcWaitingTime(burstTime, quantum);

        // Find turn around time for all processes
        int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime);

        // Display processes along with all details
        System.out.println("Process " + " Burst Time " +
                " Waiting Time " + " Turnaround Time");
        System.out.println("=======  ==========  ============  ===============");
        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println(i + "\t\t\t" + burstTime[i] +"\t\t\t" +
                    waitingTime[i] +"\t\t\t\t " + turnAroundTime[i]);
        }

        System.out.println("\nAverage waiting time = " +
                (float)totalWaitingTime / (float)n);
        System.out.println("Average turnaround time = " +
                (float)totalTurnAroundTime / (float)n);
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String[] args)
    {
        // Burst time of processes. The array index is the process ID.
        int burstTime[] = {5, 15, 4, 3};

        // Time quantum
        int quantum = 3;

        printAvgTimes(burstTime, quantum);
    }
}
