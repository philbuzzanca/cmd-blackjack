package model;

import java.util.ArrayList;

/**
 * Created by Phil on 3/21/2018.
 */
public class Dealer {
    public int getScore() {
        int handScore = 0;
        int numAces = 0;
        for (Card c : hand) {
            if (c.getBlackjackValue() == 11) numAces++;
            handScore += c.getBlackjackValue();
        }
        while(handScore > 21 && numAces > 0) {
            numAces--;
            handScore -= 10;
        }
        return handScore;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    private ArrayList<Card> hand;

    public Dealer() {
        hand = new ArrayList<>();
    }
}
