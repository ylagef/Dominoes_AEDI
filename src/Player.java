import java.util.LinkedList;


public class Player {
    private String name;
    private LinkedList<Token> hand = new LinkedList<>(); //Contains all the tokens on the hand of the player.
    private boolean isWinner; //True if the player is the winner.


    public Player(String name) {
        this.name = name;
        this.isWinner = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHand(LinkedList<Token> hand) {
        this.hand = hand;
    }

    public void printOptions() { //Prints the current options

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

    public boolean getIsWinner() {
        return this.isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public int playerPoints() { //Calulates the total points
        int points=0;
        for(Token t: hand){
            points+=t.getSide1();
            points+=t.getSide2();
        }
        return points;
    }

    public int handSize() {
        return this.hand.size();
    }

    public Token getToken(int pieceToPlay) {
        return hand.get(pieceToPlay);
    }

    public void removeToken(Token tokenToRemove) {
        hand.remove(tokenToRemove);
    }

    public void addTokenToHand(Token token) {
        hand.add(token);
    }
}