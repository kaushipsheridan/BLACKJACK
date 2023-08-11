/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.blackjackgame;
import java.util.Scanner;

public class BlackJackGame {

   
    public void start(){
        Scanner scanner = new Scanner(System.in);
        String nickname;
        String input;

        System.out.println("Welcome to Blackjack Game, coded by: Priyanshu Kaushik, Yashika Saini, Jashanpreet Kaur, Brian Singh");

        System.out.println("Rules of the Game:");
        System.out.println("1. The goal of the game is to get a hand value as close to 21 as possible without exceeding it.");
        System.out.println("2. Face cards (Jack, Queen, King) are worth 10 points. Number cards are worth their face value.");
        System.out.println("3. Ace can be worth either 1 or 11 points, depending on which value benefits the hand more.");
        System.out.println("4. The dealer deals two cards to each player. Players can see their own cards and one of the dealer's cards.");
        System.out.println("5. Players can choose to 'hit' (get another card) or 'stand' (keep their current hand).");
        System.out.println("6. Players can continue to 'hit' until they decide to 'stand' or their hand value exceeds 21.");
        System.out.println("7. After all players have played, the dealer reveals their second card.");
        System.out.println("8. The dealer must 'hit' until their hand value is 15 or less.");
        System.out.println("9. The player with a hand value closest to 21 without exceeding it wins. If a player's hand value exceeds 21, they lose.");
        
        
        
        System.out.println("");
        System.out.print("First Player Name: ");
        input = scanner.nextLine();
        nickname = input;

        int roundsPlayed = 0; // Counter for rounds played
        int playerTotalScore = 0; //Counter for player's score
        int dealerTotalScore = 0; //Counter for Dealer's score

        // mainloop
        do {
            roundsPlayed++;

            // new game message
            System.out.println();
            System.out.println("A new game has begun. Round " + roundsPlayed);
            System.out.println();

            
            Player player = new Player(nickname);
            Player dealer = new Player("Dealer");
            Deck deck = new Deck();
            deck.shuffle();
            boolean gameOver = false;

            // give cards to the player
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            System.out.println(player.getHandAsString(false));

            // give cards to the dealer
            dealer.addCard(deck.draw());
            dealer.addCard(deck.draw());
            System.out.println(dealer.getHandAsString(true));

            // player's turn
            do {
                System.out.print("Would " + player.getNickname() + " like to Hit or stay? 'Hit/Stay' ");
                do {
                    input = scanner.nextLine();
                } while (!input.equalsIgnoreCase("Hit") && !input.equalsIgnoreCase("Stay"));

                // BUST
                if (input.equalsIgnoreCase("Hit")) {
                    player.addCard(deck.draw());
                    System.out.println(player.getNickname() + " drew a card. Sum is "+player.getHandSum());
                    System.out.println();
                    System.out.println(player.getHandAsString(false));
                    if (player.getHandSum() > 21) {
                        System.out.println(
                                "You busted and got a total of " + player.getHandSum() + ". Dealer wins this time!");
                        dealerTotalScore++;
                        gameOver = true;
                    }
                }
                // STAY
                if (input.equalsIgnoreCase("stay")) {
                    System.out.println("You have chosen to stay. Your hand: " + player.getHandSum());
                }

            } while (input.equalsIgnoreCase("hit") && !gameOver);

            // dealer's turn
            if (!gameOver) {
                System.out.println();
                System.out.println("- Dealers turn -");
                System.out.println();
                System.out.println(dealer.getHandAsString(false));
            }

            while (!gameOver) {

                if (dealer.getHandSum() <= 15) {
                    // DRAW CARD
                    dealer.addCard(deck.draw());
                    System.out.println(dealer.getNickname() + " drew another card. Sum is " +dealer.getHandSum() );
                    System.out.println();
                    System.out.println(dealer.getHandAsString(false));
                    if (dealer.getHandSum() == 15) {
                        System.out.println("Blackjack! Dealer won.");
                        playerTotalScore++;
                        gameOver = true;
                    }
                    if (dealer.getHandSum() > 21) {
                        System.out.println("Dealer busted and got a total of " + dealer.getHandSum() + ". "
                                + player.getNickname() + " wins this time!");
                        playerTotalScore++;
                        gameOver = true;
                    }

                } else {
                    // STAY
                    System.out.println("Dealer has chosen to stay!");
                    System.out.println();
                    int totalDealerSum = dealer.getHandSum();
                    int totalPlayerSum = player.getHandSum();

                    if (totalDealerSum > totalPlayerSum) {
                        System.out.println("Both players have decided to stay. The winner is " + dealer.getNickname()
                                + " with a total of " + totalDealerSum + ".");
                        dealerTotalScore++;
                    } else {
                        System.out.println("Both players have decided to stay. The winner is " + player.getNickname()
                                + " with a total of " + totalPlayerSum + ".");
                        playerTotalScore++;
                    }
                    gameOver = true;
                }

            }

            if (roundsPlayed >= 5) {
                // Determine and display the final winner
                if (playerTotalScore > dealerTotalScore) {
                    System.out.println(player.getNickname() + " is the final winner with a total score of " + playerTotalScore);
                } else if (dealerTotalScore > playerTotalScore) {
                    System.out.println("Dealer is the final winner with a total score of " + dealerTotalScore);
                } else {
                    System.out.println("It's a tie! Both players have the same total score of " + playerTotalScore);
                }
                break; // Exit the loop and end the game
            }

            // ask for new game
            System.out.println();
            System.out.print("Would you like to play another round? 'Yes/No':  ");
            do {
                input = scanner.nextLine();
            } while (!input.equalsIgnoreCase("Yes") && !input.equalsIgnoreCase("No"));

        } while (input.equalsIgnoreCase("Yes"));

        // tidy up
        scanner.close();
    }
        
         public static void main(String[] args) {
             BlackJackGame b = new BlackJackGame();
             b.start();
             
    }
}
