import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Akari extends JFrame {
    JPanel p=new JPanel();
    AkariButton[][] buttons =new AkariButton[8][8];

    public static void main(String args[]){
        new Akari();
    }

    public Akari(){
        super("Akari");
        setSize(360,360);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(8,8));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                buttons[i][j]=new AkariButton(i,j);
                p.add(buttons[i][j]);
            }
        }
        add(p);
        setVisible(true);
    }
    public class AkariButton extends JButton implements ActionListener {
        ImageIcon Dark,Lit,Black,Bulb,Black_0,Black_1,Black_2,Black_3,Black_4;
        byte value=0;
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
            this.addActionListener(this);
            setIcon(Dark);
        }
        public void actionPerformed(ActionEvent e){
           System.out.println(this.x);
            System.out.println( this.y);
            switch(value) {
                case 0:
                case 7:
                    setIcon(Bulb);
                    value=8;
                    Lighting(this);
                    break;
                case 8:
                    setIcon(Dark);
                    value=0;
                    break;
            }
        }
        public void Lighting(AkariButton a){
            if((this.x-1)>-1 && buttons[this.x-1][this.y].value==0){
                buttons[this.x-1][this.y].value=7;
                buttons[this.x-1][this.y].setIcon(Lit);
            }
            if((this.x+1)<8 &&buttons[this.x+1][this.y].value==0){
                buttons[this.x+1][this.y].value=7;
                buttons[this.x+1][this.y].setIcon(Lit);
            }
            if((this.y-1)>-1 && buttons[this.x][this.y-1].value==0){
                buttons[this.x][this.y-1].value=7;
                buttons[this.x][this.y-1].setIcon(Lit);
            }
            if((this.y+1)<8 &&buttons[this.x][this.y+1].value==0){
                buttons[this.x][this.y+1].value=7;
                buttons[this.x][this.y+1].setIcon(Lit);
            }

        }
       public boolean Litable(AkariButton a){
            if(a.value==0) return true;
            else return false;
       }

    }
}
