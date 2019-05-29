import javax.swing.*;
import java.awt.*;

public class JButton extends javax.swing.JButton {

    public JButton(JPanel panel, String buttonText, String fileName) {
        super(buttonText);
        String filePath = "./img/buttons/" + fileName;
        try{
            ImageIcon icon = new ImageIcon(filePath);
            this.setIcon(icon);
        } catch (Exception e) {
            //Dialog box showing Error
            JOptionPane.showMessageDialog(panel,
                    "Error in loading image: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        //initializes buttons and sets them to newBtn
        //sets size of button
        this.setPreferredSize(new Dimension(100, 60));
        // Format text position
        this.setVerticalTextPosition(AbstractButton.BOTTOM);
        this.setHorizontalTextPosition(AbstractButton.CENTER);
    }
}
