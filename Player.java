/**
 * Represents a player in the Australian Rules Football simulation.
 * This class contains player attributes and methods for actions during a game.
 */
public class Player {
    private String name;
    private String position;
    private int goal;
    private int behind;
    private int successfulPass;
    private int kicks;
    private boolean reported;
    private boolean injured;
    private boolean onceReserved;
    
    // Default constructor
    public Player() {
        this.name = "";
        this.position = "";
        this.goal = 0;
        this.behind = 0;
        this.successfulPass = 0;
        this.kicks = 0;
        this.reported = false;
        this.injured = false;
        this.onceReserved = false;
    }

    // Constructor with name and postion
    public Player(String name,String position) {
        setName(name);
        setPosition(position);
        this.goal = 0;
        this.behind = 0;
        this.successfulPass = 0;
        this.kicks = 0;
        this.reported = false;
        this.injured = false;
        this.onceReserved = false;

    }

    // Constructor with all fields
    public Player(String name, String position, int goal) {
        this.name = name;
        this.position = position;
        this.goal = goal;
        this.behind = 0;
        this.successfulPass = 0;
        this.kicks = 0;
        this.reported = false;
        this.injured = false;
        this.onceReserved = false;

    }
    /**
     * Executes an action for the player based on their position.
     *
     * @return a string indicating the result of the action
     * @throws NoSuchFieldException if a field does not exist
     * @throws IllegalAccessException if access to a field is denied
     */
    public String takeAction()throws NoSuchFieldException,IllegalAccessException{
        addKicks();
        checkInjured();
        checkReported();
        switch (getPosition()){
            case "Forward":
                return takeForwardAction();
            case "Midfielder":
                return takeMidfielderAction();
            case "Defender":
                return takeDefenderAction();
            case "Forward (Reserve)":
                return takeForwardAction();
            case "Midfielder (Reserve)":
                return takeMidfielderAction();
            case "Defender (Reserve)":
                return takeDefenderAction();
            case "Reserve (Reserve)":
                break;
        }
        return "invalid";
    }
    /**
     * Gets a result based on the player's position and a chance factor.
     *
     * @param position the position of the player
     * @return a string representing the result
     * @throws NoSuchFieldException if a field does not exist
     * @throws IllegalAccessException if access to a field is denied
     */
    public String getResultBasedOnChance(String position)throws NoSuchFieldException,IllegalAccessException{
        return Probability.getRandomResult(position,false);
    }
    /**
     * Executes actions specific to a Forward player.
     *
     * @return a string indicating the result of the action
     * @throws NoSuchFieldException if a field does not exist
     * @throws IllegalAccessException if access to a field is denied
     */
    public String takeForwardAction()throws NoSuchFieldException,IllegalAccessException{
        String result = getResultBasedOnChance("Forward");
        switch(result){
            case "G":
                addGoal();
                break;
            case "B":
                addBehind();
                break;
            case "PF":
                addSuccessfulPass();
                break;

        }
        return result;
    }
    /**
     * Executes actions specific to a Midfielder player.
     *
     * @return a string indicating the result of the action
     * @throws NoSuchFieldException if a field does not exist
     * @throws IllegalAccessException if access to a field is denied
     */
    public String takeMidfielderAction()throws NoSuchFieldException,IllegalAccessException{
        String result = getResultBasedOnChance("Midfielder");
        switch(result){
            case "G":
                addGoal();
                break;
            case "B":
                addBehind();
                break;
            case "PF":
                addSuccessfulPass();
                break;
            case "PM":
                addSuccessfulPass();
                break;
        }
        return result;
    }
    /**
     * Executes actions specific to a Defender player.
     *
     * @return a string indicating the result of the action
     * @throws NoSuchFieldException if a field does not exist
     * @throws IllegalAccessException if access to a field is denied
     */
    public String takeDefenderAction()throws NoSuchFieldException,IllegalAccessException{
        String result = getResultBasedOnChance("Defender");
        switch(result){
            case "PM":
                addSuccessfulPass();
                break;
        }
        return result;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        if (name != ""){
            this.name = name;
        }else{
            throw new IllegalArgumentException("empty player name provided");
        }
    }

    // Getter for position
    public String getPosition() {
        return position;
    }

    // Setter for position
    public void setPosition(String position) {
        String prefix = position.charAt(0)+"";
        if (prefix.equals("F")||prefix.equals("M")||prefix.equals("D")||prefix.equals("R")){
            this.position = position;
        }else{
            throw new IllegalArgumentException("Invalid position provided");
        }
        
    }

    // Getter for goal
    public int getGoal() {
        return goal;
    }

    // Setter for goal
    public void setGoal(int goal) {
        if (goal>=0){
            this.goal = goal;
        }else{
            throw new IllegalArgumentException("Invalid goal provided");
        }
    }
    //add up on goal
    public void addGoal(){
        this.goal+=1;
    }
    // Getter for behind
    public int getBehind() {
        return behind;
    }

    // Setter for behind
    public void setBehind(int behind) {
        if (behind>=0){
            this.behind = behind;
        }else{
            throw new IllegalArgumentException("Invalid behind provided");
        }
    }
    //add up on behind
    public void addBehind(){
        this.behind+=1;
    }
    
    // Getter for successful pass
    public int getSuccessfulPass() {
        return successfulPass;
    }

    // Setter for successful pass
    public void setSuccessfulPass(int successfulPass) {
        if (successfulPass>=0){
            this.successfulPass = successfulPass;
        }else{
            throw new IllegalArgumentException("Invalid pass provided");
        }
    }
    //add up on successful pass
    public void addSuccessfulPass(){
        this.successfulPass+=1;
    }

    // Getter for kicks
    public int getKicks() {
        return kicks;
    }

    // Setter for kicks
    public void setKicks(int kicks) {
        if (kicks>=0){
            this.kicks = kicks;
        }else{
            throw new IllegalArgumentException("Invalid kicks provided");
        }
    }
    //add up on kicks
    public void addKicks(){
        this.kicks+=1;
    }

    //getter for injured
    public boolean isInjured(){
        return injured;
    }
    //set injured
    public void checkInjured(){
        if (Math.random()<0.02){
            this.injured = true;
        }
    }
    //getter for reported
    public boolean isReported(){
        return reported;
    }
    //set reported
    public void checkReported(){
        if (Math.random()<0.01){
            this.reported = true;
        }
    }
    //getter for onceReserved
    public boolean isOnceReserved(){
        return onceReserved;
    }
    //set onceReserved to true
    public void setOnceReserved(){
        this.onceReserved = true;
    }


    /**
     * Returns a string representation of the player.
     *
     * @return a string formatted as "name,position,goal"
     */    
    @Override
    public String toString() {
        return name + ","+position+"," + goal;
    }

    // Display method
    public void display() {
        System.out.println(this.toString());
    }
}
