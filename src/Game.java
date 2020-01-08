
public class Game extends Akari {

    public Game(int size) {
        super(size,size);
    }
    public static void main(String args[]){
        Game a= new Game(10);
        a.buttons[3][6].setState(State.Black);
        a.buttons[3][5].setState(State.Black);
        a.buttons[6][6].setState(State.Black);
        a.buttons[6][5].setState(State.Black);
    }


}
