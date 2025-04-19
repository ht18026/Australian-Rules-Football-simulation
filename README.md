# Australian-Rules-Football-simulation
## Specification
### Overview and background
For this assignment you will write a simulation of an Australian Rules Football game. Australian Rules Football (AFL) is a ball game played between two teams of 18 players on an oval-shaped field.  At each end of the field are two tall central goalposts and two shorter outer posts. The aim of the game is for a player to kick the ball between the opponent’s posts. If the ball is kicked between the large goalposts then a goal is scored: if it is kicked between a central and an outer post then a behind is scored. A goal is worth 6 points and a behind is worth 1 point. 

Players take positions in different sections of the field. The players near the opponent’s goalposts are called forwards. The players in the middle of the field are called midfielders. The players who defend the goals are called defenders. Figure 1 below shows a field with the different positions and the goalposts for the team who are defending the goalposts on the lower right of the diagram. This team will try to kick goals through the goalposts on the opposite side of the ground. The opposition team will defend these goalposts and their forward and defender positions will be reversed.

An AFL football game is played in four quarters of 20 minutes. The game begins in the centre circle in the middle of the ground. The players in each team try to move the ball towards their opponent's goals by kicking the ball to a teammate closer to the goals. When a player within goal kicking distance (usually a forward player) gains possession of the ball, they can attempt to kick a goal. If a goal is kicked then the team scores 6 points and the ball is returned to the centre of the ground. If a behind is kicked then the team scores one point and one of the defenders in the other team takes possession of the ball and the game continues.  

## Simulation design

The Australian Rules Football game you will write will simulate one game of football between two teams, each with 18 players.  Each team will have five forwards, eight midfielders and five defenders. In addition, each team has four reserve players who can replace players who become injured.  A number of players in each team will be star players who are more skilled at the game..

The simulated game will be played in four 20 minute quarters, with each quarter involving a series of 80 random events where the player with the ball will try to kick the ball to another teammate or through the goalposts to gain points. At times the kick will result in a turnover where a player from the opposite team gains possession of the ball. 

After each quarter the current score will be displayed. After the 4th quarter the game result is displayed. The team with the most points wins the game. Note: A goal is worth 6 points and a behind is worth 1 point.

### Program design

The core components of your program will be Team, Player and AFLGame classes but a good design will incorporate more classes. The program design will be discussed in your classes.

### Program start up

The details of the teams are read from two files: teamA.txt and teamB.txt. These files will have the same format. The first line on the file is the team name. This is followed by 22 lines, with each line giving the details for one player as three comma separated values: 

player name (String)

field position (String) - forward, midfielder, defender, reserve 

total goals kicked for the season (int)

From this data two Team objects are created. Each Team object will have team name and a collection of Player objects stored as an ArrayList. Each player object will have a player name, field position, seasons goals as fields. You will likely decide to have other fields in these classes.

The user running the simulation will be invited to nominate the number of star players in each team. Players in each team will be randomly picked to be a star player.  Each team will have from 0 (zero) to 8 star players. 

### Game Play

The game begins with the first quarter and play starts in the centre circle in the middle of the field. The teams have an equal chance of having first possession of the ball. The first player will be a midfielder, randomly chosen from the midfielders in the team.

The quarter consists of a series of events where the player in possession of the ball player kicks the ball. The outcome of the event is determined by the player's position and star status. The probability of each outcome for a normal (non-star) player is shown in Table 1 and for a star player is shown in Table 2.  For example, an ordinary midfielder has a 5% chance of kicking a goal, a 10% chance of kicking a behind, 30% chance of passing to a forward in the same team, 30% pass to another midfield in the same team, 25% turnover to a midfield in the other team.


**Actions following each outcome:**

If a goal or behind is kicked then the scores for the player and team are updated and the result is displayed. 

After a goal the ball is returned to the centre circle. The teams have an equal chance of having possession of the ball and player who takes possession will be a midfielder, randomly chosen from the midfielders in the team.

After a behind, a defender of the other team takes the ball, and play continues. The defender who takes the ball is randomly chosen from the team's defenders.

If the ball is passed to another teammate in a specified position, a player in that position is randomly chosen from the team.

If there is a turnover and the ball passes to an opposition player in a specified position, then a player from the team is randomly chosen.

At each event there is a 2% chance of a player becoming injured and needing to leave the field.  When this happens a reserve player is brought onto the field and takes the position of the injured player, and the game continues. If more than four players in a team become injured then the team must forfeit the match. In this case, the match ends and the other team is the winner.  

After 80 events the quarter ends and the current scores for each team are displayed. If one, two or three quarters have been played then the next quarter is played.

Occasionally a player will violate the rules of the game and is reported by the umpire.  Each player has a 1% chance of being reported during a game. If a player is reported they continue playing 

### End of the game

If four quarters have been played then the game ends and the final result is determined. The winner is the team with the highest number of points. If both teams have the same number of points then the game is a draw. **The following final game statistics are displayed**:

Game result 

Total goals, behinds, and points for each team

Name of the player in each team who has the greatest number of kicks. 

Name of the highest goal scorer in each team

Individual player statistics for each team:

player name and indication of whether the player is injured and/or reported

kicks

goals 

behinds

List of injured players

List of reported players

The player information is written to two separate files - teamAUpdated.txt and teamBUpdated.txt. 

### Program and Class Design
The design of the program will be discussed in your Applied Class in Week 9. It is important that you attend this class.

Important Notes
Your program must demonstrate your understanding of the object-oriented concepts and general programming constructs presented in FIT9131. Consider carefully your choice of classes, how they interact and the fields and methods of each class. You must use appropriate data structures to store the various objects in the program. You must make use of both Arrays and ArrayLists in your program. Make sure that you discuss your design with your tutor. You must document any additional assumptions you made.

You will be required to justify your design and the choice of any data structures used at the interview. 

Validation of values for fields and local variables should be implemented where appropriate. You should not allow an object of a class to be set to an invalid state (i.e., put some simple validations in your mutator methods).

Your program should handle incorrect or invalid input and present the user with relevant error messages. No invalid input should crash the program. 

Exception handling should be used where appropriate.
