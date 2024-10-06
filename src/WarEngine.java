import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class WarEngine {

    private record Player(ArrayList<Card> hand) {
    }

    private record CardPlayerSet(Card card, int playerSpot) {
    }

    private ArrayList<Card> deck;
    private ArrayList<Player> players;
    private String[] suits = { "Heart", "Diamond", "Spade", "Club" };
    private ArrayList<CardPlayerSet> table = new ArrayList<>();
    private ArrayList<Card> tempCardList = new ArrayList<>();

    public void buildDeck() {
        System.out.println("Starting buildDeck()");
        deck = new ArrayList<>();
        // for (int i = 0; i < faceValues.length; i++) {
        //     for (int j = 0; j < suits.length; j++) {
        //         deck.add(new Card(faceValues[i], suits[j], i + 1));
        //     }
        // }
        for(FaceValue fv : FaceValue.values()){
            // System.out.println(fv.toString());
            for(String suit : suits){
                deck.add(new Card(suit, fv));
            }
        }
        // deck.stream().forEach(c -> System.out.println(c.toString()));
        Collections.shuffle(deck);
    }

    public void dealCards(int numberOfPlayers) {
        System.out.println("Starting dealCards()");
        players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(new ArrayList<>()));
        }
        int i = 0;
        for (Card card : deck) {
            players.get(i % numberOfPlayers).hand.add(card);
            i++;
        }
        // printDeck(players.get(0).hand);
    }

    private void evaluateCards(ArrayList<CardPlayerSet> tbl) {
        System.out.println("Starting evaluateCards()");
        System.out.println(tbl.size());
        tbl.stream()
            // .sor
            .forEach(c -> System.out.println(c.card.toString()));

        //'original' logic
        // ArrayList<CardPlayerSet> highCards = new ArrayList<>();
        // for (CardPlayerSet cps : tbl) {
        //     // if no high card has been set
        //     if (highCards.size() == 0) {
        //         highCards.add(cps);
        //     } else {
        //         // if cps has a higher rank than high card
        //         if (cps.card.getRank() < highCards.get(0).card.getRank()) {
        //             // remove card from highCards and add cps
        //             highCards.clear();
        //             highCards.add(cps);
        //         }
        //         // if cps has the same rank as high card
        //         else if (cps.card.getRank() == highCards.get(0).card.getRank()) {
        //             highCards.add(cps);
        //         }
        //     }
        // }
        // if(highCards.size()==1){
        //     for(CardPlayerSet cps : tbl){
        //         players.get(highCards.get(0).playerSpot).hand.add(cps.card);
        //     }
        // }
        // if(highCards.size()>1){
        //     System.out.println("WAR!!!");
        //     //put the cards on the table into a temp list
        //     for(CardPlayerSet cps : tbl){
        //         tempCardList.add(cps.card);
        //     }
        //     ArrayList<Integer> warParticipants = new ArrayList<>();
        //     for(CardPlayerSet cps : highCards){
        //         warParticipants.add(cps.playerSpot);
        //     }
        // }
    }

    public void play() {
        System.out.println("Starting play()");
        for (Player player : players) {
            table.add(new CardPlayerSet(player.hand.get(0), players.indexOf(player)));
            player.hand.remove(0);
        }
        evaluateCards(table);
        //clear the table for the next round
        table = new ArrayList<>();
    }

    public void war(ArrayList<Integer> warParticipants){
        /*
         * I'm thinking of creating a new warTable list plus a down facing list (need a name),
         * then deal a card into warTable and pass that table to the evaluate method
         */
        ArrayList<CardPlayerSet> warTable = new ArrayList<>();
        for(Integer i : warParticipants){
            //need to add a check to make sure the player has at least 4 cards
            for(int j = 0; j<3;j++){
                tempCardList.add(players.get(i).hand.get(0));
                players.get(i).hand.remove(0);
            }
            players.get(i).hand.get(0);
        }
    }

    public void test() {
        System.out.println("Starting test");
        Card cardOne = deck.get(0);
        Card cardTwo = deck.get(1);
        System.out.println("Card 1: " + cardOne.toString());
        System.out.println("Card 2: " + cardTwo.toString());
        ArrayList<Card> temp = new ArrayList<>();
        temp.add(cardOne);
        temp.add(cardTwo);
        Comparator comparator = new CardComparator();
        temp.stream().sorted(comparator)
        .forEach(c -> System.out.println(c.toString()));
    }

    public void printDeck(ArrayList<Card> cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

}
