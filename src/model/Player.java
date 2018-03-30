package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phil on 3/21/2018.
 */
public class Player {

    public ArrayList<Card> getHand() {
        return hand;
    }

    private ArrayList<Card> hand;
    private ArrayList<Card> splitHand;

    public double getBankroll() {
        return bankroll;
    }

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

    public void setBankroll(double bankroll) {
        this.bankroll = bankroll;
    }

    private double bankroll;

    public Player() {
        hand = new ArrayList<>();
        bankroll = 1000;
    }
}
