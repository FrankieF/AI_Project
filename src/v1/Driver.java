/**
 * 
 */
package v1;

import java.util.Scanner;

/**
 * @author Frankie Fasola Michael Ginn

 * Driver class simulates the blackjack game between the user, AIplayer, and dealer

 */
public class Driver {
    
    private static Dealer d1;
    private static Player human;
    private static AIPlayer robot;    
    private static Scanner scnr = new Scanner(System.in);
    private static int round = 0;
    private static final int SHUFFLE_DECK = 3;

    public static void main(String[] args) {
      
	getInput("Welcome to BlackJack! To start playing type 1.", "1");
	boolean aggressive = getInput2("If you want to play a aggressive player type 1, otherwise type 2.", "1","2");
	d1 = new Dealer();
	human = new Player();
	robot = new AIPlayer(aggressive, d1);	

	d1.addPlayer(human);
	d1.addPlayer(robot);
	d1.addPlayer(d1);
	startRound();
	
    }

   
    
    /**
     * starts a new round of blackjack
     */
    private static void startRound(){
	//checks that players have chips left
	checkChips();
	//clears all old hands
	human.clearHand();
	robot.clearHand();
	d1.clearHand();
	
	//brings in a new shuffled deck
	if (shouldShuffle()){
	    d1.setDeck(d1.getDeck().getNewDeck());
	    d1.getDeck().shuffle();
	    round = 0;
	    System.out.println("New dealer has entered the game.\n");
	}

	round++;
	getInput("To start another round type 1.","1");
	getBets();
	deal();
	checkForBlackJack();
	humanTurn();

	if(!robot.getChips().noChips()){
	robotTurn();	
	}

	dealerTurn();
	d1.toWinString();
	checkWinners();
    }
    
    /**
     * getBets - gets the bets of the player and the AI
     */
    private static void getBets(){

	//player makes bet
	//must be atleast 10$ and at most the amount of chips they have
	System.out.println("Please enter bet.");
	String bet;
	try{
	do{
	    System.out.println("The minimum bet is 10.");
	    System.out.println("You have " + human.getChips().getAmount() + " chips.");
	    bet = scnr.next();
	}
	while(Integer.parseInt(bet) < 10 || (!human.getChips().checkBet(Integer.parseInt(bet))));
	human.setHandBet(Integer.parseInt(bet));
	//catches invalid bet and sets bet at minimum bet
	}catch(NumberFormatException e){
	    System.out.println("Invalid bet amount");
	    human.setHandBet(10);
	}

	
	//AI gets bet
	robot.setHandBet(robot.getBet());
	
    }
    

    /**
     * deals cards to all players
     */

    private static void deal(){
	d1.dealHand();
	System.out.println("Starting hands are: ");
	printHands();
	System.out.println("\n");
    }
    

    /**
     * prints all hands in game
     */
    private static void printHands(){
	System.out.println("Dealer: " + d1);
	System.out.println("You: " + human);
	if(!robot.getChips().noChips()){
	System.out.println("Robot: " + robot);
	}


    }
    
    /**
     * Checks hands for blackjack
     */
    private static void checkForBlackJack(){
	

	
	//Check for dealer blackjack
	if(d1.hasBlackjack()){
	    System.out.println("Dealer has blackjack.");
	    
	    if(!human.hasBlackjack() && !robot.hasBlackjack()){
		System.out.println("Dealer wins the hand. You lost $"
			+ human.getHandBet());
		System.out.println("Robot lost $" + robot.getHandBet());
		human.wonHand();
		robot.wonHand();
	    }
	    else if(human.hasBlackjack()){
		System.out.println("You have blackjack!");
		System.out.println("You push this round. No money awarded.");
	    }
	    else if(robot.hasBlackjack()){
		System.out.println("Robot has blackjack.");
		System.out.println("Robot pushed this round. No money awarded.");
	    }
	    startRound();
	}
	else if(!d1.hasBlackjack()){
	    if(human.hasBlackjack() && robot.hasBlackjack()){
		System.out.println("You and robot have blackjack!");
		System.out.println("You both win the hand. You are awarded $" +
		human.getHandBet());
		System.out.println("Robot is awarded $" + robot.getHandBet());
		human.wonHand();
		robot.wonHand();
		startRound();
	    }
	}
	
    }    
       
    
    /**
     * Player's turn to hit or stay
     */
    private static void humanTurn(){
	System.out.println("Your turn!");
	String response;
	boolean stay = false;;
	//Loops until player stays
	do{	
	    System.out.println("Type 1 to Hit or type 2 to Stay.");
	    response = scnr.next();	 
	   
	    if(response.equals("1")){
		d1.hitPlayer(human);
		printHands();
	    }
	    else if(response.equals("2")){
		stay = true;
	    }
	}while(stay == false && !human.hasBust());
	
	if(human.hasBust()){
	    System.out.println("You bust.\n");
	}
	
    }
    
       /**
     * Robot's turn to hit or stay
     */
    private static void robotTurn(){
	System.out.println("Robot's turn\nThe current hands are: ");
	printHands();
   //AI gets bet
	robot.setHandBet(robot.getBet());
	robot.turn();	
    }
    
   
    /**
     * dealer's turn to hit or stay
     */
    private static void dealerTurn(){
	System.out.println("Dealer's turn\nThe current hands are: ");
	printHands();
	if(robot.hasBust() && human.hasBust()){
	    System.out.println("Both players bust. Dealer wins.");

	}
	
	else{	
	d1.tryHit();
	}
  }
    
    /**
     * Checks the current round number to see if the deck should be shuffled.
     * @author Francis Fasola
     * @return true if the deck should be shuffled.
     */
    public static boolean shouldShuffle() {
	return round == SHUFFLE_DECK;
    }
    

    /**
     * gets input to play again
     * @param message
     * @param input
     */
    public static void getInput(String message, String input) {
	System.out.println(message);
	String response;
	do{
	 response = scnr.next();
	}
	while(!response.equals(input));
    }	
	
    /**
     * gets input for what type of AI to play against
     * @param message
     * @param input
     * @param input2
     * @return
     */
    public static boolean getInput2(String message, String input, String input2) {
	System.out.println(message);
	String response;
	do{
	 response = scnr.next();
	}
	while(!response.equals(input) && !response.equals(input2));
	return response.equals("1") ? true : false;
    }

    /**
     * checks for any winners in the game
     */
    private static void checkWinners(){	
	checkForBlackJack();
	System.out.println("\nThe final hands for the round are:\n\t");
	printHands();
	
	//check for busts
	if(human.hasBust()){
	    System.out.println("You bust! You lost $" + human.getHandBet());
	    human.lostHand();
	}
	else if(robot.hasBust()){
	    System.out.println("Robot bust! Robot lost $" + robot.getHandBet());
	    robot.lostHand();
	}
	else if(d1.hasBust()){
	    System.out.println("Dealer bust! You and robot win the hand!");
	    System.out.println("You won $" + human.getHandBet());
	    System.out.println("Robot won $" + robot.getHandBet());
	    robot.wonHand();
	    human.wonHand();
	    startRound();
	}
	
	//check scores
	if(human.getPlayerScore() > d1.getPlayerScore()  && (!human.hasBust())){
	    System.out.println("You won the hand!\nYou won $" + human.getHandBet());
	    human.wonHand();
	}
	if(human.getPlayerScore() == d1.getPlayerScore()){
	    System.out.println("You push! No money awarded.");
	}
	if(robot.getPlayerScore() > d1.getPlayerScore() && (!robot.hasBust())){
	    System.out.println("Robot won the hand. Robot won $" + robot.getHandBet());
	    robot.wonHand();
	}
	if(robot.getPlayerScore() == d1.getPlayerScore()){
	    System.out.println("Robot pushed. No money awarded.");
	}
	if(d1.getPlayerScore() > human.getPlayerScore()){
	    System.out.println("You lost the hand. You lost $" + human.getHandBet());
	    human.lostHand();
	}
	if(d1.getPlayerScore() > robot.getPlayerScore() && (!robot.getChips().noChips())){
	    System.out.println("Robot lost the hand. Robot lost $" + robot.getHandBet());
	    robot.lostHand();
	}
	
	startRound();
	
	
	

	
    }
    
    /**

     * checks that players have chips remaining to bet
     */
    private static void checkChips(){
	if(human.getChips().noChips()){
	    System.out.println("You have no chips left.");
	    System.exit(0);
	}
	else if(robot.getChips().noChips()){
	    System.out.println("Robot ran out of chips. Robot has left the game.");
	    d1.removePlayer(robot);
	}

    }
    
}
