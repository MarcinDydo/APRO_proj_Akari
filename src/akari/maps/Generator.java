package akari.maps;

import java.util.Random;

public class Generator {
    private final int[][] MAP; //cannot point to any other array
    public int[][] solution; //cannot point to any other array
    private Random rand = new Random(); //useful random object :)

    public int[][] getMAP(int ratio) {
        makeMap(ratio);
        return MAP;
    }

    /**
     * Generates map that is able to be saved
     * in a CSV file
     * @param x, y
     */
    public Generator(int x, int y) {
        MAP = new int[x][y];
        solution = new int[x][y];
    }

    /**
     * Fills the map with random structure
     * and solver-checks it
     */
    private void makeMap(int ratio) {
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[0].length; j++) {
                MAP[i][j] = assignRandom(ratio); //ratio blacks:whites - 1:n
            }
        }
        while(!checkForDark()){
            solveCheck();//solver method to check and finish the map
        }
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[0].length; j++) {
                if(MAP[i][j]==6)
                MAP[i][j] = assignNumber(8,i,j); //seek for bulbs
            }
        }
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[0].length; j++) {
                solution[i][j] = MAP[i][j];
            }
        }
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[0].length; j++) {
                if(MAP[i][j]==7 || MAP[i][j]==8) MAP[i][j] = 0; //wipes out Bulb and Lit tiles
            }
        }
    }
    /**
     * assigns random values depending on ratios
     * @return Dark or Black
     */
    private int assignRandom(int ratio) {
        if (rand.nextInt(ratio) > 0) return 0;
        else return 6;
    }

    /**
     * checks if any Dark space left
     * @return true if not
     */
    private boolean checkForDark(){
        for (int[] i:MAP) {
            for (int j:i) {
                if (j==0)return false;
            }
        }
        return true;
    }
    /**
     * solver made only for generator
     */
    private void solveCheck(){
        int x = rand.nextInt(MAP.length);
        int y = rand.nextInt(MAP[0].length);
        if(MAP[x][y] == 0){
            Expansion.expand(MAP,7 ,x,y);
        }
    }
    /**
     * counts neighbours of value 8 (bulbs)
     * near field [i,j]
     * @param i x parameter
     * @param j y parameter
     * @param tile tile to check (default bulb)
     * @return number of neighbours therefore value of tile
     */
    private int assignNumber(int tile, int i, int j){
        int counter=0;
        try{
            if(MAP[i-1][j] == tile) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        try{
            if(MAP[i][j-1] == tile) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        try{
            if(MAP[i+1][j] == tile) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        try{
            if(MAP[i][j+1] == tile) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        if(rand.nextInt(2)>0){
            if(counter>0)return counter;
            else return 5;
        }else return 6;
    }
}