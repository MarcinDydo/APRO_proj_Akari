import java.util.Random;

public class Genrator {
    private final int[][] MAP; //cannot point to any other array
    /**
     * Generates map that is able to be saved
     * in a CSV file
     * @param size
     */
    public Genrator(int size){
        MAP = new int[size][size];
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
     * assigns random values depending on ratio
     * @param ratio
     * @return
     */
    private int assignRandom(int ratio) {
        Random rand = new Random();
        if (rand.nextInt(ratio) > 0) return 0;
        else return rand.nextInt(6) + 1;
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
        try{
            if(MAP[i-1][j] == 0) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        try{
            if(MAP[i][j-1] == 0) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        try{
            if(MAP[i+1][j] == 0) counter++;
        }catch (IndexOutOfBoundsException ignored){}
        try{
            if(MAP[i][j+1] == 0) counter++;
        }catch (IndexOutOfBoundsException ignored){}
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
            switch (temp) {
                case 0:
                    try{
                        if(random.nextInt(2)<1){
                            MAP[i-1][j] = 0;
                        }else MAP[i-1][j] = 6;
                    }catch (IndexOutOfBoundsException ignored){}
                    break;
                case 1:
                    try{
                        if(random.nextInt(2)<1){
                        MAP[i][j-1] = 0;
                        }else MAP[i-1][j] = 6;
                    }catch (IndexOutOfBoundsException ignored){}
                    break;
                case 2:
                    try{
                        if(random.nextInt(2)<1){
                        MAP[i+1][j] = 0;
                        }else MAP[i-1][j] = 6;
                    }catch (IndexOutOfBoundsException ignored){}
                    break;
                case 3:
                    try{
                        if(random.nextInt(2)<1){
                        MAP[i][j+1] = 0;
                        }else MAP[i-1][j] = 6;
                    }catch (IndexOutOfBoundsException ignored){}
                    break;
            }
        }
    }
    public static void main(String[] args) {
        Genrator test = new Genrator(10);
        test.makeMap(4);
        for (int[] i: test.MAP
             ) {
            for (int j:i
                 ) {
                System.out.print(j+",");
            }
            System.out.println();
        }
    }
}
