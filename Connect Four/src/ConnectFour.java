import java.util.Scanner;

public class ConnectFour {
    private char[][] board;
    private int rows;
    private int columns;
    private char player;

    public ConnectFour(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new char[rows][columns];
        player = 'X';
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        System.out.println("--------------");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    private boolean isColumnFull(int col) {
        return board[0][col] != '-';
    }

    private boolean isValidColumn(int col) {
        return col >= 0 && col < columns;
    }

    private boolean makeMove(int col) {
        if (!isValidColumn(col) || isColumnFull(col)) {
            return false;
        }
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][col] == '-') {
                board[i][col] = player;
                return true;
            }
        }
        return false;
    }

    private boolean hasWon() {
        // Check rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (board[i][j] == player && board[i][j + 1] == player &&
                        board[i][j + 2] == player && board[i][j + 3] == player) {
                    return true;
                }
            }
        }
        // Check columns
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == player && board[i + 1][j] == player &&
                        board[i + 2][j] == player && board[i + 3][j] == player) {
                    return true;
                }
            }
        }
        // Check diagonal
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player &&
                        board[i + 2][j + 2] == player && board[i + 3][j + 3] == player) {
                    return true;
                }
            }
        }
        // Check reverse diagonal
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 3; j < columns; j++) {
                if (board[i][j] == player && board[i + 1][j - 1] == player &&
                        board[i + 2][j - 2] == player && board[i + 3][j - 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Connect Four!");
        System.out.println("Player 1: X");
        System.out.println("Player 2: O");
        System.out.println("To make a move, enter a column number (0-" + (columns - 1) + ")");
        System.out.println("--------------");
        int move;
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + player + "'s turn. Enter column number:");
            move = scanner.nextInt();

            if (makeMove(move)) {
                if (hasWon()) {
                    gameEnded = true;
                    System.out.println("Player " + player + " has won!");
                    printBoard();
                } else if (isBoardFull()) {
                    gameEnded = true;
                    System.out.println("Game ended in a tie.");
                    printBoard();
                } else {
                    player = player == 'X' ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < columns; i++) {
            if (!isColumnFull(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour(6, 7);
        game.play();
    }
}
