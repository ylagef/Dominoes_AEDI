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

    public boolean checkPiece(Piece piece, String side) {
        if (boardPieces.size() == 0) return true;

        if (side == "i" || side == "I") {
            if (piece.getSide1() == boardPieces.getFirst().getSide1() || piece.getSide2() == boardPieces.getFirst().getSide1()) {
                return true;
            }
        } else {
            if (piece.getSide1() == boardPieces.getLast().getSide2() || piece.getSide2() == boardPieces.getLast().getSide2()) {
                return true;
            }
        }
        return false;
    }

    public void placePiece(Piece piece, String side) {

        if (boardPieces.size() == 0) {
            boardPieces.add(piece);
            return;
        }

        if (side == "i" || side == "I") {
            if (piece.getSide2() != boardPieces.getFirst().getSide1()) {
                Piece aux = new Piece(piece.getSide2(), piece.getSide1());
                piece = aux;
            }
            boardPieces.addFirst(piece);
        } else {
            if (piece.getSide1() != boardPieces.getLast().getSide2()) {
                Piece aux = new Piece(piece.getSide2(), piece.getSide1());
                piece = aux;
            }
            boardPieces.addLast(piece);
        }
    }
}
