class Expansion {
    static int[][] MAP;
    public static void expand(int[][] map, int state, int x, int y) {
        MAP = map;
        if(state==7)MAP[x][y]=8;
        expandDown(state,x,y);
        expandUp(state,x,y);
        expandRight(state,x,y);
        expandLeft(state,x,y);
    }
    /**
     * Recursive method to light up or darken tiles in the down direction.
     * @param state default is 7
     * @param x coordinate in map
     * @param y coordinate in map
     */
    private static void expandDown(int state, int x, int y) {
        if (x + 1 < MAP.length && (MAP[x+1][y]==0|| MAP[x+1][y]==7 )) {
            MAP[x+1][y] = state;
            expandDown(state,x+1,y);
        }
    }

    /**
     * Recursive method to light up or darken tiles in the up direction.
     * @param state default is 7
     * @param x coordinate in map
     * @param y coordinate in map
     */
    private static void expandUp(int state, int x, int y){
        if (x -1 > -1 && (MAP[x-1][y]==0 || MAP[x-1][y]==7)) {
            MAP[x-1][y]= state;
            expandUp(state, x-1,y);
        }
    }

    /**
     * Recursive method to light up or darken tiles in the right direction.
     * @param state default is 7
     * @param x coordinate in map
     * @param y coordinate in map
     */
    private static void expandRight(int state, int x, int y) {
        if (y+1 <  MAP[0].length  && (MAP[x][y+1]==0|| MAP[x][y+1]==7)) {
            MAP[x][y+1]= state;
            expandRight(state,x,y+1);
        }
    }

    /**
     * Recursive method to light up or darken tiles in the left direction.
     * @param state default is 7
     * @param x coordinate in map
     * @param y coordinate in map
     */
    private static void expandLeft(int state, int x, int y) {
        if (y-1 > -1 && (MAP[x][y-1]==0|| MAP[x][y-1]==7 )) {
            MAP[x][y-1]= state;
            expandLeft(state,x,y-1);
        }
    }

}
