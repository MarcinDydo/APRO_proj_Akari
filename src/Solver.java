import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solver {
    private Akari akari;
    private int[][] map;
    private int x,y;

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.read("dadura_test.csv");
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
        adding_LIT_next_to_0();
        placingBulbsNextTo4();
        searching_3_on_the_walls();
        searching_2_in_corners();
        placingBulbsNextTo3();
    }

    /**
     * searching block 3 on the walls, and then placeing the bulbs next to it and expanding the bulb
     */
    public void searching_3_on_the_walls(){
        for(int i =0 ; i < x; i++){
            /**
             * sprawdza najpierw lewą ścianę
             */
            if(map[i][0]==3){
                map[i][0]=8;
                Expansion.expand(map,7,i,0);
            }
            /**
             * sprawdza prawą ścianę
             */
            if(map[i][y-1]==3){
                map[i][y-1]=8;
                Expansion.expand(map,7,i,y-1);
            }
        }

        for(int i = 0; i < y; i++){
            /**
             * sprawdzanie górna ściana
             */
            if(map[0][i]==3){
                map[0][i]=8;
                Expansion.expand(map,7,0,i);
            }
            /**
             * sprawdzana dolna ściana
             */
            if (map[x-1][i]==3){
                map[x-1][i]=8;
                Expansion.expand(map,7,x-1,i);
            }
        }
    }

    public void placingBulbsNextTo3(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++) {

            }
        }
    }

    /**
     * method search if in the corner there is a 2, and then set a bulb next to 2.
     */
    public void searching_2_in_corners(){
        int tempY = y;
        if(map[0][0]==2) {
            Expansion.expand(map,7,1,0);
            Expansion.expand(map,7,0,1);
        }
        if(map[0][y-1]==2) {
            Expansion.expand(map,7,1,y-1);
            Expansion.expand(map,7,0,y-2);
        }
        if(map[x-1][0]==2) {
            Expansion.expand(map,7,x-2,0);
            Expansion.expand(map,7,x-1,1);
        }
        if(map[x-1][y-1]==2) {
            Expansion.expand(map,7,x-2,y-1);
            Expansion.expand(map,7,x-1,y-2);
        }
    }

    /**
     *
     */
    public void placingBulbsNextTo4(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++) {
                if(map[i][k]==4) {
                    if (i - 1 > 0 && map[i - 1][k] == 0){
                        Expansion.expand(map,7,i-1,k);
                    }
                    if( k+1 < y && map[i][k+1] == 0){
                        Expansion.expand(map,7,i,k+1);
                    }
                    if( i+1 < x && map[i+1][k]==0){
                        Expansion.expand(map,7,i+1,k);
                    }
                    if( k-1 > 0 && map[i][k-1]==0){
                        Expansion.expand(map,7,i,k-1);
                    }
                }
            }
        }
    }

    /**
     * finding 0 and placing LIT-s next to 0
     */
    public void adding_LIT_next_to_0(){
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