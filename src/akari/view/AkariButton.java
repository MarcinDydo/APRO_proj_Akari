package akari.view;

import akari.maps.Checker;
import akari.maps.Generator;
import akari.maps.Saver;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Class to represent One Akari Tile.
 */
public class AkariButton extends JButton implements MouseListener {
    public State state;
    public Akari akari;
    public boolean cross;
    public int x,y;

    /**
     * Constructor for tile.
     * @param akari Akari game that this tile belongs to.
     * @param x X coordinate of this tile in akari game.
     * @param y Y coordinate of this tile in akari game.
     * @param state indicator of state and icon of the tile
     */
    AkariButton(Akari akari, int x, int y, State state) {
        this.akari = akari;
        this.x=x;
        this.y=y;
        this.addMouseListener(this);
        this.state=state;
        setIcon(state.icon); //sets default texture for button (from csv)
    }
    /**
     * Sets the state of this tile.
     * and updates its icon
     * @param state tile to be set.
     */
    private void setState(State state) {
        if(cross){
            if (state == State.Lit) this.state = State.LitCross;
            if (state == State.Dark) this.state = State.DarkCross;
        }else this.state = state;

        setIcon(this.state.icon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            cross();
        } else {
            System.out.println(this.x);
            System.out.println(this.y);
            switch (state) {
                case Dark:
                case Lit:
                    if(!cross) {
                        setState(State.Bulb);
                        expand(this, State.Lit );
                        reloadLight();
                        if (new Checker(akari).check()) {
                            Object[] options = {"New akari.Game",
                                    "Save akari.Game",
                                    "Return to game"};
                            int n = JOptionPane.showOptionDialog(akari,
                                    "You won! What would you like to do next?",
                                    "akari.Game Won",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[2]);
                            System.out.println(n);
                            if (n == 1) {
                                String savePath = JOptionPane.showInputDialog(Akari.p, "Where to save the file?", "Specify the path...");
                                Saver csvFile = new Saver(akari);
                                csvFile.saveToCSV(savePath);
                            } else if (n == 0) {
                                Generator generator = new Generator(akari.sx, akari.sy);
                                akari.swap(generator.getMAP(4));
                            }
                        }
                    }
                    break;
                case Bulb:
                case Error:
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
     * @param state akari.view.State to set.
     */
    private void expand(AkariButton a, State state) {
        expandRight(a,state);
        expandLeft(a,state);
        expandDown(a,state);
        expandUp(a,state);
    }

    /**
     * Recursive method to light up or darken tiles in the south direction.
     * @param a Button to start with.
     * @param state akari.view.State to set.
     */
    private void expandDown(AkariButton a, State state) {
        int x = a.x;
        int y = a.y;
        if (x + 1 < akari.sx && (akari.buttons[x+1][y].state==State.Dark || akari.buttons[x+1][y].state==State.Lit
                || akari.buttons[x+1][y].state==State.LitCross || akari.buttons[x+1][y].state==State.DarkCross)) {
            akari.buttons[x+1][y].setState(state);
            expandDown(akari.buttons[x+1][y],state);
        } else if((x + 1 < akari.sx) && (akari.buttons[x+1][y].state==State.Bulb)){
            akari.buttons[x+1][y].setState(State.Error);
        }
    }
    /**
     * Recursive method to light up or darken tiles in the north direction.
     * @param a Button to start with.
     * @param state akari.view.State to set.
     */
    private void expandUp(AkariButton a, State state) {
        int x = a.x;
        int y = a.y;
        if (x -1 > -1 && (akari.buttons[x-1][y].state==State.Dark || akari.buttons[x-1][y].state==State.Lit
                || akari.buttons[x-1][y].state==State.LitCross || akari.buttons[x-1][y].state==State.DarkCross)) {
            akari.buttons[x-1][y].setState(state);
            expandUp(akari.buttons[x-1][y],state);
        } else if((x -1 > -1) && (akari.buttons[x-1][y].state==State.Bulb)){
            akari.buttons[x-1][y].setState(State.Error);
        }
    }
    /**
     * Recursive method to light up or darken tiles in the right direction.
     * @param a Button to start with.
     * @param state akari.view.State to set.
     */
    private void expandRight(AkariButton a, State state) {
        int x = a.x;
        int y = a.y;
        if (y+1 < akari.sy && (akari.buttons[x][y+1].state==State.Dark || akari.buttons[x][y+1].state==State.Lit
                || akari.buttons[x][y+1].state==State.LitCross || akari.buttons[x][y+1].state==State.DarkCross)) {
            akari.buttons[x][y+1].setState(state);
            expandRight(akari.buttons[x][y+1],state);
        } else if((y + 1 < akari.sy) && (akari.buttons[x][y+1].state==State.Bulb)){
            akari.buttons[x][y+1].setState(State.Error);
        }
    }
    /**
     * Recursive method to light up or darken tiles in the left direction.
     * @param a Button to start with.
     * @param state akari.view.State to set.
     */
    private void expandLeft(AkariButton a, State state) {
        int x = a.x;
        int y = a.y;
        if (y-1 > -1 && (akari.buttons[x][y-1].state==State.Dark || akari.buttons[x][y-1].state==State.Lit
                || akari.buttons[x][y-1].state==State.LitCross || akari.buttons[x][y-1].state==State.DarkCross)) {
            akari.buttons[x][y-1].setState(state);
            expandLeft(akari.buttons[x][y-1],state);
        } else if((y-1 > -1) && (akari.buttons[x][y-1].state==State.Bulb)){
            akari.buttons[x][y-1].setState(State.Error);
        }
    }

    /**
     * Method to reload all the Lights.
     */
    public void reloadLight() {
        for(int i = 0; i< akari.sx; i++){
            for(int j = 0; j< akari.sy; j++){
                if(akari.buttons[i][j].state==State.Bulb||akari.buttons[i][j].state==State.Error){
                    akari.buttons[i][j].setState(State.Bulb);
                }
            }
        }
        for(int i = 0; i< akari.sx; i++){
            for(int j = 0; j< akari.sy; j++){
                if(akari.buttons[i][j].state==State.Bulb||akari.buttons[i][j].state==State.Error){
                    expand(akari.buttons[i][j], State.Lit);
                }
            }
        }
    }

    /**
     * Method to change cross state and texture for the tile.
     */
    private void cross() {
        if(cross){
            if(this.state == State.LitCross) {
                cross=false;
                setState(State.Lit);
            }
            if(this.state == State.DarkCross) {
                cross=false;
                setState(State.Dark);
            }
        }else{
            if(this.state == State.Dark) {
                cross=true;
                setState(State.Dark);
            }
            if(this.state == State.Lit) {
                cross=true;
                setState(State.Lit);
            }
        }
    }
}
