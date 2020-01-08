/**
 * Enum class to represent states of Akari tile
 */
public enum State {
     Dark(0),
    Lit(7),
    Black(6),
    Bulb(8),
    Black_0(5),
    Black_1(1),
    Black_2(2),
    Black_3(3),
    Black_4(4);

     int value;
    State(int value){
        this.value=value;
    }
    public static State getState(int value) {
        switch (value) {
            case 0:
                return State.Dark;
            case 1:
                return State.Black_1;
            case 2:
                return State.Black_2;
            case 3:
                return State.Black_3;
            case 4:
                return State.Black_4;
            case 5:
                return State.Black_0;
            case 6:
                return State.Black;
            case 7:
                return State.Lit;
            case 8:
                return State.Bulb;
        }
        return null;
    }
}
