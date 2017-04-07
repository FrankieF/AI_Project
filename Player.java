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
	hand = new Hand();
	chips = new Chips(500);
    }
    
    public void update() {
	
    }
    
    public void addCardToHand(Card card) {
	hand.addCard(card);
    }
}
