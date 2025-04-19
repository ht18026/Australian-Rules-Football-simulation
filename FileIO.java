import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Provides methods for reading and writing player data to and from files.
 * This class handles CSV file operations related to player information.
 */
public class FileIO
{
    String path;
    /**
     * Constructor to initialize the FileIO object with a specified file path.
     *
     * @param path the path to the file
     */
    public FileIO(String path){
        setPath(path);
    }
    /**
     * Reads player data from a CSV file and returns a list of Player objects.
     *
     * @param path the path to the CSV file
     * @return an ArrayList of Player objects
     */
    public static ArrayList<Player> readPlayersFromCsv(String path){
        String line = ""; 
        ArrayList<Player> playerList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while((line = br.readLine()) != null){
                String[] contents = line.split(",");
                Player player = new Player(contents[0],contents[1],Integer.parseInt(contents[2]));
                playerList.add(player);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return playerList;
    }
    /**
     * Converts a list of Player objects into a list of their string representations.
     *
     * @param playerList the list of Player objects
     * @return an ArrayList of strings representing the players
     */
    public static ArrayList<String> getPlayerString(ArrayList<Player> playerList){
        ArrayList<String> stringList = new ArrayList<>();
        for (Player player : playerList){
            stringList.add(player.toString());
        }
        return stringList;
    }
    /**
     * Writes a list of Player objects to a specified file.
     *
     * @param playerList the list of Player objects to write
     * @param fileName   the name of the output file
     */
    public static void writePlayerToFile(ArrayList<Player> playerList,String fileName){
        ArrayList<String> stringList = getPlayerString(playerList);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (String line: stringList){
                writer.write(line);
                writer.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public String getPath(){
        return path;
    }
    public void setPath(String path){
        this.path = path;
    }
}
