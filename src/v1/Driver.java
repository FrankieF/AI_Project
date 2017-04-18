/**
 * 
 */
package v1;

import java.util.Scanner;

/**
 * @author Frankie Fasola Michael Ginn
 *
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
    
    private static void startRound(){
	if (shouldShuffle())
	    Deck.getDeck().shuffle();
	round++;
	getInput("To start another round type 1.","1");
	getBets();
	deal();
	checkForBlackJack();
	humanTurn();
	robotTurn();	
	dealerTurn();
	d1.toWinString();

    }
    
    /**
     * getBets - gets the bets of the player and the AI
     */
    private static void getBets(){


	//player gets bet

	System.out.println("Please enter bet.");
	String bet;
	do{
	    System.out.println("The minimum bet is 10.");
	    bet = scnr.next();
	}
	while(Integer.parseInt(bet) < 10);
	human.setHandBet(Integer.parseInt(bet));
	
	//AI gets bet
	robot.setHandBet(robot.getBet());
	
    }
    
    private static void deal(){
	d1.dealHand();
	System.out.println("Starting hands are: ");
	printHands();
	System.out.println("\n");
    }
    
    private static void printHands(){
	System.out.println("Dealer: " + d1);
	System.out.println("You: " + human);
	System.out.println("Robot: " + robot);

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
	}while(stay == false);
	
    }
    
    /**
     * Robot's turn to hit or stay
     */
    private static void robotTurn(){
	System.out.println("Robot's turn\nThe current hands are: ");
	printHands();
	robot.turn();
	//Do AI code

    }
    
    private static void dealerTurn(){
	System.out.println("Dealer's turn\nThe current hands are: ");
	printHands();
	if(robot.hasBust() && human.hasBust()){
	    System.out.println("Both players bust. Dealer wins.");
	}
	
	else{	
	d1.tryHit();
	printHands();
	}
    }
    
    /**
     * Checks the current round number to see if the deck should be shuffled.
     * @author Francis Fasola
     * @return true if the deck should be shuffled.
     */
    public static boolean shouldShuffle() {
	return round > SHUFFLE_DECK;
    }
    
    public static void getInput(String message, String input) {
	System.out.println(message);
	String response;
	do{
	 response = scnr.next();
	}
	while(!response.equals(input));
    }
    
    public static boolean getInput2(String message, String input, String input2) {
	System.out.println(message);
	String response;
	do{
	 response = scnr.next();
	}
	while(!response.equals(input) && !response.equals(input2));
	return response.equals("1") ? true : false;
    }
    
}
