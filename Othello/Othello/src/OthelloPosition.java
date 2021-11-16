import java.util.*;
import java.lang.*;

/**
 * This class is used to represent game positions. It uses a 2-dimensional char
 * array for the board and a Boolean to keep track of which player has the move.
 *
 * @author Henrik Bj&ouml;rklund
 */

public class OthelloPosition {

    /**
     * For a normal Othello game, BOARD_SIZE is 8.
     */
    // 棋盘的尺寸是8个格子。注意呗static修饰，可以直接通过“类名.变量名”调用。而且被final修饰，不可更改。
    protected static final int BOARD_SIZE = 8;

    /**
     * True if the first player (white) has the move.
     */
    // 如果是白棋先手，那maxPlayer就是ture。
    protected boolean maxPlayer;

    /**
     * The representation of the board. For convenience, the array actually has two
     * columns and two rows more that the actual game board. The 'middle' is used
     * for the board. The first index is for rows, and the second for columns. This
     * means that for a standard 8x8 game board, <code>board[1][1]</code> represents
     * the upper left corner, <code>board[1][8]</code> the upper right corner,
     * <code>board[8][1]</code> the lower left corner, and <code>board[8][8]</code>
     * the lower left corner. In the array, the charachters 'E', 'W', and 'B' are
     * used to represent empty, white, and black board squares, respectively.
     */
    /**
     * board表示棋盘。为了方便检索，在游戏需要的棋盘大小上多加了两行两列。中间给真正的棋盘用。第一个值是行，第二个值是列。
     * 这意味着在8x8的棋盘中，board[1][1]代表左上角，board[1][8]代表右上角，board[8][1]代表左下角，board[1][8]代表右下角。
     * “E”，“W”和“B”分别代表空的，白色，黑色的格子。
     */
    protected char[][] board;

    /**
     * Creates a new position and sets all squares to empty.
     */
    // OthelloPosition()无参构造方法，用于初始化一个空的棋盘
    public OthelloPosition() {
        board = new char[BOARD_SIZE + 2][BOARD_SIZE + 2];
        for (int i = 0; i < BOARD_SIZE + 2; i++)
            for (int j = 0; j < BOARD_SIZE + 2; j++)
                board[i][j] = 'E';

    }

    // OthelloPosition()带参构造方法
    public OthelloPosition(String s) {
        //如果输入的字符串长度不为65（一个代表玩家的字符+64个整数值），那么就生成一个空棋盘
        if (s.length() != 65) {
            board = new char[BOARD_SIZE + 2][BOARD_SIZE + 2];
            for (int i = 0; i < BOARD_SIZE + 2; i++)
                for (int j = 0; j < BOARD_SIZE + 2; j++)
                    board[i][j] = 'E';
        } else {
            //如果输入的字符串长度为65（一个代表玩家的字符+64个整数值），那么给布尔值赋值
            board = new char[BOARD_SIZE + 2][BOARD_SIZE + 2];
            // 如果白棋先走，那maxPlayer就是true，代表当前是maxPlayer在走棋。
            maxPlayer = s.charAt(0) == 'W';
            //根据字符串给内部棋盘所占的空间赋值。
            for (int i = 1; i <= 64; i++) {
                char c;
                if (s.charAt(i) == 'E') {
                    c = 'E';
                } else if (s.charAt(i) == 'O') {
                    c = 'W';
                } else {
                    c = 'B';
                }
                // column范围1-8，row范围1-8
                int column = ((i - 1) % 8) + 1;
                int row = (i - 1) / 8 + 1;
                board[row][column] = c;
            }
        }

    }

    /**
     * Initializes the position by placing four markers in the middle of the board.
     */
    //通过在棋盘的中部放置四个棋子来初始化位置。maxPlayer 设定为 true。
    public void initialize() {
        board[BOARD_SIZE / 2][BOARD_SIZE / 2] = board[BOARD_SIZE / 2 + 1][BOARD_SIZE / 2 + 1] = 'W';
        board[BOARD_SIZE / 2][BOARD_SIZE / 2 + 1] = board[BOARD_SIZE / 2 + 1][BOARD_SIZE / 2] = 'B';
        maxPlayer = true;
    }

    /* getMoves and helper functions */

    /**
     * Returns a linked list of <code>OthelloAction</code> representing all possible
     * moves in the position. If the list is empty, there are no legal moves for the
     * player who has the move.
     */
    // 返回一个由OthelloAction类对象组成的链表。这个表内代表着所有可能的移动方案。如果为空，说明当前无步可走。
    public LinkedList<OthelloAction> getMoves() {
        // 定义一个布尔值的2x2数组
        boolean[][] candidates = new boolean[BOARD_SIZE][BOARD_SIZE];
        // 创建一个内容为OthelloAction对象的链表moves
        LinkedList<OthelloAction> moves = new LinkedList<OthelloAction>();
        // 遍历棋盘，如果这个位置是一个还没有棋子的侯选位置，就在链表中添加true，反之为false。
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                candidates[i][j] = isCandidate(i + 1, j + 1);
        //根据上面的数组 遍历棋盘，如果这个候选位置可以按照规则落子，那就再链表中添加一个OthelloAction类对象（包含行列的信息）。
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (candidates[i][j])
                    if (isMove(i + 1, j + 1))
                        moves.add(new OthelloAction(i + 1, j + 1));
        return moves;
    }

    /**
     * Check if it is possible to do a move from this position
     */
    private boolean isMove(int row, int column) {
        if (checkNorth(row, column))
            return true;
        if (checkNorthEast(row, column))
            return true;
        if (checkEast(row, column))
            return true;
        if (checkSouthEast(row, column))
            return true;
        if (checkSouth(row, column))
            return true;
        if (checkSouthWest(row, column))
            return true;
        if (checkWest(row, column))
            return true;
        if (checkNorthWest(row, column))
            return true;

        return false;
    }

    /**
     * Check if it is possible to do a move to the north from this position
     */
    //检查北面，看是否满足可以下子的条件
    private boolean checkNorth(int row, int column) {
        if (!isOpponentSquare(row - 1, column))
            return false;
        for (int i = row - 2; i > 0; i--) {
            if (isFree(i, column))
                return false;
            if (isOwnSquare(i, column))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the east from this position
     */
    // 检查东面，看是否满足可以下子的条件
    private boolean checkEast(int row, int column) {
        if (!isOpponentSquare(row, column + 1))
            return false;
        for (int i = column + 2; i <= BOARD_SIZE; i++) {
            if (isFree(row, i))
                return false;
            if (isOwnSquare(row, i))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the south from this position
     */
    // 检查南面，看是否满足可以下子的条件
    private boolean checkSouth(int row, int column) {
        if (!isOpponentSquare(row + 1, column))
            return false;
        for (int i = row + 2; i <= BOARD_SIZE; i++) {
            if (isFree(i, column))
                return false;
            if (isOwnSquare(i, column))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the west from this position
     */
    // 检查西面，看是否满足可以下子的条件
    private boolean checkWest(int row, int column) {
        if (!isOpponentSquare(row, column - 1))
            return false;
        for (int i = column - 2; i > 0; i--) {
            if (isFree(row, i))
                return false;
            if (isOwnSquare(row, i))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the northest from this position
     */
    // 检查东北方向，看是否满足可以下子的条件
    private boolean checkNorthEast(int row, int column) {
        if (!isOpponentSquare(row - 1, column + 1))
            return false;
        for (int i = 2; row - i > 0 && column + i <= BOARD_SIZE; i++) {
            if (isFree(row - i, column + i))
                return false;
            if (isOwnSquare(row - i, column + i))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the southeast from this position
     */
    // 检查东南方向，看是否满足可以下子的条件
    private boolean checkSouthEast(int row, int column) {
        if (!isOpponentSquare(row + 1, column + 1))
            return false;
        for (int i = 2; row + i <= BOARD_SIZE && column + i <= BOARD_SIZE; i++) {
            if (isFree(row + i, column + i))
                return false;
            if (isOwnSquare(row + i, column + i))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the soutwest from this position
     */
    // 检查西南方向，看是否满足可以下子的条件
    private boolean checkSouthWest(int row, int column) {
        if (!isOpponentSquare(row + 1, column - 1))
            return false;
        for (int i = 2; row + i <= BOARD_SIZE && column - i > 0; i++) {
            if (isFree(row + i, column - i))
                return false;
            if (isOwnSquare(row + i, column - i))
                return true;
        }
        return false;
    }

    /**
     * Check if it is possible to do a move to the northwest from this position
     */
    // 检查西北方向，看是否满足可以下子的条件
    private boolean checkNorthWest(int row, int column) {
        if (!isOpponentSquare(row - 1, column - 1))
            return false;
        for (int i = 2; row - i > 0 && column - i > 0; i++) {
            if (isFree(row - i, column - i))
                return false;
            if (isOwnSquare(row - i, column - i))
                return true;
        }
        return false;
    }

    /**
     * Check if the position is occupied by the opponent
     */
    // 用来检查这个格子是不是已经被对手占了
    private boolean isOpponentSquare(int row, int column) {
        // 如果当前是白棋要走，且这个查询的位置是黑棋，返回true。
        if (maxPlayer && (board[row][column] == 'B'))
            return true;
        // 反之
        if (!maxPlayer && (board[row][column] == 'W'))
            return true;
        return false;
    }

    /**
     * Check if the position is occupied by the player
     */
    // 用来检查这个格子是不是已经被自己占了
    private boolean isOwnSquare(int row, int column) {
        if (!maxPlayer && (board[row][column] == 'B'))
            return true;
        if (maxPlayer && (board[row][column] == 'W'))
            return true;
        return false;
    }

    /**
     * Check if the position is a candidate for a move (not empty and has a
     * neighbour)
     *
     * @return true if it is a candidate
     */
    // 用来检查这个格子是不是候选位置
    private boolean isCandidate(int row, int column) {
        if (!isFree(row, column))
            return false;
        if (hasNeighbor(row, column))
            return true;
        return false;
    }

    /**
     * Check if the position has any non-empty squares
     *
     * @return true if is has any neighbours
     */
    // 检查当前格子是否有邻居格子
    private boolean hasNeighbor(int row, int column) {
        if (!isFree(row - 1, column))
            return true;
        if (!isFree(row - 1, column + 1))
            return true;
        if (!isFree(row, column + 1))
            return true;
        if (!isFree(row + 1, column + 1))
            return true;
        if (!isFree(row + 1, column))
            return true;
        if (!isFree(row + 1, column - 1))
            return true;
        if (!isFree(row, column - 1))
            return true;
        if (!isFree(row - 1, column - 1))
            return true;
        return false;
    }

    /**
     * Check if the position is free/empty
     */
    // 检查当前格子是否是空的
    private boolean isFree(int row, int column) {
        if (board[row][column] == 'E')
            return true;
        return false;
    }

    /* toMove */

    /**
     * Returns true if the first player (white) has the move, otherwise false.
     */
    // 如果当前是白棋要走，返回true
    public boolean toMove() {
        return maxPlayer;
    }

    /* makeMove and helper functions */

    /**
     * Returns the position resulting from making the move <code>action</code> in
     * the current position. Observe that this also changes the player to move next.
     */
    // 谁用makeMove 这个函数，谁用try catch处理。
    public OthelloPosition makeMove(OthelloAction action) throws IllegalMoveException {

        /*
         * TODO: write the code for this method and whatever helper functions it needs.
         */
        //克隆一个OthelloPosition
        OthelloPosition currentPosition = clone();
        if (isIllegalAction(action)) {
            throw new IllegalMoveException(action);
        } else if (action.isPassMove()) {
            currentPosition.maxPlayer = !maxPlayer;
            return currentPosition;
        } else {
            char playerMarker = maxPlayer ? 'W' : 'B';
            currentPosition.board[action.getRow()][action.getColumn()] = playerMarker;
            currentPosition.board = flipPieces(currentPosition, action, playerMarker);
            currentPosition.maxPlayer = !maxPlayer;
            return currentPosition;
        }
    }

    public char[][] flipPieces(OthelloPosition currentPosition, OthelloAction action, char playerMarker) {
        int row = action.getRow();
        int column = action.getColumn();
        char[][] currentBoard = currentPosition.board;
        return toCheck(row, column, currentBoard, playerMarker);
    }

    public char[][] toCheck(int row, int column, char[][] currentBoard, char playerMarker) {
        boolean[] flags = {checkEast(row, column), checkWest(row, column), checkNorth(row, column), checkSouth(row, column)
                , checkNorthWest(row, column), checkNorthEast(row, column), checkSouthEast(row, column), checkSouthWest(row, column)};
        int[][] checkIndex = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        for (int direction = 0; direction < flags.length; direction++) {
            if (flags[direction]) {
                for (int i = 1; isOpponentSquare(row + checkIndex[direction][0] * i, column + checkIndex[direction][1] * i); i++) {
                    currentBoard[row + checkIndex[direction][0] * i][column + checkIndex[direction][1] * i] = playerMarker;
                }
            }
        }
        return currentBoard;
    }


    public boolean isIllegalAction(OthelloAction action) {
        if (action.isPassMove()){
            return false;
        }
        return action.getColumn() > 8 || action.getColumn() < 1 || action.getRow() > 8 || action.getRow() < 1;
    }


    /**
     * Returns a new <code>OthelloPosition</code>, identical to the current one.
     */
    //克隆一个新的OthelloPosition类对象，将现在的OthelloPosition更新过去
    protected OthelloPosition clone() {
        OthelloPosition newPosition = new OthelloPosition();
        newPosition.maxPlayer = maxPlayer;
        for (int i = 0; i < BOARD_SIZE + 2; i++)
            for (int j = 0; j < BOARD_SIZE + 2; j++)
                newPosition.board[i][j] = board[i][j];
        return newPosition;
    }

    /* illustrate and other output functions */

    /**
     * Draws an ASCII representation of the position. White squares are marked by
     * '0' while black squares are marked by 'X'.
     */
    // 控制台生成棋盘, 白棋是O，黑棋是X。
    public void illustrate() {
        System.out.print("   ");
        for (int i = 1; i <= BOARD_SIZE; i++)
            System.out.print("| " + i + " ");
        System.out.println("|");
        printHorizontalBorder();
        for (int i = 1; i <= BOARD_SIZE; i++) {
            System.out.print(" " + i + " ");
            for (int j = 1; j <= BOARD_SIZE; j++) {
                if (board[i][j] == 'W') {
                    System.out.print("| 0 ");
                } else if (board[i][j] == 'B') {
                    System.out.print("| X ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("| " + i + " ");
            printHorizontalBorder();
        }
        System.out.print("   ");
        for (int i = 1; i <= BOARD_SIZE; i++)
            System.out.print("| " + i + " ");
        System.out.println("|\n");
    }

    // 控制台生成棋盘边框
    private void printHorizontalBorder() {
        System.out.print("---");
        for (int i = 1; i <= BOARD_SIZE; i++) {
            System.out.print("|---");
        }
        System.out.println("|---");
    }

    // 重写了toString方法
    public String toString() {
        String s = "";
        char c, d;
        if (maxPlayer) {
            s += "W";
        } else {
            s += "B";
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                d = board[i][j];
                if (d == 'W') {
                    c = 'O';
                } else if (d == 'B') {
                    c = 'X';
                } else {
                    c = 'E';
                }
                s += c;
            }
        }
        return s;
    }

}
