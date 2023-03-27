import java.util.Scanner;

import java.util.*;

//SafeInput class
class SafeInput
{
    public static int getRangedInt(Scanner console, String prompt, int low, int max) {

        int result;

// Loop until valid input is read in
        do {
// Prompt user and loop until they have entered a number
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                console.nextLine();
                System.out.print(prompt);
            }

// Read in the number
            result = console.nextInt();
        } while (result < low || result > max);

// Return the result
        return result;
    }

    public static boolean getYNConfirm(Scanner console, String prompt)
    {
        String str;

// Prompt user and loop until they have entered a number
        System.out.print(prompt);

// Read in the string
        str = console.next();
        if (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("y"))
            return true;

// Return false
        return false;
    }
}


//Beginning of TicTacToe code
public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    // Sets up the board elements
    private static void clearBoard()
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = " ";
    }

    // displays the TicTacToe board
    private static void display()
    {
        System.out.println("\n 1 2 3\n");
        for(int row = 0; row < 3; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col] + " ");
                if (col != 2)
                    System.out.print(" |");
            }
            System.out.println();
            if (row != 2){
                System.out.println(" ____|____|____");
                System.out.println(" | | ");
            }
        }
        System.out.println();
    }

    // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
    private static boolean isValidMove(int row, int col)
    {
        if (row>=0 && row<ROW && col>=0 && col<COL && board[row][col].equals(" "))
            return true;

        return false;
    }

    // checks to see if there is a win state on the current board
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
            return true;
        return false;
    }

    // checks for a col win for specified player
    private static boolean isColWin(String player)
    {
        for(int col=0; col<COL; col++)
        {
            if(board[0][col].equals(player) && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]))
                return true;
        }
        return false;
    }

    // checks for a row win for the specified player
    private static boolean isRowWin(String player)
    {
        for(int row=0; row<ROW; row++)
        {
            if(board[row][0].equals(player) && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]))
                return true;
        }
        return false;
    }

    // checks for a diagonal win for the specified player
    private static boolean isDiagonalWin(String player)
    {
        if(board[1][1].equals(player) && ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))))
            return true;
        return false;
    }

    // checks for a tie condition: all spaces on the board are filled OR there is an X and an O in every win vector

    private static boolean isTie()
    {
        for(int row=0; row<ROW; row++)
        {
            for(int col=0; col<COL; col++)
            {
                if(board[row][col].equals(" "))
                    return false;
            }
        }
        return true;
    }

    //main method
    public static void main (String[] args)
    {
//create instance of Scanner class
        Scanner console = new Scanner(System.in);

        do
        {
//set player to "X"
            String player = "X";

//clear the board
            clearBoard();

//display the board
            display();

//start the game
            while(true)
            {
                System.out.println ("Player " + player);
//get a move
                int rowMove = SafeInput.getRangedInt(console, "Enter row: ", 1, 3);
                int colMove = SafeInput.getRangedInt(console, "Enter col: ", 1, 3);
                rowMove--;
                colMove--;
                board[rowMove][colMove] = player;

//display the board
                display();

//get the status
                if(isWin(player))
                {
                    System.out.println (player + " won!");
                    break;
                }
                else if(isTie())
                {
                    System.out.println ("This game is a Tie!");
                    break;
                }

//toggle the player
                if(player == "X")
                    player = "O";
                else
                    player = "X";

            }//end of while

        }while(SafeInput.getYNConfirm(console, "\nContinue? "));

    }
}
//Start
//Player X turn
//Input row
//Input column
//Output placement
//Player O turn
//Input row
//input column
//output placement
//game ends
//continue? Y/N