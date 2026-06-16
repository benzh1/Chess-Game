package com.example.interactivechessguinea;

public class Rook {
    private final String[] currentPieces = {"p", "r", "h", "b", "q", "k"};
    // Initializing and array contain the different pieces of the current player
    private final String[] pieces = {"p", "r", "h", "b", "q", "k", "P", "R", "H", "B", "Q", "K"};
    // Initializing an array containing each different piece
    public boolean checkValid(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        if (colour == 'W') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toUpperCase();
        } else if (colour == 'B') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toLowerCase();
        }
        // Setting the pieces in the current piece array to the correct colour
        int tempX = originalX;
        int tempY = originalY;
        // Setting temporary variables to the original x and y coordinates of the rook

        boolean pieceInSquare = false;
        for (int i = 0; i < 6; i++) {
            if (String.valueOf(board[newY][newX].charAt(0)).equals(currentPieces[i])) {
                pieceInSquare = true;
                break;
            }
        }
        // Checking if the square being moved to contains a piece belonging to the current player

        if (pieceInSquare) {
            return false;
        } else if (newX == originalX || newY == originalY) {
            if (newY < originalY) {
                for (int i = originalY; i > newY + 1; i--) {
                    tempY--;
                    // Iterating the temporary variable to simulate the piece moving square by square
                    for (int j = 0; j < 12; j++) {
                        if (board[tempY][tempX].charAt(0) == pieces[j].charAt(0)) {
                            return false;
                        }
                        // Checking if there are any pieces in the way of the move
                    }
                }
                // Checking if a move up on the y-axis is valid
                return true;
            } else if (newY > originalY) {
                for (int i = originalY; i < newY - 1; i++) {
                    tempY++;
                    // Iterating the temporary variable to simulate the piece moving square by square
                    for (int j = 0; j < 12; j++) {
                        if (String.valueOf(board[tempY][tempX].charAt(0)).equals(pieces[j])) {
                            return false;
                        }
                        // Checking if there are any pieces in the way of the move
                    }
                }
                // Checking if a move down on the y-axis is valid
                return true;
            } else if (newX < originalX) {
                for (int i = originalX; i > newX + 1; i--) {
                    tempX--;
                    // Iterating the temporary variable to simulate the piece moving square by square
                    for (int j = 0; j < 12; j++) {
                        if (String.valueOf(board[tempY][tempX].charAt(0)).equals(pieces[j])) {
                            return false;
                        }
                        // Checking if there are any pieces in the way of the move
                    }
                    // Checking if a move left on the x-axis is valid
                }
                return true;
            } else if (newX > originalX) {
                for (int i = originalX; i < newX - 1; i++) {
                    tempX++;
                    // Iterating the temporary variable to simulate the piece moving square by square
                    for (int j = 0; j < 12; j++) {
                        if (String.valueOf(board[tempY][tempX].charAt(0)).equals(pieces[j])) {
                            return false;
                        }
                        // Checking if there are any pieces in the way of the move
                    }
                    // Checking if a move left on the x-axis is valid
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}