Chess Game

This program is a regular rules chess game that operates within a windowed GUI through Java and JavaFX
The game operates on a coordinates based system where you specify the start point and destination for each move instead of dragging the pieces
For the single player mode the program generates possible next moves and then evaluates the board at each of those positions using a set of 
in built heuristics. It then uses the minimax algorithm to traverse the tree containing the possible positions and decide which next move is optimal

To play the game first run the program and the window with the game should open
You will then be prompted to either create an account of log in to an existing account, do whichever is suitable
Once logged in there is a drop down menu where you can choose for the game to be either one or two player

Two player:
For a two player game for each move you will need to enter the source and destination of the piece you want to move and then press the move 
button to complete your turn

One player:
For single player mode you can choose the difficulty to play: easy, medium, or hard
The different difficulties represent how far ahead the computer will look when generating possible positions
Once decided on a difficulty you can choose to be white or black
If you choose to be black press the "move" button so that the computer starts the game
Otherwise make the first move and then once you have moved the computer will react and makes its move instaneously
