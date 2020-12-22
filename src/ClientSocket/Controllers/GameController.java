package ClientSocket.Controllers;

import ClientSocket.Views.GameView;
import Helpers.Helpers;
import Helpers.Matrix;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameController {

    GameView gameView;

    // Function Support
    Helpers helpers = new Helpers();
    Matrix matrix = new Matrix();

    // Matrix
    boolean tick[][];
    int arrMatrix[][];

    // Components
    int time = 0;
    Timer timer, timeProcess;

    int count = 0, id;
    int objectX, objectY;
    int objectPreX, objectPreY;

    public GameController(GameView gameView) {
        int sizeXGame = gameView.getSizeXGame();
        int sizeYGame = gameView.getSizeYGame();

        this.gameView = gameView;
        this.tick = new boolean[sizeXGame][sizeYGame];
        this.arrMatrix = new int[sizeXGame][sizeYGame];

        for (int i = 0; i < sizeXGame; i++) {
            for (int j = 0; j < sizeYGame; j++) {
                // Handle Matrix
                arrMatrix[i][j] = (int) (Math.random() * 2 + 1);
                tick[i][j] = true;
                
                gameView.getBtnImage()[i][j].addActionListener(handleClickButton());
            }
        }

        // Create Matrix Random 2 Value With Size (X, Y)
        matrix.createMatrix(sizeXGame, sizeYGame, arrMatrix);
        // ShowMartrix For Debug
        matrix.showMatrix(sizeXGame, sizeYGame, arrMatrix);

        timer = new Timer(240, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleOpenImage();
                timer.stop();
            }
        });

        timeProcess = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time += 1;
                gameView.getProgressTime().setValue(gameView.getMaxTime() - time);
                if (gameView.getMaxTime() == time) {
                    timeProcess.stop();

                    // Handle Select Dialog For Re New Game
                    int select = JOptionPane.showOptionDialog(null, "Hết thời gian.\n"
                            + "Bạn có muốn chơi lại không?", "Thông báo",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            null, null);
                    if (select == 0) {
                        //handleNewGame();
                    } else {
                        System.exit(0);
                    }
                }
            }
        });
        
        gameView.setVisible(true);
    }

    public void handleOpenImage() {
        int X = objectX, Y = objectY;
        int preX = objectPreX, preY = objectPreY;

        if (id == arrMatrix[X][Y]) {
            // Set Image To Black
            gameView.getBtnImage()[preX][preY].setIcon(helpers.getSwingIcon(-1));
            arrMatrix[X][Y] = arrMatrix[preX][preY] = 0;

            tick[X][Y] = tick[preX][preY] = false;
            gameView.getBtnImage()[X][Y].setBorder(null);
            gameView.getBtnImage()[preX][preY].setBorder(null);

            gameView.getBtnImage()[X][Y].setIcon(helpers.getSwingIcon(-1));
        } else {
            tick[X][Y] = tick[preX][preY] = true;
            gameView.getBtnImage()[X][Y].setIcon(helpers.getSwingIcon(-2));
            gameView.getBtnImage()[preX][preY].setIcon(helpers.getSwingIcon(-2));
        }
    }

    public ActionListener handleClickButton() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeProcess.start();

                // Start Time and Handle Game
                int X, Y;
                String s = e.getActionCommand();
                int k = s.indexOf(32);
                X = Integer.parseInt(s.substring(0, k));
                Y = Integer.parseInt(s.substring(k + 1, s.length()));

                if (tick[X][Y]) {
                    gameView.getBtnImage()[X][Y].setIcon(helpers.getSwingIcon(arrMatrix[X][Y]));
                    if (count == 0) {
                        id = arrMatrix[X][Y];
                        objectPreX = X;
                        objectPreY = Y;
                    } else {
                        objectX = X;
                        objectY = Y;
                        timer.start();
                    }
                    count = 1 - count;
                    tick[X][Y] = false;
                }
            }
        };
    }
}
