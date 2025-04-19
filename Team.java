import java.util.ArrayList;
import java.util.Random;
/**
 * Represents a team in the Australian Rules Football simulation.
 * This class manages the players on the team and provides methods for team operations.
 */
public class Team
{   
    private String name;
    private ArrayList<Player> playerList;
    public Team()
    {
        playerList = new ArrayList<>();
        name = "";
    }
    /**
     * Constructor to initialize a team with a name and a list of players from a CSV file.
     *
     * @param name    the name of the team
     * @param csvPath the path to the CSV file containing player data
     */
    public Team(String name, String csvPath){
        this.name = name;
        ArrayList<Player> players = FileIO.readPlayersFromCsv(csvPath);
        for (Player player: players){
            if(player.getPosition().equalsIgnoreCase("Reserve")){
                player.setOnceReserved();
            }
        }
        setPlayerList(players);

    }
    /**
     * Sets the list of players for the team.
     *
     * @param players the list of Player objects
     */
    public void setPlayerList(ArrayList<Player> players){
        playerList = players;
    }
    /**
     * Gets the list of players in the team.
     *
     * @return an ArrayList of Player objects
     */
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
     /**
     * Sets the name of the team.
     *
     * @param name the name to set for the team
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Gets the name of the team.
     *
     * @return the name of the team
     */
    public String getName(){
        return name;
    }
    /**
     * Randomly selects players from the team to be star players.
     *
     * @param starCount the number of star players to assign
     */
    public void arrangeStarPlayer(int starCount){
        ArrayList<Integer> starPlayerIndices = new ArrayList<>();
        Random random = new Random();
        while (starPlayerIndices.size()<starCount){
            int index = random.nextInt(playerList.size());
            if (starPlayerIndices.contains(index)){
                continue;
            }
            starPlayerIndices.add(index);
        }
        for (int index:starPlayerIndices){
            Player player = playerList.get(index);
            StarPlayer newPlayer = new StarPlayer(player.getName(),player.getPosition(),player.getGoal());
            playerList.set(index,newPlayer);
        }
    }
    /**
     * Gets a random player from the team based on the specified position.
     *
     * @param position the position of the player to retrieve (e.g., "F" for Forward)
     * @return a randomly selected Player from the specified position
     */
    public Player getRandomPlayer(String position){
        ArrayList<Player> specifiedplayers = new ArrayList<>();

        for(Player player:getPlayerList()){
            if ((player.getPosition().charAt(0)+"").equalsIgnoreCase(position)&&!player.isInjured()){
                specifiedplayers.add(player);
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(specifiedplayers.size());
        return specifiedplayers.get(randomIndex); 
    }
    /**
     * Replaces an injured player with a reserve player.
     *
     * @param player the injured Player to replace
     * @return true if a replacement is found, false otherwise
     */
    public boolean injuredReplace(Player player){
        String position = player.getPosition();
        ArrayList<Player> reservePlayers = new ArrayList<>();

        for(Player p:getPlayerList()){
            if ((p.getPosition().charAt(0)+"").equalsIgnoreCase("R")){
                reservePlayers.add(p);
            }
        }
        if (reservePlayers.size()>0){
            Random random = new Random();
            int randomIndex = random.nextInt(reservePlayers.size());  
            Player reservePlayer = reservePlayers.get(randomIndex);
            reservePlayer.setPosition(position);
            System.out.println("Found a replacement player: "+reservePlayer.getName()+" who is playing the "+position+" field position");
            return true;
        }else{
            System.out.println("Not found a replace player, this team lose the game");
            return false;
        }
        

    }
    
    /**
     * Displays information about all players in the team.
     */
    public void display(){
        for (Player player: playerList){
            player.display();
        }
        
    }
}
