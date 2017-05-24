import java.util.LinkedList;


public class Player {
    private String name;
    private LinkedList<Token> hand = new LinkedList<>(); //Contains all the tokens on the hand of the player.
    private boolean isWinner; //True if the player is the winner.


    Player(String name) {
        this.name = name;
        this.isWinner = false;
    }

    String getName() {
        return name;
    }

    void setHand(LinkedList<Token> hand) {
        this.hand = hand;
    }

    void printOptions() { //Prints the current options

        System.out.printf("FICHAS EN LA MANO (" + handSize() + "): ||   ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.printf(i + ") " + hand.get(i) + "    ");
        }

        System.out.print("||    C) COGER FICHA   P) PASAR TURNO");
    }

    private String printHand() { //Prints the player's tokens
        String handStr = "";
        for (Token p : hand) {
            handStr += " " + p.toString();
        }
        return handStr;
    }

    @Override
    public String toString() {
        return "      " + name + " .................... " + printHand() + " .................... " + playerPoints();
    }

    boolean getIsWinner() {
        return this.isWinner;
    }

    void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    int playerPoints() { //Calulates the total points
        int points=0;
        for(Token t: hand){
            points+=t.getSide1();
            points+=t.getSide2();
        }
        return points;
    }

    int handSize() {
        return this.hand.size();
    }

    Token getToken(int pieceToPlay) {
        return hand.get(pieceToPlay);
    }

    void removeToken(Token tokenToRemove) {
        hand.remove(tokenToRemove);
    }

    void addTokenToHand(Token token) {
        hand.add(token);
    }
}