/**
 * 
 */
package v1;

/**
 * @author Frankie Fasola
 *
 */
public class Driver {

    public static void main(String[] args) {
	GameLogic gl = new GameLogic();
	gl.setState(GameState.Start);
	gl.update();
    }
}
