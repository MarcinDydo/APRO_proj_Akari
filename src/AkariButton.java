import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AkariButton extends JButton implements MouseListener {
    private Akari akari;

    public void setValue(int value) {
        this.value = value;
        switch(value) {
            case 0:
                this.setIcon(Dark);
                break;
            case 1:
                this.setIcon(Black_1);
                break;
            case 2:
                this.setIcon(Black_2);
                break;
            case 3:
                this.setIcon(Black_3);
                break;
            case 4:
                this.setIcon(Black_4);
                break;
            case 5:
                this.setIcon(Black_0);
                break;
            case 6:
                this.setIcon(Black);
                this.removeMouseListener(this);
                break;
            case 7:
                this.setIcon(Lit);
                break;
            case 8:
                this.setIcon(Bulb);
                break;
            case 9:
                this.setIcon(DarkCross);
                break;
            case 10:
                this.setIcon(LitCross);
                break;
        }
    }

    ImageIcon StateDark,StateLit,Dark,DarkCross,Lit,LitCross,Black,Bulb,Black_0,Black_1,Black_2,Black_3,Black_4;
    int cross;
    int value=0;
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
    }
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            cross(this);
        } else {
            System.out.println(this.x);
            System.out.println(this.y);
            switch (value) {
                case 0:
                case 7:
                    if(cross==0) {
                        setValue(8);
                        expand(this, 7);
                    }
                    break;
                case 8:
                        setValue(0);
                        expand(this, 0);
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

    public void expand(AkariButton a, int value){
        expandDown(a,value);
        expandUp(a,value);
        expandRight(a,value);
        expandLeft(a,value);
    }
   public boolean Litable(AkariButton a){
        if(a.value==0) return true;
        else return false;
   }       public void expandDown(AkariButton a, int state){
        int x = a.x;
        int y = a.y;
        if (x + 1 < akari.sx && (akari.buttons[x+1][y].value==0 || akari.buttons[x+1][y].value==7)) {
            akari.buttons[x+1][y].setValue(state);
            expandDown(akari.buttons[x+1][y],state);
        }
    }
    public void expandUp(AkariButton a, int state){
        int x = a.x;
        int y = a.y;
        if (x -1 > -1 && (akari.buttons[x-1][y].value==0 || akari.buttons[x-1][y].value==7)) {
            akari.buttons[x-1][y].setValue(state);
            expandUp(akari.buttons[x-1][y],state);
        }
    }
    public void expandRight(AkariButton a, int state){
        int x = a.x;
        int y = a.y;
        if (y+1 < akari.sy && (akari.buttons[x][y+1].value==0 || akari.buttons[x][y+1].value==7)) {
            akari.buttons[x][y+1].setValue(state);
            expandRight(akari.buttons[x][y+1],state);
        }
    }
    public void expandLeft(AkariButton a, int state){
        int x = a.x;
        int y = a.y;
        if (y-1 > -1 && (akari.buttons[x][y-1].value==0 || akari.buttons[x][y-1].value==7)) {
            akari.buttons[x][y-1].setValue(state);
            expandLeft(akari.buttons[x][y-1],state);
        }
    }
    public void reloadLight(){
        for(int i = 0; i< akari.sx; i++){
            for(int j = 0; j< akari.sy; j++){
                if(akari.buttons[i][j].value==8){
                    expand(akari.buttons[i][j],7);
                }
            }
        }
    }

    public void cross(AkariButton a){
    if(cross==0){
        a.Dark=DarkCross;
        a.Lit=LitCross;
        a.setValue(a.value);
        cross=1;
    } else if(cross==1){
        a.Dark=StateDark;
        a.Lit=StateLit;
        a.setValue(a.value);
        cross=0;
    }
}
}
