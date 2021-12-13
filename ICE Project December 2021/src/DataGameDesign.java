import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataGameDesign extends JPanel implements ActionListener {     // Da programmet kører på en masse små handlinger.
    // Gamedesigns Instanser
    JFrame gameFrame = new JFrame();
    JFrame resultsFrame;
    JTextField textField = new JTextField();  // Vil holde the current spørgsmål, som man er på.
    JTextArea textArea = new JTextArea();    // Vil også holde på the current spørgsmål.

    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JButton stopButton;
    JButton quitButton;
    JButton returnButton;

    // Lifelines
    JButton fifty50 = new JButton();
    JButton callAFriend = new JButton();
    JButton askMob = new JButton();

    JLabel answer_labelA = new JLabel();    // Labels til at holde på vores forskellige svarmuligheder.
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();

    // Results & Pop-Up Vinduer Instanser
    JPanel resultPanel = new JPanel();
    JLabel resultLabel = new JLabel();
    JButton resultButton = new JButton();
    JPanel highlight;

    JPanel gamePanelMidLeft;


    // Bruges til at farve optionPane vinduerne.
    UIManager UI;

    // Timer Instanser
    JLabel seconds_left = new JLabel();             // Vil fungere som selve displayet for vores countdown timer.
    JLabel seconds_left_CAF = new JLabel();

    // Audio Instanser
    SoundDesign soundDesign;    // Introduktion
    SoundDesign correctAnswer;
    SoundDesign wrongAnswer;
    SoundDesign clockRanOut;
    SoundDesign questionAudio;
    SoundDesign oneMillion;
    SoundDesign fifty50Sound;
    SoundDesign askmobSound = new SoundDesign("Soundeffects/moblifelineUse.wav");
    SoundDesign callafriendSound = new SoundDesign("Soundeffects/phonelifelineCut.wav");

    // Spørgsmåls Instans Lister
    String[] questions = {      // Indeholder spørgsmålene.
            " Hvilket apparat kaldte man tidligere for en datamat?",            // 1
            " Hvad står OOP for?",                       // 2
            " Hvilken af følgende er en rigtig syntaks for et for-loop?",                                                              // 3
            " Hvad svarer 32.000 bytes til?",                                   // 4
            " Hvilken af følgende hører ikke under OOP's grundprincipper?",                                                              // 5
            " Hvad står HTML for?",                                             // 6
            " Hvad måler man elektrisk spænding i?",                                                           // 7
            " Hvilket skolefag hed tidligere formning?",                                          // 8
            " Hvilket af disse ledere levede for færrest år siden?",                                                        // 9
            " Hvilken dansk skuespiller spiller hovedrollen i den amerikanske film 'Shot Caller fra 2017?",  // 10
            " Hvor mange øer består Danmark af?",                                                            // 11
            " Hvad samler en numismatiker på?",                                                              // 12
            " I hvilket af kroppens led sidder 'patella'?",                                                   // 13
            " Hvilket materiale er man berømt for at producere og forarbejde i Murano i det nordlige Italien?", // 14
            " Hvilket af følgende er ikke et vinmærke?"                                                     // 15

    };

    String[][] options = {      // Holder på alle svarmulighederne, til vores spørgsmål.
            // Ved at gøre det på den her måde, så kan vi tilføje og slette spørgsmål, da det her program er dynamisk.
            {" Mobiltelefon", " MP3-afspiller", " Lommeregner", " Computer"},   // 1
            {" Object-oriented Programming", " Order of the Phoenix", " Out of Process", " Ordinary Object Pointer"},                   // 2
            {" for(int 0 = i < -5; i+5)", " for(int i = 0; i < 10; i++)", " for(int i = 0; i ¤ 10; i/5)", " for(int 0 = i; i > 10; i-+)"},                               // 3
            {" 32 KB", " 320 KB", " 32 MB", " 32.000 KB"},   // 4
            {" Indkapsling", " Nedarvning", " Databaser", " Abstraktion"},        // 5
            {" HyperText Marketing Language", " HyperText Markup Language", " Home Tool Markup Language", " Hyperlinks and Text Markup Language"},            // 6
            {" Watt", " Volt", " Ohm", " Maxwell"},                 // 7
            {" Sløjd", " Håndarbejde", " Hjemkundskab", " Billedkunst"},         // 8
            {" Genghis Khan", " Tutankhamon", " Gorm den Gamle", " Julius Cæsar"},           // 9
            {" Mads Mikkelsen", " Pilou Asbæk", " Ulrich Thomsen", " Nikolaj Coster-Waldau"},   // 10
            {" ca. 50", " ca. 200", " ca. 400", " ca. 700"},         // 11
            {" Mønter", " Kort", " Terninger", " Frimærker"},        // 12
            {" I albuen", " I knæet", " I rygsøjlen", " I håndleddet"},    // 13
            {" Plastik", " Stof", " Metal", " Glas"},             // 14
            {" Bolero", " Barolo", " Brunello", " Barbaresco"}  // 15

    };

    char[] answers =    // Holder på alle de rigtige svar.
            {
                    'D',    // 1
                    'A',    // 2
                    'B',    // 3
                    'A',    // 4
                    'C',    // 5
                    'B',    // 6
                    'B',    // 7
                    'D',    // 8
                    'A',    // 9
                    'D',    // 10
                    'C',    // 11
                    'A',    // 12
                    'B',    // 13
                    'D',    // 14
                    'A'     // 15
            };

    // Pengebeløb liste
    String[] rewardsList = {"0", "1000", "2000", "3000", "4000", "5000", "8000", "12000", "20000",
            "32000", "50000", "75000", "125000", "250000", "500000", "1 MILLION"};

    // Gamedesign variabler
    private char answer;    // vil holde på svar.
    int index;      // Bruges som en timer til at vide hvilket spørgsmål man er ved.
    private int total_questions = questions.length;
    private String result;     // Holder på resultat.
    private int seconds = 30;   // Timer til hvor mange sekunder man har ved hvert spørgsmål.
    private int secondsCAF = 45;   // Timer til hvor mange sekunder man har ved hvert spørgsmål.


    // Timer Design
    Timer pause;

    Timer countdown = new Timer(1000, new ActionListener() {     // fx 2000ms = 2 sekunder.
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;      // Efter hvert action performed method, så decrementer vi seconds med 1.
            seconds_left.setText(String.valueOf(seconds));
            if (seconds <= 0) {      // Hvis timeren rammer 0.
                clockRanOut = new SoundDesign("Soundeffects/wrong-answer.wav");
                clockRanOut.play();
                displayAnswer();    // Vil display det rigtige svar, og disable alle muligheder.
            }
        }
    });  // Tilføje ');', så error går væk. Sikkert fordi Timeren mangler en parentes.

    Timer secondsCallAFriend = new Timer(1000, new ActionListener() {     // fx 2000ms = 2 sekunder.
        @Override
        public void actionPerformed(ActionEvent e) {
            secondsCAF--;
            seconds_left_CAF.setText(String.valueOf(secondsCAF));
            if (secondsCAF <= 0) {
                clockRanOut = new SoundDesign("Soundeffects/wrong-answer.wav");
                clockRanOut.play();
                displayAnswer();
                secondsCallAFriend.setRepeats(false);
                secondsCallAFriend.stop();
                callafriendSound.stop();
            }
        }
    });


    // Gamedesign constructor.
    public DataGameDesign() {
        try {
            gameRun();
        } catch (RuntimeException e) {
            System.out.println("Runtime afbrudt");
        }
        gameAudio();

    }

    public void gameAudio() {
        soundDesign = new SoundDesign("Soundeffects/førstespørgsmål.wav");
        soundDesign.play();
        soundDesign.loop();

    }


    public void gameRun() {
        // Game fields
        textField.setBounds(0, 475, 800, 20);      // 'setBounds' bruger man til at bestemme placeringen.
        textField.setBackground(new Color(0, 50, 159));
        textField.setForeground(new Color(255, 185, 0));
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 255), 1, true));
        textField.setHorizontalAlignment(JTextField.CENTER);    // Juster placeringen af teksten.(Center)
        textField.setFocusable(false);

// Game Text area display - vil vise det aktuelle spørgsmål.
        textArea.setBounds(0, 495, 800, 45);
        textArea.setLineWrap(true);           // 'Wrap' for at teksten ikke går udover skærmen, men i stedet går ned til næste linje.
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(0, 0, 250));
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setFont(new Font("Arial", Font.BOLD, 20));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 1, true));
        textArea.setEditable(false);


// Game Buttons
        // Button A
        buttonA.setBounds(0, 540, 150, 120);
        buttonA.setForeground(new Color(255, 185, 0));
        buttonA.setBackground(new Color(0, 0, 159));
        buttonA.setFont(new Font("Impact", Font.BOLD, 40));
        buttonA.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonA.addActionListener(this);
        buttonA.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        buttonA.setText("A:");
        buttonA.setOpaque(true);

        // Button B
        buttonB.setBounds(400, 540, 150, 120);
        buttonB.setForeground(new Color(255, 185, 0));
        buttonB.setBackground(new Color(0, 0, 159));
        buttonB.setFont(new Font("Impact", Font.BOLD, 40));
        buttonB.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonB.addActionListener(this);
        buttonB.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        buttonB.setText("B:");
        buttonB.setOpaque(true);

        // Button C
        buttonC.setBounds(0, 657, 150, 125);
        buttonC.setForeground(new Color(255, 185, 0));
        buttonC.setBackground(new Color(0, 0, 159));
        buttonC.setFont(new Font("Impact", Font.BOLD, 40));
        buttonC.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonC.addActionListener(this);
        buttonC.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        buttonC.setText("C:");
        buttonC.setOpaque(true);

        // Button D
        buttonD.setBounds(400, 657, 150, 125);
        buttonD.setForeground(new Color(255, 185, 0));
        buttonD.setBackground(new Color(0, 0, 159));
        buttonD.setFont(new Font("Impact", Font.BOLD, 40));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        buttonD.setText("D:");
        buttonD.setOpaque(true);

        // Tag pengene Button
        stopButton = new JButton();
        stopButton.setBounds(850, 700, 200, 50);
        stopButton.setForeground(new Color(160, 82, 45));
        stopButton.setBackground(new Color(218, 165, 32));
        stopButton.setFont(new Font("Impact", Font.PLAIN, 28));
        stopButton.setFocusable(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(gameFrame, "Er det din endelige beslutning?'", "HANS PILGAARD", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    if (e.getSource() == stopButton) {
                        soundDesign.stop();
                        countdown.stop();
                        gameFrame.removeAll();
                        gameFrame.dispose();
                        reward();
                    }
                }
            }
        });
        stopButton.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 4, false));
        stopButton.setText("TAG PENGENE");
        stopButton.setOpaque(true);

        // Afslut Spil/Quit Button
        quitButton = new JButton();
        quitButton.setBounds(1100, 700, 75, 50);
        quitButton.setForeground(new Color(255, 185, 0));
        quitButton.setBackground(new Color(0, 0, 250));
        quitButton.setFont(new Font("Impact", Font.PLAIN, 14));
        quitButton.setFocusable(false);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(gameFrame, "Bekræft afslutning af 'Hvem vil være Millionær?'", "AFSLUT PROGRAM", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    if (e.getSource() == quitButton) {
                        soundDesign.stop();
                        System.exit(0);
                    }
                }
            }
        });
        quitButton.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        quitButton.setText("AFSLUT SPIL");
        quitButton.setOpaque(true);

// Livlinjer
        // 5050 Lifeline
        fifty50Sound = new SoundDesign("Soundeffects/5050lifeline.wav");

        ImageIcon fiftyIcon = new ImageIcon("Pictures/5050small.png");
        ImageIcon fiftyIconDisabled = new ImageIcon("Pictures/5050disabled.png");
        JLabel fiftyLabel = new JLabel();
        JLabel fiftyLabelDisabled = new JLabel();
        fiftyLabel.setIcon(fiftyIcon);

        fifty50 = new JButton();
        fifty50.setBounds(875, 15, 80, 65);
        fifty50.setFont(new Font("Arial", Font.BOLD, 20));
        fifty50.setForeground(new Color(255, 185, 0));
        fifty50.setBackground(new Color(0, 0, 250));
        fifty50.setFocusable(false);
        fifty50.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    answer = 'A';
                    if (e.getSource() == fifty50) {
                        fifty50Sound.play();
                        fifty50.setEnabled(false);
                        fiftyLabelDisabled.setIcon(fiftyIconDisabled);
                        fiftyLabelDisabled.setDisabledIcon(fiftyIconDisabled);
                        fiftyLabel.setIcon(null);
                        if (answer == answers[index]) {
                            answer_labelB.setForeground(new Color(255, 0, 0));
                            answer_labelC.setForeground(new Color(255, 0, 0));
                            buttonA.setEnabled(true);
                            buttonB.setEnabled(false);
                            buttonC.setEnabled(false);
                            buttonD.setEnabled(true);
                        }
                    }
                    answer = 'B';
                    if (e.getSource() == fifty50) {
                        fifty50Sound.play();
                        fifty50.setEnabled(false);
                        fiftyLabelDisabled.setIcon(fiftyIconDisabled);
                        fiftyLabelDisabled.setDisabledIcon(fiftyIconDisabled);
                        fiftyLabel.setIcon(null);
                        if (answer == answers[index]) {
                            answer_labelC.setForeground(new Color(255, 0, 0));
                            answer_labelD.setForeground(new Color(255, 0, 0));
                            buttonA.setEnabled(true);
                            buttonB.setEnabled(true);
                            buttonC.setEnabled(false);
                            buttonD.setEnabled(false);
                        }
                    }
                    answer = 'C';
                    if (e.getSource() == fifty50) {
                        fifty50Sound.play();
                        fifty50.setEnabled(false);
                        fiftyLabelDisabled.setIcon(fiftyIconDisabled);
                        fiftyLabelDisabled.setDisabledIcon(fiftyIconDisabled);
                        fiftyLabel.setIcon(null);
                        if (answer == answers[index]) {
                            answer_labelA.setForeground(new Color(255, 0, 0));
                            answer_labelD.setForeground(new Color(255, 0, 0));
                            buttonA.setEnabled(false);
                            buttonB.setEnabled(true);
                            buttonC.setEnabled(true);
                            buttonD.setEnabled(false);
                        }
                    }
                    answer = 'D';
                    if (e.getSource() == fifty50) {
                        fifty50Sound.play();
                        fifty50.setEnabled(false);
                        fiftyLabelDisabled.setIcon(fiftyIconDisabled);
                        fiftyLabelDisabled.setDisabledIcon(fiftyIconDisabled);
                        fiftyLabel.setIcon(null);
                        if (answer == answers[index]) {
                            answer_labelA.setForeground(new Color(255, 0, 0));
                            answer_labelB.setForeground(new Color(255, 0, 0));
                            buttonA.setEnabled(false);
                            buttonB.setEnabled(false);
                            buttonC.setEnabled(true);
                            buttonD.setEnabled(true);
                        }
                    }
                }
            });
        fifty50.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        fifty50.setOpaque(true);

        // CallAFriend Lifeline
        ImageIcon cafIcon = new ImageIcon("Pictures/callafriendsmall.png");
        ImageIcon cafIconDisabled = new ImageIcon("Pictures/callafrienddisabled.png");
        JLabel cafLabel = new JLabel();
        JLabel cafLabelDisabled = new JLabel();
        cafLabel.setIcon(cafIcon);

        callAFriend = new JButton();
        callAFriend.setBounds(970, 15, 80, 65);
        callAFriend.setFont(new Font("Arial", Font.BOLD, 20));
        callAFriend.setForeground(new Color(255, 185, 0));
        callAFriend.setBackground(new Color(0, 0, 250));
        callAFriend.setFocusable(false);
        callAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] valgc = {"Ja tak Hans"};
                if (e.getSource() == callAFriend) {
                    callAFriend.setEnabled(false);
                    cafLabelDisabled.setIcon(cafIconDisabled);
                    cafLabelDisabled.setDisabledIcon(cafIconDisabled);
                    cafLabel.setIcon(null);
                    callafriendSound.play();
                    countdown.stop();
                    secondsCallAFriend.start();
                    JOptionPane.showOptionDialog(gameFrame, "Din tid begynder nu. Du har 45 sekunder til at spørge eller ringe til en ven. Held og lykke!", "HANS PILGAARD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, valgc, valgc[0]);
                    // 100 sekunder timer
                    seconds_left_CAF.setBounds(0, 200, 200, 200);
                    seconds_left_CAF.setForeground(new Color(255, 0, 0));
                    seconds_left_CAF.setFont(new Font("JetBrains Mono", Font.BOLD, 35));
                    seconds_left_CAF.setHorizontalAlignment(JTextField.CENTER);
                    seconds_left_CAF.setText(String.valueOf(secondsCAF));

                }
            }
        });

        callAFriend.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        callAFriend.setOpaque(true);


        ImageIcon askMobIcon = new ImageIcon("Pictures/askmobsmall.png");
        ImageIcon askMobIconDisabled = new ImageIcon("Pictures/askmobdisabled.png");
        JLabel askMobLabel= new JLabel();
        JLabel askMobLabelDisabled = new JLabel();
        askMobLabel.setIcon(askMobIcon);
        askMobLabel.setHorizontalAlignment(JLabel.CENTER);

        askMob = new JButton();
        askMob.setBounds(1065, 15, 80, 65);
        askMob.setFont(new Font("Arial", Font.BOLD, 20));
        askMob.setForeground(new Color(255, 185, 0));
        askMob.setBackground(new Color(0, 0, 250));
        askMob.setFocusable(false);
        askMob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = 'A';
                if (e.getSource() == askMob) {
                    askmobSound.play();
                    askMob.setEnabled(false);
                    askMobLabelDisabled.setIcon(askMobIconDisabled);
                    askMobLabelDisabled.setDisabledIcon(askMobIconDisabled);
                    askMobLabel.setIcon(null);
                    if (answer == answers[index]) {
                        JFrame askmob1 = new JFrame();
                        askmob1.setSize(415,325);
                        askmob1.setBackground(new Color(0,0,159));
                        askmob1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        askmob1.setResizable(false);
                        askmob1.setVisible(true);
                        ImageIcon askmob1Icon = new ImageIcon("Pictures/askmobStatAsmall.png");
                        JLabel askmob1Label = new JLabel();
                        askmob1Label.setIcon(askmob1Icon);
                        askmob1.add(askmob1Label);
                    }
                }
                answer = 'B';
                if (e.getSource() == askMob) {
                    askmobSound.play();
                    askMob.setEnabled(false);
                    askMobLabelDisabled.setIcon(askMobIconDisabled);
                    askMobLabelDisabled.setDisabledIcon(askMobIconDisabled);
                    askMobLabel.setIcon(null);
                    if (answer == answers[index]) {
                        JFrame askmob2 = new JFrame();
                        askmob2.setSize(415,325);
                        askmob2.setBackground(new Color(0,0,159));
                        askmob2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        askmob2.setResizable(false);
                        askmob2.setVisible(true);
                        ImageIcon askmob2Icon = new ImageIcon("Pictures/askmobStatBsmall.png");
                        JLabel askmob2Label = new JLabel();
                        askmob2Label.setIcon(askmob2Icon);
                        askmob2.add(askmob2Label);
                    }
                }
                answer = 'C';
                if (e.getSource() == askMob) {
                    askmobSound.play();
                    askMob.setEnabled(false);
                    askMobLabelDisabled.setIcon(askMobIconDisabled);
                    askMobLabelDisabled.setDisabledIcon(askMobIconDisabled);
                    askMobLabel.setIcon(null);
                    if (answer == answers[index]) {
                        JFrame askmob3 = new JFrame();
                        askmob3.setSize(415,325);
                        askmob3.setBackground(new Color(0,0,159));
                        askmob3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        askmob3.setResizable(false);
                        askmob3.setVisible(true);
                        ImageIcon askMob3Icon = new ImageIcon("Pictures/askmobStatCsmall.png");
                        JLabel askMob3Label = new JLabel();
                        askMob3Label.setIcon(askMob3Icon);
                        askmob3.add(askMob3Label);
                    }
                }
                answer = 'D';
                if (e.getSource() == askMob) {
                    askmobSound.play();
                    askMob.setEnabled(false);
                    askMobLabelDisabled.setIcon(askMobIconDisabled);
                    askMobLabelDisabled.setDisabledIcon(askMobIconDisabled);
                    askMobLabel.setIcon(null);
                    if (answer == answers[index]) {
                        JFrame askmob4 = new JFrame();
                        askmob4.setSize(415,325);
                        askmob4.setBackground(new Color(0,0,159));
                        askmob4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        askmob4.setResizable(false);
                        askmob4.setVisible(true);
                        ImageIcon askmob4Icon = new ImageIcon("Pictures/askmobStatDsmall.png");
                        JLabel askmob4Label = new JLabel();
                        askmob4Label.setIcon(askmob4Icon);
                        askmob4.add(askmob4Label);
                    }
                }
            }
        });
        askMob.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, false));
        askMob.setOpaque(true);

        // Pengecheck button
        resultPanel = new JPanel();
        resultPanel.setFont(new Font("Arial", Font.BOLD, 15));
        resultLabel = new JLabel("SE DINE RESULTATER: ");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        resultButton = new JButton("PENGECHECK");
        resultButton.setFont(new Font("Impact", Font.BOLD, 30));
        resultButton.addActionListener(this);
        resultPanel.add(resultLabel);
        resultPanel.add(resultButton);


// Game Panels
        // Question design
        answer_labelA.setBounds(150, 535, 250, 125);
        answer_labelA.setForeground(new Color(255, 255, 255));
        answer_labelA.setFont(new Font("Arial", Font.PLAIN, 15));

        answer_labelB.setBounds(550, 535, 250, 125);
        answer_labelB.setForeground(new Color(255, 255, 255));
        answer_labelB.setFont(new Font("Arial", Font.PLAIN, 15));

        answer_labelC.setBounds(150, 657, 250, 125);
        answer_labelC.setForeground(new Color(255, 255, 255));
        answer_labelC.setFont(new Font("Arial", Font.PLAIN, 15));

        answer_labelD.setBounds(550, 657, 250, 125);
        answer_labelD.setForeground(new Color(255, 255, 255));
        answer_labelD.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel panel_A = new JPanel();
        panel_A.setBackground(new Color(65, 105, 225));
        panel_A.setBounds(150, 535, 250, 125);
        panel_A.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, true));

        JPanel panel_B = new JPanel();
        panel_B.setBackground(new Color(65, 105, 225));
        panel_B.setBounds(550, 535, 250, 125);
        panel_B.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, true));

        JPanel panel_C = new JPanel();
        panel_C.setBackground(new Color(65, 105, 225));
        panel_C.setBounds(150, 657, 250, 125);
        panel_C.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));

        JPanel panel_D = new JPanel();
        panel_D.setBackground(new Color(65, 105, 225));
        panel_D.setBounds(550, 657, 250, 125);
        panel_D.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));


// Frame design
        // Left side game design
        ImageIcon iconLightLeft = new ImageIcon("Pictures/lightleftgd.png");
        JLabel lightLeft = new JLabel();
        lightLeft.setIcon(iconLightLeft);
        lightLeft.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconSpiller = new ImageIcon("Pictures/spillergd.png");
        JLabel spillergd = new JLabel();
        spillergd.setIcon(iconSpiller);
        spillergd.setVerticalAlignment(JLabel.CENTER);

        JPanel gamePanelTopLeft = new JPanel();
        gamePanelTopLeft.setBackground(new Color(0, 0, 159));
        gamePanelTopLeft.setBounds(0, 0, 400, 200);

        gamePanelMidLeft = new JPanel();
        gamePanelMidLeft.setBackground(new Color(0, 0, 159));
        gamePanelMidLeft.setBounds(0, 200, 400, 275);

        // Middle design
        ImageIcon iconLightRight = new ImageIcon("Pictures/lightrightgd.png");
        JLabel lightRight = new JLabel();
        lightRight.setIcon(iconLightRight);
        lightRight.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconHansgd = new ImageIcon("Pictures/hansventergd.png");
        JLabel hansgd = new JLabel();
        hansgd.setIcon(iconHansgd);
        hansgd.setVerticalAlignment(JLabel.CENTER);

        JPanel gamePanelCentralTop = new JPanel();
        gamePanelCentralTop.setBackground(new Color(0, 0, 159));
        gamePanelCentralTop.setBounds(400, 0, 400, 200);

        JPanel gamePanelCentralMid = new JPanel();
        gamePanelCentralMid.setBackground(new Color(0, 0, 159));
        gamePanelCentralMid.setBounds(400, 200, 400, 275);

        // Right design
        highlight = new JPanel();
        highlight.setBackground(new Color(255, 229, 94, 150));
        highlight.setBounds(890, 695, 300, 40);
        highlight.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, false));
        highlight.setOpaque(true);

        JLabel kr1000 = new JLabel();
        kr1000.setText("1  KR 1.000");
        kr1000.setForeground(new Color(255, 185, 0));
        kr1000.setBounds(925, 650, 300, 50);
        kr1000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr1000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr2000 = new JLabel();
        kr2000.setText("2  KR 2.000");
        kr2000.setForeground(new Color(255, 185, 0));
        kr2000.setBounds(925, 610, 300, 50);
        kr2000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr2000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr3000 = new JLabel();
        kr3000.setText("3  KR 3.000");
        kr3000.setForeground(new Color(255, 185, 0));
        kr3000.setBounds(925, 570, 300, 50);
        kr3000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr3000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr4000 = new JLabel();
        kr4000.setText("4  KR 4.000");
        kr4000.setForeground(new Color(255, 185, 0));
        kr4000.setBounds(925, 530, 300, 50);
        kr4000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr4000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr5000 = new JLabel();
        kr5000.setText("5  KR 5.000");
        kr5000.setForeground(new Color(255, 255, 255));
        kr5000.setBounds(925, 490, 300, 50);
        kr5000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr5000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr8000 = new JLabel();
        kr8000.setText("6  KR 8.000");
        kr8000.setForeground(new Color(255, 185, 0));
        kr8000.setBounds(925, 450, 300, 50);
        kr8000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr8000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr12000 = new JLabel();
        kr12000.setText("7  KR 12.000");
        kr12000.setForeground(new Color(255, 185, 0));
        kr12000.setBounds(925, 410, 300, 50);
        kr12000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr12000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr20000 = new JLabel();
        kr20000.setText("8  KR 20.000");
        kr20000.setForeground(new Color(255, 185, 0));
        kr20000.setBounds(925, 370, 300, 50);
        kr20000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr20000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr32000 = new JLabel();
        kr32000.setText("9  KR 32.000");
        kr32000.setForeground(new Color(255, 185, 0));
        kr32000.setBounds(925, 330, 300, 50);
        kr32000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr32000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr50000 = new JLabel();
        kr50000.setText("10  KR 50.000");
        kr50000.setForeground(new Color(255, 255, 255));
        kr50000.setBounds(905, 290, 300, 50);
        kr50000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr50000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr75000 = new JLabel();
        kr75000.setText("11  KR 75.000");
        kr75000.setForeground(new Color(255, 185, 0));
        kr75000.setBounds(905, 250, 300, 50);
        kr75000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr75000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr125000 = new JLabel();
        kr125000.setText("12  KR 125.000");
        kr125000.setForeground(new Color(255, 185, 0));
        kr125000.setBounds(905, 210, 300, 50);
        kr125000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr125000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr250000 = new JLabel();
        kr250000.setText("13  KR 250.000");
        kr250000.setForeground(new Color(255, 185, 0));
        kr250000.setBounds(905, 170, 300, 50);
        kr250000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr250000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr500000 = new JLabel();
        kr500000.setText("14  KR 500.000");
        kr500000.setForeground(new Color(255, 185, 0));
        kr500000.setBounds(905, 130, 300, 50);
        kr500000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr500000.setHorizontalTextPosition(JLabel.CENTER);
        JLabel kr1000000 = new JLabel();
        kr1000000.setText("15  1 MILLION KR");
        kr1000000.setForeground(new Color(255, 255, 255));
        kr1000000.setBounds(905, 90, 300, 50);
        kr1000000.setFont(new Font("Droid Sans Mono", Font.BOLD, 30));
        kr1000000.setHorizontalTextPosition(JLabel.CENTER);

        JPanel gamePanelTopRight = new JPanel();
        gamePanelTopRight.setBackground(new Color(0, 0, 159));
        gamePanelTopRight.setBounds(800, 0, 400, 100);

        JPanel gamePanelMidRight = new JPanel();
        gamePanelMidRight.setBackground(new Color(0, 0, 159));
        gamePanelMidRight.setBounds(800, 100, 400, 600);

        JPanel gamePanelBottomRight = new JPanel();
        gamePanelBottomRight.setBackground(new Color(0, 0, 159));
        gamePanelBottomRight.setBounds(800, 695, 400, 100);
        gamePanelBottomRight.setOpaque(true);


// Optionpane/Pop-Up design
        //    UI = new UIManager();
        //    UI.put("OptionPane.background", new ColorUIResource(197,179,88));
        //    UI.put("Panel.background", new ColorUIResource(197,179,88));

// Timere
        // 30 sekunder timer
        seconds_left.setBounds(0, 475, 800, 20);
        seconds_left.setForeground(new Color(255, 185, 0));
        seconds_left.setBackground(new Color(0, 50, 159));
        seconds_left.setFont(new Font("JetBrains Mono", Font.BOLD, 23));
        seconds_left.setHorizontalAlignment(JTextField.RIGHT);
        seconds_left.setText(String.valueOf(seconds));

// Game Frame
        gameFrame = new JFrame();
        gameFrame.setTitle("GAMERUN: Hvem Vil Være Millionær Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(null);
        gameFrame.setResizable(false);
        gameFrame.setSize(1200, 800);   // Egentlige størrelse.
        //     gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);      // SÆTTER DEN TIL FULLSCREEN
        ImageIcon logo = new ImageIcon("Pictures/logo.png");
        gameFrame.setIconImage(logo.getImage());


// Adding table
        gameFrame.add(seconds_left);
        gameFrame.add(seconds_left_CAF);

        gameFrame.add(textField);
        gameFrame.add(textArea);

        // Buttons
        gameFrame.add(buttonA);
        gameFrame.add(buttonB);
        gameFrame.add(buttonC);
        gameFrame.add(buttonD);
        gameFrame.add(stopButton);
        gameFrame.add(quitButton);

        // Lifelines Buttons
        fifty50.add(fiftyLabel);
        fifty50.add(fiftyLabelDisabled);
        callAFriend.add(cafLabel);
        callAFriend.add(cafLabelDisabled);
        askMob.add(askMobLabel);
        askMob.add(askMobLabelDisabled);
        gamePanelTopRight.add(fifty50);
        gamePanelTopRight.add(callAFriend);
        gamePanelTopRight.add(askMob);
        gameFrame.add(fifty50);
        gameFrame.add(callAFriend);
        gameFrame.add(askMob);

        // Question design
        gameFrame.add(answer_labelA);
        gameFrame.add(answer_labelB);
        gameFrame.add(answer_labelC);
        gameFrame.add(answer_labelD);
        gameFrame.add(panel_A);
        gameFrame.add(panel_B);
        gameFrame.add(panel_C);
        gameFrame.add(panel_D);

        // Left side
        gamePanelTopLeft.add(lightLeft);
        gamePanelMidLeft.add(spillergd);
        gamePanelMidLeft.add(seconds_left_CAF);
        gameFrame.add(gamePanelTopLeft);
        gameFrame.add(gamePanelMidLeft);

        // Middle side
        gamePanelCentralTop.add(lightRight);
        gamePanelCentralMid.add(hansgd);
        gameFrame.add(gamePanelCentralTop);
        gameFrame.add(gamePanelCentralMid);

        // Right side
        gameFrame.add(gamePanelBottomRight);
        gameFrame.add(gamePanelMidRight);
        gameFrame.add(gamePanelTopRight);

        gamePanelMidRight.add(highlight);
        gameFrame.add(highlight);
        gameFrame.add(kr1000);
        gameFrame.add(kr2000);
        gameFrame.add(kr3000);
        gameFrame.add(kr4000);
        gameFrame.add(kr5000);
        gameFrame.add(kr8000);
        gameFrame.add(kr12000);
        gameFrame.add(kr20000);
        gameFrame.add(kr32000);
        gameFrame.add(kr50000);
        gameFrame.add(kr75000);
        gameFrame.add(kr125000);
        gameFrame.add(kr250000);
        gameFrame.add(kr500000);
        gameFrame.add(kr1000000);
        gameFrame.add(gamePanelMidRight);

        gameFrame.setVisible(true);

        nextQuestion();

    }

    public void nextQuestion() {
        if (index >= total_questions) {
            // Pengecheck popup vindue
            JPanel resultPanel = new JPanel();
            resultPanel.setFont(new Font("Arial", Font.BOLD, 15));
            JLabel resultLabel = new JLabel("SE DINE RESULTATER: ");
            resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            JButton resultButton = new JButton("PENGECHECK");
            resultButton.setFont(new Font("Impact", Font.BOLD, 30));
            resultPanel.add(resultLabel);
            resultPanel.add(resultButton);
            JOptionPane.showOptionDialog(gameFrame, "DET RIGTIGT TILLYKKE! DU HAR VUNDET 1 MILLION KRONER!!!", "HANS PILGAARD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            soundDesign.stop();
            secondsCallAFriend.stop();
            oneMillion = new SoundDesign("Soundeffects/1MIL.wav");
            oneMillion.play();
            gameFrame.removeAll();
            gameFrame.dispose();
            reward();
        } else {
            String[] valg = {"JA TAK"};
            textField.setText("Spørgsmål " + (index + 1));       // Incrementer 'Spørgsmål' hver gang der kommer et nyt spørgsmål.
            textField.setFont(new Font("Droid Sans Mono", Font.BOLD, 20));
            textField.setForeground(new Color(255, 185, 0));
            JOptionPane.showOptionDialog(gameFrame, "SPØRGSMÅL TIL: " + rewardsList[index + 1] + " KR", "HANS PILGAARD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, valg, valg[0]);
            textArea.setText(questions[index]);                  // Hver gang index bliver incremented, så skal programmet skifte til næste spørgsmål.
            answer_labelA.setText(options[index][0]);            // Bruger vores options 2d array, for at hente svarmulighederne.
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            countdown.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException {
        correctAnswer = new SoundDesign("Soundeffects/correct.wav");
        wrongAnswer = new SoundDesign("Soundeffects/wrong.wav");
        questionAudio = new SoundDesign("Soundeffects/5000000-music.wav");

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        stopButton.setEnabled(false);

        if (e.getSource() == buttonA) {      // Hvis en person klikker på Button A, hvad skal der så ske?
            answer = 'A';
            if (answer == answers[index]) {  // Hvis vores svar er equal til det svar der er stored i vores 'answers array' i et bestemt index, så incrementer vi 'correct_guess' med 1.
                correctAnswer.play();
                Timer delayClock = new Timer(2800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        highlight.setLocation(highlight.getX(), highlight.getY() + -40);
                        callafriendSound.stop();
                        askmobSound.stop();
                    }
                });
                delayClock.setRepeats(false);
                delayClock.start();
            } else if (answer != answers[index]) {
                wrongAnswer.play();
                callafriendSound.stop();
                askmobSound.stop();
            }
        }

        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correctAnswer.play();
                Timer delayClock = new Timer(2800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        highlight.setLocation(highlight.getX(), highlight.getY() + -40);
                        callafriendSound.stop();
                        askmobSound.stop();
                    }
                });
                delayClock.setRepeats(false);
                delayClock.start();
            } else if (answer != answers[index]) {
                wrongAnswer.play();
                callafriendSound.stop();
                askmobSound.stop();
            }
        }

        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correctAnswer.play();
                Timer delayClock = new Timer(2800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        highlight.setLocation(highlight.getX(), highlight.getY() + -40);
                        callafriendSound.stop();
                        askmobSound.stop();
                    }
                });
                delayClock.setRepeats(false);
                delayClock.start();
            } else if (answer != answers[index]) {
                wrongAnswer.play();
                callafriendSound.stop();
                askmobSound.stop();
            }
        }

        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correctAnswer.play();
                Timer delayClock = new Timer(2800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        highlight.setLocation(highlight.getX(), highlight.getY() + -40);
                        callafriendSound.stop();
                        askmobSound.stop();
                    }
                });
                delayClock.setRepeats(false);
                delayClock.start();
            } else if (answer != answers[index]) {
                wrongAnswer.play();
                callafriendSound.stop();
                askmobSound.stop();
            }
        }
        if (e.getSource() == resultButton) {
            soundDesign.stop();
            gameFrame.removeAll();
            gameFrame.dispose();
            reward();
        }
        displayAnswer();
    }


    public void displayAnswer() {
        countdown.stop();
        secondsCallAFriend.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        // Skifter farve
        if (answers[index] == 'A')     // Hvis svaret er 'A', hvad gør vi så?
            answer_labelA.setForeground(new Color(255, 255, 255));
        if (answers[index] == 'B')    // Hvis svaret er 'B', hvad gør vi så?
            answer_labelB.setForeground(new Color(255, 255, 255));
        if (answers[index] == 'C')    // Hvis svaret er 'C', hvad gør vi så?
            answer_labelC.setForeground(new Color(255, 255, 255));
        if (answers[index] == 'D')     // Hvis svaret er 'D', hvad gør vi så?
            answer_labelD.setForeground(new Color(255, 255, 255));

        if (answers[index] != 'A')     // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelA.setForeground(new Color(255, 255, 255));
        if (answers[index] != 'B')    // Hvis svaret ikke er 'B', hvad gør vi så?
            answer_labelB.setForeground(new Color(255, 255, 255));
        if (answers[index] != 'C')    // Hvis svaret ikke er 'C', hvad gør vi så?
            answer_labelC.setForeground(new Color(255, 255, 255));
        if (answers[index] != 'D')     // Hvis svaret ikke er 'D', hvad gør vi så?
            answer_labelD.setForeground(new Color(255, 255, 255));

        // Delay efter man har trykket på et svar.
        Timer delayClock = new Timer(2800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answers[index] == 'A')     // Hvis svaret er 'A', hvad gør vi så?
                    answer_labelA.setForeground(new Color(240, 230, 140));
                if (answers[index] == 'B')    // Hvis svaret er 'B', hvad gør vi så?
                    answer_labelB.setForeground(new Color(240, 230, 140));
                if (answers[index] == 'C')    // Hvis svaret er 'C', hvad gør vi så?
                    answer_labelC.setForeground(new Color(240, 230, 140));
                if (answers[index] == 'D')     // Hvis svaret er 'D', hvad gør vi så?
                    answer_labelD.setForeground(new Color(240, 230, 140));

                if (answers[index] != 'A')     // Hvis svaret ikke er 'A', hvad gør vi så?
                    answer_labelA.setForeground(new Color(255, 0, 0));
                if (answers[index] != 'B')    // Hvis svaret ikke er 'B', hvad gør vi så?
                    answer_labelB.setForeground(new Color(255, 0, 0));
                if (answers[index] != 'C')    // Hvis svaret ikke er 'C', hvad gør vi så?
                    answer_labelC.setForeground(new Color(255, 0, 0));
                if (answers[index] != 'D')     // Hvis svaret ikke er 'D', hvad gør vi så?
                    answer_labelD.setForeground(new Color(255, 0, 0));

            }
        });
        delayClock.setRepeats(false);
        delayClock.start();

        // Timer i metoden, da vi gerne vil have de forkerte svar converter til deres oprindelige farve igen, efter skift af hvert spørgsmål.
        pause = new Timer(6000, new ActionListener() {     // 6 sekunders pause efter hver spørgsmål.

            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(255, 255, 255));     // Flip tekstfarverne tilbage til oprindelige farve.
                answer_labelB.setForeground(new Color(255, 255, 255));
                answer_labelC.setForeground(new Color(255, 255, 255));
                answer_labelD.setForeground(new Color(255, 255, 255));

                seconds = 30;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);        // Vi skal nemlig huske at enable vores knapper igen her.
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                stopButton.setEnabled(true);
                seconds_left_CAF.setText(null);
                seconds_left_CAF.setForeground(new Color(255,255,255,255));

                // Hvis svaret er rigtigt så fortsæt.
                if (answer == answers[index]) {
                    index++;     // For at køre videre til næste spørgsmål.
                    nextQuestion();
                    // Hvis svaret er forkert. Så åben resultat vindue.
                } else if (answer != answers[index]) {
                    // Pengecheck popup vindue
                    decider();
                    String[] options = {"Afslut Spil"};
                    JOptionPane.showOptionDialog(gameFrame, resultPanel, "RESULTAT", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    gameFrame.dispose();
                    pause.stop();
                }
            }
        });  // Tilføje ');', så error går væk. Sikkert fordi Timeren mangler en parentes.
        pause.setRepeats(false);     // For at timeren kun aktiveres en gang.
        pause.start();               // Her får vi timeren til at starte.
    }


    private void reward() {
        SoundDesign reward = new SoundDesign("Soundeffects/intromain.wav");
        reward.play();
        reward.loop();

// Result Panels
        // Return Button
        returnButton = new JButton();
        returnButton.setBounds(930, 330, 225, 100);
        returnButton.setForeground(new Color(160, 82, 45));
        returnButton.setBackground(new Color(218, 165, 32));
        returnButton.setText("TILBAGE TIL HOVEDMENUEN");
        returnButton.setFont(new Font("Impact", Font.PLAIN, 20));
        returnButton.setFocusable(false);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == returnButton) {
                    reward.stop();
                    StartMenu returnMenu = new StartMenu();
                    resultsFrame.dispose();

                }
            }
        });
        returnButton.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 4, false));
        returnButton.setOpaque(true);


        // Left side design
        ImageIcon lf1Icon = new ImageIcon("Gifs/f1top.gif");
        JLabel lf1 = new JLabel();
        lf1.setIcon(lf1Icon);

        ImageIcon lf2Icon = new ImageIcon("Gifs/f1bot.gif");
        JLabel lf2 = new JLabel();
        lf2.setIcon(lf2Icon);

        ImageIcon hansRIcon = new ImageIcon("Gifs/goldthumbs.gif");
        JLabel hansR = new JLabel();
        hansR.setIcon(hansRIcon);

        JPanel resultPanelTopLeft = new JPanel();
        resultPanelTopLeft.setBackground(new Color(0, 0, 23));
        resultPanelTopLeft.setBounds(0, 0, 300, 250);

        JPanel resultPanelCentralLeft = new JPanel();
        resultPanelCentralLeft.setBackground(new Color(0, 0, 23));
        resultPanelCentralLeft.setBounds(0, 250, 300, 270);

        JPanel resultPanelBottomLeft = new JPanel();
        resultPanelBottomLeft.setBackground(new Color(0, 0, 23));
        resultPanelBottomLeft.setBounds(0, 515, 300, 250);

        // Central side design
        ImageIcon check = new ImageIcon("Pictures/rewardcheck.png");
        JLabel checkLabel = new JLabel();
        checkLabel.setText(rewardsList[index]);
        checkLabel.setFont(new Font("Droid Sans Mono", Font.PLAIN, 40));
        checkLabel.setHorizontalTextPosition(JLabel.CENTER);
        checkLabel.setIcon(check);
        checkLabel.setVerticalAlignment(JLabel.CENTER);

        ImageIcon lightbeamIcon = new ImageIcon("Gifs/lightbeam.gif");
        JLabel lightbeam = new JLabel();
        lightbeam.setIcon(lightbeamIcon);

        ImageIcon mbag1icon = new ImageIcon("Gifs/sky3.gif");
        JLabel mbag1 = new JLabel();
        mbag1.setIcon(mbag1icon);
        mbag1.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon mbag2icon = new ImageIcon("Gifs/sky3.gif");
        JLabel mbag2 = new JLabel();
        mbag2.setIcon(mbag2icon);
        mbag2.setHorizontalAlignment(JLabel.CENTER);

        JPanel resultPanelTopCentral = new JPanel();
        resultPanelTopCentral.setBackground(new Color(0, 0, 23));
        resultPanelTopCentral.setBounds(300, 0, 600, 255);

        JPanel resultPanelCentral = new JPanel();
        resultPanelCentral.setBounds(300, 250, 600, 265);

        JPanel resultPanelBottomCentralLeft = new JPanel();
        resultPanelBottomCentralLeft.setBackground(new Color(0, 0, 23));
        resultPanelBottomCentralLeft.setBounds(300, 515, 300, 250);

        JPanel resultPanelBottomCentralRight = new JPanel();
        resultPanelBottomCentralRight.setBackground(new Color(0, 0, 23));
        resultPanelBottomCentralRight.setBounds(600, 515, 300, 250);

        // Right side design
        ImageIcon rf1Icon = new ImageIcon("Gifs/f1top.gif");
        JLabel rf1 = new JLabel();
        rf1.setIcon(rf1Icon);

        ImageIcon rf2Icon = new ImageIcon("Gifs/f1bot.gif");
        JLabel rf2 = new JLabel();
        rf2.setIcon(rf2Icon);

//        ImageIcon glistIcon = new ImageIcon("Gifs/glistfall.gif");
//        JLabel glist = new JLabel();
//        glist.setIcon(glistIcon);

        JPanel resultPanelTopRight = new JPanel();
        resultPanelTopRight.setBackground(new Color(0, 0, 23));
        resultPanelTopRight.setBounds(900, 0, 300, 250);

        JPanel resultPanelCentralRight = new JPanel();
        resultPanelCentralRight.setBackground(new Color(0, 0, 23));
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
//        resultPanelCentralRight.add(glist);
        resultPanelCentralRight.add(returnButton);
        resultsFrame.add(returnButton);
        resultsFrame.add(resultPanelTopRight);
        resultsFrame.add(resultPanelCentralRight);
        resultsFrame.add(resultPanelBottomRight);


        resultsFrame.setVisible(true);
    }

    public void decider() {
        if (index <= 4) {
            index = 0;
        }
        if (index >= 5 && index < 10) {
            index = 5;
        }
        if (index >= 10) {
            index = 10;

        }
    }
}






