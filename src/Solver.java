import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solver {
    private Akari akari;
    private int[][] map;
    private int x,y;
    /*
   0-Dark
   1- Black_1
   2- Black_2
   3- Black_3
   4- Black_4
   5- Black_0
   6- Black
   7- Lit
   8- Bulb
   */
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.read("test_z4.csv");
        System.out.println(s.write());
        s.solve();
        System.out.println("-----------------");
        System.out.println(s.write());
    }
    /*
    Przepisanie buttonów na tablice int-ów na której opiera się solver


        Solver(Akari akari){
            this.akari = akari;
            this.map = new int[akari.sx][akari.sy];
            for(int i=0;i<akari.sx;i++){
                for(int j=0;j<akari.sy;j++){
                    if(akari.buttons[i][j].state.getValue()<9 && akari.buttons[i][j].state.getValue()>-1) map[i][j]=akari.buttons[i][j].state.getValue();
                }
            }
        }

     */
    public void solve(){
        addingXnextTo0();
        placingBulbsNextTo4();
        searching_3_on_the_walls();
        placingBulbsNextTo3();

        /*
        znajdowanie najpier w rogach dwójek
        znajdowanie na śicanach 3
        i dopiero pozniej suzkanie ogolnie 3 i zaznaczanie
     */
    }


    public void searching_3_on_the_walls(){
        for(int i =0 ; i < x; i++){

            /**
             * sprawdza najjpierw lewą śianę
             */
            if(map[i][0]==3){
//                if(i-1 > 0 && map[i-1][0]==0) {
//                    map[i-1][0]=8;
//                    for(int q = 0 ; q < x - i -2; q++){   //x zmienić
//                        map[i-1-q][0] = 7;
//                    }
//                }
//                if(i+1< x && map[i+1][0]==0){
//                    map[i+1][0]=8;
//                    for(int q = 0; q < x -i-2; q++){   // czy x na mpewno q<x
//                        map[i+1+q][0]=7;
//                    }
//                }
//                if(map[i][1]==0){
//                    map[i][1]=0;
//                    for(int q = 0; q <y -1; q++){  // czy na pewno
//                        map[i][1]=7;
//                    }
//                }
            }
            /**
             * sprawdza prawą ścianę
             */
            if(map[i][y-1]==3){
//                if(i-1 > 0 && map[i-1][y]==0) {
//                    for(int q = 0 ; q < x ; q++){   //x zmienić
//                        map[i-1-q][y] = 7;
//                    }
//                }
//                if(i+1< x && map[i+1][y]==0){
//                    for(int q = 0; q < x; q++){   // czy x na mpewno q<x
//                        map[i+1+q][y]=7;
//                    }
//                }
//                if(map[i][y-1]==0){
//                    for(int q = 0; q <y -1; q++){  // czy na pewno
//                        map[i][y-1]=7;
//                    }
//                }
            }
        }

        for(int i = 0; i < y; i++){
            /**
             * sprawdzanie górna ściana
             */
            if(map[0][i]==3){

            }
            /**
             * sprawdzana dolna ściana
             */
            if (map[x-1][i] == 0){

            }
        }

    }

    public void placingBulbsNextTo3(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++) {
                if(map[i][k]==3){


                }
            }
        }
    }

    public void placingBulbsNextTo4(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++) {
                if(map[i][k]==4) {
                    if (i - 1 > 0 && map[i - 1][k] == 0){
                        map[i-1][k]=8;
                        for (int q = 1; q < x - i - 2; q++) {
                            if (map[i - 1 - q][k] == 0) {
                                map[i - 1 - q][k] = 7;
                            } else break;
                        }
                    }
                    if( k+1 < y && map[i][k+1] == 0){
                        map[i][k+1]=8;
                        for (int q = 1; q < y - i - 2; q++) {  // sprawzic czy te -2 to dobrze tutaj pasuje
                            if (map[i][k+1+q] == 0) {
                                map[i][k+1+q] = 7;
                            } else break;
                        }
                    }
                    if( i+1 < x && map[i+1][k]==0){
                        map[i+1][k]=8;
                        for (int q = 1; q < x - i - 2; q++) {
                            if (map[i+1+q][k] == 0) {
                                map[i+1+q][k] = 7;
                            } else break;
                        }
                    }
                    if( k-1 > 0 && map[i][k-1]==0){
                        map[i][k-1]=8;
                        for (int q = 1; q < y - i - 2; q++) {
                            if (map[i][k-1-q] == 0) {
                                map[i][k-1-q] = 7;
                            } else break;
                        }
                    }
                }
            }
        }
    }

    /**
     * finding 0 and placing LIT-s next to 0
     */
    public void addingXnextTo0(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++){
                if(map[i][k]==5){
                    if(k+1 < y && map[i][k+1]==0) {
                        map[i][k + 1] = 7;
                    }
                    if(i+1 < x && map[i+1][k]==0) {
                        map[i+1][k]=7;
                    }
                    if(k-1 > 0 && map[i][k-1]==0) {
                        map[i][k - 1] = 7;
                    }
                    if(i-1 > 0 && map[i-1][k]==0) {
                        map[i - 1][k] = 7;
                    }
                }
            }
        }
    }

    private void read(String fileName) throws IOException {
        BufferedReader br= new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        String separator = ";";
        x = Integer.parseInt(line.split(separator)[0]);
        y = Integer.parseInt(line.split(separator)[1]);
        int j =0;
        int map[][] = new int[x][y];
        while ((line = br.readLine()) != null) {
            for(int i = 0; i<line.split(separator).length; i++){
                map[j][i]=Integer.parseInt(line.split(separator)[i]);
            }
            j++;
        }
        br.close();
        this.map=map;
    }

    public String write(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++){
                sb.append(map[i][k]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}