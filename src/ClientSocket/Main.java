package ClientSocket;

import ClientSocket.Controllers.GameController;
import ClientSocket.Views.GameView;

public class Main {

    public static void main(String[] args) throws Exception {
        GameView gameView = new GameView(3, 6, 1200);
        GameController gameController = new GameController(gameView);
    }
}
