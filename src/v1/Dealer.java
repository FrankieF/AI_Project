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

    private List<Player> players;
    private Deck deck;
    public final int MIN_BET = 10;


    /**
     * gets the dealer's deck
     * 
     * @return
     */

    public Deck getDeck() {
	return deck;
    }

    /**
     * sets the dealer's deck
     * 
     * @param deck
     */
    public void setDeck(Deck deck) {

	this.deck = deck;
    }

    /**
     * gets the dealer's hand score
     * 
     * @return
     */
    public int getScore() {
	return this.hand.getHandScore();

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
	players = new ArrayList<Player>();

    }


    /**
     * gets the players in the game
     * @return
     */
    public List<Player> getPlayers() {
	return players;

    }

    /**
     * adds players to the game
     * @param player
     */
    public void addPlayer(Player player) {
	this.players.add(player);
    }
    /**
     * removes players from the game
     * @param player
     */
    public void removePlayer(Player player) {
	this.players.remove(player);
    }
    /**
     * dealer hits if his score is less than 17
     * This is how blackjack is played.
     */
    public void tryHit() {
	while (hand.getHandScore() < 17) {

	    hit();
	}
    }
    /**
     * adds a card to the dealers hand and deals card
     */
    private void hit() {
	addCardToHand(deck.dealCard());
    }

    /**
     * returns a string of the dealer's hand
     */
    @Override
    public String toString() {
	return getHand() + "";
    }
    /**
     * changes the hidden card in dealer's hand
     * to not hidden
     */

    public void toWinString() {
	for (Card c : hand.getHand())
	    c.setHidden(false);
    }
    
    /**
     * adds card to dealer's hand
     * first card is always face down
     */
    @Override
    public void addCardToHand(Card card) {
	// dealer's first card is face down
	if (hand.isEmpty()) {
	    card.setHidden(true);
	    hand.addCard(card);
	} else {
	    hand.addCard(card);
	}
    }

    /***
     * Deals two cards to each player to start the round.
     * 
     * @author Francis Fasola
     */
    public void dealHand() {
	for (int i = 0; i < 2; i++)

	    for (Player p : players) {


		p.addCardToHand(deck.dealCard());
	    }
    }

    /**
     * Hits a player in game
     * 
     * @param player
     */
    public void hitPlayer(Player player) {
	player.addCardToHand(deck.dealCard());

    }

}
