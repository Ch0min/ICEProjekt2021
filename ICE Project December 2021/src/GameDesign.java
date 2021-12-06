import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDesign extends JPanel implements ActionListener {     // Da programmet kører på en masse små handlinger.
// Gamedesigns Instanser
    JFrame gameFrame = new JFrame();
    JTextField textField = new JTextField();  // Vil holde the current spørgsmål, som man er på.
    JTextArea textArea = new JTextArea();    // Vil også holde på the current spørgsmål.

    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA = new JLabel();    // Labels til at holde på vores forskellige svarmuligheder.
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();

// Results & Pop-Up Vinduer Instanser
    JPanel resultPanel = new JPanel();
    JLabel resultLabel = new JLabel();
    JButton resultButton = new JButton();

    // Bruges til at farve optionPane vinduerne.
    UIManager UI;

// Timer Instanser
  //  Timer timerBarline;
  //  JProgressBar timerBar;
    JLabel seconds_left = new JLabel();             // Vil fungere som selve displayet for vores countdown timer.

// Audio Instanser
    SoundDesign soundDesign;    // Introduktion
    SoundDesign correctAnswer;
    SoundDesign wrongAnswer;
    SoundDesign finalAnswer;
    SoundDesign clockRanOut;

// Spørgsmåls Instans Lister
    String[] questions = {      // Indeholder spørgsmålene.
            "Hvilket apparat kaldte man tidligere for en datamat?",                                             // 1
            "I hvilket årstal vandt Danmarks herrelandshold deres første fodbold EM-trofæ?",                // 2
            "Hvor mange dele består en trilogi af?",                                                        // 3
            "Hvad spiser Skipper Skræk når han har brug for ekstra kræfter?",                               // 4
            "Hvilket sprog taler man i Østrig?",                                                          // 5
            "Hvilket af disse dyr hører IKKE til kattefamilien",                                                 // 6
            "Hvad måler man elektrisk spænding i?",                                                         // 7
            "Hvad kalder man et dyr der bliver ædt af andre dyr?",                                          // 8
            "Hvem blev efterladt i Nilen som baby?",                                                        // 9
            "Hvilken dansk skuespiller spiller hovedrollen i den amerikanske film 'Shot Caller fra 2017?",  // 10
            "Hvor mange øer består Danmark af?",                                                            // 11
            "Hvad samler en numismatiker på?",                                                              // 12
            "I hvilket af kroppens led sidder 'patella'?",                                                   // 13
            "Hvilket materiale er man berømt for at producere og forarbejde i Murano i det nordlige Italien?", // 14
            "Hvilket af følgende er ikke et vinmærke?"                                              // 15


    };

    String[][] options = {      // Holder på alle svarmulighederne, til vores spørgsmål.
            // Ved at gøre det på den her måde, så kan vi tilføje og slette spørgsmål, da det her program er dynamisk.
            {" Mobiltelefon", " MP3-afspiller", " Lommeregner", " Computer"},   // 1
            {" 1990", " 1992", " 1994", " 1996"},                   // 2
            {" 1", " 2", " 3", " 4"},                               // 3
            {" Gulerødder", " Kartofler", " Tomater", " Spinat"},   // 4
            {" Tysk", " Østrigsk", " Fransk", " Italiensk"},        // 5
            {" Leopard", " Los", " Gazelle", " Gepard"},            // 6
            {" Watt", " Volt", " Ohm", " Maxwell"},                 // 7
            {" Rovdyr", " Avlsdyr", " Æddyr", " Byttedyr"},         // 8
            {" Jesus", " Muhammed", " Moses", " Elijah"},           // 9
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
                    'B',    // 2
                    'C',    // 3
                    'D',    // 4
                    'A',    // 5
                    'C',    // 6
                    'B',    // 7
                    'D',    // 8
                    'C',    // 9
                    'D',    // 10
                    'C',    // 11
                    'A',     // 12
                    'B',     // 13
                    'D',     // 14
                    'A'       // 15
            };

// Pengebeløb liste
    String[] rewardsList = {"1000 KR", "2000 KR", "3000 KR", "4000 KR", "5000 KR", "8000 KR", "12000 KR", "20000 KR",
            "32000 KR", "50000 KR", "75000 KR", "125000 KR", "250000 KR", "500000 KR", "1 MILLION KR"};

// Gamedesign variabler
    private char answer;    // vil holde på svar.
    private int index;      // Bruges som en timer til at vide hvilket spørgsmål man er ved.
    private int total_questions = questions.length;
    private int correct_guesses = 0;      // vil holde på antal korrekte gæt.
    private int results;     // Holder på resultat.
    private int seconds = 30;   // Timer til hvor mange sekunder man har ved hvert spørgsmål.

// Timer Design
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


    // Gamedesign constructor.
    public GameDesign() {
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
        textField.setFont(new Font("Copperplate", Font.BOLD, 20));  // // WindowsPC : "Copperplate Gothic Bold"
        textField.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 255), 1, true));
        textField.setHorizontalAlignment(JTextField.CENTER);    // Juster placeringen af teksten.(Center)
        textField.setFocusable(false);

// Game Text area display - vil vise det aktuelle spørgsmål.
        textArea.setBounds(0, 495, 800, 45);
        textArea.setLineWrap(true);           // 'Wrap' for at teksten ikke går udover skærmen, men i stedet går ned til næste linje.
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(0, 0, 250));
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setFont(new Font("Calibri", Font.PLAIN, 19));
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
        buttonA.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));
        buttonA.setText("A:");
        buttonA.setOpaque(true);

        // Button B
        buttonB.setBounds(400, 540, 150, 120);
        buttonB.setForeground(new Color(255, 185, 0));
        buttonB.setBackground(new Color(0, 0, 159));
        buttonB.setFont(new Font("Impact", Font.BOLD, 40));
        buttonB.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonB.addActionListener(this);
        buttonB.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));
        buttonB.setText("B:");
        buttonB.setOpaque(true);

        // Button C
        buttonC.setBounds(0, 657, 150, 125);
        buttonC.setForeground(new Color(255, 185, 0));
        buttonC.setBackground(new Color(0, 0, 159));
        buttonC.setFont(new Font("Impact", Font.BOLD, 40));
        buttonC.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonC.addActionListener(this);
        buttonC.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));
        buttonC.setText("C:");
        buttonC.setOpaque(true);

        // Button D
        buttonD.setBounds(400, 657, 150, 125);
        buttonD.setForeground(new Color(255, 185, 0));
        buttonD.setBackground(new Color(0, 0, 159));
        buttonD.setFont(new Font("Impact", Font.BOLD, 40));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));
        buttonD.setText("D:");
        buttonD.setOpaque(true);

        // Pengecheck button
        resultPanel = new JPanel();
        resultPanel.setFont(new Font("Calibri", Font.BOLD, 15));
        resultLabel = new JLabel("SE DINE RESULTATER: ");
        resultLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        resultButton = new JButton("PENGECHECK");
        resultButton.setFont(new Font("Impact", Font.BOLD, 30));
        resultButton.addActionListener(this);
        resultPanel.add(resultLabel);
        resultPanel.add(resultButton);


// Game Panels
        // Question design
        answer_labelA.setBounds(150, 535, 250, 125);
        answer_labelA.setForeground(new Color(255, 255, 255));
        answer_labelA.setFont(new Font("Calibri", Font.PLAIN, 25));

        answer_labelB.setBounds(550, 535, 250, 125);
        answer_labelB.setForeground(new Color(255, 255, 255));
        answer_labelB.setFont(new Font("Calibri", Font.PLAIN, 25));

        answer_labelC.setBounds(150, 657, 250, 125);
        answer_labelC.setForeground(new Color(255, 255, 255));
        answer_labelC.setFont(new Font("Calibri", Font.PLAIN, 25));

        answer_labelD.setBounds(550, 657, 250, 125);
        answer_labelD.setForeground(new Color(255, 255, 255));
        answer_labelD.setFont(new Font("Calibri", Font.PLAIN, 25));

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

        JPanel menuPanelTopLeft = new JPanel();
        menuPanelTopLeft.setBackground(new Color(0, 0, 159));
        menuPanelTopLeft.setBounds(0, 0, 400, 200);

        JPanel menuPanelMidLeft = new JPanel();
        menuPanelMidLeft.setBackground(new Color(0, 0, 159));
        menuPanelMidLeft.setBounds(0, 200, 400, 275);

        // Middle design
        ImageIcon iconLightRight = new ImageIcon("Pictures/lightrightgd.png");
        JLabel lightRight = new JLabel();
        lightRight.setIcon(iconLightRight);
        lightRight.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconHansgd = new ImageIcon("Pictures/hansventergd.png");
        JLabel hansgd = new JLabel();
        hansgd.setIcon(iconHansgd);
        hansgd.setVerticalAlignment(JLabel.CENTER);

        JPanel menuPanelCentralTop = new JPanel();
        menuPanelCentralTop.setBackground(new Color(0, 0, 159));
        menuPanelCentralTop.setBounds(400, 0, 400, 200);

        JPanel menuPanelCentralMid = new JPanel();
        menuPanelCentralMid.setBackground(new Color(0, 0, 159));
        menuPanelCentralMid.setBounds(400, 200, 400, 275);

// Optionpane/Pop-Up design
    //    UI = new UIManager();
    //    UI.put("OptionPane.background", new ColorUIResource(197,179,88));
    //    UI.put("Panel.background", new ColorUIResource(197,179,88));

        seconds_left.setBounds(0, 475, 800, 20);
        seconds_left.setForeground(new Color(255, 185, 0));
        seconds_left.setBackground(new Color(0, 50, 159));
        seconds_left.setFont(new Font("Copperplate", Font.BOLD, 30));   // Windows: "Copperplate Gothic Bold"
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
       // gameFrame.add(timerBar);

        gameFrame.add(textField);
        gameFrame.add(textArea);

        //Buttons
        gameFrame.add(buttonA);
        gameFrame.add(buttonB);
        gameFrame.add(buttonC);
        gameFrame.add(buttonD);

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
        menuPanelTopLeft.add(lightLeft);
        menuPanelMidLeft.add(spillergd);
        gameFrame.add(menuPanelTopLeft);
        gameFrame.add(menuPanelMidLeft);

        // Middle side
        menuPanelCentralTop.add(lightRight);
        menuPanelCentralMid.add(hansgd);
        gameFrame.add(menuPanelCentralTop);
        gameFrame.add(menuPanelCentralMid);

        gameFrame.setVisible(true);
        nextQuestion();

    }

    public void nextQuestion() {
        if (index >= total_questions) {
            // Pengecheck popup vindue
            String[] options = {"Afslut"};
            JPanel resultPanel = new JPanel();
            resultPanel.setFont(new Font("Calibri", Font.BOLD, 15));
            JLabel resultLabel = new JLabel("SE DINE RESULTATER: ");
            resultLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
            JButton resultButton = new JButton("PENGECHECK");
            resultButton.setFont(new Font("Impact", Font.BOLD, 30));
            resultPanel.add(resultLabel);
            resultPanel.add(resultButton);
            JOptionPane.showOptionDialog(gameFrame, resultPanel, "RESULTAT", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.exit(0);


        } else {
            textField.setText("Spørgsmål " + (index + 1));       // Incrementer 'Spørgsmål' hver gang der kommer et nyt spørgsmål.
            textField.setFont(new Font("Copperplate", Font.BOLD, 30));
            textField.setForeground(new Color(212,175,55));
            JOptionPane.showOptionDialog(gameFrame, "SPØRGSMÅL TIL: " + rewardsList[index], "PENGEBELØB", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            textArea.setText(questions[index]);                  // Hver gang index bliver incremented, så skal programmet skifte til næste spørgsmål.
            answer_labelA.setText(options[index][0]);            // Bruger vores options 2d array, for at hente svarmulighederne.
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            countdown.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      //  int correct_guesses = 0;      // vil holde på antal korrekte gæt. Bruger vi ikke rigtigt endnu
        correctAnswer = new SoundDesign("Soundeffects/correct.wav");
        wrongAnswer = new SoundDesign("Soundeffects/wrong.wav");

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

            if (e.getSource() == buttonA) {      // Hvis en person klikker på Button A, hvad skal der så ske?
                answer = 'A';
                if (answer == answers[index]) {  // Hvis vores svar er equal til det svar der er stored i vores 'answers array' i et bestemt index, så incrementer vi 'correct_guess' med 1.
                    correctAnswer.play();
           //         correct_guesses++;
                } else if (answer != answers[index]) {
                    wrongAnswer.play();
                }
            }

            if (e.getSource() == buttonB) {
                answer = 'B';
                if (answer == answers[index]) {
                    correctAnswer.play();
               //     correct_guesses++;
                } else if (answer != answers[index]) {
                    wrongAnswer.play();
                }
            }

            if (e.getSource() == buttonC) {
                answer = 'C';
                if (answer == answers[index]) {
                    correctAnswer.play();
                //    correct_guesses++;
                } else if (answer != answers[index]) {
                    wrongAnswer.play();
                }
            }

            if (e.getSource() == buttonD) {
                answer = 'D';
                if (answer == answers[index]) {
                    correctAnswer.play();
                 //   correct_guesses++;
                } else if (answer != answers[index]) {
                    wrongAnswer.play();
                }
            }

        if (e.getSource() == resultButton) {
            gameFrame.dispose();    // Lukker menu vinduet.
            soundDesign.stop();
            ResultsWindow resultsWindow = new ResultsWindow();   // Når der klikkes på spilknappen, så åbner der et nyt vindue i GameDesign.
        }
        displayAnswer();
    }

    public void displayAnswer() {
        countdown.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        // Skifter farve
        if (answers[index] == 'A')     // Hvis svaret er 'A', hvad gør vi så?
            answer_labelA.setForeground(new Color(255,255,255));
        if (answers[index] == 'B')    // Hvis svaret er 'B', hvad gør vi så?
            answer_labelB.setForeground(new Color(255,255,255));
        if (answers[index] == 'C')    // Hvis svaret er 'C', hvad gør vi så?
            answer_labelC.setForeground(new Color(255,255,255));
        if (answers[index] == 'D')     // Hvis svaret er 'D', hvad gør vi så?
            answer_labelD.setForeground(new Color(255,255,255));

        if (answers[index] != 'A')     // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelA.setForeground(new Color(255,255,255));
        if (answers[index] != 'B')    // Hvis svaret ikke er 'B', hvad gør vi så?
            answer_labelB.setForeground(new Color(255,255,255));
        if (answers[index] != 'C')    // Hvis svaret ikke er 'C', hvad gør vi så?
            answer_labelC.setForeground(new Color(255,255,255));
        if (answers[index] != 'D')     // Hvis svaret ikke er 'D', hvad gør vi så?
            answer_labelD.setForeground(new Color(255,255,255));

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
        Timer pause = new Timer(6000, new ActionListener() {     // 5 sekunders pause efter hver spørgsmål.

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

                // Hvis svaret er rigtigt så fortsæt.
                if (answer == answers[index]) {
                    index++;     // For at køre videre til næste spørgsmål.
                    nextQuestion();

                    // Hvis svaret er forkert. Så åben resultat vindue.
                } else if (answer != answers[index]) {
                    // Pengecheck popup vindue
                    String[] options = {"Afslut"};
                    JOptionPane.showOptionDialog(gameFrame, resultPanel, "RESULTAT", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    gameFrame.dispose();

                }
            }
        });  // Tilføje ');', så error går væk. Sikkert fordi Timeren mangler en parentes.
        pause.setRepeats(false);     // For at timeren kun aktiveres en gang.
        pause.start();               // Her får vi timeren til at starte.
    }

}







