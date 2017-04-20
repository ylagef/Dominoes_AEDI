import java.util.LinkedList;

/**
 * Created by Yeray on 20/04/2017.
 */
public class Player {
    String name;
    LinkedList<Piece> hand = new LinkedList<>();

    public Player(String name, LinkedList<Piece> hand) {
        this.name = name;
        this.hand = hand;
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
}
