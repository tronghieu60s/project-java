package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerServer extends Thread {

    String username;
    PlayerServer opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public void setOpponent(PlayerServer opponent) {
        this.opponent = opponent;
    }

    public PlayerServer(Socket socket, GameServer gameServer, String username) {
        this.socket = socket;
        this.username = username;

        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("USER CONNECT: " + username);
            output.println(gameServer.getSizeXGame());
            output.println(gameServer.getSizeYGame());
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    public void run() {
        output.println("MESSAGE All is connected");
        if (username == "test1") {
            output.println("MESSAGE First turn");
        }
        
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}
