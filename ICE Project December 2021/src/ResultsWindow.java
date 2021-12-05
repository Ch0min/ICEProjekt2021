import javax.swing.*;

public class ResultsWindow {
    JFrame resultsFrame;
    SoundDesign resultsSound;

    ResultsWindow() {
        this.reward();
        resultsSound = new SoundDesign("Soundeffects/1000000-music");
        resultsSound.play();
        resultsSound.loop();

    }

    public void reward() {








// Results Frame Design.
        resultsFrame = new JFrame();
        resultsFrame.setTitle("HOVEDMENU: Hvem Vil Være Millionær?");
        resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultsFrame.setLayout(null);
        resultsFrame.setResizable(false);
        resultsFrame.setSize(1200, 800);   // Egentlige størrelse?
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        resultsFrame.setIconImage(logo.getImage());
    }
}
