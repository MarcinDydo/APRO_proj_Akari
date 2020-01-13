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
                MAP[i][j] = assignRandom(ratio,3); //ratio blacks:whites - 1:n, empty:number - 3:1
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
        else {
            if(rand.nextInt(ratioEN)>0) return 6;
            else return rand.nextInt(5) + 1; // ratio empty:number - n:1
        }
    }
    /**
     * corrects map so that it no longer
     * breaks specific rules for different fields
     * checks if no field has less 0's around itself
     * than its value
     */
    private void correctMap(){
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP.length; j++) {
                if(MAP[i][j]==6 || MAP[i][j]==0 || MAP[i][j]==5) continue;
                if(MAP[i][j]==4 && i==0 || j==0 || i==MAP.length-1 || j==MAP.length-1) MAP[i][j]--; //rule for 4
                if( MAP[i][j]==3 && (i==0 && j==0) || (i==MAP.length-1 && j==0) || (i==0 && j==MAP.length-1)
                        || (i==MAP.length-1 && j==MAP.length-1)) MAP[i][j]--; //rule for 3
                while(MAP[i][j]!=0 && MAP[i][j]!=6 && MAP[i][j]!=5 && countNeighbours(i,j) < MAP[i][j]){
                    randomAction(i,j);
                }
                if(MAP[i][j]<0) MAP[i][j]=0;//ensures no negative numbers
            }
        }
    }

    /**
     * counts neighbours of value 0
     * near field [i,j]
     * @param i
     * @param j
     * @return
     */
    private int countNeighbours(int i, int j){
        int counter=0;
        if(i>0 && MAP[i-1][j] == 0) counter++;
        if(j>0 && MAP[i][j-1] == 0) counter++;
        if(i<MAP.length-1 && MAP[i+1][j] == 0) counter++;
        if(j<MAP.length-1 && MAP[i][j+1] == 0) counter++;
        return counter;
    }

    /**
     * method either decreases value of field
     * or sets random field near to 0
     * @param i
     * @param j
     */
    private void randomAction(int i, int j){
        Random random = new Random();
        if(random.nextInt(2)<1){ // 50/50 chance
            MAP[i][j]--;
        }else {
            int temp = random.nextInt(4);
            try {
                switch (temp) {
                    case 0:
                        MAP[i - 1][j] = 0;
                        break;
                    case 1:
                        MAP[i][j - 1] = 0;
                        break;
                    case 2:
                        MAP[i + 1][j] = 0;
                        break;
                    case 3:
                        MAP[i][j + 1] = 0;
                        break;
                }
            }catch (IndexOutOfBoundsException ignored){}
        }
    }
}
