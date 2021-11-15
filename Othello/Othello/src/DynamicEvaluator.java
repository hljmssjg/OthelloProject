import java.util.LinkedList;


public class DynamicEvaluator implements OthelloAlgorithm{
    public static OthelloEvaluator evaluator;
    public int depth;

    public static boolean isFinish(OthelloPosition position){
        String line = position.toString();
        String[] markers = line.split("");
        for(String marker: markers){
            if(marker.equals("E")){
                return false;
            }
        }
        return true;
    }
    public void AIvsAI(OthelloPosition position, DynamicEvaluator dynamicEvaluator){
        while (!isFinish(position)) {
//        for(int i = 0; i < 3; i++){
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
    public static OthelloAction MaxValue(OthelloPosition position, double alpha, double beta) throws IllegalMoveException {
        if (isFinish(position)){
            OthelloAction leafNode = new OthelloAction("pass");
            leafNode.setValue(evaluator.evaluate(position));
            return leafNode;
        }
        double value = Double.NEGATIVE_INFINITY;
        LinkedList<OthelloAction> othelloActions = position.getMoves();
        if (othelloActions.size() == 0){
            return new OthelloAction("pass");
        }
        OthelloAction maxAction = new OthelloAction(0,0);
        for(OthelloAction action: othelloActions){
            OthelloPosition childNode = position.clone().makeMove(action);
            value = Math.max(value, MinValue(childNode,alpha,beta).getValue());
            alpha = Math.max(alpha,value);
            maxAction.setValue((int)value);
            maxAction.setRow(action.getRow());
            maxAction.setColumn(action.getColumn());

            if (alpha >= beta){
                break;
            }
        }
        return maxAction;
    }


    public static OthelloAction MinValue(OthelloPosition position, double alpha, double beta) throws IllegalMoveException {
        if (isFinish(position)){
            OthelloAction leafNode = new OthelloAction("pass");
            leafNode.setValue(evaluator.evaluate(position));
            return leafNode;
        }
        double value = Double.POSITIVE_INFINITY;
        LinkedList<OthelloAction> othelloActions = position.getMoves();
        if (othelloActions.size() == 0){
            return new OthelloAction("pass");
        }
        OthelloAction minAction = new OthelloAction(0,0);
        for(OthelloAction action: othelloActions){
            OthelloPosition childNode = position.clone().makeMove(action);
            value = Math.min(value, MinValue(childNode,alpha,beta).getValue());
            alpha = Math.min(alpha,value);
            minAction.setValue((int)value);
            minAction.setRow(action.getRow());
            minAction.setColumn(action.getColumn());

            if (alpha >= beta){
                break;
            }
        }
        return minAction;
    }

    public static OthelloAction AlphaBeta(OthelloPosition position) throws IllegalMoveException {
        return MaxValue(position,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    }

    @Override
    public void setEvaluator(OthelloEvaluator evaluator) {
        DynamicEvaluator.evaluator = evaluator;
    }

    @Override
    public OthelloAction evaluate(OthelloPosition position){
        try {
            return AlphaBeta(position);
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
            return new OthelloAction("pass");
        }
    }

    @Override
    public void setSearchDepth(int depth){
        this.depth = depth;
    }
}
