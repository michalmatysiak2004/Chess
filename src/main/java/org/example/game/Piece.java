package org.example.game;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.awt.Point;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public abstract class Piece {
    protected boolean isWhite;
    protected boolean haveEverMoved = false;
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }
    public boolean gethaveEvermoved(){
        return this.haveEverMoved;
    }
    public ImageIcon getIcon() {
        String path = "/piecespng/" + (isWhite ? "white" : "black") + "-" + this.getName() +".png";
        URL resource = getClass().getResource(path);

        if (resource == null) {
            System.out.println("Nie znaleziono pliku: " + path);
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(resource);
        Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);

    }
    public abstract String getName();

    public abstract List<Point> getLegalMoves(int x, int y, Piece[][] board);

    protected boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}