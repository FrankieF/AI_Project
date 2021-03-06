package v1;

/**
 * Represnts a single card to be used in a deck of cards.
 * 
 * @author Frankie Fasola, Mike Ginn
 *@version 3/2/17
 */
public abstract class Card
{
	private Suite suite;
	private int value;
	private boolean isHidden = false;
	
	public Card (int value, Suite suite) {
	    this.value = value;
	    this.suite = suite;
	}
	
	public Suite getSuite() {
	    return suite;
	}
	public void setSuite(Suite suite) {
	    this.suite = suite;
	}
	public int getValue() {
	    return value;
	}
	public void setValue(int value) {
	    this.value = value;
	}
	public boolean isHidden() {
	    return isHidden;
	}
	public void setHidden(boolean isHidden) {
	    this.isHidden = isHidden;
	}
	
	/**
	 *Checks if card is Ace
	 * @return
	 */
	public boolean isAce(){
	    return getValue() == 11;
	}
	
	/**
	 * Displays card
	 */
	@Override
	public String toString(){
	    return value + "" + suite;
	}
	
	
}

