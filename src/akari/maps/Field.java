package akari.maps;

public class Field {
    private int xCord;
    private int yCord;

    /**
     *  check if the field was visited
     * @return return true if field was visited and false i other case
     */
    boolean isVisited() {
        return visited;
    }

    /**
     * Set the boolean parameter
     * @param visited if the field is checked or not
     */
    void setVisited(boolean visited) {
        this.visited = visited;
    }

    private boolean visited;

    /**
     * Constructor of the object Field, at start the field is not viisted
     * @param xCord X cordinate of the field
     * @param yCord Y cordinate og the field
     */
    Field(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.visited = false;
    }

    /**
     *  Check the X cordinate
     * @return  return x Cordinate
     */
    public int getxCord() {
        return xCord;
    }

    /**
     *  Check the Y cordinate
     * @return return Y Cordinate
     */
    int getyCord() {
        return yCord;
    }
}
