package com.example.interactivechessguinea;

public class Knight {
    private final String[] currentPieces = {"p", "r", "h", "b", "q", "k"};
    // Initialising an array with each different type of piece the current player could have
    public boolean checkValid(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        if (colour == 'W') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toUpperCase();
        } else if (colour == 'B') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toLowerCase();
        }
        // Setting the values in the current pieces array to the correct value to match the current player's colour

        boolean pieceInSquare = false;
        for (int i = 0; i < 6; i++) {
            if (String.valueOf(board[newY][newX].charAt(0)).equals(currentPieces[i])) {
                pieceInSquare = true;
                break;
            }
        }
        // Checking if one of the current player's pieces blocks the square that is being requested to move to

        if (pieceInSquare) {
            return false;
        } else {
            if (newX == originalX + 1 && newY == originalY + 2)
                return true;
            else if (newX == originalX + 1 && newY == originalY - 2)
                return true;
            else if (newX == originalX + 2 && newY == originalY + 1)
                return true;
            else if (newX == originalX + 2 && newY == originalY - 1)
                return true;
            else if (newX == originalX - 1 && newY == originalY + 2)
                return true;
            else if (newX == originalX - 1 && newY == originalY - 2)
                return true;
            else if (newX == originalX - 2 && newY == originalY + 1)
                return true;
            else return newX == originalX - 2 && newY == originalY - 1;
            // Checking if the move matches any of the possible moves of a knight
        }
    }
}