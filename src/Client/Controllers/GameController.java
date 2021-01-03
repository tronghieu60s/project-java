package Client.Controllers;

import Client.Views.GameView;
import Helpers.Helpers;
import Helpers.Matrix;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameController {

    // Function Support
    private Helpers helpers = new Helpers();
    private Matrix matrix = new Matrix();

    // MVC
    private GameView gameView;

    // Matrix
    private boolean tick[][];
    private int arrMatrix[][];

    // Components
    private int sizeXGame = 0, sizeYGame = 0;
    private int time = 0;
    private Timer timer, timeProcess;

    private int count = 0, hit = 0, id;
    private int objectX, objectY;
    private int objectPreX, objectPreY;

    public GameController(int sizeXGame, int sizeYGame, int timeGame) throws IOException {
        this.sizeXGame = sizeXGame;
        this.sizeYGame = sizeYGame;

        this.gameView = new GameView(sizeXGame, sizeYGame, timeGame);
        this.tick = new boolean[sizeXGame][sizeYGame];
        this.arrMatrix = new int[sizeXGame][sizeYGame];

        matrix.createMatrix(sizeXGame, sizeYGame, arrMatrix);
        matrix.showMatrix(sizeXGame, sizeYGame, arrMatrix);

        for (int i = 0; i < sizeXGame; i++) {
            for (int j = 0; j < sizeYGame; j++) {
                tick[i][j] = true;
                gameView.getBtnImage()[i][j].addActionListener(handleClickButton());
            }
        }

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
                        gameView.dispose();
                        new StartController();
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

            hit++;
            if (hit == sizeXGame * sizeYGame / 2) {
                timer.stop();
                timeProcess.stop();
                int select = JOptionPane.showOptionDialog(null, "Chiến thắng!\n"
                        + "Bạn có muốn chơi lại không?", "Thông báo",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        null, null);
                if (select == 0) {
                    gameView.dispose();
                    new StartController();
                } else {
                    System.exit(0);
                }
            }
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
