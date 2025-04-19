/**
 * Australian Rules Football game simulation.
 * This class manages the game flow, player possession, and team statistics.
 * @author Xijia Wang 
 */
public class AFLGame
{   
    static final String teamAPath = "teamA.txt";
    static final String teamBPath = "teamB.txt";
    static final int totalQuarters = 4;// Total number of quarters in the game
    static final int eventsInEachQuarter = 80;// Total events in each quarter
    Team teamA;
    Team teamB;
    Team possessionTeam;    // The team currently in possession of the ball
    Player possessionPlayer;    // The player currently in possession of the ball
    /**
     * Gets the team currently in possession of the ball.
     *
     * @return the possession team
     */
    public Team getPossessionTeam(){
        return possessionTeam;
    }
    /**
     * Sets the team in possession of the ball.
     *
     * @param team the team to set as possession team
     */
    public void setPossessionTeam(Team team){
        this.possessionTeam = team;
    }
    /**
     * Gets the player currently in possession of the ball.
     *
     * @return the possession player
     */
    public Player getPossessionPlayer(){
        return possessionPlayer;
    }
    /**
     * Sets the player in possession of the ball.
     *
     * @param player the player to set as possession player
     */
    public void setPossessionPlayer(Player player){
        this.possessionPlayer = player;
    }
     /**
     * Initializes the teams and their star players based on user input.
     */
    private void initTeams(){
        int teamAStarCount = Display.askForStarPlayerCount("A");
        int teamBStarCount = Display.askForStarPlayerCount("B");
        teamA = new Team("Team A",teamAPath);
        teamB = new Team("Team B",teamBPath);
        teamA.arrangeStarPlayer(teamAStarCount);
        teamB.arrangeStarPlayer(teamBStarCount);
    }
    /**
     * Runs a quarter of the game.
     *
     * @param quarter the current quarter number
     * @return the result of the quarter
     * @throws NoSuchFieldException if a field cannot be found
     * @throws IllegalAccessException if access to a field is denied
     */
    private String runQuarter(int quarter)throws NoSuchFieldException,IllegalAccessException{
        Display.printQuarter(quarter+1);
        String ballLocation = "mid";
        String status = "start";
        for (int i = 0;i<eventsInEachQuarter; ++i){
            status = runEvent(i+1,status);
            if (status == "Team A"||status == "Team B"){
                return status;
            }
        }
        int scoreTeamA = AFLGameStat.calculateScore(teamA);
        int scoreTeamB = AFLGameStat.calculateScore(teamB);
        Display.displayQuarterScore(scoreTeamA,scoreTeamB);
        return "finished";

    }
    /**
     * Runs a specific event in the game based on the current status.
     *
     * @param eventNum the event number
     * @param status   the current game status
     * @return the result of the event
     * @throws NoSuchFieldException if a field cannot be found
     * @throws IllegalAccessException if access to a field is denied
     */
    private String runEvent(int eventNum,String status)throws NoSuchFieldException,IllegalAccessException{
        Display.printEventNum(eventNum);
        if (status=="start"||status=="G"){
            return startGameFromMid();
        }else{
            return continueGame();
        }
        
       
    }
   

    
    /**
     * Decides which player or team gains possession of the ball after an event.
     *
     * @param result the result of the event
     */
    private void decideNextPossession(String result){
        if (result == "G"){
            Display.printGoal(getPossessionPlayer());
            return;
        }
        if (result == "B"){
            Display.printBehind(getPossessionPlayer());
            setPossessionTeam(getPossessionTeam().equals(teamA)?teamB:teamA);
            Player player = getPossessionTeam().getRandomPlayer("D");
            setPossessionPlayer(player);
            Display.displayGotBall(getPossessionPlayer());

            return;
        }
        
        if (result.charAt(0)=='P'){
            Player player = getPossessionTeam().getRandomPlayer(result.charAt(1)+"");
            Display.printPass(getPossessionPlayer(),player);
            setPossessionPlayer(player);
            Display.displayGotBall(getPossessionPlayer());

            return;
        }
        
        if (result.charAt(0)=='T'){
            setPossessionTeam(getPossessionTeam().equals(teamA)?teamB:teamA);
            Player player = getPossessionTeam().getRandomPlayer(result.charAt(1)+"");
            Display.printTurnOver(getPossessionPlayer(),player);
            setPossessionPlayer(player);
            Display.displayGotBall(getPossessionPlayer());
            return;
        }

    }
    /**
     * Continues the game after an event.
     *
     * @return the result of the event
     * @throws NoSuchFieldException if a field cannot be found
     * @throws IllegalAccessException if access to a field is denied
     */
    private String continueGame() throws NoSuchFieldException,IllegalAccessException{
        Player player = getPossessionPlayer();
        Team team = getPossessionTeam();
        String result = player.takeAction();
        decideNextPossession(result);
        if(player.isInjured()){
            Display.displayInjured(player);
            boolean replaceSuccessful = team.injuredReplace(player);
            if (!replaceSuccessful){
                //if replace not successful, return the losing team
                return team.getName();
                }
        }
        return result;
    }
    /**
     * Starts the game from the center and determines initial possession.
     *
     * @return the result of the initial game action
     * @throws NoSuchFieldException if a field cannot be found
     * @throws IllegalAccessException if access to a field is denied
     */
    private String startGameFromMid()throws NoSuchFieldException,IllegalAccessException{
        System.out.println("Starting from the centre circle");
        if (Probability.pickRandomTeam()){
                Player player = teamA.getRandomPlayer("M");
                setPossessionPlayer(player);
                setPossessionTeam(teamA);
                Display.displayGotBallInMid(player);
            }else{
                Player player = teamB.getRandomPlayer("M");
                setPossessionPlayer(player);
                setPossessionTeam(teamB);
                Display.displayGotBallInMid(player);


        }
        return continueGame();
        
    }
    /**
     * Processes the final result of the game.
     *
     * @param result the result of the game
     */
    private void runResult(String result){
        AFLGameStat.computeResult(result,teamA,teamB);
    }

    /**
     * Writes the updated player information to output files.
     */
    private void writeOutput(){
        System.out.println("Writing output files");
        FileIO.writePlayerToFile(teamA.getPlayerList(),"teamAUpdated.txt");
        FileIO.writePlayerToFile(teamB.getPlayerList(),"teamBUpdated.txt");
        System.out.println("Goodbye");

    }
    /**
     * The main method to run the AFL game simulation.
     *
     * @param args command-line arguments
     * @throws NoSuchFieldException if a field cannot be found
     * @throws IllegalAccessException if access to a field is denied
     */    
    public static void main(String[] args)throws NoSuchFieldException,IllegalAccessException
    {
        AFLGame game = new AFLGame();
        Display.printWelcomeMessage();
        game.initTeams();
        String result = "";
        for (int i = 0;i<totalQuarters;++i){
            result = game.runQuarter(i);
            if (result == "Team A"||result == "Team B"){
                break;
            }        
        }
        game.runResult(result);
        game.writeOutput();
    }
}
