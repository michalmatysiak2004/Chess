package org.example.game.pieces;
import org.example.game.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    public Knight(boolean isWhite) {super(isWhite);}



    @Override
    public String getName() {
        return "knight";
    }


    @Override
    public List<Point> getLegalMoves(int row, int col, Piece[][] board) {
        List<Point> moves = new ArrayList<>();
        int[][] offsets = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Piece target = board[newRow][newCol];
                if (target == null || target.isWhite() != this.isWhite()) {
                    moves.add(new Point(newCol, newRow));
                }
            }
        }
        return moves;
    }


    public String toString(){
        return "S";
    }

}
