import javax.swing.*;
import java.awt.*;

/**
 * Class to represent Akari game.
*/
class Akari extends JFrame {
    int sx;
    int sy;
    AkariButton[][] buttons;
    static Panel p = new Panel();

    /**
     * Constructor for akari game.
     * @param x Akari height.
     * @param y Akari width.
     */
    Akari(int x, int y) {
        super("Akari");
        this.sx=x;
        this.sy=y;
        buttons=new AkariButton[x][y];
        Generator generator = new Generator(x,y);
        int[][] map = generator.getMAP(4);
        setSize(40*x,40*y+40);
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
     * Method for swaping akari tiles.
     */
    void swap(int[][] map){
        setVisible(false);
        remove(p);
        sx=map.length;
        sy=map[0].length;
        p = new Panel();
        setSize(40*map.length,40*map[0].length+40);
        p.setLayout(new GridLayout(map.length,map[0].length));
        p.setPreferredSize(new Dimension(45*map.length,45*map[0].length));
        buttons= new AkariButton[map.length][map[0].length];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                buttons[i][j] = new AkariButton(this, i,j, State.toState(map[i][j]));
                p.add(buttons[i][j]);
            }
        }
        repaint();
        add(p);
        setVisible(true);
    }
}
