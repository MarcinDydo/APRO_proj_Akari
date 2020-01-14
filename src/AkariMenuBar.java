import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Class to represent MenuBar of the akari game.
 */
class AkariMenuBar extends JMenuBar {

    AkariMenuBar(Akari akari) throws HeadlessException {
        JMenuItem generate  = new JMenuItem(new AbstractAction("Generate new level") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Generator generator = new Generator(akari.sx,akari.sy);
                akari.swap(generator.getMAP(4));
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
                String savePath = JOptionPane.showInputDialog(Akari.p, "Where to save the file?", "Specify the path...");
                Saver csvFile = new Saver(akari);
                csvFile.saveToCSV(savePath);

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
                Generator generator = new Generator(6,6);
                akari.swap(generator.getMAP(4));
            }
        });
        JMenuItem Easy = new JMenuItem(new AbstractAction("Easy") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Generator generator = new Generator(8,8);
                akari.swap(generator.getMAP(4));
            }
        });
        JMenuItem Medium = new JMenuItem(new AbstractAction("Medium") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Generator generator = new Generator(10,10);
                akari.swap(generator.getMAP(4));
            }
        });
        JMenuItem Hard = new JMenuItem(new AbstractAction("Hard") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Generator generator = new Generator(12,12);
                akari.swap(generator.getMAP(4));
            }
        });
        JMenuItem veryHard = new JMenuItem(new AbstractAction("Very Hard") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Generator generator = new Generator(15,15);
                akari.swap(generator.getMAP(4));
            }
        });
        difficulty.add(veryEasy);
        difficulty.add(Easy);
        difficulty.add(Medium);
        difficulty.add(Hard);
        difficulty.add(veryHard);
        file.add(Import);
        file.add(generate);
    }


}
