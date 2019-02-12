import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame window = new MainWindow("Calendar");
            window.setVisible(true);
        });
    }
}
