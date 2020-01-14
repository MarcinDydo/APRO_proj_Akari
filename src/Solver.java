public class Solver {
    private Akari akari;
    private int[][] map;

    Solver(Akari akari){
        this.akari = akari;
        this.map = new int[akari.sx][akari.sy];
        for(int i=0;i<akari.sx;i++){
            for(int j=0;j<akari.sy;j++){
                if(akari.buttons[i][j].state.getValue()<7 && akari.buttons[i][j].state.getValue()>-1) map[i][j]=akari.buttons[i][j].state.getValue();
            }
        }
    }

}
