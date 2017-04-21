import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    LinkedList<Piece> heap = new LinkedList<>();
    Board board = new Board();
    Player isHand;
    LinkedList<Player> players = new LinkedList<>();

    public void start() {
        System.out.printf("\t\t\t\t\tBienvenido al Dominó.");

        //TODO Añadir relleno de presentacion.
        //TODO Diseño de la interfaz gráfica.
        setPlayers();
        initializePieces();

        /*for (Player p : players) {
            System.out.printf(p.toString()+"\n");
        }*/
        play();
    }


    private void play() {

        Scanner scn = new Scanner(System.in);

        int pieceToPlay = 0;
        String sideString;
        char side;
        boolean error = false;
        int actualPlayer = 0;

        while (!emptyPlayer() && !count7Pieces()) { //The game continues until one player is out of pieces or one number has been placed 7 times in the two sides of the gameboard.

            if (actualPlayer == players.size()) actualPlayer = 0; //Round linked list

            System.out.printf("\n\n               >>>>>>>>>>>> TURNO DEL JUGADOR: " + players.get(actualPlayer).getName() + " <<<<<<<<<<<\n");


            do {
                board.printBoard();
                System.out.printf("\n****************************************************************************\n");
                System.out.printf("                                  MANO\n");
                System.out.printf("****************************************************************************\n\n");
                players.get(actualPlayer).printOptions();
                System.out.printf("\n\n****************************************************************************\n");
                System.out.printf("Seleccione una opción: ");
                pieceToPlay = scn.nextInt();
                Piece piece = players.get(actualPlayer).getHand().get(pieceToPlay);
                side = 'x';
                if (board.checkPiece(piece, side)) {
                    System.out.printf("Seleccione el lado (I/D): "); //TODO No mostrar en la primera jugada.
                    scn.nextLine();
                    side = sideElectionTocharUppercase(scn.nextLine()); //TODO Comprobar las entradas.
                    if (sideCorrectchar(side)) {
                        if (board.checkPiece(piece, side)) { //The token is been placed on the game boar and removed from the player's hand.
                            board.placePiece(piece, side);
                            players.get(actualPlayer).getHand().remove(piece);
                            error = false;
                        } else {
                            error = true;
                            System.out.printf("\nNO ES POSIBLE COLOCAR LA FICHA EN EL LADO ELEGIDO.\n");
                        }
                    } else {
                        System.out.printf("\nLADO MAL ELEGIDO. INTRODUCIR I|D.\n");
                    }

                } else {
                    error = true;
                    System.out.printf("\nNO ES POSIBLE COLOCAR LA FICHA ELEGIDA.\n");
                }
            } while (error);

            actualPlayer++; //End of the turn. Next player.
        }

        System.out.printf("GAME OVER PENDEJOS."); //TODO Mostrar mesa, manos de los jugadores, ganador y puntuaciones.
    }

    private boolean emptyPlayer() {
        /*Check if any player is without pieces*/
        for (Player p : players) {
            if (p.hand.size() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean count7Pieces() {
/*Checks if some number placed on a side has been played 7 times.*/
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
        //Create tokens

        int row = 1;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < row; j++) {
                Piece piece = new Piece(i, j);
                heap.add(piece);
            }
            row++;
        }

        //Shuffle tokens
        Collections.shuffle(heap);

        //Distribute tokens
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

        System.out.printf("\n\nIntroduzca el número de jugadores y pulse intro.\n\n");
        System.out.printf("El número de jugadores debe ser 2,3 ó 4.\n\n");

        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.printf("Introduzca el número de jugadores: ");

            Scanner scn = new Scanner(System.in);
            numberOfPlayers = scn.nextInt();//TODO Comprobar entrada de datos.

            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.printf("ERROR: EL NÚMERO DE JUGADORES INTRODUCIDO NO ES CORRECTO.\n\n");
            }
        }

        //Create players
        String playerName;
//TODO Comprobar que lo jugadores tienen nombres distintos.
        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.printf("Introduzca el nombre del jugador " + i + ": ");

            Scanner scn = new Scanner(System.in);
            playerName = scn.nextLine();

            Player player = new Player(playerName);
            players.add(player);
        }

    }

    private char sideElectionTocharUppercase(String side) {
        char sideChar;
        side = side.toUpperCase();
        sideChar = side.charAt(0);
        return sideChar;
    }

    private boolean sideCorrectchar(char c) {
        return (c == 'I' || c == 'D');
    }
}

