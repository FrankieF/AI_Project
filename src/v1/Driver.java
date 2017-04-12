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
	GetBets();
	Deal();
	
	
    }
    
    private static void GetBets(){
	
    }
    
    private static void Deal(){
    }
    
}
