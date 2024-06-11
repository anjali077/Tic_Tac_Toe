import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}

class MainMenu extends JFrame implements ActionListener {
    private JButton singlePlayerButton;
    private JButton multiplayerButton;

    public MainMenu() {
        setTitle("Tic-Tac-Toe Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        getContentPane().setBackground(Color.DARK_GRAY);

        singlePlayerButton = createButton("Single Player");
        multiplayerButton = createButton("Multiplayer");

        add(singlePlayerButton);
        add(multiplayerButton);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        button.setFocusPainted(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singlePlayerButton) {
            new TicTacToeSinglePlayer();
        } else if (e.getSource() == multiplayerButton) {
            new TicTacToeMultiplayer();
        }
    }
}

class TicTacToeSinglePlayer extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true;

    public TicTacToeSinglePlayer() {
        setTitle("Tic-Tac-Toe Single Player");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        getContentPane().setBackground(Color.DARK_GRAY);

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) {
            return;
        }

        if (playerX) {
            clickedButton.setText("X");
            playerX = false;
            checkForWinner();
            if (!playerX) {
                aiMove();
            }
        }
    }

    private void aiMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText("O");
                    playerX = true;
                    checkForWinner();
                    return;
                }
            }
        }
    }

    private void checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) {
                announceWinner(buttons[i][0].getText());
                return;
            }
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) {
                announceWinner(buttons[0][i].getText());
                return;
            }
        }

        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) {
            announceWinner(buttons[0][0].getText());
        } else if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            announceWinner(buttons[0][2].getText());
        }

        boolean full = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    full = false;
                    break;
                }
            }
        }
        if (full) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            resetBoard();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerX = true;
    }
}

class TicTacToeMultiplayer extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true;

    public TicTacToeMultiplayer() {
        setTitle("Tic-Tac-Toe Multiplayer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        getContentPane().setBackground(Color.DARK_GRAY);

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) {
            return;
        }

        if (playerX) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }
        playerX = !playerX;

        checkForWinner();
    }

    private void checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) {
                announceWinner(buttons[i][0].getText());
                return;
            }
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) {
                announceWinner(buttons[0][i].getText());
                return;
            }
        }

        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) {
            announceWinner(buttons[0][0].getText());
        } else if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            announceWinner(buttons[0][2].getText());
        }

        boolean full = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    full = false;
                    break;
                }
            }
        }
        if (full) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            resetBoard();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerX = true;
    }
}
