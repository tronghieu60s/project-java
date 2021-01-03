package Client.Controllers;

import Client.Views.StartView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartController {
    public StartController(){
        StartView startView = new StartView();
        startView.getBtnEasy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GameController(3, 4, 300);
                } catch (IOException ex) {
                    Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        startView.getBtnNormal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GameController(3, 6, 300);
                } catch (IOException ex) {
                    Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        startView.getBtnHard().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GameController(4, 6, 600);
                } catch (IOException ex) {
                    Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        startView.getBtnInsane().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GameController(4, 6, 400);
                } catch (IOException ex) {
                    Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        startView.setVisible(true);
    }
}
