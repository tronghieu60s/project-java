package ClientSocket.Views;

import Helpers.Helpers;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public final class GameView extends JFrame implements ActionListener {

    // Function Support
    Helpers helpers = new Helpers();
    
    // Screen Base
    Container container;
    JPanel panelMain;

    // Size Game
    int maxTime;
    private int sizeXGame;
    private int sizeYGame;
    
    // Components
    JProgressBar progressTime;
    JButton btnImage[][];

    public int getSizeXGame() {
        return sizeXGame;
    }

    public void setSizeXGame(int sizeXGame) {
        this.sizeXGame = sizeXGame;
    }

    public int getSizeYGame() {
        return sizeYGame;
    }

    public void setSizeYGame(int sizeYGame) {
        this.sizeYGame = sizeYGame;
    }

    public JProgressBar getProgressTime() {
        return progressTime;
    }

    public void setProgressTime(JProgressBar progressTime) {
        this.progressTime = progressTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public JButton[][] getBtnImage() {
        return btnImage;
    }

    public void setBtnImage(JButton[][] btnImage) {
        this.btnImage = btnImage;
    }
    
    // Constructor
    public GameView(int sizeXGame, int sizeYGame, int maxTime) {
        this.sizeXGame = sizeXGame;
        this.sizeYGame = sizeYGame;
        this.maxTime = maxTime;
        this.btnImage = new JButton[sizeXGame][sizeYGame];
        
        initInterface();

        // Set Size, Properties And Show JFrame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(sizeYGame * 120, sizeXGame * 170 + 60);
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
                btnImage[i][j].setIcon(helpers.getSwingIcon(-2));
            }
        }

        // Process Bar
        progressTime = new JProgressBar(0, maxTime);
        progressTime.setValue(maxTime);
        progressTime.setForeground(Color.red);

        // Add To Container
        newContainer.add(panelMain);
        newContainer.add(progressTime, "North");
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
