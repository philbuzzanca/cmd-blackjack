package model;

import model.Card;

import java.util.LinkedList;

/**
 * Created by Phil on 3/20/2018.
 */
public class Deck {

    private int cardCount;

    public void shuffle() {
        for(int i = 0; i < 2000; i++){
            int rand = (int) Math.floor(Math.random() * (cardCount));
            deck.addLast(deck.remove(rand));
        }
    }

    public Card deal() {
        return deck.pop();
    }

    public Deck() {
        deck = new LinkedList<>();
        cardCount = 0;
    }

    public Deck(int decks){
        deck = new LinkedList<>();
        cardCount = 52 * decks;
        initDeck(decks);
    }

    private void initDeck(int decks){
        for(int i = 0; i < decks; i++){
           for(int j = 1; j < 5; j++) {
               for(int k = 1; k < 14; k++) {
                   switch (j) {
                       case 1:
                           deck.add(new Card(k, Card.Suit.CLUB));
                           break;
                       case 2:
                           deck.add(new Card(k, Card.Suit.HEART));
                           break;
                       case 3:
                           deck.add(new Card(k, Card.Suit.DIAMOND));
                           break;
                       case 4:
                           deck.add(new Card(k, Card.Suit.SPADE));
                           break;
                   }
               }
           }
        }
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    private LinkedList<Card> deck;

}
