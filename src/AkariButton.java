import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Class to represent One Akari Tile.
 */
public class AkariButton extends JButton implements MouseListener {
    public State state;
    private Akari akari;

    /**
     * Sets the state of this tile.
     * @param state tile to be set.
     */
    public void setState(State state) {
        this.state = state;
        switch(state) {
            case Dark:
                this.setIcon(Dark);
                break;
            case Black_1:
                this.setIcon(Black_1);
                break;
            case Black_2:
                this.setIcon(Black_2);
                break;
            case Black_3:
                this.setIcon(Black_3);
                break;
            case Black_4:
                this.setIcon(Black_4);
                break;
            case Black_0:
                this.setIcon(Black_0);
                break;
            case Black:
                this.setIcon(Black);
                this.removeMouseListener(this);
                break;
            case Lit:
                this.setIcon(Lit);
                break;
            case Bulb:
                this.setIcon(Bulb);
                break;
        }
    }

    /**
     * Icons that can represent tile state.
     */
    ImageIcon StateDark,StateLit,Dark,DarkCross,Lit,LitCross,Black,Bulb,Black_0,Black_1,Black_2,Black_3,Black_4;
    /**
    Indicator of cross marking.
     */
    int cross;
    /**
     * Position of the tile in the akari game.
     */
    int x,y;
    /*
    0-Dark
    1- Black_1
    2- Black_2
    3- Black_3
    4- Black_4
    5- Black_0
    6- Black
    7- Lit
    8- Bulb
    9-Dark_Cross
    10-Dark_Lit
     */

    /**
     * Constructor for tile.
     * @param akari Akari game that this tile belongs to.
     * @param x X coordinate of this tile in akari game.
     * @param y Y coordinate of this tile in akari game.
     */
    public AkariButton(Akari akari, int x, int y){
        this.akari = akari;
        this.x=x;
        this.y=y;
        StateDark=new ImageIcon(this.getClass().getResource("dark.png"));
        StateLit=new ImageIcon(this.getClass().getResource("lit.png"));
        Dark=new ImageIcon(this.getClass().getResource("dark.png"));
        Lit=new ImageIcon(this.getClass().getResource("lit.png"));
        DarkCross=new ImageIcon(this.getClass().getResource("dark_cross.png"));
        LitCross=new ImageIcon(this.getClass().getResource("lit_cross.png"));
        Bulb=new ImageIcon(this.getClass().getResource("bulb.png"));
        Black=new ImageIcon(this.getClass().getResource("black.png"));
        Black_0=new ImageIcon(this.getClass().getResource("black_0.png"));
        Black_1=new ImageIcon(this.getClass().getResource("black_1.png"));
        Black_2=new ImageIcon(this.getClass().getResource("black_2.png"));
        Black_3=new ImageIcon(this.getClass().getResource("black_3.png"));
        Black_4=new ImageIcon(this.getClass().getResource("black_4.png"));

        this.addMouseListener(this);
        setIcon(Dark);
        this.state=State.Dark;
    }

@Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            cross(this);
        } else {
            System.out.println(this.x);
            System.out.println(this.y);
            switch (state) {
                case Dark:
                case Lit:
                    if(cross==0) {
                        setState(State.Bulb);
                        expand(this, State.Lit  );
                    }
                    break;
                case Bulb:
                        setState(State.Dark);
                        expand(this, State.Dark);
                        reloadLight();
                    break;
            }
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

    /**
     * Recursive method to light up or darken tiles.
     * @param a Button to start with.
     * @param state State to set.
     */
    public void expand(AkariButton a, State state){
        expandDown(a,state);
        expandUp(a,state);
        expandRight(a,state);
        expandLeft(a,state);
    }

    /**
     * Recursive method to light up or darken tiles in the south direction.
     * @param a Button to start with.
     * @param state State to set.
     */
    public void expandDown(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (x + 1 < akari.sx && (akari.buttons[x+1][y].state==State.Dark || akari.buttons[x+1][y].state==State.Lit)) {
            akari.buttons[x+1][y].setState(state);
            expandDown(akari.buttons[x+1][y],state);
        }
    }
    /**
     * Recursive method to light up or darken tiles in the north direction.
     * @param a Button to start with.
     * @param state State to set.
     */
    public void expandUp(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (x -1 > -1 && (akari.buttons[x-1][y].state==State.Dark || akari.buttons[x-1][y].state==State.Lit)) {
            akari.buttons[x-1][y].setState(state);
            expandUp(akari.buttons[x-1][y],state);
        }
    }
    /**
     * Recursive method to light up or darken tiles in the right direction.
     * @param a Button to start with.
     * @param state State to set.
     */
    public void expandRight(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (y+1 < akari.sy && (akari.buttons[x][y+1].state==State.Dark || akari.buttons[x][y+1].state==State.Lit)) {
            akari.buttons[x][y+1].setState(state);
            expandRight(akari.buttons[x][y+1],state);
        }
    }
    /**
     * Recursive method to light up or darken tiles in the left direction.
     * @param a Button to start with.
     * @param state State to set.
     */
    public void expandLeft(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (y-1 > -1 && (akari.buttons[x][y-1].state==State.Dark || akari.buttons[x][y-1].state==State.Lit)) {
            akari.buttons[x][y-1].setState(state);
            expandLeft(akari.buttons[x][y-1],state);
        }
    }

    /**
     * Method to reload all the Lights.
     */
    public void reloadLight(){
        for(int i = 0; i< akari.sx; i++){
            for(int j = 0; j< akari.sy; j++){
                if(akari.buttons[i][j].state==State.Bulb){
                    expand(akari.buttons[i][j],State.Lit);
                }
            }
        }
    }

    /**
     * Method used to put cross on tile.
     * @param a Tile to put cross on.
     */
    public void cross(AkariButton a){
    if(cross==0){
        a.Dark=DarkCross;
        a.Lit=LitCross;
        a.setState(a.state);
        cross=1;
    } else if(cross==1){
        a.Dark=StateDark;
        a.Lit=StateLit;
        a.setState(a.state);
        cross=0;
    }
}
}
