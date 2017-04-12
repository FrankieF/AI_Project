package v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represnts a standard deck of 52 playing cards for a game of blackjack.
 * 
 * @author Frankie Fasola, Mike Ginn
 * @version 3/2/17
 *
 */
public class Deck {
    
    private static Deck deck;
    public static Deck getDeck() {
	return deck == null ? deck = new Deck() : deck;
    }
    
    // The numeric values cards can have
    private final int[] cardValues = {2,3,4,5,6,7,8,9,10,10,10,10,11};
    private final String[] faceValues = {"Jack", "Queen", "King", "Ace"};
    public static final int NUMBER_OF_CARDS = 52; 
    private List<Card> usedCards, readyCards;
    
    /***
     * Create a new deck by initlizing the 52 cards.
     * 
     * @author Francis Fasola
     */
    private Deck() {
	usedCards = new ArrayList<Card>();
	readyCards = new ArrayList<Card>();
	createSuit(Suite.DIAMONDS);
	createSuit(Suite.HEARTS);
	createSuit(Suite.CLUBS);
	createSuit(Suite.SPADES);
    }
    
    public List<Card> getUsedCards() {
        return usedCards;
    }
    public List<Card> getReadyCards() {
        return readyCards;
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
	    readyCards.add(c);
	}
	for (int i = 0; i < faceCards; i++) {
	    Card c = new FaceCard(cardValues[i+9], suite, faceValues[i]);
	    readyCards.add(c);
	}
    }
    
    public void shuffle() {
	List<Card> deck = new ArrayList<Card>();
	int max = deck.size();
	while (max > 0) {
	    Random r = new Random();
	    int offset = r.nextInt(NUMBER_OF_CARDS);
	    deck.add(readyCards.get(offset));
	    readyCards.remove(offset);
	    max--;
	}
	readyCards = deck;
    }
    
    /**
     * Returns cards from the used deck to the ready deck.
     * 
     * @author Francis Fasola
     * @param shuffle - Determines if the returned cards are shuffled
     */
    public void returnCards(boolean shuffle) {
	for (Card c : usedCards)
	    readyCards.add(c);
	usedCards.clear();
	if (shuffle)
	    shuffle();
    }
    
    /**
     * Returns the card on top of the deck.
     * 
     * @author Francis Fasola
     * @return The card on top of the deck.
     */
    public Card dealCard() {
	int top = readyCards.size();
	Card c = readyCards.remove(top);
	usedCards.add(c);
	return c;
    }

}
