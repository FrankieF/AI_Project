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
    
    private static Dealer dealer = null;
    public static Dealer getDealer() {
	return dealer == null ? new Dealer() : dealer;
    }
    
    private Deck deck;
    private int score, gameNumber;
    private final int SHUFFLE_DECK = 3;
    
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
    
    /***
     * Creates a new dealer initialized with a deck of cards.
     * 
     * @author Francis Fasola
     */
    public Dealer() {
	super();
	deck = Deck.getDeck();
	deck.shuffle();
	gameNumber = 0;
    }
    
    public void update() {
	dealHand();
	tryHit();
	resetGame();		
    }
    
    public void tryHit() {
	if (score < 17)
	    hit();
    }
    
    private void hit() {
	this.hand.addCard(deck.dealCard());
    }
    
    private void resetGame() {
	gameNumber++;
	for (Player p : GameLogic.getPlayers())
	    p.getHand().clearHand();
	if (gameNumber >= SHUFFLE_DECK) {
	    gameNumber = 0;
	    deck.returnCards(true);
	} else
	    deck.shuffle();
	dealHand();
    }
    
    /***
     * Deals two cards to each player to start the round.
     * @author Francis Fasola
     */
    public void dealHand() {
	for (int i = 0; i < 2; i++)
	    for (Player p : GameLogic.getPlayers()) {
		p.addCardToHand(deck.dealCard());
		if(p.isBlackJack()) {
		    
		}
	    }
    }
    
    
    
    
    
}
