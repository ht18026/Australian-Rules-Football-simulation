import java.lang.reflect.Field;
import java.util.Random;
/**
 * Provides methods to determine outcomes based on player positions and probabilities.
 * This class handles random selection of outcomes for players in Australian Rules Football.
 */
class Probability{
    private static final String[] FORWARD_OUTCOMES = {"G","B","PF","TD"};
    private static final double[] FORWARD_CHANCES = {0.3,0.4,0.2,0.1};
    private static final double[] FORWARD_STAR_CHANCES = {0.45,0.4,0.1,0.05};

    private static final String[] MIDFIELDER_OUTCOMES = {"G","B","PF","PM","TM"};
    private static final double[] MIDFIELDER_CHANCES = {0.05,0.1,0.3,0.3,0.25};
    private static final double[] MIDFIELDER_STAR_CHANCES = {0.1,0.1,0.35,0.35,0.1};    
    
    private static final String[] DEFENDER_OUTCOMES = {"PM","TF"};
    private static final double[] DEFENDER_CHANCES = {0.8,0.2};
    private static final double[] DEFENDER_STAR_CHANCES = {0.95,0.05}; 
    /**
     * Randomly picks a team.
     *
     * @return true if the first team is chosen, false otherwise
     */
    public static boolean pickRandomTeam(){
        return new Random().nextBoolean();
    }
    /**
     * Gets a random result based on the player's position and whether they are a star player.
     *
     * @param position the position of the player (e.g., Forward, Midfielder, Defender)
     * @param isStar   indicates if the player is a star
     * @return a string representing the outcome (e.g., "G" for goal, "B" for behind)
     * @throws NoSuchFieldException    if the expected field does not exist
     * @throws IllegalAccessException  if the field cannot be accessed
     */
    public static String getRandomResult(String position,boolean isStar) throws NoSuchFieldException,IllegalAccessException{
        Class<?> thisClass = Probability.class;
        String outcomesFieldName = position.toUpperCase()+"_OUTCOMES";
        String chancesFieldName;
        if (isStar){
            chancesFieldName = position.toUpperCase()+"_STAR_CHANCES";
        }else{
            chancesFieldName = position.toUpperCase()+"_CHANCES";
        }
        
        Field outcomesField; 
        Field chancesField;
        String[] outcomes;
        double[] chances;
        try{
            outcomesField = thisClass.getDeclaredField(outcomesFieldName);
            chancesField = thisClass.getDeclaredField(chancesFieldName);
            outcomesField.setAccessible(true);
            chancesField.setAccessible(true);
            outcomes = (String[]) outcomesField.get(null);
            chances = (double[]) chancesField.get(null);
        }catch(NoSuchFieldException|IllegalAccessException e){
            throw e;
        }
        
        String result = getOutcomesBasedOnChances(outcomes,chances);

        return result;
    }
    /**
     * Determines an outcome based on predefined chances.
     *
     * @param outcomes an array of possible outcomes
     * @param chances  an array of corresponding probabilities for the outcomes
     * @return a randomly chosen outcome based on the provided chances
     */
    private static String getOutcomesBasedOnChances(String[] outcomes, double[] chances){
        double randomValue = new Random().nextDouble();
        double cumulativeChance = 0.0;
        for (int i = 0;i<outcomes.length;++i){
            cumulativeChance+=chances[i];
            if (randomValue<cumulativeChance){
                return outcomes[i];
            }
        }
        return "Invalid";
    }

}
