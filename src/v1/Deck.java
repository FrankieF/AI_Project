package v1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represnts a standard deck of 52 playing cards for a game of blackjack.
 * 
 * @author Frankie Fasola, Mike Ginn
 * @version 3/2/17
 *
 */
public class Deck {
    
    // The numeric values cards can have
    private final int[] cardValues = {2,3,4,5,6,7,8,9,10,10,10,10,11};
    private final String[] faceValues = {"Jack", "Queen", "King", "Ace"};
    public static final int NUMBER_OF_CARDS = 52; 
    private List<Card> deck;
    
    /***
     * Create a new deck by initlizing the 52 cards.
     * 
     * @author Francis Fasola
     */
    public Deck() {
	deck = new ArrayList<Card>();
	createSuit(Suite.DIAMONDS);
	createSuit(Suite.HEARTS);
	createSuit(Suite.CLUBS);
	createSuit(Suite.SPADES);
    }
    
    /***
     * Returns the deck of cards.
     * 
     * @author Francis Fasola
     * @return The deck of cards.
     */
    public List<Card> getDeck() {
	return this.deck;
    }
    
    /***
     * Creates the specificed suit of cards; first by numeric cards, then by face value cards.
     * 
     * @author Francis Fasola
     * @param suite - The suit to create.
     */
    private void createSuit (Suite suite){
	int cardsInSuite = 9, faceCards = 4;
	for(int i = 0; i < cardsInSuite; i++){
	    Card c = new NumericCard(cardValues[i], suite);
	    deck.add(c);
	}
	for (int i = 0; i < faceCards; i++) {
	    Card c = new FaceCard(cardValues[i+9], suite, faceValues[i]);
	    deck.add(c);
	}
    }

}
