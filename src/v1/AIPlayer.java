/**
 * 
 */
package v1;

/**
 * @author Frankie Fasola
 *
 */
public class AIPlayer extends Player {

    public final int HIGH_CARD_VALUE = 6;
    public double HIT_PROBABILITY = 0;
    public static final double HIT_AGGRESSIVE = .40;
    public static final double HIT_CONSERVATIVE = .70;
    private Dealer dealer;
    
    public AIPlayer(boolean isAggressive, Dealer dealer) {
	HIT_PROBABILITY = !isAggressive ? HIT_CONSERVATIVE : HIT_AGGRESSIVE;
	this.dealer = dealer;
    }
    
    public int getBet() {
	int count = count();
	if (count > 0) 
	    return highBet(count);
	else
	    return Dealer.getDealer().MIN_BET;
    }

    public int count() {
	int count = 0;
	for (Card c : Deck.getDeck().getUsedCards()) {
	    switch(c.getValue()){
	    case 2:
	    case 3:
	    case 4:
	    case 5:
	    case 6:
	      count++;
	      break;
	    case 10:
	      count--;
	      break;
	  }
	}
	return count;
    }
    
    public void turn() {
	boolean stay = false;
	while (!stay) {
        	int scoreNeeded = this.getHand().getScoreNeeded();
        	double chance = getChanceOfWinning(scoreNeeded);
        	double confidance = changeConfidance(chance);
        	int count = count();
        	double prob = (double)(chance * confidance * count);
        	if (prob > HIT_PROBABILITY) {
        	    this.addCardToHand(Dealer.getDealer().getDeck().dealCard());
        	} 
        	else 
        	    stay = true;
        	if (this.getHand().isBust())
        	    stay = true;
	}
	
    }
    
    public boolean isLessThanDealerFaceCard() {
	return this.getHand().getHandScore() - 10 <= dealer.getHand().getPlayerFaceupCard();
    }
    
    public double changeConfidance(double chance) {
	int dealerScore = dealer.getHand().getPlayerFaceupCard();
	double _chance = chance;
	if (isLessThanDealerFaceCard()) 
	    _chance = dealerScore > HIGH_CARD_VALUE ? _chance * 1.05 : _chance;
        else if (this.getHand().getHandScore() > 17) {
            _chance *= .90;
        }
	return _chance;
	
    }

    /**
     * Checks the cards remamining in the game to make a decision on whether to stay or hit.
     * @author Francis Fasola
     * @param scoreNeeded The score needed to reach 21.
     * @return A value 0-1 representing the percent of getting a card less than or equal
     * to the scoreNeeded.
     */
public double getChanceOfWinning(int scoreNeeded) {
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
    
    public int highBet(int count) {
    int ammntToBet;
    	if(count >= 5)
    	{
    	    ammntToBet = Dealer.getDealer().MIN_BET * 2;
    	}
    	else
    	{
    	    ammntToBet = Dealer.getDealer().MIN_BET;
    	}
    return ammntToBet;
   }
}
