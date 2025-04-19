import java.util.Scanner;
/**
 * Provides various display methods for the Australian Rules Football simulation.
 * This class handles user prompts, game messages, and player actions.
 */
class Display{
    /**
     * Prints a welcome message for the game.
     */
    public static void printWelcomeMessage(){
        System.out.println("Welcome to the Australian Rules Football Simulation");
        System.out.println("===================================================");
    }
    /**
     * Asks the user for the number of star players in a given team.
     *
     * @param team the team for which to ask the star player count
     * @return the number of star players in the team
     */
    public static int askForStarPlayerCount(String team){
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        while(input<0||input>8){
            System.out.println("Enter number of star players in team "+team+" [0-8]");
            if (scanner.hasNextInt()){
                input = scanner.nextInt();
                if(input<0||input>8){
                    System.out.println("Error: value is not in range: 0 to 8");
                }
            }else{
                System.out.println("Error: value is not a number: "+scanner.next());
            }
        }
        return input;

    }
    /**
     * Prints the current quarter number.
     *
     * @param quarter the current quarter number
     */
    public static void printQuarter(int quarter){
        System.out.println("*** Quarter "+quarter+" ***");
    }
    /**
     * Prints the event number.
     *
     * @param eventNum the current event number
     */
    public static void printEventNum(int eventNum){
        System.out.println("#"+eventNum+":");
    }
     /**
     * Displays a message indicating that a player has gained possession of the ball.
     *
     * @param player the player who gained possession
     */
    public static void displayGotBall(Player player){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        System.out.println(String.format("%s (%s,Team %s) got the ball",name,position,team));
    }
    /**
     * Displays a message indicating that a player is injured.
     *
     * @param player the injured player
     */
    public static void displayInjured(Player player){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        System.out.println(String.format("%s (%s,Team %s) is injured",name,position,team));
    }
    /**
     * Displays a message indicating that a player has gained possession in the center circle.
     *
     * @param player the player who gained possession
     */
    public static void displayGotBallInMid(Player player){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        System.out.println(String.format("%s (%s,Team %s) got the ball in the centre circle",name,position,team));
    }
    /**
     * Prints a message indicating that a player has scored a goal.
     *
     * @param player the player who scored the goal
     */
    public static void printGoal(Player player){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        System.out.println(String.format("%s (%s,Team %s) got a goal, scored 6 points",name,position,team));
        
    }
    /**
     * Prints a message indicating that a player has scored a behind.
     *
     * @param player the player who scored the behind
     */
    public static void printBehind(Player player){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        System.out.println(String.format("%s (%s,Team %s) got a behind, scored 1 points",name,position,team));
        
    }
    /**
     * Prints a message indicating that a player has passed the ball to another player.
     *
     * @param player      the player who made the pass
     * @param nextPlayer  the player who received the pass
     */
    public static void printPass(Player player, Player nextPlayer){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        String nextName = nextPlayer.getName();
        String nextTeam = nextName.charAt(6)+"";
        String nextPosition = nextPlayer.getPosition();
        System.out.println(String.format("%s (%s,Team %s) pass ball to %s (%s,Team %s)",name,position,team,nextName,nextPosition,nextTeam));    
    }
    /**
     * Prints a message indicating that a player has lost the ball to another player.
     *
     * @param player      the player who lost the ball
     * @param nextPlayer  the player who gained possession of the ball
     */
    public static void printTurnOver(Player player, Player nextPlayer){
        String name = player.getName();
        String team = name.charAt(6)+"";
        String position = player.getPosition();
        String nextName = nextPlayer.getName();
        String nextTeam = nextName.charAt(6)+"";
        String nextPosition = nextPlayer.getPosition();
        System.out.println(String.format("%s (%s,Team %s) lost ball to %s (%s,Team %s)",name,position,team,nextName,nextPosition,nextTeam));   

    }
    /**
     * Displays the scores of both teams at the end of a quarter.
     *
     * @param aScore the score of Team A
     * @param bScore the score of Team B
     */
    public static void displayQuarterScore(int aScore, int bScore){
        System.out.println("\nQuarter has finished\n"); 
        System.out.println("Team A has "+aScore+" points");
        System.out.println("Team B has "+bScore+" points");


    }
}
