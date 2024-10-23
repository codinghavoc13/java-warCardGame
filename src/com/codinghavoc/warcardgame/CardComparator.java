package com.codinghavoc.warcardgame;
import java.util.Comparator;

public class CardComparator implements Comparator<Card>{
    @Override
    public int compare(Card cardOne, Card cardTwo){
        return Integer.compare(cardOne.getFaceValue().rank, cardTwo.getFaceValue().rank);
    }
    
}
