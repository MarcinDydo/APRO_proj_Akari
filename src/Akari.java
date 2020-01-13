import javax.swing.*;
import java.awt.*;

/**
 * Class to represent Akari game
*/
class Akari extends JFrame {
    int sx;
    int sy;
    AkariButton[][] buttons;
    static Panel p = new Panel();

    /**
     * Constructor for akari game.
     * @param x Akari height.
     * @param y Akari width
     */
    Akari(int x, int y) {
        super("Akari");
        this.sx=x;
        this.sy=y;
        buttons=new AkariButton[x][y];
        Generator generator = new Generator(x,y);
        int[][] map = generator.getMAP(4);
        setSize(45*x,45*y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                buttons[i][j] = new AkariButton(this, i,j, State.toState(map[i][j]));
                p.add(buttons[i][j]);
            }
        }
        add(p);
        this.setJMenuBar(new AkariMenuBar(this));
        setVisible(true);

    }

    /**
     * Constructor for loaded maps.
     * @param x Akari height.
     * @param y Akari width.
     * @param map Map of buttons.
     */
    Akari(int x, int y,int[][]map) {
        super("Akari");
        this.sx=x;
        this.sy=y;
        buttons=new AkariButton[x][y];
        setSize(45*x,45*y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                buttons[i][j] = new AkariButton(this, i,j, State.toState(map[i][j]));
                p.add(buttons[i][j]);
            }
        }
        add(p);
        this.setJMenuBar(new AkariMenuBar(this));
        setVisible(true);

    }

    /**
     * Method for deleting akari window and contents.
     */
    void wipe(){
        for(int i=0;i<sx;i++){
            for(int j=0;j<sy;j++){
                p.remove(buttons[i][j]);
                buttons[0][0] = null;
            }
        }
        setVisible(false);
        dispose();
    }
}
