package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerServer extends Thread {

    private String username;
    private PlayerServer opponent;

    // SOCKET JAVA
    private Socket serverSocket;
    private BufferedReader inFromClient;
    private PrintWriter outToClient;

    public void setOpponent(PlayerServer opponent) {
        this.opponent = opponent;
    }

    public PlayerServer(Socket socket, GameServer gameServer, String username) {
        this.serverSocket = socket;
        this.username = username;

        try {
            inFromClient = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            outToClient = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("User " + username + " connected!");
            outToClient.println(gameServer.getSizeXGame());
            outToClient.println(gameServer.getSizeYGame());

            for (int i = 0; i < gameServer.getSizeXGame(); i++) {
                for (int j = 0; j < gameServer.getSizeYGame(); j++) {
                    outToClient.println(gameServer.getArrMatrix()[i][j]);
                }
            }
        } catch (IOException e) {
            System.out.println("Player " + opponent.username + " out game!");
        }
    }

    public void run() {
        try {
            outToClient.println("Another player connected!");

            // Listening Socket
            while (true) {
                if (serverSocket != null) {
                    String msg = "";
                    while ((msg = inFromClient.readLine()) != null) {
                        opponent.outToClient.println(msg);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
