import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
    private List<Player> players = new ArrayList<>();
    private Deck deck;
    private Player dealer;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.setupGame();
        game.playGame();
        game.determineWinners();
    }

    private void setupGame() {
        System.out.println("How many decks do you want to use (2, 4, 6)?");
        int numberOfDecks = scanner.nextInt();
        while (numberOfDecks != 2 && numberOfDecks != 4 && numberOfDecks != 6) {
            System.out.println("Invalid number of decks. Please enter 2, 4, or 6:");
            numberOfDecks = scanner.nextInt();
        }

        System.out.println("How many players are there (1-6)?");
        int numberOfPlayers = scanner.nextInt();
        while (numberOfPlayers < 1 || numberOfPlayers > 6) {
            System.out.println("Invalid number of players. Please enter a number between 1 and 6:");
            numberOfPlayers = scanner.nextInt();
        }

        deck = new Deck(numberOfDecks);
        dealer = new Player(true);
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(false));
        }

        // Deal two cards to each player and the dealer
        players.forEach(p -> {
            p.addCard(deck.draw());
            p.addCard(deck.draw());
        });
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());

        // Show players' hands and dealer's partial hand
        players.forEach(Player::printHand);
        System.out.println("Dealer's initial hand:");
        dealer.printPartialHand(); // Only show the dealer's first card and hide the second
    }

    private void playGame() {
        for (Player player : players) {
            System.out.println("\nPlayer's turn:");
            player.printHand();
            while (true) {
                System.out.println("Do you want to hit or stand? (hit/stand)");
                String action = scanner.next();
                if (action.equalsIgnoreCase("hit")) {
                    player.addCard(deck.draw());
                    player.printHand();
                    if (player.getHandValue() > 21) {
                        System.out.println("Player busts!");
                        break;
                    }
                } else if (action.equalsIgnoreCase("stand")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please type 'hit' or 'stand'.");
                }
            }
        }

        System.out.println("\nDealer's turn:");
        dealer.printHand(); // Now show the full hand
        while (dealer.getHandValue() < 17) {
            dealer.addCard(deck.draw());
            dealer.printHand();
            if (dealer.getHandValue() > 21) {
                System.out.println("Dealer busts!");
                break;
            }
        }
    }

    private void determineWinners() {
        int dealerValue = dealer.getHandValue();
        System.out.println("\nFinal results:");
        System.out.println("Dealer's hand value: " + dealerValue);
        for (Player player : players) {
            int playerValue = player.getHandValue();
            System.out.println("Player's hand value: " + playerValue);
            if (playerValue > 21) {
                System.out.println("Player busts! Dealer wins.");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                System.out.println("Player wins!");
            } else if (playerValue == dealerValue) {
                System.out.println("Push! It's a tie.");
            } else {
                System.out.println("Dealer wins.");
            }
        }
    }
}
