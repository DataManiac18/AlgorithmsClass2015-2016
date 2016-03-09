import java.util.*;
public class MainTree
{
    public static void main(String[] args)
    {
        int index = 1;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        boolean test = true;
        while(test)
        {
            int input = in.nextInt();
            if(input < 0)
            {
                test = false;
            }
            else
            {
                nums.add(input);
            }
        }
        ArrayList<Integer> zeroes = new ArrayList<Integer>();
        for(int i = 0; i < nums.size(); i++)
        {
            if(nums.get(i) == 0)
            {
                zeroes.add(i);
            }
        }
        int h = 0;
        while(h < zeroes.size())
        {
            boolean isTree = true;
            int root = -1;
            int numRoot;
            if(!((h == 0 && zeroes.get(h) == 0) || (h > 0 && zeroes.get(h)-zeroes.get(h-1)-1 == 0)))
            {
                if(h > 0)
                {
                    numRoot = zeroes.get(h)-zeroes.get(h-1)-1;
                    for(int k = zeroes.get(h-1)+1; k < zeroes.get(h); k++)
                    {
                        if(k % 2 == 0)
                        {
                            if(nums.get(k) != root)
                            {
                                int tempRootNum = numRoot;
                                for(int w = zeroes.get(h-1)+2; w < zeroes.get(h); w += 2)
                                {
                                    if(nums.get(k) == nums.get(w))
                                    {
                                        numRoot--;
                                        w = zeroes.get(h);
                                    }
                                }
                                if(tempRootNum == numRoot)
                                {
                                    root = nums.get(k);
                                }
                            }
                            else
                            {
                                numRoot--;
                            }
                        }
                        else
                        {
                            numRoot--;
                            for(int w = zeroes.get(h-1)+2; w < zeroes.get(h); w += 2)
                            {
                                if(w != k && nums.get(k) == nums.get(w))
                                {
                                    isTree = false;
                                }
                            }
                        }
                    }
                }
                else
                {
                    numRoot = zeroes.get(h);
                    for(int k = 0; k < zeroes.get(h); k++)
                    {
                        if(k % 2 == 0)
                        {
                            if(nums.get(k) != root)
                            {
                                int tempRootNum = numRoot;
                                for(int w = 1; w < zeroes.get(h); w += 2)
                                {
                                    if(nums.get(k) == nums.get(w))
                                    {
                                        numRoot--;
                                        w = zeroes.get(h);
                                    }
                                }
                                if(tempRootNum == numRoot)
                                {
                                    root = nums.get(k);
                                }
                            }
                            else
                            {
                                numRoot--;
                            }
                        }
                        else
                        {
                            numRoot--;
                            for(int w = 1; w < zeroes.get(h); w += 2)
                            {
                                if(w != k && nums.get(k) == nums.get(w))
                                {
                                    isTree = false;
                                }
                            }
                        }
                    } 
                }
                if(numRoot != 1)
                {
                    isTree = false;
                }
            }
            if(isTree)
            {
                System.out.println("Case " + index + " is a tree.");
                index++;
            }
            else
            {
                System.out.println("Case " + index + " is not a tree.");
                index++;
            }
            h += 2;
        }
    }
}