package akari.maps;

import akari.view.Akari;
import akari.view.AkariButton;
import akari.view.State;

/**
 * akari.maps.Checker class for akari
 */
public class Checker {
    private Akari akari;

    /**
     * Constructor for checker.
     *
     * @param akari akari.view.Akari to check.
     */
    public Checker(Akari akari) {
        this.akari = akari;
    }

    /**
     * Method to check if the game is solved.
     * @return <code>true</code> if game is solved, <code>false</code otherwise.
     */
    public boolean check() {
        for (int i = 0; i < akari.buttons.length; i++) {
            for (int j = 0; j < akari.buttons.length; j++) {
                if (akari.buttons[i][j].state.getValue() < 1) return false;
                if (akari.buttons[i][j].state.getValue() == 9) return false;
                if (akari.buttons[i][j].state.getValue() < 6 && akari.buttons[i][j].state.getValue() > 0){
                    if(!blackCheck(akari.buttons[i][j])) return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to check if black tile has the same number of neighbour lights as the number on it.
     * @param a Button to check.
     * @return <code>true</code> if it has the same, <code>false</code> if it has different amount of neighbour lights.
     */
    private boolean blackCheck(AkariButton a) {
        int numberOfBulbs = 0;
        if (a.x + 1 < akari.sx && (akari.buttons[a.x + 1][a.y].state == State.Bulb)) {
            numberOfBulbs++;
        }
        if (a.y + 1 < akari.sx && (akari.buttons[a.x][a.y + 1].state == State.Bulb)) {
            numberOfBulbs++;
        }
        if (a.x - 1 > -1 && (akari.buttons[a.x - 1][a.y].state == State.Bulb)) {
            numberOfBulbs++;
        }
        if (a.y - 1 > -1 && (akari.buttons[a.x][a.y - 1].state == State.Bulb)) {
            numberOfBulbs++;
        }
        if (a.state.getValue() == 5) {
            return numberOfBulbs == 0;
        } else {
            return numberOfBulbs == a.state.getValue();
        }
    }
}
