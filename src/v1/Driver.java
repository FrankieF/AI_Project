/**
 * 
 */
package v1;

import java.util.Scanner;

/**
 * @author Frankie Fasola
 *
 */
public class Driver {
    
    private static Dealer d1;
    private static Player human;
    private static Player robot;
    
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

	GetBets();
	Deal();
	
	
    }
    
    private static void GetBets(){
	

	System.out.println("Please enter bet.");
	String bet;
	do{
	    System.out.println("The minimum bet is 10.");
	    bet = scnr.next();
	}
	while(Integer.parseInt(bet) < 10);
	human.setHandBet(Integer.parseInt(bet));
	
	//To do AI code
	
    }
    
    private static void Deal(){
	d1.dealHand();
	System.out.println("Dealer: " + d1);
	System.out.println("You: " + human);
	System.out.println("Robot: " + robot);

    }
    
}
