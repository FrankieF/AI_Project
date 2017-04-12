package v1;

import java.util.ArrayList;

/**
 * Simulates a player's hand
 * @author Michael
 *
 */
public class Hand {
    
    private ArrayList <Card> hand; //player's hand is an ArrayList of cards
    private int handSize;  //number of cards in hand
    private int handScore; //score of card values in hand
    private int scoreNeeded; // score needed to get blackjack
    private int bet;
    private final int BLACKJACK_VALUE = 21;
    private final int ACE_VALUE = 11;
    
    
   /**
    * Hand constructor creates a new hand
    */
  public Hand(int bet){
      hand = new ArrayList<Card>();
      scoreNeeded = 21;
      this.bet = bet;
  }
  
  /**
   * Hand constructor creates hand
   * from ArrayList of Cards
   * @param hand
   */
  public Hand(ArrayList <Card> hand){
      this.hand = hand;
      for(Card c : hand){
	  handSize++;
	  handScore += c.getValue();
	  scoreNeeded = BLACKJACK_VALUE - handScore;
      }
  }
  
  /**
   * returns number of cards in hand
   * @return
   */
  public int getHandSize(){
      return handSize;
     }
  
  /**
   * sets handSize
   * @param handSize
   */
  public void setHandSize(int handSize){
      this.handSize = handSize;
  }
  
  /**
   * returns score in hand
   * @return
   */
  public int getHandScore(){
      return handScore;
  }
  
  /**
   * sets handScore
   * @param handScore
   */
  public void setHandScore(int handScore){
      this.handScore = handScore;
  }
  
  public int getScoreNeeded(){
      if(handScore >= 21){
	  return 0;
      }
      return scoreNeeded;
  }
  
  public void setScoreNeeded(int scoreNeeded){
      this.scoreNeeded = scoreNeeded;
  }
  
  public int getBet(){
      return bet;
  }
  
  public void setBet(int bet){
      this.bet=bet;
  }
  
  /**
   * adds a card to the hand
   * @param c
   */
  public void addCard(Card c){
      hand.add(c);
      //if card is ace check for bust 
      //if so ace value becomes 1 
      if(c.isAce() && (handScore + ACE_VALUE > BLACKJACK_VALUE)){
	  handScore++;
      }
      else{
      handScore += c.getValue();
      }
      handSize++;
      scoreNeeded = BLACKJACK_VALUE - handScore;
  }
  
  /**
   * checks if hand is empty
   * @return
   */
  public boolean isEmpty(){
      return handSize == 0;
  }
  
  /**
   * checks for player bust
   * @return
   */
  public boolean isBust(){
      return handScore > BLACKJACK_VALUE;
  }
  
  public boolean isBlackJack(){
      return handScore == BLACKJACK_VALUE;
  }
  
  /**
   * displays cards in hand
   */
  @Override
  public String toString(){
      String handString = "";
      if(isEmpty()){
	  return "No cards in hand";
      }
      for(Card c : hand){
	  if(c.isHidden()){
	      handString += "? ";
	  }
	  else{
	  handString += c + " ";
	  }
      }
      return handString;
  }

}
