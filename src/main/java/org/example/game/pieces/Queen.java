package org.example.game.pieces;

import javax.swing.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.game.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Queen extends Piece{
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String getName() {
        return "queen";
    }


    @Override
    public List<Point> getLegalMoves(int row, int col, Piece[][] board) {
        List<Point> moves = new ArrayList<>();
        int [][] directions = {
                {1,0}, {0,1}, {-1,0} ,{0,-1},
                {1,1}, {1, -1}, {-1,1}, {-1,-1}
        };

        for(int[] direction : directions){
            int newRow = row ;
            int newCol = col;
            boolean searchformoves = true;
            while(searchformoves){
                newRow +=  direction[0];
                newCol += direction[1];
                if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                    Piece target = board[newRow][newCol];
                    if (target == null ) {
                        moves.add(new Point(newCol, newRow));
                    }else if (target.isWhite() != this.isWhite()){
                        moves.add(new Point(newCol, newRow));
                        searchformoves = false;
                    }else searchformoves = false;

                }else searchformoves = false;
            }


        }
        return moves;
    }

    public String toString(){
        return "Q";
    }
}
