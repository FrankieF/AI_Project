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
	Deck d = Deck.getDeck();
	for(Card c : d.getReadyCards())	    
	    if (c instanceof FaceCard)
		System.out.format("Card: %d %s %s\n", c.getValue(), c.getSuite().toString(), ((FaceCard) c).getFaceValue());
	    else
		System.out.format("Card: %d %s\n", c.getValue(), c.getSuite().toString());
	

    }

}
