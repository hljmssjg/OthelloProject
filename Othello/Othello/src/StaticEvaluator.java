import java.util.LinkedList;

public class StaticEvaluator implements OthelloEvaluator {
    public static int[][] WPC = {
            {500, -25, 10, 5, 5, 10, -25, 500},
            {-25, -45, 1, 1, 1, 1, -45, -25},
            {10, 1, 3, 2, 2, 3, 1, 10},
            {5, 1, 2, 1, 1, 2, 1, 5},
            {5, 1, 2, 1, 1, 2, 1, 5},
            {10, 1, 3, 2, 2, 3, 1, 10},
            {-25, -45, 1, 1, 1, 1, -45, -25},
            {500, -25, 10, 5, 5, 10, -25, 500}
    };

    @Override
    public int evaluate(OthelloPosition position) {
        int value = 0;
        position.initialize();
        char[][] board = position.board;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (board[i][j] == 'W') {
                    value += WPC[i - 1][j - 1];
                } else if (board[i][j] == 'B') {
                    value -= WPC[i - 1][j - 1];
                }
            }
        }
        int ownActions = position.getMoves().size();
        OthelloPosition opponentPosition = position.clone();
        opponentPosition.maxPlayer = !position.maxPlayer;
        int opponentActions = opponentPosition.getMoves().size();
        int movesDiff = ownActions - opponentActions;

        return value + movesDiff;
    }

}
