import java.util.Arrays;

public class ProbabilityTest
{
    private static final String[] FORWARD_OUTCOMES = {"G","B","PF","TD"};
    private static final String[] MIDFIELDER_OUTCOMES = {"G","B","PF","PM","TM"};
    private static final String[] DEFENDER_OUTCOMES = {"PM","TF"};
    
    public static void main(String[] args)throws NoSuchFieldException,IllegalAccessException{
        ProbabilityTest test = new ProbabilityTest();
        test.testGetRandomResultForForward();
        test.testGetRandomResultForMid();
        test.testGetRandomResultForDefender();
        test.testGetRandomResultForStarForward();
        test.testGetRandomResultForStarMid();
        test.testGetRandomResultForStarDefender();


    }
    void testGetRandomResultForForward()throws NoSuchFieldException,IllegalAccessException{
        String result = Probability.getRandomResult("Forward",false);
        System.out.println(result);
        assert(isInArray(result,FORWARD_OUTCOMES));
    }
    void testGetRandomResultForStarForward()throws NoSuchFieldException,IllegalAccessException{
        String result = Probability.getRandomResult("Forward",true);
        assert(isInArray(result,FORWARD_OUTCOMES));

    }
    void testGetRandomResultForMid()throws NoSuchFieldException,IllegalAccessException{
        String result = Probability.getRandomResult("Midfielder",false);
        assert(isInArray(result,MIDFIELDER_OUTCOMES));

    }
    
    void testGetRandomResultForStarMid()throws NoSuchFieldException,IllegalAccessException{
        String result = Probability.getRandomResult("Midfielder",true);
        assert(isInArray(result,MIDFIELDER_OUTCOMES));
    }
    void testGetRandomResultForDefender()throws NoSuchFieldException,IllegalAccessException{
        String result = Probability.getRandomResult("Defender",false);
        assert(isInArray(result,DEFENDER_OUTCOMES));

    }
    
    void testGetRandomResultForStarDefender()throws NoSuchFieldException,IllegalAccessException{
        String result = Probability.getRandomResult("Defender",true);
        assert(isInArray(result,DEFENDER_OUTCOMES));

    }
    boolean isInArray(String value, String[] array) {
        return Arrays.stream(array).anyMatch(x -> x == value);
    }
    
    
}
