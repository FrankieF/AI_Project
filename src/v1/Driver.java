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

    public static void main(String[] args) {
	System.out.println("Welcome to BlackJack! To start playing type 1.");
	String response;
	do{
	 response = scnr.next();
	}
	while(!response.equals("1"));
	startRound();
	
    }
    
    private static void startRound(){
	d1 = new Dealer();
	human = new Player();
	robot = new AIPlayer();	

	d1.addPlayer(human);
	d1.addPlayer(robot);
	d1.addPlayer(d1);

	getBets();
	deal();
	checkForBlackJack();
	humanTurn();
	robotTurn();		

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
	
	boolean dealerBlackJack = false;
	//Check for dealer blackjack
	if(d1.hasBlackjack()){
	    System.out.println("Dealer has blackjack.");
	    dealerBlackJack = true;
	}
	
	//Check for player blackjack
	//Check for push
	if(human.hasBlackjack() && dealerBlackJack == true){
	    human.getChips().addAmount(human.getHandBet());
	    System.out.println("You have blackjack!");
	    System.out.println("You push this round. No money awarded.");
	}
	//Check for win
	else if(human.hasBlackjack() && dealerBlackJack == false){
	    System.out.println("You have blackjack!");
	    System.out.println("You won the hand! You won $" + human.getHandBet());
	    human.getChips().addAmount(human.getHandBet());
	    }
	//Check for loss
	else if(dealerBlackJack == true){
	    System.out.println("You lost this hand. You lost $" + human.getHandBet());
	    human.getChips().removeAmount(human.getHandBet());
	}
	//Check robots opening hand
	//Check for push
	if(robot.hasBlackjack() && dealerBlackJack == true){
	   System.out.println("Robot has blackjack.");
	   System.out.println("Robot pushes this hand. No money awarded.");
	}
	//Check for win
	else if(robot.hasBlackjack() && dealerBlackJack == false){
	    robot.getChips().addAmount(robot.getHandBet());
	    System.out.println("Robot has blackjack.");
	    System.out.println("Robot won the hand. Robot won $" + robot.getHandBet());
	    }
	//Check for loss
	else if(dealerBlackJack == true){
	    System.out.println("Robot lost this hand. Robot lost $" + robot.getHandBet());
	    robot.getChips().removeAmount(robot.getHandBet());
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
	//Do AI code

    }
    
}
