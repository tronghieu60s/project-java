package ServerSocket;

import Helpers.Matrix;
import java.io.*;
import java.net.ServerSocket;

public class ServerMain {

    private Matrix matrix = new Matrix();

    private ServerSocket serverSocket;
    private Thread serverThread;

    public static void main(String[] args) throws IOException {
        ServerMain main = new ServerMain();
    }

    public ServerMain() {
        try {
            this.serverSocket = new ServerSocket(Helpers.ConfigSocket.port);
        } catch (IOException ex) {
        }
        serverThread = new Thread(serverAccept);
        serverThread.start();
    }

    public Runnable serverAccept = new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("Server is running...");
                while (true) {
                    GameServer game = new GameServer();

                    matrix.createMatrix(game.getSizeXGame(), game.getSizeYGame(), game.getArrMatrix());
                    matrix.showMatrix(game.getSizeXGame(), game.getSizeYGame(), game.getArrMatrix());

                    PlayerServer player1 = new PlayerServer(serverSocket.accept(), game, "test1");
                    PlayerServer player2 = new PlayerServer(serverSocket.accept(), game, "test2");
                    player1.setOpponent(player2);
                    player2.setOpponent(player1);

                    player1.start();
                    player2.start();
                }
            } catch (IOException e) {
                System.out.println("Server already exists...");
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }
            }
        }
    };
}
