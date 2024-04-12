import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private boolean isDealer = false;

    public Player(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getHandValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            int cardValue = card.getValue();
            if (card.getRank().equals("Ace")) {
                aces++;
            }
            value += cardValue;
        }
        while (value > 21 && aces > 0) {
            value -= 10; // Count Ace as 1 instead of 11
            aces--;
        }
        return value;
    }

    public void printHand() {
        hand.forEach(System.out::println);
        System.out.println("Total value: " + getHandValue());
    }

    public void printPartialHand() {
        if (!hand.isEmpty()) {
            System.out.println(hand.get(0)); // Only show the first card
            System.out.println("One card hidden");
        }
    }

    public boolean isDealer() {
        return isDealer;
    }
}
