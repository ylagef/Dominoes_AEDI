import java.util.LinkedList;


public class Player {
    String name;
    LinkedList<Token> hand = new LinkedList<>(); //Contains all the tokens on the hand of the player.
    boolean winner;

    public boolean getWinner(){
        return this.winner;
    }

    public void setWinner(boolean winner){
        this.winner=winner;
    }
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Token> getHand() {
        return hand;
    }

    public void setHand(LinkedList<Token> hand) {
        this.hand = hand;
    }

    public void printOptions() {

        System.out.printf( "FICHAS EN LA MANO (" + this.getHand().size() +"): ||   ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.printf(i + ") " + hand.get(i) + "    ");
        }

        System.out.print("||    C) COGER FICHA   P) PASAR TURNO");
    }

    private String printHand() {
        String handS = "";
        for (Token p : hand) {
            handS += " " + p.toString();
        }
        return handS;
    }

    @Override
    public String toString() {
        return "      "+name+" ..... "+ printHand() +" ..... "+ playerPoints();
    }

    public int playerPoints(){
        int points=0;
        LinkedList<Token> hand =getHand();
        for(Token t: hand){
            points+=t.getSide1();
            points+=t.getSide2();
        }
        return points;
    }
}
