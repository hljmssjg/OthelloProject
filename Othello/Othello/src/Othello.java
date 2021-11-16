public class Othello {
    public static void main(String[] args) {
        OthelloPosition othelloPosition = new OthelloPosition();
        othelloPosition.initialize();
        othelloPosition.illustrate();

        StaticEvaluator staticEvaluator = new StaticEvaluator();
        DynamicEvaluator dynamicEvaluator = new DynamicEvaluator();
        dynamicEvaluator.setEvaluator(staticEvaluator);
        dynamicEvaluator.setSearchDepth(10);
        dynamicEvaluator.setTimeLimit(1);

        dynamicEvaluator.Start(othelloPosition,dynamicEvaluator);









    }
}