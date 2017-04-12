/**
 * 
 */
package v1;

/**
 * @author Frankie Fasola
 *
 */
public class Player {

    protected Hand hand;
    protected Chips chips;
    protected boolean stay = false;
    
    public Player() {
	hand = new Hand();
	chips = new Chips(500);
    }
    
    public void update() {
	while (!hand.isBust() && !hand.isBlackJack() && !stay) {
	    
	}
    }
    
    public boolean isBlackJack() {
	return hand.getHandScore() == 21 ? true : false;
	    
    }
    
    public void addCardToHand(Card card) {
	hand.addCard(card);
    }
    
    public Hand getHand() {
	return this.hand;
    }
    
    public void bet(int amount) {
	this.chips.checkBet(amount);
    }
    
    public boolean outOfMoney() {
	return this.chips.noChips();
    }
}
