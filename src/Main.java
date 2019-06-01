import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set Swing Look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Construct with title
        GUI_Frame App = new GUI_Frame("cab302 Vector Design Tool");

        //Run
        SwingUtilities.invokeLater(App);
    }
}
