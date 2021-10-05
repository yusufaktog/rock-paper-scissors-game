package core.concretes.game;

import core.concretes.handlers.*;
import core.concretes.players.Computer;
import core.concretes.symbols.*;
import core.supers.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author joseph
 */
public class GamePanel extends JPanel implements ActionListener {

    private int rounds;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;

    PriorityComparator comparator;

    Player humanPlayer;
    Computer computerPlayer;

    RockSymbol rockSymbol;
    PaperSymbol paperSymbol;
    ScissorsSymbol scissorsSymbol;

    GameSymbol[] symbols;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;

    JPanel computerPanel;
    JPanel humanPanel;

    JLabel humanLabel;
    JLabel computerLabel;

    JTextField roundCountText;
    JTextField computerScoreText;
    JTextField humanScoreText;
    JTextField chooseSymbolText;
    JTextField winnerInfoText;

    public GamePanel() {
        
        getRoundInput();
        loadPreferences();
        instantiateComponents();
        initializeComponents();
    }

    private void addComponent(JComponent component, JComponent parentComponent, int x, int y, int width, int height, LayoutManager layout, Color bColor) {

        component.setBounds(x, y, width, height);
        component.setLayout(layout);
        component.setBackground(bColor);

        if (component instanceof JLabel) { // to centralize symbol images
            component.setBorder(new EmptyBorder(40, 20, 0, 0));
            parentComponent.add(component, BorderLayout.CENTER);

        } else {
            parentComponent.add(component);
        }
    }

    private void addTextField(JPanel parentComponent, JTextField textField, int x, int y, int width, int height, Font font, String text, Color bColor, Color fColor) {

        textField.setBounds(x, y, width, height);
        textField.setFont(font);
        textField.setHorizontalAlignment(0);
        textField.setEditable(false);
        textField.setBackground(bColor);
        textField.setForeground(fColor);
        textField.setText(text);
        textField.setBorder(null);
        parentComponent.add(textField);
    }

    final void getRoundInput() {
        try {
            this.rounds = Math.abs(Integer.valueOf(JOptionPane.showInputDialog(null, "enter rounds ", "rounds ?", JOptionPane.QUESTION_MESSAGE)));

        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid round number.\nThe round value is set to its default value!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            this.rounds = 10;
        }
        
    }

    final void initializeComponents() {

        symbols = new GameSymbol[]{rockSymbol, paperSymbol, scissorsSymbol};

        addComponent(humanPanel, this, 450, 100, 275, 400, null, Color.darkGray);
        addComponent(computerPanel, this, 75, 100, 275, 400, null, Color.darkGray);

        addTextField(computerPanel, computerScoreText, 0, 0, 275, 50, new Font("Ink Free", Font.BOLD, 30), "Computer: " + computerPlayer.getScore(), Color.orange, Color.black);
        addTextField(humanPanel, humanScoreText, 0, 0, 275, 50, new Font("Ink Free", Font.BOLD, 30), "You: " + humanPlayer.getScore(), Color.orange, Color.black);
        addTextField(this, chooseSymbolText, 0, 520, 800, 50, new Font("Algerian", Font.ITALIC, 30), "Please choose a symbol", Color.black, Color.red);
        addTextField(this, roundCountText, 0, 0, 800, 40, new Font("Algerian", Font.ITALIC, 25), rounds + " rounds left", Color.black, Color.red);
        addTextField(this, winnerInfoText, 0, 40, 800, 40, new Font("Times New Roman", Font.ITALIC, 25), "", Color.black, Color.green);

        ButtonHandler.loadButton(paperButton, 100, 100, 100, 600, paperSymbol.getImage(), this, this);
        ButtonHandler.loadButton(scissorsButton, 100, 100, 350, 600, scissorsSymbol.getImage(), this, this);
        ButtonHandler.loadButton(rockButton, 100, 100, 600, 600, rockSymbol.getImage(), this, this);

        addComponent(humanLabel, humanPanel, 50, 0, 275, 350, null, Color.black);
        addComponent(computerLabel, computerPanel, 50, 0, 275, 350, null, Color.black);
    }

    final void instantiateComponents() {

        rockSymbol = new RockSymbol("rockSymbol", 1, ImageHandler.readImage("resources/rock.png"));
        paperSymbol = new PaperSymbol("paperSymbol", 2, ImageHandler.readImage("resources/paper.png"));
        scissorsSymbol = new ScissorsSymbol("scissorsSymbol", 3, ImageHandler.readImage("resources/scissors.png"));

        rockButton = new JButton();
        paperButton = new JButton();
        scissorsButton = new JButton();

        humanPanel = new JPanel();
        computerPanel = new JPanel();

        humanScoreText = new JTextField();
        computerScoreText = new JTextField();
        chooseSymbolText = new JTextField();
        roundCountText = new JTextField();
        winnerInfoText = new JTextField();

        humanPlayer = new Player("Yusuf");
        computerPlayer = new Computer("Computer");

        humanLabel = new JLabel();
        computerLabel = new JLabel();

        comparator = new PriorityComparator();

    }

    final void loadPreferences() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void updateScores() {
        humanScoreText.setText("You: " + humanPlayer.getScore());
        computerScoreText.setText("Computer: " + computerPlayer.getScore());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GameSymbol playerChoice = null;
        GameSymbol computerChoice;

        --rounds;
        roundCountText.setText(rounds + " rounds left");

        if (e.getSource() == rockButton) {
            playerChoice = rockSymbol;
        } else if (e.getSource() == paperButton) {
            playerChoice = paperSymbol;
        } else if (e.getSource() == scissorsButton) {
            playerChoice = scissorsSymbol;
        }

        int index = computerPlayer.chooseRandomIndex();
        computerChoice = symbols[index];

        humanLabel.setIcon(ImageHandler.getScaledImage(playerChoice.getImage(), 130, 130));
        computerLabel.setIcon(ImageHandler.getScaledImage(computerChoice.getImage(), 130, 130));

        playerChoice.setOwner(humanPlayer);
        computerChoice.setOwner(computerPlayer);

        Player winner = null;
        if (computerChoice.getPriority() == playerChoice.getPriority()) {
            winnerInfoText.setText("Tie!");

        } else {
            winner = comparator.determineWinner(playerChoice, computerChoice).getOwner();
            winnerInfoText.setText(winner.getName() + " wins");
            winner.setScore(winner.getScore() + 1);
            updateScores();
        }
        checkRoundCount();

    }

    public void checkRoundCount() {
        if (rounds == 0) {
            String winnerStr = computerPlayer.getScore() == humanPlayer.getScore() ? "Tie!" : (humanPlayer.getScore() > computerPlayer.getScore() ? "YOU WON" : "YOU LOST");
            int exit = JOptionPane.showOptionDialog(this, winnerStr, "Rounds Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"OK", "RESTART"}, null);
            System.out.println(exit);
            if (exit == 0 || exit == -1) {
                System.exit(0);
            } else if (exit == 1) {
                GameFrame.game.dispose(); // dispose currently running frame
                GameFrame.startNewGame();
            }
        }
    }
}