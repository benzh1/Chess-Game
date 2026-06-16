package com.example.interactivechessguinea;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView blackBishop1, blackBishop2, blackKing, blackKnight1, blackKnight2, blackRook1, blackRook2;
    @FXML
    private ImageView blackPawn1, blackPawn2, blackPawn3, blackPawn4, blackPawn5, blackPawn6, blackPawn7, blackPawn8;
    @FXML
    private ImageView blackQueen, blackQueen1, blackQueen2, blackQueen3, blackQueen4, blackQueen5, blackQueen6, blackQueen7, blackQueen8;
    @FXML
    private ImageView whiteBishop1, whiteBishop2, whiteKnight1, whiteKnight2, whiteKing, whiteRook1, whiteRook2;
    @FXML
    private ImageView whitePawn1, whitePawn2, whitePawn3, whitePawn4, whitePawn5, whitePawn6, whitePawn7, whitePawn8;
    @FXML
    private ImageView whiteQueen, whiteQueen1, whiteQueen2, whiteQueen3, whiteQueen4, whiteQueen5, whiteQueen6, whiteQueen7, whiteQueen8;
    @FXML
    private ImageView capturedBlackQueen, capturedBlackRook1, capturedBlackRook2, capturedBlackBishop1, capturedBlackBishop2, capturedBlackKnight1, capturedBlackKnight2;
    @FXML
    private ImageView capturedBlackPawn1, capturedBlackPawn2, capturedBlackPawn3, capturedBlackPawn4, capturedBlackPawn5, capturedBlackPawn6, capturedBlackPawn7, capturedBlackPawn8;
    @FXML
    private ImageView capturedWhiteQueen, capturedWhiteRook1, capturedWhiteRook2, capturedWhiteBishop1, capturedWhiteBishop2, capturedWhiteKnight1, capturedWhiteKnight2;
    @FXML
    private ImageView capturedWhitePawn1, capturedWhitePawn2, capturedWhitePawn3, capturedWhitePawn4, capturedWhitePawn5, capturedWhitePawn6, capturedWhitePawn7, capturedWhitePawn8;
    @FXML
    private TextField newPosition, originalPosition;
    @FXML
    private Button submitButton;
    @FXML
    private TextArea moveList;
    @FXML
    private ComboBox <String> numberOfPlayers;
    @FXML
    private ComboBox <String> difficultyLevel;
    @FXML
    private ComboBox <String> startingColour;
    @FXML
    private Label startingColourLabel;
    @FXML
    private Label endGameLabel;
    private boolean isCheckmate = false;
    private int currentBP1X = 0, currentBP1Y = 0, currentBP2X = 0, currentBP2Y = 0, currentBP3X = 0, currentBP3Y = 0, currentBP4X = 0, currentBP4Y = 0;
    private int currentBP5X = 0, currentBP5Y = 0, currentBP6X = 0, currentBP6Y = 0, currentBP7X = 0, currentBP7Y = 0, currentBP8X = 0, currentBP8Y = 0;
    private int currentBR1X = 0, currentBR1Y = 0, currentBR2X = 0, currentBR2Y = 0, currentBH1X = 0, currentBH1Y = 0, currentBH2X = 0, currentBH2Y = 0;
    private int currentBB1X = 0, currentBB1Y = 0, currentBB2X = 0, currentBB2Y = 0, currentBKX = 0, currentBKY = 0, currentBQX = 0, currentBQY = 0;
    private int currentBQ1X = 0, currentBQ1Y = 0, currentBQ2X = 0, currentBQ2Y = 0, currentBQ3X = 0, currentBQ3Y = 0, currentBQ4X = 0, currentBQ4Y = 0;
    private int currentBQ5X = 0, currentBQ5Y = 0, currentBQ6X = 0, currentBQ6Y = 0, currentBQ7X = 0, currentBQ7Y = 0, currentBQ8X = 0, currentBQ8Y = 0;
    private int currentWP1X = 0, currentWP1Y = 0, currentWP2X = 0, currentWP2Y = 0, currentWP3X = 0, currentWP3Y = 0, currentWP4X = 0, currentWP4Y = 0;
    private int currentWP5X = 0, currentWP5Y = 0, currentWP6X = 0, currentWP6Y = 0, currentWP7X = 0, currentWP7Y = 0, currentWP8X = 0, currentWP8Y = 0;
    private int currentWR1X = 0, currentWR1Y = 0, currentWR2X = 0, currentWR2Y = 0, currentWH1X = 0, currentWH1Y = 0, currentWH2X = 0, currentWH2Y = 0;
    private int currentWB1X = 0, currentWB1Y = 0, currentWB2X = 0, currentWB2Y = 0, currentWKX = 0, currentWKY = 0, currentWQX = 0, currentWQY = 0;
    private int currentWQ1X = 0, currentWQ1Y = 0, currentWQ2X = 0, currentWQ2Y = 0, currentWQ3X = 0, currentWQ3Y = 0, currentWQ4X = 0, currentWQ4Y = 0;
    private int currentWQ5X = 0, currentWQ5Y = 0, currentWQ6X = 0, currentWQ6Y = 0, currentWQ7X = 0, currentWQ7Y = 0, currentWQ8X = 0, currentWQ8Y = 0;
    private final char[] columnIndexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private final char[] rowIndexes = {'8', '7', '6', '5', '4', '3', '2', '1'};
    private final String[][] board = {
            {"r1", "h1", "b1", "q", "k", "b2", "h2", "r2"},
            {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"},
            {"_", "_", "_", "_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_", "_", "_", "_"},
            {"_", "_", "_", "_", "_", "_", "_", "_"},
            {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"},
            {"R1", "H1", "B1", "Q", "K", "B2", "H2", "R2"}};

    private String[][] tempBoard = new String[8][8];
    private char currentColour = 'W';
    private Pawn pawn = new Pawn();
    private Rook rook = new Rook();
    private Bishop bishop = new Bishop();
    private Knight knight = new Knight();
    private Queen queen = new Queen();
    private King king = new King();
    private final String[] currentPieces = {"p", "r", "b", "h", "q", "k"};
    private int computerMoveOGYCoord, computerMoveOGXCoord, computerMoveNewYCoord, computerMoveNewXCoord;
    private boolean isCastleMove;
    private char previousColourMove = 'B';
    private int playerNumber = 1;
    private int moveCounter = 0;
    private String originalColour = "White";
    public void resetButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChessGUI.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
        isCheckmate = false;
        board[0][0] = "r1";
        board[0][1] = "h1";
        board[0][2] = "b1";
        board[0][3] = "q";
        board[0][4] = "k";
        board[0][5] = "b2";
        board[0][6] = "h2";
        board[0][7] = "r2";
        board[1][0] = "p1";
        board[1][1] = "p2";
        board[1][2] = "p3";
        board[1][3] = "p4";
        board[1][4] = "p5";
        board[1][5] = "p6";
        board[1][6] = "p7";
        board[1][7] = "p8";
        board[2][0] = "_";
        board[2][1] = "_";
        board[2][2] = "_";
        board[2][3] = "_";
        board[2][4] = "_";
        board[2][5] = "_";
        board[2][6] = "_";
        board[2][7] = "_";
        board[3][0] = "_";
        board[3][1] = "_";
        board[3][2] = "_";
        board[3][3] = "_";
        board[3][4] = "_";
        board[3][5] = "_";
        board[3][6] = "_";
        board[3][7] = "_";
        board[4][0] = "_";
        board[4][1] = "_";
        board[4][2] = "_";
        board[4][3] = "_";
        board[4][4] = "_";
        board[4][5] = "_";
        board[4][6] = "_";
        board[4][7] = "_";
        board[5][0] = "_";
        board[5][1] = "_";
        board[5][2] = "_";
        board[5][3] = "_";
        board[5][4] = "_";
        board[5][5] = "_";
        board[5][6] = "_";
        board[5][7] = "_";
        board[6][0] = "P1";
        board[6][1] = "P2";
        board[6][2] = "P3";
        board[6][3] = "P4";
        board[6][4] = "P5";
        board[6][5] = "P6";
        board[6][6] = "P7";
        board[6][7] = "P8";
        board[7][0] = "R1";
        board[7][1] = "H1";
        board[7][2] = "B1";
        board[7][3] = "Q";
        board[7][4] = "K";
        board[7][5] = "B2";
        board[7][6] = "H2";
        board[7][7] = "R2";
        tempBoard = new String[8][8];
        currentColour = 'W';
        pawn = new Pawn();
        rook = new Rook();
        bishop = new Bishop();
        knight = new Knight();
        queen = new Queen();
        king = new King();
        currentPieces[0] = "p";
        currentPieces[1] = "r";
        currentPieces[2] = "b";
        currentPieces[3] = "h";
        currentPieces[4] = "q";
        currentPieces[5] = "k";
        computerMoveOGYCoord = -1;
        computerMoveOGXCoord = -1;
        computerMoveNewYCoord = -1;
        computerMoveNewXCoord = -1;
        isCastleMove = false;
        previousColourMove = 'B';
        playerNumber = 1;
        moveCounter = 0;
        originalColour = "White";
    }
    public void logoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginGUI.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
    public void onButtonPress() {
            submitButton.setOnAction(e -> {
                playerNumber = Integer.parseInt(numberOfPlayers.getSelectionModel().getSelectedItem());
                originalColour = startingColour.getSelectionModel().getSelectedItem();
                if (playerNumber == 1 && originalColour.equals("Black") && moveCounter == 0) {
                    makeComputerMove();
                    previousColourMove = 'W';
                    moveCounter++;
                    startingColour.setVisible(false);
                    startingColourLabel.setVisible(false);
                    return;
                }
                if (playerNumber == 2) {
                    if (previousColourMove == 'B') {
                        currentColour = 'W';
                    } else {
                        currentColour = 'B';
                    }
                    String originalPos;
                    String newPos;
                    if (originalPosition.getLength() == 0 || newPosition.getLength() == 0) {
                        return;
                    } else {
                        originalPos = originalPosition.getText();
                        newPos = newPosition.getText();
                    }
                    int originalPosXIndex = -1;
                    int originalPosYIndex = -1;
                    int newPosXIndex = -1;
                    int newPosYIndex = -1;
                    int yMove;
                    int xMove;
                    char firstCharacterOriginalPos = String.valueOf(originalPos.charAt(0)).toUpperCase().charAt(0);
                    char firstCharacterNewPos = String.valueOf(newPos.charAt(0)).toUpperCase().charAt(0);
                    // Initializing all the variables to starting values
                    if (originalPos.length() == 2 && newPos.length() == 2) {
                        for (int i = 0; i < 8; i++) {
                            if (firstCharacterOriginalPos == columnIndexes[i])
                                originalPosXIndex = i;
                            if (originalPos.charAt(1) == rowIndexes[i])
                                originalPosYIndex = i;
                            if (firstCharacterNewPos == columnIndexes[i])
                                newPosXIndex = i;
                            if (newPos.charAt(1) == rowIndexes[i])
                                newPosYIndex = i;
                        }
                        // Finding the correct coordinates in numbers of the piece being moved

                        if (!(originalPosXIndex == -1 || originalPosYIndex == -1 || newPosXIndex == -1 || newPosYIndex == -1)) {
                            // Checking that all the coordinates are valid and have changed

                            if (inputCoordinates(originalPosXIndex, originalPosYIndex, newPosXIndex, newPosYIndex)) {
                                // Checking the move and coordinates are valid

                                xMove = (newPosXIndex - originalPosXIndex) * 53;
                                yMove = (newPosYIndex - originalPosYIndex) * 53;
                                // Calculating the distance of how far the pieces on the GUI board will move

                                System.out.println("xMove: " + xMove);
                                System.out.println("yMove: " + yMove);
                                if (currentColour == 'B') {
                                    moveList.appendText(newPos + "\t" + "\n");
                                } else {
                                    moveList.appendText(newPos + "\t");
                                }
                                moveCounter++;
                                startingColour.setVisible(false);
                                startingColourLabel.setVisible(false);
                                // Moving the correct piece on both the GUI board and 2D array board whilst changing the current colour to the other player after the move
                                switch (board[originalPosYIndex][originalPosXIndex]) {
                                    case "r1" -> blackRook1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "h1" -> blackKnight1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "b1" -> blackBishop1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q" -> blackQueenMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q1" -> blackQueen1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q2" -> blackQueen2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q3" -> blackQueen3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q4" -> blackQueen4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q5" -> blackQueen5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q6" -> blackQueen6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q7" -> blackQueen7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q8" -> blackQueen8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "k" -> blackKingMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "b2" -> blackBishop2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "h2" -> blackKnight2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "r2" -> blackRook2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p1" -> blackPawn1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p2" -> blackPawn2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p3" -> blackPawn3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p4" -> blackPawn4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p5" -> blackPawn5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p6" -> blackPawn6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p7" -> blackPawn7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p8" -> blackPawn8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "R1" -> whiteRook1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "H1" -> whiteKnight1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "B1" -> whiteBishop1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q" -> whiteQueenMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q1" -> whiteQueen1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q2" -> whiteQueen2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q3" -> whiteQueen3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q4" -> whiteQueen4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q5" -> whiteQueen5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q6" -> whiteQueen6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q7" -> whiteQueen7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q8" -> whiteQueen8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "K" -> whiteKingMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "B2" -> whiteBishop2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "H2" -> whiteKnight2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "R2" -> whiteRook2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P1" -> whitePawn1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P2" -> whitePawn2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P3" -> whitePawn3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P4" -> whitePawn4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P5" -> whitePawn5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P6" -> whitePawn6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P7" -> whitePawn7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P8" -> whitePawn8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                        for (int a = 0; a < 8; a++) {
                            for (int b = 0; b < 8; b++) {
                                System.out.print("\t" + board[a][b]);
                            }
                            System.out.println();
                        }
                        System.out.println(currentColour);
                        if (currentColour == 'W') {
                            previousColourMove = 'W';
                            currentColour = 'B';
                        } else {
                            previousColourMove = 'B';
                            currentColour = 'W';
                        }
                        doesMoveCheck();
                        if (isCheckmate) {
                            System.out.println("CHECKMATE");
                            endGameLabel.setVisible(true);
                            endGameLabel.setText("CHECKMATE");
                        }
                        GenerateMoves generateMoves = new GenerateMoves(true);
                        generateMoves.makeMove(currentColour, board);
                        if (generateMoves.getMoveCounter() == 0) {
                            System.out.println("DRAW");
                            endGameLabel.setVisible(true);
                            endGameLabel.setText("DRAW");
                        }
                    }
                } else if (playerNumber == 1) {
                    if (previousColourMove == 'B') {
                        currentColour = 'W';
                    } else {
                        currentColour = 'B';
                    }
                    String originalPos;
                    String newPos;
                    if (originalPosition.getLength() == 0 || newPosition.getLength() == 0) {
                        return;
                    } else {
                        originalPos = originalPosition.getText();
                        newPos = newPosition.getText();
                    }
                    int originalPosXIndex = -1;
                    int originalPosYIndex = -1;
                    int newPosXIndex = -1;
                    int newPosYIndex = -1;
                    int yMove;
                    int xMove;
                    char firstCharacterOriginalPos = String.valueOf(originalPos.charAt(0)).toUpperCase().charAt(0);
                    char firstCharacterNewPos = String.valueOf(newPos.charAt(0)).toUpperCase().charAt(0);
                    // Initializing all the variables to starting values
                    if (originalPos.length() == 2 && newPos.length() == 2) {
                        for (int i = 0; i < 8; i++) {
                            if (firstCharacterOriginalPos == columnIndexes[i])
                                originalPosXIndex = i;
                            if (originalPos.charAt(1) == rowIndexes[i])
                                originalPosYIndex = i;
                            if (firstCharacterNewPos == columnIndexes[i])
                                newPosXIndex = i;
                            if (newPos.charAt(1) == rowIndexes[i])
                                newPosYIndex = i;
                        }
                        // Finding the correct coordinates in numbers of the piece being moved

                        if (!(originalPosXIndex == -1 || originalPosYIndex == -1 || newPosXIndex == -1 || newPosYIndex == -1)) {
                            // Checking that all the coordinates are valid and have changed

                            if (inputCoordinates(originalPosXIndex, originalPosYIndex, newPosXIndex, newPosYIndex)) {
                                // Checking the move and coordinates are valid

                                xMove = (newPosXIndex - originalPosXIndex) * 53;
                                yMove = (newPosYIndex - originalPosYIndex) * 53;
                                // Calculating the distance of how far the pieces on the GUI board will move

                                System.out.println("xMove: " + xMove);
                                System.out.println("yMove: " + yMove);

                                moveList.appendText(newPos);
                                moveCounter++;
                                startingColour.setVisible(false);
                                startingColourLabel.setVisible(false);
                                // Moving the correct piece on both the GUI board and 2D array board whilst changing the current colour to the other player after the move
                                switch (board[originalPosYIndex][originalPosXIndex]) {
                                    case "r1" -> blackRook1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "h1" -> blackKnight1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "b1" -> blackBishop1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q" -> blackQueenMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q1" -> blackQueen1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q2" -> blackQueen2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q3" -> blackQueen3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q4" -> blackQueen4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q5" -> blackQueen5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q6" -> blackQueen6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q7" -> blackQueen7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "q8" -> blackQueen8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "k" -> blackKingMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "b2" -> blackBishop2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "h2" -> blackKnight2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "r2" -> blackRook2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p1" -> blackPawn1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p2" -> blackPawn2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p3" -> blackPawn3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p4" -> blackPawn4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p5" -> blackPawn5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p6" -> blackPawn6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p7" -> blackPawn7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "p8" -> blackPawn8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "R1" -> whiteRook1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "H1" -> whiteKnight1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "B1" -> whiteBishop1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q" -> whiteQueenMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q1" -> whiteQueen1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q2" -> whiteQueen2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q3" -> whiteQueen3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q4" -> whiteQueen4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q5" -> whiteQueen5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q6" -> whiteQueen6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q7" -> whiteQueen7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "Q8" -> whiteQueen8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "K" -> whiteKingMove(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "B2" -> whiteBishop2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "H2" -> whiteKnight2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "R2" -> whiteRook2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P1" -> whitePawn1Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P2" -> whitePawn2Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P3" -> whitePawn3Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P4" -> whitePawn4Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P5" -> whitePawn5Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P6" -> whitePawn6Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P7" -> whitePawn7Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                    case "P8" -> whitePawn8Move(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex, xMove, yMove);
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                        for (int a = 0; a < 8; a++) {
                            for (int b = 0; b < 8; b++) {
                                System.out.print("\t" + board[a][b]);
                            }
                            System.out.println();
                        }
                        System.out.println(currentColour);
                    } else {
                        return;
                    }
                    if (currentColour == 'W') {
                        currentColour = 'B';
                        doesMoveCheck();
                        if (isCheckmate) {
                            System.out.println("CHECKMATE");
                            endGameLabel.setVisible(true);
                            endGameLabel.setText("CHECKMATE");
                        }
                        currentColour = 'W';
                    } else {
                        currentColour = 'W';
                        doesMoveCheck();
                        if (isCheckmate) {
                            System.out.println("CHECKMATE");
                            endGameLabel.setVisible(true);
                            endGameLabel.setText("CHECKMATE");
                        }
                        currentColour = 'B';
                    }

                    GenerateMoves generateMoves = new GenerateMoves(true);
                    generateMoves.makeMove(currentColour, board);
                    if (generateMoves.getMoveCounter() == 0) {
                        System.out.println("DRAW");
                        endGameLabel.setVisible(true);
                        endGameLabel.setText("DRAW");
                    }
                    makeComputerMove();
                } else {
                    makeComputerMove();
                }
            });
    }
    public void makeComputerMove() {
        if (moveCounter == 0) {
            if (previousColourMove == 'W' || currentColour == 'B') {
                currentColour = 'W';
            } else {
                currentColour = 'B';
            }
        } else {
            if (previousColourMove == 'W' || currentColour == 'B') {
                currentColour = 'B';
            } else {
                currentColour = 'W';
            }
        }
        NodeTree nodeTree = getNodeTree();
        computerMoveNewXCoord = nodeTree.getNewX();
        computerMoveNewYCoord = nodeTree.getNewY();
        computerMoveOGXCoord = nodeTree.getOriginalX();
        computerMoveOGYCoord = nodeTree.getOriginalY();
        String computerMoveChessCoord = getComputerMoveChessCoord();
        moveList.appendText(computerMoveChessCoord);
        int xMove = (computerMoveNewXCoord - computerMoveOGXCoord) * 53;
        int yMove = (computerMoveNewYCoord - computerMoveOGYCoord) * 53;
        if (currentColour == 'W') {
            currentColour = 'B';
        } else {
            currentColour = 'W';
        }
        System.out.println("Computer OG X: " + computerMoveOGXCoord);
        System.out.println("Computer OG Y: " + computerMoveOGYCoord);
        System.out.println("Computer New X: " + computerMoveNewXCoord);
        System.out.println("Computer New Y: " + computerMoveNewYCoord);
        // Moving the correct piece on both the GUI board and 2D array board whilst changing the current colour to the other player after the move
        switch (board[computerMoveOGYCoord][computerMoveOGXCoord]) {
            case "r1" -> blackRook1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "h1" -> blackKnight1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "b1" -> blackBishop1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q" -> blackQueenMove(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q1" -> blackQueen1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q2" -> blackQueen2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q3" -> blackQueen3Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q4" -> blackQueen4Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q5" -> blackQueen5Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q6" -> blackQueen6Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q7" -> blackQueen7Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "q8" -> blackQueen8Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "k" -> {
                if (nodeTree.isHasCastled()) {
                    if (computerMoveNewXCoord == 7) {
                        board[0][5] = board[computerMoveNewYCoord][computerMoveNewXCoord];
                        board[0][7] = "_";
                        board[0][6] = "k";
                        board[0][4] = "_";
                        currentBR2X = currentBR2X - 106;
                        blackRook2.setTranslateY(currentBR2Y);
                        blackRook2.setTranslateX(currentBR2X);
                        blackRook2.setY(currentBR2Y);
                        blackRook2.setX(currentBR2X);
                        currentBKX = currentBKX + 106;
                        blackKing.setTranslateY(currentBKY);
                        blackKing.setTranslateX(currentBKX);
                        blackKing.setY(currentBKY);
                        blackKing.setX(currentBKX);
                    } else if (computerMoveNewXCoord == 0) {
                        board[0][3] = board[computerMoveNewYCoord][computerMoveNewXCoord];
                        board[0][0] = "_";
                        board[0][2] = "k";
                        board[0][4] = "_";
                        currentBR1X = currentBR1X + 159;
                        blackRook1.setTranslateY(currentBR1Y);
                        blackRook1.setTranslateX(currentBR1X);
                        blackRook1.setY(currentBR1Y);
                        blackRook1.setX(currentBR1X);
                        currentBKX = currentBKX - 106;
                        blackKing.setTranslateY(currentBKY);
                        blackKing.setTranslateX(currentBKX);
                        blackKing.setY(currentBKY);
                        blackKing.setX(currentBKX);
                    }
                } else if (!board[computerMoveNewYCoord][computerMoveNewXCoord].equals("_")) {
                    takePiece(computerMoveNewYCoord, computerMoveNewXCoord);
                }
                if (!nodeTree.isHasCastled()) {
                    board[computerMoveNewYCoord][computerMoveNewXCoord] = "k";
                    board[computerMoveOGYCoord][computerMoveOGXCoord] = "_";
                    currentBKY = currentBKY + yMove;
                    currentBKX = currentBKX + xMove;
                    blackKing.setTranslateY(currentBKY);
                    blackKing.setTranslateX(currentBKX);
                    blackKing.setY(currentBKY);
                    blackKing.setX(currentBKX);
                }
                nodeTree.setHasCastled(false);
            } case "b2" -> blackBishop2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "h2" -> blackKnight2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "r2" -> blackRook2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p1" -> blackPawn1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p2" -> blackPawn2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p3" -> blackPawn3Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p4" -> blackPawn4Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p5" -> blackPawn5Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p6" -> blackPawn6Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p7" -> blackPawn7Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "p8" -> blackPawn8Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "R1" -> whiteRook1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "H1" -> whiteKnight1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "B1" -> whiteBishop1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q" -> whiteQueenMove(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q1" -> whiteQueen1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q2" -> whiteQueen2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q3" -> whiteQueen3Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q4" -> whiteQueen4Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q5" -> whiteQueen5Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q6" -> whiteQueen6Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q7" -> whiteQueen7Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "Q8" -> whiteQueen8Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "K" -> {
                if (nodeTree.isHasCastled()) {
                    if (computerMoveNewXCoord == 7) {
                        board[7][5] = board[computerMoveNewYCoord][computerMoveNewXCoord];
                        board[7][7] = "_";
                        board[7][6] = "K";
                        board[7][4] = "_";
                        currentWR2X = currentWR2X - 106;
                        whiteRook2.setTranslateY(currentWR2Y);
                        whiteRook2.setTranslateX(currentWR2X);
                        whiteRook2.setY(currentWR2Y);
                        whiteRook2.setX(currentWR2X);
                        currentWKX = currentWKX + 106;
                        whiteKing.setTranslateY(currentWKY);
                        whiteKing.setTranslateX(currentWKX);
                        whiteKing.setY(currentWKY);
                        whiteKing.setX(currentWKX);
                    } else if (computerMoveNewXCoord == 0) {
                        board[7][3] = board[computerMoveNewYCoord][computerMoveNewXCoord];
                        board[7][0] = "_";
                        board[7][2] = "K";
                        board[7][4] = "_";
                        currentWR1X = currentWR1X + 159;
                        whiteRook1.setTranslateY(currentWR1Y);
                        whiteRook1.setTranslateX(currentWR1X);
                        whiteRook1.setY(currentWR1Y);
                        whiteRook1.setX(currentWR1X);
                        currentWKX = currentWKX - 106;
                        whiteKing.setTranslateY(currentWKY);
                        whiteKing.setTranslateX(currentWKX);
                        whiteKing.setY(currentWKY);
                        whiteKing.setX(currentWKX);
                    }
                } else if (!board[computerMoveNewYCoord][computerMoveNewXCoord].equals("_")) {
                    takePiece(computerMoveNewYCoord, computerMoveNewXCoord);
                }
                if (!nodeTree.isHasCastled()) {
                    board[computerMoveNewYCoord][computerMoveNewXCoord] = "K";
                    board[computerMoveOGYCoord][computerMoveOGXCoord] = "_";
                    currentWKY = currentWKY + yMove;
                    currentWKX = currentWKX + xMove;
                    whiteKing.setTranslateY(currentWKY);
                    whiteKing.setTranslateX(currentWKX);
                    whiteKing.setY(currentWKY);
                    whiteKing.setX(currentWKX);
                }
                nodeTree.setHasCastled(false);
            } case "B2" -> whiteBishop2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "H2" -> whiteKnight2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "R2" -> whiteRook2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P1" -> whitePawn1Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P2" -> whitePawn2Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P3" -> whitePawn3Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P4" -> whitePawn4Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P5" -> whitePawn5Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P6" -> whitePawn6Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P7" -> whitePawn7Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
            case "P8" -> whitePawn8Move(computerMoveNewXCoord, computerMoveNewYCoord, computerMoveOGXCoord, computerMoveOGYCoord, xMove, yMove);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        if (NodeTree.getEvaluation() == 1000000 || NodeTree.getEvaluation() == -1000000) {
            System.out.println("CHECKMATE");
            endGameLabel.setVisible(true);
            endGameLabel.setText("CHECKMATE");
        }
        GenerateMoves generateMoves = new GenerateMoves(true);
        generateMoves.makeMove(currentColour, board);
        if (generateMoves.getMoveCounter() == 0) {
            System.out.println("DRAW");
            endGameLabel.setVisible(true);
            endGameLabel.setText("DRAW");
        }
        if (moveCounter != 0 && playerNumber != 1) {
            if (previousColourMove == 'W') {
                previousColourMove = 'B';
                currentColour = 'W';
            } else {
                previousColourMove = 'W';
                currentColour = 'B';
            }
        }
        moveCounter++;
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberOfPlayers.setItems(FXCollections.observableArrayList("0", "1", "2"));
        numberOfPlayers.setValue("1");
        difficultyLevel.setItems(FXCollections.observableArrayList("Easy", "Medium", "Hard"));
        difficultyLevel.setValue("Hard");
        startingColour.setItems(FXCollections.observableArrayList("White", "Black"));
        startingColour.setValue("White");
        moveList.setEditable(false);

        capturedBlackQueen.setVisible(false);
        capturedBlackRook1.setVisible(false);
        capturedBlackRook2.setVisible(false);
        capturedBlackBishop1.setVisible(false);
        capturedBlackBishop2.setVisible(false);
        capturedBlackKnight1.setVisible(false);
        capturedBlackKnight2.setVisible(false);
        capturedBlackPawn1.setVisible(false);
        capturedBlackPawn2.setVisible(false);
        capturedBlackPawn3.setVisible(false);
        capturedBlackPawn4.setVisible(false);
        capturedBlackPawn5.setVisible(false);
        capturedBlackPawn6.setVisible(false);
        capturedBlackPawn7.setVisible(false);
        capturedBlackPawn8.setVisible(false);

        capturedWhiteQueen.setVisible(false);
        capturedWhiteRook1.setVisible(false);
        capturedWhiteRook2.setVisible(false);
        capturedWhiteBishop1.setVisible(false);
        capturedWhiteBishop2.setVisible(false);
        capturedWhiteKnight1.setVisible(false);
        capturedWhiteKnight2.setVisible(false);
        capturedWhitePawn1.setVisible(false);
        capturedWhitePawn2.setVisible(false);
        capturedWhitePawn3.setVisible(false);
        capturedWhitePawn4.setVisible(false);
        capturedWhitePawn5.setVisible(false);
        capturedWhitePawn6.setVisible(false);
        capturedWhitePawn7.setVisible(false);
        capturedWhitePawn8.setVisible(false);

        blackQueen1.setVisible(false);
        blackQueen2.setVisible(false);
        blackQueen3.setVisible(false);
        blackQueen4.setVisible(false);
        blackQueen5.setVisible(false);
        blackQueen6.setVisible(false);
        blackQueen7.setVisible(false);
        blackQueen8.setVisible(false);

        whiteQueen1.setVisible(false);
        whiteQueen2.setVisible(false);
        whiteQueen3.setVisible(false);
        whiteQueen4.setVisible(false);
        whiteQueen5.setVisible(false);
        whiteQueen6.setVisible(false);
        whiteQueen7.setVisible(false);
        whiteQueen8.setVisible(false);

        currentBP1X = (int) blackPawn1.getX();
        currentBP1Y = (int) blackPawn1.getY();
        currentBP2X = (int) blackPawn2.getX();
        currentBP2Y = (int) blackPawn2.getY();
        currentBP3X = (int) blackPawn3.getX();
        currentBP3Y = (int) blackPawn3.getY();
        currentBP4X = (int) blackPawn4.getX();
        currentBP4Y = (int) blackPawn4.getY();
        currentBP5X = (int) blackPawn5.getX();
        currentBP5Y = (int) blackPawn5.getY();
        currentBP6X = (int) blackPawn6.getX();
        currentBP6Y = (int) blackPawn6.getY();
        currentBP7X = (int) blackPawn7.getX();
        currentBP7Y = (int) blackPawn7.getY();
        currentBP8X = (int) blackPawn8.getX();
        currentBP8Y = (int) blackPawn8.getY();
        currentBR1X = (int) blackRook1.getX();
        currentBR1Y = (int) blackRook1.getY();
        currentBR2X = (int) blackRook2.getX();
        currentBR2Y = (int) blackRook2.getY();
        currentBH1X = (int) blackKnight1.getX();
        currentBH1Y = (int) blackKnight1.getY();
        currentBH2X = (int) blackKnight2.getX();
        currentBH2Y = (int) blackKnight2.getY();
        currentBB1X = (int) blackBishop1.getX();
        currentBB1Y = (int) blackBishop1.getY();
        currentBB2X = (int) blackBishop2.getX();
        currentBB2Y = (int) blackBishop2.getY();
        currentBQX = (int) blackQueen.getX();
        currentBQY = (int) blackQueen.getY();
        currentBQ1X = (int) blackQueen1.getX();
        currentBQ1Y = (int) blackQueen1.getY();
        currentBQ2X = (int) blackQueen2.getX();
        currentBQ2Y = (int) blackQueen2.getY();
        currentBQ3X = (int) blackQueen3.getX();
        currentBQ3Y = (int) blackQueen3.getY();
        currentBQ4X = (int) blackQueen4.getX();
        currentBQ4Y = (int) blackQueen4.getY();
        currentBQ5X = (int) blackQueen5.getX();
        currentBQ5Y = (int) blackQueen5.getY();
        currentBQ6X = (int) blackQueen6.getX();
        currentBQ6Y = (int) blackQueen6.getY();
        currentBQ7X = (int) blackQueen7.getX();
        currentBQ7Y = (int) blackQueen7.getY();
        currentBQ8X = (int) blackQueen8.getX();
        currentBQ8Y = (int) blackQueen8.getY();
        currentBKX = (int) blackKing.getX();
        currentBKY = (int) blackKing.getY();


        currentWP1X = (int) whitePawn1.getX();
        currentWP1Y = (int) whitePawn1.getY();
        currentWP2X = (int) whitePawn2.getX();
        currentWP2Y = (int) whitePawn2.getY();
        currentWP3X = (int) whitePawn3.getX();
        currentWP3Y = (int) whitePawn3.getY();
        currentWP4X = (int) whitePawn4.getX();
        currentWP4Y = (int) whitePawn4.getY();
        currentWP5X = (int) whitePawn5.getX();
        currentWP5Y = (int) whitePawn5.getY();
        currentWP6X = (int) whitePawn6.getX();
        currentWP6Y = (int) whitePawn6.getY();
        currentWP7X = (int) whitePawn7.getX();
        currentWP7Y = (int) whitePawn7.getY();
        currentWP8X = (int) whitePawn8.getX();
        currentWP8Y = (int) whitePawn8.getY();
        currentWR1X = (int) whiteRook1.getX();
        currentWR1Y = (int) whiteRook1.getY();
        currentWR2X = (int) whiteRook2.getX();
        currentWR2Y = (int) whiteRook2.getY();
        currentWH1X = (int) whiteKnight1.getX();
        currentWH1Y = (int) whiteKnight1.getY();
        currentWH2X = (int) whiteKnight2.getX();
        currentWH2Y = (int) whiteKnight2.getY();
        currentWB1X = (int) whiteBishop1.getX();
        currentWB1Y = (int) whiteBishop1.getY();
        currentWB2X = (int) whiteBishop2.getX();
        currentWB2Y = (int) whiteBishop2.getY();
        currentWQX = (int) whiteQueen.getX();
        currentWQY = (int) whiteQueen.getY();
        currentWQ1X = (int) whiteQueen1.getX();
        currentWQ1Y = (int) whiteQueen1.getY();
        currentWQ2X = (int) whiteQueen2.getX();
        currentWQ2Y = (int) whiteQueen2.getY();
        currentWQ3X = (int) whiteQueen3.getX();
        currentWQ3Y = (int) whiteQueen3.getY();
        currentWQ4X = (int) whiteQueen4.getX();
        currentWQ4Y = (int) whiteQueen4.getY();
        currentWQ5X = (int) whiteQueen5.getX();
        currentWQ5Y = (int) whiteQueen5.getY();
        currentWQ6X = (int) whiteQueen6.getX();
        currentWQ6Y = (int) whiteQueen6.getY();
        currentWQ7X = (int) whiteQueen7.getX();
        currentWQ7Y = (int) whiteQueen7.getY();
        currentWQ8X = (int) whiteQueen8.getX();
        currentWQ8Y = (int) whiteQueen8.getY();
        currentWKX = (int) whiteKing.getX();
        currentWKY = (int) whiteKing.getY();
        onButtonPress();
    }
    public boolean isInBoard(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex) {
        return newPosXIndex < 0 || newPosXIndex > 7 || newPosYIndex < 0 || newPosYIndex > 7 || originalPosXIndex < 0 || originalPosXIndex > 7 || originalPosYIndex < 0 || originalPosYIndex > 7;
    }
    public void blackRook1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "r1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBR1Y = currentBR1Y + yMove;
        currentBR1X = currentBR1X + xMove;
        blackRook1.setTranslateY(currentBR1Y);
        blackRook1.setTranslateX(currentBR1X);
        blackRook1.setY(currentBR1Y);
        blackRook1.setX(currentBR1X);
    }
    public void blackRook2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "r2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBR2Y = currentBR2Y + yMove;
        currentBR2X = currentBR2X + xMove;
        blackRook2.setTranslateY(currentBR2Y);
        blackRook2.setTranslateX(currentBR2X);
        blackRook2.setY(currentBR2Y);
        blackRook2.setX(currentBR2X);
    }
    public void blackKnight1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "h1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBH1Y = currentBH1Y + yMove;
        currentBH1X = currentBH1X + xMove;
        blackKnight1.setTranslateY(currentBH1Y);
        blackKnight1.setTranslateX(currentBH1X);
        blackKnight1.setY(currentBH1Y);
        blackKnight1.setX(currentBH1X);
    }
    public void blackKnight2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "h2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBH2Y = currentBH2Y + yMove;
        currentBH2X = currentBH2X + xMove;
        blackKnight2.setTranslateY(currentBH2Y);
        blackKnight2.setTranslateX(currentBH2X);
        blackKnight2.setY(currentBH2Y);
        blackKnight2.setX(currentBH2X);
    }
    public void blackBishop1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "b1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBB1Y = currentBB1Y + yMove;
        currentBB1X = currentBB1X + xMove;
        blackBishop1.setTranslateY(currentBB1Y);
        blackBishop1.setTranslateX(currentBB1X);
        blackBishop1.setY(currentBB1Y);
        blackBishop1.setX(currentBB1X);
    }
    public void blackBishop2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "b2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBB2Y = currentBB2Y + yMove;
        currentBB2X = currentBB2X + xMove;
        blackBishop2.setTranslateY(currentBB2Y);
        blackBishop2.setTranslateX(currentBB2X);
        blackBishop2.setY(currentBB2Y);
        blackBishop2.setX(currentBB2X);
    }
    public void blackQueenMove(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQY = currentBQY + yMove;
        currentBQX = currentBQX + xMove;
        blackQueen.setTranslateY(currentBQY);
        blackQueen.setTranslateX(currentBQX);
        blackQueen.setY(currentBQY);
        blackQueen.setX(currentBQX);
    }
    public void blackQueen1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ1Y = currentBQ1Y + yMove;
        currentBQ1X = currentBQ1X + xMove;
        blackQueen1.setTranslateY(currentBQ1Y);
        blackQueen1.setTranslateX(currentBQ1X);
        blackQueen1.setY(currentBQ1Y);
        blackQueen1.setX(currentBQ1X);
    }
    public void blackQueen2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ2Y = currentBQ2Y + yMove;
        currentBQ2X = currentBQ2X + xMove;
        blackQueen2.setTranslateY(currentBQ2Y);
        blackQueen2.setTranslateX(currentBQ2X);
        blackQueen2.setY(currentBQ2Y);
        blackQueen2.setX(currentBQ2X);
    }
    public void blackQueen3Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q3";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ3Y = currentBQ3Y + yMove;
        currentBQ3X = currentBQ3X + xMove;
        blackQueen3.setTranslateY(currentBQ3Y);
        blackQueen3.setTranslateX(currentBQ3X);
        blackQueen3.setY(currentBQ3Y);
        blackQueen3.setX(currentBQ3X);
    }
    public void blackQueen4Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q4";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ4Y = currentBQ4Y + yMove;
        currentBQ4X = currentBQ4X + xMove;
        blackQueen4.setTranslateY(currentBQ4Y);
        blackQueen4.setTranslateX(currentBQ4X);
        blackQueen4.setY(currentBQ4Y);
        blackQueen4.setX(currentBQ4X);
    }
    public void blackQueen5Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q5";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ5Y = currentBQ5Y + yMove;
        currentBQ5X = currentBQ5X + xMove;
        blackQueen5.setTranslateY(currentBQ5Y);
        blackQueen5.setTranslateX(currentBQ5X);
        blackQueen5.setY(currentBQ5Y);
        blackQueen5.setX(currentBQ5X);
    }
    public void blackQueen6Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q6";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ6Y = currentBQ6Y + yMove;
        currentBQ6X = currentBQ6X + xMove;
        blackQueen6.setTranslateY(currentBQ6Y);
        blackQueen6.setTranslateX(currentBQ6X);
        blackQueen6.setY(currentBQ6Y);
        blackQueen6.setX(currentBQ6X);
    }
    public void blackQueen7Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q7";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ7Y = currentBQ7Y + yMove;
        currentBQ7X = currentBQ7X + xMove;
        blackQueen7.setTranslateY(currentBQ7Y);
        blackQueen7.setTranslateX(currentBQ7X);
        blackQueen7.setY(currentBQ7Y);
        blackQueen7.setX(currentBQ7X);
    }
    public void blackQueen8Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "q8";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentBQ8Y = currentBQ8Y + yMove;
        currentBQ8X = currentBQ8X + xMove;
        blackQueen8.setTranslateY(currentBQ8Y);
        blackQueen8.setTranslateX(currentBQ8X);
        blackQueen8.setY(currentBQ8Y);
        blackQueen8.setX(currentBQ8X);
    }
    public void blackKingMove(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (isCastleMove) {
            if (newPosXIndex == 7) {
                board[0][5] = board[newPosYIndex][newPosXIndex];
                board[0][7] = "_";
                board[0][6] = "k";
                board[0][4] = "_";
                currentBR2X = currentBR2X - 106;
                blackRook2.setTranslateY(currentBR2Y);
                blackRook2.setTranslateX(currentBR2X);
                blackRook2.setY(currentBR2Y);
                blackRook2.setX(currentBR2X);
                currentBKX = currentBKX + 106;
                blackKing.setTranslateY(currentBKY);
                blackKing.setTranslateX(currentBKX);
                blackKing.setY(currentBKY);
                blackKing.setX(currentBKX);
            } else if (newPosXIndex == 0) {
                board[0][3] = board[newPosYIndex][newPosXIndex];
                board[0][0] = "_";
                board[0][2] = "k";
                board[0][4] = "_";
                currentBR1X = currentBR1X + 159;
                blackRook1.setTranslateY(currentBR1Y);
                blackRook1.setTranslateX(currentBR1X);
                blackRook1.setY(currentBR1Y);
                blackRook1.setX(currentBR1X);
                currentBKX = currentBKX - 106;
                blackKing.setTranslateY(currentBKY);
                blackKing.setTranslateX(currentBKX);
                blackKing.setY(currentBKY);
                blackKing.setX(currentBKX);
            }
        } else if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        if (!isCastleMove) {
            board[newPosYIndex][newPosXIndex] = "k";
            board[originalPosYIndex][originalPosXIndex] = "_";
            currentBKY = currentBKY + yMove;
            currentBKX = currentBKX + xMove;
            blackKing.setTranslateY(currentBKY);
            blackKing.setTranslateX(currentBKX);
            blackKing.setY(currentBKY);
            blackKing.setX(currentBKX);
        }
        isCastleMove = false;
    }
    public void blackPawn1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
        return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q1";
            blackQueen1.setVisible(true);
            blackPawn1.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p1";
            currentBP1Y = currentBP1Y + yMove;
            currentBP1X = currentBP1X + xMove;
            blackPawn1.setTranslateY(currentBP1Y);
            blackPawn1.setTranslateX(currentBP1X);
            blackPawn1.setY(currentBP1Y);
            blackPawn1.setX(currentBP1X);
        }
        currentBQ1Y = currentBQ1Y + yMove;
        currentBQ1X = currentBQ1X + xMove;
        blackQueen1.setTranslateY(currentBQ1Y);
        blackQueen1.setTranslateX(currentBQ1X);
        blackQueen1.setY(currentBQ1Y);
        blackQueen1.setX(currentBQ1X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q2";
            blackQueen2.setVisible(true);
            blackPawn2.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p2";
            currentBP2Y = currentBP2Y + yMove;
            currentBP2X = currentBP2X + xMove;
            blackPawn2.setTranslateY(currentBP2Y);
            blackPawn2.setTranslateX(currentBP2X);
            blackPawn2.setY(currentBP2Y);
            blackPawn2.setX(currentBP2X);
        }
        currentBQ2Y = currentBQ2Y + yMove;
        currentBQ2X = currentBQ2X + xMove;
        blackQueen2.setTranslateY(currentBQ2Y);
        blackQueen2.setTranslateX(currentBQ2X);
        blackQueen2.setY(currentBQ2Y);
        blackQueen2.setX(currentBQ2X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn3Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q3";
            blackQueen3.setVisible(true);
            blackPawn3.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p3";
            currentBP3Y = currentBP3Y + yMove;
            currentBP3X = currentBP3X + xMove;
            blackPawn3.setTranslateY(currentBP3Y);
            blackPawn3.setTranslateX(currentBP3X);
            blackPawn3.setY(currentBP3Y);
            blackPawn3.setX(currentBP3X);
        }
        currentBQ3Y = currentBQ3Y + yMove;
        currentBQ3X = currentBQ3X + xMove;
        blackQueen3.setTranslateY(currentBQ3Y);
        blackQueen3.setTranslateX(currentBQ3X);
        blackQueen3.setY(currentBQ3Y);
        blackQueen3.setX(currentBQ3X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn4Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q4";
            blackQueen4.setVisible(true);
            blackPawn4.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p4";
            currentBP4Y = currentBP4Y + yMove;
            currentBP4X = currentBP4X + xMove;
            blackPawn4.setTranslateY(currentBP4Y);
            blackPawn4.setTranslateX(currentBP4X);
            blackPawn4.setY(currentBP4Y);
            blackPawn4.setX(currentBP4X);
        }
        currentBQ4Y = currentBQ4Y + yMove;
        currentBQ4X = currentBQ4X + xMove;
        blackQueen4.setTranslateY(currentBQ4Y);
        blackQueen4.setTranslateX(currentBQ4X);
        blackQueen4.setY(currentBQ4Y);
        blackQueen4.setX(currentBQ4X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn5Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q5";
            blackQueen5.setVisible(true);
            blackPawn5.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p5";
            currentBP5Y = currentBP5Y + yMove;
            currentBP5X = currentBP5X + xMove;
            blackPawn5.setTranslateY(currentBP5Y);
            blackPawn5.setTranslateX(currentBP5X);
            blackPawn5.setY(currentBP5Y);
            blackPawn5.setX(currentBP5X);
        }
        currentBQ5Y = currentBQ5Y + yMove;
        currentBQ5X = currentBQ5X + xMove;
        blackQueen5.setTranslateY(currentBQ5Y);
        blackQueen5.setTranslateX(currentBQ5X);
        blackQueen5.setY(currentBQ5Y);
        blackQueen5.setX(currentBQ5X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn6Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q6";
            blackQueen6.setVisible(true);
            blackPawn6.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p6";
            currentBP6Y = currentBP6Y + yMove;
            currentBP6X = currentBP6X + xMove;
            blackPawn6.setTranslateY(currentBP6Y);
            blackPawn6.setTranslateX(currentBP6X);
            blackPawn6.setY(currentBP6Y);
            blackPawn6.setX(currentBP6X);
        }
        currentBQ6Y = currentBQ6Y + yMove;
        currentBQ6X = currentBQ6X + xMove;
        blackQueen6.setTranslateY(currentBQ6Y);
        blackQueen6.setTranslateX(currentBQ6X);
        blackQueen6.setY(currentBQ6Y);
        blackQueen6.setX(currentBQ6X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn7Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q7";
            blackQueen7.setVisible(true);
            blackPawn7.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p7";
            currentBP7Y = currentBP7Y + yMove;
            currentBP7X = currentBP7X + xMove;
            blackPawn7.setTranslateY(currentBP7Y);
            blackPawn7.setTranslateX(currentBP7X);
            blackPawn7.setY(currentBP7Y);
            blackPawn7.setX(currentBP7X);
        }
        currentBQ7Y = currentBQ7Y + yMove;
        currentBQ7X = currentBQ7X + xMove;
        blackQueen7.setTranslateY(currentBQ7Y);
        blackQueen7.setTranslateX(currentBQ7X);
        blackQueen7.setY(currentBQ7Y);
        blackQueen7.setX(currentBQ7X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void blackPawn8Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 7) {
            board[newPosYIndex][newPosXIndex] = "q8";
            blackQueen8.setVisible(true);
            blackPawn8.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "p8";
            currentBP8Y = currentBP8Y + yMove;
            currentBP8X = currentBP8X + xMove;
            blackPawn8.setTranslateY(currentBP8Y);
            blackPawn8.setTranslateX(currentBP8X);
            blackPawn8.setY(currentBP8Y);
            blackPawn8.setX(currentBP8X);
        }
        currentBQ8Y = currentBQ8Y + yMove;
        currentBQ8X = currentBQ8X + xMove;
        blackQueen8.setTranslateY(currentBQ8Y);
        blackQueen8.setTranslateX(currentBQ8X);
        blackQueen8.setY(currentBQ8Y);
        blackQueen8.setX(currentBQ8X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whiteRook1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "R1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWR1Y = currentWR1Y + yMove;
        currentWR1X = currentWR1X + xMove;
        whiteRook1.setTranslateY(currentWR1Y);
        whiteRook1.setTranslateX(currentWR1X);
        whiteRook1.setY(currentWR1Y);
        whiteRook1.setX(currentWR1X);
    }
    public void whiteRook2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "R2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWR2Y = currentWR2Y + yMove;
        currentWR2X = currentWR2X + xMove;
        whiteRook2.setTranslateY(currentWR2Y);
        whiteRook2.setTranslateX(currentWR2X);
        whiteRook2.setY(currentWR2Y);
        whiteRook2.setX(currentWR2X);
    }
    public void whiteKnight1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "H1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWH1Y = currentWH1Y + yMove;
        currentWH1X = currentWH1X + xMove;
        whiteKnight1.setTranslateY(currentWH1Y);
        whiteKnight1.setTranslateX(currentWH1X);
        whiteKnight1.setY(currentWH1Y);
        whiteKnight1.setX(currentWH1X);
    }
    public void whiteKnight2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "H2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWH2Y = currentWH2Y + yMove;
        currentWH2X = currentWH2X + xMove;
        whiteKnight2.setTranslateY(currentWH2Y);
        whiteKnight2.setTranslateX(currentWH2X);
        whiteKnight2.setY(currentWH2Y);
        whiteKnight2.setX(currentWH2X);
    }
    public void whiteBishop1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "B1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWB1Y = currentWB1Y + yMove;
        currentWB1X = currentWB1X + xMove;
        whiteBishop1.setTranslateY(currentWB1Y);
        whiteBishop1.setTranslateX(currentWB1X);
        whiteBishop1.setY(currentWB1Y);
        whiteBishop1.setX(currentWB1X);
    }
    public void whiteBishop2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "B2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWB2Y = currentWB2Y + yMove;
        currentWB2X = currentWB2X + xMove;
        whiteBishop2.setTranslateY(currentWB2Y);
        whiteBishop2.setTranslateX(currentWB2X);
        whiteBishop2.setY(currentWB2Y);
        whiteBishop2.setX(currentWB2X);
    }
    public void whiteQueenMove(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        board[newPosYIndex][newPosXIndex] = "Q";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQY = currentWQY + yMove;
        currentWQX = currentWQX + xMove;
        whiteQueen.setTranslateY(currentWQY);
        whiteQueen.setTranslateX(currentWQX);
        whiteQueen.setY(currentWQY);
        whiteQueen.setX(currentWQX);
    }
    public void whiteQueen1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q1";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ1Y = currentWQ1Y + yMove;
        currentWQ1X = currentWQ1X + xMove;
        whiteQueen1.setTranslateY(currentWQ1Y);
        whiteQueen1.setTranslateX(currentWQ1X);
        whiteQueen1.setY(currentWQ1Y);
        whiteQueen1.setX(currentWQ1X);
    }
    public void whiteQueen2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q2";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ2Y = currentWQ2Y + yMove;
        currentWQ2X = currentWQ2X + xMove;
        whiteQueen2.setTranslateY(currentWQ2Y);
        whiteQueen2.setTranslateX(currentWQ2X);
        whiteQueen2.setY(currentWQ2Y);
        whiteQueen2.setX(currentWQ2X);
    }
    public void whiteQueen3Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q3";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ3Y = currentWQ3Y + yMove;
        currentWQ3X = currentWQ3X + xMove;
        whiteQueen3.setTranslateY(currentWQ3Y);
        whiteQueen3.setTranslateX(currentWQ3X);
        whiteQueen3.setY(currentWQ3Y);
        whiteQueen3.setX(currentWQ3X);
    }
    public void whiteQueen4Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q4";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ4Y = currentWQ4Y + yMove;
        currentWQ4X = currentWQ4X + xMove;
        whiteQueen4.setTranslateY(currentWQ4Y);
        whiteQueen4.setTranslateX(currentWQ4X);
        whiteQueen4.setY(currentWQ4Y);
        whiteQueen4.setX(currentWQ4X);
    }
     public void whiteQueen5Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q5";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ5Y = currentWQ5Y + yMove;
        currentWQ5X = currentWQ5X + xMove;
        whiteQueen5.setTranslateY(currentWQ5Y);
        whiteQueen5.setTranslateX(currentWQ5X);
        whiteQueen5.setY(currentWQ5Y);
        whiteQueen5.setX(currentWQ5X);
    }
    public void whiteQueen6Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q6";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ6Y = currentWQ6Y + yMove;
        currentWQ6X = currentWQ6X + xMove;
        whiteQueen6.setTranslateY(currentWQ6Y);
        whiteQueen6.setTranslateX(currentWQ6X);
        whiteQueen6.setY(currentWQ6Y);
        whiteQueen6.setX(currentWQ6X);
    }
    public void whiteQueen7Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q7";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ7Y = currentWQ7Y + yMove;
        currentWQ7X = currentWQ7X + xMove;
        whiteQueen7.setTranslateY(currentWQ7Y);
        whiteQueen7.setTranslateX(currentWQ7X);
        whiteQueen7.setY(currentWQ7Y);
        whiteQueen7.setX(currentWQ7X);
    }
    public void whiteQueen8Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        board[newPosYIndex][newPosXIndex] = "Q8";
        board[originalPosYIndex][originalPosXIndex] = "_";
        currentWQ8Y = currentWQ8Y + yMove;
        currentWQ8X = currentWQ8X + xMove;
        whiteQueen8.setTranslateY(currentWQ8Y);
        whiteQueen8.setTranslateX(currentWQ8X);
        whiteQueen8.setY(currentWQ8Y);
        whiteQueen8.setX(currentWQ8X);
    }
    public void whiteKingMove(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (isCastleMove) {
            if (newPosXIndex == 7) {
                board[7][5] = board[newPosYIndex][newPosXIndex];
                board[7][7] = "_";
                board[7][6] = "K";
                board[7][4] = "_";
                currentWR2X = currentWR2X - 106;
                whiteRook2.setTranslateY(currentWR2Y);
                whiteRook2.setTranslateX(currentWR2X);
                whiteRook2.setY(currentWR2Y);
                whiteRook2.setX(currentWR2X);
                currentWKX = currentWKX + 106;
                whiteKing.setTranslateY(currentWKY);
                whiteKing.setTranslateX(currentWKX);
                whiteKing.setY(currentWKY);
                whiteKing.setX(currentWKX);
            } else if (newPosXIndex == 0) {
                board[7][3] = board[newPosYIndex][newPosXIndex];
                board[7][0] = "_";
                board[7][2] = "K";
                board[7][4] = "_";
                currentWR1X = currentWR1X + 159;
                whiteRook1.setTranslateY(currentWR1Y);
                whiteRook1.setTranslateX(currentWR1X);
                whiteRook1.setY(currentWR1Y);
                whiteRook1.setX(currentWR1X);
                currentWKX = currentWKX - 106;
                whiteKing.setTranslateY(currentWKY);
                whiteKing.setTranslateX(currentWKX);
                whiteKing.setY(currentWKY);
                whiteKing.setX(currentWKX);
            }
        } else if (!board[newPosYIndex][newPosXIndex].equals("_")) {
            takePiece(newPosYIndex, newPosXIndex);
        }
        if (!isCastleMove) {
            board[newPosYIndex][newPosXIndex] = "K";
            board[originalPosYIndex][originalPosXIndex] = "_";
            currentWKY = currentWKY + yMove;
            currentWKX = currentWKX + xMove;
            whiteKing.setTranslateY(currentWKY);
            whiteKing.setTranslateX(currentWKX);
            whiteKing.setY(currentWKY);
            whiteKing.setX(currentWKX);
        }
        isCastleMove = false;
    }
    public void whitePawn1Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q1";
            whiteQueen1.setVisible(true);
            whitePawn1.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P1";
            currentWP1Y = currentWP1Y + yMove;
            currentWP1X = currentWP1X + xMove;
            whitePawn1.setTranslateY(currentWP1Y);
            whitePawn1.setTranslateX(currentWP1X);
            whitePawn1.setY(currentWP1Y);
            whitePawn1.setX(currentWP1X);
        }
        currentWQ1Y = currentWQ1Y + yMove;
        currentWQ1X = currentWQ1X + xMove;
        whiteQueen1.setTranslateY(currentWQ1Y);
        whiteQueen1.setTranslateX(currentWQ1X);
        whiteQueen1.setY(currentWQ1Y);
        whiteQueen1.setX(currentWQ1X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn2Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q2";
            whiteQueen2.setVisible(true);
            whitePawn2.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P2";
            currentWP2Y = currentWP2Y + yMove;
            currentWP2X = currentWP2X + xMove;
            whitePawn2.setTranslateY(currentWP2Y);
            whitePawn2.setTranslateX(currentWP2X);
            whitePawn2.setY(currentWP2Y);
            whitePawn2.setX(currentWP2X);
        }
        currentWQ2Y = currentWQ2Y + yMove;
        currentWQ2X = currentWQ2X + xMove;
        whiteQueen2.setTranslateY(currentWQ2Y);
        whiteQueen2.setTranslateX(currentWQ2X);
        whiteQueen2.setY(currentWQ2Y);
        whiteQueen2.setX(currentWQ2X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn3Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q3";
            whiteQueen3.setVisible(true);
            whitePawn3.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P3";
            currentWP3Y = currentWP3Y + yMove;
            currentWP3X = currentWP3X + xMove;
            whitePawn3.setTranslateY(currentWP3Y);
            whitePawn3.setTranslateX(currentWP3X);
            whitePawn3.setY(currentWP3Y);
            whitePawn3.setX(currentWP3X);
        }
        currentWQ3Y = currentWQ3Y + yMove;
        currentWQ3X = currentWQ3X + xMove;
        whiteQueen3.setTranslateY(currentWQ3Y);
        whiteQueen3.setTranslateX(currentWQ3X);
        whiteQueen3.setY(currentWQ3Y);
        whiteQueen3.setX(currentWQ3X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn4Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q4";
            whiteQueen4.setVisible(true);
            whitePawn4.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P4";
            currentWP4Y = currentWP4Y + yMove;
            currentWP4X = currentWP4X + xMove;
            whitePawn4.setTranslateY(currentWP4Y);
            whitePawn4.setTranslateX(currentWP4X);
            whitePawn4.setY(currentWP4Y);
            whitePawn4.setX(currentWP4X);
        }
        currentWQ4Y = currentWQ4Y + yMove;
        currentWQ4X = currentWQ4X + xMove;
        whiteQueen4.setTranslateY(currentWQ4Y);
        whiteQueen4.setTranslateX(currentWQ4X);
        whiteQueen4.setY(currentWQ4Y);
        whiteQueen4.setX(currentWQ4X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn5Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q5";
            whiteQueen5.setVisible(true);
            whitePawn5.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P5";
            currentWP5Y = currentWP5Y + yMove;
            currentWP5X = currentWP5X + xMove;
            whitePawn5.setTranslateY(currentWP5Y);
            whitePawn5.setTranslateX(currentWP5X);
            whitePawn5.setY(currentWP5Y);
            whitePawn5.setX(currentWP5X);
        }
        currentWQ5Y = currentWQ5Y + yMove;
        currentWQ5X = currentWQ5X + xMove;
        whiteQueen5.setTranslateY(currentWQ5Y);
        whiteQueen5.setTranslateX(currentWQ5X);
        whiteQueen5.setY(currentWQ5Y);
        whiteQueen5.setX(currentWQ5X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn6Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q6";
            whiteQueen6.setVisible(true);
            whitePawn6.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P6";
            currentWP6Y = currentWP6Y + yMove;
            currentWP6X = currentWP6X + xMove;
            whitePawn6.setTranslateY(currentWP6Y);
            whitePawn6.setTranslateX(currentWP6X);
            whitePawn6.setY(currentWP6Y);
            whitePawn6.setX(currentWP6X);

        }
        currentWQ6Y = currentWQ6Y + yMove;
        currentWQ6X = currentWQ6X + xMove;
        whiteQueen6.setTranslateY(currentWQ6Y);
        whiteQueen6.setTranslateX(currentWQ6X);
        whiteQueen6.setY(currentWQ6Y);
        whiteQueen6.setX(currentWQ6X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn7Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q7";
            whiteQueen7.setVisible(true);
            whitePawn7.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P7";
            currentWP7Y = currentWP7Y + yMove;
            currentWP7X = currentWP7X + xMove;
            whitePawn7.setTranslateY(currentWP7Y);
            whitePawn7.setTranslateX(currentWP7X);
            whitePawn7.setY(currentWP7Y);
            whitePawn7.setX(currentWP7X);
        }
        currentWQ7Y = currentWQ7Y + yMove;
        currentWQ7X = currentWQ7X + xMove;
        whiteQueen7.setTranslateY(currentWQ7Y);
        whiteQueen7.setTranslateX(currentWQ7X);
        whiteQueen7.setY(currentWQ7Y);
        whiteQueen7.setX(currentWQ7X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public void whitePawn8Move(int newPosXIndex, int newPosYIndex, int originalPosXIndex, int originalPosYIndex, int xMove, int yMove) {
        if (isInBoard(newPosXIndex, newPosYIndex, originalPosXIndex, originalPosYIndex)) {
            return;
        }
        if (!board[newPosYIndex][newPosXIndex].equals("_"))
            takePiece(newPosYIndex, newPosXIndex);
        if (newPosYIndex == 0) {
            board[newPosYIndex][newPosXIndex] = "Q8";
            whiteQueen8.setVisible(true);
            whitePawn8.setVisible(false);
        } else {
            board[newPosYIndex][newPosXIndex] = "P8";
            currentWP8Y = currentWP8Y + yMove;
            currentWP8X = currentWP8X + xMove;
            whitePawn8.setTranslateY(currentWP8Y);
            whitePawn8.setTranslateX(currentWP8X);
            whitePawn8.setY(currentWP8Y);
            whitePawn8.setX(currentWP8X);
        }
        currentWQ8Y = currentWQ8Y + yMove;
        currentWQ8X = currentWQ8X + xMove;
        whiteQueen8.setTranslateY(currentWQ8Y);
        whiteQueen8.setTranslateX(currentWQ8X);
        whiteQueen8.setY(currentWQ8Y);
        whiteQueen8.setX(currentWQ8X);
        board[originalPosYIndex][originalPosXIndex] = "_";
    }
    public String getComputerMoveChessCoord() {
        String computerMoveChessCoord = "";
        if (computerMoveNewXCoord == 0) {
            computerMoveChessCoord = "\t" + "A";
        } else if (computerMoveNewXCoord == 1) {
            computerMoveChessCoord = "\t" + "B";
        } else if (computerMoveNewXCoord == 2) {
            computerMoveChessCoord = "\t" + "C";
        } else if (computerMoveNewXCoord == 3) {
            computerMoveChessCoord = "\t" + "D";
        } else if (computerMoveNewXCoord == 4) {
            computerMoveChessCoord = "\t" + "E";
        } else if (computerMoveNewXCoord == 5) {
            computerMoveChessCoord = "\t" + "F";
        } else if (computerMoveNewXCoord == 6) {
            computerMoveChessCoord = "\t" + "G";
        } else if (computerMoveNewXCoord == 7) {
            computerMoveChessCoord = "\t" + "H";
        }
        if (computerMoveNewYCoord == 0) {
            computerMoveChessCoord += 8 + "\n";
        } else if (computerMoveNewYCoord == 1) {
            computerMoveChessCoord += 7 + "\n";
        } else if (computerMoveNewYCoord == 2) {
            computerMoveChessCoord += 6 + "\n";
        } else if (computerMoveNewYCoord == 3) {
            computerMoveChessCoord += 5 + "\n";
        } else if (computerMoveNewYCoord == 4) {
            computerMoveChessCoord += 4 + "\n";
        } else if (computerMoveNewYCoord == 5) {
            computerMoveChessCoord += 3 + "\n";
        } else if (computerMoveNewYCoord == 6) {
            computerMoveChessCoord += 2 + "\n";
        } else if (computerMoveNewYCoord == 7) {
            computerMoveChessCoord += 1 + "\n";
        }
        return computerMoveChessCoord;
    }
    public NodeTree getNodeTree() {
        int difficulty = 0;
        if (difficultyLevel.getSelectionModel().getSelectedItem().equals("Easy")) {
            difficulty = 2;
        } else if (difficultyLevel.getSelectionModel().getSelectedItem().equals("Medium")) {
            difficulty = 3;
        } else if (difficultyLevel.getSelectionModel().getSelectedItem().equals("Hard")) {
            difficulty = 4;
        }
        return new NodeTree(board, currentColour, difficulty);
    }
    public boolean doesMoveCheck() {
        King checkKing = new King();
        if (checkKing.isChecked(tempBoard, currentColour)) {
            System.out.println("in check");
            isCheckmate = checkKing.isCheckmate(board, currentColour);
            return false;
        } else {
            System.out.println("not in check");
            return true;
        }
        // Checking if the move results in the current players king being in check
    }
    public boolean inputCoordinates(int originalX, int originalY, int newX, int newY) {
        if (isInBoard(newX, newY, originalX, originalY)) {
            return false;
        }
        if (currentColour == 'W') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toUpperCase();
        } else if (currentColour == 'B') {
            for (int i = 0; i < 6; i++)
                currentPieces[i] = currentPieces[i].toLowerCase();
        }
        // Changing the current piece array to be the correct colour

        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, 8);
        }
        // Copying the current game board onto a temporary board

        // Checking the move is valid for the piece on the given square
        // If valid then the move is made on the temporary board
        // Then the temporary board is passed into a method to check if the move results in any checks
        // If it doesn't then the move is valid

        if (board[originalY][originalX].charAt(0) == currentPieces[0].charAt(0)) {
            if (pawn.checkValid(originalX, originalY, newX, newY, currentColour, tempBoard)) {
                if ((currentColour == 'W' && newY == 7) || (currentColour == 'B' && newY == 0)) {
                    tempBoard[originalY][originalX] = "_";
                    tempBoard[newY][newX] = currentPieces[4] + board[originalY][originalX].charAt(1);
                    return doesMoveCheck();
                } else {
                    tempBoard[originalY][originalX] = "_";
                    tempBoard[newY][newX] = board[originalY][originalX];
                    return doesMoveCheck();
                }
            } else
                return false;
        } else if (board[originalY][originalX].charAt(0) == currentPieces[1].charAt(0)) {
            if (rook.checkValid(originalX, originalY, newX, newY, currentColour, board)) {
                tempBoard[originalY][originalX] = "_";
                tempBoard[newY][newX] = board[originalY][originalX];
                return doesMoveCheck();
            } else
                return false;
        } else if (board[originalY][originalX].charAt(0) == currentPieces[2].charAt(0)) {
            if (bishop.checkValid(originalX, originalY, newX, newY, currentColour, board)) {
                tempBoard[originalY][originalX] = "_";
                tempBoard[newY][newX] = board[originalY][originalX];
                return doesMoveCheck();
            } else
                return false;
        } else if (board[originalY][originalX].charAt(0) == currentPieces[3].charAt(0)) {
            if (knight.checkValid(originalX, originalY, newX, newY, currentColour, board)) {
                tempBoard[originalY][originalX] = "_";
                tempBoard[newY][newX] = board[originalY][originalX];
                return doesMoveCheck();
            } else
                return false;
        } else if (board[originalY][originalX].charAt(0) == currentPieces[4].charAt(0)) {
            if (queen.checkValid(originalX, originalY, newX, newY, currentColour, board)) {
                tempBoard[originalY][originalX] = "_";
                tempBoard[newY][newX] = board[originalY][originalX];
                return doesMoveCheck();
            } else
                return false;
        } else if (board[originalY][originalX].charAt(0) == currentPieces[5].charAt(0)) {
            if (king.canCastle(originalX, originalY, newX, newY, currentColour, board)) {
                if (originalX == 4 && originalY == 7) {
                    if (newX == 7 && newY == 7) {
                        tempBoard[7][7] = "_";
                        tempBoard[7][6] = "K";
                        tempBoard[7][5] = board[newY][newX];
                        tempBoard[7][4] = "_";
                        isCastleMove = true;
                        return doesMoveCheck();
                    } else if (newX == 0 && newY == 7) {
                        tempBoard[7][0] = "_";
                        tempBoard[7][2] = "K";
                        tempBoard[7][3] = board[newY][newX];
                        tempBoard[7][4] = "_";
                        isCastleMove = true;
                        return doesMoveCheck();
                    }
                } else if (originalX == 4 && originalY == 0) {
                    if (newX == 7 && newY == 0) {
                        tempBoard[0][7] = "_";
                        tempBoard[0][6] = "k";
                        tempBoard[0][5] = board[newY][newX];
                        tempBoard[0][4] = "_";
                        isCastleMove = true;
                        return doesMoveCheck();
                    } else if (newX == 0 && newY == 0) {
                        tempBoard[0][0] = "_";
                        tempBoard[0][2] = "k";
                        tempBoard[0][3] = board[newY][newX];
                        tempBoard[0][4] = "_";
                        isCastleMove = true;
                        return doesMoveCheck();
                    }
                }
            } else if (king.checkValid(originalX, originalY, newX, newY, currentColour, board)) {
                tempBoard[newY][newX] = board[originalY][originalX];
                tempBoard[originalY][originalX] = "_";
                return doesMoveCheck();
            }
        } else {
            return false;
        }
        return false;
    }
    public void takePiece(int newY, int newX) {
        // If a piece is being taken then it will set the piece being taken to invisible, so it appears as if it has been taken on the GUI
        if (newY < 0 || newY > 7 || newX < 0 || newX > 7) {
            return;
        }
        if (currentColour == 'W') {
            System.out.println("White capturing");
            switch (board[newY][newX]) {
                case "p1" -> {
                    blackPawn1.setVisible(false);
                    capturedBlackPawn1.setVisible(true);
                } case "p2" -> {
                    blackPawn2.setVisible(false);
                    capturedBlackPawn2.setVisible(true);
                } case "p3" -> {
                    blackPawn3.setVisible(false);
                    capturedBlackPawn3.setVisible(true);
                } case "p4" -> {
                    blackPawn4.setVisible(false);
                    capturedBlackPawn4.setVisible(true);
                } case "p5" -> {
                    blackPawn5.setVisible(false);
                    capturedBlackPawn5.setVisible(true);
                } case "p6" -> {
                    blackPawn6.setVisible(false);
                    capturedBlackPawn6.setVisible(true);
                } case "p7" -> {
                    blackPawn7.setVisible(false);
                    capturedBlackPawn7.setVisible(true);
                } case "p8" -> {
                    blackPawn8.setVisible(false);
                    capturedBlackPawn8.setVisible(true);
                } case "r1" -> {
                    blackRook1.setVisible(false);
                    capturedBlackRook1.setVisible(true);
                } case "r2" -> {
                    blackRook2.setVisible(false);
                    capturedBlackRook2.setVisible(true);
                } case "h1" -> {
                    blackKnight1.setVisible(false);
                    capturedBlackKnight1.setVisible(true);
                } case "h2" -> {
                    blackKnight2.setVisible(false);
                    capturedBlackKnight2.setVisible(true);
                } case "b1" -> {
                    blackBishop1.setVisible(false);
                    capturedBlackBishop1.setVisible(true);
                } case "b2" -> {
                    blackBishop2.setVisible(false);
                    capturedBlackBishop2.setVisible(true);
                } case "q" -> {
                    blackQueen.setVisible(false);
                    capturedBlackQueen.setVisible(true);
                } case "q1" -> blackQueen1.setVisible(false);
                case "q2" -> blackQueen2.setVisible(false);
                case "q3" -> blackQueen3.setVisible(false);
                case "q4" -> blackQueen4.setVisible(false);
                case "q5" -> blackQueen5.setVisible(false);
                case "q6" -> blackQueen6.setVisible(false);
                case "q7" -> blackQueen7.setVisible(false);
                case "q8" -> blackQueen8.setVisible(false);
            }
        } else if (currentColour == 'B') {
            System.out.println("Black capturing");
            switch (board[newY][newX]) {
                case "P1" -> {
                    whitePawn1.setVisible(false);
                    capturedWhitePawn1.setVisible(true);
                } case "P2" -> {
                    whitePawn2.setVisible(false);
                    capturedWhitePawn2.setVisible(true);
                } case "P3" -> {
                    whitePawn3.setVisible(false);
                    capturedWhitePawn3.setVisible(true);
                } case "P4" -> {
                    whitePawn4.setVisible(false);
                    capturedWhitePawn4.setVisible(true);
                } case "P5" -> {
                    whitePawn5.setVisible(false);
                    capturedWhitePawn5.setVisible(true);
                } case "P6" -> {
                    whitePawn6.setVisible(false);
                    capturedWhitePawn6.setVisible(true);
                } case "P7" -> {
                    whitePawn7.setVisible(false);
                    capturedWhitePawn7.setVisible(true);
                } case "P8" -> {
                    whitePawn8.setVisible(false);
                    capturedWhitePawn8.setVisible(true);
                } case "R1" -> {
                    whiteRook1.setVisible(false);
                    capturedWhiteRook1.setVisible(true);
                } case "R2" -> {
                    whiteRook2.setVisible(false);
                    capturedWhiteRook2.setVisible(true);
                } case "H1" -> {
                    whiteKnight1.setVisible(false);
                    capturedWhiteKnight1.setVisible(true);
                } case "H2" -> {
                    whiteKnight2.setVisible(false);
                    capturedWhiteKnight2.setVisible(true);
                } case "B1" -> {
                    whiteBishop1.setVisible(false);
                    capturedWhiteBishop1.setVisible(true);
                } case "B2" -> {
                    whiteBishop2.setVisible(false);
                    capturedWhiteBishop2.setVisible(true);
                } case "Q" -> {
                    whiteQueen.setVisible(false);
                    capturedWhiteQueen.setVisible(true);
                } case "Q1" -> whiteQueen1.setVisible(false);
                case "Q2" -> whiteQueen2.setVisible(false);
                case "Q3" -> whiteQueen3.setVisible(false);
                case "Q4" -> whiteQueen4.setVisible(false);
                case "Q5" -> whiteQueen5.setVisible(false);
                case "Q6" -> whiteQueen6.setVisible(false);
                case "Q7" -> whiteQueen7.setVisible(false);
                case "Q8" -> whiteQueen8.setVisible(false);
            }
        }
    }
}