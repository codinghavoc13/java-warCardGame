public class WarCardGameMain {

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 1; i++){
            run();
        }
        WarEngine.displayStatistics();
    }

    private static void run(){
        WarEngine.buildDeck();
        WarEngine.dealCards(4);
        WarEngine.play();
    }
    
}
