package org.example.gui;
import org.example.game.Game;
import org.example.game.Piece;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
public class ChessFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel chessBoardPanel;
    private JPanel sidePanel;
    private JLabel currentPlayerLabel;
    private JButton restartButton;
    private JTextArea moveHistoryArea;
    private final JButton[][] buttons = new JButton[8][8];
    private final Game game;
    private List<Point> highlightedMoves = new ArrayList<>();
    private Point selectedSquare = null;
    public ChessFrame(Game game) {
        this.game = game;
        setTitle("Szachy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // MENU
        createMenuBar();

        // GŁÓWNY PANEL
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // PANEL SZACHOWNICY
        chessBoardPanel = new JPanel(new GridLayout(8, 8));
        initializeBoardButtons();
        mainPanel.add(chessBoardPanel, BorderLayout.CENTER);
        updateBoardIcons();
        // PANEL BOCZNY
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, 600));
        mainPanel.add(sidePanel, BorderLayout.EAST);

        // Label z aktualnym graczem
        currentPlayerLabel = new JLabel("Białe na ruchu");
        currentPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sidePanel.add(currentPlayerLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Przycisk restart
        restartButton = new JButton("Nowa gra");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> resetGame());
        sidePanel.add(restartButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Historia ruchów
        moveHistoryArea = new JTextArea(20, 15);
        moveHistoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(moveHistoryArea);
        sidePanel.add(scrollPane);
    }



    private void initializeBoardButtons() {
        chessBoardPanel.setLayout(new GridLayout(8, 8));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setOpaque(true);
                square.setBorderPainted(false);
                square.setFocusPainted(false);
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.DARK_GRAY);

                final int r = row;
                final int c = col;
                square.addActionListener(e -> onSquareClicked(r, c));

                buttons[row][col] = square;
                chessBoardPanel.add(square);
            }
        }
    }
    private void updateBoardIcons() {
        Icon dotIcon = new DotIcon(16, new Color(100, 100, 100, 180)); // mała szara kropka

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int finalCol = col;
                int finalRow = row;
                Piece piece = game.getPieceAt(row, col);


                boolean isSelected = selectedSquare != null && selectedSquare.x == col && selectedSquare.y == row;
                boolean isHighlighted = highlightedMoves.stream()
                        .anyMatch(p -> p.x == finalCol && p.y == finalRow);

                if (piece != null) {
                    buttons[row][col].setIcon(piece.getIcon());
                } else if (isHighlighted) {
                    buttons[row][col].setIcon(dotIcon); // tu kropka
                } else {
                    buttons[row][col].setIcon(null);
                }
                if (isSelected) {
                    buttons[row][col].setBackground(Color.YELLOW);
                } else {
                    buttons[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.DARK_GRAY);
                }
            }
        }
    }
    private void onSquareClicked(int row, int col) {
        Piece piece = game.getPieceAt(row,col);
        if(piece!=null && game.isWhoMoves() == piece.isWhite()){
            piece = null;
        }
        if (selectedSquare == null){
            if(piece == null )  {
                System.out.println("wybrano pole bez figury");
                selectedSquare = null;
            }

            else {
                this.highlightedMoves = piece.getLegalMoves(row,col, game.board);
                selectedSquare = new Point(col, row);

                System.out.println("wybrano" + piece.getName());
            }
        }

        else {
            System.out.println("CHCE WYKONAC RUCH");
            if(selectedSquare.x == col && selectedSquare.y == row){
                selectedSquare = null;
                highlightedMoves.clear();
            }
            else if (highlightedMoves.stream().anyMatch(p -> p.x == col && p.y == row)) {


                // Kliknięto na pole z legalnym ruchem => wykonujemy ruch
                boolean moved = game.movePiece(selectedSquare.y, selectedSquare.x, row, col);
                if (moved) {
                    // resetujemy stan po ruchu
                    selectedSquare = null;
                    highlightedMoves.clear();
                    // aktualizujemy labelki itp.
                    currentPlayerLabel.setText(game.isWhoMoves() ? "Czarne na ruchu" : "Białe na ruchu");
                }
            }
            else if (piece != null){
                highlightedMoves = piece.getLegalMoves(row,col, game.board);
                selectedSquare = new Point(col, row);
                System.out.println("wybrano" + piece.getName());
            }else {
                // Kliknięto na puste pole poza highlightem - anulujemy wybór
                selectedSquare = null;
                highlightedMoves.clear();
            }


        }
        updateBoardIcons();
    }

    private void resetGame() {
        currentPlayerLabel.setText("Białe na ruchu");
        moveHistoryArea.setText("");
        JOptionPane.showMessageDialog(this, "Gra została zresetowana.");
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Gra");
        JMenuItem newGameItem = new JMenuItem("Nowa gra");
        JMenuItem exitItem = new JMenuItem("Wyjście");

        newGameItem.addActionListener(e -> resetGame());
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Pomoc");
        JMenuItem aboutItem = new JMenuItem("O programie");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Szachy w Swingu\nAutor: Ty :)",
                "O programie", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }


}
