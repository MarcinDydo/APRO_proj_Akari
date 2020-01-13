/**
 * Checker class for akari
 */
class Checker {
    private Akari akari;

    Checker(Akari akari) {
        this.akari = akari;
    }

    boolean check() {
        for (int i = 0; i < akari.buttons.length; i++) {
            for (int j = 0; j < akari.buttons.length; j++) {
                if (akari.buttons[i][j].state.getValue() < 1) return false;
                if (akari.buttons[i][j].state.getValue() < 6 && akari.buttons[i][j].state.getValue() > 0)
                    return blackCheck(akari.buttons[i][j]);
            }
        }
        return true;
    }

    private boolean blackCheck(AkariButton a) {
        int numberOfBulbs = 0;
        if (a.x + 1 < akari.sx && (akari.buttons[a.x + 1][a.y].state == State.Dark)) {
            numberOfBulbs++;
        }
        if (a.y + 1 < akari.sx && (akari.buttons[a.x][a.y + 1].state == State.Dark)) {
            numberOfBulbs++;
        }
        if (a.x - 1 > -1 && (akari.buttons[a.x - 1][a.y].state == State.Dark)) {
            numberOfBulbs++;
        }
        if (a.y - 1 > -1 && (akari.buttons[a.x][a.y - 1].state == State.Dark)) {
            numberOfBulbs++;
        }
        if (a.state.getValue() == 5) {
            return numberOfBulbs == 0;
        } else {
            return numberOfBulbs == a.state.getValue();
        }
    }
}
