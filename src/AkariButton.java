import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AkariButton extends JButton implements MouseListener {
    public State state;
    private Akari akari;

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

    ImageIcon StateDark,StateLit,Dark,DarkCross,Lit,LitCross,Black,Bulb,Black_0,Black_1,Black_2,Black_3,Black_4;
    int cross;
    int bulb_conflict;
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

    public void expand(AkariButton a, State state){
        expandDown(a,state);
        expandUp(a,state);
        expandRight(a,state);
        expandLeft(a,state);
    }
   public boolean Litable(AkariButton a){
        if(a.state== State.Dark) return true;
        else return false;
   }       public void expandDown(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (x + 1 < akari.sx && (akari.buttons[x+1][y].state==State.Dark || akari.buttons[x+1][y].state==State.Lit)) {
            if(akari.buttons[x+1][y].state==State.Bulb){
                akari.buttons[x+1][y].bulb_conflict=1;
            }
            akari.buttons[x+1][y].setState(state);
            expandDown(akari.buttons[x+1][y],state);
        }
    }
    public void expandUp(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (x -1 > -1 && (akari.buttons[x-1][y].state==State.Dark || akari.buttons[x-1][y].state==State.Lit)) {
            akari.buttons[x-1][y].setState(state);
            expandUp(akari.buttons[x-1][y],state);
        }
    }
    public void expandRight(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (y+1 < akari.sy && (akari.buttons[x][y+1].state==State.Dark || akari.buttons[x][y+1].state==State.Lit)) {
            akari.buttons[x][y+1].setState(state);
            expandRight(akari.buttons[x][y+1],state);
        }
    }
    public void expandLeft(AkariButton a, State state){
        int x = a.x;
        int y = a.y;
        if (y-1 > -1 && (akari.buttons[x][y-1].state==State.Dark || akari.buttons[x][y-1].state==State.Lit)) {
            akari.buttons[x][y-1].setState(state);
            expandLeft(akari.buttons[x][y-1],state);
        }
    }
    public void reloadLight(){
        for(int i = 0; i< akari.sx; i++){
            for(int j = 0; j< akari.sy; j++){
                if(akari.buttons[i][j].state==State.Bulb){
                    expand(akari.buttons[i][j],State.Lit);
                }
            }
        }
    }

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
