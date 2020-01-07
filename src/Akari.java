import javax.swing.*;
import java.awt.*;

public class Akari extends JFrame {
    int sx;
    int sy;
    JPanel p=new JPanel();
    AkariButton[][] buttons;


    public Akari(int x,int y){
        super("Akari");
        this.sx=x;
        this.sy=y;
        buttons=new AkariButton[x][y];
        setSize(45*x,45*y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MenuBar test = new MenuBar();

        Menu test1 = new Menu("test1");
        test.add(test1);
        p.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                buttons[i][j]=new AkariButton(this, i,j);
                p.add(buttons[i][j]);
            }
        }
        add(p);
        setVisible(true);
        this.setMenuBar(test);
    }
}
