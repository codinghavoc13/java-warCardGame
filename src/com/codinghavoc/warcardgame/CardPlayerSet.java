package com.codinghavoc.warcardgame;
public class CardPlayerSet {
    public Card card;
    public int playerID;
    public CardPlayerSet(Card card, int playerID) {
        this.card = card;
        this.playerID = playerID;
    }
    @Override
    public String toString() {
        return "CardPlayerSet [card=" + card + ", playerID=" + playerID + "]";
    }

    public FaceValue getCardFaceValue(){
        return card.getFaceValue();
    }

    public int getCardRank(){
        return card.getRank();
    }
    
}
