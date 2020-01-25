package akari.maps;

import akari.view.Akari;
import akari.view.Difficulty;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Solver {

    private Akari akari;
    private int[][] map;
    private int x, y;
    private Stack<int[][]> stack;

    public Solver() {
        this.stack = new Stack<>();
    }

    public static void main(String[] args) throws IOException {
        //Solver s = new Solver(new Akari(Difficulty.Easy));
        Solver s = new Solver();
        s.read("test_rozwiazany"+".csv");
        System.out.println(s.write());
        s.solve();
        System.out.println("-----------------");
        System.out.println(s.write());
        Akari a = new Akari(Difficulty.Easy);
//        s.akari.swap(s.map);
        a.swap(s.map);
    }



// ****** TUTAJ W KOMENTARZU PRZY PARAM jest // ABY NIE WYWALO BLEDU JA NA RAZIE ******
    /**
     * this constructor is to get a two dimensional table: int [][] map
     * //@param akari class form where i get map[][]
     */
//    akari.maps.Solver(akari.view.Akari akari){
//        this.akari = akari;
//        this.map = new int[akari.sx][akari.sy];
//        for(int i=0;i<akari.sx;i++){
//            for(int j=0;j<akari.sy;j++){
//                if(akari.buttons[i][j].state.getValue()<9 && akari.buttons[i][j].state.getValue()>-1) map[i][j]=akari.buttons[i][j].state.getValue();
//            }
//        }
//    }

    public void solve() {
        adding_LIT_next_to_0(); //działa
        placing_bulbs_next_to_4(); //działa
        searching_3_on_the_walls(); //działą
        searching_2_in_corners();  //działa ale jest niepotrzebna bo jest searching_field...

        while(all_black_equal_number_of_bulbs()==1) {
            searching_field_with_equals_numer_of_free_space();  // działą
            placing_x_if_black_field_has_equalas_number_of_bulbs();
        }
        back_tracking();

    }

    public void test_stack(){
        System.out.println("##########################");
        System.out.println(write(przypisanie_map_na_temp()));
        map[0][0]=9;
        stack.push(przypisanie_map_na_temp());
        map[0][0]=0;
        System.out.println(write(przypisanie_map_na_temp()));
        map=stack.pop();
        System.out.println(write(przypisanie_map_na_temp()));
        System.out.println("#################");
    }


    public void back_tracking(){
        stack.push(przypisanie_map_na_temp());
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 0 && no_collision(i, k) && black_fields_in_4_sides_equals_number_of_bulbs(i,k)) {
                    Expansion.expand(map, 7, i, k);
                    stack.push(przypisanie_map_na_temp());
                    if(searching_field(9) && !searching_field(0)){
                        stack.pop();
                        map[i][k]=9;
                    }
                    back_tracking();
                }
            }
        }
    }

    public boolean searching_field(int value){
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if(map[i][k]==value) return true;
            }
        }
        return false;
    }

    public int[][] przypisanie_map_na_temp(){
        int[][] temp = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                temp[i][k] = map[i][k];
            }
        }
        return temp;
    }

    /**
     * sprawdza czy w 4 strony jet odpowiednia ilosc zarowek
     * @param i  parametr x dla zaroweki
     * @param k paramtery y dla zarowki
     * @return gdy jest za duzo sasiadow to zwraca false, gdy est za malo zarowek to stawia true
     */
    public boolean  black_fields_in_4_sides_equals_number_of_bulbs(int i, int k){
        i = i -1;
        if( chceck_if_i_and_k_is_correct(i,k) && (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3)){
                    /*
                    gdy jest za duzo sasiadow to zwraca false
                    gdy est za malo zarowek to stawia true
                     */
            int valueOfField = map[i][k];
            int counter = 0;
            if (i - 1 >= 0 &&  map[i - 1][k] == 8) {
                counter++;
            }
            if (k + 1 < y && map[i][k + 1] == 8) {
                counter++;
            }
            if (i + 1 < x && map[i + 1][k] == 8 ) {

                counter++;
            }
            if (k - 1 >= 0 &&  map[i][k - 1] == 8) {
                counter++;
            }
            if (valueOfField > counter) {
                return  true;
            }
            if(valueOfField < counter){
                return false;
            }
            if(valueOfField == counter) System.out.println("rowna ilosc");
        }
        i = i+1; k = k+1;
        if( chceck_if_i_and_k_is_correct(i,k) && (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3)){
                    /*
                    gdy jest za duzo sasiadow to zwraca false
                    gdy est za malo zarowek to stawia true
                     */
            int valueOfField = map[i][k];
            int counter = 0;
            if (i - 1 >= 0 &&  map[i - 1][k] == 8) {
                counter++;
            }
            if (k + 1 < y && map[i][k + 1] == 8) {
                counter++;
            }
            if (i + 1 < x && map[i + 1][k] == 8 ) {

                counter++;
            }
            if (k - 1 >= 0 &&  map[i][k - 1] == 8) {
                counter++;
            }
            if (valueOfField > counter) {
                return  true;

            }
            if(valueOfField < counter){
                return false;
            }
            if(valueOfField == counter) System.out.println("rowna ilosc");
        }
        i = i+1; k = k-1;
        if( chceck_if_i_and_k_is_correct(i,k) && (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3)){
                    /*
                    gdy jest za duzo sasiadow to zwraca false
                    gdy est za malo zarowek to stawia true
                     */
            int valueOfField = map[i][k];
            int counter = 0;
            if (i - 1 >= 0 &&  map[i - 1][k] == 8) {
                counter++;
            }
            if (k + 1 < y && map[i][k + 1] == 8) {
                counter++;
            }
            if (i + 1 < x && map[i + 1][k] == 8 ) {

                counter++;
            }
            if (k - 1 >= 0 &&  map[i][k - 1] == 8) {
                counter++;
            }
            if (valueOfField > counter) {
                return  true;

            }
            if(valueOfField < counter){
                return false;
            }
            if(valueOfField == counter) System.out.println("rowna ilosc");
        }
        i = i-1; k = k-1;
        if( chceck_if_i_and_k_is_correct(i,k) && (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3)){
                    /*
                    gdy jest za duzo sasiadow to zwraca false
                    gdy est za malo zarowek to stawia true
                     */
            int valueOfField = map[i][k];
            int counter = 0;
            if (i - 1 >= 0 &&  map[i - 1][k] == 8) {
                counter++;
            }
            if (k + 1 < y && map[i][k + 1] == 8) {
                counter++;
            }
            if (i + 1 < x && map[i + 1][k] == 8 ) {

                counter++;
            }
            if (k - 1 >= 0 &&  map[i][k - 1] == 8) {
                counter++;
            }
            if (valueOfField > counter) {
                return  true;

            }
            if(valueOfField < counter){
                return false;
            }
            if(valueOfField == counter) System.out.println("rowna ilosc");
        }
        return true;
    }

    /**
     *
     * @param i x cordinate
     * @param k y cordinate
     * @return return true if i and k isn`t out of band
     */
    public boolean chceck_if_i_and_k_is_correct(int i, int k){
        return i >= 0 && i < x && k >= 0 && k < y;
    }

    public void  placing_x_if_black_field_has_equalas_number_of_bulbs(){
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if(map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3){
                    int valueOfField = map[i][k];
                    int counter = 0;
                    if (i - 1 >= 0 &&  map[i - 1][k] == 8) {
                        counter++;
                    }
                    if (k + 1 < y && map[i][k + 1] == 8) {
                        counter++;
                    }
                    if (i + 1 < x && map[i + 1][k] == 8 ) {

                        counter++;
                    }
                    if (k - 1 >= 0 &&  map[i][k - 1] == 8) {
                        counter++;
                    }
                    if(valueOfField == counter){
                        if (chceck_if_i_and_k_is_correct(i-1,k) && map[i - 1][k] == 0) {
                            map[i-1][k]=9;
                        }
                        if (chceck_if_i_and_k_is_correct(i,k+1) && map[i][k + 1] == 0) {
                            map[i][k+1]=9;
                        }
                        if (chceck_if_i_and_k_is_correct(i+1,k) && map[i + 1][k] == 0) {
                            map[i+1][k]=9;
                        }
                        if (chceck_if_i_and_k_is_correct(i,k-1) && map[i][k - 1] == 0) {
                            map[i][k-1]=9;
                        }
                    }
                }
            }
        }
    }

    /**
     * szukam sobie  czarnego pola, od 1 do 3 i sprawdzam czy wokół niego jest opcja wstawienia żąrówki
     * @return gdy jest za duzo sasiadow to zwraca false, gdy est za malo zarowek to stawia true
     */
    public int  all_black_equal_number_of_bulbs(){
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if(map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3){
                    int valueOfField = map[i][k];
                    int counter = 0;
                    if (i - 1 >= 0 &&  map[i - 1][k] == 8) {
                        counter++;
                    }
                    if (k + 1 < y && map[i][k + 1] == 8) {
                        counter++;
                    }
                    if (i + 1 < x && map[i + 1][k] == 8 ) {

                        counter++;
                    }
                    if (k - 1 >= 0 &&  map[i][k - 1] == 8) {
                        counter++;
                    }
                    if (valueOfField > counter) {
                        return  1;
                    }
                    if(valueOfField < counter){
                        return 0;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * @return return true if the map is solved and false if the map isnt solved
     */
    public boolean is_solved() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 0 || map[i][k]==9) {
                    return false;
                }
                if (map[i][k] == 8) {
                    if (!no_collision(i, k)) return false;
                }
                /**
                 * dodać jeszcze warunek spr czy dla wsyztskich blookow z numerami zgadza sie liczba zarowek
                 */
            }
        }
        return true;
    }

    /**
     * @param i x cordinate
     * @param k y  cordinate
     * @return return true if the map[i][k] is a black , and false in other case
     */
    public boolean is_black_block(int i, int k) {
        return map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3 || map[i][k] == 4 || map[i][k] == 5 || map[i][k] == 6;
    }

    /**
     * @param i x cordintate
     * @param k y cordinate
     * @return return true if there is no colision and we can place there a bulb
     */
    public boolean no_collision(int i, int k) {
        //sprawdzenie w prawą stornę
        for (int j = 1; j < y - k; j++) {  //jak j< k....
            if (k + j < y && is_black_block(i, k + j)) {
                break;
            }
            if (k + j < y && map[i][k + j] == 8) {
                return false;
            }
        }
        //sprawdzenie dla lewą stronę
        for (int j = 1; j < y - k; j++) {
            if (k - j > 0 && is_black_block(i, k - j)) {
                break;
            }
            if (k - j > 0 && map[i][k - j] == 8) {
                return false;
            }
        }
        //sprawdzenie do góry
        for (int j = 1; j < x - i; j++) {
            if (i - j > 0 && is_black_block(i - j, k)) {
                break;
            }
            if (i - j > 0 && map[i - j][k] == 8) {
                return false;
            }
        }
        //sprawdzenie do dołu
        for (int j = 1; j < x - i; j++) {
            if (i + j < x && is_black_block(i + j, k)) {
                break;
            }
            if (i + j < x && map[i + j][k] == 8) {
                return false;
            }
        }
        return true;
    }

    /**
     * searching block 3 on the walls, and then placeing the bulbs next to it and expanding the bulb
     */
    public void searching_3_on_the_walls() {
        for (int i = 0; i < x; i++) {
            /**
             * sprawdza najpierw lewą ścianę
             */
            if (map[i][0] == 3) {
                if (i + 1 < x) Expansion.expand(map, 7, i + 1, 0);
                if (i - 1 > 0) Expansion.expand(map, 7, i - 1, 0);
                Expansion.expand(map, 7, i, 1);
            }
            /**
             * sprawdza prawą ścianę
             */
            if (map[i][y - 1] == 3) {
                if (i - 1 > 0) Expansion.expand(map, 7, i - 1, y - 1);
                if (i + 1 < x) Expansion.expand(map, 7, i + 1, y - 1);
                Expansion.expand(map, 7, i, y - 2);
            }
        }

        for (int i = 0; i < y; i++) {
            /**
             * sprawdzanie górna ściana
             */
            if (map[0][i] == 3) {
                if (i - 1 > 0) Expansion.expand(map, 7, 0, i - 1);
                if (i + 1 < y) Expansion.expand(map, 7, 0, i + 1);
                Expansion.expand(map, 7, 1, i);
            }
            /**
             * sprawdzana dolna ściana
             */
            if (map[x - 1][i] == 3) {
                if (i - 1 > 0) Expansion.expand(map, 7, x - 1, i - 1);
                if (i + 1 < y) Expansion.expand(map, 7, x - 1, i + 1);
                Expansion.expand(map, 7, x - 2, i);
            }
        }
    }

    public void searching_field_with_equals_numer_of_free_space() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3) {
                    class Field {
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
                    if (i - 1 >= 0 && (map[i - 1][k] == 0 || map[i - 1][k] == 8) && map[i - 1][k] != 7) {
                        stack.push(new Field(i - 1, k));
                        counter++;
                    }
                    if (k + 1 < y && (map[i][k + 1] == 0 || map[i][k + 1] == 8) && map[i][k + 1] != 7) {
                        stack.push(new Field(i, k + 1));
                        counter++;
                    }
                    if (i + 1 < x && (map[i + 1][k] == 0 || map[i + 1][k] == 8) && map[i + 1][k] != 7) {
                        stack.push(new Field(i + 1, k));
                        counter++;
                    }
                    if (k - 1 >= 0 && (map[i][k - 1] == 0 || map[i][k - 1] == 8) && map[i][k - 1] != 7) {
                        stack.push(new Field(i, k - 1));
                        counter++;
                    }
                    if (valueOfField == counter) {
                        while (!stack.empty()) {
                            Field field = stack.pop();
                            Expansion.expand(map, 7, field.xCord, field.yCord);
                        }
                        //searching_field_with_equals_numer_of_free_space();
                    }

                }
            }
        }
    }

    /**
     * method search if in the corner there is a 2, and then set a bulb next to 2.
     */
    public void searching_2_in_corners() {
        int tempY = y;
        if (map[0][0] == 2) {
            Expansion.expand(map, 7, 1, 0);
            Expansion.expand(map, 7, 0, 1);
        }
        if (map[0][y - 1] == 2) {
            Expansion.expand(map, 7, 1, y - 1);
            Expansion.expand(map, 7, 0, y - 2);
        }
        if (map[x - 1][0] == 2) {
            Expansion.expand(map, 7, x - 2, 0);
            Expansion.expand(map, 7, x - 1, 1);
        }
        if (map[x - 1][y - 1] == 2) {
            Expansion.expand(map, 7, x - 2, y - 1);
            Expansion.expand(map, 7, x - 1, y - 2);
        }
    }

    /**
     * no co robi, stawia zarowki am gszie jest 4
     */
    public void placing_bulbs_next_to_4() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 4) {
                    if (chceck_if_i_and_k_is_correct(i-1,k) && map[i - 1][k] == 0) {
                        Expansion.expand(map, 7, i - 1, k);
                    }
                    if (chceck_if_i_and_k_is_correct(i,k+1) && map[i][k + 1] == 0) {
                        Expansion.expand(map, 7, i, k + 1);
                    }
                    if (chceck_if_i_and_k_is_correct(i+1,k) && map[i + 1][k] == 0) {
                        Expansion.expand(map, 7, i + 1, k);
                    }
                    if (chceck_if_i_and_k_is_correct(i,k-1) && map[i][k - 1] == 0) {
                        Expansion.expand(map, 7, i, k - 1);
                    }
                }
            }
        }
    }

    /**
     * finding 0 and placing LIT-s next to 0
     */
    public void adding_LIT_next_to_0() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 5) {
                    if (chceck_if_i_and_k_is_correct(i,k+1) && map[i][k + 1] == 0) {
                        map[i][k + 1] = 9;
                    }
                    if (chceck_if_i_and_k_is_correct(i+1,k) && map[i + 1][k] == 0) {
                        map[i + 1][k] = 9;
                    }
                    if (chceck_if_i_and_k_is_correct(i,k-1) && map[i][k - 1] == 0) {
                        map[i][k - 1] = 9;
                    }
                    if (chceck_if_i_and_k_is_correct(i-1,k) && map[i - 1][k] == 0) {
                        map[i - 1][k] = 9;
                    }
                }
            }
        }
    }

    private void read(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        String separator = ";";
        x = Integer.parseInt(line.split(separator)[0]);
        y = Integer.parseInt(line.split(separator)[1]);
        int j = 0;
        int[][] map = new int[x][y];
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.split(separator).length; i++) {
                map[j][i] = Integer.parseInt(line.split(separator)[i]);
            }
            j++;
        }
        br.close();
        this.map = map;
    }

    public String write() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                sb.append(map[i][k] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public String write(int [][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                sb.append(map[i][k] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}