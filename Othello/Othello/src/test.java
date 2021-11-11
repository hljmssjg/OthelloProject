public class test {
    public static void main(String[] args) {
        int[] east = {0, 1};
        int[] west = {0, -1};
        int[] north = {-1, 0};
        int[] south = {1, 0};
        int[] northWest = {-1, -1};
        int[] northEast = {-1, 1};
        int[] southEast = {1, 1};
        int[] southWest = {1, -1};
        int[][] directions = {{0, 1},west,north,south,northWest,northEast,southEast,southWest};
        System.out.println(directions[1][1]);
    }
}
