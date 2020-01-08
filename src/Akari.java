import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Generator.Generator;
/**
 * Class to represent Akari game
*/
public class Akari extends JFrame {
    int sx;
    int sy;
    Panel p1=new Panel();
    Panel p=new Panel();
    AkariButton[][] buttons;

    /**
     * Constructor for akari game.
     * @param x Akari height.
     * @param y Akari width
     */
    public Akari(int x,int y){
        super("Akari");
        this.sx=x;
        this.sy=y;
        Generator g = new Generator(sx);
        buttons=new AkariButton[x][y];
        setSize(45*x,45*y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                buttons[i][j]=new AkariButton(this, i,j,State.getState(g.getMAP()[i][j]) );
                p.add(buttons[i][j]);
            }
        }
        add(p);
        this.setJMenuBar(new AkariMenuBar());
        setVisible(true);

    }

    /**
     * Method to check if solution is right.
     * @return true if solution is right, false when solution is wrong.
     */
    //Tu narazie piszcie jak chcecie metode do sprawdzania, na moje trzeba na pewno sprawdzic przy tych z cyframi czy jest tyle ile powinno bycc zarowek i druga sprawa to bedzie kolizja zarowek
            public boolean check(){
                for(int i=0;i<sx;i++){
                    for(int j=0;j<sx;j++){
                        if(buttons[i][j].state==State.Dark){
                            return false;
                        }
                    }
                }
                return true;
            }

    /**
     * Class to represent MenuBar of the akari game.
     */
    public class AkariMenuBar extends JMenuBar {
        JMenu file=new JMenu("File");
        JMenuItem Check=new JMenuItem(new AbstractAction("Check") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(check());
            }
        });
        JMenuItem Load=new JMenuItem(new AbstractAction("Load from CSV") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("click1");
            }
        });
        JMenuItem Save=new JMenuItem(new AbstractAction("Save as CSV") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("click2");
            }
        });
        JMenuItem Import=new JMenuItem(new AbstractAction("Import into PNG") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("click3");
            }
        });

        public AkariMenuBar() throws HeadlessException {
            this.add(file);
            this.add(Check);
            file.add(Load);
            file.add(Save);
            file.add(Import);
        }
    }

}
