import java.util.*;
public class MainHayGood
{
    public static boolean checkConnectivity(int[][] farms, int numOnes)
    {
        boolean connected = true;
        boolean[] roadsConn = new boolean[farms.length];
        if(numOnes > 0)
        {
            roadsConn[0] = true;
            ArrayList<Integer> directs = new ArrayList<Integer>();
            directs.add(0);
            while(directs.size() > 0)
            {
                ArrayList<Integer> directsTemp = new ArrayList<Integer>();
                for(int i = 0; i < directs.size(); i++)
                {
                    for(int k = 0; k < farms.length; k++)
                    {
                        if(farms[directs.get(i)][k] > 0 && !roadsConn[k])
                        {
                            roadsConn[k] = true;
                            directsTemp.add(k);
                        }
                    }
                }
                directs = directsTemp;
            }

            for(int i = 0; i < roadsConn.length; i++)
            {
                if(!roadsConn[i])
                {
                    connected = false;
                }
            }
        }
        else
        {
            return false;
        }
        return connected;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println();
        int numFarms = in.nextInt();
        int numRoads = in.nextInt();
        int[][] lengths = new int[numRoads][3];
        int[][] list = new int[numFarms][numFarms];
        //first of three is origin farm, second is destination, third is road length
        for(int i = 0; i < numRoads; i++)
        {
            for(int k = 0; k < 3; k++)
            {
                int input = in.nextInt();
                lengths[i][k] = input;
            }
        }
        boolean farmsConnected = false;
        int finalLargest = -1;
        int currentRuns = 0;
        int numOnes = 0;
        while(!farmsConnected)
        {
            int currSmallest = 1000000001;
            int dex = -1;
            for(int i = 0; i < lengths.length; i++)
            {
                if(lengths[i][2] < currSmallest && lengths[i][2] > -1)
                {
                    currSmallest = lengths[i][2];
                    dex = i;
                }
            }
            lengths[dex][2] = -1;
            if(lengths[dex][0] == 1 || lengths[dex][1] == 1)
            {
                numOnes++;
            }
            list[lengths[dex][0]-1][lengths[dex][1]-1]++;
            list[lengths[dex][1]-1][lengths[dex][0]-1]++;
            currentRuns++;
            if(!(currentRuns < numFarms-1))
            {
                if(checkConnectivity(list, numOnes))
                {
                    farmsConnected = true;
                }
            }
            finalLargest = currSmallest; 
        }
        System.out.println(finalLargest);
    }
}