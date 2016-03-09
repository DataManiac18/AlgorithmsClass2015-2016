import java.util.*;
import java.math.BigDecimal;
public class MainEx
{
    public static void main(String[] args)
    {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        ArrayList<Double> numsD = new ArrayList<Double>();
        Scanner in = new Scanner(System.in);
        boolean currInt = false;
        System.out.println(2.5 % 2);
        while(in.hasNextDouble() || in.hasNextInt())
        {
            if(currInt)
            {
                int input = in.nextInt();
                nums.add(input);
            }
            else
            {
                double input = in.nextDouble();
                numsD.add(input);
            }
            currInt = !currInt;
        }
        for(int i = 0; i < nums.size(); i++)
        {
            BigDecimal calculationH = BigDecimal.pow(nums.get(i), numsD.get(i));
            String c = new BigDecimal(calculationH).toPlainString();
            if(Double.valueOf(c) % 1 == 0.0)
            {
                System.out.println(c);
            }
            else if(calculationH > 0.0 && calculationH < 1)
            {
                if(dex > 0)
                {
                    System.out.println(c.substring(1,dex));
                }
                else
                {
                    System.out.println(c.substring(1));
                }
            }
            else
            {
                if(dex > 0)
                {
                    System.out.println(c.substring(0,dex));
                }
                else
                {
                    System.out.println(c);
                }
            }
        }
    }
}
