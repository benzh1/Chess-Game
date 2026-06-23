# Chess

A fully featured chess game built in Java with a JavaFX GUI, supporting both two-player and AI-driven single-player modes.

## Overview

This project implements a complete chess application with a windowed interface, user account management, and an AI opponent. Moves are entered via a coordinate-based system rather than drag-and-drop, keeping the focus on the logic layer. The single-player AI uses the **Minimax algorithm** with a suite of board evaluation heuristics to determine its next move, with configurable search depth to control difficulty.

## Features

- **JavaFX GUI** — clean windowed interface with a rendered chessboard updated after each move
- **Account system** — players can create an account or log in to an existing one before playing
- **Two-player mode** — pass-and-play on the same machine, with full move validation enforcing standard chess rules
- **Single-player AI** — computer opponent powered by Minimax with heuristic board evaluation
- **Three difficulty levels** — Easy, Medium, and Hard; each increases the AI's search depth, allowing it to look further ahead and find stronger moves
- **Choice of colour** — in single-player mode, play as White or Black; the AI moves instantly after each player turn

## How to Play

### Setup

1. Run the program — the game window will open automatically
2. Create an account or log in to an existing one when prompted
3. Select **One Player** or **Two Player** from the dropdown menu

### Two Player

Each turn, enter the source square and destination square of the piece you want to move, then press **Move** to confirm. The board updates and control passes to the other player.

### One Player

1. Select a difficulty: **Easy**, **Medium**, or **Hard**
2. Choose to play as **White** or **Black**
3. If you chose Black, press **Move** to let the AI open the game; otherwise make your move first
4. After each of your moves the AI responds instantly

## AI Design

The computer opponent works in two stages each turn:

1. **Move generation** — all legal moves from the current position are enumerated
2. **Board evaluation** — each resulting position is scored using a set of heuristics (material balance, piece activity, positional factors)

The Minimax algorithm then traverses the resulting game tree to the configured search depth, assuming optimal play from both sides, and selects the move that leads to the best outcome for the AI.

| Difficulty | Search Depth |
|------------|-------------|
| Easy       | Shallow      |
| Medium     | Moderate     |
| Hard       | Deep         |

## Getting Started

**Prerequisites:** Java 11+ and JavaFX 11+

```bash
# Clone the repository
git clone https://github.com/benzh1/chess.git
cd chess

# Compile (adjust module path to your JavaFX SDK location)
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml \
  -d out/ src/*.java

# Run
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml \
  -cp out/ Main
```

> If you are using an IDE such as IntelliJ IDEA or Eclipse, add the JavaFX SDK as a library and configure the VM options above in your run configuration.

## Project Structure

```
chess/
├── src/
│   ├── Main.java               # Entry point
│   ├── Board.java              # Board state and move validation
│   ├── Piece.java              # Piece types and movement rules
│   ├── AI.java                 # Minimax algorithm and heuristics
│   ├── AccountManager.java     # User account creation and login
│   └── GameController.java     # GUI event handling and game flow
└── README.md
```

## Technologies

- **Java** — core game logic, move generation, and AI
- **JavaFX** — windowed GUI and board rendering
- **Minimax algorithm** — adversarial search for AI decision-making
