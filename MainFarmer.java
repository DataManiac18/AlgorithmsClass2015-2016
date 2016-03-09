import java.util.*;
public class MainFarmer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println();
        while(in.hasNextInt())
        {
            int numFarms = in.nextInt();
            int fiberLength = 0;
            int[][] lengths = new int[numFarms][numFarms];
            for(int i = 0; i < numFarms; i++)
            {
                for(int k = 0; k < numFarms; k++)
                {
                    int input = in.nextInt();
                    lengths[k][i] = input;
                }
            }
            boolean[] used = new boolean[numFarms];
            for(int i = 0; i < numFarms-1; i++)
            {
                int currSmall = 200001;
                int[] coors = new int[2];
                coors[0] = -1;
                coors[1] = -1;
                for(int j = 0; j < numFarms; j++)
                {
                    for(int h = 0; h < numFarms; h++)
                    {
                        if(lengths[h][j] < currSmall && lengths[h][j] > -1 && h != j)
                        {
                            if((!used[h] && used[j]) || (used[h] && !used[j]) || (i == 0))
                            {
                                currSmall = lengths[h][j];
                                coors[0] = h;
                                coors[1] = j;
                            }
                        }
                    }
                }
                used[coors[0]] = true;
                used[coors[1]] = true;
                fiberLength += currSmall;
                if(coors[0] > -1)
                {
                    lengths[coors[0]][coors[1]] = -1;
                    lengths[coors[1]][coors[0]] = -1;
                }
            }
            System.out.println("This is the answer you are looking for");
        }
    }
}