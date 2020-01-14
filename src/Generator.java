import java.util.Random;

public class Generator {
    private final int[][] MAP; //cannot point to any other array

    public int[][] getMAP(int ratio) {
        return MAP;
    }

    /**
     * Generates map that is able to be saved
     * in a CSV file
     *
     * @param x, y
     */
    public Generator(int x, int y) {
        MAP = new int[x][y];
         makeMap(3);
    }

    /**
     * Fills the map with random structure
     * and checks if no rules has been broken
     * need to add: solver check
     */
    private void makeMap(int ratio) {
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[0].length; j++) {
                MAP[i][j] = assignRandom(ratio); //ratio blacks:whites - 1:n
            }
        }
        while(!checkForBlank()){
            solveCheck();//solver method to check and finish the map
        }
    }
    /**
     * assigns random values depending on ratios
     * @return
     */
    private int assignRandom(int ratio) {
        Random rand = new Random();
        if (rand.nextInt(ratio) > 0) return 0;
        else return 6;
    }

    private boolean checkForBlank(){
        for (int[] i:MAP
        ) {
            for (int j:i
            ) {
                if (j==0)return false;
            }
        }
        return true;
    }
    /**
     * solver made only for generator
     */
    private void solveCheck(){
        Random rand = new Random();
        int x = rand.nextInt(MAP.length);
        int y = rand.nextInt(MAP[0].length);
        if(MAP[x][y] == 0){
                    MAP[x][y]=8;
                    Expansion.expand(MAP,7 ,x,y);

        }
    }

    public static void main(String[] args) {
        Akari a=new Akari(1,1);
        Generator gen = new Generator(10,10);
        a.swap(gen.MAP);
        for (int[] i:gen.MAP
             ) {
            for (int j:i
                 ) {
                System.out.print(j + ",");
            }
            System.out.println();
        }
    }
}