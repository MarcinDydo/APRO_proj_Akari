import javax.swing.*;
/**
 * Enum class to represent states of Akari tile
 */
public enum State {
    Error(-1),
    Dark(0),
    Black_1(1),Black_2(2),Black_3(3),Black_4(4),Black_0(5),
    Black(6),
    Lit(7),
    Bulb(8),
    DarkCross(9),
    LitCross(10);
    /**
     * Icon that can represent tile state.
     */
    ImageIcon icon;
    private int value;
    public int getValue() {
        return value;
    }

    /**
     * constructor
     * @param value
     */
    State(int value){
        switch (value){
            case -1:
                this.icon=new ImageIcon(this.getClass().getResource("bulb_conflict.png")); break;
            case 0:
                this.icon=new ImageIcon(this.getClass().getResource("dark.png")); break;
            case 1:
                this.icon=new ImageIcon(this.getClass().getResource("black_1.png")); break;
            case 2:
                this.icon=new ImageIcon(this.getClass().getResource("black_2.png")); break;
            case 3:
                this.icon=new ImageIcon(this.getClass().getResource("black_3.png")); break;
            case 4:
                this.icon=new ImageIcon(this.getClass().getResource("black_4.png")); break;
            case 5:
                this.icon=new ImageIcon(this.getClass().getResource("black_0.png")); break;
            case 6:
                this.icon=new ImageIcon(this.getClass().getResource("black.png")); break;
            case 7:
                this.icon=new ImageIcon(this.getClass().getResource("lit.png")); break;
            case 8:
                this.icon=new ImageIcon(this.getClass().getResource("bulb.png")); break;
            case 9:
                this.icon=new ImageIcon(this.getClass().getResource("dark_cross.png")); break;
            case 10:
                 this.icon=new ImageIcon(this.getClass().getResource("lit_cross.png")); break;
        }
        this.value=value;
    }

    /**
     * converts number to State
     * @param fromMap integer representing tile
     * @return
     */
    public static State toState(int fromMap){
        switch (fromMap) {
            default:
                return Dark;
            case 1:
                return Black_1;
            case 2:
                return Black_2;
            case 3:
                return Black_3;
            case 4:
                return Black_4;
            case 5:
                return Black_0;
            case 6:
                return Black;
            case 7:
                return Lit;
            case 8:
                return Bulb;
            case 9:
                return DarkCross;
            case 10:
                return LitCross;
        }
    }
       /*
    0-Dark
    1- Black_1
    2- Black_2
    3- Black_3
    4- Black_4
    5- Black_0
    6- Black
    7- Lit
    8- Bulb
    9-Dark_Cross
    10-Dark_Lit
     */
}
