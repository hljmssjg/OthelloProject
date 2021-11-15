public class Othello {
    public static void main(String[] args) {
        // String s = "WO111111E1111111E1111111E111111111111111111111111111111111111111E";;
        OthelloPosition othelloPosition = new OthelloPosition();
        othelloPosition.initialize();
        othelloPosition.illustrate();

        StaticEvaluator staticEvaluator = new StaticEvaluator();
        DynamicEvaluator dynamicEvaluator = new DynamicEvaluator();
        dynamicEvaluator.setEvaluator(staticEvaluator);

        dynamicEvaluator.AIvsAI(othelloPosition,dynamicEvaluator);








    }
}