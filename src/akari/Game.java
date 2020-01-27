package akari;

import akari.view.Akari;
import akari.view.Difficulty;

/**
 * Class to start the game.
 */
public class Game extends Akari {

    private Game(Difficulty difficulty) {
        super(difficulty);

    }

    public static void main(String[] args){
        new Game(Difficulty.Medium);
    }
}
