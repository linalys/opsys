import java.util.ArrayList;

public class WorstFit
{
    /*
     * Method to allocate memory to blocks according to the worst fit
     * algorithm. It should return an ArrayList of Integers, where the
     * index is the process ID (zero-indexed) and the value is the block
     * number (also zero-indexed).
     */
    static ArrayList<Integer> worstFit(int sizeOfBlocks[], int sizeOfProcesses[])
    {

        int m=sizeOfBlocks.length; //number o blocks
        int n=sizeOfProcesses.length; //number of processes
        ArrayList<Integer> array = new ArrayList<Integer>(n); // Stores block id -> block allocated to a process
        //for each process
        for(int i=0;i<n;i++){
            //We want to find the biggest size  among the free available partitions
            int worst=-255; //initialize the maximum block size(worst) with -255
            for(int j=0;j<m;j++){
                if(sizeOfBlocks[j]>=sizeOfProcesses[i]) { //find suitable blocks
                    if (worst == -255) { //if we haven't found a price for worst
                        worst = j;
                    }
                    else{
                       if(sizeOfBlocks[worst]<sizeOfBlocks[j]){ //if we have-> check if the new price is bigger
                           worst=j; //replace it
                       }
                    }
                }
            }
            if (worst != -255)// If we could find a block for current process
            {
                array.add(worst);  // allocate block worst to process
                sizeOfBlocks[worst] -= sizeOfProcesses[i];  // reduce available memory in this block
            }
            else {
                array.add(-255); //add price -255->has not been actually allocated
            }
        }
        return array;


    }

    // Method to print the memory allocation
    public static void printMemoryAllocation(ArrayList<Integer> memAllocation) {
        System.out.println("Process No.\tBlock No.");
        System.out.println("===========\t=========");
        for (int i = 0; i < memAllocation.size(); i++)
        {
            System.out.print("\t " + i + "\t\t\t");
            // if a process has been allocated position -255, it means that it
            // has not been actually allocated
            if (memAllocation.get(i) != -255)
                System.out.print(memAllocation.get(i));
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String[] args)
    {
        /* There are 5 available blocks in this example. The block ID
         * is the array index and the available block size is the value.
         * So we have the following blocks and sizes:
         *
         *   BlockID    Size
         *   =======    ====
         *      0        200
         *      1        500
         *      2        100
         *      3        300
         *      4        600
         *
         */
        int sizeOfBlocks[] = {200, 500, 100, 300, 600};
        /* And there are 4 processes. The process ID is the array
         * index. So we have these processes and sizes:
         *
         *   ProcessID     Size
         *   =========     ====
         *       0          214
         *       1          415
         *       2          112
         *       3          425
         */
        int sizeOfProcesses[] = {214, 415, 112, 425};

        ArrayList<Integer> memAlloc = worstFit(sizeOfBlocks, sizeOfProcesses);
        printMemoryAllocation(memAlloc);
    }
}

