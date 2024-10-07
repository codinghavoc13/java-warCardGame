import java.util.Comparator;

public class CardPlayerSetComparator implements Comparator<CardPlayerSet>{
    @Override
    public int compare(CardPlayerSet setOne, CardPlayerSet setTwo){
        return Integer.compare(setOne.card.getFaceValue().rank, setTwo.card.getFaceValue().rank);
    }
    
}