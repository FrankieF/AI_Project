/**
 * 
 */
package v1;

/**
 * @author Frankie Fasola
 *
 */
public class Player {

    Hand hand;
    Chips chips;
    
    /**
     * player constructor
     * creates an empty hand and 500 chips
     */
    public Player() {
	hand = new Hand(0);
	chips = new Chips(500);
    }
   

    /**
     * gets the player's hand
     * @return
     */
    public Hand getHand(){
	return hand;

    }
    /**
     * sets the player's hand
     * @param hand
     */
    public void setHand(Hand hand){
	this.hand = hand;
    }
    /**
     * gets the player's current bet
     * @return
     */
    public int getHandBet(){
	return hand.getBet();
    }
    /**
     * sets the player's current bet
     * @param bet
     */
    public void setHandBet(int bet){
	this.hand.setBet(bet);
    }
    /**
     * adds cards to the player's hand
     * @param card
     */
    public void addCardToHand(Card card) {
	hand.addCard(card);
    }
    

    /**
     * gets the player's current score of hand
     * @return
     */

    public int getPlayerScore(){
	return hand.getHandScore();
    }
    

    /**
     * gets the player's chips
     * @return
     */
    public Chips getChips(){
	return chips;
    }
    /**
     * sets the player's chips
     * @param chips
     */
    public void setChips(Chips chips){
	this.chips = chips;
    }
    /**
     * clears the player's hand
     */
    public void clearHand(){
	this.hand.clear();
    }
    /**
     * when player loses hand
     * player's bet is subtracted from chips
     */
    public void lostHand(){
	this.chips.removeAmount(hand.getBet());;
    }
    /**
     * when player wins hand
     * player's bet is added to chips
     */
    public void wonHand(){
	this.chips.addAmount(hand.getBet());
    }
    /**
     * check if player has blackjack
     * @return
     */
    public boolean hasBlackjack(){
	return hand.isBlackJack();
    }
    /**
     * checks if player busted
     * @return
     */
    public boolean hasBust(){
	return hand.isBust();
    }
    /**
     * returns string of players hand
     */
    public String toString(){
	return hand + ":" + hand.getHandScore() + " current bet:$" + hand.getBet() + " Chip Total:" + this.chips.getAmount();

    }
}
