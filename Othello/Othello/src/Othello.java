public class Othello {
    public static void main(String[] args) {
        if(args.length!=2){
            throw new IllegalArgumentException("There should be two arguments, a position string and a time limit in second");
        }
        if(args[0].length() > 65){
            throw new IllegalArgumentException("The position string is too long, it should be 65 long.");
        }
        if(args[0].length() < 65){
            throw new IllegalArgumentException("The position string is too short, it should be 65 long.");
        }

        OthelloPosition othelloPosition = new OthelloPosition(args[0]);
        int timeLimit = Integer.parseInt(args[1]);


        StaticEvaluator staticEvaluator = new StaticEvaluator();
        DynamicEvaluator dynamicEvaluator = new DynamicEvaluator();
        dynamicEvaluator.setEvaluator(staticEvaluator);
        dynamicEvaluator.setSearchDepth(7);
        dynamicEvaluator.setTimeLimit(timeLimit);

        dynamicEvaluator.Start(othelloPosition,dynamicEvaluator);
        // dynamicEvaluator.AIvsAI(othelloPosition,dynamicEvaluator);


    }
}