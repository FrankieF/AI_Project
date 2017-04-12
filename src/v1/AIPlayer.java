/**
 * 
 */
package v1;

/**
 * @author Frankie Fasola
 *
 */
public class AIPlayer extends Player {

    
    /**
     * Checks the cards remamining in the game to make a decision on whether to stay or hit.
     * @author Francis Fasola
     * @param scoreNeeded The score needed to reach 21.
     * @return A value 0-1 representing the percent of getting a card less than or equal
     * to the scoreNeeded.
     */
    public double count(int scoreNeeded) {
	if (scoreNeeded >= 10)
	    return 1;
	
	int[] cards = new int[12];
	for (int i = 0; i < 12; i++)
	    cards[i] = 0;
	
	for (int i = 0; i < Deck.getDeck().getUsedCards().size(); i++) {
	    switch(Deck.getDeck().getUsedCards().get(i).getValue()) {
	    case 2:
		cards[2]++;
	    case 3:
		cards[3]++;
	    case 4:
		cards[4]++;
	    case 5:
		cards[5]++;
	    case 6:
		cards[6]++;
	    case 7:
		cards[7]++;
	    case 8:
		cards[8]++;
	    case 9:
		cards[9]++;
	    case 10:
		cards[10]++;
	    case 11:
		cards[11]++;
	    }
	}
	
	int suitableCards = scoreNeeded * 4;
	int usedCards = 0;
	for (int i = 2; i <= scoreNeeded; i++) {
	    usedCards += cards[i];
	}
	
	suitableCards -= usedCards;
	return suitableCards / Deck.getDeck().getReadyCards().size();
    }
}
