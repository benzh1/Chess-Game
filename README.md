# Chess

A fully functional chess game built in Java with a JavaFX GUI, supporting both two-player and AI-driven single-player modes.

## Overview

This project implements a complete chess application with a windowed interface, user account management, and an AI opponent. Moves are entered via a coordinate-based system rather than drag-and-drop, keeping the focus on the logic of the game. The single-player AI uses the **Minimax algorithm** with a set of self-designed of board evaluation heuristics to determine its next move, with different difficulty levels that alter the search depth.

## Features

- **JavaFX GUI** — windowed interface with a rendered chessboard that updates after each move
- **Account system** — players can create an account or log in to an existing one before playing
- **Two-player mode** — pass-and-play on the same machine, with move validation enforcing standard chess rules
- **Single-player AI** — computer opponent powered by Minimax with heuristic board evaluation
- **Three difficulty levels** — Easy, Medium, and Hard; each difficulty increases the AI's search depth, allowing it to look further ahead and find stronger moves
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
4. After each of your moves the AI will respond instantly

## AI Design

The computer opponent works in two stages each turn:

1. **Move generation** — all legal moves from the current position are enumerated up to the search depth corresponding to the selected difficulty
2. **Board evaluation** — each resulting position is scored using a set of heuristics (material balance, piece activity, positional factors)

The Minimax algorithm then traverses the resulting game tree, assuming optimal play from both sides, and selects the move that leads to the best outcome for the AI.

| Difficulty | Search Depth |
|------------|-------------|
| Easy       | Shallow      |
| Medium     | Moderate     |
| Hard       | Deep         |


## Technologies

- **Java** — core game logic, move generation, and AI
- **JavaFX** — windowed GUI and board rendering
- **Minimax algorithm** — adversarial search for AI decision-making
