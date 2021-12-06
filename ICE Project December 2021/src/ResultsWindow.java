import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsWindow extends JPanel {
    JFrame resultsFrame;
    SoundDesign resultsSound;

    public ResultsWindow() {
        this.reward();
        resultsSound = new SoundDesign("Soundeffects/1000000-music.wav");
     //   resultsSound.play();
     //   resultsSound.loop();

    }

    private void reward() {











// Results Frame Design.
        resultsFrame = new JFrame();
        resultsFrame.setTitle("RESULTAT: Hvem Vil Være Millionær?");
        resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultsFrame.setLayout(null);
        resultsFrame.setResizable(false);
        resultsFrame.setSize(1200, 800);   // Egentlige størrelse?
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        resultsFrame.setIconImage(logo.getImage());
        resultsFrame.setVisible(true);
    }

}
