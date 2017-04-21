import java.util.LinkedList;


public class Player {
    String name;
    LinkedList<Piece> hand = new LinkedList<>(); //Contains all the tokens on the hand of the player.

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Piece> getHand() {
        return hand;
    }

    public void setHand(LinkedList<Piece> hand) {
        this.hand = hand;
    }

    public void printOptions() {
        //TODO Solo elegir las fichas que tiene. Opción de poner ficha. Opción de pasar turno.
        //System.out.println("Jugador " + name + ", seleccione su ficha:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.printf(i + ") " + hand.get(i) + "    ");
        }
    }

    private String printHand() {
        String handS = "";
        for (Piece p : hand) {
            handS += " " + p.toString();
        }
        return handS;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + " - " + printHand();
    }


}
