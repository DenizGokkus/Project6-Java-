import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(int numDecks) {
        cards = new ArrayList<>();
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

        for (int i = 0; i < numDecks; i++) {
            for (String suit : suits) {
                for (String rank : ranks) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }
}
