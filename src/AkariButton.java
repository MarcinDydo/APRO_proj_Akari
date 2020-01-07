import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
switch(value) {
    case 0:
    case 7:
        setIcon(Bulb);
        value=8;
        break;

    case 8:
        setIcon(Dark);
        value=0;
        break;
}
    }

}
