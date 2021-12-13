import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsWindowTest {
    JFrame resultsFrame;
    SoundDesign resultsSound;

    public ResultsWindowTest() {
        this.reward();
    }

    private void reward() {
        SoundDesign reward = new SoundDesign("Soundeffects/intromain.wav");
        reward.play();
        reward.loop();

// Result Panels
        // Left side design
        ImageIcon lf1Icon = new ImageIcon("Gifs/f1top.gif");
        JLabel lf1 = new JLabel();
        lf1.setIcon(lf1Icon);

        ImageIcon lf2Icon = new ImageIcon("Gifs/f1bot.gif");
        JLabel lf2 = new JLabel();
        lf2.setIcon(lf2Icon);

        ImageIcon hansRIcon = new ImageIcon("Pictures/goldthumbs.png");
        JLabel hansR = new JLabel();
        hansR.setIcon(hansRIcon);

        JPanel resultPanelTopLeft = new JPanel();
        resultPanelTopLeft.setBackground(new Color(0, 0, 23));
        resultPanelTopLeft.setBounds(0, 0, 300, 250);

        JPanel resultPanelCentralLeft = new JPanel();
        resultPanelCentralLeft.setBackground(new Color(0,0,23));
        resultPanelCentralLeft.setBounds(0, 250, 300, 270);
        
        JPanel resultPanelBottomLeft = new JPanel();
        resultPanelBottomLeft.setBackground(new Color(0, 0, 23));
        resultPanelBottomLeft.setBounds(0, 515, 300, 250);

        // Central side design
        ImageIcon check = new ImageIcon("Pictures/rewardcheck.png");
        JLabel checkLabel = new JLabel();
        checkLabel.setText("1.000.000");
        checkLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        checkLabel.setHorizontalTextPosition(JLabel.CENTER);
        checkLabel.setIcon(check);
        checkLabel.setVerticalAlignment(JLabel.CENTER);

        ImageIcon lightbeamIcon = new ImageIcon("Pictures/lightbeam.png");
        JLabel lightbeam = new JLabel();
        lightbeam.setIcon(lightbeamIcon);

        ImageIcon mbag1icon = new ImageIcon("Gifs/mbag.gif");
        JLabel mbag1 = new JLabel();
        mbag1.setIcon(mbag1icon);
        mbag1.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon mbag2icon = new ImageIcon("Gifs/mbag.gif");
        JLabel mbag2 = new JLabel();
        mbag2.setIcon(mbag2icon);
        mbag2.setHorizontalAlignment(JLabel.CENTER);

        JPanel resultPanelTopCentral = new JPanel();
        resultPanelTopCentral.setBackground(new Color(0,0,23));
        resultPanelTopCentral.setBounds(300, 0, 600, 255);

        JPanel resultPanelCentral = new JPanel();
        resultPanelCentral.setBounds(300, 250, 600, 265);

        JPanel resultPanelBottomCentralLeft = new JPanel();
        resultPanelBottomCentralLeft.setBackground(new Color(0,0,23));
        resultPanelBottomCentralLeft.setBounds(300, 515, 300, 250);

        JPanel resultPanelBottomCentralRight = new JPanel();
        resultPanelBottomCentralRight.setBackground(new Color(0,0,23));
        resultPanelBottomCentralRight.setBounds(600, 515, 300, 250);

        // Right side design
        ImageIcon rf1Icon = new ImageIcon("Gifs/f1top.gif");
        JLabel rf1 = new JLabel();
        rf1.setIcon(rf1Icon);

        ImageIcon rf2Icon = new ImageIcon("Gifs/f1bot.gif");
        JLabel rf2 = new JLabel();
        rf2.setIcon(rf2Icon);

        ImageIcon glistIcon = new ImageIcon("Gifs/glistfall.gif");
        JLabel glist = new JLabel();
        glist.setIcon(glistIcon);

        JPanel resultPanelTopRight = new JPanel();
        resultPanelTopRight.setBackground(new Color(0, 0, 23));
        resultPanelTopRight.setBounds(900, 0, 300, 250);

        JPanel resultPanelCentralRight = new JPanel();
        resultPanelCentralRight.setBackground(new Color(0,0,23));
        resultPanelCentralRight.setBounds(900, 250, 300, 270);

        JPanel resultPanelBottomRight = new JPanel();
        resultPanelBottomRight.setBackground(new Color(0, 0, 23));
        resultPanelBottomRight.setBounds(900, 515, 300, 250);

// Results Frame Design
        resultsFrame = new JFrame();
        resultsFrame.setTitle("RESULTAT: Hvem Vil Være Millionær?");
        resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultsFrame.setLayout(null);
        resultsFrame.setResizable(false);
        resultsFrame.setSize(1200, 800);   // Egentlige størrelse?
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        resultsFrame.setIconImage(logo.getImage());


// Adding Table
        // Left side
        resultPanelTopLeft.add(lf1);
        resultPanelBottomLeft.add(lf2);
        resultPanelCentralLeft.add(hansR);
        resultsFrame.add(resultPanelTopLeft);
        resultsFrame.add(resultPanelCentralLeft);
        resultsFrame.add(resultPanelBottomLeft);

        // Central side
        resultPanelCentral.add(checkLabel);
        resultPanelTopCentral.add(lightbeam);
        resultPanelBottomCentralLeft.add(mbag1);
        resultPanelBottomCentralRight.add(mbag2);
        resultsFrame.add(resultPanelTopCentral);
        resultsFrame.add(resultPanelCentral);
        resultsFrame.add(resultPanelBottomCentralLeft);
        resultsFrame.add(resultPanelBottomCentralRight);

        // Right side
        resultPanelTopRight.add(rf1);
        resultPanelBottomRight.add(rf2);
        resultPanelCentralRight.add(glist);
        resultsFrame.add(resultPanelTopRight);
        resultsFrame.add(resultPanelCentralRight);
        resultsFrame.add(resultPanelBottomRight);



        resultsFrame.setVisible(true);
    }

}