import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Yeray on 20/04/2017.
 */
public class Game {
    LinkedList<Piece> heap = new LinkedList<>();
    Board board = new Board();
    Player isHand;
    LinkedList<Player> players = new LinkedList<>();

    public void start() {
        System.out.printf("\t\t\t\t\tBienvenido al Dominó.");

        //TODO Añadir relleno de presentacion

        setPlayers();
        initializePieces();

        /*for (Player p : players) {
            System.out.printf(p.toString()+"\n");
        }*/
        play();
    }


    private void play() {
        Scanner scn = new Scanner(System.in);
        int pieceToAdd = 0;
        String side = "";
        boolean err = false;

        while (!emptyPlayer() || !count7Pieces()) {
            int actualPlayer = 0;

            board.printBoard();
            players.get(actualPlayer).printOptions();

            //TODO Comprobar que las entradas estén bien formateadas.

            while (err) {
                err = false;
                pieceToAdd = scn.nextInt();
                Piece piece = players.get(actualPlayer).getHand().get(pieceToAdd);

                System.out.printf("Seleccione el lado: (I/D)");
                scn.nextLine();
                side = scn.nextLine();

                if (!board.checkPiece(piece, side)) {
                    err = true;
                    System.out.printf("ERROR, VUELVA A INTRODUCIR LOS DATOS.\n");
                } else {
                    board.placePiece(piece, side);
                    players.get(actualPlayer).getHand().remove(piece);
                }
            }

            actualPlayer++;
        }
        System.out.printf("GAME OVER PENDEJOS.");
    }

    private boolean emptyPlayer() {
        for (Player p : players) {
            if (p.hand.size() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean count7Pieces() {

        if (board.getBoardPieces().size() == 0) return false;

        int first, last;
        int firstCount = 0, lastCount = 0;
        first = board.getBoardPieces().getFirst().getSide1();
        last = board.getBoardPieces().getLast().getSide2();

        for (Piece p : heap) {
            if (p.getSide1() == first || p.getSide2() == first) {
                firstCount++;
            } else if (p.getSide1() == last || p.getSide2() == last) {
                lastCount++;
            }
        }

        return firstCount == 7 || lastCount == 7;
    }

    private void initializePieces() {
        //Create pieces

        int row = 1;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < row; j++) {
                Piece piece = new Piece(i, j);
                heap.add(piece);
            }
            row++;
        }

        //Shuffle pieces
        Collections.shuffle(heap);

        //First hand out of heap pieces
        for (int i = 0; i < players.size(); i++) {
            LinkedList<Piece> hand = players.get(i).getHand();
            for (int j = 0; j < 7; j++) {
                hand.add(heap.getFirst());
                heap.removeFirst();
            }
        }
    }

    private void setPlayers() {
        //Set number of players and create them.

        int numberOfPlayers = 0;

        System.out.printf("\n\nPara iniciar una nueva partida introduzca el número de jugadores y pulse intro.\n\n");
        System.out.printf("El número de jugadores debe ser 2,3 ó 4.\n\n");

        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.printf("Introduzca el número de jugadores: ");

            //TODO Comprobar que no metan caralladas

            Scanner scn = new Scanner(System.in);
            numberOfPlayers = scn.nextInt();

            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.printf("EL NÚMERO DE JUGADORES NO ES CORRECTO.\n\n");
            }
        }

        //Create players
        String playerName;

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.printf("Introduzca el nombre del jugador " + i + ": ");

            Scanner scn = new Scanner(System.in);
            playerName = scn.nextLine();

            Player player = new Player(playerName);
            players.add(player);
        }

    }
}
