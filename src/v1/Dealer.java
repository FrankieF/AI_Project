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
    private int score, gameNumber;
    private final int SHUFFLE_DECK = 3;
    public final int MIN_BET = 50;
    
    
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
	deck = deck.getDeck();
	deck.shuffle();      
	gameNumber = 0;	
	players = new ArrayList<Player>();
   
    }
    

    public List<Player> getPlayers(){
	return players;

    }
    
    public void addPlayer(Player player){
	this.players.add(player);
    }
    
    
    public void tryHit() {
	if (score < 17)
	    hit();
    }
    
    private void hit() {
	this.hand.addCard(deck.dealCard());
    }
    
    private void resetGame() {
	deck.shuffle();
    }
    
    @Override
    public String toString(){
	return getHand() + "";
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
    public void dealHand() {
	for (int i = 0; i < 2; i++)

	    for (Player p : players) {

		p.addCardToHand(deck.dealCard());
	
    }
    }
    
    /**
     * Hits a player in game
     * @param player
     */
    public void hitPlayer(Player player){
	player.addCardToHand(deck.dealCard());
    }
    
    public static void main (String args []){
	Dealer d1 = new Dealer();
	System.out.println(d1);
    }
    
    
    
    
    
}
