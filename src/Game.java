import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Yeray on 20/04/2017.
 */
public class Game {

    public void start() {

        System.out.printf("\t\t\t\t\tBienvenido al Dominó.");
        setPlayers();
        initializePieces();
    }

    private void initializePieces() {
        //Create, shuffle and first hand out pieces.
        LinkedList<Piece> pieces = new LinkedList<>();
        int row = 1;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < row; j++) {
                Piece piece = new Piece(i, j);
                pieces.add(piece);
            }
            row++;
        }

        //Shuffle pieces
        Collections.shuffle(pieces);


    }

    private void setPlayers() {
        //Set number of players, create them and set order.

        int numberOfPlayers = 0;

        System.out.printf("\n\nPara iniciar una nueva partida introduzca el número de jugadores y pulse intro.\n\n");
        System.out.printf("El número de jugadores debe ser 2,3 ó 4.\n\n");

        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.printf("Introduzca el número de jugadores: ");

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

            Player player = new Player(i, playerName);
            players.put(player);
        }

        //Shuffle players order
        players.shuffle();
    }


}