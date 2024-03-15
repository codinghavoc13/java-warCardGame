import java.util.ArrayList;

public class WarCardGameMain {

    public static void main(String[] args) throws Exception {
        WarEngine we = new WarEngine();
        we.buildDeck();
        // we.printDeck(we.getDeck());
        we.dealCards(4);
        we.play();
        // we.test();
    }
    
}
