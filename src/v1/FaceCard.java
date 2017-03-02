package v1;

/**
 * Represents the face cards in a playing deck; Jack, Queen, King, Ace.
 * 
 * @author Frankie Fasola, Mike Ginn
 *@version 3/2/17
 */
public class FaceCard extends Card {

    private String faceValue;
    
    public FaceCard(int value, Suite suite, String faceValue) {
	super(value, suite);
	this.faceValue = faceValue;
	// TODO Auto-generated constructor stub
    }
    
    public String getFaceValue() {
	return this.faceValue;
    }
    
    public void setFaceValue(String s) {
	this.faceValue = s;
    }

}
