import java.util.*;

public class Bingogame {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int MAX_NUMBER = 75;

    private int[][] board = new int[ROWS][COLS];
    private Set<Integer> calledNumbers = new HashSet<>();

    public BingoGame() {
        initializeBoard();
    }

    private void initializeBoard() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= MAX_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = numbers.get(i * COLS + j);
            }
        }
    }

    public void playGame() {
        while (!isBingo()) {
            int nextNumber = callNextNumber();
            System.out.println("Next number is: " + nextNumber);
            markNumber(nextNumber);
        }
        System.out.println("Bingo! You win!");
    }

    private int callNextNumber() {
        int nextNumber;
        do {
            nextNumber = new Random().nextInt(MAX_NUMBER) + 1;
        } while (calledNumbers.contains(nextNumber));
        calledNumbers.add(nextNumber);
        return nextNumber;
    }

    private void markNumber(int number) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == number) {
                    board[i][j] = 0;
                    return;
                }
            }
        }
    }

    private boolean isBingo() {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0] == 0 && board[i][1] == 0 && board[i][2] == 0 && board[i][3] == 0 && board[i][4] == 0) {
                return true;
            }
        }
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == 0 && board[1][j] == 0 && board[2][j] == 0 && board[3][j] == 0 && board[4][j] == 0) {
                return true;
            }
        }
        if (board[0][0] == 0 && board[1][1] == 0 && board[2][2] == 0 && board[3][3] == 0 && board[4][4] == 0) {
            return true;
        }
        if (board[0][4] == 0 && board[1][3] == 0 && board[2][2] == 0 && board[3][1] == 0 && board[4][0] == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BingoGame game = new BingoGame();
        game.playGame();
    }
}

