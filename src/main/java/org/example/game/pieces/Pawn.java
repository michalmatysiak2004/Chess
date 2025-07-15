package org.example.game.pieces;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.game.Game;
import org.example.game.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }


    @Override
    public String getName() {
        return "pawn";
    }

    @Override
    public List<Point> getLegalMoves(int row, int col, Piece[][] board) {
        List<Point> moves = new ArrayList<>();
        int direction = this.isWhite ? -1 : 1; // białe idą w górę (zmniejszają row), czarne w dół (zwiększają row)
        int startRow = this.isWhite ? 6 : 1;

        int forwardRow = row + direction;

        // Ruch do przodu o 1 pole jeśli jest puste
        if (forwardRow >= 0 && forwardRow < Game.BOARD_SIZE && board[forwardRow][col] == null) {
            moves.add(new Point(col, forwardRow));

            // Ruch o 2 pola do przodu z pozycji startowej jeśli oba pola puste
            int doubleForwardRow = row + 2 * direction;
            if (row == startRow && doubleForwardRow >= 0 && doubleForwardRow < Game.BOARD_SIZE
                    && board[doubleForwardRow][col] == null) {
                moves.add(new Point(col, doubleForwardRow));
            }
        }

        // Bicie na ukos: diagonale po jednym polu do przodu
        int[] diagCols = {col - 1, col + 1};
        for (int newCol : diagCols) {
            if (newCol >= 0 && newCol < Game.BOARD_SIZE && forwardRow >= 0 && forwardRow < Game.BOARD_SIZE) {
                Piece target = board[forwardRow][newCol];
                if (target != null && target.isWhite() != this.isWhite) {
                    moves.add(new Point(newCol, forwardRow));
                }
            }
        }

        return moves;
    }



    public String toString(){
        return "P";
    }
}