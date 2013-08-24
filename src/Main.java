import Game.BoardException;
import GraphicalBoard.GraphicalBoardException;
import GraphicalBoard.WindowGame;;

public class Main {
	private static WindowGame __windowGame;
	
	public static void main(String[] args) throws BoardException, GraphicalBoardException {
		while (true) {
			createNewGame(800, 600, 100, 100);
		}
	}
	
	private static void createNewGame(final int parWindowWidth, final int parWindowHeight, final int parGameWidth, final int parGameHeight) throws BoardException, GraphicalBoardException {
		__windowGame = new WindowGame(parWindowWidth, parWindowHeight);
		
		__windowGame.initWindow();
		__windowGame.initGame(parGameWidth, parGameHeight);
		__windowGame.setWindowVisible(true);
		
		__windowGame.launchGame();
	}
}
