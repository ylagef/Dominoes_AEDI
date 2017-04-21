import java.util.LinkedList;


public class Board { //TODO Renombrar todos los Board a GameBoard

    private LinkedList<Piece> boardPieces = new LinkedList<>(); //Atribute that contains the tokens placed on the game board.

    public Board() {
    }

    public LinkedList<Piece> getBoardPieces() {

        return boardPieces;
    }

    public void setBoardPieces(LinkedList<Piece> boardPieces) {

        this.boardPieces = boardPieces;
    }

    public void printBoard() { //TODO Integrar en la interfaz gráfica.
        System.out.printf("\n****************************************************************************\n");
        System.out.printf("                               TABLERO DE JUEGO\n");
        System.out.printf("****************************************************************************\n\n");
        for (Piece p : boardPieces) {
            System.out.printf(p.toString() + " ");
        }
        System.out.printf("\n\n****************************************************************************\n");
        System.out.printf("****************************************************************************\n");
    }

    public boolean checkPiece(Piece piece, char side) {
        /*Method that checks if a piece is allowed to be placed in the board.*/

        if (boardPieces.size() == 0) {
            return true;
        } else {
            if(side=='x'){ //Only in the first turn of the game.
                return (piece.getSide1() == boardPieces.getFirst().getSide1() || piece.getSide2() == boardPieces.getFirst().getSide1()) || (piece.getSide1() == boardPieces.getLast().getSide2() || piece.getSide2() == boardPieces.getLast().getSide2());
            }
            else{
            if (side == 'I' ) {
                if (piece.getSide1() == boardPieces.getFirst().getSide1() || piece.getSide2() == boardPieces.getFirst().getSide1()) {
                    return true;
                }
            } else {
                if (side == 'D') {
                    if (piece.getSide1() == boardPieces.getLast().getSide2() || piece.getSide2() == boardPieces.getLast().getSide2()) {
                        return true;
                    }
                }
            }
            return false;
            }
        }
    }

    public void placePiece(Piece piece, char side) {
        /*Method thath places a piece in the table. Need the checkPiece method to be used before. */

//TODO Simplificar la elección de lado con una letra solo por lado.
        if (boardPieces.size() == 0) {
            boardPieces.add(piece);
            return;
        }

        if (side == 'I') {
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
