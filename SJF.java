public class SJF
{

    // Method that calculates the waiting time for all processes
    static int[] calcWaitingTime(int burstTime[], int quantum)
    {
        int n = burstTime.length;
        int[] ar= new int[n]; //number of processes
        for(int i=0;i<n;i++){
            ar[i]=i; //ID of process
        }
        int[] time=new int[n]; //the waiting time of each process
        int[] bt;
        bt = burstTime.clone(); //copy the table burstTime[]->bt to make the appropriate changes
        //sort the bt and ar according to the smallest burst time
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(bt[j]>bt[j+1]){
                    int temp=bt[j]; //temp:helping role
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;
                    temp=ar[j];
                    ar[j]=ar[j+1];
                    ar[j+1]=temp;
                }
            }
        }
        //the waiting time is the previous waiting time plus the burst time of previoues process in the sorted table ar
        for(int i=1;i<n;i++){
            time[i]=time[i-1]+bt[i-1];
        }
        for(int i=0;i<n;i++){//print IDs
            System.out.println(ar[i]);
        }
        int[]waitingtime=time.clone(); //copy the table time to the table waitingtime
        for (int i=0;i<n;i++){
            waitingtime[ar[i]]=time[i];// according to the classification
        }
        return waitingtime;

    }

    // Method that calculates turn around time for all processes
    static int[] calcTurnAroundTime(int burstTime[], int waitingTime[])
    {
        int n = burstTime.length; //number of processes
        int[] ar= new int[n];//IDs
        for(int i=0;i<n;i++){
            ar[i]=i;
        }
        int[] time=new int[n];
        //calculating turnaround time by adding burstTime and waitingTime for each process
        for(int i=0;i<n;i++){
            time[i]=burstTime[i]+waitingTime[i];
        }

        return time;
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
            System.out.println(i + "\t\t\t" + burstTime[i] +"\t\t\t " +
                    waitingTime[i] +"\t\t\t " + turnAroundTime[i]);
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
