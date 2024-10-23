package com.codinghavoc.warcardgame;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/*TODO Consider converting the stream calls to parallelStreams in some cases, especially
 * if I want to expand the program to allow for large number of players and multiple
 * decks of cards
 */
public class WarEngine {
    private static int roundCount = 0;
    private static HashMap<String, Integer> statistics = new HashMap<>();
    private static int gameCount = 0;

    private static record Player(ArrayList<Card> hand, int id) {
    }

    /*
     * Look into converting deck/table/tempCardList and anything else
     * that store cards or cardplayersets into a Queue to keep cards in order
     */
    private static ArrayList<Card> deck;
    private static HashMap<Integer, Player> players;
    private static String[] suits = { "Heart", "Diamond", "Spade", "Club" };
    private static ArrayList<CardPlayerSet> table = new ArrayList<>();
    private static ArrayList<Card> tempCardList = new ArrayList<>();

    public static void buildDeck() {
        // System.out.println("Starting buildDeck()");
        deck = new ArrayList<>();
        for (FaceValue fv : FaceValue.values()) {
            for (String suit : suits) {
                deck.add(new Card(suit, fv));
            }
        }
        Collections.shuffle(deck);
    }

    public static void dealCards(int numberOfPlayers) {
        // System.out.println("Starting dealCards()");
        players = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.put(i, new Player(new ArrayList<>(), i));
        }
        int i = 0;
        for (Card card : deck) {
            players.get(i % numberOfPlayers).hand.add(card);
            i++;
        }
    }

    public static void displayStatistics(){
        statistics.entrySet().stream()
        .forEach(s->System.out.println("Player " + s.getKey() + " won " + s.getValue() + " times (" + displayWinPct(s.getValue()) + "%)"));
    }

    private static void evaluateCards(ArrayList<CardPlayerSet> tbl) {
        // System.out.println("Starting evaluateCards()");
        // tbl.stream().forEach(cps -> System.out.println(cps.toString()));
        tbl.stream().sorted(new CardPlayerSetComparator());
        List<CardPlayerSet> filtered = tbl.stream()
                .filter(cps -> cps.card.getRank() == tbl.stream()
                        .sorted(new CardPlayerSetComparator()).toList().get(0).getCardRank())
                .toList();

        if (filtered.size() > 1) {
            tempCardList.addAll(tbl.stream().map(c -> c.card).toList());
            tbl.clear();

            List<Player> removeThese = players.values().stream().filter(p -> p.hand.size() == 0).toList();

            List<Integer> removeTheseIds = removeThese.stream().map(r -> r.id).toList();
            List<Integer> filteredIds = filtered.stream().map(c -> c.playerID).toList();
            List<Integer> diff = new ArrayList<Integer>(filteredIds);
            diff.removeAll(removeTheseIds);

            if (diff.size() > 1) {
                war(new ArrayList<>(diff));
            }
        } else {
            tempCardList.addAll(tbl.stream().map(c -> c.card).toList());
            tbl.clear();

            players.get(filtered.get(0).playerID).hand.addAll(tempCardList);
            tempCardList.clear();
        }
        removePlayer();
    }

    public static void play() {
        boolean repeat = true;
        roundCount = 1;
        gameCount++;
        // System.out.println("Starting play()");
        while (repeat) {
            // System.out.println("Hand count at beginning of play for round " + roundCount);
            // players.entrySet().stream()
            //         .forEach(p -> System.out.println("Player " + p.getKey() + ": " + p.getValue().hand.size()));

            players.entrySet().stream().forEach(p -> {
                table.add(new CardPlayerSet(p.getValue().hand.get(0), p.getValue().id));
                p.getValue().hand.remove(0);
            });

            evaluateCards(table);

            table = new ArrayList<>();
            if (players.size() == 1) {
                // System.out.println("Player " + players.entrySet().stream().findFirst().get().getKey() + " wins after " + roundCount + " rounds");
                updateStatistics(players.entrySet().stream().findFirst().get().getKey().toString());
                repeat = false;
            }
            // try {
            //     Thread.sleep(1500);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            roundCount++;
            if(roundCount == 10000){
                // System.out.println("It's a draw");
                updateStatistics("Draw");
                repeat = false;
            }
        }
    }

    private static void removePlayer() {
        players.entrySet().removeIf(p -> p.getValue().hand.size() == 0);
    }

    private static void updateStatistics(String result){
        if(!statistics.containsKey(result)){
            statistics.put(result, 1);
        } else {
            statistics.put(result, statistics.get(result) + 1);
        }
    }

    public static void war(ArrayList<Integer> warParticipants) {
        // System.out.println("Starting war()");
        // System.out.println("Starting a war between the following: " + warParticipants);

        ArrayList<CardPlayerSet> warTable = new ArrayList<>();
        for (Integer i : warParticipants) {

            for (int j = 0; j < 3; j++) {
                if (players.get(i).hand.size() == 1) {
                    continue;
                }
                tempCardList.add(players.get(i).hand.get(0));
                players.get(i).hand.remove(0);
            }
            warTable.add(new CardPlayerSet(players.get(i).hand.get(0), i));
        }
    }

    public static void test() {
        System.out.println("Starting test");
        /*
         * stack the deck for 3 players
         */
        deck = new ArrayList<>();
        // first two cards are the same to trigger a war
        deck.add(new Card("Heart", FaceValue.KING));// this should be in player 0s hand at the end
        deck.add(new Card("Diamond", FaceValue.KING));// this should be in player 0s hand at the end
        deck.add(new Card("Diamond", FaceValue.TEN));// this should be in player 0s hand at the end
        // next six cards don't matter
        deck.add(new Card("TESTING 1", FaceValue.TWO));// this should be in player 0s hand at the end
        deck.add(new Card("TESTING 2", FaceValue.THREE));// this should be in player 0s hand at the end
        deck.add(new Card("TESTING 3", FaceValue.FOUR));
        deck.add(new Card("TESTING 4", FaceValue.FIVE));// this should be in player 0s hand at the end
        deck.add(new Card("TESTING 5", FaceValue.SIX));// this should be in player 0s hand at the end
        deck.add(new Card("TESTING 6", FaceValue.SEVEN));
        deck.add(new Card("TESTING 7", FaceValue.EIGHT));// this should be in player 0s hand at the end
        deck.add(new Card("TESTING 8", FaceValue.NINE));// this should be in player 0s hand at the end
        deck.add(new Card("TESTING 9", FaceValue.TEN));
        // two different cards to pick a winner
        deck.add(new Card("Spade", FaceValue.JACK));// this should be in player 0s hand at the end
        deck.add(new Card("Heart", FaceValue.QUEEN));// this should be in player 0s hand at the end
        deck.add(new Card("Diamond", FaceValue.ACE));

        dealCards(3);
        // for(Player player : players){
        // System.out.println(player.hand);
        // }

        play();
    }

    public static void printDeck(ArrayList<Card> cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public static ArrayList<Card> getDeck() {
        return deck;
    }

    private static String displayWinPct(int wins){
        return String.format("%.2f",(double)wins/(double)gameCount*100);
    }

}
