import javax.swing.*;

public class MillionWindow {
    JFrame resultsMillionFrame;

    public MillionWindow() {
        this.rewardMillion();
        
    }
    
    


    private void rewardMillion() {








// Results Frame Design.
        resultsMillionFrame = new JFrame();
        resultsMillionFrame.setTitle("RESULTAT: Hvem Vil Være Millionær?");
        resultsMillionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultsMillionFrame.setLayout(null);
        resultsMillionFrame.setResizable(false);
        resultsMillionFrame.setSize(1200, 800);   // Egentlige størrelse?
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        resultsMillionFrame.setIconImage(logo.getImage());
    }
}

