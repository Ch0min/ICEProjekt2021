import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryWindow extends JFrame implements ActionListener {

    JFrame categoryWindow = new JFrame();
    JPanel categoryPanel = new JPanel();
    JButton standardButton = new JButton();
    JButton datamatikButton = new JButton();
    JButton comingsoonButton = new JButton();
    SoundDesign soundDesign;

    public CategoryWindow(){
        this.setCategoryWindow();
        soundDesign = new SoundDesign("Soundeffects/1000000-music.wav");
        soundDesign.play();
        soundDesign.loop();

    }


    public void setCategoryWindow() {
// Buttons
        ImageIcon standardButtonicon = new ImageIcon("Pictures/StandardEdition.png");
        standardButton.setIcon(standardButtonicon);
        standardButton.setBounds(100, 75, 300, 650);
        standardButton.setFocusable(false);
        standardButton.addActionListener(this);
        standardButton.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, false));
        standardButton.setOpaque(true);

        ImageIcon datamatIcon = new ImageIcon("Gifs/WalkingCode2.gif");
        datamatikButton.setIcon(datamatIcon);
        datamatikButton.setBounds(450, 75, 300, 650);
        datamatikButton.setFocusable(false);
        datamatikButton.addActionListener(this);
        datamatikButton.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 100), 3, false));
        datamatikButton.setOpaque(true);

        ImageIcon comingsoonIcon = new ImageIcon("Pictures/ComingSoon.png");
        comingsoonButton.setIcon(comingsoonIcon);
        comingsoonButton.setBounds(800, 75, 300, 650);
        comingsoonButton.setFocusable(false);
        comingsoonButton.addActionListener(this);
        comingsoonButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 159), 3, false));
        comingsoonButton.setOpaque(true);

// Category Panel
        ImageIcon blueBGICon = new ImageIcon("Pictures/categorybg.png");
        JLabel blueBG = new JLabel();
        blueBG.setIcon(blueBGICon);
        categoryPanel.setBounds(0, 0, 1200, 800);

// Category Frame
        categoryWindow = new JFrame();
        categoryWindow.setTitle("KATEGORIMENU: Hvem Vil Være Millionær?");
        categoryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categoryWindow.setLayout(null);
        categoryWindow.setResizable(false);
        categoryWindow.setSize(1200, 800);   // Egentlige størrelse?
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        categoryWindow.setIconImage(logo.getImage());

// Adding Table
        categoryWindow.add(datamatikButton);
        categoryWindow.add(standardButton);
        categoryWindow.add(comingsoonButton);

        categoryPanel.add(blueBG);
        categoryWindow.add(categoryPanel);

        categoryWindow.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == standardButton) {
            categoryWindow.dispose();
            soundDesign.stop();
            GameDesign gameDesign = new GameDesign();
        }
         if (e.getSource() == datamatikButton){
             categoryWindow.dispose();
             soundDesign.stop();
             DataGameDesign dataGameDesign = new DataGameDesign();
         }
        //if(e.getSource()==comingsoonButton){
        //categoryWindwow.dispose();
        //ComingSoonFrame comingSoonFrame = new ComingSoonFrame();

    }
}