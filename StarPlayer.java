/**
 * Represents a star player in the Australian Rules Football simulation.
 * This class extends the Player class to apply the chance for Star players.
 */
public class StarPlayer extends Player{
    
    /**
     * Constructor to initialize a StarPlayer with a name and position.
     *
     * @param name     the name of the star player
     * @param position the position of the star player (e.g., Forward, Midfielder)
     */    public StarPlayer(String name,String position) {
        super(name,position);
    }

    /**
     * Constructor to initialize a StarPlayer with all fields.
     *
     * @param name     the name of the star player
     * @param position the position of the star player
     * @param goal     the number of goals scored by the star player
     */
    public StarPlayer(String name, String position, int goal) {
        super(name,position,goal);
    }
    /**
     * Overrides the method to get the result based on chance for star players.
     * Uses the Probability class to determine outcomes specific to star players.
     *
     * @param position the position of the star player
     * @return a string representing the outcome (e.g., "G" for goal, "B" for behind)
     * @throws NoSuchFieldException    if the expected field does not exist
     * @throws IllegalAccessException  if the field cannot be accessed
     */    
    @Override
    public String getResultBasedOnChance(String position)throws NoSuchFieldException,IllegalAccessException{
        return Probability.getRandomResult(position,true);
    }

}
