/**
 * This interface defines the mandatory methods for game playing algorithms,
 * i.e., algorithms that take an <code>OthelloAlgorithm</code> and return a
 * suggested move for the player who has the move.
 * 
 * The algorithm only defines the search method. The heuristic evaluation of
 * positions is given by an <code>OthelloEvaluator</code> which is given to the
 * algorithm.
 * 
 * @author Henrik Bj&ouml;rklund
 */

public interface OthelloAlgorithm {

	/**
	 * Sets the <code>OthelloEvaluator</code> the algorithm is to use for
	 * heuristic evaluation.
	 */
	// 一个设置评估器的启发式的方法，需要重写
	public void setEvaluator(OthelloEvaluator evaluator);

	/**
	 * Returns the <code>OthelloAction</code> the algorithm considers to be the
	 * best move.
	 */
	// 一个evaluate方法，对当前的OthelloPosition类的对象评估，返回一个OthelloAction类的值。
	// 说白了就是对位置进行评估，返回一个最好的行动。
	public OthelloAction evaluate(OthelloPosition position);

	/** Sets the maximum search depth of the algorithm. */
	// 设置最大搜索深度
	public void setSearchDepth(int depth);
}