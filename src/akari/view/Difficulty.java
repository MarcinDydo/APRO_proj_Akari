package akari.view;

/**
 * Enum class to represent Difficulty of the game.
 */
public enum Difficulty {
    veryEasy(6),
    Easy(8),
    Medium(10),
    Hard(12),
    veryHard(15);
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
