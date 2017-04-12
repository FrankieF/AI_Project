package v1;

import java.util.ArrayList;

/**
 * The gamelogic of blackjack. This class controls game and interfaces with other classes
 * to deal cards, perform AI moves, etc.
 * 
 * @author Frankie Fasola, Mike Ginn
 *@version 3/2/17
 */
public class GameLogic {

    private Dealer dealer;
    private Player player;
    private AIPlayer ai;
    
    private static ArrayList<Player> players;
    /***
     * Returns the instance of players or creates a new one if there is no instance.
     * @author Francis Fasola
     * @return The instance of players.
     */
    public static ArrayList<Player> getPlayers() {
	return players == null ? players = new ArrayList<Player>() : players;
    }
    
    private GameState state = GameState.Start;
    
    public void setState(GameState state) {
	this.state = state;
    }
    
    public GameLogic () {
	this.dealer = new Dealer();
	this.player = new Player();
	this.ai = new AIPlayer();
    }
    
    public void update() {
	Deck d = Deck.getDeck();
	for(Card c : d.getReadyCards())	    
	    if (c instanceof FaceCard)
		System.out.format("Card: %d %s %s\n", c.getValue(), c.getSuite().toString(), ((FaceCard) c).getFaceValue());
	    else
		System.out.format("Card: %d %s\n", c.getValue(), c.getSuite().toString());
	
	switch (state) {
	case Start:
	    createPlayers();
	    setState(GameState.Playing);
	    break;
	case Playing:
	    deal();
	    playGame();
	    break;
	case End:
	    exit();
	    break;
	default:
	    break;
	}

    }
    
    private void createPlayers() {
	players.add(player);
	players.add(ai);
	players.add(dealer);
    }
    
    private void deal() {
	dealer.setState(GameState.Deal);
    }
    
    private void playGame() {
	boolean isPlaying = true;
	while(isPlaying) {
	    for (Player p : players) {
		p.update();
	    }
	    isPlaying = player.outOfMoney();
	    dealer.setState(GameState.Reset);
	}
	setState(GameState.End);
    }

    private void exit() {
	System.out.println("Game over.\n\tWinner is: ");
	System.exit(0);
    }
}
