package com.example.interactivechessguinea;

public class King {
    private final String[] opposingPieces = {"p", "r", "b", "h", "q", "k"};
    // Array defining the opposition's piece's
    private final String[] currentPieces = {"p", "r", "b", "h", "q", "k"};
    // The original y coordinate of the current player's king
    public boolean checkValid(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        if (originalX < 0 || originalX > 7 || originalY < 0 || originalY > 7 || newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false;
        }
        // Variable used to identify if the player has castled in the current game
        if (colour == 'W') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toUpperCase();
        } else if (colour == 'B') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toLowerCase();
        }
        // Setting the current players piece's and king's original y coordinate to the correct ones based on the player's colour

        if (isChecked(newX, newY, colour, board))
            return false;
        // Checking if the move results in the king being in check, if so then the move is not valid
        boolean pieceInSquare = false;
        for (int i = 0; i < 6; i++) {
            if (board[newY][newX].charAt(0) == currentPieces[i].charAt(0)) {
                pieceInSquare = true;
                break;
            }
        }
        // Checking if the new square selected contains one of the current player's pieces, if so then the move is invalid
        // This could stop castling as castling "moves" the king to on top of the rook
        if (pieceInSquare) {
            return false;
        } else {
            if (newX == originalX + 1 && newY == originalY)
                return true;
            else if (newX == originalX + 1 && newY == originalY + 1)
                return true;
            else if (newX == originalX + 1 && newY == originalY - 1)
                return true;
            else if (newX == originalX && newY == originalY + 1)
                return true;
            else if (newX == originalX && newY == originalY - 1)
                return true;
            else if (newX == originalX - 1 && newY == originalY)
                return true;
            else if (newX == originalX - 1 && newY == originalY + 1)
                return true;
            else return newX == originalX - 1 && newY == originalY - 1;
            // Checking if the move is one of the 8 possibly king moves
        }
    }
    public boolean canCastle(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        boolean canCastle = true;
        if (originalX < 0 || originalX > 7 || originalY < 0 || originalY > 7 || newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false;
        }
        if (colour == 'W') {
            if (board[originalY][originalX].equals("K") && newY == originalY) {
                if (newX == 0 && newY == 7) {
                    for (int i = 1; i < 4; i++) {
                        if (!board[originalY][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        System.out.println("White long castle");
                        return true;
                    }
                } else if (newX == 7 && newY == 7) {
                    for (int i = 5; i < 7; i++) {
                        if (!board[originalY][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        System.out.println("White short castle");
                        return true;
                    }
                }
            }
        } else {
            if (board[originalY][originalX].equals("k") && originalY == newY) {
                if (newX == 0 && newY == 0) {
                    for (int i = 1; i < 4; i++) {
                        if (!board[originalY][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        System.out.println("Black long castle");
                        return true;
                    }
                } else if (newX == 7 && newY == 0) {
                    for (int i = 5; i < 7; i++) {
                        if (!board[originalY][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        System.out.println("Black short castle");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isChecked(int kingX, int kingY, char colour, String[][] board) {
        // Checking if the current player's king is in check
        if (kingX < 0 || kingX > 7 || kingY < 0 || kingY > 7) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            if (colour == 'W') {
                opposingPieces[i] = opposingPieces[i].toLowerCase();
            } else if (colour == 'B') {
                opposingPieces[i] = opposingPieces[i].toUpperCase();
            }
            // Setting the opposing players piece's to the correct colour based on the current players piece colour
        }
        if (colour == 'W') {
            if (kingX != 7 && kingX != 0 && kingY != 0) {
                if (board[kingY - 1][kingX + 1].charAt(0) == 'p' || board[kingY - 1][kingX - 1].charAt(0) == 'p') {
                    return true;
                }
            } else if (kingX == 7 && kingY != 0) {
                if (board[kingY - 1][kingX - 1].charAt(0) == 'p') {
                    return true;
                }
            } else if (kingX == 0 && kingY != 0) {
                if (board[kingY - 1][kingX + 1].charAt(0) == 'p') {
                    return true;
                }
            }
            // Checking if the white king is in check by a pawn
        } else if (colour == 'B') {
            if (kingX != 7 && kingX != 0 && kingY != 7) {
                if (board[kingY + 1][kingX + 1].charAt(0) == 'P' || board[kingY + 1][kingX - 1].charAt(0) == 'P')
                    return true;
            } else if (kingX == 7 && kingY != 7) {
                if (board[kingY + 1][kingX - 1].charAt(0) == 'P')
                    return true;
            } else if (kingX == 0 && kingY != 7) {
                if (board[kingY + 1][kingX + 1].charAt(0) == 'P')
                    return true;
            }
            // Checking if the black king is in check by a pawn
        }
        int tempX = kingX;
        int tempY = kingY;
        // Setting temporary x and y coordinate variables to the current coordinates of the king
        while (tempY < 7) {
            tempY++;
            // Iterates the variable in the direction being checked
            if (board[tempY][tempX].charAt(0) != opposingPieces[1].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[1].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check from the bottom on the y-axis
        }
        tempY = kingY;
        // Resets the value of the temporary variable that was changed
        while (tempY > 0) {
            tempY--;
            // Iterates the variable in the direction being checked
            if (board[tempY][tempX].charAt(0) != opposingPieces[1].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[1].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check from the top on the y-axis
        }
        tempY = kingY;
        // Resets the value of the temporary variable that was changed
        while (tempX < 7) {
            tempX++;
            // Iterates the variable in the direction being checked
            if (board[tempY][tempX].charAt(0) != opposingPieces[1].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[1].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check from the right on the x-axis
        }
        tempX = kingX;
        // Resets the value of the temporary variable that was changed
        while (tempX > 0) {
            tempX--;
            // Iterates the variable in the direction being checked
            if (board[tempY][tempX].charAt(0) != opposingPieces[1].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[1].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check from the left on the x-axis
        }
        // Checking if the king is in check from a rook or queen on the horizontal and vertical axis
        if (kingX > 1 && kingY > 0) {
            if (board[kingY - 1][kingX - 2].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX > 0 && kingY > 1) {
            if (board[kingY - 2][kingX - 1].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX > 1 && kingY < 7) {
            if (board[kingY + 1][kingX - 2].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX > 0 && kingY < 6) {
            if (board[kingY + 2][kingX - 1].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX < 6 && kingY < 7) {
            if (board[kingY + 1][kingX + 2].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX < 7 && kingY < 6) {
            if (board[kingY + 2][kingX + 1].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX < 6 && kingY > 0) {
            if (board[kingY - 1][kingX + 2].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        if (kingX < 7 && kingY > 1) {
            if (board[kingY - 2][kingX + 1].charAt(0) == opposingPieces[3].charAt(0)) {
                return true;
            }
        }
        // Checking if the king is in check from a knight in each possible position

        if (kingX < 7 && kingY < 7) {
            if (board[kingY + 1][kingX + 1].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingX < 7) {
            if (board[kingY][kingX + 1].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingX < 7 && kingY > 0) {
            if (board[kingY - 1][kingX + 1].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingY < 7) {
            if (board[kingY + 1][kingX].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingX > 0 && kingY < 7) {
            if (board[kingY + 1][kingX - 1].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingX > 0) {
            if (board[kingY][kingX - 1].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingX > 0 && kingY > 0) {
            if (board[kingY - 1][kingX - 1].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        if (kingY > 0) {
            if (board[kingY - 1][kingX].charAt(0) == opposingPieces[5].charAt(0)) {
                return true;
            }
        }
        tempX = kingX;
        // Resets the value of the temporary variable that was changed
        while (tempX < 7 && tempY < 7) {
            tempX++;
            tempY++;
            // Iterates the variables in the direction being checked
            if (board[tempY][tempX].charAt(0) != opposingPieces[2].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[2].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check on the diagonal at 135 degrees around clockwise
        }
        tempX = kingX;
        tempY = kingY;
        // Resets the values of the temporary variables that were changed
        while (tempX > 0 && tempY > 0) {
            tempX--;
            tempY--;
            if (board[tempY][tempX].charAt(0) != opposingPieces[2].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[2].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check on the diagonal at 315 degrees around clockwise
        }
        tempX = kingX;
        tempY = kingY;
        // Resets the values of the temporary variables that were changed
        while (tempX > 0 && tempY < 7) {
            tempX--;
            tempY++;
            if (board[tempY][tempX].charAt(0) != opposingPieces[2].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[2].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check on the diagonal at 225 degrees around clockwise
        }
        tempX = kingX;
        tempY = kingY;
        // Resets the values of the temporary variables that were changed
        while (tempX < 7 && tempY > 0) {
            tempX++;
            tempY--;
            if (board[tempY][tempX].charAt(0) != opposingPieces[2].charAt(0) && board[tempY][tempX].charAt(0) != opposingPieces[4].charAt(0) && board[tempY][tempX].charAt(0) != '_') {
                break;
                // Checks if there is a piece in the way of a possible check and if so then it breaks out of the loop
            } else if (board[tempY][tempX].charAt(0) == opposingPieces[2].charAt(0) || board[tempY][tempX].charAt(0) == opposingPieces[4].charAt(0)) {
                return true;
            }
            // Checks if the king is in check on the diagonal at 45 degrees around clockwise
        }
        // Checking if the king is in check from a bishop or queen on the diagonal axis
        return false;
    }
    public boolean isChecked(String[][] board, char colour) {
        int kingX = 0;
        int kingY = 0;
        if (colour == 'W') {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].equals("K")) {
                        kingY = i;
                        kingX = j;
                    }
                }
            }
        } else if (colour == 'B') {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].equals("k")) {
                        kingY = i;
                        kingX = j;
                    }
                }
            }
        }

        // If the location of the king is unknown this will find the location of the king and then check if it is in check
        return isChecked(kingX, kingY, colour, board);
    }
    public boolean isCheckmate(String[][] board, char colour) {
        GenerateMoves generateMoves = new GenerateMoves(true);
        generateMoves.makeMove(colour, board);
        return generateMoves.getMoveCounter() == 0;
    }
}