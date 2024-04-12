public class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        switch (rank) {
            case "Ace":
                return 11;
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                return Integer.parseInt(rank);
            case "Jack":
            case "Queen":
            case "King":
                return 10;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
