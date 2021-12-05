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
        this.startMenu();
        soundDesign = new SoundDesign("Soundeffects/menu.wav");
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
        spilKnap.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));   // WindowsPC : "Copperplate Gothic Bold"
        spilKnap.setBackground(new Color(0, 50, 200));
        spilKnap.setForeground(new Color(240, 230, 140));
        spilKnap.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 5, true));
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
        quitKnap.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));  // WindowsPC : "Copperplate Gothic Bold"
        quitKnap.setBackground(new Color(0, 50, 200));
        quitKnap.setForeground(new Color(240, 230, 140));
        quitKnap.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, true));
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

        ImageIcon iconLightLeft = new ImageIcon("Pictures/lightleftgd.png");
        JLabel lightLeft = new JLabel();
        lightLeft.setIcon(iconLightLeft);
        lightLeft.setVerticalAlignment(JLabel.CENTER);

        JPanel menuPanelTopLeft = new JPanel();
        menuPanelTopLeft.setBackground(new Color(0, 0, 159));
        menuPanelTopLeft.setBounds(0, 0, 400, 200);

        JPanel menuPanelMidLeft = new JPanel();
        menuPanelMidLeft.setBackground(new Color(0, 0, 159));
        menuPanelMidLeft.setBounds(0, 200, 400, 275);

        JPanel menuPanelBottomLeft = new JPanel();
        menuPanelBottomLeft.setBackground(new Color(0, 0, 159));
        menuPanelBottomLeft.setBounds(0, 460, 400, 450);

        // Middle design
        ImageIcon toplogo = new ImageIcon("Pictures/hvvmlogo.png");
        JLabel label3 = new JLabel();
        label3.setIcon(toplogo);
        label3.setVerticalAlignment(JLabel.TOP);

        ImageIcon iconMidLight = new ImageIcon("Pictures/lightmid.png");
        JLabel midLight = new JLabel();
        midLight.setIcon(iconMidLight);
        midLight.setVerticalAlignment(JLabel.BOTTOM);

        JPanel menuPanelCentralTop = new JPanel();
        menuPanelCentralTop.setBackground(new Color(0, 0, 159));
        menuPanelCentralTop.setBounds(400, 0, 400, 450);

        JPanel menuPanelCentralBot = new JPanel();
        menuPanelCentralBot.setBackground(new Color(0, 0, 159));
        menuPanelCentralBot.setBounds(400, 450, 400, 450);

        // Right side design
        ImageIcon iconLightRight = new ImageIcon("Pictures/lightrightgd.png");
        JLabel lightRight = new JLabel();
        lightRight.setIcon(iconLightRight);
        lightRight.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconCrowdMenu = new ImageIcon("Pictures/rightmidmenu.png");
        JLabel crowdMenu = new JLabel();
        crowdMenu.setIcon(iconCrowdMenu);
        crowdMenu.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconRightbg = new ImageIcon("Pictures/rightbotmenu.png");
        JLabel rightbg = new JLabel();
        rightbg.setIcon(iconRightbg);
        rightbg.setVerticalAlignment(JLabel.CENTER);

        JPanel menuPanelTopRight = new JPanel();
        menuPanelTopRight.setBackground(new Color(0, 0, 159));
        menuPanelTopRight.setBounds(800, 0, 400, 200);

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
        menuPanelTopLeft.add(lightLeft);
        menuPanelMidLeft.add(label);
        menuPanelBottomLeft.add(label2);
        menuFrame.add(menuPanelTopLeft);
        menuFrame.add(menuPanelMidLeft);
        menuFrame.add(menuPanelBottomLeft);

        // Middle
        menuPanelCentralTop.add(label3);
        menuPanelCentralBot.add(midLight);
        menuFrame.add(menuPanelCentralTop);
        menuFrame.add(menuPanelCentralBot);

        // Right side
        menuPanelTopRight.add(lightRight);
        menuPanelMidRight.add(crowdMenu);
        menuPanelBottomRight.add(rightbg);
        menuFrame.add(menuPanelTopRight);
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

