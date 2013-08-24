package GraphicalBoard;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Game.Board;
import Game.BoardException;

public class GraphicalBoard extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Color __defaultBackgroundColor = Color.WHITE;
	private final Color __defaultCellColor = Color.BLACK;
	private final int __windowWidth;
	private final int __windowHeight;
	private final Board __board;
	private int __cellWidth;
	private int __cellHeight;

	public GraphicalBoard(final int parWindowWidth, final int parWindowHeight, final int parGameWidth, final int parGameHeight) {
		super();
		setBackground(__defaultBackgroundColor);
		__windowWidth = parWindowWidth;
		__windowHeight = parWindowHeight;
		__board = new Board(parGameWidth, parGameHeight);
		__cellWidth = 0;
		__cellHeight = 0;
	}

	public final void initGraphicalBoard() throws BoardException, GraphicalBoardException {
		__board.initBoard();

		if (!GraphicalBoardException.isValidResolutions(__windowWidth, __windowHeight)) {
			throw GraphicalBoardException.resolutionException("La résolution actuelle n'est pas valide", __windowWidth, __windowHeight);
		}

		__cellWidth = __windowWidth/__board.getWidth();
		__cellHeight = __windowHeight/__board.getHeight();

		if (!GraphicalBoardException.isValidCellSize(__cellWidth, __cellHeight)) {
			throw GraphicalBoardException.cellSizeExceptions("La taille des cellules actuelles n'est pas valide", __cellWidth, __cellHeight);
		}
	}

	public final void nextTurn() throws BoardException {
		__board.nextTurn();
	}
	
	public final boolean isGameFinished() {
		return __board.isGameFinished();
	}

	public final void paintComponent(final Graphics parGraphics) {
		parGraphics.setColor(__defaultCellColor);

		for (int locHeightIdx = 0; locHeightIdx < __board.getHeight(); ++locHeightIdx) {
			for (int locWidthIdx = 0; locWidthIdx < __board.getWidth(); ++locWidthIdx) {
				if (__board.getBoard()[locWidthIdx][locHeightIdx] == 1) {
					parGraphics.fillRect(locWidthIdx * __cellWidth, locHeightIdx * __cellHeight, __cellWidth, __cellHeight);
				}

			}
		}
	}
}
