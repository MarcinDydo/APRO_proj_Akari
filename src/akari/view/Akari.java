package akari.view;

import akari.maps.Generator;

import javax.swing.*;
import java.awt.*;

/**
 * Class to represent Akari game.
*/
public class Akari extends JFrame {
    public Score score;
    public int[][] solution;
    public int sx;
    public int sy;
     Difficulty difficulty;
    public AkariButton[][] buttons;
     static Panel p = new Panel();static int ratio = 3;
    /**
     * Constructor for akari game.
     * @param diff - level of difficulty
     */
    public Akari(Difficulty diff) {
        super("Akari");
        this.difficulty=diff;
        this.sx=difficulty.getSize();
        this.sy=difficulty.getSize();
        buttons=new AkariButton[sx][sy];
        Generator generator = new Generator(sx,sy);
        int[][] map = generator.getMAP(15);
        solution = generator.solution;
        setSize(40*sx,40*sy+40);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(sx,sy));
        for(int i=0;i<sx;i++){
            for(int j=0;j<sy;j++){
                buttons[i][j] = new AkariButton(this, i,j, State.toState(map[i][j]));
                p.add(buttons[i][j]);
            }
        }
        add(p);
        AkariMenuBar a = new AkariMenuBar(this);
        this.setJMenuBar(a);
        score = new Score();
        a.add(score);
        setVisible(true);

    }


    /**
     * Method for swaping akari tiles.
     */
    public void swap(int[][] map){
        solution = map;
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
        AkariMenuBar a = new AkariMenuBar(this);
        this.setJMenuBar(a);
        score = new Score();
        score.score = 0;
        a.add(score);
        buttons[0][0].reloadLight();
        setVisible(true);
    }

    /**
     * Swaping methods for maps with solutions
     *
     * @param map map to be swaped.
     * @param sol solution of the swaped map.
     */
    public void swapWS(int[][] map, int[][] sol) {
        solution = sol;
        setVisible(false);
        remove(p);
        sx = map.length;
        sy = map[0].length;
        p = new Panel();
        setSize(40 * map.length, 40 * map[0].length + 40);
        p.setLayout(new GridLayout(map.length, map[0].length));
        p.setPreferredSize(new Dimension(45 * map.length, 45 * map[0].length));
        buttons = new AkariButton[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                buttons[i][j] = new AkariButton(this, i, j, State.toState(map[i][j]));
                p.add(buttons[i][j]);
            }
        }
        repaint();
        add(p);
        AkariMenuBar a = new AkariMenuBar(this);
        this.setJMenuBar(a);
        score = new Score();
        a.add(score);
        setVisible(true);
    }
}
