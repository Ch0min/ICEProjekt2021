import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDesign implements ActionListener {     // Da programmet kører på en masse små handlinger.
// Gamedesigns instance variabler
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

// TIMER INSTANSER
    JProgressBar timerBar = new JProgressBar();
    JLabel time_label = new JLabel();               // 2 labels for timeren. Vil display timeren.
    JLabel seconds_left = new JLabel();             // Vil fungere som selve displayet for vores countdown timer.

// RESULTATER - muligvis bruges i results window class.
//    JTextField number_right = new JTextField();     //  Vil vises efter vi har calculated vores resultater.
//    JTextField percentage = new JTextField();       // Vil vise en procentdel af ens endelig score.

//  LYD GAME DESIGN
    SoundDesign soundDesign;    // Introduktion
    SoundDesign correctAnswer;
    SoundDesign wrongAnswer;


        String[] questions = {      // Indeholder spørgsmålene.
            "Hvilket årstal vandt Danmark deres første EM-trofæ?",  // 1
            "Hvad hedder den danske fodboldspiller 'Mæhle' til fornavn?"   // 2

    };

    String[][] options = {      // Holder på alle svarmulighederne, til vores spørgsmål.
            // Ved at gøre det på den her måde, så kan vi tilføje og slette spørgsmål, da det her program er dynamisk.
            {"1990", "1992", "1994", "1996"},                   // 1
            {"Tobias", "Joakim", "Frederik", "Jesper"},         // 2

    };

    char[] answers =    // Holder på alle de rigtige svar.
            {
                    'B',
                    'B'
            };

// Gamedesign variabler
    char guess;     // vil holde på gæt.
    char answer;    // vil holde på svar.
    int index;      // Bruges som en timer til at vide hvilket spørgsmål man er ved.
    int correct_guesses = 0;      // vil holde på antal korrekte gæt.
    int results;     // Holder på resultat.
    int seconds = 30;   // Timer til hvor mange sekunder man har ved hvert spørgsmål.

    Timer countdown = new Timer(1000, new ActionListener() {     // fx 2000ms = 2 sekunder.

       @Override
       public void actionPerformed(ActionEvent e) {
           seconds--;      // Efter hvert action performed method, så decrementer vi seconds med 1.
           seconds_left.setText(String.valueOf(seconds));
           
           if(seconds <= 0) {      // Hvis timeren rammer 0.
                displayAnswer();    // Vil display det rigtige svar, og disable alle muligheder.
           }
       }
   });  // Tilføje ');', så error går væk. Sikkert fordi Timeren mangler en parentes.


    // Gamedesign constructor.
    public GameDesign() {
        gameRun();
        soundDesign = new SoundDesign("Soundeffects/førstespørgsmål.wav");
        soundDesign.play();
        soundDesign.loop();
    }

    public void gameRun() {
        // Game fields
//        textField.setBounds(0, 475, 800, 20);      // 'setBounds' bruger man til at bestemme placeringen.
//        textField.setBackground(new Color(0, 50, 159));
//        textField.setFont(new Font("Copperplate", Font.BOLD, 15));  // // WindowsPC : "Copperplate Gothic Bold"
//        textField.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 1, true));
//        textField.setHorizontalAlignment(JTextField.CENTER);    // Juster placeringen af teksten.(Center)
//        textField.setFocusable(false);

// Game Text area display - vil vise det aktuelle spørgsmål.
        textArea.setBounds(0, 495, 800, 45);
        textArea.setLineWrap(true);           // 'Wrap' for at teksten ikke går udover skærmen, men i stedet går ned til næste linje.
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(0, 0, 250));
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setFont(new Font("Calibri", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 1, true));
        textArea.setEditable(false);


// Game Buttons
        // Button A
        buttonA.setBounds(0, 535, 150, 125);
        buttonA.setForeground(new Color(255, 185, 0));
        buttonA.setBackground(new Color(0, 0, 159));
        buttonA.setFont(new Font("Impact", Font.BOLD, 40));
        buttonA.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonA.addActionListener(this);
        buttonA.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));
        buttonA.setText("A:");
        buttonA.setOpaque(true);

        // Button B
        buttonB.setBounds(0, 657, 150, 125);
        buttonB.setForeground(new Color(255, 185, 0));
        buttonB.setBackground(new Color(0, 0, 159));
        buttonB.setFont(new Font("Impact", Font.BOLD, 40));
        buttonB.setFocusable(false);     // Vil gøre så en button ikke bliver highlighted når den bliver trykket på.
        buttonB.addActionListener(this);
        buttonB.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 2, true));
        buttonB.setText("B:");
        buttonB.setOpaque(true);

        // Button C
        buttonC.setBounds(400, 535, 150, 125);
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

// Game Panels
        // Question design
        answer_labelA.setBounds(150, 535, 250, 125);
        answer_labelA.setForeground(new Color(255, 255, 255));
        answer_labelA.setFont(new Font("Calibri", Font.PLAIN, 25));

        answer_labelB.setBounds(150, 657, 250, 125);
        answer_labelB.setForeground(new Color(255, 255, 255));
        answer_labelB.setFont(new Font("Calibri", Font.PLAIN, 25));

        answer_labelC.setBounds(550, 535, 250, 125);
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
        panel_B.setBounds(150, 657, 250, 125);
        panel_B.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 0), 3, true));


        JPanel panel_C = new JPanel();
        panel_C.setBackground(new Color(65, 105, 225));
        panel_C.setBounds(550, 535, 250, 125);
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

// Timer design
        seconds_left.setBounds(0,475,800,20);
        seconds_left.setForeground(new Color(255, 70, 70));
        seconds_left.setFont(new Font("Copperplate", Font.BOLD, 25));
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));
        timerBar.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 159), 5, false));
        timerBar.setValue(0);
        timerBar.setBounds(0,475,800,20);
//        timerBar.setStringPainted(true);
//        timerBar.setFont(new Font("Copperplate", Font.PLAIN, 20));

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
        gameFrame.add(timerBar);

      //  gameFrame.add(textField);
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

    public void fillTimerBar() {
        int count = 100;    // 30 sekunder.
        while (count > 0) {
            timerBar.setValue(count);
            try {
                Thread.sleep(300); // Thread: tillader et program til at køre mere effektivt ved at køre flere tasks på samme tid
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count -= 1;
            displayAnswer();
        }
        timerBar.setString("TIDEN ER UDLØBET");
    }

    public void nextQuestion() {
        int total_questions = questions.length;
        if(index >= total_questions) {
         //   results();

        } else {
            textField.setText("Spørgsmål " + (index + 1));       // Incrementer 'Question' hver gang der kommer et nyt spørgsmål.
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
        int correct_guesses = 0;      // vil holde på antal korrekte gæt.
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        correctAnswer  = new SoundDesign("Soundeffects/correct-answer.wav");
        wrongAnswer = new SoundDesign("Soundeffects/wrong-answer.wav");

        if (e.getSource() == buttonA) {      // Hvis en person klikker på Button A, hvad skal der så ske?
            answer = 'A';
            if (answer == answers[index]) {  // Hvis vores svar er equal til det svar der er stored i vores 'answers array' i et bestemt index, så incrementer vi 'correct_guess' med 1.
                correctAnswer.play();
                correct_guesses++;
            } else if (answer != answers[index]) {
                wrongAnswer.play();
            }
        }

        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correctAnswer.play();
                correct_guesses++;
            } else if (answer != answers[index]) {
                wrongAnswer.play();

            }
        }

        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correctAnswer.play();
                correct_guesses++;
            } else if (answer != answers[index]) {
                wrongAnswer.play();
            }
        }

        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correctAnswer.play();
                correct_guesses++;
            } else if (answer != answers[index]) {
                wrongAnswer.play();
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {
        countdown.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] == 'A')     // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelA.setForeground(new Color(240, 230, 140));
        if (answers[index] == 'B')    // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelB.setForeground(new Color(240, 230, 140));
        if (answers[index] == 'C')    // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelC.setForeground(new Color(240, 230, 140));
        if (answers[index] == 'D')     // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelD.setForeground(new Color(240, 230, 140));

        if (answers[index] != 'A')     // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelA.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'B')    // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelB.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'C')    // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelC.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'D')     // Hvis svaret ikke er 'A', hvad gør vi så?
            answer_labelD.setForeground(new Color(255, 0, 0));

        // Timer i metoden, da vi gerne vil have de forkerte svar converter til deres oprindelige farve igen, efter skift af hvert spørgsmål.
        Timer pause = new Timer(5000, new ActionListener() {     // 5 sekunders pause efter hver spørgsmål.

            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(255, 255, 255));     // Flip tekstfarverne tilbage til oprindelige farve.
                answer_labelB.setForeground(new Color(255, 255, 255));
                answer_labelC.setForeground(new Color(255, 255, 255));
                answer_labelD.setForeground(new Color(255, 255, 255));

                answer = ' ';    // Reset vores svar.
                seconds = 30;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);        // Vi skal nemlig huske at enable vores knapper igen her.
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;     // For at køre videre til næste spørgsmål.
                nextQuestion();
            }
        });  // Tilføje ');', så error går væk. Sikkert fordi Timeren mangler en parentes.
        pause.setRepeats(false);     // For at timeren kun aktiveres en gang.
        pause.start();               // Her får vi timeren til at starte.
    }
}







