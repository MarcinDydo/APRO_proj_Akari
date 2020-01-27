package akari.maps;

import akari.view.Akari;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Solver {

    private Akari akari;
    public int[][] map;
    private int x, y;
    private Stack<int[][]> stack;
    private Field[][] fields;

    public Solver() {
        this.stack = new Stack<>();
    }

    public Solver(Akari akari) {
        this.akari = akari;
        this.map = new int[akari.sx][akari.sy];
        for(int i=0;i<akari.sx;i++){
            for(int j=0;j<akari.sy;j++){
                if(akari.buttons[i][j].state.getValue()<9 && akari.buttons[i][j].state.getValue()>-1) map[i][j]=akari.buttons[i][j].state.getValue();
            }
        }
        solve();
    }


    private void solve() {
        set_up_map_of_objects();
        set_coross_next_to_0();
        set_bulbs_next_to_4();
        set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(0, 0);
        set_cross_if_black_block_value_equals_number_of_bulbs();
        place_all_first_poossible_bulbs_on_stack();
        back_tracking();
        removing_lit_and_cross();
    }

    private void removing_lit_and_cross(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++) {
                if( map[i][k] == 7 || map[i][k] == 9) map[i][k]=0;
            }
        }
    }

    /**
     * Method place on stack all possible first moves in order to let work a backtracking method
     */
    private void place_all_first_poossible_bulbs_on_stack(){
        for(int i = 0; i < x; i++){
            for(int k = 0; k < y; k++){
                if(map[i][k]==0){
                    int[][] temp = rewriting_map();
                    Expansion.expand(temp,7,i,k);
                    stack.push(temp);
                }
            }
        }
    }

    private void back_tracking(){
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

    /**
     * method set cross on dark fields next to black field if the black field have next to it equals number of bulbs
     */
    private void set_cross_if_black_block_value_equals_number_of_bulbs() {
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
    private boolean if_value_of_fields_equals_quantity_of_bulbs_next_to_it(int i, int k) {
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
    private void set_up_map_of_objects() {
        this.fields = new Field[x][y];
        for (int i = 0; i < x; i++) {
            for (int k = 0; k < y; k++) fields[i][k] = new Field(i, k);
        }
    }

    /**
     * recursion taht set bulbs if the number of dark space equals a quantity od value of the black field
     */
    private void set_bulbs_next_to_black_field_if_value_equals_quantity_of_white_space(int i, int k) {
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
    private void set_bulbs_next_to_4() {
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
    private void set_coross_next_to_0() {
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

    /**
     * check if the map is solved
     * @return
     */
    private boolean is_solved(int[][] map){
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
     * Method check if it is possible to set bulb in field with cords i,k. If on the expansion of the bulb appear bulb
     * return false, or if on the expansion of the light appear black block, method check others requirement
     * @param i x cordintate
     * @param k y cordinate
     * @return return true if there is no colision and we can place there a bulb
     */
    private boolean no_collision(int i, int k) {
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
     * Rewtiring map in order to not place on stack reference but a int[][]
     * @return return map
     */
    private int[][] rewriting_map() {
        int[][] map_temp = new int[x][y];
        for (int i = 0; i < x; i++) {
            if (y >= 0) System.arraycopy(map[i], 0, map_temp[i], 0, y);
        }
        return map_temp;
    }

    /**
     * Method check whether the field is black or not.
     * @param i x cordinate
     * @param k y cordinate
     * @return true if the block is black and false other case
     */
    private boolean is_black_block(int i, int k) {
        return map[i][k] == 1 || map[i][k] == 2 || map[i][k] == 3 || map[i][k] == 4 || map[i][k] == 5 || map[i][k] == 6;
    }

    /**
     * Method check if the i and k is not greater or lower nad a x and y.
     * @param i x cordinate
     * @param k y cordinate
     * @return return true if i and k isn`t out of band
     */
    private boolean check_the_bounds(int i, int k) {
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
}