import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private Akari akari;
    private int[][] map;
    private int x,y;
    private Punkt[][] punkty;

    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        s.read("dadura_test.csv");
        //s.read("test_rozwiazany.csv");
        System.out.println(s.write());
        s.solve();
        System.out.println("-----------------");
        System.out.println(s.write());
    }

    // ****** TUTAJ W KOMENTARZU PRZY PARAM jest // ABY NIE WYWALO BLEDU JA NA RAZIE ******
    /**
     * this constructor is to get a two dimensional table: int [][] map
     * //@param akari class form where i get map[][]
     */
//    Solver(Akari akari){
//        this.akari = akari;
//        this.map = new int[akari.sx][akari.sy];
//        for(int i=0;i<akari.sx;i++){
//            for(int j=0;j<akari.sy;j++){
//                if(akari.buttons[i][j].state.getValue()<9 && akari.buttons[i][j].state.getValue()>-1) map[i][j]=akari.buttons[i][j].state.getValue();
//            }
//        }
//    }

    public void solve(){
        adding_LIT_next_to_0(); //działa
        placing_bulbs_next_to_4(); //działa
        searching_3_on_the_walls(); //działą
        //searching_2_in_corners();  //działa ale jest niepotrzebna bo jest searching_field...
        searching_field_with_equals_numer_of_free_space();  // działą
        //back_tracking();   //do poprwany tylko to
        brute_force();
    }

    class Punkt{

        private int xCord;
        private int yCord;
        private int value;

        public void setValue(int value) {
            this.value = value;
        }

        private boolean visted;

        public boolean isVisted() {
            return visted;
        }

        public void setVisted(boolean visted) {
            this.visted = visted;
        }

        public Punkt(int xCord, int yCord, int value, boolean visited) {
            this.xCord = xCord;
            this.yCord = yCord;
            this.value=value;
            this.visted=visited;
        }
    }

    public void back_tracking(){
        //ArrayList<Punkt> punkts = new ArrayList<>();
        Punkt[][] punkts = new Punkt[x][y];
        for(int i = 0; i < x ; i++) {
            for (int k = 0; k < y; k++) {
                //if (map[i][k] == 0) punkts.add(new Punkt(i, k, map[i][k], false));
                //punkts.add(new Punkt(i,k,map[i][k],false));  //obiekty dla całej mapy
                punkts[i][k] = new Punkt(i,k,map[i][k],false);
            }
        }
        this.punkty=punkts;
        solving(punkts);
    }

    //trzeba ogarnac backtracing, jak na razie to jest tylko jakiś pomysł, któy nie działą
    public void solving(Punkt[][] fields){

//        punkty[1][1].yCord;
//        punkty[1][1].xCord;
//        punkty[1][1].visted;
//        punkty[1][1].value;

//        while(!is_solved()){
//            int tempX, tempY;
//            for(int i = 0; i < x ; i++) {
//                for (int k = 0; k < y; k++) {
//                    if(punkty[i][k].value==0 && no_collision(i,k) && !punkty[i][k].isVisted()){
//                        map[i][k]=8;
//                        punkty[i][k].setValue(8);
//                        solving(fields);
//                    }
//                    if(punkty[i][k].value==8 && !no_collision(i,k) && !punkty[i][k].isVisted()){
//                        punkty[i][k].setVisted(true);
//                    }
//                }
//            }
//        }
    }

    public void brute_force(){
        int rand = (int) (Math.random()*10);
        ArrayList<Punkt> punkts = new ArrayList<>();
        for(int i = 0; i < x ; i++){
            for(int k = 0; k < y; k++) {
                if(map[i][k]==0){
                    punkts.add(new Punkt(i,k,map[i][k],false));
                }
            }
        }
        while(!is_solved()){
            rand = (int) (Math.random()*2);
            if(rand > punkts.size()){
                brute_force();
            }
            Punkt punkt = punkts.get(rand);
            if(no_collision(punkt.xCord,punkt.yCord)){
                map[punkt.xCord][punkt.yCord] = 8;
            }
            for(int i = 0; i < x ; i++){
                for(int k = 0; k < y; k++) {
                    if(map[i][k]==0) {
                        brute_force();
                    }
                }
            }

        }
    }

    /**
     *
     * @return return true if the map is solved and false if the map isnt solved
     */
    public boolean is_solved(){
        for(int i = 0; i < x ; i++){
            for(int k = 0; k < y; k++){
                if(map[i][k]==0) {
                    return false;
                }
                if(map[i][k]==8) {
                    if(!no_collision(i,k)) return false;
                }
            }
        }
        return true;
    }

    /**
     * @param i x cordinate
     * @param k y  cordinate
     * @return return true if the map[i][k] is a black , and false in other case
     */
    public boolean is_black_block(int i, int k){
        if( map[i][k]==1 || map[i][k]==2 || map[i][k]==3 || map[i][k]==4 || map[i][k]==5 || map[i][k]==6){
            return true;
        }
        else return false;
    }

    /* z tą metodą może być jakiś problem. w if dodałem kolejny wartunek że np k+j < y ,
    wszescniej bez tego niby działało lae teraz nie sprawdzałem czy działa dalej
    */
    /**
     * @param i x cordintate
     * @param k y cordinate
     * @return return true if there is no colision and we can place there a bulb
     */
    public boolean no_collision(int i, int k){
        //sprawdzenie w prawą stornę
        for(int j = 1; j < y - k; j++){  //jak j< k....
            if(k+j<y && is_black_block(i,k+j)){
                break;
            }
            if(k+j<y && map[i][k+j]==8){
                return false;
            }
        }
        //sprawdzenie dla lewą stronę
        for(int j = 1; j < y - k ; j++){
            if(k-j > 0 &&is_black_block(i,k-j)){
                break;
            }
            if(k-j > 0 && map[i][k-j]==8){
                return false;
            }
        }
        //sprawdzenie do góry
        for(int j = 1; j < x - i; j++){
            if(i-j > 0 && is_black_block(i-j,k)){
                break;
            }
            if(i-j > 0 &&map[i-j][k]==8){
                return false;
            }
        }
        //sprawdzenie do dołu
        for(int j = 1; j < x - i; j++){
            if(i+j < x && is_black_block(i+j,k)){
                break;
            }
            if(i+j < x && map[i+j][k]==8){
                return false;
            }
        }
        return true;
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
                if(i+1<x) Expansion.expand(map,7,i+1,0);
                if(i-1>0) Expansion.expand(map,7,i-1,0);
                Expansion.expand(map,7,i,1);
            }
            /**
             * sprawdza prawą ścianę
             */
            if(map[i][y-1]==3){
                if(i-1 > 0)Expansion.expand(map,7,i-1,y-1);
                if(i+1 < x) Expansion.expand(map,7,i+1,y-1);
                Expansion.expand(map,7,i,y-2);
            }
        }

        for(int i = 0; i < y; i++){
            /**
             * sprawdzanie górna ściana
             */
            if(map[0][i]==3){
                if(i-1 > 0) Expansion.expand(map,7,0,i-1);
                if(i+1 < y) Expansion.expand(map,7,0,i+1);
                Expansion.expand(map,7,1,i);
            }
            /**
             * sprawdzana dolna ściana
             */
            if (map[x-1][i]==3){
                if(i-1 > 0) Expansion.expand(map,7,x-1,i-1);
                if(i+1 < y) Expansion.expand(map,7,x-1,i+1);
                Expansion.expand(map,7,x-2,i);
            }
        }
    }

    public void searching_field_with_equals_numer_of_free_space(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++) {
                if(map[i][k]==1 || map[i][k]==2 ||map[i][k]==3) {
                    class Field{
                        public int getxCord() {
                            return xCord;
                        }
                        public int getyCord() {
                            return yCord;
                        }
                        private int xCord;
                        private int yCord;
                        public Field(int xCord, int yCord) {
                            this.xCord = xCord;
                            this.yCord = yCord;
                        }
                    }
                    Stack<Field> stack = new Stack();
                    int valueOfField = map[i][k];
                    int counter = 0;
                    if (i - 1 >= 0 && (map[i - 1][k] == 0 || map[i-1][k]==8) && map[i - 1][k] !=7 ) {
                        stack.push(new Field(i - 1, k));
                        counter++;
                    }
                    if (k + 1 < y && (map[i][k + 1] == 0 || map[i][k+1]==8) && map[i][k+1] != 7) {
                        stack.push(new Field(i,k+1));
                        counter++;
                    }
                    if (i + 1 < x && (map[i + 1][k] == 0 || map[i+1][k]==8) && map[i+1][k] != 7) {
                        stack.push(new Field(i+1,k));
                        counter++;
                    }
                    if (k - 1 >= 0 && (map[i][k - 1] == 0 || map[i][k-1]==8) && map[i][k-1] != 7) {
                        stack.push(new Field(i,k-1));
                        counter++;
                    }
                    if (valueOfField==counter) {
                        while(!stack.empty()){
                            Field field = stack.pop();
                            Expansion.expand(map,7,field.xCord,field.yCord);
                        }
                    }
                }
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
     * no co robi, stawia zarowki am gszie jest 4
     */
    public void placing_bulbs_next_to_4(){
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