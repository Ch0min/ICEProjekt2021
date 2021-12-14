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
          //GameDesign gameDesign = new GameDesign();     // Skal ikke være her til sidst. Bruges kun til at lave Gamedesign så menuen ikke popper op først hele tiden.
        //DataGameDesign d = new DataGameDesign();     // Skal ikke være her til sidst. Bruges kun til at lave Gamedesign så menuen ikke popper op først hele tiden.

    }
}
