import java.util.LinkedList;

public class Othello {
    public static void main(String[] args) {
        // String s = "WO111111E1111111E11111111111111111111111111111111111111111111111O";
        // System.out.println(s.length());
        OthelloPosition othelloPosition = new OthelloPosition();
        othelloPosition.initialize();
        othelloPosition.illustrate();

        OthelloPosition newOthelloPosition = new OthelloPosition();

        LinkedList<OthelloAction> othelloActions = othelloPosition.getMoves();
        System.out.println(othelloActions.size());
        for (OthelloAction othelloAction : othelloActions) {
            try {
                newOthelloPosition = othelloPosition;
                newOthelloPosition = othelloPosition.makeMove(othelloAction);
            } catch (IllegalMoveException e) {
                System.out.println("The error is:" + e);
                System.out.print("The wrong row&column is:" );
                e.getAction().print();

            }
            System.out.println("_____________________________________________________");
            newOthelloPosition.illustrate();
        }

/*        OthelloAction othelloAction = new OthelloAction(3,5);
        try {
            newOthelloPosition = othelloPosition;
            newOthelloPosition = othelloPosition.makeMove(othelloAction);
        } catch (IllegalMoveException e) {
            System.out.println("The error is:" + e.toString());
            System.out.print("The wrong row&column is:" );
            e.getAction().print();
        }
        System.out.println("_____________________________________________________");
        newOthelloPosition.illustrate();*/
    }
}