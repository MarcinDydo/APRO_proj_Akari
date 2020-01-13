import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * Class to represent Akari game
*/
public class Akari extends JFrame {
    int sx;
    int sy;
    AkariButton[][] buttons;
    Panel p = new Panel();

    /**
     * Constructor for akari game.
     * @param x Akari height.
     * @param y Akari width
     */
    public Akari(int x,int y){
        super("Akari");
        this.sx=x;
        this.sy=y;
        buttons=new AkariButton[x][y];
        Generator generator = new Generator(x,y);
        int[][] map = generator.getMAP(4);
        setSize(45*x,45*y);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                buttons[i][j] = new AkariButton(this, i,j, State.toState(map[i][j]));
                p.add(buttons[i][j]);
            }
        }
        add(p);
        this.setJMenuBar(new AkariMenuBar());
        setVisible(true);

    }
    private boolean checkMap(){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j].state.getValue() < 1) return false;
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
                if(checkMap()) System.out.println("Congratulations!"); //for further expansion
                else System.out.println("Game not finished");
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
