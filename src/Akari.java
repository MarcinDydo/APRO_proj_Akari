import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Akari extends JFrame {
    int sx;
    int sy;
    JPanel p=new JPanel();
    AkariButton[][] buttons;

    public static void main(String args[]){
        Akari a= new Akari(10,10);
        a.buttons[5][2].setValue(6);
        a.buttons[5][7].setValue(6);
    }

    public Akari(int x,int y){
        super("Akari");
        this.sx=x;
        this.sy=y;
        buttons=new AkariButton[x][y];
        setSize(40*x,40*y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                buttons[i][j]=new AkariButton(i,j);
                p.add(buttons[i][j]);
            }
        }
        add(p);
        setVisible(true);
    }
    public class AkariButton extends JButton implements MouseListener {
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
                    break;
                case 7:
                    this.setIcon(Lit);
                    break;
                case 8:
                    this.setIcon(Bulb);
                    break;
            }
        }

        ImageIcon Dark,Lit,Black,Bulb,Black_0,Black_1,Black_2,Black_3,Black_4;
        int value=0;
        int x,y;
        int cross=0;
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
         */
        boolean lit=false;
        public AkariButton(int x,int y){
            this.x=x;
            this.y=y;
            Dark=new ImageIcon(this.getClass().getResource("dark.png"));
            Lit=new ImageIcon(this.getClass().getResource("lit.png"));
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
        public void mouseClicked(MouseEvent e){
           System.out.println(this.x);
            System.out.println( this.y);
            switch(value) {
                case 0:
                case 7:
                    setValue(8);
                    expand(this,7);
                    break;
                case 8:
                    setValue(0);
                    expand(this,0);
                    reloadLight();
                    break;
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

        public void expand(AkariButton a,int value){
            expandDown(a,value);
            expandUp(a,value);
            expandRight(a,value);
            expandLeft(a,value);
        }
       public boolean Litable(AkariButton a){
            if(a.value==0) return true;
            else return false;
       }
       public void expandDown(AkariButton a,int state){
            int x = a.x;
            int y = a.y;
           if (x + 1 < sx && (buttons[x+1][y].value==0 || buttons[x+1][y].value==7)) {
               buttons[x+1][y].setValue(state);
               expandDown(buttons[x+1][y],state);
           }
       }
        public void expandUp(AkariButton a,int state){
            int x = a.x;
            int y = a.y;
            if (x -1 > -1 && (buttons[x-1][y].value==0 || buttons[x-1][y].value==7)) {
                buttons[x-1][y].setValue(state);
                expandUp(buttons[x-1][y],state);
            }
        }
        public void expandRight(AkariButton a,int state){
            int x = a.x;
            int y = a.y;
            if (y+1 < sy && (buttons[x][y+1].value==0 || buttons[x][y+1].value==7)) {
                buttons[x][y+1].setValue(state);
                expandRight(buttons[x][y+1],state);
            }
        }
        public void expandLeft(AkariButton a,int state){
            int x = a.x;
            int y = a.y;
            if (y-1 > -1 && (buttons[x][y-1].value==0 || buttons[x][y-1].value==7)) {
                buttons[x][y-1].setValue(state);
                expandLeft(buttons[x][y-1],state);
            }
        }
        public void reloadLight(){
            for(int i=0;i<sx;i++){
                for(int j=0;j<sy;j++){
                    if(buttons[i][j].value==8){
                        expand(buttons[i][j],7);
                    }
                }
            }
        }



    }
}
