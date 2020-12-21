package Helpers;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Helpers {
    public javax.swing.Icon getSwingIcon(int index) {
        int width = 120, height = 170;
        Image image = new ImageIcon(getClass().getResource("/ClientSocket/Views/Images/icon" + index + ".jpg")).getImage();
        javax.swing.Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
        return icon;
    }
}
