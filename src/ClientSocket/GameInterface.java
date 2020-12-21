package ClientSocket;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public final class GameInterface extends JFrame implements ActionListener {

    // Function Support
    Helpers helpers = new Helpers();
    Matrix matrix = new Matrix();

    // Screen Base
    Container container;
    JPanel panelMain;

    // Size Game
    int sizeXGame = 3;
    int sizeYGame = 8;
    boolean tick[][] = new boolean[sizeXGame][sizeYGame];
    int arrMatrix[][] = new int[sizeXGame][sizeYGame];

    // Components
    Timer timer, timeProcess;
    JProgressBar progressTime;
    JButton btnImage[][] = new JButton[sizeXGame][sizeYGame];

    // Position
    int count = 0, hit = 0, id;
    int objectX, objectY;
    int objectPreX, objectPreY;
    int maxTime = 1200, time = 0;

    public GameInterface() {
        this.setTitle("Game Lật Hình");
        initInterface();

        // Set Size, Properties And Show JFrame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(sizeYGame * 120, sizeXGame * 170 + 60);
        this.setVisible(true);

        timer = new Timer(240, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleOpenImage();
                timer.stop();
            }
        });

        timeProcess = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time += 1;
                progressTime.setValue(maxTime - time);
                if (maxTime == time) {
                    timeProcess.stop();

                    // Handle Select Dialog For Re New Game
                    int select = JOptionPane.showOptionDialog(null, "Hết thời gian.\n"
                            + "Bạn có muốn chơi lại không?", "Thông báo",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            null, null);
                    if (select == 0) {
                        handleNewGame();
                    } else {
                        System.exit(0);
                    }
                }
            }
        });
    }

    public void initInterface() {
        // Init Container
        Container newContainer = this.getContentPane();
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(sizeXGame, sizeYGame));

        // Render Image For Game [sizeXGame, sizeYGame]
        for (int i = 0; i < sizeXGame; i++) {
            for (int j = 0; j < sizeYGame; j++) {
                btnImage[i][j] = new JButton();
                panelMain.add(btnImage[i][j]);

                // Handle btnImage
                btnImage[i][j].setActionCommand(i + " " + j);
                btnImage[i][j].addActionListener(this);
                btnImage[i][j].setBackground(Color.black);
                btnImage[i][j].setIcon(helpers.getSwingIcon(0));

                // Handle Another
                arrMatrix[i][j] = (int) (Math.random() * 2 + 1);
                tick[i][j] = true;
            }
        }

        // Process Bar
        progressTime = new JProgressBar(0, maxTime);
        progressTime.setValue(maxTime);
        progressTime.setForeground(Color.red);

        // Create Matrix Random 2 Value With Size (X, Y)
        matrix.createMatrix(sizeXGame, sizeYGame, arrMatrix);
        // ShowMartrix For Debug
        matrix.showMatrix(sizeXGame, sizeYGame, arrMatrix);

        // Add To Container
        newContainer.add(panelMain);
        newContainer.add(progressTime, "North");
    }

    public void handleOpenImage() {
        int X = objectX, Y = objectY;
        int preX = objectPreX, preY = objectPreY;
        if (id == arrMatrix[X][Y]) {
            // Set Image To Black
            btnImage[preX][preY].setIcon(helpers.getSwingIcon(-1));
            arrMatrix[X][Y] = arrMatrix[preX][preY] = 0;

            tick[X][Y] = tick[preX][preY] = false;
            btnImage[X][Y].setBorder(null);
            btnImage[preX][preY].setBorder(null);

            btnImage[X][Y].setIcon(helpers.getSwingIcon(-1));
            hit++;
            if (hit == sizeXGame * sizeYGame / 2) {
                timer.stop();
                timeProcess.stop();
            }
        } else {
            tick[X][Y] = tick[preX][preY] = true;
            btnImage[X][Y].setIcon(helpers.getSwingIcon(0));
            btnImage[preX][preY].setIcon(helpers.getSwingIcon(0));
        }
    }

    public void handleNewGame() {
        this.dispose();
        new GameInterface();
    }

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
            btnImage[X][Y].setIcon(helpers.getSwingIcon(arrMatrix[X][Y]));
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
}
