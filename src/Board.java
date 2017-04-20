import java.util.LinkedList;

/**
 * Created by Yeray on 20/04/2017.
 */
public class Board {

    private LinkedList<Piece> boardPieces = new LinkedList<>();

    public Board() {
    }

    public LinkedList<Piece> getBoardPieces() {
        return boardPieces;
    }

    public void setBoardPieces(LinkedList<Piece> boardPieces) {
        this.boardPieces = boardPieces;
    }

    public void printBoard() {
        System.out.printf("\n****************************************************************************\n");
        for (Piece p : boardPieces) {
            System.out.printf(p.toString() + " ");
        }
        System.out.printf("\n****************************************************************************\n");
    }
}
