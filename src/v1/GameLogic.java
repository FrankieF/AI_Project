package v1;

/**
 * The gamelogic of blackjack. This class controls game and interfaces with other classes
 * to deal cards, perform AI moves, etc.
 * 
 * @author Frankie Fasola, Mike Ginn
 *@version 3/2/17
 */
public class GameLogic {

    public static void main(String[] args) {
	Deck d = new Deck();
	for(Card c : d.getDeck())
	    System.out.format("Card: %d %s\n", c.getValue(), c.getSuite().toString());

    }

}
