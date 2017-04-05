package v1;

import java.util.ArrayList;

/**
 * Simulates a player's hand
 * @author Michael
 *
 */
public class Hand {
    
    private ArrayList <Card> hand;
    
  public Hand(){
      hand = new ArrayList<Card>();
  }
  
  public Hand(ArrayList <Card> hand){
      this.hand = hand;
  }

}
