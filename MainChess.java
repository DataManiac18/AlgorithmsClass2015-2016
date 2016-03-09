import java.util.*;
public class MainChess {
    public static int[][] getKnightNeighbors(String[][] board, int currX, int currY)
    {
        int[][] neighbors = new int[8][2];
        if(currX-1 < board.length && currY-2 < board[0].length && currX-1 > -1 && currY-2 > -1)
        {
            neighbors[0][0] = currX-1; neighbors[0][1] = currY-2;
        }
        else
        {
            neighbors[0][0] = -1; neighbors[0][1] = -1;
        }
        if(currX+1 < board.length && currY-2 < board[0].length && currX+1 > -1 && currY-2 > -1)
        {
            neighbors[1][0] = currX+1; neighbors[1][1] = currY-2;
        }
        else
        {
            neighbors[1][0] = -1; neighbors[1][1] = -1;
        }
        if(currX-2 < board.length && currY-1 < board[0].length && currX-2 > -1 && currY-1 > -1)
        {
            neighbors[2][0] = currX-2; neighbors[2][1] = currY-1;
        }
        else
        {
            neighbors[2][0] = -1; neighbors[2][1] = -1;
        }
        if(currX+2 < board.length && currY-1 < board[0].length && currX+2 > -1 && currY-1 > -1)
        {
            neighbors[3][0] = currX+2; neighbors[3][1] = currY-1;
        }
        else
        {
            neighbors[3][0] = -1; neighbors[3][1] = -1;
        }
        if(currX-2 < board.length && currY+1 < board[0].length && currX-2 > -1 && currY+1 > -1)
        {
            neighbors[4][0] = currX-2; neighbors[4][1] = currY+1;
        }
        else
        {
            neighbors[4][0] = -1; neighbors[4][1] = -1;
        }
        if(currX+2 < board.length && currY+1 < board[0].length && currX+2 > -1 && currY+1 > -1)
        {
            neighbors[5][0] = currX+2; neighbors[5][1] = currY+1;
        }
        else
        {
            neighbors[5][0] = -1; neighbors[5][1] = -1;
        }
        if(currX-1 < board.length && currY+2 < board[0].length && currX-1 > -1 && currY+2 > -1)
        {
            neighbors[6][0] = currX-1; neighbors[6][1] = currY+2;
        }
        else
        {
            neighbors[6][0] = -1; neighbors[6][1] = -1;
        }
        if(currX+1 < board.length && currY+2 < board[0].length && currX+1 > -1 && currY+2 > -1)
        {
            neighbors[7][0] = currX+1; neighbors[7][1] = currY+2;
        }
        else
        {
            neighbors[7][0] = -1; neighbors[7][1] = -1;
        }
        return neighbors;
    }

    public static boolean checkArray(boolean[][] arr)
    {
        boolean check = true;
        for(int i = 0; i < arr.length; i++)
        {
            for(int k = 0; k < arr[0].length; k++)
            {
                if(!arr[i][k])
                {
                    check = false;
                }
            }
        }
        return check;
    }

    public static Stack<String> goThroughNeighbors(String[][] board1, int currNum, int currLett, boolean[][] boolBoard)
    {
        String[][] board = board1;
        Stack<String> order = new Stack<String>();
        boolBoard[currNum][currLett] = true;
        int[][] neighbors = getKnightNeighbors(board, currNum, currLett);
        int check = 0;
        for(int k = 0; k < 8; k++)
        {
            if(neighbors[k][1] > -1 && !boolBoard[neighbors[k][0]][neighbors[k][1]])
            {
                check++;
                order = new Stack<String>();
                order.push(board[currNum][currLett]);
                Stack<String> newOrder = goThroughNeighbors(board, neighbors[k][0], neighbors[k][1], boolBoard);
                Stack<String> temp = new Stack<String>();
                while(!newOrder.empty())
                {
                    String x = newOrder.pop();
                    temp.push(x); 
                }
                while(!temp.empty())
                {
                    String x = temp.pop();
                    order.push(x);
                }
                if(checkArray(boolBoard))
                {
                    k = 8;
                }
            }
        }
        if(check == 0)
        {
            order.push(board[currNum][currLett]);
        }
        if(!checkArray(boolBoard))
        {
            boolBoard[currNum][currLett] = false;
        }
        return order;
    }

    public static String getLetter(int i)
    {
        return String.valueOf((char) (i + 65));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numScenarios = in.nextInt();
        for(int current = 1; current < numScenarios+1; current++)
        {
            boolean ended = false;
            int nums = in.nextInt();
            int letts = in.nextInt();
            Stack<String> order;
            String message = "";
            String[][] boardPos = new String[nums][letts];
            boolean[][] boolBoard = new boolean[nums][letts];
            for(int i = 1; i < nums+1; i++)
            {
                for(int j = 0; j < letts; j++)
                {
                    boardPos[i-1][j] = "" + getLetter(j) + i;
                }
            }
            for(int i = 0; i < nums; i++)
            {
                for(int j = 0; j < letts; j++)
                {
                    order = goThroughNeighbors(boardPos, i, j, boolBoard);
                    if(order.size() == nums*letts)
                    {
                        Stack<String> temp = new Stack<String>();
                        while(!order.empty())
                        {
                            temp.push(order.pop());
                        }
                        while(!temp.empty())
                        {
                            message += temp.pop();
                        }
                        i = nums;
                        j = letts;
                    }
                    boolBoard = new boolean[nums][letts];
                }
            }
            if(message.length() < 1)
            {
                message = "impossible";
            }

            System.out.println("Scenario #" + current + ":");
            System.out.println(message);
            System.out.println();
        }
    }
}