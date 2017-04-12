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

public class Dealer extends Player {
    
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
	super();
	deck = Deck.getDeck();
	deck.shuffle();
    }
    
    public void update() {
	switch (currentState) {
	case Deal :
	    dealHand();
	    break;
	case Hit :
	    tryHit();
	    break;
	case Stay :
	    stay();
	    break;
	case Reset :
	    resetGame();
	    break;
	default :
	    System.err.println("Error: Dealer not in valid state!");
		
	}
    }
    
    public void tryHit() {
	if (score < 17)
	    hit();
	else
	    setState(GameState.Stay);
    }
    
    private void hit() {
	this.hand.addCard(deck.dealCard());
    }
    
    private void stay() {
	
    }
    
    private void resetGame() {
	deck.shuffle();
    }
    
    
    @Override
    public void addCardToHand(Card card){
	//dealer's first card is face down
	if(hand.isEmpty()){
	    card.setHidden(true);
	    hand.addCard(card);
	}
	else{
	    hand.addCard(card);
	}
    }
    
    /***
     * Deals two cards to each player to start the round.
     * @author Francis Fasola
     */
    private void dealHand() {
	for (int i = 0; i < 2; i++)
	    for (Player p : GameLogic.getPlayers())
		p.addCardToHand(deck.dealCard());
	
    }
    
    public static void main (String args []){
	Dealer d1 = new Dealer();
	Card c = new NumericCard(11, Suite.DIAMONDS);
	d1.addCardToHand(c);
	System.out.println(d1);
    }
    
    
    
    
    
}
