package org.example.game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.game.Piece;
import org.example.game.pieces.*;
import java.util.Scanner;
@NoArgsConstructor
@Getter
@Setter
public class Game {
    public static final int  BOARD_SIZE = 8;
    public  Piece[][] board;
    private boolean whoMoves; // 1 - white // 0 - black


    public void setupBoard() {
        // CZARNE FIGURY
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(false);
        }

        // BIAŁE FIGURY
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);

        for (int col = 0; col < 8; col++) {
            board[6][col] = new Pawn(true);
        }

        // Puste pola
        for (int row = 2; row <= 5; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
    }
    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol){
        Piece piece = board[fromRow][fromCol];
        if (piece instanceof King && Math.abs(toCol - fromCol) == 2){
            if(toCol > fromCol){ //krotka roszada
                Piece piece2 = board[fromRow][fromCol+3];
                board[toRow][toCol-1] = piece2;
                board[fromRow][fromCol+3] = null;
            }
            else {
                Piece piece2 = board[fromRow][fromCol-4];
                board[toRow][toCol+1] = piece2;
                board[fromRow][fromCol-4] = null;
            }
        }
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;
        piece.setHaveEverMoved(true);
        whoMoves = !whoMoves;
        return true;
    }
    public void startGame(){
        whoMoves = false;
        board = new Piece[8][8];
        setupBoard();
        gameloop();



    }
    public void gameloop(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.print("Naciśnij ENTER, aby kontynuować (lub wpisz 'exit'): ");
            String input = scanner.nextLine();

        }


    }
    public  void printBoard() {
        for (int row = 0; row < 8; row++) {
            System.out.print(8 - row + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    System.out.print(" " + piece.toString() + "  ");
                } else {
                    System.out.print(" .  ");
                }
            }
            System.out.println();
        }
        System.out.println("   a   b   c   d   e   f   g   h");
    }


    public Piece getPieceAt(int row, int col){
        return board[row][col];
    }


}
