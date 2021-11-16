import java.util.LinkedList;


public class DynamicEvaluator implements OthelloAlgorithm {
    public OthelloEvaluator evaluator;
    public int depth;
    public long timeLimit;
    long startTime;
    long endTime;

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit * 1000L;
    }

    public static boolean isFinish(OthelloPosition position) {
        String line = position.toString();
        String[] markers = line.split("");
        for (String marker : markers) {
            if (marker.equals("E")) {
                return false;
            }
        }
        return true;
    }


    public void Start(OthelloPosition position, DynamicEvaluator dynamicEvaluator) {
        while (!isFinish(position)) {
            OthelloAction bestAction = dynamicEvaluator.evaluate(position);
            try {
                OthelloPosition updatedPosition = position.makeMove(bestAction);
                updatedPosition.illustrate();
                position = updatedPosition;
            } catch (IllegalMoveException e) {
                e.printStackTrace();
            }
        }
    }

    public OthelloAction MaxValue(OthelloPosition position, double alpha, double beta, int depth) throws IllegalMoveException, TimeLimitException {

        if(System.currentTimeMillis() > endTime){
            throw new TimeLimitException();
        }
        if (depth == 0) {
            OthelloAction leafNode = new OthelloAction("pass");
            leafNode.setValue(this.evaluator.evaluate(position));
            return leafNode;
        }
        double value = Double.NEGATIVE_INFINITY;
        LinkedList<OthelloAction> othelloActions = position.getMoves();
        if (othelloActions.size() == 0) {
            return new OthelloAction("pass");
        }
        OthelloAction maxAction = new OthelloAction(0, 0);
        for (OthelloAction action : othelloActions) {
            OthelloPosition childNode = position.clone().makeMove(action);
            value = Math.max(value, MinValue(childNode, alpha, beta, depth - 1).getValue());
            alpha = Math.max(alpha, value);
            maxAction.setValue((int) value);
            maxAction.setRow(action.getRow());
            maxAction.setColumn(action.getColumn());

            if (alpha >= beta) {
                break;
            }
        }

        return maxAction;
    }

    public OthelloAction MinValue(OthelloPosition position, double alpha, double beta, int depth) throws IllegalMoveException, TimeLimitException {
        if(System.currentTimeMillis() > endTime){
            throw new TimeLimitException();
        }
        if (depth == 0) {
            OthelloAction leafNode = new OthelloAction("pass");
            leafNode.setValue(this.evaluator.evaluate(position));
            return leafNode;
        }
        double value = Double.POSITIVE_INFINITY;
        LinkedList<OthelloAction> othelloActions = position.getMoves();
        if (othelloActions.size() == 0) {
            return new OthelloAction("pass");
        }
        OthelloAction minAction = new OthelloAction(0, 0);
        for (OthelloAction action : othelloActions) {
            OthelloPosition childNode = position.clone().makeMove(action);
            value = Math.min(value, MaxValue(childNode, alpha, beta, depth - 1).getValue());
            beta = Math.min(beta, value);
            minAction.setValue((int) value);
            minAction.setRow(action.getRow());
            minAction.setColumn(action.getColumn());

            if (alpha >= beta) {
                break;
            }
        }
        return minAction;
    }

    public OthelloAction AlphaBeta(OthelloPosition position,int depth) throws IllegalMoveException {
        startTime = System.currentTimeMillis();
        endTime = startTime + timeLimit ;
        OthelloAction bestMove = new OthelloAction(0,0);
        for(int i = 1; i <= depth; i++){
            try {
                bestMove = MaxValue(position, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, i);
            } catch (TimeLimitException e) {
                break;
            }
        }
        return bestMove;
    }


    @Override
    public void setEvaluator(OthelloEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public OthelloAction evaluate(OthelloPosition position) {
        try {
            return AlphaBeta(position,depth);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
            System.exit(0);
            return new OthelloAction("pass");
        }
    }

    @Override
    public void setSearchDepth(int depth) {
        this.depth = depth + 1;
    }
}
