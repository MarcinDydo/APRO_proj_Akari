import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class GUI extends JFrame {
    int spacing =5;
    public int mx = -100;
    public int my = -100;

    Random rand = new Random();

    int[][] neighbour =new int[8][8]; //cyfry
    int[][] black =new int[8][8]; //sciany
    int[][] map =new int[8][8]; //gdzie sa lampki
    boolean[][] lit =new boolean[8][8]; //czy zapalone
    public GUI() {
        this.setTitle("Test");
        this.setSize(680,680);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        for(int i=0; i<8;i++) {
            for(int j=0;j<8;j++) {
                if(rand.nextInt(100)<20){
                    black[i][j]=1;
                } else {
                    black[i][j] = 0;
                }
                lit[0][0]=false;
            }
        }
        for(int i=0; i<8;i++) {
            for(int j=0;j<8;j++) {
                    if(black[i][j]==1){
                        if(rand.nextInt(100)<40){
                            neighbour[i][j]=rand.nextInt(5);
                        }
                }
            }
        }

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }
    public class Board extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,720,680);
            g.setColor(Color.GRAY);
            for(int i=0; i<8;i++) {
                for(int j=0;j<8;j++) {
                    g.setColor(Color.GRAY);
                    if(lit[i][j]==true) {
                        g.setColor(Color.YELLOW);
                    }
                    if(black[i][j]==1){
                        g.setColor(Color.BLACK);
                    }
                    if(mx>=2*spacing+i*80 && mx< spacing+i*80+80 && my>= spacing+j*80+26 && my <j*80+ 80+26){
                        g.setColor(Color.RED);
                    }
                    g.fillRect(spacing+i*80,spacing+j*80,80-2*spacing,80-2*spacing);
                }
            }
            for(int i=0; i<8;i++) {
                for (int j = 0; j < 8; j++) {
                    g.setColor(Color.WHITE);

                    if(black[i][j]==1) {
                        if (neighbour[i][j] != 0) {
                            g.drawString(Integer.toString(neighbour[i][j]), spacing + i *80 +40, spacing + j * 80+40);
                        }
                    }
                }
            }
        }
    }

    public class Move implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            my = e.getY();
            mx= e.getX();
        }
    }
    public class Click implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(inBoxX()!=-1&& inBoxY()!=-1){
                System.out.println("X "+inBoxX());
                System.out.println("Y "+inBoxY());
            }
            else {
                System.out.println("Out of the box");
            }
            if(lit[inBoxX()][inBoxY()]==false){
                lit[inBoxX()][inBoxY()]=true;
            } else{
                lit[inBoxX()][inBoxY()]=false;
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    public int inBoxX() {
        for(int i=0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(mx>=2*spacing+i*80 && mx< spacing+i*80+80 && my>= spacing+j*80+26 && my <j*80+ 80+26){
                    return i;
                }

            }
        }
        return -1;
            }
    public int inBoxY() {
        for(int i=0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(mx>=2*spacing+i*80 && mx< spacing+i*80+80 && my>= spacing+j*80+26 && my <j*80+ 80+26){
                    return j;
                }

            }
        }
        return -1;
    }

    }
