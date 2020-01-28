package akari.view;

/**
 * Enum class to represent Difficulty of the game.
 */
public enum Difficulty {
    veryEasy(5),
    Easy(7),
    Medium(9),
    Hard(11),
    veryHard(13);
    private int size;

    Difficulty(int size){
        this.size=size;
    }

    /**
     * Getter for difficulty
     *
     * @return size of map.
     */
    public int getSize() {
        return size;
    }
}
