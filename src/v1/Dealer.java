/**
 * Representation of the dealer in a game of blackjack. This class holds a deck of cards, deals and plays the game.
 * This class is implemented with basic AI following the dealer rules of blackjack.
 * 
 * @author Frankie Fasola, Mike Ginn
 *
 */
package v1;

import java.util.List;
import java.util.ArrayList;

public class Dealer {
    
    private Deck deck;
    private int score;
    private GameState currentState, nextState;
    
    public Deck getDeck() {
        return deck;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }    
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public GameState getState() {
	return currentState;
    }
    public void setState(GameState state) {
	this.currentState = state;
    }
    
    /***
     * Creates a new dealer initialized with a deck of cards.
     * 
     * @author Francis Fasola
     */
    public Dealer() {
	deck = Deck.getDeck();
	deck.shuffle();
    }
    
    public void update() {
	switch (currentState) {
	case Deal :
	    deal();
	    break;
	case Hit :
	    tryHit();
	    break;
	case Stay :
	    stay();
	    break;
	case Reset :
	    break;
	default :
	    System.err.println("Error: Dealer not in valid state!");
		
	}
    }
    
    public void tryHit() {
	if (score < 17)
	    hit();
    }
    
    private void hit() {
	
    }
    
    private void stay() {
	
    }
    
    private void deal() {
	
    }
    
    
    
    
    
}
