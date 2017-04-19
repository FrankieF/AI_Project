package v1;

/**
 * Simulates the chips a player has in a game
 * @author Michael
 *
 */
public class Chips {
    
    private int amount; //amount of money in chips
    
    /**
     * Constructor creates chips with no amount set
     */
    public Chips(){
	this.amount = 0;
    }
    
    /**
     * Constructor for Chips takes an amount
     * @param amount
     */
    public Chips(int amount){
	this.amount = amount;
    }
    
    /**
     * Get amount
     * @return
     */
    public int getAmount(){
	return amount;
    }
    
    /**
     * Set amount
     * @param amount
     */
    public void setAmount(int amount){
	this.amount = amount;
    }
    
    /**
     * Check if player has enough chips to make bet
     * @param betAmount
     * @return
     */
    public boolean checkBet(int betAmount){
	if(betAmount > amount){
	    return false;
	}
	else{
	    return true;
	}
    }
    
    public void removeAmount(int lostAmount){
	this.amount -= lostAmount;
    }
    
    public void addAmount(int wonAmount){
	this.amount += wonAmount;
    }
    
    /**
     * removes amount from chips
     * @param lostAmount
     */
    public void removeAmount(int lostAmount){
	this.amount -= lostAmount;
    }
    
    /**
     * adds amount to chips
     * @param wonAmount
     */
    public void addAmount(int wonAmount){
	this.amount += wonAmount;
    }
    
    /**
     * checks if player is out of chips
     * @return
     */
    public boolean noChips(){
	return amount == 0;
    }
    
    /**
     * Displays amount of money player has
     */
    @Override
    public String toString(){
	return "$" + amount;
    }

}
