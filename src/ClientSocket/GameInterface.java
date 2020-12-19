package ClientSocket;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class GameInterface extends JFrame implements ActionListener {

    // Function Support
    Helpers helpers = new Helpers();
    
    // Screen Base
    Container container;
    JPanel panelMain, panelAlert;

    // Size Game
    int sizeXGame = 3;
    int sizeYGame = 7;
    boolean tick[][] = new boolean[sizeXGame][sizeYGame];
    int arrMatrix[][] = new int[sizeXGame][sizeYGame];
    JButton btnImage[][] = new JButton[sizeXGame][sizeYGame];

    public GameInterface(int score) {
        this.setTitle("Game Lật Hình");
        initInterface(score);

        // Set Size, Properties And Show JFrame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(sizeYGame * 120, sizeXGame * 170 + 90);
        this.setVisible(true);
    }

    public void initInterface(int score) {
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

        // ShowMartrix For Debug
        helpers.showMatrix(sizeXGame, sizeYGame, arrMatrix);
        
        // Add Panel To Container
        newContainer.add(panelMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
