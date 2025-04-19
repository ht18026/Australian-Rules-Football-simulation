
public class PlayerTest
{
    public static void main(String[] args){
        PlayerTest test = new PlayerTest();
        test.testDefaultConstructor();
        test.testNonDefaultConstructor();
        test.testToString();
        test.testDisplay();
        test.testGetPosition();
        test.testSetValidPosition();
        test.testSetInvalidPosition();

    }
    void testDefaultConstructor(){
        Player player = new Player();
        assert player.getName().equals("") : "Name should be an empty string";
        assert player.getPosition().equals("") : "Position should be an empty string";
        assert player.getGoal() == 0 : "Goal should be initialized to 0";
        assert player.getBehind() == 0 : "Behind should be initialized to 0";
        assert player.getSuccessfulPass() == 0 : "Successful passes should be initialized to 0";
        assert player.getKicks() == 0 : "Kicks should be initialized to 0";
        assert !player.isReported() : "Reported should be false";
        assert !player.isInjured() : "Injured should be false";
        assert !player.isOnceReserved() : "Once reserved should be false";
        System.out.println("test 1 assertions passed.");
    }
    
    void testNonDefaultConstructor(){
        Player player = new Player("test","Forward",1);
        assert player.getName().equals("test") : "Name should be test";
        assert player.getPosition().equals("Forward") : "Position should be Forward";
        assert player.getGoal() == 1 : "Goal should be initialized to 1";
        assert player.getBehind() == 0 : "Behind should be initialized to 0";
        assert player.getSuccessfulPass() == 0 : "Successful passes should be initialized to 0";
        assert player.getKicks() == 0 : "Kicks should be initialized to 0";
        assert !player.isReported() : "Reported should be false";
        assert !player.isInjured() : "Injured should be false";
        assert !player.isOnceReserved() : "Once reserved should be false";
        System.out.println("test 2 assertions passed.");
    }
    void testToString(){
        Player player = new Player("test","Forward",1);
        assert player.toString().equals("test,Forward,1") : "toString result should be: test,Forward,1";
        System.out.println("test 3 assertions passed.");
    }
    
    void testDisplay(){
        Player player = new Player("test","Forward",1);
        player.display();
        System.out.println("test 4 assertions passed.");

    }
    void testGetPosition(){
        Player player = new Player("test","Forward",1);
        assert player.getPosition().equals("Forward") : "Position should be Forward";
        System.out.println("test 5 assertions passed.");
    }
    void testSetValidPosition(){
        Player player = new Player("test","Forward",1);
        player.setPosition("Midfielder");
        assert player.getPosition().equals("Midfielder") : "Position should be Midfielder";
        System.out.println("test 6 assertions passed.");
    }
    
    void testSetInvalidPosition(){
        Player player = new Player("test","Forward",1);
        try{        
            player.setPosition("invalid");
            System.out.println("test 6 assertions failed.");

        }catch(IllegalArgumentException e){
            System.out.println("test 6 assertions passed.");

        }
    }

}
