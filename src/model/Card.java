package model;

/**
 * Created by Phil on 3/20/2018.
 */
public class Card {

    enum Suit {
        CLUB, SPADE, HEART, DIAMOND
    }

    public int getBlackjackValue() {
        switch (value) {
            case 1:
                return 11;
            case 11:
            case 12:
            case 13:
                return 10;
            default:
                return value;
        }
    }

    private int value;

    private Suit suit;

    Card(int value, Suit suit) {
        if (0 < value && value < 14) { //1 2 3 4 5 6 7 8 9 10 j11 q12 k13
            this.suit = suit;
            this.value = value;
        }
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        switch (value) {
            case 13:
                ret.append("K");
                break;
            case 12:
                ret.append("Q");
                break;
            case 11:
                ret.append("J");
                break;
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
                ret.append(value);
                break;
            case 1:
                ret.append("A");
                break;
        }
        ret.append(" of ");
        ret.append(this.suit.toString());
        return ret.toString();
    }
}
