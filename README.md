# Snake-Game
A project in Java when i was in Third Semester 

Snake is a classic fun board game, which is played on a board built in levels, which you go up if you fall on a ladder base and you go down if you fall into a snake mouth. The game is played with 2-4 players and the goal is to reach the last tile of the board, avoiding obstacles and collecting points.

![image](https://user-images.githubusercontent.com/95449708/153505424-e1ee1ea4-bd5e-4c71-a0e9-ee70beab030e.png)

At the beginning of the game, all players start from 0 and try to reach the end. They roll the dice and whoever rolls the smallest number plays first, whoever has the second smallest number plays second and so on. The first player rolls the dice again and moves as many steps as he shows. If he reaches one of the tiles that shows him that the ladder starts, he goes up it. Each ladder can only be used once during the game. This means that when a player climbs a ladder, then the ladder breaks and can not be reused. However, if it reaches a point on the board where a snake is located, it descends to the corresponding tile where its tail ends.

However, apart from the snakes and the stairs, a tile may contain an apple, which depending on its color (red or black) adds or subtracts points from the score of the player falling on it. When a player eats an apple, it disappears from the board.

The players play in rounds according to the order set at the beginning of the game, ie when the first player finishes his turn, the second continues, then the third and so on.

HeuristicPlayer class

The HeuristicPlayer class will represent the player playing strategy. It inherits the Player class and has the following additional variables:
i. ArrayList <Integer []> path: information about each player's move during the game. More specifically, the structure will include information about the dice, the number of points offered by the movement, the number of steps, the number of apples he ate, the number of snakes he was bitten by and the number of ladders he used. player in his specific move. You can also save whatever else you may find useful.

The functions you need to implement are:

a. The constructors of the class.

b. Double evaluate function (int currentPos, int dice): This function evaluates the player's move when the dice roll is in the currentPos position. The function returns the evaluation of the move, according to the target function you have set.

c. Int getNextMove (int currentPos) function: This function is responsible for selecting the player's final move. The function should include the following:

	i. Creating a structure (Dynamic or non-dynamic table) that will store the player's possible moves as well as the evaluation of each of them.
	ii. Evaluate each possible move using the double evaluate function (int currentPos, int dice) and store the evaluation in the structure you have created.
	iii. Choosing the best move based on the evaluation you made.
	iv. Move the player.
	v. Update the path class variable.
	vi. Return of the new position of the player.
CAUTION!!! During the tests that you will do to select the best move, you should be careful to leave the board and the score of the players unchanged. These should only be changed after selecting the optimal move, ie during the actual movement of the player.

d. Void statistics () function: This function prints data on the player's moves in each round of the game (dice chosen and actions taken eg by the player in round 1 he set the dice equal to 4 and climbed a ladder "), as well as statistics for all his moves. Specifically, the following are required to be printed:

	• The number of tile visits containing a snake head.

	• The number of tile visits containing a ladder base.

	• The number of tile visits it contains with red apple.

	• The number of tile visits it contains with black apple.
	
Game Class

The Game class will represent the game and will have the following variables:

i. int round: the current round of the game.

The functions you need to implement are:
	
	a. The constructors of the class.
	
	b. All get and set functions for the class variable.
	
	c. Map <Integer, Integer> setTurns (ArrayList <Object> players) function: a function for determining the order in which players will play. At the beginning of the game the players roll the dice and whoever rolls the smallest number plays first, whoever has the second smallest number plays second and so on. The function returns a map with the players ids and the dice they rolled, sorted by dice.
	
d. Public static void main function (String [] args): game start function. In this function a sequence of actions should be performed:
	
	• Creation of a 10x20 dashboard, with 3 snakes, 3 ladders and 6 apples.
	• Set of 2 players, a player who plays with random moves and a HeuristicPlayer player.
	• Determining the order in which the players will play.
	• Players play alternately until they reach a certain round eg round 100 or someone finishes. Then the game is over. The winner     is the one who at the end of the game is ahead of his teammate on the board and at the same time has the highest score. Here       you can use the weights you set in the goal function for both parameters to determine the winner of the game. In case of a         tie, the winner is the one who finished first.
	• Print statistics for the HeuristicPlayer player.
	• Print the number of game rounds played by the players, the score of each player and the winner of the game.

CAUTION!!!
	• If you want to read the values ​​of the variables of an object of a class or set values ​​to the variables you must use the corresponding getters and setters.
