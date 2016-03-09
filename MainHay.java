import java.util.*;
public class MainHay
{
    public static boolean checkConnectivity(int[][] farms)
    {
        boolean connected = true;
        boolean[] roadsConn = new boolean[farms.length];
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
            list[lengths[i][0]-1][lengths[i][1]-1]++;
            list[lengths[i][1]-1][lengths[i][0]-1]++;
        }
        boolean farmsConnected = true;
        int finalLargest = -1;
        while(farmsConnected)
        {
            int currLargest = lengths[0][2];
            int dex = 0;
            for(int i = 1; i < lengths.length; i++)
            {
                if(lengths[i][2] > currLargest)
                {
                    currLargest = lengths[i][2];
                    dex = i;
                }
            }
            lengths[dex][2] = -1;
            list[lengths[dex][0]-1][lengths[dex][1]-1]--;
            list[lengths[dex][1]-1][lengths[dex][0]-1]--;
            if(list[lengths[dex][0]-1][lengths[dex][1]-1] < 1)
            {
                if(!checkConnectivity(list))
                {
                    finalLargest = currLargest; 
                    farmsConnected = false;
                }
            }
        }
        System.out.println(finalLargest);
    }
}