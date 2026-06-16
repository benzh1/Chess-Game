package com.example.interactivechessguinea;

public class Queen {
    public boolean checkValid(int originalX, int originalY, int newX, int newY, char colour, String[][] board) {
        Bishop bishop = new Bishop();
        Rook rook = new Rook();
        if (bishop.checkValid(originalX, originalY, newX, newY, colour, board))
            return true;
        else
            return rook.checkValid(originalX, originalY, newX, newY, colour, board);
        // checking that the move is either a valid rook move or bishop move
    }
}