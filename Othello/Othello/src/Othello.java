public class Othello {
    public static void main(String[] args) {
        OthelloPosition othelloPosition = new OthelloPosition();
        othelloPosition.initialize();
        othelloPosition.illustrate();

        StaticEvaluator staticEvaluator = new StaticEvaluator();
        DynamicEvaluator dynamicEvaluator = new DynamicEvaluator();
        dynamicEvaluator.setEvaluator(staticEvaluator);

        dynamicEvaluator.generateTree(othelloPosition,dynamicEvaluator);








    }
}