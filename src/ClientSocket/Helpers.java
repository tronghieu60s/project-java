package ClientSocket;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Helpers {
    
    public javax.swing.Icon getSwingIcon(int index) {
        int width = 120, height = 170;
        Image image = new ImageIcon(getClass().getResource("./icon/icon" + index + ".jpg")).getImage();
        javax.swing.Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
        return icon;
    }
    
    public void showMatrix(int x, int y, int[][] arr) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------");
        System.out.println();
    }
}
