package controller;

import model.Card;
import model.Dealer;
import model.Deck;
import model.Player;

import java.util.Scanner;

/**
 * Created by Phil on 3/20/2018.
 */
public class Blackjack {

    private static Deck discard;
    private static Deck deck;
    private static Dealer dealer;
    private static Player playerOne;
    private static int count;
    private static double wager;

    public static void main(String[] args){
        System.out.println("Welcome to blackjack!");
        System.out.println("Dealer stands on all 17s");
        System.out.println("Using 4 decks. Decks reshuffle at 50%");
        System.out.println("Type \"start\" to begin.");
        System.out.println("Bankroll: $1000");
        discard = new Deck();
        deck = new Deck(2);
        deck.shuffle();
        dealer = new Dealer();
        playerOne = new Player();
        count = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("start")){
            System.out.println("Type \"start\" to begin.");
            input = scanner.nextLine();
        }
        play();
    }

    private static void play() {
        boolean playing = true;
        while(playing) {
            start();
            boolean done = false;
            boolean split = false;
            boolean firstMove = true;
            printBoard(false);
            if (playerOne.getScore() == 21 && dealer.getScore() != 21) {
                System.out.println("Blackjack!");
                playerOne.setBankroll(playerOne.getBankroll() + wager + (wager * 1.5));
            }
            else if (playerOne.getScore() == 21 && dealer.getScore() == 21) {
                System.out.println("Player and dealer have blackjack! Push!");
                playerOne.setBankroll(playerOne.getBankroll() + wager);
            }
            else {
                if (dealer.getScore() == 21) {
                    System.out.println("Dealer has blackjack!");
                    done = true;
                }
                while (playerOne.getScore() < 21 && !done) {
                    System.out.println("Type \"hit\" or \"stand\" or \"double\" or \"split\"");
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    switch (input) {
                        case "hit":
                            dealToPlayer(deck.deal());
                            firstMove = false;
                            printBoard(false);
                            break;
                        case "stand":
                            done = true;
                            break;
                        case "double":
                            if (!firstMove) {
                                break;
                            }
                            dealToPlayer(deck.deal());
                            playerOne.setBankroll(playerOne.getBankroll() - wager);
                            wager *= 2;
                            printBoard(false);
                            split = true;
                            done = true;
                            break;
                        case "split":
                            if (playerOne.getHand().size() != 2) break;
                            if (playerOne.getHand().get(0).getBlackjackValue() != playerOne.getHand().get(1).getBlackjackValue()) break;
                            split();
                            done = true;
                            break;
                        default:
                            break;
                    }
                }
                /*if (split) {

                }*/
                if (playerOne.getScore() > 21) {
                    System.out.println("Player busted!");
                    printBoard(true);
                }
                else {
                    while (dealer.getScore() < 17) {
                        dealToDealer(deck.deal());
                    }
                    printBoard(true);
                    if (dealer.getScore() > 21) {
                        System.out.println("Dealer busts. You win!");
                        playerOne.setBankroll(playerOne.getBankroll() + (2 * wager));
                    }
                    else if (dealer.getScore() == playerOne.getScore()) {
                        System.out.println("Push!");
                        playerOne.setBankroll(playerOne.getBankroll() + wager);
                    }
                    else if (dealer.getScore() > playerOne.getScore()) {
                        System.out.println("The dealer beat you!");
                    }
                    else {
                        System.out.println("You win!");
                        playerOne.setBankroll(playerOne.getBankroll() + (2 * wager));
                    }
                }
            }
            System.out.println("Count: " + count);
            System.out.println();
            System.out.println("Play again? (yes/quit)");
            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();
            if(in.equals("quit")){
                playing = false;
            }
            else {
                cleanUpTable();
            }
        }
    }

    private static void split() {

    }

    private static void start() {
        if (discard.getDeck().size() >= deck.getDeck().size()*2) {
            shuffle();
            count = 0;
        }
        System.out.println("Bankroll: " + playerOne.getBankroll());
        System.out.print("Enter wager: ");
        Scanner sc = new Scanner(System.in);
        wager = sc.nextDouble();
        playerOne.setBankroll(playerOne.getBankroll() - wager);
        dealToPlayer(deck.deal());
        dealToDealer(deck.deal());
        dealToPlayer(deck.deal());
        dealToDealer(deck.deal());
    }

    private static void shuffle() {
        System.out.println("Shuffling deck");
        System.out.println("Shuffling deck");
        System.out.println("Shuffling deck");
        System.out.println("Shuffling deck");
        System.out.println("Shuffling deck");
        while(!discard.getDeck().isEmpty()) {
            deck.getDeck().add(discard.deal());
        }
        deck.shuffle();
    }

    private static void printBoard(boolean dealerTurn) {
        System.out.println();
        System.out.println("------------------------------------");
        if(dealerTurn){
            for (Card card : dealer.getHand()) {
                System.out.print(card.toString() + "\t");
            }
            System.out.println();
            System.out.println("Dealer score: " + dealer.getScore());
        }
        else {
            System.out.println(dealer.getHand().get(0).toString());
            System.out.println("Dealer score: " + dealer.getHand().get(0).getBlackjackValue());
        }
        System.out.println();
        System.out.println();
        for (Card card : playerOne.getHand()){
            System.out.print(card.toString()+"\t");
        }
        System.out.println();
        System.out.println("Player score: " + playerOne.getScore());
        System.out.println("------------------------------------");
        System.out.println();
    }

    private static void dealToPlayer(Card card) {
        playerOne.getHand().add(card);
        if(card.getBlackjackValue() > 9) count -= 1;
        else if(card.getBlackjackValue() < 7) count += 1;
    }

    private static void dealToDealer(Card card) {
        dealer.getHand().add(card);
        if(card.getBlackjackValue() > 9) count -= 1;
        else if(card.getBlackjackValue() < 7) count += 1;
    }

    private static void cleanUpTable() {
        while(!playerOne.getHand().isEmpty()) {
            discard.getDeck().add(playerOne.getHand().remove(0));
        }
        while(!dealer.getHand().isEmpty()) {
            discard.getDeck().add(dealer.getHand().remove(0));
        }
    }

}
