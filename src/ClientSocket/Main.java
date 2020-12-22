package ClientSocket;

import ClientSocket.Controllers.GameController;
import ClientSocket.Views.GameView;
import Helpers.ConfigSocket;
import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception {
        GameView gameView = new GameView(3, 7, 1200);
        GameController gameController = new GameController(gameView);
    }
}
