import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Class to represent MenuBar of the akari game.
 */
class AkariMenuBar extends JMenuBar {
    Score score = new Score();
    /**
     * Constructor for Menu bar.
     *
     * @param akari Akari this menu bar will be attached to.
     * @throws HeadlessException Thrown when code that is dependent on a mouse is called in an environment that does not a mouse.
     */
    AkariMenuBar(Akari akari) throws HeadlessException {
        JMenuItem Help = new JMenuItem(new AbstractAction("Help") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(Akari.p,
                        "Akari is a logic puzzle with simple rules and challenging solutions.\n" +
                                "\n" +
                                "The rules are simple.Light Up is played on a rectangular grid.\n The grid has both black cells and white cells in it.\n The objective is to place light bulbs on the grid so that every white square is lit. \nA cell is illuminated by a light bulb if they're in the same row or column. \n Also, no light bulb may illuminate another light bulb.\n" +
                                "Some of the black cells have numbers in them.\n A number in a black cell indicates how many light bulbs share an edge with that cell.\n" +
                                "Left click a square to place a light bulb. Right click to mark with X.");
            }
        });
        JMenuItem generate = new JMenuItem(new AbstractAction("Generate new level") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Generator generator = new Generator(akari.difficulty.getSize(),akari.difficulty.getSize());
                akari.swap(generator.getMAP(Akari.ratio));
            }
        });
        JMenu file = new JMenu("File");
        JMenu difficulty = new JMenu("Change Difficulty");
        this.add(file);
        this.add(difficulty);
        JMenuItem check = new JMenuItem(new AbstractAction("Check") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (new Checker(akari).check()) {
                    Object[] options = {"New Game",
                            "Save Game",
                            "Return to game"};
                    int n = JOptionPane.showOptionDialog(akari,
                            "You won! What would you like to do next?",
                            "Game Won",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[2]);
                    System.out.println(n);
                    if(n==1){
                        String savePath = JOptionPane.showInputDialog(Akari.p, "Where to save the file?", "Specify the path...");
                        Saver csvFile = new Saver(akari);
                        csvFile.saveToCSV(savePath);
                    } else if(n==0){
                        Generator generator = new Generator(akari.sx,akari.sy);
                        akari.swap(generator.getMAP(4));
                    }
                }
                else JOptionPane.showMessageDialog(Akari.p,
                        "Solution is incorrect");
            }
        });
        this.add(check);
        JMenuItem load = new JMenuItem(new AbstractAction("Load from CSV") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String loadPath = JOptionPane.showInputDialog(akari, "Where to load the file from?", "Specify the path...");
                try {
                    new Loader("maps/"+loadPath+".csv",akari);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(akari,
                            "Incorrect path");
                }
            }
        });
        file.add(load);
        JMenuItem save = new JMenuItem(new AbstractAction("Save as CSV") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String savePath = JOptionPane.showInputDialog(Akari.p, "Where to save the file?", "");
                    Saver csvFile = new Saver(akari);
                    csvFile.saveToCSV(savePath);
                }catch (NullPointerException ignored){}
            }
        });
        file.add(save);
        JMenuItem Import = new JMenuItem(new AbstractAction("Import into PNG") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("click3");
            }

    });
        JMenuItem veryEasy = new JMenuItem(new AbstractAction("Very Easy") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                akari.difficulty = Difficulty.veryEasy;
                Generator generator = new Generator(akari.difficulty.getSize(),akari.difficulty.getSize());
                akari.swap(generator.getMAP(Akari.ratio));
            }
        });
        JMenuItem Easy = new JMenuItem(new AbstractAction("Easy") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                akari.difficulty = Difficulty.Easy;
                Generator generator = new Generator(akari.difficulty.getSize(),akari.difficulty.getSize());
                akari.swap(generator.getMAP(Akari.ratio));
            }
        });
        JMenuItem Medium = new JMenuItem(new AbstractAction("Medium") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                akari.difficulty = Difficulty.Medium;
                Generator generator = new Generator(akari.difficulty.getSize(),akari.difficulty.getSize());
                akari.swap(generator.getMAP(Akari.ratio));
            }
        });
        JMenuItem Hard = new JMenuItem(new AbstractAction("Hard") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                akari.difficulty = Difficulty.Hard;
                Generator generator = new Generator(akari.difficulty.getSize(),akari.difficulty.getSize());
                akari.swap(generator.getMAP(Akari.ratio));
            }
        });
        JMenuItem veryHard = new JMenuItem(new AbstractAction("Very Hard") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                akari.difficulty = Difficulty.veryHard;
                Generator generator = new Generator(akari.difficulty.getSize(),akari.difficulty.getSize());
                akari.swap(generator.getMAP(Akari.ratio));
            }
        });
        difficulty.add(veryEasy);
        difficulty.add(Easy);
        difficulty.add(Medium);
        difficulty.add(Hard);
        difficulty.add(veryHard);
        file.add(Import);
        file.add(generate);
        add(Help);
        add(score);
    }


}
