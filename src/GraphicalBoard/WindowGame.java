package GraphicalBoard;

import javax.swing.JFrame;

import Game.BoardException;

public class WindowGame {
	private final JFrame __window;
	private final int __windowWidth;
	private final int __windowHeight;
	private final String __title = "Jeu de la vie";
	private GraphicalBoard __graphicalBoard;

	public WindowGame(final int parWindowWidth, final int parWindowHeight) {
		this.__window = new JFrame(this.__title);
		this.__windowWidth = parWindowWidth;
		this.__windowHeight = parWindowHeight;
	}

	final public void initWindow() {
		this.__window.setSize(__windowWidth, __windowHeight);
		this.__window.setLocationRelativeTo(null);
		this.__window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	final public void initGame(final int parGameWidth, final int parGameHeight) throws BoardException, GraphicalBoardException {
		this.__graphicalBoard = new GraphicalBoard(this.__windowWidth, this.__windowHeight, parGameWidth, parGameHeight);

		this.__graphicalBoard.initGraphicalBoard();
	}

	final public void launchGame() throws BoardException {
		while (!__graphicalBoard.isGameFinished()) {
			final long locEndTime = System.currentTimeMillis() + 1000;

			this.__graphicalBoard.nextTurn();
			drawGame();

			while (System.currentTimeMillis() < locEndTime) {
			}
		}
	}

	final public void setWindowVisible(final boolean parVisible) {
		this.__window.setVisible(parVisible);
	}

	final public boolean isGameFinished() {
		return __graphicalBoard.isGameFinished();
	}

	final private void drawGame() {
		this.__window.setContentPane(this.__graphicalBoard);
	}
}