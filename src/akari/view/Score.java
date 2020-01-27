package akari.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JMenuItem implements ActionListener {
    int score = 600;
    private JLabel timeLabel;

    public Score() {
        timeLabel = new JLabel();
        add(timeLabel);
        Timer timer = new Timer(1000, this);
        timer.setInitialDelay(1);
        timer.start();
    }

    public int getScore() {
        return score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        score--;
        //System.out.println(e.getSource());
        timeLabel.setText("" + score + "pkt.");
    }

    public void reset() {
        score = 600;
    }
}
