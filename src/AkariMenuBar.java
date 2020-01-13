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
        this.add(file);
        JMenuItem check = new JMenuItem(new AbstractAction("Check") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (new Checker(akari).check()) JOptionPane.showMessageDialog(Akari.p,
                        "You win!");
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
                    new Load("maps/"+loadPath+".csv",akari);
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
                Save csvFile = new Save(akari);
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
        file.add(Import);
        file.add(generate);
    }


}
