
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class Game {

    private LinkedList<Token> heap = new LinkedList<>();
    private Board board = new Board();
    private Player handPlayer;
    private LinkedList<Player> players = new LinkedList<>();

    public void start() {
        System.out.printf("\n\n\n\n ---------------------------------------------------------------- DOMINO ------------------------------------------------------------------");
        System.out.printf("\n\n/* Equipo de proyecto formado por: Yeray Lage Freitas, Martin Puga Egea y Luis Rodriguez Cudeiro */");
        System.out.printf("\n\n\n\n### AJUSTES ###: \n\n\t·Para una buena experiencia de juego, ajustar el tamaño de ventana del terminal a la linea discontinua mostrada a continuación y el título del juego.\n\n\t·Una vez ajustado el tamaño de pantalla, pulsar ENTER.\n\n");

        System.out.println("\n\n\n\n\n\n\n ------------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scn = new Scanner(System.in);
        scn.nextLine();
        setPlayers();
        initializePieces();
        play();
    }

    private void play() {

        Scanner scn = new Scanner(System.in);

        int pieceToPlay = 0;
        String optionString;
        String sideString;
        char side, option;
        boolean error = false, emptyBoard = true, takeTokenfromHeap;
        int actualPlayer = 0;

        
        /* Closed Game (Tool)
        LinkedList<Token> closedGame = new LinkedList<>();
        closedGame.add(new Token(1, 1));
        closedGame.add(new Token(1, 1));
        closedGame.add(new Token(1, 1));
        closedGame.add(new Token(1, 1));        
        closedGame.add(new Token(2, 2));
        closedGame.add(new Token(2, 2));
        closedGame.add(new Token(2, 2));
        closedGame.add(new Token(2, 6));
        Metan la ficha 6:2
        board.setBoardTokens(closedGame);
        -----------------------------------*/


        while (!emptyPlayer() && !board.closedEdges()) {
            if (actualPlayer == players.size()) {//The game continues until one player is out of pieces or both sides of the gameboard has been closed with tokens placed 7 times..

                actualPlayer = 0; //Round linked list
            }
            System.out.printf("\n\n                                                               >>>>>>>>>>>> TURNO DEL JUGADOR: " + players.get(actualPlayer).getName() + " <<<<<<<<<<<\n\n");

            takeTokenfromHeap = false;
            do {
                error = false;

                board.printBoard();
                System.out.printf("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                System.out.printf("                                                                                     ACCIONES\n");
                System.out.printf("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");

                players.get(actualPlayer).printOptions();
                System.out.printf("\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                System.out.printf("\t>>> Seleccione una accion: ");

                optionString = scn.nextLine();
                if (numberOrString(optionString) && optionString.length() != 0) { //If it's a number
                    pieceToPlay = Integer.parseInt(optionString);
                    if (pieceToPlay >= 0 && pieceToPlay < players.get(actualPlayer).handSize()) {
                        Token token = players.get(actualPlayer).getToken(pieceToPlay);
                        side = 'x';
                        if (board.checkPiece(token, side) && !emptyBoard) { //In the first turn of the game, there is no side placement election.
                            System.out.printf("\t>>> Seleccione el lado donde desea colocar la ficha (I/D): ");
                            sideString = scn.nextLine();
                            side = sideElectionToCharUppercase(sideString);
                            System.out.printf("\n\n");

                            if (sideCorrectChar(side) && sideString.length() == 1) {
                                if (board.checkPiece(token, side)) { //The token is been placed on the game board and removed from the player's hand.
                                    board.placePiece(token, side);
                                    players.get(actualPlayer).removeToken(token);
                                    takeTokenfromHeap = false;
                                    error = false;
                                } else {
                                    error = true;
                                    System.out.printf("\n###  NO ES POSIBLE COLOCAR LA FICHA EN EL LADO ELEGIDO  ###\n\n");
                                }
                            } else {
                                error = true;
                                System.out.printf("\n###  LADO MAL ELEGIDO. INTRODUCIR I|D  ###\n\n");
                            }

                        } else {
                            if (emptyBoard) {
                                board.placePiece(token, 'X');
                                players.get(actualPlayer).removeToken(token);
                                emptyBoard = false;
                                error = false;
                                takeTokenfromHeap = false;
                            } else {
                                error = true;
                                System.out.printf("\n\n\n\n\n ###  NO ES POSIBLE COLOCAR LA FICHA ELEGIDA  ###\n\n");
                            }
                        }
                    } else {
                        error = true;
                        System.out.printf("\n\n\n\n\n ###  ACCION MAL ELEGIDA  ###\n\n");
                    }

                } else { //It's a string
                    if (optionString.length() != 0) {

                        option = optionString.toUpperCase().charAt(0);

                        switch (option) {
                            case 'C': //Take a token from the stack to the hand.
                                if (board.firstTime()) {
                                    System.out.printf("\n\n###  NO PUEDE COGER FICHA EN EL PRIMER TURNO.  ###\n\n");
                                    error = true;
                                } else {
                                    if (!heap.isEmpty() && !takeTokenfromHeap) { //Take a token from the heap it's only possible if it's not empty.
                                        players.get(actualPlayer).addTokenToHand(heap.pop());
                                        takeTokenfromHeap = true;
                                        System.out.printf("\n\n>>>>>  HA COGIDO FICHA  \n\n");
                                    } else {
                                        if (takeTokenfromHeap) {
                                            System.out.printf("\n\n###  NO PUEDE COGER MAS FICHAS. YA HA COGIDO UNA EN ESTE TURNO.  ###\n\n");
                                        } else {
                                            System.out.printf("\n\n###  NO QUEDAN FICHAS PARA PODER COGER  ###\n\n");
                                        }
                                    }
                                    error = true;
                                }
                                break;
                            case 'P': //Pass the turn.
                                if (board.firstTime()) {
                                    System.out.printf("\n\n###  NO PUEDE PASAR EN EL PRIMER TURNO.  ###\n\n");
                                    error = true;
                                } else {
                                    if (!heap.isEmpty()) {
                                        if (!takeTokenfromHeap) {
                                            System.out.printf("\n\n###  NO ES POSIBLE PASAR TURNO. TIENE QUE COGER FICHA  ###\n\n");
                                            error = true;
                                        }
                                    }
                                    takeTokenfromHeap = false;
                                }
                                break;

                            default:
                                error = true;
                                System.out.printf("\n\n###  ACCION MAL ELEGIDA  ###\n\n");
                        }
                    } else {
                        error = true;
                        System.out.printf("\n\n###  ACCION MAL ELEGIDA  ###\n\n");
                    }
                }

            } while (error || takeTokenfromHeap);

            actualPlayer++; //End of the turn. Next player.
        }

        /* //Comprobación de empate (Tool)
        
        LinkedList<Token> hand0 = new LinkedList<>();
        hand0.add(new Token(6, 4));
        players.get(1).setHand(hand0);

        LinkedList<Token> hand1 = new LinkedList<>();
        hand1.add(new Token(4, 6));
        players.get(2).setHand(hand1);
        //-----------------------------------------------------*/
        endGame(); //The results are shown and the game end.
    }

    private boolean emptyPlayer() {
        /*Check if any player is without pieces*/
        for (Player p : players) {
            if (p.handSize() == 0) {
                return true;
            }
        }
        return false;
    }

    private void initializePieces() {
        //Create tokens

        int row = 1;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < row; j++) {
                Token token = new Token(i, j);
                heap.add(token);
            }
            row++;
        }

        //Shuffle tokens
        Collections.shuffle(heap);

        //Distribute tokens
        for (int i = 0; i < players.size(); i++) {
            // LinkedList<Token> hand = players.get(i).getHand();
            LinkedList<Token> hand = new LinkedList<>();
            for (int j = 0; j < 7; j++) {
                hand.add(heap.getFirst());
                heap.removeFirst();
            }
            players.get(i).setHand(hand);
        }
    }

    private void setPlayers() {

        /*Sets number of players and creates them.*/
        int numberOfPlayers = 0, ascii;
        boolean error;
        String line;

        System.out.println("******************* JUGADORES *******************\n");

        Scanner scn = new Scanner(System.in);
        char numberOfPlayersChar;

        do {
            error = false;
            System.out.printf("\t>>> Numero de jugadores (2, 3 o 4): "); //Ask for the number of players. They must be from  2 to 4 players.
            line = scn.nextLine();

            if (numberOrString(line) && line.length() == 1) { //Check that the user select 2, 3 or 4 players. Repeat until correct election.
                numberOfPlayersChar = line.charAt(0);
                ascii = (int) numberOfPlayersChar;
                switch (ascii) { //ASCII 2=50, 3=51, 4=52.
                    case 50:
                        numberOfPlayers = 2;
                        break;
                    case 51:
                        numberOfPlayers = 3;
                        break;
                    case 52:
                        numberOfPlayers = 4;
                        break;
                    default:
                        System.out.print("Default");
                        System.out.printf("###   EL NÚMERO DE JUGADORES DEBE  SER 2, 3 o 4   ###\n\n");
                        error = true;
                }
            } else {
                System.out.printf("### EL NÚMERO DE JUGADORES DEBE  SER 2, 3 o 4   ###\n\n");
                error = true;
            }
        } while (error);

        System.out.printf("\n");
        String playerName;

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.printf("\t>>> Nombre del jugador " + i + ": ");

            playerName = scn.nextLine();

            Player player = new Player(playerName); //Creates the player with the information provided.
            if (i == 1) {
                handPlayer = player;
            }
            players.add(player);
        }
        System.out.printf("\n\n\n");
    }

    private char sideElectionToCharUppercase(String side) {
        char sideChar;
        side = side.toUpperCase();
        sideChar = side.charAt(0);
        return sideChar;
    }

    private boolean sideCorrectChar(char c) {

        /* Checks if the player has selected left or right */
        return (c == 'I' || c == 'D');
    }

    private boolean numberOrString(String line) {

        /*Checks if the option selected only contains number characters. Returns true if "line" is a number. Returns false if "line" is a String*/
        boolean isNumber = true;
        int i = 0, c, length = line.length();

        while (isNumber && length > 0) {
            c = (int) line.charAt(i); //Convert char to ASCII.
            if (c < 48 || c > 57) { //ASCII 0=48, 9=57.
                isNumber = false;
            } else {
                i++;
                length--;
            }
        }
        return isNumber;
    }

    private void endGame() {
        //End of the game
        System.out.printf("\n\n                                                                     >>>>>>>>>>>> FIN DEL JUEGO <<<<<<<<<<<\n");
        board.printBoard();
        System.out.println();
        System.out.printf("      JUGADOR ............... FICHAS ............... PUNTOS\n\n");
        if (!board.closedEdges()) { //The game ends because one player has his hand empty of tokens.
            for (Player p : players) {
                System.out.print(p);
                if (p.handSize() == 0) {
                    System.out.println(" [  GANADOR  ]");
                } else {
                    System.out.println();
                }
                System.out.println();
            }
        } else { //The game ends because both edges has been closed.

            boolean handWinner = false; //This flag is true when the hand player wins.
            int bestPlayerPoints = 1000;//Setted to 1000 because it's an impossible score.

            for (Player p : players) { //Selects one of the best players and his points.
                if (p.playerPoints() < bestPlayerPoints) {
                    // bestPlayer = p;
                    bestPlayerPoints = p.playerPoints();
                }
            }
            for (Player p : players) { //Compares all the players with the best points to check if somebody has the same (Tie).
                if (p.playerPoints() == bestPlayerPoints) { //if (!p.equals(bestPlayer) && p.playerPoints() == bestPlayerPoints) { //Change to if (p.playerPoints() == bestPlayerPoints)
                    p.setIsWinner(true);
                }
            }
            for (Player p : players) {
                if (p.getIsWinner() && p.equals(handPlayer)) {   //if (!p.equals(bestPlayer) && p.getWinner() && p.equals(isHand)) { //Check if any of the winners is hand. //Change to if (p.getWinner() && p.equals(isHand)) {
                    //  bestPlayer = p;
                    handWinner = true;
                }
            }
            if (handWinner) { //The hand player wins.
                for (Player p : players) {
                    System.out.print(p);
                    if (p.equals(handPlayer)) {
                        System.out.println(" [  GANADOR  ]");
                    } else {
                        System.out.println();
                    }
                }
            } else { //Shows the best players.
                for (Player p : players) {
                    System.out.print(p);
                    if (p.getIsWinner()) {
                        System.out.println(" [ GANADOR ]");
                    } else {
                        System.out.println();
                    }
                }
            }
        }
    }
}
