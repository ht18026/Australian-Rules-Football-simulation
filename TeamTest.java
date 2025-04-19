
public class TeamTest
{
    public static void main(String[] args){
        TeamTest test = new TeamTest();
        test.testInitTeam();
    }
    void testInitTeam(){
        Team team = new Team("Team A","teamA.txt");
        team.display();
        FileIO.writePlayerToFile(team.getPlayerList(),"testTeam.txt");
        team.arrangeStarPlayer(5);
        team.display();
        FileIO.writePlayerToFile(team.getPlayerList(),"testTeam.txt");

    }
}
