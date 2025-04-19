import java.text.DecimalFormat;
/**
 * Represents statistics and calculations for an Australian Rules Football game.
 * This class provides methods to calculate goals, behinds, scores, and to display game results.
 */
public class AFLGameStat{
   /**
     * Calculates the total number of goals scored by a team.
     *
     * @param team the team whose goals are to be calculated
     * @return the total number of goals scored by the team
     */
    public static int calculateGoal(Team team){
        int totalGoal = 0;
        for (Player player: team.getPlayerList()){
            totalGoal+=player.getGoal();
        }
        return totalGoal;
    }
    /**
     * Calculates the total number of behinds scored by a team.
     *
     * @param team the team whose behinds are to be calculated
     * @return the total number of behinds scored by the team
     */
    public static int calculateBehind(Team team){
        int totalBehind = 0;
        for (Player player: team.getPlayerList()){
            totalBehind+=player.getBehind();
        }
        return totalBehind;
    }
    /**
     * Calculates the total score of a team based on goals and behinds.
     *
     * @param team the team whose score is to be calculated
     * @return the total score of the team
     */
    public static int calculateScore(Team team){
        int totalScore = 0;
        for (Player player: team.getPlayerList()){
            totalScore+=player.getGoal()*6+player.getBehind();
        }
        return totalScore;
    }
    /**
     * Computes and displays the result of the game based on the final scores and forfeits.
     *
     * @param losingTeam the name of the losing team, if applicable
     * @param teamA     the first team
     * @param teamB     the second team
     */
    public static void computeResult(String losingTeam,Team teamA,Team teamB){
        System.out.println("Game Result");
        System.out.println("===========");
        if (losingTeam == "Team A"){
            System.out.println("Team B has won the game because team A forfeits the match.");

        }else if (losingTeam == "Team B"){
            System.out.println("Team A has won the game because team B forfeits the match.");

        }
        else{
            int scoreTeamA = AFLGameStat.calculateScore(teamA);
            int scoreTeamB = AFLGameStat.calculateScore(teamB);   
            if (scoreTeamA>scoreTeamB){
                System.out.println("Team A has won the game with "+scoreTeamA+" score.");
            }else if(scoreTeamA<scoreTeamB){
                System.out.println("Team B has won the game with "+scoreTeamB+" score.");
            }else{
            System.out.println("The game is a draw!");
            }
        }  
        
        System.out.println("\nTeam A");
        printTeamStat(teamA);
        printIndividualStat(teamA);
        
        System.out.println("\nTeam B");
        printTeamStat(teamB);
        printIndividualStat(teamB);

        System.out.println("\nInjured Players");
        System.out.println("===============");
        System.out.println("Team teamA");
        printInjuredPlayer(teamA);
        System.out.println("Team teamB");
        printInjuredPlayer(teamB);
        System.out.println("\nReported Players");
        System.out.println("===============");
        System.out.println("Team teamA");
        printReportedPlayer(teamA);
        System.out.println("Team teamB");
        printReportedPlayer(teamB);
 
    }
    /**
     * Prints the overall statistics of a team.
     *
     * @param team the team whose statistics are to be printed
     */
    private static void printTeamStat(Team team){
        int totalGoal = calculateGoal(team);
        int totalBehind = calculateBehind(team);
        int totalScore = calculateScore(team);
        System.out.println("  Total goals: "+totalGoal);
        System.out.println("  Total behinds: "+totalBehind);
        System.out.println("  Total scores: "+totalScore);
        printGreatestKicksPlayer(team);
        printMostGoalPlayer(team);
        
        
    }
    /**
     * Prints the player with the greatest number of kicks in a team.
     *
     * @param team the team whose players are to be evaluated
     */
    private static void printGreatestKicksPlayer(Team team){
        System.out.println("  Who has the greatest number of kicks?");
        int maxKicks = 0;
        for (Player player: team.getPlayerList()){
            if (player.getKicks()>maxKicks){
                maxKicks = player.getKicks();
            }
        }
        for (Player player: team.getPlayerList()){
            if (player.getKicks()==maxKicks){
                System.out.println("    "+player.getName()+" kicked the ball "+player.getKicks()+" times");
            }
        }
    }
    /**
     * Prints the player who kicked the most goals in a team.
     *
     * @param team the team whose players are to be evaluated
     */
    private static void printMostGoalPlayer(Team team){
        System.out.println("  Who kicked the most goals?");
        int maxGoal = 0;
        for (Player player: team.getPlayerList()){
            if (player.getGoal()>maxGoal){
                maxGoal = player.getGoal();
            }
        }
        for (Player player: team.getPlayerList()){
            if (player.getGoal()==maxGoal){
                System.out.println("    "+player.getName()+" kicked "+player.getGoal()+" goals");
            }
        }
    }
    /**
     * Prints individual statistics for each player on the team.
     *
     * @param team the team whose players' statistics are to be printed
     */
    private static void printIndividualStat(Team team){
        System.out.println("  Individual statistics:");
        System.out.printf("    %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n","Name","Kicks","Goals","Behinds","Pass","Percent","Position");
        for (Player player:team.getPlayerList()){
            
            String name = player.getClass()==StarPlayer.class?player.getName()+"*":player.getName();
            String kicks = player.getKicks()+"";
            String goals = player.getGoal()+"";
            String behinds = player.getBehind()+"";
            String pass = player.getSuccessfulPass()+"";
            double percentValue = player.getKicks()>0?((double)(player.getGoal()+player.getBehind()+player.getSuccessfulPass())*100/player.getKicks()):0.0;
            DecimalFormat df = new DecimalFormat("0.00");
            String percent = df.format(percentValue)+"%";
            String position = player.getPosition();

            if(player.isInjured()){
                position+=" (injured)";
            }else if(player.isOnceReserved()&&!player.getPosition().equalsIgnoreCase("Reserve")){
                position+=" (Reserve)";
            }
            System.out.printf("    %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",name,kicks,goals,behinds,pass,percent,position);

        }
    }
    /**
     * Prints a list of injured players on the team.
     *
     * @param team the team whose injured players are to be printed
     */
    private static void printInjuredPlayer(Team team){
        for (Player player:team.getPlayerList()){
            if (player.isInjured()){              
                System.out.println("  "+player.getName()+" ("+player.getPosition()+")");
            }
        }
    }
    /**
     * Prints a list of reported players on the team.
     *
     * @param team the team whose reported players are to be printed
     */
    private static void printReportedPlayer(Team team){
        for (Player player:team.getPlayerList()){
            if (player.isReported()){
                System.out.println("  "+player.getName()+" ("+player.getPosition()+")");
            }
        }
    }
    
}
