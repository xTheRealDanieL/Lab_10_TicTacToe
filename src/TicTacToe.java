import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;

    private static final int COL = 3;

    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        boolean finished = false;
        boolean playing = true;
        Scanner in = new Scanner(System.in);
        String player = "X";
        int moveCnt = 0;
        int row = -1;
        int col = -1;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;
        do // program loop
        {
            // begin a game
            player = "X";
            playing = true;
            moveCnt = 0;
            clearBoard();
            do // game loop
            {
                // get the move
                do {
                    display();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(in, "Enter row", 1, 3);
                    col = SafeInput.getRangedInt(in, "Enter col", 1, 3);
                    row--;
                    col--;
                    if (isValidMove(row, col) == false) {
                        System.out.println("There is already a move there");
                    }
                } while (!isValidMove(row, col));
                board[row][col] = player;
                moveCnt++;

                if (moveCnt >= MOVES_FOR_WIN && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    playing = false;
                }

                if (moveCnt >= MOVES_FOR_TIE && isTie()) {
                    display();
                    System.out.println("It's a TIE");
                    playing = false;
                }

                // Toggle the player var back and forth from X to O

                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }
            } while (playing);

            finished = SafeInput.getYNConfirm(in, "Done playing ?");
        } while (!finished);

    }

    private static void clearBoard() {
        //sets all the board elements to a space
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        // shows the Tic Tac Toe game
        for (int row = 0; row < ROW; row++) {
            System.out.print("| ");
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagnalWin(player)) {
            return true;
        }

        return false;
    }

    private static boolean isColWin(String player) {
        // checks for a col win for specified player
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        // checks for a row win for specified player
        for (int row = 0; row < ROW; row++) {
            if (board[0][row].equals(player) && board[1][row].equals(player) && board[2][row].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        // checks for a diagonal win for specified player
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if(board[row][col] == " ")
                {
                    return false;
                }
            }
        }
        return true;
    }
}


