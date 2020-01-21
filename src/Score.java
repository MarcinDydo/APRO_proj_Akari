import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Score extends JMenuItem implements ActionListener
{
    private JLabel timeLabel;
    int score = 600;
    public Score()
    {
        timeLabel = new JLabel();
        add( timeLabel );
        Timer timer = new Timer(1000, this);
        timer.setInitialDelay(1);
        timer.start();
    }

    public int getScore() {
        return score;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        score--;
        //System.out.println(e.getSource());
        timeLabel.setText(""+score+"pkt.");
    }
}