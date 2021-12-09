import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel implements ActionListener {
    JFrame menuFrame;
    JButton spilKnap;
    JButton quitKnap;
    SoundDesign soundDesign;

    public StartMenu() {
        startMenu();
        startMenuAudio();
    }

    public void startMenuAudio() {
        soundDesign = new SoundDesign("Soundeffects/intromain.wav");
        soundDesign.play();
        soundDesign.loop();
    }


    // Design menu
    public void startMenu() {
//Menu Buttons
        // Startknap
        ImageIcon buttonIcon1 = new ImageIcon("Pictures/bagmoney.png");
        spilKnap = new JButton();
        spilKnap.setBounds(400, 300, 400, 100);
        spilKnap.addActionListener(this);       // this = klassen implementerer ActionListener interface.
        spilKnap.setText("BEGYND SPIL");
        spilKnap.setFocusable(false);   // Fjerner outline focus box rundt om teksten.
        spilKnap.setIcon(buttonIcon1);
        spilKnap.setHorizontalAlignment(JButton.LEFT);
        spilKnap.setHorizontalTextPosition(JButton.RIGHT);
        spilKnap.setVerticalTextPosition(JButton.CENTER);
        spilKnap.setFont(new Font("Georgia", Font.BOLD, 25));
        spilKnap.setBackground(new Color(0, 50, 200));
        spilKnap.setForeground(new Color(240, 230, 140));
        spilKnap.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 5, false));
        spilKnap.setOpaque(true);


        // Quitknap
        ImageIcon buttonIcon2 = new ImageIcon("Pictures/check.png");
        quitKnap = new JButton();
        quitKnap.setBounds(400, 425, 400, 75);
        quitKnap.addActionListener(this);
        quitKnap.setText("AFSLUT");
        quitKnap.setFocusable(false);
        quitKnap.setIcon(buttonIcon2);
        quitKnap.setHorizontalAlignment(JButton.LEFT);
        quitKnap.setHorizontalTextPosition(JButton.RIGHT);
        quitKnap.setVerticalTextPosition(JButton.CENTER);
        quitKnap.setFont(new Font("Georgia", Font.PLAIN, 20));
        quitKnap.setBackground(new Color(0, 50, 200));
        quitKnap.setForeground(new Color(240, 230, 140));
        quitKnap.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, false));
        quitKnap.setOpaque(true);


// Menu Panels
        // Left side design
        ImageIcon icon = new ImageIcon("Pictures/taleboble.png");
        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setVerticalAlignment(JLabel.CENTER);

        ImageIcon icon2 = new ImageIcon("Pictures/hansmenu.png");
        JLabel label2 = new JLabel();
        label2.setIcon(icon2);
        label2.setHorizontalAlignment(JLabel.CENTER);

        JPanel menuPanelMidLeft = new JPanel();
        menuPanelMidLeft.setBackground(new Color(0, 0, 159));
        menuPanelMidLeft.setBounds(0, 200, 400, 275);

        JPanel menuPanelBottomLeft = new JPanel();
        menuPanelBottomLeft.setBackground(new Color(0, 0, 159));
        menuPanelBottomLeft.setBounds(0, 460, 400, 450);

        // Central side design
        ImageIcon spotlightIcon = new ImageIcon("Pictures/spotlight.png");
        JLabel spotlight = new JLabel();
        spotlight.setIcon(spotlightIcon);
        spotlight.setVerticalAlignment(JLabel.BOTTOM);

        ImageIcon topBorderIcon = new ImageIcon("Pictures/topBorder.png");
        JLabel topBorder = new JLabel();
        topBorder.setIcon(topBorderIcon);
        topBorder.setVerticalAlignment(JLabel.BOTTOM);

        ImageIcon iconMidLight = new ImageIcon("Pictures/lightmid.png");
        JLabel midLight = new JLabel();
        midLight.setIcon(iconMidLight);
        midLight.setVerticalAlignment(JLabel.BOTTOM);

        JPanel menuPanelCentralTop = new JPanel();
        menuPanelCentralTop.setBackground(new Color(0,0,159));
        menuPanelCentralTop.setBounds(0, 0, 1200, 200);

        JPanel menuPanelCentral = new JPanel();
        menuPanelCentral.setBackground(new Color(0,0,159));
        menuPanelCentral.setBounds(400, 200, 400, 300);

        JPanel menuPanelCentralBot = new JPanel();
        menuPanelCentralBot.setBackground(new Color(0, 0, 159));
        menuPanelCentralBot.setBounds(400, 450, 400, 450);

        // Right side design
        ImageIcon iconCrowdMenu = new ImageIcon("Pictures/rightmidmenu.png");
        JLabel crowdMenu = new JLabel();
        crowdMenu.setIcon(iconCrowdMenu);
        crowdMenu.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconRightbg = new ImageIcon("Pictures/rightbotmenu.png");
        JLabel rightbg = new JLabel();
        rightbg.setIcon(iconRightbg);
        rightbg.setVerticalAlignment(JLabel.CENTER);

        JPanel menuPanelMidRight = new JPanel();
        menuPanelMidRight.setBackground(new Color(0, 0, 159));
        menuPanelMidRight.setBounds(800, 200, 400, 275);

        JPanel menuPanelBottomRight = new JPanel();
        menuPanelBottomRight.setBackground(new Color(0, 0, 159));
        menuPanelBottomRight.setBounds(800, 460, 400, 450);

// Menu Frame
        menuFrame = new JFrame();
        menuFrame.setTitle("HOVEDMENU: Hvem Vil Være Millionær?");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(null);
        menuFrame.setResizable(false);
        menuFrame.setSize(1200, 800);   // Egentlige størrelse?
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        menuFrame.setIconImage(logo.getImage());


// Adding table
        // Buttons
        menuFrame.add(spilKnap);
        menuFrame.add(quitKnap);

        // Left side
        menuPanelMidLeft.add(label);
        menuPanelBottomLeft.add(label2);
        menuFrame.add(menuPanelMidLeft);
        menuFrame.add(menuPanelBottomLeft);

        // Middle
        menuPanelCentralTop.add(spotlight);
        menuPanelCentral.add(topBorder);

        menuPanelCentralBot.add(midLight);
        menuFrame.add(menuPanelCentralTop);
        menuFrame.add(menuPanelCentral);
        menuFrame.add(menuPanelCentralBot);

        // Right side
        menuPanelMidRight.add(crowdMenu);
        menuPanelBottomRight.add(rightbg);
        menuFrame.add(menuPanelMidRight);
        menuFrame.add(menuPanelBottomRight);

        menuFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
// Kører programmet videre til GameDesign.
        if(e.getSource() == spilKnap) {
            menuFrame.dispose();    // Lukker menu vinduet.
            soundDesign.stop();
            GameDesign openGame = new GameDesign();   // Når der klikkes på spilknappen, så åbner der et nyt vindue i GameDesign.


// Et popup vindue, der bekræfter om man vil afslut programmet.
        } else if (JOptionPane.showConfirmDialog(menuFrame, "Bekræft afslutning af 'Hvem vil være Millionær?'", "AFSLUT PROGRAM", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            if(e.getSource() == quitKnap) {
                soundDesign.stop();
                System.exit(0);

            }
        }
    }
}

