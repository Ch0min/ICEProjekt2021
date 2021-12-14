import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartMenu startMenu = new StartMenu();  // Klassen starter vores flow af programmet.

            }
        });
    }
}
