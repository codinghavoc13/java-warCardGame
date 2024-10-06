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
    public void setFaceValue(FaceValue faceValue) {
        this.faceValue = faceValue;
    }
    // public String getFaceValue() {
    //     return faceValue;
    // }
    // public void setFaceValue(String faceValue) {
    //     this.faceValue = faceValue;
    // }
    public String getSuit() {
        return suit;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    // public int getRank() {
    //     return rank;
    // }
    // public void setRank(int rank) {
    //     this.rank = rank;
    // }
    @Override
    public String toString() {
        return "Card [faceValue=" + faceValue + ", suit=" + suit + "]";
    }

    
}
