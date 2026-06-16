package com.example.interactivechessguinea;

public class NodeTree {
    private static ChessNode currentNode;
    private static ChessNode rootNode;
    private final int depthLimit = 1;
    private final int rootDepth;
    private static int evaluation;
    private final String[][] bestNextPosition = new String[8][8];
    private final String[][] basePosition = new String[8][8];
    private int originalX = 0;
    private int originalY = 0;
    private int newX = 0;
    private int newY = 0;
    private boolean hasCastled = false;
    private final King king = new King();
    private final GenerateMoves generateMoves = new GenerateMoves();
    public NodeTree(String[][] board, char colour, int difficulty) {
        if (difficulty > 1 && difficulty < 5) {
            rootDepth = difficulty;
        } else {
            rootDepth = 4;
        }
        ChessNode node;
        if (colour == 'W')
            node = new ChessNode(board, rootDepth, 'B');
        else
            node = new ChessNode(board, rootDepth, 'W');
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                basePosition[j][i] = String.valueOf(board[j][i].charAt(0));
            }
        }
        node.setParentNode(node);
        rootNode = node;
        currentNode = node;
        setCurrentNodeChildren();
    }
    public void setCurrentNodeChildren() {
        if (currentNode.getDepth() != depthLimit) {
            do {
                generateMoves.makeMove(currentNode.getColour(), currentNode.getBoard());
                if (king.isCheckmate(currentNode.getBoard(), currentNode.getColour())) {
                    break;
                }
            } while (currentNode.getChildrenSize() == 0);
            if (king.isCheckmate(currentNode.getBoard(), currentNode.getColour())) {
                backTracking();
            } else {
                currentNode = currentNode.getChildren();
                setCurrentNodeChildren();
            }
        } else {
            backTracking();
        }
    }
    public void backTracking() {
        if (king.isCheckmate(currentNode.getBoard(), currentNode.getColour())) {
            for (int i = 0; i < 2; i++) {
                if (currentNode.getParentNode() != null) {
                    currentNode = currentNode.getParentNode();
                }
            }
        } else {
            currentNode = currentNode.getParentNode();
            currentNode = currentNode.getParentNode();
        }
        while (currentNode.getChildIndex() >= currentNode.getChildrenSize() || currentNode.getChildrenSize() == 0) {
            if (currentNode.getParentNode() != null) {
                currentNode = currentNode.getParentNode();
            }
            if (currentNode.getDepth() == rootDepth && currentNode.getChildIndex() >= currentNode.getChildrenSize()) {
                System.out.println("Min-maxing");
                currentNode = rootNode;
                minimax(currentNode, currentNode.getDepth(), currentNode.getColour());
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(" | " + bestNextPosition[i][j] + " | ");
                    }
                    System.out.println();
                }
                if (bestNextPosition[0][0] == null) {
                    System.out.println("CHECKMATE");
                    System.exit(0);
                }
                ChessNode bestNode = new ChessNode(bestNextPosition, currentNode.getChild(0).getColour());
                System.out.println(evaluate(bestNode));
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!basePosition[j][i].equals(bestNextPosition[j][i])) {
                            if (rootNode.getColour() == 'W') {
                                if (bestNextPosition[7][0].equals("_") && bestNextPosition[7][1].equals("_") && bestNextPosition[7][2].equals("K") && bestNextPosition[7][3].equals("R") && bestNextPosition[7][4].equals("_")) {
                                    if (basePosition[7][0].equals("R") && basePosition[7][1].equals("_") && basePosition[7][2].equals("_") && basePosition[7][3].equals("_") && basePosition[7][4].equals("K")) {
                                        originalX = 4;
                                        originalY = 7;
                                        newX = 0;
                                        newY = 7;
                                        hasCastled = true;
                                    }
                                } else if (bestNextPosition[7][7].equals("_") && bestNextPosition[7][6].equals("K") && bestNextPosition[7][5].equals("R") && bestNextPosition[7][4].equals("_")) {
                                    if (basePosition[7][7].equals("R") && basePosition[7][6].equals("_") && basePosition[7][5].equals("_") && basePosition[7][4].equals("K")) {
                                        originalX = 4;
                                        originalY = 7;
                                        newX = 7;
                                        newY = 7;
                                        hasCastled = true;
                                    }
                                }
                            } else if (rootNode.getColour() == 'B') {
                                if (bestNextPosition[0][0].equals("_") && bestNextPosition[0][1].equals("_") && bestNextPosition[0][2].equals("k") && bestNextPosition[0][3].equals("r") && bestNextPosition[0][4].equals("_")) {
                                    if (basePosition[0][0].equals("r") && basePosition[0][1].equals("_") && basePosition[0][2].equals("_") && basePosition[0][3].equals("_") && basePosition[0][4].equals("k")) {
                                        originalX = 4;
                                        originalY = 0;
                                        newX = 0;
                                        newY = 0;
                                        hasCastled = true;
                                    }
                                } else if (bestNextPosition[0][7].equals("_") && bestNextPosition[0][6].equals("k") && bestNextPosition[0][5].equals("r") && bestNextPosition[0][4].equals("_")) {
                                    if (basePosition[0][7].equals("r") && basePosition[0][6].equals("_") && basePosition[0][5].equals("_") && basePosition[0][4].equals("k")) {
                                        originalX = 4;
                                        originalY = 0;
                                        newX = 7;
                                        newY = 0;
                                        hasCastled = true;
                                    }
                                }
                            }
                            if (!hasCastled) {
                                if (bestNextPosition[j][i].equals("_")) {
                                    originalX = i;
                                    originalY = j;
                                } else {
                                    newX = i;
                                    newY = j;
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        currentNode = currentNode.getChildren();
        setCurrentNodeChildren();
    }
    public int minimax(ChessNode position, int depth, char maximisingPlayer) {
        int maxEval;
        int minEval;
        if (depth == depthLimit || position.getChildrenNodeList().isEmpty()) {
            return evaluate(position);
        }
        if (maximisingPlayer == 'W') {
            maxEval = -100000000;
            for (int i = 0; i < position.getChildrenSize(); i++) {
                evaluation = minimax(position.getChild(i), position.getChild(i).getDepth(), position.getChild(i).getColour());
                if (evaluation > maxEval) {
                    maxEval = evaluation;
                    if (depth == rootDepth) {
                        for (int a = 0; a < 8; a++) {
                            for (int j = 0; j < 8; j++) {
                                bestNextPosition[a][j] = position.getChild(i).getBoard()[a][j];
                            }
                        }
                    }
                }
            }
            return maxEval;
        } else {
            minEval = 100000000;
            for (int i = 0; i < position.getChildrenSize(); i++) {
                evaluation = minimax(position.getChild(i), position.getChild(i).getDepth(), position.getChild(i).getColour());
                if (evaluation < minEval) {
                    minEval = evaluation;
                    if (depth == rootDepth) {
                        for (int a = 0; a < 8; a++) {
                            for (int j = 0; j < 8; j++) {
                                bestNextPosition[a][j] = position.getChild(i).getBoard()[a][j];
                            }
                        }
                    }
                }
            }
            return minEval;
        }
    }
    public int evaluate(ChessNode node) {
        String[][] board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = node.getBoard()[i][j];
            }
        }
        int evaluation = 0;
        int piecesLeft = 64;
        boolean rankEmpty = true;
        boolean fileEmpty = true;
        int backRankRookEval = 5;
        boolean canRookSee = false;
        int rookCounter;
        int rookSeeEval = 5;
        int doubledPawnEval = -2;
        int castleEval = 4;
        int pawnEval = 75;
        int length1 = 10;
        int length2 = 15;
        int length3 = 20;
        int passedPawnEval = 4;
        int pawnCounter = 0;
        boolean pawnMajority = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals("_")) {
                    piecesLeft--;
                }
                if (board[i][j].equalsIgnoreCase("p")) {
                    pawnCounter++;
                }
            }
        }
        if (piecesLeft <= 10) {
            pawnEval = 100;
            passedPawnEval = 15;
        }
        if (piecesLeft <= 20 && pawnCounter > piecesLeft / 2) {
            pawnMajority = true;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (board[i][j].charAt(0)) {
                    case 'p' -> {
                        boolean isPassed = true;
                        evaluation -= pawnEval;
                        if (piecesLeft <= 10 || pawnMajority) {
                            if (i == 0) {
                                evaluation -= 1;
                            } else if (i == 1) {
                                evaluation -= 4;
                            } else if (i == 2) {
                                evaluation -= 6;
                            } else if (i == 3) {
                                evaluation -= 8;
                            } else if (i == 4) {
                                evaluation -= 12;
                            } else if (i == 5) {
                                evaluation -= 16;
                            } else if (i == 6) {
                                evaluation -= 20;
                            } else {
                                evaluation -= 24;
                            }
                        } else {
                            evaluation -= piecePosition(board, i, j);
                        }
                        if (i != 0) {
                            if (board[i - 1][j].equals("p")) {
                                evaluation -= doubledPawnEval;
                            }
                        }
                        if (i != 7) {
                            if (board[i + 1][j].equals("p")) {
                                evaluation -= doubledPawnEval;
                            }
                        }
                        for (int a = i + 1; a < 8; a++) {
                            if (board[a][j].equals("P")) {
                                isPassed = false;
                                break;
                            }
                        }
                        if (isPassed) {
                            evaluation -= passedPawnEval;
                        }
                    }
                    case 'r' -> {
                        rookCounter = 0;
                        evaluation -= 375;
                        evaluation -= piecePosition(board, i, j);
                        if (i == 0) {
                            evaluation -= backRankRookEval;
                        }
                        for (int a = 0; a < 8; a++) {
                            if (board[a][j].equals("r")) {
                                if (rookCounter == 0) {
                                    canRookSee = true;
                                }
                                rookCounter++;
                            }
                            if (!board[a][j].equals("r") && !board[a][j].equals("_") && canRookSee) {
                                canRookSee = false;
                            }
                            if (rookCounter == 2 && canRookSee) {
                                evaluation -= rookSeeEval;
                                break;
                            }
                        }
                        rookCounter = 0;
                        for (int b = 0; b < 8; b++) {
                            if (board[i][b].equals("r")) {
                                if (rookCounter == 0) {
                                    canRookSee = true;
                                }
                                rookCounter++;
                            }
                            if (!board[i][b].equals("r") && !board[i][b].equals("_") && canRookSee) {
                                canRookSee = false;
                            }
                            if (rookCounter == 2 && canRookSee) {
                                evaluation -= rookSeeEval;
                                break;
                            }
                        }

                        for (int a = 0; a < 8; a++) {
                            if (!board[i][a].equals("_") && a != j) {
                                rankEmpty = false;
                            }
                            if (!board[a][j].equals("_") && a != i) {
                                fileEmpty = false;
                            }
                        }

                        if (rankEmpty) {
                            if (i == 0 || i == 7) {
                                evaluation -= length1;
                            } else if (i == 1 || i == 6) {
                                evaluation -= length2;
                            } else {
                                evaluation -= length3;
                            }
                        }
                        if (fileEmpty) {
                            if (j == 0 || j == 7) {
                                evaluation -= length1;
                            } else if (j == 1 || j == 6) {
                                evaluation -= length2;
                            } else {
                                evaluation -= length3;
                            }
                        }
                        rankEmpty = true;
                        fileEmpty = true;
                        if ((board[0][2].equals("k") && board[0][3].equals("r") || (board[0][5].equals("r") && board[0][6].equals("k")))) {
                            evaluation -= castleEval;
                        }
                    }
                    case 'b', 'h' -> {
                        evaluation -= 225;
                        evaluation -= piecePosition(board, i, j);
                        if (board[i][j].charAt(0) == 'h') {
                            if (knightMoveCounter(i, j) > 4) {
                                evaluation -= 1;
                            }
                        }
                    }
                    case 'q' -> {
                        evaluation -= 675;
                        evaluation -= piecePosition(board, i, j);
                        for (int a = 0; a < 8; a++) {
                            if (!board[i][a].equals("_") && a != j) {
                                rankEmpty = false;
                            }
                            if (!board[a][j].equals("_") && a != i) {
                                fileEmpty = false;
                            }
                        }

                        if (rankEmpty) {
                            if (i == 0 || i == 7) {
                                evaluation -= length1;
                            } else if (i == 1 || i == 6) {
                                evaluation -= length2;
                            } else {
                                evaluation -= length3;
                            }
                        }
                        if (fileEmpty) {
                            if (j == 0 || j == 7) {
                                evaluation -= length1;
                            } else if (j == 1 || j == 6) {
                                evaluation -= length2;
                            } else {
                                evaluation -= length3;
                            }
                        }
                        rankEmpty = true;
                        fileEmpty = true;
                    }
                    case 'k' -> {
                        if (piecesLeft <= 10) {
                            int surroundingPieces = getSurroundingPiecesA(board, i, j);
                            if (surroundingPieces >= 2) {
                                evaluation -= 2;
                            }
                        }
                    }
                    case 'P' -> {
                        boolean isPassed = true;
                        evaluation += pawnEval;
                        if (piecesLeft <= 10 || pawnMajority) {
                            if (i == 7) {
                                evaluation += 1;
                            } else if (i == 6) {
                                evaluation += 4;
                            } else if (i == 5) {
                                evaluation += 6;
                            } else if (i == 4) {
                                evaluation += 8;
                            } else if (i == 3) {
                                evaluation += 12;
                            } else if (i == 2) {
                                evaluation += 16;
                            } else if (i == 1) {
                                evaluation += 20;
                            } else {
                                evaluation += 24;
                            }
                        } else {
                            evaluation += piecePosition(board, i, j);
                        }
                        if (i != 0) {
                            if (board[i - 1][j].equals("P")) {
                                evaluation += doubledPawnEval;
                            }
                        }
                        if (i != 7) {
                            if (board[i + 1][j].equals("P")) {
                                evaluation += doubledPawnEval;
                            }
                        }
                        for (int a = i + 1; a < 8; a++) {
                            if (board[a][j].equals("p")) {
                                isPassed = false;
                                break;
                            }
                        }
                        if (isPassed) {
                            evaluation += passedPawnEval;
                        }
                    }
                    case 'R' -> {
                        rookCounter = 0;
                        evaluation += 375;
                        evaluation += piecePosition(board, i, j);
                        if (i == 7) {
                            evaluation += backRankRookEval;
                        }
                        for (int a = 0; a < 8; a++) {
                            if (board[a][j].equals("R")) {
                                if (rookCounter == 0) {
                                    canRookSee = true;
                                }
                                rookCounter++;
                            }
                            if (!board[a][j].equals("R") && !board[a][j].equals("_") && canRookSee) {
                                canRookSee = false;
                            }
                            if (rookCounter == 2 && canRookSee) {
                                evaluation += rookSeeEval;
                                break;
                            }
                        }
                        rookCounter = 0;
                        for (int b = 0; b < 8; b++) {
                            if (board[i][b].equals("R")) {
                                if (rookCounter == 0) {
                                    canRookSee = true;
                                }
                                rookCounter++;
                            }
                            if (!board[i][b].equals("R") && !board[i][b].equals("_") && canRookSee) {
                                canRookSee = false;
                            }
                            if (rookCounter == 2 && canRookSee) {
                                evaluation += rookSeeEval;
                                break;
                            }
                        }
                        for (int a = 0; a < 8; a++) {
                            if (!board[i][a].equals("_") && a != j) {
                                rankEmpty = false;
                            }
                            if (!board[a][j].equals("_") && a != i) {
                                fileEmpty = false;
                            }
                        }
                        if (rankEmpty) {
                            if (i == 0 || i == 7) {
                                evaluation += length1;
                            } else if (i == 1 || i == 6) {
                                evaluation += length2;
                            } else {
                                evaluation += length3;
                            }
                        }
                        if (fileEmpty) {
                            if (j == 0 || j == 7) {
                                evaluation += length1;
                            } else if (j == 1 || j == 6) {
                                evaluation += length2;
                            } else {
                                evaluation += length3;
                            }
                        }
                        rankEmpty = true;
                        fileEmpty = true;
                        if ((board[7][2].equals("K") && board[0][3].equals("R") || (board[0][5].equals("R") && board[0][6].equals("K")))) {
                            evaluation += castleEval;
                        }
                    }
                    case 'B', 'H' -> {
                        evaluation += 225;
                        evaluation += piecePosition(board, i, j);
                        if (board[i][j].charAt(0) == 'h') {
                            if (knightMoveCounter(i, j) > 4) {
                                evaluation += 1;
                            }
                        }
                    }
                    case 'Q' -> {
                        evaluation += 675;
                        evaluation += piecePosition(board, i, j);
                        for (int a = 0; a < 8; a++) {
                            if (!board[i][a].equals("_") && a != j) {
                                rankEmpty = false;
                            }
                            if (!board[a][j].equals("_") && a != i) {
                                fileEmpty = false;
                            }
                        }
                        if (rankEmpty) {
                            if (i == 0 || i == 7) {
                                evaluation += length1;
                            } else if (i == 1 || i == 6) {
                                evaluation += length2;
                            } else {
                                evaluation += length3;
                            }
                        }
                        if (fileEmpty) {
                            if (j == 0 || j == 7) {
                                evaluation += length1;
                            } else if (j == 1 || j == 6) {
                                evaluation += length2;
                            } else {
                                evaluation += length3;
                            }
                        }
                        rankEmpty = true;
                        fileEmpty = true;
                    }
                    case 'K' -> {
                        if (piecesLeft <= 10) {
                            int surroundingPieces = getSurroundingPiecesB(board, i, j);
                            if (surroundingPieces >= 2) {
                                evaluation += 2;
                            }
                        }
                    }
                }
            }
        }
        if (king.isChecked(board, 'B')) {
            if (king.isCheckmate(board, 'B')) {
                evaluation += 1000000;
            } else {
                evaluation += 3;
            }
        } else if (king.isChecked(board, 'W')) {
            if (king.isCheckmate(board, 'W')) {
                evaluation -= 1000000;
            } else {
                evaluation -= 3;
            }
        } else {
            GenerateMoves generateMoves1 = new GenerateMoves(true);
            generateMoves1.makeMove('W', board);
            if (generateMoves1.getMoveCounter() == 0) {
                evaluation = 0;
            }
            generateMoves1.makeMove('B', board);
            if (generateMoves1.getMoveCounter() == 0) {
                evaluation = 0;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (board[0][i].equals("K")) {
                evaluation += 1;
            }
            if (board[0][i].equals("k")) {
                evaluation -= 1;
            }
        }
        return evaluation;
    }
    private static int piecePosition(String[][] board, int i, int j) {
        if (i < 0 || i > 7 || j < 0 || j > 7) {
            return  0;
        }
        int tempEval = 0;
        int eval1 = 10;
        int eval2 = 11;
        int eval3 = 12;
        int eval4 = 14;
        int eval5 = 16;
        int eval6 = 20;
        int eval7 = 28;
        int piecesLeft = 64;
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                if (board[a][b].equals("_")) {
                    piecesLeft--;
                }
            }
        }
        if (piecesLeft <= 16) {
            eval1 = 2;
            eval2 = 3;
            eval3 = 4;
            eval4 = 5;
            eval5 = 6;
            eval6 = 10;
            eval7 = 10;
        }
        if ((j == 0 || j == 7) && (i == 0 || i == 7)) {
            tempEval += eval1;
        } else if (((j == 0 || j == 7) && (i == 1 || i == 6)) ^ ((j == 1 || j == 6) && (i == 0 || i == 7))) {
            tempEval += eval2;
        } else if ((i == 0 || i == 7) ^ (j == 0 || j == 7)) {
            tempEval += eval3;
        } else if ((j == 1 || j == 6) && (i == 1 || i == 6)) {
            tempEval += eval4;
        } else if ((i == 1 || i == 6) ^ (j == 1 || j == 6)) {
            tempEval += eval5;
        } else if ((j == 2 || j == 5) ^ ((i == 2 || i == 5) && j > 2 && j < 5)) {
            tempEval += eval6;
        } else {
            tempEval += eval7;
        }
        return tempEval;
    }
    private static int getSurroundingPiecesA(String[][] board, int i, int j) {
        if (i < 0 || i > 7 || j < 0 || j > 7) {
            return  0;
        }
        int surroundingPieces = 0;
        try {
            if (board[i + 1][j + 1].toLowerCase().equals(board[i + 1][j + 1])) {
                surroundingPieces++;
            }
            if (board[i + 1][j].toLowerCase().equals(board[i + 1][j])) {
                surroundingPieces++;
            }
            if (board[i + 1][j - 1].toLowerCase().equals(board[i + 1][j - 1])) {
                surroundingPieces++;
            }
            if (board[i][j + 1].toLowerCase().equals(board[i][j + 1])) {
                surroundingPieces++;
            }
            if (board[i][j - 1].toLowerCase().equals(board[i][j - 1])) {
                surroundingPieces++;
            }
            if (board[i - 1][j + 1].toLowerCase().equals(board[i - 1][j + 1])) {
                surroundingPieces++;
            }
            if (board[i - 1][j].toLowerCase().equals(board[i - 1][j])) {
                surroundingPieces++;
            }
            if (board[i - 1][j - 1].toLowerCase().equals(board[i - 1][j - 1])) {
                surroundingPieces++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        return surroundingPieces;
    }
    private static int getSurroundingPiecesB(String[][] board, int i, int j) {
        if (i < 0 || i > 7 || j < 0 || j > 7) {
            return  0;
        }
        int surroundingPieces = 0;
        try {
            if (board[i + 1][j + 1].toUpperCase().equals(board[i + 1][j + 1])) {
                surroundingPieces++;
            }
            if (board[i + 1][j].toUpperCase().equals(board[i + 1][j])) {
                surroundingPieces++;
            }
            if (board[i + 1][j - 1].toUpperCase().equals(board[i + 1][j - 1])) {
                surroundingPieces++;
            }
            if (board[i][j + 1].toUpperCase().equals(board[i][j + 1])) {
                surroundingPieces++;
            }
            if (board[i][j - 1].toUpperCase().equals(board[i][j - 1])) {
                surroundingPieces++;
            }
            if (board[i - 1][j + 1].toUpperCase().equals(board[i - 1][j + 1])) {
                surroundingPieces++;
            }
            if (board[i - 1][j].toUpperCase().equals(board[i - 1][j])) {
                surroundingPieces++;
            }
            if (board[i - 1][j - 1].toUpperCase().equals(board[i - 1][j - 1])) {
                surroundingPieces++;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        return surroundingPieces;
    }
    private static int knightMoveCounter(int i, int j) {
        if (i < 0 || i > 7 || j < 0 || j > 7) {
            return  0;
        }
        int count = 0;
        if ((i + 2 < 8) && (j + 1 < 8)) {
            count++;
        }
        if ((i + 2 < 8) && (j - 1 >= 0)) {
            count++;
        }
        if ((i + 1 < 8) && (j + 2 < 8)) {
            count++;
        }
        if ((i + 1 < 8) && (j - 2 >= 0)) {
            count++;
        }
        if ((i - 1 >= 0) && (j + 2 < 8)) {
            count++;
        }
        if ((i - 1 >= 0) && (j - 2 >= 0)) {
            count++;
        }
        if ((i - 2 >= 0) && (j + 1 < 8)) {
            count++;
        }
        if ((i - 2 >= 0) && (j - 1 >= 0)) {
            count++;
        }
        return  count;
    }
    public int getNewX() { return newX; }
    public int getNewY() { return newY; }
    public int getOriginalX() { return originalX; }
    public int getOriginalY() { return originalY; }
    public boolean isHasCastled() { return hasCastled; }
    public void setHasCastled(boolean hasCastled) { this.hasCastled = hasCastled; }
    public static int getEvaluation() { return evaluation; }
    public static ChessNode getCurrentNode() { return  currentNode; }
}