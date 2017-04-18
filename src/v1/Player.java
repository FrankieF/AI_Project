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
    
    public Player() {
	hand = new Hand(0);
	chips = new Chips(500);
    }
   
    public Hand getHand(){
	return hand;
    }
    
    public void setHand(Hand hand){
	this.hand = hand;
    }
    
    public int getHandBet(){
	return hand.getBet();
    }
    
    public void setHandBet(int bet){
	this.hand.setBet(bet);
    }
    
    public void addCardToHand(Card card) {
	hand.addCard(card);
    }
    
    public Chips getChips(){
	return chips;
    }
    
    public void setChips(Chips chips){
	this.chips = chips;
    }
    
    public void lostHand(){
	this.chips.removeAmount(hand.getBet());;
    }
    
    public void wonHand(){
	this.chips.addAmount(hand.getBet());
    }
    
    public boolean hasBlackjack(){
	return hand.isBlackJack();
    }
    
    public boolean hasBust(){
	return hand.isBust();
    }
    
    public String toString(){
	return hand + ": " + hand.getHandScore() + " $" + hand.getBet();
    }
}
