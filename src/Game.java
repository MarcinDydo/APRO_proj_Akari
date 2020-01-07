
public class Game extends Akari {


    public Game(int difficulty) {
        super(difficulty*6,difficulty*6);
    }
    public static void main(String args[]){
        Game a= new Game(10);
        a.buttons[5][2].setValue(6);
        a.buttons[5][7].setValue(6);
    }
}
