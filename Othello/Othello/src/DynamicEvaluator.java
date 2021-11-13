import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class DynamicEvaluator implements OthelloAlgorithm{
    private OthelloEvaluator evaluator;
    public int depth;

    public boolean isFinish(OthelloPosition position){
        String line = position.toString();
        String[] markers = line.split("");
        for(String marker: markers){
            if(marker.equals("E")){
                return false;
            }
        }
        return true;
    }
    public void generateTree(OthelloPosition position, DynamicEvaluator dynamicEvaluator){
        while (!dynamicEvaluator.isFinish(position)) {
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

    @Override
    public void setEvaluator(OthelloEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public OthelloAction evaluate(OthelloPosition position){
        LinkedList<OthelloAction> othelloActions = position.getMoves();
        ArrayList<Integer> values = new ArrayList<>();
        for(OthelloAction othelloAction: othelloActions){
            try {
                OthelloPosition newOthelloPosition = position.makeMove(othelloAction);
                int value = evaluator.evaluate(newOthelloPosition);
                values.add(value);
            } catch (IllegalMoveException e) {
                e.printStackTrace();
            }
        }
        if(values.size()==0){
            return new OthelloAction("pass");
        }
        if(position.maxPlayer){
            int maxNum = Collections.max(values);
            int index = values.indexOf(maxNum);
            return othelloActions.get(index);
        }else{
            int minNum = Collections.min(values);
            int index = values.indexOf(minNum);
            return othelloActions.get(index);
        }

    }

    @Override
    public void setSearchDepth(int depth){
        this.depth = depth;
    }
}
