package akari.maps;

public class Field {
    private int xCord;
    private int yCord;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    private boolean visited;

    public Field(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.visited = false;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }
}
