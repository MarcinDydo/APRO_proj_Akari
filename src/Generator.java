import java.util.Random;

public class Generator {
    private final int[][] MAP; //cannot point to any other array

    public int[][] getMAP(int ratio) {
        makeMap(ratio);
        return MAP;
    }

    /**
     * Generates map that is able to be saved
     * in a CSV file
     * @param x, y
     */
    public Generator(int x, int y){
        MAP = new int[x][y];
    }
    /**
     * Fills the map with random structure
     * and checks if no rules has been broken
     * need to add: solver check
     */
    private void makeMap(int ratio){
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP.length; j++) {
                MAP[i][j] = assignRandom(ratio); //ratio blacks:whites - 1:n
            }
        }
        correctMap();
    }
    /**
     * assigns random values depending on ratios
     * @param ratioBW ratio black to white
     * @param ratioEN ratio empty to number
     * @return
     */
    private int assignRandom(int ratioBW, int ratioEN) {
        Random rand = new Random();
        if (rand.nextInt(ratioBW) > 0) return 0;
        else return 6;
    }
