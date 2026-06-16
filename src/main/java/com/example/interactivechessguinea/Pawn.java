package com.example.interactivechessguinea;

public class Pawn {
    private final String[] opposingPieces = {"p", "r", "h", "b", "q", "k"};
    // Initializing an array to contain the pieces that the opponent would have
    private final String[] pieces = {"p", "r", "h", "b", "q", "k", "P", "R", "H", "B", "Q", "K"};
    // Initializing an array to contain each different piece for both players
    public boolean checkValid(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        boolean check1 = ((newX == originalX + 1 && originalX < 7) || (newX == originalX - 1 && originalX > 0));
        if (colour == 'B') {
            for (int i = 0; i < 6; i++)
                opposingPieces[i] = opposingPieces[i].toUpperCase();
            // Changing the opposing pieces to the correct colour
            if (originalY == 1 && newY == originalY + 2 && originalX == newX && board[newY - 1][newX].equals("_")) {
                boolean pieceInSquare = false;
                for (int i = 0; i < 12; i++) {
                    if (String.valueOf(board[newY][newX].charAt(0)).equals(pieces[i])) {
                        pieceInSquare = true;
                        break;
                    }
                    // Checking that the square being moved to doesn't contain any piece
                }
                // Checking if a two space move is allowed
                return !pieceInSquare;
            } else if (originalY > 0 && newY == originalY + 1 && originalX == newX && originalY < 8) {
                boolean pieceInSquare = false;
                for (int i = 0; i < 12; i++) {
                    if (String.valueOf(board[newY][newX].charAt(0)).equals(pieces[i])) {
                        pieceInSquare = true;
                        break;
                    }
                    // Checking that the square being moved to doesn't contain any piece
                }
                // Checking if a normal pawn move is allowed
                return !pieceInSquare;
            } else if (originalY > 0 && newY == originalY + 1 && originalY < 8 && check1) {
                boolean pieceInSquare = false;
                for (int i = 0; i < 6; i++) {
                    if (String.valueOf(board[newY][newX].charAt(0)).equals(opposingPieces[i])) {
                        pieceInSquare = true;
                        break;
                    }
                    // Checking that the square being moved to contains an opposing piece
                }
                // Checking if a capturing pawn move is allowed
                return pieceInSquare;
            } else
                return false;
        } else if (colour == 'W') {
            for (int i = 0; i < 6; i++)
                opposingPieces[i] = opposingPieces[i].toLowerCase();
            // Changing the opposing pieces to the correct colour
            if (originalY == 6 && newY == originalY - 2 && originalX == newX && board[newY + 1][newX].equals("_")) {
                boolean pieceInSquare = false;
                for (int i = 0; i < 12; i++) {
                    if (String.valueOf(board[newY][newX].charAt(0)).equals(pieces[i])) {
                        pieceInSquare = true;
                        break;
                    }
                    // Checking that the square being moved to doesn't contain any piece
                }
                // Checking if a two space move is allowed
                return !pieceInSquare;
            } else if (originalY < 7 && newY == originalY - 1 && originalX == newX && originalY > 0) {
                boolean pieceInSquare = false;
                for (int i = 0; i < 12; i++) {
                    if (String.valueOf(board[newY][newX].charAt(0)).equals(pieces[i])) {
                        pieceInSquare = true;
                        break;
                    }
                    // Checking that the square being moved to doesn't contain any piece
                }
                // Checking if a normal pawn move is allowed
                return !pieceInSquare;
            } else if (originalY < 7 && newY == originalY - 1 && originalY > 0 && check1) {
                boolean pieceInSquare = false;
                for (int i = 0; i < 6; i++) {
                    if (String.valueOf(board[newY][newX].charAt(0)).equals(opposingPieces[i])) {
                        pieceInSquare = true;
                        break;
                    }
                    // Checking that the square being moved to contains an opposing piece
                }
                // Checking if a capturing pawn move is allowed
                return pieceInSquare;
            } else
                return false;
        }
        return false;
    }
}