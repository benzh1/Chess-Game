package com.example.interactivechessguinea;

import java.util.ArrayList;

public class ChessNode {
    private final ArrayList<ChessNode> childrenNodeList = new ArrayList<>();
    private final String[][] board = new String[8][8];
    private int childIndex = 0;
    private final int depth;
    private ChessNode parentNode;
    private char colour;
    public ChessNode(String[][] board, char colour) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                this.board[j][i] = String.valueOf(board[j][i].charAt(0));
        }
        parentNode = NodeTree.getCurrentNode();
        depth = parentNode.getDepth() - 1;
        this.colour = colour;
    }
    public ChessNode(String[][] board, int depth, char colour) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                this.board[j][i] = String.valueOf(board[j][i].charAt(0));
        }
        this.depth = depth;
        this.colour = colour;
    }
    public void addChild(ChessNode childNode) {
        childrenNodeList.add(childNode);
    }
    public ChessNode getChild(int childIndex) {
        return childrenNodeList.get(childIndex);
    }
    public ChessNode getChildren() {
        return childrenNodeList.get(childIndex++);
    }
    public String[][] getBoard() {
        return board;
    }
    public int getDepth() {
        return depth;
    }
    public int getChildIndex() {
        return  childIndex;
    }
    public int getChildrenSize() {
        return childrenNodeList.size();
    }
    public ChessNode getParentNode() {
        return parentNode;
    }
    public void setColour(char colour) { this.colour = colour; }
    public char getColour() { return colour; }
    public ArrayList<ChessNode> getChildrenNodeList() { return childrenNodeList; }
    public void setParentNode(ChessNode parentNode) { this.parentNode = parentNode; }
}
