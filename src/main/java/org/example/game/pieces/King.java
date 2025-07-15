package org.example.game.pieces;



import javax.swing.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.game.Piece;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class King extends Piece{
    public King(boolean isWhite) {
        super(isWhite);
    }



    @Override
    public String getName() {
        return "king";
    }

    @Override
    public List<Point> getLegalMoves(int row, int col, Piece[][] board) {
        List<Point> moves = new ArrayList<>();
        int[][] offsets = {
                {-1,-1}, {-1,0}, {-1,1},
                {0,-1}, {0,0}, {0,1},
                {1,-1}, {1,0}, {1,1},
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
        // sprawdzanie czy mozna robic roszade:
        //krotka roszada
        if(!this.haveEverMoved && board[row][col+1] == null && board[row][col+2] == null){
            Piece target = board[row][col+3];
            if (!target.gethaveEvermoved()){
                moves.add(new Point(col+2, row));
            }
        }
        if(!this.haveEverMoved && board[row][col-1] == null && board[row][col-2] == null && board[row][col-3] == null){
            Piece target = board[row][col-4];
            if (!target.gethaveEvermoved()){
                moves.add(new Point(col-2, row));
            }
        }

        return moves;
    }

    public String toString(){
        return "K";
    }
}