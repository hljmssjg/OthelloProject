
/**
 * This class represents a 'move' in a game. The move is simply represented by
 * two integers: the row and the column where the player puts the marker. In
 * addition, the <code>OthelloAction</code> has a field where the estimated
 * value of the move can be stored during computations.
 * 
 * @author Henrik Bj&ouml;rklund
 */

public class OthelloAction {

    /** The row where the marker is placed. */
    // protected意味着这个变量不可以被不同的包中的无关类调用
    // 定义一个protected变量row = -1表示行数
    protected int row = -1;

    /** The column where the marker is placed. */
    // 定义一个protected变量column = -1表示列数
    protected int column = -1;

    /** The estimated value of the move. */
    // 定义一个移动的估计数值
    protected int value = 0;

    /** True if the player has to pass, i.e., if there is no legal move. */
    // 定义一个布尔值pass，代表着当前棋手是否可以有子可下。
    // 如果当前不可走，那就应该"pass"了，为true。
    protected boolean pass = false;

    /**
     * Creates a new <code>OthelloAction</code> with row <code>r</code>, column
     * <code>c</code>, and value 0.
     */
    // OthelloAction的带参构造方法，使用行数和列数构造。
    public OthelloAction(int r, int c) {
        row = r;
        column = c;
        value = 0;
    }
    // OthelloAction的带参构造方法，使用行数和列数和是否可以移动构造。
    public OthelloAction(int r, int c, boolean p) {
        row = r;
        column = c;
        value = 0;
        pass = p;
    }
    // 字符串构造法。使用“pass”或者读取这个字符的Unicode值
    // 返回指定的Unicode字符代表 int价值。
    public OthelloAction(String s) {
        if (s.equals("pass")) {
            row = 0;
            column = 0;
            value = 0;
            pass = true;
        } else {
            // charAt() 方法可返回指定位置的字符。
            row = Character.getNumericValue(s.charAt(1));
            column = Character.getNumericValue(s.charAt(3));
            value = 0;
        }
    }
    // 一堆 value、column、row的get、set方法。
    /** Sets the estimated value of the move. */
    // 一个设置移动估值的方法
    public void setValue(int v) {
        value = v;
    }
    // 一个返回移动估值的方法
    /** Returns the estimated value of the move. */
    public int getValue() {
        return value;
    }

    /** Sets the column where the marker is to be placed. */
    public void setColumn(int c) {
        column = c;
    }

    /** Returns the column where the marker is to be placed. */
    public int getColumn() {
        return column;
    }

    /** Sets the row where the marker is to be placed. */
    public void setRow(int r) {
        row = r;
    }

    /** Returns the row where the marker is to be placed. */
    public int getRow() {
        return row;
    }

    /**
     * Sets the boolean that indicates whether this is a pass move. This should only
     * be true if there are no legal moves.
     */
    public void setPassMove(boolean b) {
        pass = b;
    }

    /**
     * Returns true if this is a pass move, indicating that the player has no legal
     * moves. Otherwise returns false.
     */
    // 一个用来判断是否下一步无路可走的方法。返回布尔值
    public boolean isPassMove() {
        return pass;
    }
    // 控制台输出的"show"方法
    public void print() {
        if (pass) {
            System.out.println("pass");
        } else {
            System.out.println("(" + row + "," + column + ")");
        }
    }

}
