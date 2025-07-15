package org.example;
import org.example.game.Game;

import org.example.gui.ChessFrame;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.getProperty("user.dir");
        Game game = new Game();
        SwingUtilities.invokeLater(() -> {
            ChessFrame frame = new ChessFrame(game);
            frame.setVisible(true);
        });

        game.startGame();
        game.setWhoMoves(true);


    }
}