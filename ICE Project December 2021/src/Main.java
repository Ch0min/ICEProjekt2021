import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

      //  StartMenu startMenu = new StartMenu();  // Klassen starter voes flow af programmet.
       // GameDesign gameDesign = new GameDesign();     // Skal ikke være her til sidst. Bruges kun til at lave Gamedesign så menuen ikke popper op først hele tiden.
        ResultsWindow rw = new ResultsWindow();    //   Samme som ^

    }
}


/* NOTER TIL NÆSTE GANG
- Pop result window, 'Knappen Pengecheck' skal åbne nyt vindue.       //done
- Få resultwindow op, når man har tabt.                               //done
- Når timeren rammer 0, skal der lyd på                                 // done
- Final answer lyd - delay efter man har har trykket et svar            // done
- resultwindow popup vindue skal designes
- pengebeløb popup vindue skal designes
- resultwindow skal der ny lyd til
- Check om hvis man får en million at pengecheck popup vindue kommer frem
- Højre side i gamedesign skal designes  /livslinjer, et træ af pengebeløb så man kan se hvor man er nået.
- millionwindow skal designes og laves lyd til
-
- Til sidst gør det mere OOP
 */