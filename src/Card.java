public class Card {
    // private String faceValue;
    private String suit;
    // private int rank;
    private FaceValue faceValue;

    public Card(String suit, FaceValue faceValue) {
        this.suit = suit;
        this.faceValue = faceValue;
    }
    
    public FaceValue getFaceValue() {
        return faceValue;
    }

    public int getRank(){
        return faceValue.rank;
    }
    
    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card [faceValue=" + faceValue + ", suit=" + suit + "]";
    }

    
}
