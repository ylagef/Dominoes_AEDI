
public class Piece { //TODO Renombrar todos los piece por Token

    private int Side1;
    private int Side2;

    public Piece(int Side1, int Side2) {
        this.Side1 = Side1;
        this.Side2 = Side2;
    }

    public int getSide1() {
        return Side1;
    }

    public void setSide1(int Side1) {
        this.Side1 = Side1;
    }

    public int getSide2() {
        return Side2;
    }

    public void setSide2(int Side2) {
        this.Side2 = Side2;
    }

    @Override
    public String toString() {
        return "[" + Side1 + "|" + Side2 + "]";
    }
}
