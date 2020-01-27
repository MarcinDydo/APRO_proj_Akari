package akari.maps;

import akari.view.Akari;
import akari.view.Difficulty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Solver_v2 {

    private int[][] map;
    private int x, y;
    private Stack<int[][]> stack;
    private Stack<int[]> tabOfMoves;
    private Field[][] fields;
    private ArrayList<int[][]> arrayList;

    public Solver_v2() {
        this.stack = new Stack<>();
        this.tabOfMoves = new Stack<>();
        this.arrayList = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        Solver_v2 s = new Solver_v2();
        s.read("maps/test10"+ ".csv");  //nazwa
        System.out.println(s.write());
        s.solve();
        System.out.println("-----------------");
        System.out.println(s.write());
        Akari a = new Akari(Difficulty.Easy);
        a.swap(s.map);
    }

    public void solve() {
        set_up_map_of_objects();
        set_coross_next_to_0();
        set_bulbs_next_to_4();
        set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(0, 0);
        set_cross_if_black_block_value_equals_number_of_bulbs();
        add_all_on_stack();
        back_tracking();

    }

    /**
     * metohod that add all possible dirtst moves on stack
     */
    public void add_all_on_stack(){
       // boolean flag = true;
        //stack.push(rewriting_map());
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++){

//                int[] rowNbr = new int[]{-1, 0, 1, 0};
//                int[] colNbr = new int[]{0, 1, 0, -1};
//                for (int j = 0; j < 4; j++) {
//                    if (check_the_bounds(i + rowNbr[j], k + colNbr[j]) && map[i + rowNbr[j]][k + colNbr[j]] == 5) {
//                        flag = false; break;
//                    }
//                }


                if(map[i][k]==0){
                    int[][] temp = rewriting_map();
                    Expansion.expand(temp,7,i,k);
                    stack.push(temp);
                }
            }
        }
    }

    public void back_tracking(){
        while (!stack.empty()){
            map = stack.pop();
            for(int i = 0; i < x; i++) {
                for (int k = 0; k < y; k++) {

                    if (map[i][k] == 0) {
                        if (check_the_bounds(i, k) && no_collision(i, k) && if_value_of_fields_equals_quantity_of_bulbs_next_to_it(i, k)) {
                            int[][] temp = rewriting_map();
                            Expansion.expand(temp,7,i,k);
                            stack.push(temp);
                        }
                    }
                }
            }
            if(is_solved(map)){
                return;
            }
        }

    }

//    /**
//     * algoyrthm of BACK_TRACKINGGGGGGGG KING
//     */
//    public void back_tracking() {
//        if (!is_solved()) {
//            if(searching_field(0)) {  //może jeszcze czy jest 9
//                int tab[] = find_white_field(rewriting_map()); int i = tab[0], k = tab[1];
//                if (check_the_bounds(i, k) && (map[i][k] == 0) && no_collision(i, k) && if_value_of_fields_equals_quantity_of_bulbs_next_to_it(i, k)) {
//                    Expansion.expand(map, 7, i, k);
//                    set_cross_if_black_block_value_equals_number_of_bulbs();
//                    stack.push(rewriting_map());
//                    tabOfMoves.push(new int[] {i,k});  //tablica ktora pretrzymuje w którym miejscu żarówka się zmieniła //int temp[] = new int[] {i,k};
//                    back_tracking();
//                }
//                else {
//                    map[i][k] = 9;
//                    back_tracking();
//                }
//            }
//            else{
//                if(stack.empty() || tabOfMoves.empty()) return;  //tutaj jest błąd
//                int temp[] = new int[2];
//                while(searching_field(0)) {
//                    stack.pop();
//                    map = stack.pop();
//                    temp = tabOfMoves.pop();
//                }
//                map[temp[0]][temp[1]]=9;
//                back_tracking();
//            }
//        }
//    }

    /**
     * maethod that check if all the black fields have equals numbe of ulbs next to it
     * @return if the field have not equal value and number of bulbs return false, in other case return true
     */
    public boolean check_all_blacK_field_if_have_equals_number_of_bulbs() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3) {
                    int counter = 0;
                    int[] rowNbr = new int[]{-1, 0, 1, 0};
                    int[] colNbr = new int[]{0, 1, 0, -1};
                    for (int j = 0; j < 4; j++) {
                        if (check_the_bounds(i + rowNbr[j], k + colNbr[j]) && map[i + rowNbr[j]][k + colNbr[j]] == 8) {
                            counter++;
                        }
                    }
                    if(counter != map[i][k]) return false;
                }
            }
        }
        return true;
    }

    /**
     * method set cross on dark fields next to black field if the black field have next to it equals number of bulbs
     */
    public void set_cross_if_black_block_value_equals_number_of_bulbs() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3) {
                    int counter = 0;
                    int[] rowNbr = new int[]{-1, 0, 1, 0};
                    int[] colNbr = new int[]{0, 1, 0, -1};
                    for (int j = 0; j < 4; j++) {
                        if (check_the_bounds(i + rowNbr[j], k + colNbr[j]) && map[i + rowNbr[j]][k + colNbr[j]] == 8) {
                            counter++;
                        }
                    }
                    for (int j = 0; j < 4; j++) {
                        if (counter == map[i][k]) {
                            if (check_the_bounds(i + rowNbr[j], k + colNbr[j]) && map[i + rowNbr[j]][k + colNbr[j]] == 0)
                                map[i + rowNbr[j]][k + colNbr[j]] = 9;
                        }
                    }
                }
            }
        }
    }


    /**
     * method check if it is possible to set bulb in map[i][k], method check if there is a black fields next to it and
     * if there is a black field check if that field have equals number of bulbs
     * @param i x cordinate
     * @param k y cordiate
     * @return return true if it is posssible to set bulb and false in other case
     */
    public boolean if_value_of_fields_equals_quantity_of_bulbs_next_to_it(int i, int k) {
        int[] rowNbr = new int[]{-1, 0, 1, 0};
        int[] colNbr = new int[]{0, 1, 0, -1};
        int counter = 0;
        for (int j = 0; j < 4; j++) {
            int temp_i = i + rowNbr[j];
            int temp_k = k + colNbr[j];
            if (check_the_bounds(temp_i,temp_k) && (map[temp_i][temp_k] == 1 || map[temp_i][temp_k] == 2 || map[temp_i][temp_k] == 3)) {
                for (int p = 0; p < 4; p++) {
                    int temp_i_2 = i + rowNbr[j] + rowNbr[p];
                    int temp_k_2 = k + colNbr[j] + colNbr[p];
                    if (check_the_bounds(temp_i_2, temp_k_2) && map[temp_i_2][temp_k_2] == 8) {
                        counter++;
                    }
                }
                if (counter == map[i + rowNbr[j]][k + colNbr[j]]) return false;
            }
        }
        return true;
    }

    /**
     * create a map of objects
     */
    public void set_up_map_of_objects() {
        this.fields = new Field[x][y];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) fields[i][k] = new Field(i, k);
        }
    }

    /**
     * recursion taht set bulbs if the number of dark space equals a quantity od value of the black field
     */
    public void set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(int i, int k) {
        if (!(map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3) || fields[i][k].isVisited()) {
            if (k + 1 == y) {
                if (i + 1 != x) set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(i + 1, 0);
            } else set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(i, k + 1);
        } else {
            Stack<Field> stack = new Stack();
            int counter = 0;
            int[] rowNbr = new int[]{-1, 0, 1, 0};
            int[] colNbr = new int[]{0, 1, 0, -1};
            for (int j = 0; j < 4; j++) {
                if (check_the_bounds(i + rowNbr[j], k + colNbr[j]) &&
                        (map[i + rowNbr[j]][k + colNbr[j]] == 0 || map[i + rowNbr[j]][k + colNbr[j]] == 8)) {
                    stack.push(new Field(i + rowNbr[j], k + colNbr[j]));
                    counter++;
                }
            }
            if (counter == map[i][k]) {
                fields[i][k].setVisited(true);
                while (!stack.empty()) {
                    Field field = stack.pop();
                    Expansion.expand(map, 7, field.getxCord(), field.getyCord());
                }
                set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(0, 0);
            }
            //recursion
            if (k + 1 == y) {
                if (i + 1 != x) set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(i + 1, 0);
            } else set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(i, k + 1);

        }
    }

    /**
     * method that set bulbs next to parameterized black field with 4 number
     */
    public void set_bulbs_next_to_4() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 4) {
                    if (check_the_bounds(i - 1, k) && map[i - 1][k] == 0) Expansion.expand(map, 7, i - 1, k);
                    if (check_the_bounds(i, k + 1) && map[i][k + 1] == 0) Expansion.expand(map, 7, i, k + 1);
                    if (check_the_bounds(i + 1, k) && map[i + 1][k] == 0) Expansion.expand(map, 7, i + 1, k);
                    if (check_the_bounds(i, k - 1) && map[i][k - 1] == 0) Expansion.expand(map, 7, i, k - 1);
                }
            }
        }
    }

    /**
     * method that set cross next to 0 fields in order to forbid place bulb there
     */
    public void set_coross_next_to_0() {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == 5) {
                    if (check_the_bounds(i, k + 1) && map[i][k + 1] == 0) map[i][k + 1] = 9;
                    if (check_the_bounds(i + 1, k) && map[i + 1][k] == 0) map[i + 1][k] = 9;
                    if (check_the_bounds(i, k - 1) && map[i][k - 1] == 0) map[i][k - 1] = 9;
                    if (check_the_bounds(i - 1, k) && map[i - 1][k] == 0) map[i - 1][k] = 9;
                }
            }
        }
    }

    /**
     * find the white field and set the tempX and tempY for that field
     */
    public int[] find_white_field(int[][] map){
        boolean flag = false;
        int[] temp = new int[2];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if(map[i][k]==0){
                    temp = new int[]{i, k};
                    flag =true;
                }
                if (flag) break;
            }
            if (flag) break;
        }
        return temp;
    }


    /**
     * check if the map is solved
     * @return
     */
    public boolean is_solved(int [][] map){
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if(map[i][k]==0 || map[i][k]==9) return false;
                if (map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3) {
                    int counter = 0;
                    int[] rowNbr = new int[]{-1, 0, 1, 0};
                    int[] colNbr = new int[]{0, 1, 0, -1};
                    for (int j = 0; j < 4; j++) {
                        if (check_the_bounds(i + rowNbr[j], k + colNbr[j]) && map[i + rowNbr[j]][k + colNbr[j]] == 8) {
                            counter++;
                        }
                    }
                    if(counter != map[i][k]) return false;
                }
            }
        }
        return true;
    }

    /**
     * @param i x cordintate
     * @param k y cordinate
     * @return return true if there is no colision and we can place there a bulb
     */
    public boolean no_collision(int i, int k) {
        for (int j = 1; j < y - k; j++) {
            if (check_the_bounds(i, k + j) && is_black_block(i, k + j)) break;
            if (check_the_bounds(i, k + j) && map[i][k + j] == 8) return false;
        }
        for (int j = 1; j < y - k; j++) {
            if (check_the_bounds(i, k - j) && is_black_block(i, k - j)) break;
            if (check_the_bounds(i, k - j) && map[i][k - j] == 8) return false;
        }
        for (int j = 1; j < x - i; j++) {
            if (check_the_bounds(i - j, k) && is_black_block(i - j, k)) break;
            if (check_the_bounds(i - j, k) && map[i - j][k] == 8) return false;
        }
        for (int j = 1; j < x - i; j++) {
            if (check_the_bounds(i + j, k) && is_black_block(i + j, k)) break;
            if (check_the_bounds(i + j, k) && map[i + j][k] == 8) return false;
        }
        return true;
    }

    /**
     * @param value value of the field
     * @return return true if that value is on the map
     */
    public boolean searching_field(int value) {
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                if (map[i][k] == value) return true;
            }
        }
        return false;
    }

    /**
     * rewtiring map in order to not place on stack reference but a int[][]
     * @return return map
     */
    public int[][] rewriting_map() {
        int[][] map_ = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) {
                map_[i][k] = map[i][k];
            }
        }
        return map_;
    }

    /**
     * @param i x cordinate
     * @param k y cordinate
     * @return true if the block is black and false other case
     */
    public boolean is_black_block(int i, int k) {
        return map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3 || map[i][k] == 4 || map[i][k] == 5 || map[i][k] == 6;
    }

    /**
     * @param i x cordinate
     * @param k y cordinate
     * @return return true if i and k isn`t out of band
     */
    public boolean check_the_bounds(int i, int k) {
        return i >= 0 && i < x && k >= 0 && k < y;
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
}
