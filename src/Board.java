
import java.util.LinkedList;

public class Board { //TODO Renombrar todos los Board a GameBoard

    private LinkedList<Token> boardTokens;  //Atribute that contains the tokens placed on the game board.

    public Board() {
        this.boardTokens = new LinkedList<>();
    }

    public LinkedList<Token> getBoardTokens() {

        return boardTokens;
    }

    public void setBoardTokens(LinkedList<Token> boardTokens) {

        this.boardTokens = boardTokens;
    }

    public void printBoard() {
        System.out.printf("\n****************************************************************************************************************************************************************************\n");
        System.out.printf("                                                                                 TABLERO DE JUEGO\n");
        System.out.printf("****************************************************************************************************************************************************************************\n\n");
        for (Token p : boardTokens) {
            System.out.printf(p.toString() + " ");
        }
        System.out.printf("\n\n****************************************************************************************************************************************************************************\n");
    }

    public boolean checkPiece(Token token, char side) {
        /*Method that checks if a token is allowed to be placed in the board.*/

        if (boardTokens.isEmpty()) {
            return true;
        } else {
            if (side == 'x') { //Only in the first turn of the game.
                return (token.getSide1() == boardTokens.getFirst().getSide1() || token.getSide2() == boardTokens.getFirst().getSide1()) || (token.getSide1() == boardTokens.getLast().getSide2() || token.getSide2() == boardTokens.getLast().getSide2());
            } else {
                if (side == 'I') {
                    if (token.getSide1() == boardTokens.getFirst().getSide1() || token.getSide2() == boardTokens.getFirst().getSide1()) {
                        return true;
                    }
                } else {
                    if (side == 'D') {
                        if (token.getSide1() == boardTokens.getLast().getSide2() || token.getSide2() == boardTokens.getLast().getSide2()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }

    public void placePiece(Token token, char side) {
        /*Method thath places a token in the table. Need the checkPiece method to be used before. */

        switch (side) {
            case 'I':
                if (token.getSide2() != boardTokens.getFirst().getSide1()) { //Turns the token numbers
                    Token aux = new Token(token.getSide2(), token.getSide1());
                    token = aux;
                }
                boardTokens.addFirst(token);
                break;
            case 'D':
                if (token.getSide1() != boardTokens.getLast().getSide2()) { //Turns de token numbers
                    Token aux = new Token(token.getSide2(), token.getSide1());
                    token = aux;
                }
                boardTokens.addLast(token);
                break;
            case 'X': //In case of empty table
                boardTokens.add(token);
                break;
        }

    }

    public boolean closedEdges() {
        /*Method that checks if both sides has been closed with numbers that has been placed 8 times.*/

        if (this.getBoardTokens().isEmpty()) {
            return false;
        } else {
            int leftNumber, rightNumber;
            int leftCount = 0, rightCount = 0;
            leftNumber = this.getBoardTokens().getFirst().getSide1();
            rightNumber = this.getBoardTokens().getLast().getSide2();

            for (Token p : this.getBoardTokens()) {
                rightCount += p.rightCount(rightNumber);
                leftCount += p.leftCount(leftNumber);
            }
            return leftCount == 8 && rightCount == 8;
        }
    }

    public boolean firstTime() {
        return boardTokens.isEmpty();
    }
}
