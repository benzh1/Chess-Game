package com.example.interactivechessguinea;

public class Bishop {
    private final String[] currentPieces = {"p", "r", "h", "b", "q", "k"};
    // Initializing an array containing the current players pieces
    private final String[] pieces = {"p", "r", "h", "b", "q", "k", "P", "R", "H", "B", "Q", "K"};
    // Initializing an array containing all the different pieces
    public boolean checkValid(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        if (originalX < 0 || originalX > 7 || newX < 0 || newX > 7 || originalY < 0 || originalY > 7 || newY < 0 || newY > 7) {
            return  false;
        }
        if (colour == 'W') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toUpperCase();
        } else if (colour == 'B') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toLowerCase();
        }
        // Changing the current pieces array to be the correct colour

        int tempX = originalX;
        int tempY = originalY;
        // Setting temporary x and y coordinates to the original x and y coordinates of the piece

        boolean pieceInSquare = false;
        for (int i = 0; i < 6; i++) {
            if (board[newY][newX].charAt(0) == currentPieces[i].charAt(0)) {
                pieceInSquare = true;
                break;
            }
        }
        // Checking if the square being moved to contains one of the current player's pieces

        if (pieceInSquare)
            return false;
        else {
            if (newX < originalX && newY < originalY) {
                for (int i = originalX; i > newX + 1; i--) {
                    tempX--;
                    tempY--;
                    // Iterating the temporary variables in the direction being moved
                    for (int j = 0; j < 12; j++) {
                        if (board[tempY][tempX].charAt(0) == pieces[j].charAt(0)) {
                            return false;
                        }
                        // Checking if there is a piece in the way of the move
                    }
                    // Checking if a bishop move is valid at 315 degrees
                }
                return tempY == newY + 1 && tempX == newX + 1;
            } else if (newX < originalX && newY > originalY) {
                for (int i = originalX; i > newX + 1; i--) {
                    tempX--;
                    tempY++;
                    // Iterating the temporary variables in the direction being moved
                    for (int j = 0; j < 12; j++) {
                        if (board[tempY][tempX].charAt(0) == pieces[j].charAt(0)) {
                            return false;
                        }
                        // Checking if there is a piece in the way of the move
                    }
                    // Checking if a bishop move is valid at 225 degrees
                }
                return tempY == newY - 1 && tempX == newX + 1;
            } else if (newX > originalX && newY < originalY) {
                for (int i = originalX; i < newX - 1; i++) {
                    tempX++;
                    tempY--;
                    // Iterating the temporary variables in the direction being moved
                    for (int j = 0; j < 12; j++) {
                        if (board[tempY][tempX].charAt(0) == pieces[j].charAt(0)) {
                            return false;
                        }
                        // Checking if there is a piece in the way of the move
                    }
                    // Checking if a bishop move is valid at 45 degrees
                }
                return tempY == newY + 1 && tempX == newX - 1;
            } else if (newX > originalX && newY > originalY) {
                for (int i = originalX; i < newX - 1; i++) {
                    tempX++;
                    tempY++;
                    // Iterating the temporary variables in the direction being moved
                    for (int j = 0; j < 12; j++) {
                        if (board[tempY][tempX].charAt(0) == pieces[j].charAt(0)) {
                            return false;
                        }
                        // Checking if there is a piece in the way of the move
                    }
                    // Checking if a bishop move is valid at 135 degrees
                }
                return tempY == newY - 1 && tempX == newX - 1;
            } else {
                return false;
            }
        }
    }
}