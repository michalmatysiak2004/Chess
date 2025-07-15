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
public class Rook extends Piece{
    public Rook(boolean isWhite) {
        super(isWhite);
    }



    @Override
    public String getName() {
        return "rook";
    }

    @Override
    public List<Point> getLegalMoves(int row, int col, Piece[][] board) {
        System.out.println("Szukam ruchu dla wiezy");
        List<Point>  moves = new ArrayList<>();
        int[][] directions = {
                {1,0}, {0,1}, {-1,0} ,{0,-1}
        };

        for (int[] direction : directions){
            boolean searchformoves = true;
            int newRow = row;
            int newCol = col;
            while(searchformoves){
                 newRow +=   direction[0];
                 newCol +=   direction[1];
                if(newRow >= 0 && newCol>=0 && newRow < 8 && newCol < 8){
                    Piece target = board[newRow][newCol];
                    if(target == null) {
                        moves.add(new Point(newCol, newRow));
                        System.out.println("dodaje ruch na pole:" + newCol + "," + newRow);
                    }
                    else if (target.isWhite() != this.isWhite()){
                        moves.add(new Point(newCol, newRow));
                        searchformoves = false;
                    }
                    else searchformoves = false;
                }
                else searchformoves = false;
            }
        }

        return moves;
    }

    public String toString(){
        return "R";
    }
}