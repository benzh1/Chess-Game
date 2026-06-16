package com.example.interactivechessguinea;

public class GenerateMoves {
    private char colour;
    // The colour of the side the moves are being generated for
    private final String[][] tempBoard = new String[8][8];
    private final String[][] staticBoard = new String[8][8];
    // Temporary board which the current board is copied onto so that moves can be simulated without any change
    private final String[] currentPieces = {"P", "R", "B", "H", "Q", "K"};
    // Contains the current pieces of the current player
    private final String[] pieces = {"P", "R", "B", "H", "Q", "K","p", "r", "b", "h", "q", "k"};
    // All the different pieces
    private int x;
    // The current x coordinate being searched
    private int y;
    // The current y coordinate being searched
    private int moveCounter = 0;
    private final boolean isCheckmating;
    private final King king = new King();
    private boolean canCastle = true;
    public GenerateMoves(boolean isCheckmating) {
        // For checking for a checkmate
        this.isCheckmating = isCheckmating;
    }
    public GenerateMoves() {
        isCheckmating = false;
        //System.out.println("Generating");
    }
    public void makeMove(char colour, String[][] tempBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.tempBoard[j][i] = tempBoard[j][i];
                staticBoard[j][i] = tempBoard[j][i];
            }
        }
        // Copying the current game board onto the simulated temporary board
        this.colour = colour;

        if (colour == 'W') {
            for (int i = 0; i < 6; i++) {
                currentPieces[i] = currentPieces[i].toUpperCase();
            }
        } else {
            for (int i = 0; i < 6; i++) {
                currentPieces[i] = currentPieces[i].toLowerCase();
            }
        }
        // Setting the current pieces array to contain the correct pieces related the player the moves are being generated for
        for (y = 0; y < 8; y++) {
            for (x = 0; x < 8; x++) {
                if (tempBoard[y][x].charAt(0) == currentPieces[0].charAt(0)) {
                    makePawnMove();
                } else if (tempBoard[y][x].charAt(0) == currentPieces[1].charAt(0)) {
                    makeRookMove();
                } else if (tempBoard[y][x].charAt(0) == currentPieces[2].charAt(0)) {
                    makeBishopMove();
                } else if (tempBoard[y][x].charAt(0) == currentPieces[3].charAt(0))
                    makeKnightMove();
                else if (tempBoard[y][x].charAt(0) == currentPieces[4].charAt(0)) {
                    makeQueenMove();
                } else if (tempBoard[y][x].charAt(0) == currentPieces[5].charAt(0)) {
                    makeKingMove();
                }
            }
        }
        // Searching through the board and at each square checking what piece it contains
        // When it contains a piece making all the possible moves with that piece
    }
    public void makePawnMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        if (colour == 'W' && y > 0) {
            // Checking the colour of the pawn and that it is not at the end of the board
            if (tempBoard[y - 1][x].equals("_")) {
                // Checking that the square in front of the pawn is empty
                if (y == 6 && tempBoard[y - 2][x].equals("_")) {
                    // Checking that the second square in front of the pawn is empty, and it is on its starting square
                    tempBoard[y - 2][x] = currentPieces[0];
                    tempBoard[y][x] = "_";
                    // Changing the temporary board
                    validateMove(tempBoard);
                    // Checking if the move puts the players king in check
                    // if it doesn't then the move is added to the game tree
                    tempBoard[y - 2][x] = "_";
                    tempBoard[y - 1][x] = currentPieces[0];
                    // Resetting the temporary board so that the pawn has only moved one space
                    // Checking if the move puts the players king in check
                    // if it doesn't then the move is added to the game tree
                    // Resetting the temporary board back to its original position
                } else {
                    // If the initial 2 square move is not possible then this is executed
                    tempBoard[y][x] = "_";
                    tempBoard[y - 1][x] = currentPieces[0];
                    if (y - 1 == 0) {
                        tempBoard[y - 1][x] = currentPieces[4];
                    }
                    // Changing the temporary board
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    // Resetting the temporary board back to its original position
                }
                validateMove(tempBoard);
                tempBoard[y - 1][x] = "_";
                tempBoard[y][x] = currentPieces[0];
            }
            if (x < 7 && !tempBoard[y - 1][x + 1].equals("_")) {
                // Checking that it is possible for a capturing move to occur
                boolean pieceInSquare = false;
                for (int i = 0; i < 6; i++) {
                    if (currentPieces[i].charAt(0) == tempBoard[y - 1][x + 1].charAt(0)) {
                        pieceInSquare = true;
                        break;
                    }
                }
                // Checking that the square being moved to doesn't contain one of the current player's pieces
                if (!pieceInSquare) {
                    tempBoard[y - 1][x + 1] = currentPieces[0];
                    tempBoard[y][x] = "_";
                    if (y - 1 == 0) {
                        tempBoard[y - 1][x + 1] = currentPieces[4];
                    }
                    // Changing the temporary board
                    validateMove(tempBoard);
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                    // Resetting the temporary board back to its original position
                }
            }
            if (x > 0 && !tempBoard[y - 1][x - 1].equals("_")) {
                // Checking that it is possible for a capturing move to occur
                boolean pieceInSquare = false;
                for (int i = 0; i < 6; i++) {
                    if (currentPieces[i].charAt(0) == tempBoard[y - 1][x - 1].charAt(0)) {
                        pieceInSquare = true;
                        break;
                    }
                }
                // Checking that the square being moved to doesn't contain one of the current player's pieces
                if (!pieceInSquare) {
                    tempBoard[y - 1][x - 1] = currentPieces[0];
                    tempBoard[y][x] = "_";
                    if (y - 1 == 0) {
                        tempBoard[y - 1][x - 1] = currentPieces[4];
                    }
                    // Changing the temporary board
                    validateMove(tempBoard);
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                    // Resetting the temporary board back to its original position
                }
            }
        } else if (colour == 'B' && y < 7) {
            // Checking the colour of the pawn and that it is not at the end of the board
            if (tempBoard[y + 1][x].equals("_")) {
                // Checking that the square in front of the pawn is empty
                if (y == 1 && tempBoard[y + 2][x].equals("_")) {
                    // Checking that the second square in front of the pawn is empty, and it is on its starting square
                    tempBoard[y + 2][x] = currentPieces[0];
                    tempBoard[y][x] = "_";
                    // Changing the temporary board
                    validateMove(tempBoard);
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    tempBoard[y + 2][x] = "_";
                    tempBoard[y + 1][x] = currentPieces[0];
                    // Resetting the temporary board so that the pawn has only moved one space
                    // Checking if the move puts the players king in check
                    // if it doesn't then the move is added to the game tree
                    // Resetting the temporary board back to its original position
                } else {
                    // If the initial 2 square move is not possible then this is executed
                    tempBoard[y][x] = "_";
                    tempBoard[y + 1][x] = currentPieces[0];
                    if (y + 1 == 7) {
                        tempBoard[y + 1][x] = currentPieces[4];
                    }
                    // Changing the temporary board
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    // Resetting the temporary board back to its original position
                }
                validateMove(tempBoard);
                tempBoard[y + 1][x] = "_";
                tempBoard[y][x] = currentPieces[0];
            }
            if (x < 7 && !tempBoard[y + 1][x + 1].equals("_")) {
                // Checking that it is possible for a capturing move to occur
                boolean pieceInSquare = false;
                for (int i = 0; i < 6; i++) {
                    if (currentPieces[i].charAt(0) == tempBoard[y + 1][x + 1].charAt(0)) {
                        pieceInSquare = true;
                        break;
                    }
                }
                // Checking that the square being moved to doesn't contain one of the current player's pieces
                if (!pieceInSquare) {
                    tempBoard[y + 1][x + 1] = currentPieces[0];
                    tempBoard[y][x] = "_";
                    if (y + 1 == 7) {
                        tempBoard[y + 1][x + 1] = currentPieces[4];
                    }
                    // Changing the temporary board
                    validateMove(tempBoard);
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                    // Resetting the temporary board back to its original position
                }
            }
            if (x > 0 && !tempBoard[y + 1][x - 1].equals("_")) {
                // Checking that it is possible for a capturing move to occur
                boolean pieceInSquare = false;
                for (int i = 0; i < 6; i++) {
                    if (currentPieces[i].charAt(0) == tempBoard[y + 1][x - 1].charAt(0)) {
                        pieceInSquare = true;
                        break;
                    }
                }
                // Checking that the square being moved to doesn't contain one of the current player's pieces
                if (!pieceInSquare) {
                    tempBoard[y + 1][x - 1] = currentPieces[0];
                    tempBoard[y][x] = "_";
                    if (y + 1 == 7) {
                        tempBoard[y + 1][x - 1] = currentPieces[4];
                    }
                    // Changing the temporary board
                    validateMove(tempBoard);
                    // Checking if the move puts the players king in check
                    // if it doesn't check, then the move is added to the game tree
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                    // Resetting the temporary board back to its original position
                }
            }
        }
    }
    public void makeRookMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        if (x != 7) {
            rightMove(1);
        }
        if (x != 0) {
            leftMove(1);
        }
        if (y != 7) {
            upMove(1);
        }
        if (y != 0) {
            downMove(1);
        }
        if (colour == 'W') {
            if (tempBoard[7][4].equals("K")) {
                if (x == 0 && y == 7) {
                    for (int i = 1; i < 4; i++) {
                        if (!tempBoard[y][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        tempBoard[7][0] = "_";
                        tempBoard[7][3] = "R";
                        tempBoard[7][2] = "K";
                        tempBoard[7][4] = "_";
                        validateMove(tempBoard);
                        tempBoard[7][0] = "R";
                        tempBoard[7][3] = "_";
                        tempBoard[7][2] = "_";
                        tempBoard[7][4] = "K";
                    }
                    canCastle = true;
                } else if (x == 7 && y == 7) {
                    for (int i = 5; i < 7; i++) {
                        if (!tempBoard[y][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        tempBoard[7][4] = "_";
                        tempBoard[7][5] = "R";
                        tempBoard[7][6] = "K";
                        tempBoard[7][7] = "_";
                        validateMove(tempBoard);
                        tempBoard[7][4] = "K";
                        tempBoard[7][5] = "_";
                        tempBoard[7][6] = "_";
                        tempBoard[7][7] = "R";
                    }
                    canCastle = true;
                }
            }
        } else {
            if (tempBoard[0][4].equals("k")) {
                if (x == 0 && y == 0) {
                    for (int i = 1; i < 4; i++) {
                        if (!tempBoard[y][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        tempBoard[0][0] = "_";
                        tempBoard[0][2] = "k";
                        tempBoard[0][3] = "r";
                        tempBoard[0][4] = "_";
                        validateMove(tempBoard);
                        tempBoard[0][0] = "r";
                        tempBoard[0][2] = "_";
                        tempBoard[0][3] = "_";
                        tempBoard[0][4] = "k";
                    }
                    canCastle = true;
                } else if (x == 7 && y == 0) {
                    for (int i = 5; i < 7; i++) {
                        if (!tempBoard[y][i].equals("_")) {
                            canCastle = false;
                            break;
                        }
                    }
                    if (canCastle) {
                        tempBoard[0][4] = "_";
                        tempBoard[0][5] = "r";
                        tempBoard[0][6] = "k";
                        tempBoard[0][7] = "_";
                        validateMove(tempBoard);
                        tempBoard[0][4] = "k";
                        tempBoard[0][5] = "_";
                        tempBoard[0][6] = "_";
                        tempBoard[0][7] = "r";
                    }
                    canCastle = true;
                }
            }
        }
    }
    public void makeBishopMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        if (x != 7 && y != 7) {
            topRightDiagonalMove(2);
        }
        if (x != 0 && y != 0) {
            bottomLeftDiagonalMove(2);
        }
        if (x != 0 && y != 7) {
            topLeftDiagonalMove(2);
        }
        if (x != 7 && y != 0) {
            bottomRightDiagonalMove(2);
        }
    }
    public void makeKnightMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        if (x < 7 && y < 6) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 2][x + 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 2][x + 1] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 2][x + 1].equals("_")) {
                    tempBoard[y + 2][x + 1] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x < 6 && y < 7) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 1][x + 2].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 1][x + 2] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 1][x + 2].equals("_")) {
                    tempBoard[y + 1][x + 2] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x < 6 && y > 0) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 1][x + 2].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 1][x + 2] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 1][x + 2].equals("_")) {
                    tempBoard[y - 1][x + 2] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x < 7 && y > 1) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 2][x + 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 2][x + 1] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 2][x + 1].equals("_")) {
                    tempBoard[y - 2][x + 1] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x > 0 && y > 1) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 2][x - 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 2][x - 1] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 2][x - 1].equals("_")) {
                    tempBoard[y - 2][x - 1] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x > 1 && y > 0) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 1][x - 2].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 1][x - 2] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 1][x - 2].equals("_")) {
                    tempBoard[y - 1][x - 2] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x > 1 && y < 7) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 1][x - 2].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 1][x - 2] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 1][x - 2].equals("_")) {
                    tempBoard[y + 1][x - 2] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x > 0 && y < 6) {
            boolean pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 2][x - 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 2][x - 1] = currentPieces[3];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 2][x - 1].equals("_")) {
                    tempBoard[y + 2][x - 1] = "_";
                    tempBoard[y][x] = currentPieces[3];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
    }
    public void makeQueenMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        makeQueenRookMove();
        makeQueenBishopMove();
    }
    public void makeQueenRookMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        if (x != 7) {
            rightMove(4);
        }
        if (x != 0) {
            leftMove(4);
        }
        if (y != 7) {
            upMove(4);
        }
        if (y != 0) {
            downMove(4);
        }
    }
    public void makeQueenBishopMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        if (x != 7 && y != 7) {
            topRightDiagonalMove(4);
        }
        if (x != 0 && y != 0) {
            bottomLeftDiagonalMove(4);
        }
        if (x != 0 && y != 7) {
            topLeftDiagonalMove(4);
        }
        if (x != 7 && y != 0) {
            bottomRightDiagonalMove(4);
        }
    }
    public void rightMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempX;
        boolean canMove = true;
        for (tempX = x + 1; tempX < 8; tempX++) {
            if (canMove) {
                if (tempX != x + 1) {
                    for (int i = x + 1; i <= tempX - 1; i++) {
                        for (int j = 0; j < 12; j++) {
                            if (tempBoard[y][i].charAt(0) == pieces[j].charAt(0)) {
                                canMove = false;
                                break;
                            }
                        }
                        if (!canMove) {
                            break;
                        }
                    }
                    if (canMove) {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[y][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[y][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[y][tempX].equals("_")) {
                                tempBoard[y][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                } else {
                    boolean pieceInSquare = false;
                    for (int i = 0; i < 6; i++) {
                        if (tempBoard[y][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                            pieceInSquare = true;
                            break;
                        }
                    }
                    if (!pieceInSquare) {
                        tempBoard[y][tempX] = currentPieces[pieceNumber];
                        tempBoard[y][x] = "_";
                        validateMove(tempBoard);
                        if (staticBoard[y][tempX].equals("_")) {
                            tempBoard[y][tempX] = "_";
                            tempBoard[y][x] = currentPieces[pieceNumber];
                        } else {
                            for (int i = 0; i < 8; i++) {
                                System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                            }
                        }
                    }
                }
            }
        }
    }
    public void leftMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempX;
        boolean canMove = true;
        for (tempX = x - 1; tempX >= 0; tempX--) {
            if (canMove) {
                if (tempX != x - 1) {
                    for (int i = x - 1; i >= tempX + 1; i--) {
                        for (int j = 0; j < 12; j++) {
                            if (tempBoard[y][i].charAt(0) == pieces[j].charAt(0)) {
                                canMove = false;
                                break;
                            }
                        }
                        if (!canMove) {
                            break;
                        }
                    }
                    if (canMove) {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i ++) {
                            if (tempBoard[y][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[y][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[y][tempX].equals("_")) {
                                tempBoard[y][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                } else {
                    boolean pieceInSquare = false;
                    for (int i = 0; i < 6; i ++) {
                        if (tempBoard[y][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                            pieceInSquare = true;
                            break;
                        }
                    }
                    if (!pieceInSquare) {
                        tempBoard[y][tempX] = currentPieces[pieceNumber];
                        tempBoard[y][x] = "_";
                        validateMove(tempBoard);
                        if (staticBoard[y][tempX].equals("_")) {
                            tempBoard[y][tempX] = "_";
                            tempBoard[y][x] = currentPieces[pieceNumber];
                        } else {
                            for (int i = 0; i < 8; i++) {
                                System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                            }
                        }
                    }
                }
            }
        }
    }
    public void upMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempY;
        boolean canMove = true;
        for (tempY = y + 1; tempY < 8; tempY++) {
            if (canMove) {
                if (tempY != y + 1) {
                    for (int i = y + 1; i <= tempY - 1; i++) {
                        for (int j = 0; j < 12; j++) {
                            if (tempBoard[i][x].charAt(0) == pieces[j].charAt(0)) {
                                canMove = false;
                                break;
                            }
                        }
                        if (!canMove) {
                            break;
                        }
                    }
                    if (canMove) {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempY][x].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][x] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][x].equals("_")) {
                                tempBoard[tempY][x] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                } else {
                    boolean pieceInSquare = false;
                    for (int i = 0; i < 6; i++) {
                        if (tempBoard[tempY][x].charAt(0) == currentPieces[i].charAt(0)) {
                            pieceInSquare = true;
                            break;
                        }
                    }
                    if (!pieceInSquare) {
                        tempBoard[tempY][x] = currentPieces[pieceNumber];
                        tempBoard[y][x] = "_";
                        validateMove(tempBoard);
                        if (staticBoard[tempY][x].equals("_")) {
                            tempBoard[tempY][x] = "_";
                            tempBoard[y][x] = currentPieces[pieceNumber];
                        } else {
                            for (int i = 0; i < 8; i++) {
                                System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                            }
                        }
                    }
                }
            }
        }
    }
    public void downMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempY;
        boolean canMove = true;
        for (tempY = y - 1; tempY >= 0; tempY--) {
            if (canMove) {
                if (tempY != y - 1) {
                    for (int i = y - 1; i >= tempY + 1; i--) {
                        for (int j = 0; j < 12; j++) {
                            if (tempBoard[i][x].charAt(0) == pieces[j].charAt(0)) {
                                canMove = false;
                                break;
                            }
                        }
                        if (!canMove) {
                            break;
                        }
                    }
                    if (canMove) {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i ++) {
                            if (tempBoard[tempY][x].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][x] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][x].equals("_")) {
                                tempBoard[tempY][x] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                } else {
                    boolean pieceInSquare = false;
                    for (int i = 0; i < 6; i ++) {
                        if (tempBoard[tempY][x].charAt(0) == currentPieces[i].charAt(0)) {
                            pieceInSquare = true;
                            break;
                        }
                    }
                    if (!pieceInSquare) {
                        tempBoard[tempY][x] = currentPieces[pieceNumber];
                        tempBoard[y][x] = "_";
                        validateMove(tempBoard);
                        if (staticBoard[tempY][x].equals("_")) {
                            tempBoard[tempY][x] = "_";
                            tempBoard[y][x] = currentPieces[pieceNumber];
                        } else {
                            for (int i = 0; i < 8; i++) {
                                System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                            }
                        }
                    }
                }
            }
        }
    }
    public void topRightDiagonalMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int varTemp;
        int statTemp;
        int temp2x;
        int temp2y;
        boolean canMove = true;
        int tempX = x;
        int tempY = y;
        if (y > x) {
            varTemp = y + 1;
            statTemp = y + 1;
        } else {
            varTemp = x + 1;
            statTemp = x + 1;
        }
        for (;varTemp < 8; varTemp++) {
            tempX++;
            tempY++;
            temp2x = x;
            temp2y = y;
            if (canMove) {
                if (varTemp != statTemp) {
                    for (int i = statTemp; i <= varTemp - 1; i++) {
                        temp2x++;
                        temp2y++;
                        for (int j = 0; j < 12; j++) {
                            if (tempBoard[temp2y][temp2x].charAt(0) == pieces[j].charAt(0)) {
                                canMove = false;
                                break;
                            }
                        }
                        if (!canMove) {
                            break;
                        }
                    }
                    if (canMove) {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][tempX].equals("_")) {
                                tempBoard[tempY][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                } else {
                    boolean pieceInSquare = false;
                    for (int i = 0; i < 6; i++) {
                        if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                            pieceInSquare = true;
                            break;
                        }
                    }
                    if (!pieceInSquare) {
                        tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                        tempBoard[y][x] = "_";
                        validateMove(tempBoard);
                        if (staticBoard[tempY][tempX].equals("_")) {
                            tempBoard[tempY][tempX] = "_";
                            tempBoard[y][x] = currentPieces[pieceNumber];
                        } else {
                            for (int i = 0; i < 8; i++) {
                                System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                            }
                        }
                    }
                }
            }
        }
    }
    public void bottomLeftDiagonalMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempX = x;
        int tempY = y;
        int varTemp;
        int statTemp;
        int temp2x;
        int temp2y;
        boolean canMove = true;
        if (y < x) {
            varTemp = y - 1;
            statTemp = y - 1;
        } else {
            varTemp = x - 1;
            statTemp = x - 1;
        }
        for (;varTemp >= 0; varTemp--) {
            tempX--;
            tempY--;
            temp2x = x;
            temp2y = y;
            if (canMove) {
                if (varTemp != statTemp) {
                    for (int i = statTemp; i >= varTemp + 1; i--) {
                        temp2x--;
                        temp2y--;
                        for (int j = 0; j < 12; j++) {
                            if (tempBoard[temp2y][temp2x].charAt(0) == pieces[j].charAt(0)) {
                                canMove = false;
                                break;
                            }
                        }
                        if (!canMove) {
                            break;
                        }
                    }
                    if (canMove) {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempX][tempY].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][tempX].equals("_")) {
                                tempBoard[tempY][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                } else {
                    boolean pieceInSquare = false;
                    for (int i = 0; i < 6; i++) {
                        if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                            pieceInSquare = true;
                            break;
                        }
                    }
                    if (!pieceInSquare) {
                        tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                        tempBoard[y][x] = "_";
                        validateMove(tempBoard);
                        if (staticBoard[tempY][tempX].equals("_")) {
                            tempBoard[tempY][tempX] = "_";
                            tempBoard[y][x] = currentPieces[pieceNumber];
                        } else {
                            for (int i = 0; i < 8; i++) {
                                System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                            }
                        }
                    }
                }
            }
        }
    }
    public void topLeftDiagonalMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempX = x;
        int tempY = y;
        int varTemp;
        int statTemp;
        int finalPos;
        int temp2x;
        int temp2y;
        boolean canMove = true;
        if (7 - y < x) {
            varTemp = y + 1;
            statTemp = y + 1;
            finalPos = 7;
        } else {
            varTemp = x - 1;
            statTemp = x - 1;
            finalPos = 0;
        }
        if (finalPos == 0) {
            for (;varTemp >= 0; varTemp--) {
                tempX--;
                tempY++;
                temp2x = x;
                temp2y = y;
                if (canMove) {
                    if (varTemp != statTemp) {
                        for (int i = statTemp; i >= varTemp + 1; i--) {
                            temp2x--;
                            temp2y++;
                            for (int j = 0; j < 12; j++) {
                                if (tempBoard[temp2y][temp2x].charAt(0) == pieces[j].charAt(0)) {
                                    canMove = false;
                                    break;
                                }
                            }
                            if (!canMove)
                                break;
                        }
                        if (canMove) {
                            boolean pieceInSquare = false;
                            for (int i = 0; i < 6; i++) {
                                if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                    pieceInSquare = true;
                                    break;
                                }
                            }
                            if (!pieceInSquare) {
                                tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                                tempBoard[y][x] = "_";
                                validateMove(tempBoard);
                                if (staticBoard[tempY][tempX].equals("_")) {
                                    tempBoard[tempY][tempX] = "_";
                                    tempBoard[y][x] = currentPieces[pieceNumber];
                                } else {
                                    for (int i = 0; i < 8; i++) {
                                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                    }
                                }
                            }
                        }
                    } else {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][tempX].equals("_")) {
                                tempBoard[tempY][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (;varTemp < 8; varTemp++) {
                tempX--;
                tempY++;
                temp2x = x;
                temp2y = y;
                if (canMove) {
                    if (varTemp != statTemp) {
                        for (int i = statTemp; i <= varTemp - 1; i++) {
                            temp2x--;
                            temp2y++;
                            for (int j = 0; j < 12; j++) {
                                if (tempBoard[temp2y][temp2x].charAt(0) == pieces[j].charAt(0)) {
                                    canMove = false;
                                    break;
                                }
                            }
                            if (!canMove)
                                break;
                        }
                        if (canMove) {
                            boolean pieceInSquare = false;
                            for (int i = 0; i < 6; i++) {
                                if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                    pieceInSquare = true;
                                    break;
                                }
                            }
                            if (!pieceInSquare) {
                                tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                                tempBoard[y][x] = "_";
                                validateMove(tempBoard);
                                if (staticBoard[tempY][tempX].equals("_")) {
                                    tempBoard[tempY][tempX] = "_";
                                    tempBoard[y][x] = currentPieces[pieceNumber];
                                } else {
                                    for (int i = 0; i < 8; i++) {
                                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                    }
                                }
                            }
                        }
                    } else {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][tempX].equals("_")) {
                                tempBoard[tempY][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void bottomRightDiagonalMove(int pieceNumber) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieceNumber < 0 || pieceNumber + 1 > currentPieces.length) {
            return;
        }
        int tempX = x;
        int tempY = y;
        int varTemp;
        int statTemp;
        int finalPos;
        int temp2x;
        int temp2y;
        boolean canMove = true;
        if (y < 7 - x) {
            varTemp = y - 1;
            statTemp = y - 1;
            finalPos = 0;
        } else {
            varTemp = x + 1;
            statTemp = x + 1;
            finalPos = 7;
        }
        if (finalPos == 7) {
            for (;varTemp < 8; varTemp++) {
                tempX++;
                tempY--;
                temp2x = x;
                temp2y = y;
                if (canMove) {
                    if (varTemp != statTemp) {
                        for (int i = statTemp; i <= varTemp - 1; i++) {
                            temp2x++;
                            temp2y--;
                            for (int j = 0; j < 12; j++) {
                                if (tempBoard[temp2y][temp2x].charAt(0) == pieces[j].charAt(0)) {
                                    canMove = false;
                                    break;
                                }
                            }
                            if (!canMove)
                                break;
                        }
                        if (canMove) {
                            boolean pieceInSquare = false;
                            for (int i = 0; i < 6; i++) {
                                if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                    pieceInSquare = true;
                                    break;
                                }
                            }
                            if (!pieceInSquare) {
                                tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                                tempBoard[y][x] = "_";
                                validateMove(tempBoard);
                                if (staticBoard[tempY][tempX].equals("_")) {
                                    tempBoard[tempY][tempX] = "_";
                                    tempBoard[y][x] = currentPieces[pieceNumber];
                                } else {
                                    for (int i = 0; i < 8; i++) {
                                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                    }
                                }
                            }
                        }
                    } else {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][tempX].equals("_")) {
                                tempBoard[tempY][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (;varTemp >= 0; varTemp--) {
                tempX++;
                tempY--;
                temp2x = x;
                temp2y = y;
                if (canMove) {
                    if (varTemp != statTemp) {
                        for (int i = statTemp; i >= varTemp + 1; i--) {
                            temp2x++;
                            temp2y--;
                            for (int j = 0; j < 12; j++) {
                                if (tempBoard[temp2y][temp2x].charAt(0) == pieces[j].charAt(0)) {
                                    canMove = false;
                                    break;
                                }
                            }
                            if (!canMove)
                                break;
                        }
                        if (canMove) {
                            boolean pieceInSquare = false;
                            for (int i = 0; i < 6; i++) {
                                if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                    pieceInSquare = true;
                                    break;
                                }
                            }
                            if (!pieceInSquare) {
                                tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                                tempBoard[y][x] = "_";
                                validateMove(tempBoard);
                                if (staticBoard[tempY][tempX].equals("_")) {
                                    tempBoard[tempY][tempX] = "_";
                                    tempBoard[y][x] = currentPieces[pieceNumber];
                                } else {
                                    for (int i = 0; i < 8; i++) {
                                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                    }
                                }
                            }
                        }
                    } else {
                        boolean pieceInSquare = false;
                        for (int i = 0; i < 6; i++) {
                            if (tempBoard[tempY][tempX].charAt(0) == currentPieces[i].charAt(0)) {
                                pieceInSquare = true;
                                break;
                            }
                        }
                        if (!pieceInSquare) {
                            tempBoard[tempY][tempX] = currentPieces[pieceNumber];
                            tempBoard[y][x] = "_";
                            validateMove(tempBoard);
                            if (staticBoard[tempY][tempX].equals("_")) {
                                tempBoard[tempY][tempX] = "_";
                                tempBoard[y][x] = currentPieces[pieceNumber];
                            } else {
                                for (int i = 0; i < 8; i++) {
                                    System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void makeKingMove() {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        boolean pieceInSquare = false;
        if (x > 0 && y < 7) {
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 1][x - 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 1][x - 1] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 1][x - 1].equals("_")) {
                    tempBoard[y + 1][x - 1] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (y < 7) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 1][x].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 1][x] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 1][x].equals("_")) {
                    tempBoard[y + 1][x] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x < 7 && y < 7) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y + 1][x + 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y + 1][x + 1] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y + 1][x + 1].equals("_")) {
                    tempBoard[y + 1][x + 1] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x < 7) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y][x + 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y][x + 1] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y][x + 1].equals("_")) {
                    tempBoard[y][x + 1] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x < 7 && y > 0) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 1][x + 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 1][x + 1] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 1][x + 1].equals("_")) {
                    tempBoard[y - 1][x + 1] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (y > 0) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 1][x].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 1][x] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 1][x].equals("_")) {
                    tempBoard[y - 1][x] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x > 0 && y > 0) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y - 1][x - 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y - 1][x - 1] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y - 1][x - 1].equals("_")) {
                    tempBoard[y - 1][x - 1] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
        if (x > 0) {
            pieceInSquare = false;
            for (int i = 0; i < 6; i++) {
                if (tempBoard[y][x - 1].charAt(0) == currentPieces[i].charAt(0)) {
                    pieceInSquare = true;
                    break;
                }
            }
            if (!pieceInSquare) {
                tempBoard[y][x - 1] = currentPieces[5];
                tempBoard[y][x] = "_";
                validateMove(tempBoard);
                if (staticBoard[y][x - 1].equals("_")) {
                    tempBoard[y][x - 1] = "_";
                    tempBoard[y][x] = currentPieces[5];
                } else {
                    for (int i = 0; i < 8; i++) {
                        System.arraycopy(staticBoard[i], 0, tempBoard[i], 0, 8);
                    }
                }
            }
        }
    }
    public void validateMove(String[][] tempBoard) {
        if (!king.isChecked(tempBoard, colour)) {
            if (isCheckmating) {
                moveCounter++;
            } else {
                ChessNode node;
                if (colour == 'W') {
                    node = new ChessNode(tempBoard, 'B');
                } else {
                    node = new ChessNode(tempBoard, 'W');
                }
                NodeTree.getCurrentNode().addChild(node);
            }
        }
    }
    public int getMoveCounter() { return moveCounter; }
}